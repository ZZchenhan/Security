package xxk.wuhai.seacurity.work.bean;

import java.util.List;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/10 16:16
 */
public class AplyResult {
    /**
     * approvalRecordList : [{"apName":"string","apUserId":0,"approvalRecordId":0,"createTime":"string","lrBeginTime":"string","lrEndTime":"string","lraDays":0,"name":"string","patchTime":"string","pictureUrls":["string"],"status":"string","supplement":"string","typeId":"string","userId":0}]
     * haveNext : false
     * totalNum : 0
     */
    private boolean haveNext;
    private int totalNum;
    private List<ApprovalRecordListBean> approvalRecordList;

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

    public List<ApprovalRecordListBean> getApprovalRecordList() {
        return approvalRecordList;
    }

    public void setApprovalRecordList(List<ApprovalRecordListBean> approvalRecordList) {
        this.approvalRecordList = approvalRecordList;
    }

}
