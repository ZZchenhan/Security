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
import xxk.wuhai.seacurity.databinding.ActivityLeaveBinding;
import xxk.wuhai.seacurity.utils.GlideUtils;
import xxk.wuhai.seacurity.utils.PesonInfoHelper;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.ApDetailResult;
import xxk.wuhai.seacurity.work.vo.ApDetailVo;

/**
 * 审批状态消息
 */
public class LeaveActivity extends BaseActivity {
    ActivityLeaveBinding binding;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    @Override
    public int layoutId() {
        return R.layout.activity_leave;
    }


    @Override
    protected View databindViews() {
        binding =  DataBindingUtil.inflate(LayoutInflater.from(this),layoutId(),null,false);
        return super.databindViews();
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
        imageView1 = findViewById(R.id.pic1);
        imageView2 = findViewById(R.id.pic2);
        imageView3 = findViewById(R.id.pic3);
    }

    /**
     *
     * @param context
     * @param id
     * @param type 是否能够审核
     */
    public static void openActivity(Context context, int id, int type){
        context.startActivity(new Intent(context,LeaveActivity.class)
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
                        binding.name.setText(apDetailBean.getApName());
                        binding.startTime.setText(apDetailBean.getLrBeginTime());
                        binding.endTime.setText(apDetailBean.getLrEndTime());
                        binding.days.setText(apDetailBean.getLraDays());
                        binding.type.setText(PesonInfoHelper.leaveType(apDetailBean.getTypeId()));
                        binding.result.setText(apDetailBean.getSupplement()+"");
                        try {
                            Glide.with(LeaveActivity.this)
                                    .load(apDetailBean.getPictureUrls().get(0)).into(imageView1);
                            Glide.with(LeaveActivity.this)
                                    .load(apDetailBean.getPictureUrls().get(1)).into(imageView2);
                            Glide.with(LeaveActivity.this)
                                    .load(apDetailBean.getPictureUrls().get(2)).into(imageView3);
                        }catch (Exception e){
                            e.getMessage();
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
