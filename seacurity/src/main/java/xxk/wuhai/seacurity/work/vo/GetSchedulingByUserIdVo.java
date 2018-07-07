package xxk.wuhai.seacurity.work.vo;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */
public class GetSchedulingByUserIdVo {
    private String dateMonth;
    private long userId;

    public GetSchedulingByUserIdVo(String dateMonth, long userId) {
        this.dateMonth = dateMonth;
        this.userId = userId;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
