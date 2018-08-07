package com.hz.junxinbaoan.msg.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;

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
import com.hz.junxinbaoan.databinding.ActivityExamineBinding;
import com.hz.junxinbaoan.utils.PesonInfoHelper;
import com.hz.junxinbaoan.weight.photoview.PhoneDialog;
import com.hz.junxinbaoan.work.api.WorkDutyApi;
import com.hz.junxinbaoan.work.bean.ApDetailResult;
import com.hz.junxinbaoan.work.vo.ApDetailVo;

public class ExamineActivity extends BaseActivity {
    ActivityExamineBinding binding;

    @Override
    public int layoutId() {
        return R.layout.activity_examine;
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
                return "审批状态消息";
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
        initData();
    }

    @Override
    public void findViews() {

    }

    /**
     *
     * @param context
     * @param id
     * @param type 是否能够审核
     */
    public static void openActivity(Context context,int id,int type){
        context.startActivity(new Intent(context,ExamineActivity.class)
        .putExtra("id",id)
                .putExtra("type",type)
        );
    }

    public void initData(){
        MyApplication.retrofitClient
                .getRetrofit().create(WorkDutyApi.class)
                .apDetail(new ApDetailVo(getIntent().getIntExtra("id",0)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<ApDetailResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ApDetailResult> detail) {
                        if(!detail.getCode().equals("200")){
                            toast(detail.getMessage());
                            return;
                        }
                        ApDetailResult.ApDetailBean apDetailBean = detail.getResult().getApDetail();
                        if(apDetailBean == null){
                            toast("获取详情为空");
                            return;
                        }
                        binding.name.setText(apDetailBean.getName());
                        binding.time.setText(apDetailBean.getPatchTime());
                        binding.type.setText("补签");
                        binding.result.setText(apDetailBean.getSupplement()+"");
                        try {
                            if(apDetailBean.getPictureUrls() == null || apDetailBean.getPictureUrls().size() == 0){
                                binding.llPic.setVisibility(View.GONE);
                            }else {
                                Glide.with(ExamineActivity.this)
                                        .load(apDetailBean.getPictureUrls().get(0)).into(binding.pic1);
                                binding.pic1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        PhoneDialog.seePic(ExamineActivity.this,apDetailBean.getPictureUrls(),0);
                                    }
                                });
                                Glide.with(ExamineActivity.this)
                                        .load(apDetailBean.getPictureUrls().get(1)).into(binding.pic2);
                                binding.pic2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        PhoneDialog.seePic(ExamineActivity.this,apDetailBean.getPictureUrls(),1);
                                    }
                                });
                                Glide.with(ExamineActivity.this)
                                        .load(apDetailBean.getPictureUrls().get(2)).into(binding.pic3);
                                binding.pic3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        PhoneDialog.seePic(ExamineActivity.this,apDetailBean.getPictureUrls(),2);
                                    }
                                });
                            }
                        }catch (Exception e){

                        }
                        binding.name1.setText(apDetailBean.getApproverName());
                        binding.resul1.setText(PesonInfoHelper.detailStatus(apDetailBean.getStatus()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
