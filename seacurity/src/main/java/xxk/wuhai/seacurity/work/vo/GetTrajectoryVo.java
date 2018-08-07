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
    private long userId;

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public GetTrajectoryVo(String dayStr, long userId) {
        this.dayStr = dayStr;
        this.userId = userId;
    }
}
