package xxk.wuhai.seacurity;

import android.app.Application;
import android.os.Build;

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

   @Override
    public void onCreate() {
        super.onCreate();
       JPushInterface.setDebugMode(true);
       JPushInterface.init(this);
       retrofitClient = new RetrofitClient(this,"http://47.98.241.211:9001/");
       deviceId = Build.SERIAL;
    }
}
