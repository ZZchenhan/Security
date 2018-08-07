package com.hz.junxinbaoan.work.adapter;

import android.support.annotation.Nullable;

import com.amap.api.services.core.PoiItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.hz.junxinbaoan.R;

/**
 * Created by 86936 on 2018/6/28.
 *
 * @QQ 869360026
 */

public class PoiAdapter extends BaseQuickAdapter<PoiItem,BaseViewHolder> {

    public int chckPostion = -1;
    public PoiAdapter(int layoutResId, @Nullable List<PoiItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiItem item) {
        if(mData.indexOf(item) == chckPostion){
            helper.setGone(R.id.iv_select, true);
        }else{
            helper.setGone(R.id.iv_select, false);
        }
        helper.setText(R.id.name,item.getTitle())
                .setText(R.id.tv_location,item.getProvinceName()+item.getCityName()+item.getBusinessArea()+item.getTitle());
    }

    public void setChckPostion(int chckPostion) {
        this.chckPostion = chckPostion;
    }


}
