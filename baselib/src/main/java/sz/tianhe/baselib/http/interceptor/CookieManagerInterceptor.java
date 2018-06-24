package sz.tianhe.baselib.http.interceptor;

import android.content.Context;
import android.util.Log;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import sz.tianhe.baselib.http.cookie.LocalCookieMannager;

/**
 * 项目名称:etc_wallet
 * 类描述 保存用户Cookie
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 17:03
 */
public class CookieManagerInterceptor implements CookieJar {
    private Context mContext;

    private static LocalCookieMannager mLocalCookieMannager;

    public CookieManagerInterceptor(Context context){
        this.mContext = context;
        if(mLocalCookieMannager ==null){
            mLocalCookieMannager = new LocalCookieMannager(context);
        }
    }


    //将Cookie，保存到什么位置
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        Log.e("Cookie","保存cookies"+cookies);
        if (cookies!=null && cookies.size() != 0){
            for(Cookie cookie:cookies){
                mLocalCookieMannager.addCookie(url,cookie);
            }
        }
    }

    // 从什么位置加载Cookie
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return mLocalCookieMannager.get(url);
    }
}
