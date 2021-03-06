package sz.tianhe.baselib.presenter;

import android.content.Context;

import java.lang.ref.WeakReference;

import sz.tianhe.baselib.model.IBaseModel;
import sz.tianhe.baselib.view.IBaseView;

/**
 * 项目名称:etc_wallet
 * 类描述 缺省的代理器
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 15:45
 */
public abstract class AbstarctPresenter<M extends IBaseModel, V extends IBaseView> implements IBasePresenter {
    /**
     * 存储的视图应用，防止null指针应用
     */
    protected WeakReference<IBaseView> mBaseView;

    protected Context mContext;

    protected IBaseModel baseModel;

    public AbstarctPresenter(Context context) {
        this.mContext = context;
        this.baseModel = baseModel();
    }

    /**
     * prensenter强制要求一个请求模型，可以为null
     *
     * @return
     */
    public abstract M baseModel();


    @Override
    public void attachView(IBaseView view) {
        mBaseView = new WeakReference<IBaseView>(view);
    }

    @Override
    public void dettacheView() {
        mBaseView = null;
    }
}
