package xxk.wuhai.seacurity.login.bean;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */
public class DeptVoBean {
    /**
     * deptId : 15
     * deptName : 未分组
     * orgId : 19
     * sortNum : 0
     */

    private int deptId;
    private String deptName;
    private int orgId;
    private int sortNum;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }
}
