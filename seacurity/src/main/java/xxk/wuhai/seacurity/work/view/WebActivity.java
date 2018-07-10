package xxk.wuhai.seacurity.work.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;

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
import xxk.wuhai.seacurity.databinding.ActivityWebBinding;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.StudyDetail;
import xxk.wuhai.seacurity.work.vo.GetStudyNoticeVo;

public class WebActivity extends BaseActivity {

    private  static String title;

    @Override
    public int layoutId() {
        return R.layout.activity_web;
    }
    ActivityWebBinding binding;
    @Override
    protected View databindViews() {
        binding =  DataBindingUtil.inflate(LayoutInflater.from(this),layoutId(),null,false);
        return binding.getRoot();
    }

    LeftIconNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
         leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "我的排班";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
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
        leftIconNavagation.setTvTitle(title);
        if(title.equals("学习中心")){
            getStudyDetails();
        }
    }

    @Override
    public void findViews() {

    }

    public static void openActivity(Context context, String title, int id){
        WebActivity.title = title;
        context.startActivity(new Intent(context,WebActivity.class).putExtra("id",id));
    }


    private void getStudyDetails(){
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getStudyDetails(new GetStudyNoticeVo(getIntent().getIntExtra("id",0)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<StudyDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<StudyDetail> studyDetailResult) {
                        if(!studyDetailResult.getCode().equals("200")){
                            toast(studyDetailResult.getMessage());
                            return;
                        }
                        binding.title.setText(studyDetailResult.getResult().getStudyNoticeVo().getStudyTitle());
                        binding.time.setText(studyDetailResult.getResult().getStudyNoticeVo().getStudyCreateTime());
                        binding.content.setText(Html.fromHtml(studyDetailResult.getResult().getStudyNoticeVo().getStudyContent()));
                        binding.from.setText(studyDetailResult.getResult().getStudyNoticeVo().getCompanyName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e!=null){
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
