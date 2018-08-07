package com.hz.junxinbaoan.login.bean;

import java.util.List;

import com.hz.junxinbaoan.login.vo.UpdateUsers;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/21 15:11
 */
public class UserInfoBean {
    /**
     * userId : 21
     * userType : 4
     * name : 管理员二
     * accountName : CE5D55AE79D4F853B3F5EFFF1A1F0EAF
     * openId :
     * idCard : 410101199104222146
     * sex : 1
     * phone : 18129851846
     * email : 13750876192@qq.com
     * nation : 汉族
     * maritalStatus : 1
     * birthday : 2018-06-30
     * politicsType : 01
     * residenceStreetId : 0
     * residenceStreetName : null
     * residenceDistrictId : 2848
     * residenceDistrictName : 三环以内
     * residenceCityId : 51035
     * residenceCityName : 东丽区
     * residenceProvinceId : 3
     * residenceProvinceName : 天津
     * residenceAddress : 天津东丽区全境
     * livingStreetId : 0
     * livingStreetName : null
     * livingDistrictId : 699
     * livingDistrictName : 阿城区
     * livingCityId : 698
     * livingCityName : 哈尔滨市
     * livingProvinceId : 10
     * livingProvinceName : 黑龙江
     * livingAddress : 天津东丽区全境天津东丽区全境
     * photoUrl : http://www.baidu.com/
     * education : 3
     * height : 175
     * weight : 150
     * age : 18
     * bloodType : 2
     * workYear : 1
     * remark :
     * status : 0
     * namePinyin : guanliyuaner
     * gmtCreate : 2018-07-17 12:14:47
     * deviceNumber : RJCDU17616001737
     * deviceType : 5
     * clientId :
     * activeTime : 2018-07-17 12:14:47
     * guardUserInfo : null
     * orgId : 19
     * deptId : 19
     * relUserDeptOrgVo : {"deptId":19,"orgId":19,"level":"1","status":"0","joinTime":"2018-07-10 00:00:00","quitTime":"2018-07-10 17:49:35","beOfficialTime":"2018-07-10 17:49:35","rank":"经理","rankLevel":"0"}
     * relRoles : [{"roleId":15}]
     * deptVo : {"deptId":19,"deptName":"测试科技保安一部二大队","parentId":16,"orgId":19,"sortNum":2,"completeName":null,"deptVoList":null}
     */

    private long userId;
    private String userType;
    private String name;
    private String accountName;
    private String openId;
    private String idCard;
    private String sex;
    private String phone;
    private String email;
    private String nation;
    private String maritalStatus;
    private String birthday;
    private String politicsType;
    private int residenceStreetId;
    private Object residenceStreetName;
    private int residenceDistrictId;
    private String residenceDistrictName;
    private int residenceCityId;
    private String residenceCityName;
    private int residenceProvinceId;
    private String residenceProvinceName;
    private String residenceAddress;
    private int livingStreetId;
    private Object livingStreetName;
    private int livingDistrictId;
    private String livingDistrictName;
    private int livingCityId;
    private String livingCityName;
    private int livingProvinceId;
    private String livingProvinceName;
    private String livingAddress;
    private String photoUrl;
    private int education;
    private int height;
    private int weight;
    private int age;
    private String bloodType;
    private String workYear;
    private String remark;
    private String status;
    private String namePinyin;
    private String gmtCreate;
    private String deviceNumber;
    private String deviceType;
    private String clientId;
    private String activeTime;
    private Object guardUserInfo;
    private int orgId;
    private int deptId;
    private RelUserDeptOrgVoBean relUserDeptOrgVo;
    private DeptVoBean deptVo;
    private List<UpdateUsers.RelRolesBean> relRoles;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(String politicsType) {
        this.politicsType = politicsType;
    }

    public int getResidenceStreetId() {
        return residenceStreetId;
    }

    public void setResidenceStreetId(int residenceStreetId) {
        this.residenceStreetId = residenceStreetId;
    }

    public Object getResidenceStreetName() {
        return residenceStreetName;
    }

    public void setResidenceStreetName(Object residenceStreetName) {
        this.residenceStreetName = residenceStreetName;
    }

    public int getResidenceDistrictId() {
        return residenceDistrictId;
    }

    public void setResidenceDistrictId(int residenceDistrictId) {
        this.residenceDistrictId = residenceDistrictId;
    }

    public String getResidenceDistrictName() {
        return residenceDistrictName;
    }

    public void setResidenceDistrictName(String residenceDistrictName) {
        this.residenceDistrictName = residenceDistrictName;
    }

