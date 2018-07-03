package xxk.wuhai.seacurity.login.result;

/**
 * Created by 86936 on 2018/7/2.
 *
 * @QQ 869360026
 */
public class LoginResult {
    private long random;
    private String accessToken;
    private String username;

    public long getRandom() {
        return random;
    }

    public void setRandom(long random) {
        this.random = random;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
