package xxk.wuhai.seacurity.login.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */
public class OrgVo implements Cloneable{
    /**
     * 部门对象
     */
    private List<DeptVoBean> deptVoList;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 机构id
     */
    private long orgId;

    /**
     * 0-公司，1-公安
     */
    private String type;

    public List<DeptVoBean> getDeptVoList() {
        return deptVoList;
    }

    public void setDeptVoList(List<DeptVoBean> deptVoList) {
        this.deptVoList = deptVoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OrgVo() {

    }

    public OrgVo(List<DeptVoBean> deptVoList, String name, long orgId, String type) {

        this.deptVoList = deptVoList;
        this.name = name;
        this.orgId = orgId;
        this.type = type;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        OrgVo orgVo = (OrgVo) super.clone();
        orgVo.deptVoList = (List<DeptVoBean>) ((ArrayList<DeptVoBean>)this.deptVoList).clone();
        return super.clone();
    }
}
