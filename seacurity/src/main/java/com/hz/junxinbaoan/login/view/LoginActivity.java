package com.hz.junxinbaoan.login.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.activity.BaseActivity;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.login.bean.UserDetailInfo;
import com.hz.junxinbaoan.login.presenter.LoginPrensenter;
import com.hz.junxinbaoan.login.view.itf.ILoginView;
import com.hz.junxinbaoan.main.view.MainActivity;

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

    /**
     * 电话
     * @return
     */
    private TextView tvCall;

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
        tvCall = findViewById(R.id.call);
        btnLogin.setOnClickListener(this);
        tvForgetPass.setOnClickListener(this);
        ivSee.setOnClickListener(this);

        SpannableString spannableString = new SpannableString("客服电话：0571-87246950");
        ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor("#2196F3"));
        spannableString.setSpan(span, 5, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvCall.setText(spannableString);
        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0571-87246950"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }catch (Exception e){

                }
            }
        });
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
                    ivSee.setImageResource(R.mipmap.ic_no_see);
                }else{
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivSee.setImageResource(R.mipmap.icon_see);
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
