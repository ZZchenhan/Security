package com.hz.junxinbaoan.work.vo;

import java.util.List;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/10 15:40
 */
public class SupplementApplyVo {
    private long approverID;
    private String attendanceId;
    private List<String> paPictureUrls;
    private String patchTime;
    private String supplement;

    public long getApproverID() {
        return approverID;
    }

    public void setApproverID(long approverID) {
        this.approverID = approverID;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public List<String> getPaPictureUrls() {
        return paPictureUrls;
    }

    public void setPaPictureUrls(List<String> paPictureUrls) {
        this.paPictureUrls = paPictureUrls;
    }

    public String getPatchTime() {
        return patchTime;
    }

    public void setPatchTime(String patchTime) {
        this.patchTime = patchTime;
    }

    public String getSupplement() {
        return supplement;
    }

    public void setSupplement(String supplement) {
        this.supplement = supplement;
    }
}
