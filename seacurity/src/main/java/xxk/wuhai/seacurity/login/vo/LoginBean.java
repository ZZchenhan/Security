package xxk.wuhai.seacurity.login.vo;

/**
 * Created by 86936 on 2018/7/2.
 *
 * @QQ 869360026
 */

public class LoginBean {
    private String deviceId;
    private String loginName;
    private String password;

    public LoginBean(String deviceId, String loginName, String password) {
        this.deviceId = deviceId;
        this.loginName = loginName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {

        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
