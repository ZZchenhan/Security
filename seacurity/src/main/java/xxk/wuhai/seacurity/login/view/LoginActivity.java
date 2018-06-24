package xxk.wuhai.seacurity.login.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.navagation.SimpleNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftTitleNavagation;
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
public class LoginActivity extends BaseActivity implements View.OnClickListener{

    Button btnLogin;

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public IBaseNavagation navagation() {
        return new LeftTitleNavagation(this) {
            @Override
            public String title() {
                return "登录";
            }
        }.setTitleColor(R.color.white).
                setNavagationBackgroudColor(R.color.colorPrimary);
    }

    @Override
    public void initView() {

    }

    @Override
    public void findViews() {
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                MainActivity.openActivity(this,MainActivity.class);
                break;
        }
    }
}
