package sz.tianhe.baselib.http.interceptor;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * 项目名称:etc_wallet
 * 类描述 基类拦截器 给所有请求加上请求头
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 16:50
 */
public class BaseInterceptor implements Interceptor{
    public  static String name = "CE5D55AE79D4F853B3F5EFFF1A1F0EAF";
    public static String token="aV2AzezaRM13REkYU/q1yQgW/Rm6YP42DlywAFgS7DlcDiWqJVAJIaJZxqU78yah";
    public static String random = "1530984849836";
    private Map<String,String> mHeaders;

    public BaseInterceptor(Map<String,String> headers){
        this.mHeaders = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original  = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        if(null!= this.mHeaders && this.mHeaders.size() >0){
            for (String key:this.mHeaders.keySet()
                 ) {
                requestBuilder.addHeader(key,mHeaders.get(key));
            }
        }
        requestBuilder.addHeader("x-random",random);
        requestBuilder.addHeader("x-terminal-type","2");
        requestBuilder.addHeader("x-username",name);
        requestBuilder.addHeader("x-access-token",token);
        requestBuilder.addHeader("Accept","application/json;charset=UTF-8");
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
