package com.hz.junxinbaoan.work.vo;

/**
 * Created by 86936 on 2018/7/14.
 *
 * @QQ 869360026
 */

public class GetSchedulingVo {

    /**
     * date : string
     * userId : 0
     */

    private String dayStr;
    private long userId;

    public GetSchedulingVo(String date, long userId) {
        this.dayStr = date;
        this.userId = userId;
    }

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
