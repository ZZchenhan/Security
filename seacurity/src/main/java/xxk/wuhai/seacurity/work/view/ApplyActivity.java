package xxk.wuhai.seacurity.work.view;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.msg.view.LeaveActivity;
import xxk.wuhai.seacurity.weight.HinitDialog;

public class ApplyActivity extends BaseActivity {

    @Override
    public int layoutId() {
        return R.layout.activity_apply;
    }
    HinitDialog dialog;
    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "审批申请";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(view -> finish());
        leftIconNavagation.setTitleColor(R.color.white);
        return leftIconNavagation;
    }

    @Override
    public void initView() {

    }

    @Override
    public void findViews() {

    }

    @SuppressLint("WrongConstant")
    public void onClick(View view){
        switch (view.getId()){
            case R.id.levae:
                ApplyLeaveActivity.openActivity(this,ApplyLeaveActivity.class);
                break;
            case R.id.supplement:
                SupplementSignActivity.openActivity(this,SupplementSignActivity.class);
                break;
            case R.id.tv_apply:
                MyAplyListActivity.openActivity(this,0);
                break;
            case R.id.tv_recive:
//                if(dialog == null){
//                    dialog = new HinitDialog(this);
//                }
//                dialog.show();
////                Toast toast =  new Toast(Utils.getApp());
////                toast.setView(LayoutInflater.from(ApplyActivity.this).inflate(R.layout.layout_no_permision,null,false));
////                toast.setDuration(Toast.LENGTH_LONG);
////                toast.setGravity(Gravity.CENTER,0,0);
////                toast.show();
                MyAplyListActivity.openActivity(this,1);
                break;
        }
    }
}
