package xxk.wuhai.seacurity.work.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.msg.view.LeaveActivity;

public class ApplyActivity extends BaseActivity {

    @Override
    public int layoutId() {
        return R.layout.activity_apply;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "审批申请";
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

    @Override
    public void findViews() {

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.levae:
                ApplyLeaveActivity.openActivity(this,ApplyLeaveActivity.class);
                break;
            case R.id.supplement:
                SupplementSignActivity.openActivity(this,SupplementSignActivity.class);
                break;
            case R.id.tv_apply:
                MyAplyListActivity.openActivity(this,1);
                break;
            case R.id.tv_recive:
                MyAplyListActivity.openActivity(this,0);
                break;
        }
    }
}
