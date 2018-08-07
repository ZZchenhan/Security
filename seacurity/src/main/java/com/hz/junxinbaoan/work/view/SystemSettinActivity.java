package com.hz.junxinbaoan.work.view;

import android.view.View;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;

public class SystemSettinActivity extends BaseActivity {

    private LinearLayout rl_clean;

    @Override
    public int layoutId() {
        return R.layout.activity_system_settin;
    }
    LeftIconNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
        leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this);
        leftIconNavagation.setTvTitle(new SimpleDateFormat("系统设置").format(new Date()));
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return leftIconNavagation;
    }

    @Override
    public void initView() {

    }

    @Override
    public void findViews() {
        rl_clean = findViewById(R.id.rl_clean);
        rl_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("清除成功");
            }
        });
    }
}
