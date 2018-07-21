package xxk.wuhai.seacurity.work.vo;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/10 17:31
 */
public class ApDetailVo {
    private int approvalRecordId;
    private String messageId;

    public ApDetailVo(int approvalRecordId) {
        this.approvalRecordId = approvalRecordId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getApprovalRecordId() {
        return approvalRecordId;
    }

    public void setApprovalRecordId(int approvalRecordId) {
        this.approvalRecordId = approvalRecordId;
    }

    public ApDetailVo(int approvalRecordId, String messageId) {
        this.approvalRecordId = approvalRecordId;
        this.messageId = messageId;
    }
}
