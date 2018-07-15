package xxk.wuhai.seacurity.login.vo;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class AddLabelInfoApiVo {

    /**
     * labelId : 0
     * userId : 0
     */

    private int labelId;
    private int userId;

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public AddLabelInfoApiVo(int labelId, int userId) {
        this.labelId = labelId;
        this.userId = userId;
    }
}
