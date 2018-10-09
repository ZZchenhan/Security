package com.hz.junxinbaoan.login.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;
import com.hz.junxinbaoan.login.presenter.ForgetPrensenter;
import com.hz.junxinbaoan.login.view.itf.IForgetView;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/25 22:15
 */
public class ForgetActivity extends BaseActivity implements IForgetView {

    /**
     * 手机号码
     */
    private AppCompatEditText etPhone;

    /**
     * 验证码
     */
    private AppCompatEditText etCode;

    /**
     * 获取验证码Code
     */
    private TextView tvGetCode;


    /**
     * 密码
     */
    private AppCompatEditText etPass;


    /**
     * 确认验证码
     */
    private AppCompatEditText etConfirmPass;

    /**
     * 确认按钮
     */
    private Button btnConfirm;


    private ImageView ivSee1;

    private ImageView ivSee2;

    private boolean isShow1 = false;

    private boolean isSho2 = false;

    private TextView tvCall;

    @Override
    public int layoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "忘记密码";
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
        etPhone = findViewById(R.id.phone);
        etCode = findViewById(R.id.code);
        tvGetCode = findViewById(R.id.tv_get_code);
        etPass = findViewById(R.id.pass);
        etConfirmPass = findViewById(R.id.confirm_pass);
        btnConfirm = findViewById(R.id.btn_confirm);
        ivSee1 = findViewById(R.id.see);
        ivSee2 = findViewById(R.id.see2);
        ivSee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShow1 = !isShow1;
                if(isShow1){
                    etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivSee1.setImageResource(R.mipmap.ic_no_see);
                }else{
                    etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivSee1.setImageResource(R.mipmap.icon_see);
                }
            }
        });
        ivSee2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSho2 = !isSho2;
                if(isSho2){
                    etConfirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivSee2.setImageResource(R.mipmap.ic_no_see);
                }else{
                    etConfirmPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivSee2.setImageResource(R.mipmap.icon_see);
                }
            }
        });
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ForgetPrensenter)presenter).getCode(etPhone,tvGetCode);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ForgetPrensenter)presenter).updatePass(ForgetActivity.this,etPhone,etCode,etPass,etConfirmPass);
            }
        });
        tvCall = findViewById(R.id.tv_call);
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
    public IBasePresenter createPrensenter() {
        return new ForgetPrensenter();
    }

    @Override
    public void findViews() {

    }

    @Override
    public void updateForgetSuccess() {
        Intent intent = new Intent();
        intent.putExtra("phone",etPhone.getText().toString());
        intent.putExtra("pass",etPass.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void updateForgetFaile(String msg) {
        toast(msg);
    }
}
