package xxk.wuhai.seacurity.work.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;

public class RecordFaileActivity extends BaseActivity {

    private TextView distance;
    private TextView current;
    private TextView location;

    @Override
    public int layoutId() {
        return R.layout.activity_record_faile;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "不在打卡范围";
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
        distance.setText(  getIntent().getStringExtra("distance") == null?"未知": getIntent().getStringExtra("distance")+"米");
        location.setText(  getIntent().getStringExtra("location") == null?"未知": getIntent().getStringExtra("location"));
        current.setText(  getIntent().getStringExtra("current") == null?"未知": getIntent().getStringExtra("current"));
    }

    @Override
    public void findViews() {
        distance = findViewById(R.id.distance);
        current = findViewById(R.id.current);
        location = findViewById(R.id.location);
    }
}
