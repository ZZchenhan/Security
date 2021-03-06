package com.hz.junxinbaoan.common.navagation;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
public abstract class CommonNavagation extends AbstarctNavagation {

    private TextView tvTitle;
    private ImageView ivLeft;
    private TextView tvRight;
    private ImageView ivRight;

    public CommonNavagation(Context context) {
        super(context);
        tvTitle = (TextView) navagationView.findViewById(R.id.tv_title);
        ivLeft = navagationView.findViewById(R.id.iv_left);
        tvRight = navagationView.findViewById(R.id.tv_right);
        ivRight = navagationView.findViewById(R.id.iv_right);
        tvTitle.setText(title());
    }

    @Override
    public int layoutId() {
        return R.layout.navagation_comon;
    }

    /**
     * 设置导航栏标题
     *
     * @return
     */
    public abstract String title();

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

    public CommonNavagation setRight(String title){
        this.tvRight.setText(title);
        return this;
    }

    public CommonNavagation setRight(String title,int bg){
        this.tvRight.setText(title);
        this.tvRight.setBackgroundResource(bg);
        return this;
    }


    public CommonNavagation setRightImageResouce(int resouce){
        Drawable drawable = mContext.getResources().getDrawable(resouce);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        this.tvRight.setCompoundDrawables(null,null,drawable,null);
        return this;
    }

    public CommonNavagation setRight(View.OnClickListener clickListener){
        this.tvRight.setOnClickListener(clickListener);
        return this;
    }

    public CommonNavagation setRight(int resouceId){
        this.ivRight.setImageResource(resouceId);
        this.ivRight.setVisibility(View.VISIBLE);
        this.tvRight.setVisibility(View.GONE);
        return this;
    }

    public CommonNavagation setRightColor(int colors){
        this.tvRight.setTextColor(mContext.getResources().getColor(colors));
        return this;
    }

    public CommonNavagation setRightOnclickListner(View.OnClickListener onclickListner){
        this.tvRight.setOnClickListener(onclickListner);
        this.ivRight.setOnClickListener(onclickListner);
        return this;
    }
}
