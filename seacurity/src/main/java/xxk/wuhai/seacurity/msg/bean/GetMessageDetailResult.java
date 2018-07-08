package xxk.wuhai.seacurity.msg.bean;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class GetMessageDetailResult {
    private MessageDetail messageDetail;
    private PersonnelDetails personnelDetails;

    public MessageDetail getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(MessageDetail messageDetail) {
        this.messageDetail = messageDetail;
    }

    public PersonnelDetails getPersonnelDetails() {
        return personnelDetails;
    }

    public void setPersonnelDetails(PersonnelDetails personnelDetails) {
        this.personnelDetails = personnelDetails;
    }
}
