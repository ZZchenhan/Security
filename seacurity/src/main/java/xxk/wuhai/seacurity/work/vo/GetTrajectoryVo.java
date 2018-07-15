package xxk.wuhai.seacurity.work.vo;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class GetTrajectoryVo {

    /**
     * dayStr : 2018-07-01
     * userId : 0
     */

    private String dayStr;
    private int userId;

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public GetTrajectoryVo(String dayStr, int userId) {
        this.dayStr = dayStr;
        this.userId = userId;
    }
}
