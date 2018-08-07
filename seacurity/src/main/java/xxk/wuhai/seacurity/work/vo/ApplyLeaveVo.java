package xxk.wuhai.seacurity.work.vo;

import java.util.List;

/**
 * Created by 86936 on 2018/7/10.
 *
 * @QQ 869360026
 */

public class ApplyLeaveVo {
    private long approverID;
    private String leaveRequestBeginTime;
    private int leaveRequestDays;
    private String leaveRequestEndTime;
    private String leaveRequestTypeId;
    private String leaveUserId;
    private List<String> lrPictureUrls;
    private String supplement;

    public long getApproverID() {
        return approverID;
    }

    public void setApproverID(long approverID) {
        this.approverID = approverID;
    }

    public String getLeaveRequestBeginTime() {
        return leaveRequestBeginTime;
    }

    public void setLeaveRequestBeginTime(String leaveRequestBeginTime) {
        this.leaveRequestBeginTime = leaveRequestBeginTime;
    }

    public int getLeaveRequestDays() {
        return leaveRequestDays;
    }

    public void setLeaveRequestDays(int leaveRequestDays) {
        this.leaveRequestDays = leaveRequestDays;
    }

    public String getLeaveRequestEndTime() {
        return leaveRequestEndTime;
    }

    public void setLeaveRequestEndTime(String leaveRequestEndTime) {
        this.leaveRequestEndTime = leaveRequestEndTime;
    }

    public String getLeaveRequestTypeId() {
        return leaveRequestTypeId;
    }

    public void setLeaveRequestTypeId(String leaveRequestTypeId) {
        this.leaveRequestTypeId = leaveRequestTypeId;
    }

    public String getLeaveUserId() {
        return leaveUserId;
    }

    public void setLeaveUserId(String leaveUserId) {
        this.leaveUserId = leaveUserId;
    }

    public List<String> getLrPictureUrls() {
        return lrPictureUrls;
    }

    public void setLrPictureUrls(List<String> lrPictureUrls) {
        this.lrPictureUrls = lrPictureUrls;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }
}
