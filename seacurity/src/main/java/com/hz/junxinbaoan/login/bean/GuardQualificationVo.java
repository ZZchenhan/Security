package com.hz.junxinbaoan.login.bean;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class GuardQualificationVo {
    private String issueDate;
    private String issueOrganization;
    private Integer qualificationId;
    private String qualificationNo;

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueOrganization() {
        return issueOrganization;
    }

    public void setIssueOrganization(String issueOrganization) {
        this.issueOrganization = issueOrganization;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationNo() {
        return qualificationNo;
    }

    public void setQualificationNo(String qualificationNo) {
        this.qualificationNo = qualificationNo;
    }
}
