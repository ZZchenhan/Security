package sz.tianhe.baselib.presenter;

import sz.tianhe.baselib.model.IBaseModel;
import sz.tianhe.baselib.view.IBaseView;

/**
 * 项目名称:etc_wallet
 * 类描述 mvp设计模式基础的prensenter类
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 14:20
 */
public interface IBasePresenter<M extends IBaseModel,V extends IBaseView> {
    /**
     * 绑定视图
     * @param view
     */
    void attachView(IBaseView view);

    void dettacheView();
}
