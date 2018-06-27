package sz.tianhe.baselib.utils;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by 86936 on 2018/6/26.
 * 常用的设备工具类 如检查GPS 蓝牙等
 *
 * @QQ 869360026
 */

public class DeviceUtils {
    public static boolean gpsIsOpen(Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return gps;
    }


}
