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
    private String messageId;

    public GetSchedulingVo(String date, long userId) {
        this.dayStr = date;
        this.userId = userId;
    }

    public GetSchedulingVo(String dayStr, long userId, String messageId) {
        this.dayStr = dayStr;
        this.userId = userId;
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
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
