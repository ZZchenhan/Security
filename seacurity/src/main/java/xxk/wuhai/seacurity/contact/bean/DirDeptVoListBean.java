package xxk.wuhai.seacurity.contact.bean;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */
public class DirDeptVoListBean {
    /**
     * deptId : 0
     * deptName : string
     * orgId : 0
     * parentId : 0
     * sortNum : 0
     */

    private int deptId;
    private String deptName;
    private int orgId;
    private int parentId;
    private int sortNum;
    private int staffNum;

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public int getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(int staffNum) {
        this.staffNum = staffNum;
    }
}
