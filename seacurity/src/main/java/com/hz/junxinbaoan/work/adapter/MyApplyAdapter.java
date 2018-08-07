package com.hz.junxinbaoan.work.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.utils.PesonInfoHelper;
import com.hz.junxinbaoan.work.bean.ApprovalRecordListBean;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class MyApplyAdapter extends BaseQuickAdapter<ApprovalRecordListBean,BaseViewHolder> {
    public MyApplyAdapter(@Nullable List<ApprovalRecordListBean> data) {
        super(R.layout.item_my_aply,data);
    }
    public int type = 0;//0我提交的 1我审核的

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, ApprovalRecordListBean item) {
        if(type == 0) {
            helper.setText(R.id.type, item.getPatchTime() != null && !item.getPatchTime().equals("")? "补签申请" : "请假申请")
                    .setText(R.id.status, PesonInfoHelper.aplyStatus2(item.getStatus()))
                    .setText(R.id.name, "姓名:" + item.getApName())
                    .setText(R.id.time, item.getPatchTime() != null &&!item.getPatchTime().equals("") ? "补签时间：" +
                            item.getPatchTime().replace("-", ".") :
                            "请假时间:" + item.getLrBeginTime().replace("-", ".") +
                                    "-" + item.getLrEndTime().replace("-", "."))
                    .setText(R.id.times, item.getCreateTime())
                    .setText(R.id.reson, "事由：" + (item.getSupplement() == null ? "" : item.getSupplement()));
            if (PesonInfoHelper.aplyStatus2(item.getStatus()).equals("待审批")) {
                helper.setTextColor(R.id.status, Color.parseColor("#F43530"));
            } else {
                helper.setTextColor(R.id.status, Color.parseColor("#49B1FA"));
            }
        }else{
            helper.setText(R.id.type, item.getPatchTime() != null && !item.getPatchTime().equals("") ? "补签申请" : "请假申请")
                    .setText(R.id.status, PesonInfoHelper.aplyStatus2(item.getStatus()))
                    .setText(R.id.name, "姓名:" + item.getApName())
                    .setText(R.id.time, item.getPatchTime() != null &&!item.getPatchTime().equals("") ? "补签时间：" +
                            item.getPatchTime().replace("-", ".") :
                            "请假时间:" + item.getLrBeginTime().replace("-", ".") +
                                    "-" + item.getLrEndTime().replace("-", "."))
                    .setText(R.id.times, item.getCreateTime())
                    .setText(R.id.reson, "事由：" + (item.getSupplement() == null ? "" : item.getSupplement()));
            if (PesonInfoHelper.aplyStatus2(item.getStatus()).equals("待审批")) {
                helper.setTextColor(R.id.status, Color.parseColor("#F43530"));
            } else {
                helper.setTextColor(R.id.status, Color.parseColor("#49B1FA"));
            }
        }
    }
}
