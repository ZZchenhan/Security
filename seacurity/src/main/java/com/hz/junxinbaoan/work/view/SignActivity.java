package com.hz.junxinbaoan.work.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;

/**
 * Created by 86936 on 2018/6/29.
 *
 * @QQ 869360026
 */

public class SignActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    private FrameLayout content;

    private RadioGroup rg;

    @Override
    public int layoutId() {
        return R.layout.activity_sign;
    }
    LeftIconNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
        leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this);
        leftIconNavagation.setTvTitle("签到");
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return leftIconNavagation;
    }
    SignFragment signFragment;
    SignListFragment signListFragment;
    @Override
    public void initView() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.sign){
                    fragmentManager.beginTransaction().hide(signListFragment).show(signFragment).commit();
                    leftIconNavagation.setTvTitle("签到");
                }else{
                    fragmentManager.beginTransaction().hide(signFragment).show(signListFragment).commit();
                    leftIconNavagation.setTvTitle("签到详情");
                }
            }
        });
        rg.check(R.id.sign);
    }

    @Override
    public void findViews() {
        fragmentManager = getSupportFragmentManager();
        content = findViewById(R.id.content);
        rg = findViewById(R.id.rg);
        signFragment = new SignFragment();
        signListFragment = new SignListFragment();
       fragmentManager.beginTransaction().add(R.id.content,signFragment,"sign").add(R.id.content,signListFragment,"signList").commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        signFragment.onActivityResult(requestCode,resultCode,data);
    }
}
