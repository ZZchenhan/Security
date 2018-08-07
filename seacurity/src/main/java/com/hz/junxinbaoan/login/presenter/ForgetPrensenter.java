package com.hz.junxinbaoan.login.presenter;

import android.app.Activity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.utils.PhoneUtils;
import sz.tianhe.baselib.view.IBaseView;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.login.bean.CodeBean;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.login.bean.UpdateBean;
import com.hz.junxinbaoan.login.api.UserApi;
import com.hz.junxinbaoan.login.view.itf.IForgetView;
import com.hz.junxinbaoan.login.vo.ForgetPassVo;
import com.hz.junxinbaoan.login.vo.GetCodeVo;

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
            return;
        }

        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .updatePass(new ForgetPassVo("2",etPhone.getText().toString(),etCode.getText().toString(),etPass.getText().toString()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<UpdateBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UpdateBean> updateBeanResult) {
                        if(iForgetView == null){
                            return;
                        }
                        if(updateBeanResult.getCode().equals("200")){
                            if(updateBeanResult.getResult().getStatus().equals("0")){
                                iForgetView.updateForgetFaile("修改密码失败");
                            }else{
                                iForgetView.updateForgetSuccess();
                            }
                        }else{
                            iForgetView.toast(updateBeanResult.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(iForgetView == null){
                            return;
                        }
                        iForgetView.toast("程序出现异常");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getCode(final TextView phone,final TextView tvShow){
        tvShow.setClickable(false);
        if(!PhoneUtils.isMobile0(phone.getText().toString())){
            iForgetView.toast("请输入正确的手机号码");
            tvShow.setClickable(true);
            return;
        }

        MyApplication.retrofitClient.getRetrofit().create(UserApi.class).
                getCode(new GetCodeVo(phone.getText().toString(),"2"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<CodeBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CodeBean> codeBeanResult) {
                        if(codeBeanResult.getCode().equals("200")){
                            Observable.interval(1,TimeUnit.SECONDS)
                                    .take(60).observeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Observer<Long>() {
                                        @Override
                                        public void onSubscribe(Disposable d) {

                                        }

                                        @Override
                                        public void onNext(Long aLong) {
                                            if(tvShow!=null)
                                            tvShow.setText(60-aLong+"秒");
                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onComplete() {
                                            if(tvShow!=null)
                                            tvShow.setClickable(true);
                                        }
                                    });
                        }else{
                            if(iForgetView!=null)
                            iForgetView.toast(codeBeanResult.getMessage());
                            tvShow.setClickable(true);
                        }
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
