package xxk.wuhai.seacurity.work.vo;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 * 用户考勤信息
 */
public class GetSchedulingByTimeVo {

    public GetSchedulingByTimeVo(String endDay, String startDay, int userId) {
        this.endDay = endDay;
        this.startDay = startDay;
        this.userId = userId;
    }

    /**
     * endDay : string
     * startDay : string
     * userId : 0
     */


    private String endDay;
    private String startDay;
    private int userId;

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
