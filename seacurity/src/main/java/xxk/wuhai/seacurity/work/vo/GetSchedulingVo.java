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

    private String date;
    private int userId;

    public GetSchedulingVo(String date, int userId) {
        this.date = date;
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
