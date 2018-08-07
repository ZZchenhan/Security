package com.hz.junxinbaoan.work.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.work.bean.StudyListResult;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class StudyAdapter extends BaseQuickAdapter<StudyListResult.StudyNoticeInfoListBean,BaseViewHolder> {
    public StudyAdapter(@Nullable List<StudyListResult.StudyNoticeInfoListBean> data) {
        super(R.layout.item_study,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudyListResult.StudyNoticeInfoListBean item) {
            helper.setText(R.id.tv_title,item.getStudyTitle())
                    .setText(R.id.time,item.getStudyCreateTime())
                    .setText(R.id.status,item.getIsRead() == null || item.getIsRead().equals("0")?"未读":"已读")
                    .setText(R.id.content,item.getStudySummary());
        Glide.with(mContext)
                .load(item.getStudyPictureUrl())
                .into((ImageView) helper.getView(R.id.pic));
    }
}
