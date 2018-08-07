package com.hz.junxinbaoan.msg.vo;

/**
 * Created by 86936 on 2018/7/5.
 *
 * @QQ 869360026
 */
public class GetMessageListVo {

    private String messageType;

    private int pageNum;

    private int pageSize;

    private String searchTitle;

    public GetMessageListVo(String messageType, int pageNum, int pageSize, String searchTitle) {
        this.messageType = messageType;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.searchTitle = searchTitle;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }
}
