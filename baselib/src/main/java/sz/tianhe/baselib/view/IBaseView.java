package sz.tianhe.baselib.view;

import sz.tianhe.baselib.presenter.IBasePresenter;

/**
 * 项目名称:etc_wallet
 * 类描述 mvp基础的baseView
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 14:21
 */
public interface IBaseView<P extends IBasePresenter> {
    /**
     * 显示进度条
     */
    void showLoadingView();

    /**
     * 隐藏进度条
     */
    void hideLoadingView();

    /**
     * 设置toast
     * @param msg
     */
    void toast(String msg);


}
