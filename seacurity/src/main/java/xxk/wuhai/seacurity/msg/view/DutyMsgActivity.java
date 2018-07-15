package xxk.wuhai.seacurity.msg.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.databinding.ActivityDutyMsgBinding;
import xxk.wuhai.seacurity.msg.MsgApi;
import xxk.wuhai.seacurity.msg.adapter.DutyMsgAdapter;
import xxk.wuhai.seacurity.msg.bean.GetMessageDetailResult;
import xxk.wuhai.seacurity.msg.vo.GetMessageDetailVO;

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
                return "排班变动信息";
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
        binding.dutyTime.setText(result.getCardPosition().getScheduleTime());
        binding.changeTime.setText(result.getCardPosition().getWorkTime());
        binding.workTimes.setText(result.getCardPosition().getScheduleName()
        +result.getCardPosition().getBeginAttendanceTime()+"-"+result.getCardPosition().getEndAttendanceTime());
        try{
            binding.startLocation.setText(result.getCardPosition().getCardPositionInfoVoList().get(0).getAttendanceLocation());
            binding.endLoction.setText(result.getCardPosition().getCardPositionInfoVoList().get(1).getAttendanceLocation());
        }catch (Exception e){

        }
    }

}
