package com.hz.junxinbaoan;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;
import sz.tianhe.baselib.http.RetrofitClient;
import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import com.hz.junxinbaoan.login.bean.UserDetailInfo;
import com.hz.junxinbaoan.login.interceptor.LoginInterceptor;
import com.hz.junxinbaoan.utils.ShareUtlts;


/**
 * Created by 86936 on 2018/7/1.
 *
 * @QQ 869360026
 */

public class MyApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks{

    public static MyApplication app;
    public static Context context;

    ArrayList<Activity> list = new ArrayList<Activity>();
    public static RetrofitClient retrofitClient;

    public static String deviceId;

    public static UserDetailInfo userDetailInfo;
    //外网
    public static String baseUrl = "https://app.junxin-baoan.cn/";



    //内网
//    public static String baseUrl = "http://47.98.241.211/";

    public static String aluyun = "http://tongyongbucket.oss-cn-hangzhou.aliyuncs.com/";

    public static OSS oss = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        context = this.getApplicationContext();
        app = this;
        super.onCreate();
//        MyApplication.app.init();
        JPushInterface.setDebugMode(true);
       JPushInterface.init(this);
       retrofitClient = new RetrofitClient(this,baseUrl){
           @Override
           public void addInterceptor(OkHttpClient.Builder builder) {
               super.addInterceptor(builder);
               builder.addInterceptor(new LoginInterceptor(MyApplication.this));
           }
       };
       deviceId = Build.SERIAL;
       Utils.init(this);
       Context context = getApplicationContext();
        // 获取当前包名
          String packageName = context.getPackageName();
        // 获取当前进程名
           String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
          CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
       CrashReport.initCrashReport(getApplicationContext(), "9800fce075", true,strategy);
       String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
       BaseInterceptor.random = ShareUtlts.getRandom(this);
       BaseInterceptor.token = ShareUtlts.getToken(this);
       BaseInterceptor.name = ShareUtlts.getName(this);
       ToastUtils.setBgColor(getResources().getColor(R.color.gray));
        ToastUtils.setMsgColor(getResources().getColor(R.color.white));
       OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIqNxd4rFLe0l6","xZEViV4zH0VaVza8kd4cdNZ8TGvFLS");
        oss = new OSSClient(this, endpoint, credentialProvider);
        registerActivityLifecycleCallbacks(this);
    }


    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public void init(){
        //设置该CrashHandler为程序的默认处理器
        UnCeHandler catchExcep = new UnCeHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }

    /**
     * Activity关闭时，删除Activity列表中的Activity对象*/
    public void removeActivity(Activity a){
        list.remove(a);
    }

    /**
     * 向Activity列表中添加Activity对象*/
    public void addActivity(Activity a){
        list.add(a);
    }

    /**
     * 关闭Activity列表中的所有Activity*/
    public void finishActivity(){
        unregisterActivityLifecycleCallbacks(this);
        for (Activity activity : list) {
            if (null != activity) {
                activity.finish();
            }
        }

        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        list.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        list.remove(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
