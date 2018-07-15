package xxk.wuhai.seacurity.work.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.AddSuggetResult;
import xxk.wuhai.seacurity.work.vo.AddSusgetVO;

public class SuggestActivity extends BaseActivity {
    TextView btnConfirm;
    EditText editText;
    @Override
    public int layoutId() {
        return R.layout.activity_suggest;
    }
    LeftIconNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
        leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this);
        leftIconNavagation.setTvTitle(new SimpleDateFormat("意见反馈").format(new Date()));
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return leftIconNavagation;
    }

    @Override
    public void initView() {

    }

    @Override
    public void findViews() {
        editText = findViewById(R.id.edit);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aply();
            }
        });
    }
    private void aply(){
        if(editText.getText().toString().length() == 0){
            toast("请输入反馈建议");
            return;
        }
        AddSusgetVO addSusgetVO = new AddSusgetVO(editText.getText().toString());
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .addSuggestion(addSusgetVO).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddSuggetResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddSuggetResult stringResult) {
                        if(!stringResult.getCode().equals("200")){
                            toast(stringResult.getMessage());
                            return;
                        }
                        toast("添加评价成功");
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e!=null)
                             toast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
