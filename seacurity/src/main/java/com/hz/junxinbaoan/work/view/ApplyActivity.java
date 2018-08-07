package com.hz.junxinbaoan.work.view;

import android.annotation.SuppressLint;
import android.view.View;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;
import com.hz.junxinbaoan.weight.HinitDialog;

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
