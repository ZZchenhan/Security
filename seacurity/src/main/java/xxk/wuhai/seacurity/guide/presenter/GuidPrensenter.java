package xxk.wuhai.seacurity.guide.presenter;

import android.content.Context;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;
import sz.tianhe.baselib.model.IBaseModel;
import sz.tianhe.baselib.model.bean.Result;
import sz.tianhe.baselib.presenter.AbstarctPresenter;
import sz.tianhe.baselib.utils.VersionUtils;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.guide.model.GuideModel;
import xxk.wuhai.seacurity.bean.CompanyBean;
import xxk.wuhai.seacurity.guide.view.itf.IGuideView;


/**
 * 引导页导航
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 18:29
 */
public class GuidPrensenter extends AbstarctPresenter {

    IGuideView iGuideView;

    public GuidPrensenter(Context context, IGuideView iGuideView) {
        super(context);
        this.iGuideView = iGuideView;
    }

    @Override
    public void init() {
        loadVersion();
        handOver();
    }

    @Override
    public IBaseModel baseModel() {
        return null;
    }


    public void getComcpnyInfo() {
        ((GuideModel) baseModel).getCompnay().observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Result<CompanyBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<CompanyBean> guideBeanResult) {
                        if (guideBeanResult.getCode() == 0) {
                            if (mBaseView != null) {
                                iGuideView.loadCompanyView(guideBeanResult.getData());
                            }
                        }else{
                            onError(new RuntimeException());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void loadVersion(){
        String verionName = VersionUtils.getLocalVersionName(mContext);
        iGuideView.versionName(mContext.getResources().getString(R.string.app_name),verionName);
    }

    /**
     * 延迟跳转
     */
    public void handOver(){
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new rx.Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        iGuideView.handover();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        
                    }
                });
    }
}
