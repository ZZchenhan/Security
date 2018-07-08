package xxk.wuhai.seacurity.contact.bean;

import java.util.List;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class ContanctResult {
    /**
     * dirDeptVoList : [{"deptId":0,"deptName":"string","orgId":0,"parentId":0,"sortNum":0}]
     * dirOrgVo : {"name":"string","orgId":0,"type":"string"}
     * directoryVoList : [{"email":"string","livingAddress":"string","name":"string","namePinyin":"string","phone":"string","photoUrl":"string","rank":"string","sex":"string","status":"string","userId":0,"userType":"string"}]
     */
    private DirOrgVoBean dirOrgVo;
    private List<DirDeptVoListBean> dirDeptVoList;
    private List<DirectoryVo> directoryVoList;

    public DirOrgVoBean getDirOrgVo() {
        return dirOrgVo;
    }

    public void setDirOrgVo(DirOrgVoBean dirOrgVo) {
        this.dirOrgVo = dirOrgVo;
    }

    public List<DirDeptVoListBean> getDirDeptVoList() {
        return dirDeptVoList;
    }

    public void setDirDeptVoList(List<DirDeptVoListBean> dirDeptVoList) {
        this.dirDeptVoList = dirDeptVoList;
    }

    public List<DirectoryVo> getDirectoryVoList() {
        return directoryVoList;
    }

    public void setDirectoryVoList(List<DirectoryVo> directoryVoList) {
        this.directoryVoList = directoryVoList;
    }

    public static class DirOrgVoBean {
        /**
         * name : string
         * orgId : 0
         * type : string
         */

        private String name;
        private int orgId;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
