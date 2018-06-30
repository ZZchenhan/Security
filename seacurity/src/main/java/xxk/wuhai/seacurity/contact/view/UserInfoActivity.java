package xxk.wuhai.seacurity.contact.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;

public class UserInfoActivity extends BaseActivity {

    private TextView name;

    private TextView userName;

    private TextView tvZhiwei;

    private TextView sex;

    private TextView age;

    private TextView phone;

    private TextView tvZan;

    private TextView tvCai;

    private TextView tag1;
    private TextView tag2;
    private TextView tag3;
    private TextView tag4;
    private TextView tag5;
    private TextView see;

    @Override
    public void findViews() {
        name = findViewById(R.id.name);
        userName = findViewById(R.id.user_name);
        tvZhiwei = findViewById(R.id.zhiwei);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        tvZan = findViewById(R.id.zan);
        tvCai = findViewById(R.id.cai);
        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        tag3 = findViewById(R.id.tag3);
        tag4 = findViewById(R.id.tag4);
        tag5 = findViewById(R.id.tag5);
        see = findViewById(R.id.tv_see);

        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddImpressionActivity.openActivity(UserInfoActivity.this,AddImpressionActivity.class);
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "个人信息";
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

    }



}
