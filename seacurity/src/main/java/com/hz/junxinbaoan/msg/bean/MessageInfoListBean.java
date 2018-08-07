package com.hz.junxinbaoan.msg.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 86936 on 2018/7/5.
 *
 * @QQ 869360026
 */
public class MessageInfoListBean implements MultiItemEntity,Serializable{
    /**
     * messageId : 6
     * messageTypeId : 1
     * messageTitle : 全体注意
     * messageSummary : 没签到的赶紧签到
     * messagePictureUrls : null
     * messageCreateTime : 2018-07-04 15:06:42
     * status : 0
     * isTop : 0
     * associatedId : null
     * userId : null
     * orgId : null
     * deptId : null
     * reportBeginTime : null
     * reportEndTime : null
     */
    /**
     * 消息ID
     */
    private int messageId;
    /**
     * 消息类型id 1：公司消息 2：排班变动 3：审批提醒消息 4：提醒打卡消息 5：报表消息 6：人事消息
     */
    private String messageTypeId;
    /**
     * 消息标题
     */
    private String messageTitle;
    /**
     * 消息摘要
     */
    private String messageSummary;
    /**
     * 消息图片
     */
    private List<String> messagePictureUrls;
    /**
     * 创建时间
     */
    private String messageCreateTime;
    /**
     * 0 未读  1已读
     */
    private String status;
    private String isTop;
    /**
     * 关联id
     */
    private String associatedId;
    private String userId;
    private String orgId;
    private String deptId;
    private String reportBeginTime;
    private String reportEndTime;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageSummary() {
        return messageSummary;
    }

    public void setMessageSummary(String messageSummary) {
        this.messageSummary = messageSummary;
    }

    public List<String> getMessagePictureUrls() {
        return messagePictureUrls;
    }

    public void setMessagePictureUrls(List<String> messagePictureUrls) {
        this.messagePictureUrls = messagePictureUrls;
    }

    public String getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(String messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getAssociatedId() {
        return associatedId;
    }

    public void setAssociatedId(String associatedId) {
        this.associatedId = associatedId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getReportBeginTime() {
        return reportBeginTime;
    }

    public void setReportBeginTime(String reportBeginTime) {
        this.reportBeginTime = reportBeginTime;
    }

    public String getReportEndTime() {
        return reportEndTime;
    }

    public void setReportEndTime(String reportEndTime) {
        this.reportEndTime = reportEndTime;
    }

    @Override
    public int getItemType() {
        return messagePictureUrls == null || messagePictureUrls.size() == 0?0:1;
    }
}
