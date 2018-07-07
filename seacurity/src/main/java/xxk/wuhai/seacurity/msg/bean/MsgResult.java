package xxk.wuhai.seacurity.msg.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by 86936 on 2018/7/3.
 *
 * @QQ 869360026
 */

public class MsgResult{

        /**
         * messageInfoList : [{"messageId":6,"messageTypeId":"1","messageTitle":"全体注意","messageSummary":"没签到的赶紧签到","messagePictureUrls":null,"messageCreateTime":"2018-07-04 15:06:42","status":"0","isTop":"0","associatedId":null,"userId":null,"orgId":null,"deptId":null,"reportBeginTime":null,"reportEndTime":null},{"messageId":5,"messageTypeId":"1","messageTitle":"全体注意","messageSummary":"没签到的赶紧签到","messagePictureUrls":null,"messageCreateTime":"2018-07-04 15:06:22","status":"0","isTop":"0","associatedId":null,"userId":null,"orgId":null,"deptId":null,"reportBeginTime":null,"reportEndTime":null},{"messageId":4,"messageTypeId":"1","messageTitle":"全体注意","messageSummary":"噜啦噜啦类","messagePictureUrls":null,"messageCreateTime":"2018-07-04 10:31:11","status":"0","isTop":"0","associatedId":null,"userId":null,"orgId":null,"deptId":null,"reportBeginTime":null,"reportEndTime":null}]
         * totalNum : 3
         * unreadNum : 3
         * haveNext : false
         */
        private int totalNum;
        private int unreadNum;
        private boolean haveNext;
        private List<MessageInfoListBean> messageInfoList;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public int getUnreadNum() {
            return unreadNum;
        }

        public void setUnreadNum(int unreadNum) {
            this.unreadNum = unreadNum;
        }

        public boolean isHaveNext() {
            return haveNext;
        }

        public void setHaveNext(boolean haveNext) {
            this.haveNext = haveNext;
        }

        public List<MessageInfoListBean> getMessageInfoList() {
            return messageInfoList;
        }

        public void setMessageInfoList(List<MessageInfoListBean> messageInfoList) {
            this.messageInfoList = messageInfoList;
        }

}

