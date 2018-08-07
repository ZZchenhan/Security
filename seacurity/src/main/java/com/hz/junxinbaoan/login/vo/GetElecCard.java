package com.hz.junxinbaoan.login.vo;

/**
 * Created by 86936 on 2018/7/23.
 *
 * @QQ 869360026
 */

public class GetElecCard {
    private String accountName;

    public GetElecCard(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
