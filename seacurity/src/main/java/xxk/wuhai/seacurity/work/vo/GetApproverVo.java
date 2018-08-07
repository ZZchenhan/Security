package xxk.wuhai.seacurity.work.vo;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class GetApproverVo {
    public GetApproverVo(long userId) {
        this.userId = userId;
    }

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