    public int getResidenceCityId() {
        return residenceCityId;
    }

    public void setResidenceCityId(int residenceCityId) {
        this.residenceCityId = residenceCityId;
    }

    public String getResidenceCityName() {
        return residenceCityName;
    }

    public void setResidenceCityName(String residenceCityName) {
        this.residenceCityName = residenceCityName;
    }

    public int getResidenceProvinceId() {
        return residenceProvinceId;
    }

    public void setResidenceProvinceId(int residenceProvinceId) {
        this.residenceProvinceId = residenceProvinceId;
    }

    public String getResidenceProvinceName() {
        return residenceProvinceName;
    }

    public void setResidenceProvinceName(String residenceProvinceName) {
        this.residenceProvinceName = residenceProvinceName;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public int getLivingStreetId() {
        return livingStreetId;
    }

    public void setLivingStreetId(int livingStreetId) {
        this.livingStreetId = livingStreetId;
    }

    public Object getLivingStreetName() {
        return livingStreetName;
    }

    public void setLivingStreetName(Object livingStreetName) {
        this.livingStreetName = livingStreetName;
    }

    public int getLivingDistrictId() {
        return livingDistrictId;
    }

    public void setLivingDistrictId(int livingDistrictId) {
        this.livingDistrictId = livingDistrictId;
    }

    public String getLivingDistrictName() {
        return livingDistrictName;
    }

    public void setLivingDistrictName(String livingDistrictName) {
        this.livingDistrictName = livingDistrictName;
    }

    public int getLivingCityId() {
        return livingCityId;
    }

    public void setLivingCityId(int livingCityId) {
        this.livingCityId = livingCityId;
    }

    public String getLivingCityName() {
        return livingCityName;
    }

    public void setLivingCityName(String livingCityName) {
        this.livingCityName = livingCityName;
    }

    public int getLivingProvinceId() {
        return livingProvinceId;
    }

    public void setLivingProvinceId(int livingProvinceId) {
        this.livingProvinceId = livingProvinceId;
    }

    public String getLivingProvinceName() {
        return livingProvinceName;
    }

    public void setLivingProvinceName(String livingProvinceName) {
        this.livingProvinceName = livingProvinceName;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public Object getGuardUserInfo() {
        return guardUserInfo;
    }

    public void setGuardUserInfo(Object guardUserInfo) {
        this.guardUserInfo = guardUserInfo;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public RelUserDeptOrgVoBean getRelUserDeptOrgVo() {
        return relUserDeptOrgVo;
    }

    public void setRelUserDeptOrgVo(RelUserDeptOrgVoBean relUserDeptOrgVo) {
        this.relUserDeptOrgVo = relUserDeptOrgVo;
    }

    public DeptVoBean getDeptVo() {
        return deptVo;
    }

    public void setDeptVo(DeptVoBean deptVo) {
        this.deptVo = deptVo;
    }

    public List<UpdateUsers.RelRolesBean> getRelRoles() {
        return relRoles;
    }

    public void setRelRoles(List<UpdateUsers.RelRolesBean> relRoles) {
        this.relRoles = relRoles;
    }

    public static class RelUserDeptOrgVoBean {
        /**
         * deptId : 19
         * orgId : 19
         * level : 1
         * status : 0
         * joinTime : 2018-07-10 00:00:00
         * quitTime : 2018-07-10 17:49:35
         * beOfficialTime : 2018-07-10 17:49:35
         * rank : 经理
         * rankLevel : 0
         */

        private int deptId;
        private int orgId;
        private String level;
        private String status;
        private String joinTime;
        private String quitTime;
        private String beOfficialTime;
        private String rank;
        private String rankLevel;

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

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getRankLevel() {
            return rankLevel;
        }

        public void setRankLevel(String rankLevel) {
            this.rankLevel = rankLevel;
        }
    }

    public static class DeptVoBean {
        /**
         * deptId : 19
         * deptName : 测试科技保安一部二大队
         * parentId : 16
         * orgId : 19
         * sortNum : 2
         * completeName : null
         * deptVoList : null
         */

        private int deptId;
        private String deptName;
        private int parentId;
        private int orgId;
        private int sortNum;
        private Object completeName;
        private Object deptVoList;

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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
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

        public Object getCompleteName() {
            return completeName;
        }

        public void setCompleteName(Object completeName) {
            this.completeName = completeName;
        }

        public Object getDeptVoList() {
            return deptVoList;
        }

        public void setDeptVoList(Object deptVoList) {
            this.deptVoList = deptVoList;
        }
    }

}
