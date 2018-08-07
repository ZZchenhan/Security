package com.hz.junxinbaoan.main.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/25 23:40
 */
public class SettingActivity extends BaseActivity {


    TextView tvCacheSize;

    LinearLayout llSeting;

    @Override
    public int layoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "修改个人信息";
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
        tvCacheSize.setText("共"+20+"M缓存文件");
    }

    @Override
    public void findViews() {
        tvCacheSize = findViewById(R.id.cache_size);
        llSeting = findViewById(R.id.rl_clean);
        llSeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("清楚缓存");
            }
        });
    }
}
