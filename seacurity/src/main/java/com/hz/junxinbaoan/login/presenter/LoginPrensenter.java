package com.hz.junxinbaoan.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;


import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.utils.PhoneUtils;
import sz.tianhe.baselib.view.IBaseView;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.login.api.UserApi;
import com.hz.junxinbaoan.login.bean.UserDetailInfo;
import com.hz.junxinbaoan.login.result.LoginResult;
import com.hz.junxinbaoan.login.vo.GetUserInfoVo;
import com.hz.junxinbaoan.login.vo.LoginBean;
import com.hz.junxinbaoan.login.view.itf.ILoginView;
import com.hz.junxinbaoan.utils.ShareUtlts;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/25 22:33
 */
public class LoginPrensenter implements IBasePresenter {

    ILoginView loginView;

    private Context mContext;
    public LoginPrensenter(Context context){
        this.mContext = context;
    }

    @Override
    public void attachView(IBaseView view) {
        this.loginView = (ILoginView) view;
    }

    @Override
    public void dettacheView() {
        loginView = null;
    }

    @Override
    public void init() {
//        test();
    }

    public void login(Activity activity, AppCompatEditText phone, AppCompatEditText pass) {
        if (TextUtils.isEmpty(phone.getText().toString())) {
            loginView.toast("请输入手机号码");
            return;
        }
        if (!PhoneUtils.isMobile0(phone.getText().toString())) {
            loginView.toast("请输入正确的手机号码");
            return;
        }

        if (TextUtils.isEmpty(pass.getText().toString())) {
            loginView.toast("请输入密码");
            return;
        }

        if (pass.getText().toString().length() < 6 || pass.getText().toString().length() > 18) {
            loginView.toast("密码长度在6-18位之间");
            return;
        }
        final LoginBean loginBean = new LoginBean(MyApplication.deviceId, phone.getText().toString(), pass.getText().toString());
        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .login(loginBean).subscribeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.newThread())
        .flatMap(new Function<Result<LoginResult>, ObservableSource<Result<UserDetailInfo>>>() {
            @Override
            public ObservableSource<Result<UserDetailInfo>> apply(Result<LoginResult> loginResultResult) throws Exception {
                if(loginResultResult.getCode().equals("200")){
                    BaseInterceptor.token = loginResultResult.getResult().getAccessToken();
                    BaseInterceptor.name = loginResultResult.getResult().getUsername();
                    BaseInterceptor.random = loginResultResult.getResult().getRandom()+"";
                    return MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                            .getUserInfo(new GetUserInfoVo());
                }else if(!loginResultResult.getCode().equals("1038")){
                    throw new RuntimeException(loginResultResult.getMessage());
                }else{
                    throw new RuntimeException("");
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Result<UserDetailInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<UserDetailInfo> result) {
                if(loginView==null){
                    return;
                }
                if(result.getCode().equals("200")){
                    ShareUtlts.save(mContext,BaseInterceptor.token,BaseInterceptor.random,BaseInterceptor.name);
                    MyApplication.userDetailInfo = result.getResult();
                    JPushInterface.setAlias(mContext,1,MyApplication.userDetailInfo.getUserInfo().getPhone()+"");
                    Set<String> tags = new HashSet<>();
                    tags.add(MyApplication.userDetailInfo.getDeptVo().getOrgId()+"");
                    tags.add(MyApplication.userDetailInfo.getDeptVo().getDeptId()+"");
                    JPushInterface.setTags(mContext,3123,tags);
                    loginView.loginSuccess(result.getResult());
                }else if(result.getCode().equals("1038")){
                    throw new RuntimeException("");
                }else{
                    if(loginView!=null){
                        loginView.toast(result.getMessage() == null || result.getMessage().equals("")?"系统错误码:"+result.getCode():result.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if(loginView!=null){
                    if(!e.getMessage().equals("")) {
                        loginView.toast(e.getMessage());
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void test(){
        final LoginBean loginBean = new LoginBean("3252096g7ec80aa4367cdea5511e0b8fa371e555", "13903844569","844569");
        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .login(loginBean).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .flatMap(new Function<Result<LoginResult>, ObservableSource<Result<UserDetailInfo>>>() {
                    @Override
                    public ObservableSource<Result<UserDetailInfo>> apply(Result<LoginResult> loginResultResult) throws Exception {
                        if(loginResultResult.getCode().equals("200")){
                            BaseInterceptor.token = loginResultResult.getResult().getAccessToken();
                            BaseInterceptor.name = loginResultResult.getResult().getUsername();
                            BaseInterceptor.random = loginResultResult.getResult().getRandom()+"";
                            return MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                                    .getUserInfo(new GetUserInfoVo());
                        }else{
                            throw new RuntimeException(loginResultResult.getMessage());
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<UserDetailInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserDetailInfo> result) {
                        if(loginView==null){
                            return;
                        }
                        if(result.getCode().equals("200")){
                            MyApplication.userDetailInfo = result.getResult();
                            loginView.loginSuccess(result.getResult());
                            JPushInterface.setAlias(mContext,1,MyApplication.userDetailInfo.getUserInfo().getPhone()+"");
                            Set<String> tags = new HashSet<>();
                            tags.add(MyApplication.userDetailInfo.getDeptVo().getOrgId()+"");
                            tags.add(MyApplication.userDetailInfo.getDeptVo().getDeptId()+"");
                            JPushInterface.setTags(mContext,3123,tags);
                        }else{
                            if(loginView!=null){
                                loginView.toast(result.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(loginView!=null){
                            loginView.toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
