package sz.tianhe.baselib.http.interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称:etc_wallet
 * 类描述 基类拦截器 给所有请求加上请求头
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 16:50
 */
public class BaseInterceptor implements Interceptor{

    private Map<String,String> mHeaders;

    public BaseInterceptor(Map<String,String> headers){
        this.mHeaders = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        if(null!= this.mHeaders && this.mHeaders.size() >0){
            for (String key:this.mHeaders.keySet()
                 ) {
                requestBuilder.addHeader(key,mHeaders.get(key));
            }
        }
        return chain.proceed(requestBuilder.build());
    }
}
