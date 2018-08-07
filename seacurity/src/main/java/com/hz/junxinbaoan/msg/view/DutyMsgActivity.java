package com.hz.junxinbaoan.msg.view;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;
import com.hz.junxinbaoan.databinding.ActivityDutyMsgBinding;
import com.hz.junxinbaoan.msg.MsgApi;
import com.hz.junxinbaoan.msg.bean.GetMessageDetailResult;
import com.hz.junxinbaoan.msg.vo.GetMessageDetailVO;

public class DutyMsgActivity extends BaseActivity {

    ActivityDutyMsgBinding binding;

    @Override
    public int layoutId() {
        return R.layout.activity_duty_msg;
    }

    @Override
    protected View databindViews() {
        binding =  DataBindingUtil.inflate(LayoutInflater.from(this),layoutId(),null,false);
        return binding.getRoot();
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "排班变动消息";
            }
        };
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftIconNavagation.setTitleColor(R.color.white);
        return leftIconNavagation;
    }

    @Override
    public void initView() {
        MyApplication.retrofitClient.getRetrofit().create(MsgApi.class)
                .getMsgDetails(
                        new GetMessageDetailVO(getIntent().
                                getIntExtra("msgId",0),
                                getIntent().getStringExtra("msgType")))

                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<GetMessageDetailResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GetMessageDetailResult> getMessageDetailResultResult) {
                        if(isFinishing()){
                            return;
                        }
                        if(!getMessageDetailResultResult.getCode().equals("200")){
                            toast(getMessageDetailResultResult.getMessage());
                            return;
                        }
                        setData(getMessageDetailResultResult.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(!isFinishing()){
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void findViews() {


    }

    private void setData(GetMessageDetailResult result){
       if(result == null){
           return;
       }
        binding.dutyTime.setText(result.getCardPosition().getName());
        binding.changeTime.setText(result.getCardPosition().getScheduleTime());
        binding.workTimes.setText(result.getCardPosition().getScheduleName()
       +" " +result.getCardPosition().getBeginAttendanceTime()+"-"+result.getCardPosition().getEndAttendanceTime() +" "+result.getCardPosition().getWorkTime()+"小时");
        try{
            binding.startLocation.setText(type(result.getCardPosition().getCardPositionInfoVoList().get(0).getAttendanceType()) +"位置:"+result.getCardPosition().getCardPositionInfoVoList().get(0).getAttendanceLocation());
           if(result.getCardPosition().getCardPositionInfoVoList().size()-1 == 0){
               return;
           }
            binding.endLoction.setText(type(result.getCardPosition().getCardPositionInfoVoList().get(result.getCardPosition().getCardPositionInfoVoList().size()-1).getAttendanceType()) +"位置:"+result.getCardPosition().getCardPositionInfoVoList().get(1).getAttendanceLocation());
        }catch (Exception e){

        }
    }


    private String type(String type){
        switch (type){
            case "0":
                return "上班";
            case "1":
                return "中途";
            default:
                return "下班";
        }
    }
}
