package xxk.wuhai.seacurity;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by 86936 on 2018/7/1.
 *
 * @QQ 869360026
 */

public class MyApplication extends Application {
   @Override
    public void onCreate() {
        super.onCreate();
       JPushInterface.setDebugMode(true);
       JPushInterface.init(this);
    }
}
