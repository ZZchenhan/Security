package com.hz.junxinbaoan.work.bean;

import java.io.Serializable;

/**
 * Created by 86936 on 2018/7/10.
 *
 * @QQ 869360026
 */

public class StudyDetail implements Serializable{

    /**
     * studyNoticeVo : {"companyName":"string","studyContent":"string","studyCreateTime":"string","studyTitle":"string"}
     */

    private StudyNoticeVoBean studyNoticeVo;

    public StudyNoticeVoBean getStudyNoticeVo() {
        return studyNoticeVo;
    }

    public void setStudyNoticeVo(StudyNoticeVoBean studyNoticeVo) {
        this.studyNoticeVo = studyNoticeVo;
    }

    public static class StudyNoticeVoBean implements Serializable{
        /**
         * companyName : string
         * studyContent : string
         * studyCreateTime : string
         * studyTitle : string
         */

        private String companyName;
        private String studyContent;
        private String studyCreateTime;
        private String studyTitle;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getStudyContent() {
            return studyContent;
        }

        public void setStudyContent(String studyContent) {
            this.studyContent = studyContent;
        }

        public String getStudyCreateTime() {
            return studyCreateTime;
        }

        public void setStudyCreateTime(String studyCreateTime) {
            this.studyCreateTime = studyCreateTime;
        }

        public String getStudyTitle() {
            return studyTitle;
        }

        public void setStudyTitle(String studyTitle) {
            this.studyTitle = studyTitle;
        }
    }
}
