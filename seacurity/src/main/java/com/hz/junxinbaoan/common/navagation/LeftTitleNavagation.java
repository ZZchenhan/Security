package com.hz.junxinbaoan.common.navagation;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.widget.TextView;

import sz.tianhe.baselib.navagation.AbstarctNavagation;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import com.hz.junxinbaoan.R;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/25 1:17
 */
public abstract class LeftTitleNavagation extends AbstarctNavagation {

    private TextView tvTitle;
    public LeftTitleNavagation(Context context) {
        super(context);
        tvTitle = (TextView) navagationView.findViewById(sz.tianhe.baselib.R.id.tv_title);
        tvTitle.setText(title());
    }

    @Override
    public int layoutId() {
        return R.layout.navagation_left_title;
    }

    /**
     * 设置导航栏标题
     * @return
     */
    public abstract String title();

    /**
     * 设置导航栏标题颜色
     * @param colors
     */
    public IBaseNavagation setTitleColor(@ColorRes int colors){
        this.tvTitle.setTextColor(mContext.getResources().getColor(colors));
        return this;
    }

    public void setTvTitle(String tvTitle){
        this.tvTitle.setText(tvTitle);
    }

}
