package xxk.wuhai.seacurity;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cn.jpush.android.api.JPushInterface;
import sz.tianhe.baselib.http.RetrofitClient;
import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.result.LoginResult;
import xxk.wuhai.seacurity.utils.ShareUtlts;


/**
 * Created by 86936 on 2018/7/1.
 *
 * @QQ 869360026
 */

public class MyApplication extends Application {
    public static RetrofitClient retrofitClient;

    public static String deviceId;

    public static UserDetailInfo userDetailInfo;

    public static String baseUrl = "http://47.98.241.211/";

    public static String aluyun = "http://tongyongbucket.oss-cn-hangzhou.aliyuncs.com/";

    public static OSS oss = null;

   @Override
    public void onCreate() {
        super.onCreate();
       JPushInterface.setDebugMode(true);
       JPushInterface.init(this);
       retrofitClient = new RetrofitClient(this,baseUrl);
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
       CrashReport.initCrashReport(getApplicationContext(), "9800fce075", false,strategy);
       String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
       BaseInterceptor.random = ShareUtlts.getRandom(this);
       BaseInterceptor.token = ShareUtlts.getToken(this);
       BaseInterceptor.name = ShareUtlts.getName(this);
       ToastUtils.setBgColor(getResources().getColor(R.color.gray));
        ToastUtils.setMsgColor(getResources().getColor(R.color.white));
       OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIqNxd4rFLe0l6","xZEViV4zH0VaVza8kd4cdNZ8TGvFLS");
        oss = new OSSClient(this, endpoint, credentialProvider);
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
}
