package sz.tianhe.baselib.http.interceptor;

import android.util.Log;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by 86936 on 2018/7/1.
 *
 * @QQ 869360026
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.e("http",message);
    }
}
