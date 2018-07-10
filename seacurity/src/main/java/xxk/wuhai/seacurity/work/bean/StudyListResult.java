package xxk.wuhai.seacurity.work.bean;

import java.util.List;

/**
 * Created by 86936 on 2018/7/10.
 *
 * @QQ 869360026
 */

public class StudyListResult {

    /**
     * haveNext : false
     * studyNoticeInfoList : [{"isRead":"string","studyCreateTime":"string","studyNoticeId":0,"studyPictureUrl":"string","studyReadCount":0,"studySummary":"string","studyTitle":"string"}]
     * totalNum : 0
     */

    private boolean haveNext;
    private int totalNum;
    private List<StudyNoticeInfoListBean> studyNoticeInfoList;

    public boolean isHaveNext() {
        return haveNext;
    }

    public void setHaveNext(boolean haveNext) {
        this.haveNext = haveNext;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<StudyNoticeInfoListBean> getStudyNoticeInfoList() {
        return studyNoticeInfoList;
    }

    public void setStudyNoticeInfoList(List<StudyNoticeInfoListBean> studyNoticeInfoList) {
        this.studyNoticeInfoList = studyNoticeInfoList;
    }

    public static class StudyNoticeInfoListBean {
        /**
         * isRead : string
         * studyCreateTime : string
         * studyNoticeId : 0
         * studyPictureUrl : string
         * studyReadCount : 0
         * studySummary : string
         * studyTitle : string
         */

        private String isRead;
        private String studyCreateTime;
        private int studyNoticeId;
        private String studyPictureUrl;
        private int studyReadCount;
        private String studySummary;
        private String studyTitle;

        public String getIsRead() {
            return isRead;
        }

        public void setIsRead(String isRead) {
            this.isRead = isRead;
        }

        public String getStudyCreateTime() {
            return studyCreateTime;
        }

        public void setStudyCreateTime(String studyCreateTime) {
            this.studyCreateTime = studyCreateTime;
        }

        public int getStudyNoticeId() {
            return studyNoticeId;
        }

        public void setStudyNoticeId(int studyNoticeId) {
            this.studyNoticeId = studyNoticeId;
        }

        public String getStudyPictureUrl() {
            return studyPictureUrl;
        }

        public void setStudyPictureUrl(String studyPictureUrl) {
            this.studyPictureUrl = studyPictureUrl;
        }

        public int getStudyReadCount() {
            return studyReadCount;
        }

        public void setStudyReadCount(int studyReadCount) {
            this.studyReadCount = studyReadCount;
        }

        public String getStudySummary() {
            return studySummary;
        }

        public void setStudySummary(String studySummary) {
            this.studySummary = studySummary;
        }

        public String getStudyTitle() {
            return studyTitle;
        }

        public void setStudyTitle(String studyTitle) {
            this.studyTitle = studyTitle;
        }
    }
}
