package com.hz.junxinbaoan.login.vo;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */
public class ForgetPassVo {
    /**
     * 1:图验,2:短验
     */
    private String codeType;
    /**
     * 录帐号名，身份证/手机号
     */
    private String loginName;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 密码
     */
    private String password;

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ForgetPassVo(String codeType, String loginName, String verifyCode, String password) {
        this.codeType = codeType;
        this.loginName = loginName;
        this.verifyCode = verifyCode;
        this.password = password;
    }
}
