package sz.tianhe.baselib.http.cookie;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import okhttp3.Cookie;

/**
 * 项目名称:etc_wallet
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/6/22 17:04
 */
public class CookieSerializable implements Serializable {
    private transient Cookie cookie;
    private transient Cookie clientCookie;

    public CookieSerializable(Cookie cookie){
        this.cookie = cookie;
    }

    public Cookie getCookies(){
        Cookie bestCookie = this.cookie;
        if(clientCookie != null){
            bestCookie = clientCookie;
        }
        return bestCookie;
    }

    public void writeObject(ObjectOutput output)throws IOException {
        output.writeObject(cookie.name());
        output.writeObject(cookie.value());
        output.writeLong(cookie.expiresAt());
        output.writeObject(cookie.domain());
        output.writeObject(cookie.path());
        output.writeBoolean(cookie.secure());
        output.writeBoolean(cookie.httpOnly());
        output.writeBoolean(cookie.hostOnly());
        output.writeBoolean(cookie.persistent());
    }

    public void readObject(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        String name = (String) objectInput.readObject();
        String value = (String) objectInput.readObject();
        long expiresAt = objectInput.readLong();
        String domain = (String) objectInput.readObject();
        String path = (String) objectInput.readObject();
        boolean secure = objectInput.readBoolean();//是否是安全的 https传输
        boolean httpOnly = objectInput.readBoolean();
        boolean hostOnly = objectInput.readBoolean();
        boolean persistent = objectInput.readBoolean();

        Cookie.Builder builder = new Cookie.Builder();
        builder.name(name).value(value)
                .expiresAt(expiresAt)
                .path(path);
        if(secure){
            builder.secure();
        }
        if(httpOnly){
            builder.httpOnly();
        }
        if(hostOnly){
            builder.hostOnlyDomain(domain);
        }else{
            builder.domain(domain);
        }
        clientCookie = builder.build();
    }
}
