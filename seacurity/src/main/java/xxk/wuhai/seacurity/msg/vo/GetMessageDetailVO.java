package xxk.wuhai.seacurity.msg.vo;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class GetMessageDetailVO {
    private int messageId;

    private String messageTypeId;

    public GetMessageDetailVO(int messageId, String messageTypeId) {
        this.messageId = messageId;
        this.messageTypeId = messageTypeId;
    }

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
}
