package sz.tianhe.baselib.navagation;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.widget.TextView;

import sz.tianhe.baselib.R;

/**
 * 项目名称:etc_wallet
 * 类描述 简单的导航栏
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 15:06
 */
public abstract class SimpleNavagation extends AbstarctNavagation {
    private TextView tvTitle;
    public SimpleNavagation(Context context) {
        super(context);
        tvTitle = (TextView) navagationView.findViewById(R.id.tv_title);
        tvTitle.setText(title());
    }

    @Override
    public int layoutId() {
        return R.layout.simple_navagation;
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

}
