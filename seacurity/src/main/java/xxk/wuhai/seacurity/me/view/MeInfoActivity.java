package xxk.wuhai.seacurity.me.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;

public class MeInfoActivity extends BaseActivity {

    MeInfoFragment meInfoFragment = new MeInfoFragment();
    MeInfoUpdateFragment meInfoUpdateFragment = new MeInfoUpdateFragment();


    @Override
    public int layoutId() {
        return R.layout.activity_me_info;
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
        getSupportFragmentManager().beginTransaction().add(R.id.content,meInfoFragment).
                add(R.id.content,meInfoUpdateFragment)
                .show(meInfoFragment).hide(meInfoUpdateFragment).commit();
    }

    @Override
    public void findViews() {

    }


    public void change(){
        getSupportFragmentManager().beginTransaction().hide(meInfoFragment).show(meInfoUpdateFragment).commit();
    }
}
