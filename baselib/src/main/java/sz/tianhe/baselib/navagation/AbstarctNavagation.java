package sz.tianhe.baselib.navagation;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;

import sz.tianhe.baselib.R;

/**
 * 项目名称:etc_wallet
 * 类描述 缺省的导航栏
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 14:59
 */
public abstract class AbstarctNavagation implements IBaseNavagation {

    /**
     * 上下文内容
     */
    protected Context mContext;

    /**
     * 布局增压器
     */
    private LayoutInflater layoutInflater;

    /**
     * 真实的导航栏
     */
    protected View navagationView;

    public AbstarctNavagation(Context context) {
        this.mContext = context;
        this.layoutInflater = LayoutInflater.from(this.mContext);
        this.navagationView = this.layoutInflater.inflate(layoutId(), null);
    }

    public abstract int layoutId();


    @Override
    public View getNavagation() {
        return navagationView;
    }

    @Override
    public IBaseNavagation setNavagationBackgroudColor(@ColorRes int color){
        this.navagationView.setBackgroundColor(this.mContext.getResources().getColor(color));
        return this;
    }
}
