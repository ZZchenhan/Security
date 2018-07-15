package xxk.wuhai.seacurity.work.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;

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
