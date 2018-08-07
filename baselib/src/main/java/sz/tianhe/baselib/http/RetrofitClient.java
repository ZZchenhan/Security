package sz.tianhe.baselib.http;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import sz.tianhe.baselib.http.interceptor.CookieManagerInterceptor;
import sz.tianhe.baselib.http.interceptor.HttpLogger;

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
        if(this.headers == null){
            headers = new HashMap<>();
        }

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
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //1、添加自定义头
        //2 Cookie管理
        //3 缓存管理
        //4 网络状态访问
        OkHttpClient.Builder builder =  new OkHttpClient().newBuilder()
                .addInterceptor(new BaseInterceptor(this.headers))
                .cookieJar(new CookieManagerInterceptor(this.mContext))
                .addNetworkInterceptor(logInterceptor)
                .connectionPool(new ConnectionPool(8, 10, TimeUnit.SECONDS))
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS).sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                ;
        addInterceptor(builder);
        this.okHttpClient = builder.build();
                retrofit = new Retrofit.Builder()
                .baseUrl(this.serverUrl).client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson())).
                        build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public RequestBody getRequestBody(Map<String,String> prams){
        JSONObject jsonObject = new JSONObject();
        Iterator iterator = prams.keySet().iterator();
        try {
        while (iterator.hasNext()){
            String k = iterator.next().toString();

                jsonObject.put(k,prams.get(k));

        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
        return requestBody;
    }
    public void addInterceptor(OkHttpClient.Builder builder){

    }
}
