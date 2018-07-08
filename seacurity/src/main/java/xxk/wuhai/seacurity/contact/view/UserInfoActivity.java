package xxk.wuhai.seacurity.contact.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.contact.bean.DirectoryVo;
import xxk.wuhai.seacurity.login.bean.UserInfoBean;

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

    DirectoryVo directoryVo;

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
        directoryVo = (DirectoryVo) getIntent().getSerializableExtra("data");
        if(directoryVo == null){
            return;
        }

        setDada(directoryVo);
    }



    private void setDada(DirectoryVo userDetailInfo){
        name.setText(userDetailInfo.getName()==null?"":userDetailInfo.getName().length()<2?userDetailInfo.getName():userDetailInfo.getName().substring(userDetailInfo.getName().length()-2,userDetailInfo.getName().length()));
        userName.setText(userDetailInfo.getName()+"");
        tvZhiwei.setText(MyApplication.userDetailInfo.getDeptVo()==null?"":MyApplication.userDetailInfo.getDeptVo().getDeptName()+"");
        sex.setText(userDetailInfo.getSex().equals("0")?"女":"男");
        age.setText(userDetailInfo.getSex()+"");
        phone.setText(userDetailInfo.getPhone()+"");
        tvZan.setText(String.format("赞：%s","0"));
        tvCai.setText(String.format("踩：%s","0"));
        tag1.setVisibility(View.GONE);
        tag2.setVisibility(View.GONE);
        tag3.setVisibility(View.GONE);
        tag4.setVisibility(View.GONE);
        tag5.setVisibility(View.GONE);
        see .setVisibility(View.GONE);
    }


    public static void openActivity(Context context,DirectoryVo directoryVo){
        context.startActivity(new Intent(context,UserInfoActivity.class).putExtra("data",directoryVo));
    }


}
