package xxk.wuhai.seacurity.msg.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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
import xxk.wuhai.seacurity.databinding.ActivityExamineBinding;
import xxk.wuhai.seacurity.utils.PesonInfoHelper;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.ApDetailResult;
import xxk.wuhai.seacurity.work.vo.ApDetailVo;

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
                                Glide.with(ExamineActivity.this)
                                        .load(apDetailBean.getPictureUrls().get(1)).into(binding.pic2);
                                Glide.with(ExamineActivity.this)
                                        .load(apDetailBean.getPictureUrls().get(2)).into(binding.pic3);
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
