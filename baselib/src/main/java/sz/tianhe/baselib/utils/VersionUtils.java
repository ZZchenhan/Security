package sz.tianhe.baselib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 软件版本 工具类
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 20:09
 */
public class VersionUtils {

    /**
     * 获取本软件版本号
     * @param ctx
     * @return
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }


    /**
     * 获取本软件版本名
     * @param ctx
     * @return
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "错误";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
