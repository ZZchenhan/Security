package xxk.wuhai.seacurity.login.bean;

import java.util.List;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class UserDetailInfo implements Cloneable{

    /**
     * userInfo : {"userId":21,"userType":"4","name":"管理员二","accountName":"CE5D55AE79D4F853B3F5EFFF1A1F0EAF","openId":"","idCard":"410101199104222146","sex":"1","phone":"18129851846","email":"13750876192@qq.com","nation":"汉族","maritalStatus":"1","birthday":"2018-06-30","politicsType":"01","residenceStreetId":0,"residenceStreetName":null,"residenceDistrictId":2848,"residenceDistrictName":"三环以内","residenceCityId":51035,"residenceCityName":"东丽区","residenceProvinceId":3,"residenceProvinceName":"天津","residenceAddress":"天津东丽区全境","livingStreetId":0,"livingStreetName":null,"livingDistrictId":699,"livingDistrictName":"阿城区","livingCityId":698,"livingCityName":"哈尔滨市","livingProvinceId":10,"livingProvinceName":"黑龙江","livingAddress":"天津东丽区全境天津东丽区全境","photoUrl":"http://www.baidu.com/","education":3,"height":175,"weight":150,"age":18,"bloodType":"2","workYear":"1","remark":"","status":"0","namePinyin":"guanliyuaner","gmtCreate":"2018-07-17 12:14:47","deviceNumber":"RJCDU17616001737","deviceType":"5","clientId":"","activeTime":"2018-07-17 12:14:47","guardUserInfo":null,"orgId":19,"deptId":19,"relUserDeptOrgVo":{"deptId":19,"orgId":19,"level":"1","status":"0","joinTime":"2018-07-10 00:00:00","quitTime":"2018-07-10 17:49:35","beOfficialTime":"2018-07-10 17:49:35","rank":"经理","rankLevel":"0"},"relRoles":[{"roleId":15}],"deptVo":{"deptId":19,"deptName":"测试科技保安一部二大队","parentId":16,"orgId":19,"sortNum":2,"completeName":null,"deptVoList":null}}
     * orgVo : {"orgId":null,"name":"科技公司","type":null,"deptVoList":null}
     * deptVo : {"deptId":19,"deptName":"测试科技保安一部二大队","orgId":19,"sortNum":2}
     * resourceVoList : [{"resourceId":1,"name":"登录","type":"0","sortNum":1,"sourceType":"0","parentId":null,"parentIds":"0","isSelected":"1","permission":"/client-api/user/login"},{"resourceId":2,"name":"考勤打卡","type":"0","sortNum":2,"sourceType":"0","parentId":null,"parentIds":"1","isSelected":"0","permission":""},{"resourceId":3,"name":"随手签","type":"0","sortNum":3,"sourceType":"0","parentId":null,"parentIds":"1","isSelected":"0","permission":""},{"resourceId":4,"name":"线索爆料","type":"0","sortNum":4,"sourceType":"0","parentId":null,"parentIds":"1","isSelected":"0","permission":""},{"resourceId":5,"name":"我的排班","type":"0","sortNum":5,"sourceType":"0","parentId":null,"parentIds":"1","isSelected":"0","permission":""},{"resourceId":6,"name":"审批申请","type":"0","sortNum":6,"sourceType":"0","parentId":null,"parentIds":"1","isSelected":"0","permission":""},{"resourceId":7,"name":"学习中心","type":"0","sortNum":7,"sourceType":"0","parentId":null,"parentIds":"1","isSelected":"0","permission":""}]
     */

    private UserInfoBean userInfo;
    private OrgVoBean orgVo;
    private DeptVoBeanX deptVo;
    private List<ResourceVoListBean> resourceVoList;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public OrgVoBean getOrgVo() {
        return orgVo;
    }

    public void setOrgVo(OrgVoBean orgVo) {
        this.orgVo = orgVo;
    }

    public DeptVoBeanX getDeptVo() {
        return deptVo;
    }

    public void setDeptVo(DeptVoBeanX deptVo) {
        this.deptVo = deptVo;
    }

    public List<ResourceVoListBean> getResourceVoList() {
        return resourceVoList;
    }

    public void setResourceVoList(List<ResourceVoListBean> resourceVoList) {
        this.resourceVoList = resourceVoList;
    }

    public static class OrgVoBean {
        /**
         * orgId : null
         * name : 科技公司
         * type : null
         * deptVoList : null
         */

        private Object orgId;
        private String name;
        private Object type;
        private Object deptVoList;

        public Object getOrgId() {
            return orgId;
        }

        public void setOrgId(Object orgId) {
            this.orgId = orgId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getDeptVoList() {
            return deptVoList;
        }

        public void setDeptVoList(Object deptVoList) {
            this.deptVoList = deptVoList;
        }
    }

    public static class DeptVoBeanX {
        /**
         * deptId : 19
         * deptName : 测试科技保安一部二大队
         * orgId : 19
         * sortNum : 2
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

    public static class ResourceVoListBean {
        /**
         * resourceId : 1
         * name : 登录
         * type : 0
         * sortNum : 1
         * sourceType : 0
         * parentId : null
         * parentIds : 0
         * isSelected : 1
         * permission : /client-api/user/login
         */

        private int resourceId;
        private String name;
        private String type;
        private int sortNum;
        private String sourceType;
        private Object parentId;
        private String parentIds;
        private String isSelected;
        private String permission;

        public int getResourceId() {
            return resourceId;
        }

        public void setResourceId(int resourceId) {
            this.resourceId = resourceId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSortNum() {
            return sortNum;
        }

        public void setSortNum(int sortNum) {
            this.sortNum = sortNum;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public String getParentIds() {
            return parentIds;
        }

        public void setParentIds(String parentIds) {
            this.parentIds = parentIds;
        }

        public String getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(String isSelected) {
            this.isSelected = isSelected;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }
    }
}



