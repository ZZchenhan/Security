package com.hz.junxinbaoan.work.vo;

/**
 * Created by 86936 on 2018/7/21.
 *
 * @QQ 869360026
 */

public class ApiGetUserSignByDayVO {
    private String beginTime;
    private String endTime;
    private String pagaSize="1";
    private String pageNum="20";


    public ApiGetUserSignByDayVO(String beginTime) {
        this.beginTime = beginTime;
        this.endTime = beginTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPagaSize() {
        return pagaSize;
    }

    public void setPagaSize(String pagaSize) {
        this.pagaSize = pagaSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
}
