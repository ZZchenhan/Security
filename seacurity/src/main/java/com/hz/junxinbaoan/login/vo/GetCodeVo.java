package com.hz.junxinbaoan.login.vo;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */
public class GetCodeVo {
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 1：登录 2：忘记密码 3：微信账号绑定
     */
    private String type;

    public GetCodeVo(String mobile, String type) {
        this.mobile = mobile;
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
