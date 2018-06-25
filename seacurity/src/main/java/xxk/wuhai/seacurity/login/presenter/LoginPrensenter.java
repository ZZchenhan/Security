package xxk.wuhai.seacurity.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.IBaseView;
import xxk.wuhai.seacurity.login.view.itf.ILoginView;

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

    }

    public void login(Activity activity, AppCompatEditText phone, AppCompatEditText pass) {
        if (TextUtils.isEmpty(phone.getText().toString())) {
            loginView.toast("请输入手机号码");
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
        loginView.loginSuccess(null);
    }
}
