package sz.tianhe.baselib.http.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * 项目名称:etc_wallet
 * 类描述 实现浏览器将cookie 保存到本地 和内存中
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 17:06
 */
public class LocalCookieMannager {
    private static String TAG = "cookieManager";
    private static String COOKIE_PREFS = "okhttp_local_cookie";
    private  final SharedPreferences sharedPreferences;
    private  final Map<String,ConcurrentHashMap<String, Cookie>> cookies;

    public LocalCookieMannager(Context context){
        sharedPreferences = context.getSharedPreferences(COOKIE_PREFS,Context.MODE_PRIVATE);
        cookies = new HashMap<>();

        //将偏好设置里面的Cookies提取到内存中保存
        //保存格式为
        Map<String,String> localCookies = (Map<String, String>) sharedPreferences.getAll();
        for(Map.Entry<String,String> entry:localCookies.entrySet()){
            String[] names = entry.getKey().split(",");//名字是以 cookie1,cookie2
            for(String name:names){
                String encodeCookieString = sharedPreferences.getString(name,null);
                if(encodeCookieString != null){
                    Cookie decodeCookie =decodeCookie(encodeCookieString);
                    if(decodeCookie != null){
                        if(!cookies.containsKey(entry.getKey())){
                            //没有这个主机的Cookie
                            cookies.put(entry.getKey(),new ConcurrentHashMap<String, Cookie>());
                        }
                        cookies.get(entry.getKey()).put(name,decodeCookie);
                    }
                }
            }
        }

    }

    /**
     * 添加Cookie 到本地
     * @param cookie
     */
    public void addCookie(HttpUrl url, Cookie cookie){
        String name = getCookieName(cookie);

        if(cookie.persistent()){
            //不可持久化的Cookie
            //1 确定这个Cookie 是否在内存中存在 存在删除
            if(cookies.containsKey(url.host())){
                cookies.get(url.host()).remove(name);
            }
        }else{
            //可持久化的Cookie
            if(!cookies.containsKey(url.host())){
                cookies.put(url.host(),new ConcurrentHashMap<String, Cookie>());
            }
            cookies.get(url.host()).put(name,cookie);//将Cookie 放到类存中
        }
        //存储到本地
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(url.host(), TextUtils.join(",",cookies.get(url.host()).keySet()));
        editor.putString(name,encodeCookie(new CookieSerializable(cookie)));//放置cookie的 主机名+cookie名;
    }


    public List<Cookie> get(HttpUrl url){
        ArrayList<Cookie> ret = new ArrayList<>();
        for (String key : cookies.keySet())
            ret.addAll(cookies.get(key).values());

        return ret;
    }

    /**
     * 加密cookie 为字符串
     * @param cookie
     */
    public String encodeCookie(CookieSerializable cookie){
        if(cookie == null){
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objecOutput = new ObjectOutputStream(os);
            objecOutput.writeObject(cookie);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  byte2Str(os.toByteArray());
    }

    private Cookie decodeCookie(String string){
        byte[] bytes = str2Bytes(string);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            cookie = ((CookieSerializable)objectInputStream.readObject()).getCookies();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException c){
            c.printStackTrace();
        }

        return cookie;
    }

    private String getCookieName(Cookie cookie) {
        return  cookie.domain()+"@"+cookie.name();
    }


    /**
     * 16近制字符串转2进制数组
     * @param hexString
     * @return
     */
    private byte[] str2Bytes(String hexString){
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }


    /**
     * 二进制数组转十六进制字符串
     *
     * @param bytes byte array to be converted
     * @return string containing hex values
     */
    protected String byte2Str(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }
}
