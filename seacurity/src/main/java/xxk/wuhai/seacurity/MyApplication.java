package xxk.wuhai.seacurity;

import android.app.Application;
import android.os.Build;

import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;

import cn.jpush.android.api.JPushInterface;
import sz.tianhe.baselib.http.RetrofitClient;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.result.LoginResult;


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

    public static String aluyun = "https://tongyongbucket.oss-cn-hangzhou.aliyuncs.com/";

    public static OSS oss = null;

   @Override
    public void onCreate() {
        super.onCreate();
       JPushInterface.setDebugMode(true);
       JPushInterface.init(this);
       retrofitClient = new RetrofitClient(this,baseUrl);
       deviceId = Build.SERIAL;
       String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

       OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIqNxd4rFLe0l6","xZEViV4zH0VaVza8kd4cdNZ8TGvFLS");
        oss = new OSSClient(this, endpoint, credentialProvider);
    }
}
