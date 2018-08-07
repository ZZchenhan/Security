package com.hz.junxinbaoan.login.vo;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class AddLabelInfoApiVo {

    /**
     * labelId : 0
     * userId : 0
     */

    private int labelId;
    private long userId;

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public AddLabelInfoApiVo(int labelId, long userId) {
        this.labelId = labelId;
        this.userId = userId;
    }
}
