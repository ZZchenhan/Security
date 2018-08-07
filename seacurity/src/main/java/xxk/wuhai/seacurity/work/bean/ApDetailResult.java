package xxk.wuhai.seacurity.work.bean;

import java.util.List;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/10 17:47
 */
public class ApDetailResult {

    /**
     * apDetail : {"apName":"string","apUserId":0,"approvalRecordId":0,"approverId":0,"approverName":"string","createTime":"string","lrBeginTime":"string","lrEndTime":"string","lraDays":"string","name":"string","patchTime":"string","pictureUrls":["string"],"status":"string","supplement":"string","typeId":"string","userId":0}
     */

    private ApDetailBean apDetail;

    public ApDetailBean getApDetail() {
        return apDetail;
    }

    public void setApDetail(ApDetailBean apDetail) {
        this.apDetail = apDetail;
    }

    public static class ApDetailBean {
        /**
         * apName : string
         * apUserId : 0
         * approvalRecordId : 0
         * approverId : 0
         * approverName : string
         * createTime : string
         * lrBeginTime : string
         * lrEndTime : string
         * lraDays : string
         * name : string
         * patchTime : string
         * pictureUrls : ["string"]
         * status : string
         * supplement : string
         * typeId : string
         * userId : 0
         */

        private String apName;
        private long apUserId;
        private int approvalRecordId;
        private int approverId;
        private String approverName;
        private String createTime;
        private String lrBeginTime;
        private String lrEndTime;
        private String lraDays;
        private String name;
        private String patchTime;
        private String status;
        private String supplement;
        private String typeId;
        private long userId;
        private List<String> pictureUrls;

        public String getApName() {
            return apName;
        }

        public void setApName(String apName) {
            this.apName = apName;
        }

        public long getApUserId() {
            return apUserId;
        }

        public void setApUserId(long apUserId) {
            this.apUserId = apUserId;
        }

        public int getApprovalRecordId() {
            return approvalRecordId;
        }

        public void setApprovalRecordId(int approvalRecordId) {
            this.approvalRecordId = approvalRecordId;
        }

        public int getApproverId() {
            return approverId;
        }

        public void setApproverId(int approverId) {
            this.approverId = approverId;
        }

        public String getApproverName() {
            return approverName;
        }

        public void setApproverName(String approverName) {
            this.approverName = approverName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getLrBeginTime() {
            return lrBeginTime;
        }

        public void setLrBeginTime(String lrBeginTime) {
            this.lrBeginTime = lrBeginTime;
        }

        public String getLrEndTime() {
            return lrEndTime;
        }

        public void setLrEndTime(String lrEndTime) {
            this.lrEndTime = lrEndTime;
        }

        public String getLraDays() {
            return lraDays;
        }

        public void setLraDays(String lraDays) {
            this.lraDays = lraDays;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPatchTime() {
            return patchTime;
        }

        public void setPatchTime(String patchTime) {
            this.patchTime = patchTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSupplement() {
            return supplement;
        }

        public void setSupplement(String supplement) {
            this.supplement = supplement;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public List<String> getPictureUrls() {
            return pictureUrls;
        }

        public void setPictureUrls(List<String> pictureUrls) {
            this.pictureUrls = pictureUrls;
        }
    }
}
