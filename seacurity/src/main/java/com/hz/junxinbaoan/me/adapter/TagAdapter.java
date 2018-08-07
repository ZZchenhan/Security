package com.hz.junxinbaoan.me.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.login.result.TagResult;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class TagAdapter extends BaseMultiItemQuickAdapter<TagResult.ResultBean.LabelVoListBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TagAdapter(List<TagResult.ResultBean.LabelVoListBean> data) {
        super(data);
        addItemType(0, R.layout.layout_tag);
        addItemType(1, R.layout.layout_tag_see);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagResult.ResultBean.LabelVoListBean item) {
        helper.addOnClickListener(R.id.tag);
        if (item.getItemType() == 0) {
            helper.setText(R.id.tag, item.getLabelName() + " " + item.getLabelNum());
            if (item.getIsLightUp().equals("1")) {
                //电量
                helper.setBackgroundRes(R.id.tag, R.drawable.bg_info_blue);
                helper.setTextColor(R.id.tag, Color.WHITE);
            } else {
                helper.setBackgroundRes(R.id.tag, R.drawable.bg_info_normal);
                helper.setTextColor(R.id.tag, Color.parseColor("#888888"));
            }
        }
    }
}
