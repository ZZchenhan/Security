package com.hz.junxinbaoan.work.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.work.bean.UserSignListResult;

/**
 * Created by 86936 on 2018/6/29.
 *
 * @QQ 869360026
 */

public class SignListAdapter extends BaseQuickAdapter<UserSignListResult.UserSignInfoVosBean,BaseViewHolder> {
    public SignListAdapter(int layoutResId, @Nullable List<UserSignListResult.UserSignInfoVosBean> data) {
        super(layoutResId, data);
    }

    //2018-07-11 17:13:54
    @Override
    protected void convert(BaseViewHolder helper, UserSignListResult.UserSignInfoVosBean item) {
        helper.setGone(R.id.ll_pic,false);
        helper.setText(R.id.date,item.getSignTime()!=null?item.getSignTime().substring(5,10):"未知时间")
                .setText(R.id.time,item.getSignTime()!=null?item.getSignTime().substring(11,16):"未知时间")
                .setText(R.id.site,item.getPoiName() == null?"":item.getPoiName())
                .setText(R.id.remark,item.getRemark()==null?"":item.getRemark())
                .setText(R.id.locaion,item.getAttendanceLocation()==null?"":item.getAttendanceLocation());
        try{
            if(item.getImageUrls() == null ||item.getImageUrls().size() == 0){
                helper.setGone(R.id.ll_pic,false);
                helper.setGone(R.id.pic1,false);
                helper.setGone(R.id.pic2,false);
                helper.setGone(R.id.pic3,false);
                return;
            }
            helper.setGone(R.id.ll_pic,true);
            ((ImageView) helper.getView(R.id.pic1)).setImageResource(R.color.white);
            ((ImageView) helper.getView(R.id.pic2)).setImageResource(R.color.white);
            ((ImageView) helper.getView(R.id.pic3)).setImageResource(R.color.white);
            Glide.with(mContext).load(item.getImageUrls().get(0))
                    .into((ImageView) helper.getView(R.id.pic1));
            helper.setGone(R.id.pic1,true);
            helper.addOnClickListener(R.id.pic1);
            Glide.with(mContext).load(item.getImageUrls().get(1))
                    .into((ImageView) helper.getView(R.id.pic2));
            helper.setGone(R.id.pic2,true);
            helper.addOnClickListener(R.id.pic2);
            Glide.with(mContext).load(item.getImageUrls().get(2))
                    .into((ImageView) helper.getView(R.id.pic3));
            helper.setGone(R.id.pic3,true);
            helper.addOnClickListener(R.id.pic3);
        }catch (Exception e){

        }
    }
}
