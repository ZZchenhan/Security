package xxk.wuhai.seacurity.login.vo;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class GetPraiseAndLabelVo {
    private int userId;

    public GetPraiseAndLabelVo(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}