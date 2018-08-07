package com.hz.junxinbaoan.common.navagation;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.view.View;
import android.widget.ImageView;
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
public  class LeftIconNavagation extends AbstarctNavagation {

    private TextView tvTitle;
    private ImageView ivLeft;

    public LeftIconNavagation(Context context) {
        super(context);
        tvTitle = (TextView) navagationView.findViewById(sz.tianhe.baselib.R.id.tv_title);
        ivLeft = navagationView.findViewById(R.id.iv_left);
        tvTitle.setText(title());
    }

    public void setTvTitle(String string){
        tvTitle.setText(string);
    }

    @Override
    public int layoutId() {
        return R.layout.navagation_left_icons;
    }

    /**
     * 设置导航栏标题
     *
     * @return
     */
    public  String title(){
        return "";
    }

    public void setLeftResuoce(int resouce) {
        ivLeft.setImageResource(resouce);
    }

    public void setIconClick(View.OnClickListener iconClick){
        ivLeft.setOnClickListener(iconClick);
    }

    /**
     * 设置导航栏标题颜色
     *
     * @param colors
     */
    public IBaseNavagation setTitleColor(@ColorRes int colors) {
        this.tvTitle.setTextColor(mContext.getResources().getColor(colors));
        return this;
    }
}
