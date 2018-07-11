package xxk.wuhai.seacurity.work.vo;

/**
 * Created by 86936 on 2018/7/11.
 *
 * @QQ 869360026
 */

public class GetPersonScheduingVo {

    /**
     * empId : 19 部门id
     * endDay : 2018-07-31 结束时间
     * pageNum : 1 页数
     * pageSize : 20
     * staffId : 26 用户id
     * startDay : 2018-07-01 开始时间
     */

    private int empId;
    private String endDay;
    private int pageNum;
    private int pageSize;
    private int staffId;
    private String startDay;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }
}
