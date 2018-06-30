package xxk.wuhai.seacurity.login.presenter;

import android.app.Activity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.IBaseView;
import xxk.wuhai.seacurity.login.view.itf.IForgetView;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/25 22:50
 */
public class ForgetPrensenter implements IBasePresenter {

    IForgetView iForgetView;

    @Override
    public void attachView(IBaseView view) {
        this.iForgetView = (IForgetView) view;
        if(obeObservable != null){
            obeObservable.unsubscribeOn(AndroidSchedulers.mainThread());
        }
    }

    @Override
    public void dettacheView() {
        iForgetView = null;
    }

    @Override
    public void init() {

    }

    public void updatePass(Activity activity, AppCompatEditText etPhone,AppCompatEditText etCode,AppCompatEditText etPass,AppCompatEditText etConfirm){
        if(TextUtils.isEmpty(etPhone.getText().toString())){
            iForgetView.toast("请输入手机号码");
            return;
        }

        if(TextUtils.isEmpty(etCode.getText().toString())){
            iForgetView.toast("请输入验证码");
            return;
        }

        if(TextUtils.isEmpty(etPass.getText().toString())){
            iForgetView.toast("请输入密码");
            return;
        }

        if(TextUtils.isEmpty(etConfirm.getText().toString())){
            iForgetView.toast("请再次输入密码");
            return;
        }
        if(etConfirm.getText().toString().length() <6|| etConfirm.getText().toString().length()>18
                || etPass.getText().toString().length() <6|| etPass.getText().toString().length()>18
                ){
            iForgetView.toast("密码长度在6-18位之间");
            return;
        }


        if(!etConfirm.getText().toString().equals(etPass.getText().toString())){
            iForgetView.toast("两次密码不一致");
        }
        iForgetView.updateForgetSuccess();
    }
    Observable obeObservable;
    Observer observer;
    public void getCode(final TextView textView){
        if(observer == null || obeObservable==null) {
            observer = new Observer<Long>() {
                @Override
                public void onSubscribe(Disposable d) {
                    textView.setClickable(false);
                }

                @Override
                public void onNext(Long o) {
                    textView.setText("剩余" + o + "秒");
                }

                @Override
                public void onError(Throwable e) {
                    obeObservable = null;
                }

                @Override
                public void onComplete() {
                    obeObservable = null;
                    textView.setClickable(true);
                    textView.setText("获取验证码");
                }
            };

            obeObservable = Observable.interval(1, TimeUnit.SECONDS).take(60).map(new Function<Long, Long>() {
                @Override
                public Long apply(Long aLong) throws Exception {
                    return new Long(60 - aLong);
                }
            }).observeOn(AndroidSchedulers.mainThread());
        }

        obeObservable.subscribe(observer);
    }

}
