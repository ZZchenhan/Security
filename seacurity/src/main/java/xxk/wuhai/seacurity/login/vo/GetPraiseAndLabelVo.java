package xxk.wuhai.seacurity.login.vo;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class GetPraiseAndLabelVo {
    private long userId;

    public GetPraiseAndLabelVo(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}