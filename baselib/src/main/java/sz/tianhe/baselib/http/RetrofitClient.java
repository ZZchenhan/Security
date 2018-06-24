package sz.tianhe.baselib.http;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import sz.tianhe.baselib.http.interceptor.CacheInterceptor;
import sz.tianhe.baselib.http.interceptor.CookieManagerInterceptor;

/**
 * 项目名称:etc_wallet
 * 类描述 retrofit请求
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 17:19
 */
public class RetrofitClient {
    /**
     * 服务器路径
     */
    private String serverUrl;

    /**
     * Retrofit
     */
    private Retrofit retrofit;

    /**
     * okhttp客户端
     */
    private OkHttpClient okHttpClient;

    /**
     * 缓存
     */
    public Cache cache;

    /**
     * 缓存路径
     */
    public static File cacheFile;

    /**
     * 上下文内容
     */
    private Context mContext;

    /**
     * 自定义头部文件
     */
    private Map<String, String> headers;

    public RetrofitClient(Context context, String serverUrl) {
        this(serverUrl, context, null);
    }

    public RetrofitClient(String serverUrl, Context context, Map<String, String> headers) {
        this.serverUrl = serverUrl;
        this.mContext = context;
        this.headers = headers;


        /**
         * 判断服务器路径是否正确
         */
        if (TextUtils.isEmpty(serverUrl)) {
            throw new RuntimeException("请输入正确的服务器路径");
        }

        /**
         * 生成缓存文件
         */
        if (null == this.cacheFile) {
            this.cacheFile = new File(this.mContext.getCacheDir(), "cache");
        }
        if (null == this.cache) {
            try {
                //设置缓存大小
                this.cache = new Cache(this.cacheFile, 1024 * 1024 * 10);
            } catch (Exception e) {
                //设置缓存失败
            }
        }

        //1、添加自定义头
        //2 Cookie管理
        //3 缓存管理
        //4 网络状态访问
        this.okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new BaseInterceptor(this.headers))
                .cookieJar(new CookieManagerInterceptor(this.mContext))
                .cache(this.cache)
                .addInterceptor(new CacheInterceptor(this.mContext))
                .addNetworkInterceptor(new CacheInterceptor(this.mContext))
                .connectionPool(new ConnectionPool(8, 10, TimeUnit.SECONDS))
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(this.serverUrl).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).
                        addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                        build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
