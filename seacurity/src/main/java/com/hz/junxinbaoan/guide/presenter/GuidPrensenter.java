package com.hz.junxinbaoan.guide.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


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
import sz.tianhe.baselib.presenter.AbstarctPresenter;
import sz.tianhe.baselib.utils.ToastUtils;
import sz.tianhe.baselib.utils.VersionUtils;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.guide.view.itf.IGuideView;
import com.hz.junxinbaoan.login.api.UserApi;
import com.hz.junxinbaoan.login.bean.UserDetailInfo;
import com.hz.junxinbaoan.login.vo.GetUserInfoVo;


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
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.RECORD_AUDIO
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //6.0以上需要检查权限
            checkPermision();
        }else{
            handOver();
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
                .subscribe(new Observer<com.hz.junxinbaoan.bean.Result<UserDetailInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(com.hz.junxinbaoan.bean.Result<UserDetailInfo> result) {
                        if(iGuideView==null){
                           toast(result.getMessage());
                            return;
                        }
                        if(result.getCode().equals("200")){
                            MyApplication.userDetailInfo = result.getResult();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(iGuideView!=null){
                           toast(e.getMessage());
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

    public void toast(String msg){
        ToastUtils.makeText(mContext,msg,ToastUtils.LENGTH_LONG).show();
    }
}
