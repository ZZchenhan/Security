package xxk.wuhai.seacurity.work.vo;

/**
 * Created by 86936 on 2018/7/14.
 *
 * @QQ 869360026
 */

public class GetSchedulingVo {

    /**
     * date : string
     * userId : 0
     */

    private String dayStr;
    private int userId;

    public GetSchedulingVo(String date, int userId) {
        this.dayStr = date;
        this.userId = userId;
    }

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
}
