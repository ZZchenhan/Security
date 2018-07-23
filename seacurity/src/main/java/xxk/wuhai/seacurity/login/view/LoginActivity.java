package xxk.wuhai.seacurity.login.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.navagation.SimpleNavagation;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.UserInfoBean;
import xxk.wuhai.seacurity.common.navagation.LeftTitleNavagation;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.presenter.LoginPrensenter;
import xxk.wuhai.seacurity.login.view.itf.ILoginView;
import xxk.wuhai.seacurity.main.view.MainActivity;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/25 1:16
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    Button btnLogin;
    LoginPrensenter loginPrensenter;

    ImageView ivSee;
    boolean isShow = false;

    /**
     * 手机
     */
    private AppCompatEditText etPhone;

    /**
     * 验证码
     */
    private AppCompatEditText etPass;

    /**
     * 忘记密码
     */
    private TextView tvForgetPass;

    @Override
    public IBasePresenter createPrensenter() {
        loginPrensenter = new LoginPrensenter(this);
        return loginPrensenter;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public IBaseNavagation navagation() {
        return null;
    }

    @Override
    public void initView() {
            loginPrensenter.init();
    }

    @Override
    public void findViews() {
        etPhone = findViewById(R.id.phone);
        etPass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btn_login);
        tvForgetPass = findViewById(R.id.tv_forget_pass);
        ivSee = findViewById(R.id.see);
        btnLogin.setOnClickListener(this);
        tvForgetPass.setOnClickListener(this);
        ivSee.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginPrensenter.login(this, etPhone, etPass);
                break;
            case R.id.tv_forget_pass:
                ForgetActivity.openActivity(this, ForgetActivity.class, 1);
                break;
            case R.id.see:
                isShow = !isShow;
                if(isShow){
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivSee.setImageResource(R.mipmap.icon_see);
                }else{
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivSee.setImageResource(R.mipmap.ic_no_see);
                }
                break;
        }
    }

    @Override
    public void loginSuccess(UserDetailInfo userInfoBean) {
        MainActivity.openActivity(this, MainActivity.class);
        finish();
    }

    @Override
    public void loginFaile(String msg) {
        toast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == requestCode && resultCode == RESULT_OK) {
            etPhone.setText(getIntent().getStringExtra("phone"));
            etPhone.setText(getIntent().getStringExtra("pass"));
            btnLogin.performClick();
        }
    }
}
