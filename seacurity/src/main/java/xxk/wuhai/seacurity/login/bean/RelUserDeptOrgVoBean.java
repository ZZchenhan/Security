package xxk.wuhai.seacurity.login.bean;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */
public class RelUserDeptOrgVoBean implements Cloneable{
    /**
     * deptId : 15
     * orgId : 19
     * level : 0
     * status : 0
     * joinTime : 2018-07-04T13:37:56.000+0000
     * quitTime : 2018-07-04T13:37:56.000+0000
     * beOfficialTime : 2018-07-04T13:37:56.000+0000
     * rank : null
     */

    private int deptId;
    private int orgId;
    private String level;
    private String status;
    private String joinTime;
    private String quitTime;
    private String beOfficialTime;
    private Object rank;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(String quitTime) {
        this.quitTime = quitTime;
    }

    public String getBeOfficialTime() {
        return beOfficialTime;
    }

    public void setBeOfficialTime(String beOfficialTime) {
        this.beOfficialTime = beOfficialTime;
    }

    public Object getRank() {
        return rank;
    }

    public void setRank(Object rank) {
        this.rank = rank;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        RelUserDeptOrgVoBean o = (RelUserDeptOrgVoBean) super.clone();
        return o;
    }
}
