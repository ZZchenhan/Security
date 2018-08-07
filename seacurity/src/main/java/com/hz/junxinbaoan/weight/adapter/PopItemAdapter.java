package com.hz.junxinbaoan.weight.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.work.bean.AplyUser;

/**
 * Created by 86936 on 2018/7/9.
 *
 * @QQ 869360026
 */

public class PopItemAdapter extends BaseQuickAdapter<AplyUser,BaseViewHolder> {
    public PopItemAdapter(@Nullable List<AplyUser> data) {
        super(R.layout.item_pop_string,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AplyUser item) {
        helper.setText(R.id.remind_apply,item.getName());
    }
}
