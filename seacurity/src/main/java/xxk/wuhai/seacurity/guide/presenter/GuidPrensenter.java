package xxk.wuhai.seacurity.guide.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


import com.blankj.utilcode.util.ToastUtils;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import sz.tianhe.baselib.model.IBaseModel;
import sz.tianhe.baselib.model.bean.Result;
import sz.tianhe.baselib.presenter.AbstarctPresenter;
import sz.tianhe.baselib.utils.VersionUtils;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.guide.model.GuideModel;
import xxk.wuhai.seacurity.bean.CompanyBean;
import xxk.wuhai.seacurity.guide.view.itf.IGuideView;
import xxk.wuhai.seacurity.login.api.UserApi;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.vo.GetUserInfoVo;
import xxk.wuhai.seacurity.main.view.MainActivity;
import xxk.wuhai.seacurity.utils.ShareUtlts;


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

    private final int REQUSET_PERMISSION = 1;

    /**
     * 需要检查的权限
     */
    String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    //用户需要申请的权限
    List<String> mPermissionList = new ArrayList<>();

    private void checkPermision() {
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(mContext, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                //检查所需要的所有权限 当权限不为允许的时候 添加到需要申请表中
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.size() == 0) {
            //没有需要申请的权限
            handOver();
            return;
        }

        String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);
        //申请权限
        ActivityCompat.requestPermissions((Activity) mContext, permissions, REQUSET_PERMISSION);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUSET_PERMISSION) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, permissions[i]);
                    if (showRequestPermission) {
                        //有权限被拒绝
                        iGuideView.showPermisionUnAccept();
                        return;
                    }
                }
            }
            handOver();
        }
    }

    public GuidPrensenter(Context context, IGuideView iGuideView) {
        super(context);
        this.iGuideView = iGuideView;
    }

    @Override
    public void init() {
        loadVersion();
        getUserInfo();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            //6.0以上需要检查权限
            checkPermision();
        }

    }


    @Override
    public IBaseModel baseModel() {
        return null;
    }


    public void getUserInfo() {
        if(BaseInterceptor.token == null || BaseInterceptor.token.equals("")) {
            return;
        }
        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .getUserInfo(new GetUserInfoVo())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<xxk.wuhai.seacurity.bean.Result<UserDetailInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(xxk.wuhai.seacurity.bean.Result<UserDetailInfo> result) {
                        if(iGuideView==null){
                            ToastUtils.showShort(result.getMessage());
                            return;
                        }
                        if(result.getCode().equals("200")){
                            MyApplication.userDetailInfo = result.getResult();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(iGuideView!=null){
                            ToastUtils.showShort(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void loadVersion() {
        String verionName = VersionUtils.getLocalVersionName(mContext);
        iGuideView.versionName(mContext.getResources().getString(R.string.app_name), verionName);
    }

    /**
     * 延迟跳转
     */
    public void handOver() {
    Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        iGuideView.handover();
                    }
                });
    }
}
