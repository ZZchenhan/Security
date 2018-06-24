package sz.tianhe.baselib.navagation;

import android.support.annotation.ColorRes;
import android.view.View;

/**
 * 项目名称:etc_wallet
 * 类描述 基础的导航栏
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 14:57
 */
public interface IBaseNavagation {
    /**
     * 获得导航栏
     * @return
     */
    View getNavagation();

    /**
     * 设置导航栏背景
     * @param color
     * @return
     */
    IBaseNavagation setNavagationBackgroudColor(@ColorRes int color);
}
