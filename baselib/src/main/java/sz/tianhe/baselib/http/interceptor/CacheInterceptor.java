package sz.tianhe.baselib.http.interceptor;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import sz.tianhe.baselib.utils.NetUtils;

/**
 * 项目名称:etc_wallet
 * 类描述 缓存拦截器
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 16:55
 */
public class CacheInterceptor implements Interceptor {
    /**
     * 上下文内容
     */
    public Context mContext;

    public CacheInterceptor(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetUtils.isNetworkAvailable(mContext)) {
            Response response = chain.proceed(request);
            //20秒缓存
            int maxAge = 5;
            String cacheControl = request.cacheControl().toString();
            Log.i("Cache", "20s 都会加载这个缓存" + cacheControl);

            return response.newBuilder().removeHeader("")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public,max-age=" + maxAge).build();
        } else {
            Log.e("Load", "无网络，加载缓存数据");
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE).build();
            Response response = chain.proceed(request);
            int maxStale = 60 * 60 * 24*7;
            //最大缓存为1天
            return response.newBuilder().removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale).build();
        }
    }


}
