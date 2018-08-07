package com.hz.junxinbaoan.login.vo;

import java.util.List;

import com.hz.junxinbaoan.login.bean.UserDetailInfo;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class UpdateUsers {

    public void change(UserDetailInfo userDetailInfo){
        this.age  = userDetailInfo.getUserInfo().getAge();
        this.birthday = userDetailInfo.getUserInfo().getBirthday();
        this.bloodType = userDetailInfo.getUserInfo().getBloodType();
        this.clientId = userDetailInfo.getUserInfo().getClientId();
        this.residenceProvinceId = userDetailInfo.getUserInfo().getResidenceProvinceId();
        this.residenceCityId = userDetailInfo.getUserInfo().getResidenceCityId();
        this.residenceDistrictId = userDetailInfo.getUserInfo().getResidenceDistrictId();
        this.livingProvinceId = userDetailInfo.getUserInfo().getLivingProvinceId();
        this.livingCityId = userDetailInfo.getUserInfo().getLivingCityId();
        this.livingDistrictId = userDetailInfo.getUserInfo().getLivingDistrictId();
        this.deviceNumber = userDetailInfo.getUserInfo().getDeviceNumber();
        this.education = userDetailInfo.getUserInfo().getEducation();
        this.email = userDetailInfo.getUserInfo().getEmail();
        this.guardExtendInfo = null;
        this.height = userDetailInfo.getUserInfo().getHeight();
        this.idCard = userDetailInfo.getUserInfo().getIdCard();
        this.livingAddress = userDetailInfo.getUserInfo().getLivingAddress();
        this.maritalStatus = userDetailInfo.getUserInfo().getMaritalStatus() == null?"0":userDetailInfo.getUserInfo().getMaritalStatus();
        this.name = userDetailInfo.getUserInfo().getName();
        this.namePinyin = userDetailInfo.getUserInfo().getNamePinyin();
        this.nation = userDetailInfo.getUserInfo().getNation();
        this.openId = userDetailInfo.getUserInfo().getOpenId();
        this.phone = userDetailInfo.getUserInfo().getPhone();
        this.photoUrl = userDetailInfo.getUserInfo().getPhotoUrl();
        this.politicsType = userDetailInfo.getUserInfo().getPoliticsType();
        this.relDept = new RelDeptBean();
        if(userDetailInfo.getUserInfo().getRelUserDeptOrgVo() != null){
            this.relDept.deptId = userDetailInfo.getUserInfo().getRelUserDeptOrgVo().getDeptId();
            this.relDept.level = userDetailInfo.getUserInfo().getRelUserDeptOrgVo().getLevel();
        }
        this.relOrg = new RelOrgBean();
        this.relOrg.orgId = userDetailInfo.getUserInfo().getOrgId();
        this.relRoles = userDetailInfo.getUserInfo().getRelRoles();
        this.remark = userDetailInfo.getUserInfo().getRemark();
        this.residenceAddress = userDetailInfo.getUserInfo().getResidenceAddress();
        this.sex = userDetailInfo.getUserInfo().getSex();
        this.weight = userDetailInfo.getUserInfo().getWeight();
        this.workYear = userDetailInfo.getUserInfo().getWorkYear();
    }

    /**
     * age : 32
     * birthday : 2018-01-01
     * bloodType : 99
     * clientId : wserasfdasdf
     * deviceNumber : asdfasdfas
     * education : 0
     * email : 23412341@qq.com
     * guardExtendInfo : {"certificateNo":"string","expireDate":"2019-01-01","issueDate":"2018-01-01","issueOrganization":"string","qualificationNo":"string"}
     * height : 160
     * idCard : 35032219990101999x
     * livingAddress : 文二西路180号
     * livingCityId : 1213
     * livingDistrictId : 52667
     * livingProvinceId : 15
     * livingStreetId : 52667
     * maritalStatus : 1
     * name : 张三
     * namePinyin : zhangsan
     * nation : 汉族
     * openId : asdfasfdasdfasdfasd
     * phone : 12345689878
     * photoUrl : http://www.baidu.com/
     * politicsType : 13
     * relDept : {"deptId":0,"level":"string","rank":"string"}
     * relOrg : {"orgId":0}
     * relRoles : [{"roleId":0}]
     * remark : asdf
     * residenceAddress : 文二西路180号
     * residenceCityId : 1213
     * residenceDistrictId : 3411
     * residenceProvinceId : 15
     * residenceStreetId : 52667
     * sex : 1
     * weight : 170
     * workYear : 1
     */

    private int age;
    private String birthday;
    private String bloodType;
    private String clientId;
    private String deviceNumber;
    private int education;
    private String email;
    private GuardExtendInfoBean guardExtendInfo;
    private int height;
    private String idCard;
    private String livingAddress;
    private int livingCityId;
    private int livingDistrictId;
    private int livingProvinceId;
    private int livingStreetId;
    private String maritalStatus;
    private String name;
    private String namePinyin;
    private String nation;
    private String openId;
    private String phone;
    private String photoUrl;
    private String politicsType;
    private RelDeptBean relDept;
    private RelOrgBean relOrg;
    private String remark;
    private String residenceAddress;
    private int residenceCityId;
    private int residenceDistrictId;
    private int residenceProvinceId;
    private int residenceStreetId;
    private String sex;
    private int weight;
    private String workYear;
    private List<RelRolesBean> relRoles;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GuardExtendInfoBean getGuardExtendInfo() {
        return guardExtendInfo;
    }

    public void setGuardExtendInfo(GuardExtendInfoBean guardExtendInfo) {
        this.guardExtendInfo = guardExtendInfo;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public int getLivingCityId() {
        return livingCityId;
    }

    public void setLivingCityId(int livingCityId) {
        this.livingCityId = livingCityId;
    }

    public int getLivingDistrictId() {
        return livingDistrictId;
    }

    public void setLivingDistrictId(int livingDistrictId) {
        this.livingDistrictId = livingDistrictId;
    }

    public int getLivingProvinceId() {
        return livingProvinceId;
    }

    public void setLivingProvinceId(int livingProvinceId) {
        this.livingProvinceId = livingProvinceId;
    }

    public int getLivingStreetId() {
        return livingStreetId;
    }

    public void setLivingStreetId(int livingStreetId) {
        this.livingStreetId = livingStreetId;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(String politicsType) {
        this.politicsType = politicsType;
    }

    public RelDeptBean getRelDept() {
        return relDept;
    }

    public void setRelDept(RelDeptBean relDept) {
        this.relDept = relDept;
    }

    public RelOrgBean getRelOrg() {
        return relOrg;
    }

    public void setRelOrg(RelOrgBean relOrg) {
        this.relOrg = relOrg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public int getResidenceCityId() {
        return residenceCityId;
    }

    public void setResidenceCityId(int residenceCityId) {
        this.residenceCityId = residenceCityId;
    }

    public int getResidenceDistrictId() {
        return residenceDistrictId;
    }

    public void setResidenceDistrictId(int residenceDistrictId) {
        this.residenceDistrictId = residenceDistrictId;
    }

    public int getResidenceProvinceId() {
        return residenceProvinceId;
    }

    public void setResidenceProvinceId(int residenceProvinceId) {
        this.residenceProvinceId = residenceProvinceId;
    }

    public int getResidenceStreetId() {
        return residenceStreetId;
    }

    public void setResidenceStreetId(int residenceStreetId) {
        this.residenceStreetId = residenceStreetId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public List<RelRolesBean> getRelRoles() {
        return relRoles;
    }

    public void setRelRoles(List<RelRolesBean> relRoles) {
        this.relRoles = relRoles;
    }

    public static class GuardExtendInfoBean {
        /**
         * certificateNo : string
         * expireDate : 2019-01-01
         * issueDate : 2018-01-01
         * issueOrganization : string
         * qualificationNo : string
         */

        private String certificateNo;
        private String expireDate;
        private String issueDate;
        private String issueOrganization;
        private String qualificationNo;

        public String getCertificateNo() {
            return certificateNo;
        }

        public void setCertificateNo(String certificateNo) {
            this.certificateNo = certificateNo;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

        public String getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(String issueDate) {
            this.issueDate = issueDate;
        }

        public String getIssueOrganization() {
            return issueOrganization;
        }

        public void setIssueOrganization(String issueOrganization) {
            this.issueOrganization = issueOrganization;
        }

        public String getQualificationNo() {
            return qualificationNo;
        }

        public void setQualificationNo(String qualificationNo) {
            this.qualificationNo = qualificationNo;
        }
    }

    public static class RelDeptBean {
        /**
         * deptId : 0
         * level : string
         * rank : string
         */

        private int deptId;
        private String level;
        private String rank;

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }

    public static class RelOrgBean {
        /**
         * orgId : 0
         */

        private int orgId;

        public int getOrgId() {
            return orgId;
        }

        public void setOrgId(int orgId) {
            this.orgId = orgId;
        }
    }

    public static class RelRolesBean {
        /**
         * roleId : 0
         */

        private int roleId;

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }
    }
}
