package xxk.wuhai.seacurity.login.bean;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */
public class UserInfoBean implements Cloneable{
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
     * maritalStatus : null
     * birthday : 2018-06-30T00:00:00.000+0000
     * politicsType : 01
     * residenceStreetId : null
     * residenceStreetName : null
     * residenceDistrictId : null
     * residenceDistrictName : null
     * residenceCityId : null
     * residenceCityName : null
     * residenceProvinceId : null
     * residenceProvinceName : null
     * residenceAddress : 杭州
     * livingStreetId : null
     * livingStreetName : null
     * livingDistrictId : null
     * livingDistrictName : null
     * livingCityId : null
     * livingCityName : null
     * livingProvinceId : null
     * livingProvinceName : null
     * livingAddress : 浙江杭州
     * photoUrl : http://www.baidu.com/
     * education : 0
     * height : 175
     * weight : 150
     * age : 18
     * bloodType : 0
     * workYear : 1
     * remark :
     * status : 0
     * namePinyin : guanliyuan
     * gmtCreate : 2018-07-07T13:59:52.000+0000
     * deviceNumber : RJCDU17616001737
     * deviceType : 2
     * clientId :
     * activeTime : 2018-07-07T13:59:53.000+0000
     * guardUserInfo : null
     * orgId : 19
     * deptId : 15
     * relUserDeptOrgVo : {"deptId":15,"orgId":19,"level":"0","status":"0","joinTime":"2018-07-04T13:37:56.000+0000","quitTime":"2018-07-04T13:37:56.000+0000","beOfficialTime":"2018-07-04T13:37:56.000+0000","rank":null}
     */

    private int userId;
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
    private Integer residenceStreetId;
    private String residenceStreetName;
    private Integer residenceDistrictId;
    private String residenceDistrictName;
    private Integer residenceCityId;
    private String residenceCityName;
    private Integer residenceProvinceId;
    private String residenceProvinceName;
    private String residenceAddress;
    private Integer livingStreetId;
    private String livingStreetName;
    private Integer livingDistrictId;
    private String livingDistrictName;
    private Integer livingCityId;
    private String livingCityName;
    private Integer livingProvinceId;
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
    private GuardUserVo guardUserInfo;
    private int orgId;
    private int deptId;
    private RelUserDeptOrgVoBean relUserDeptOrgVo;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public Integer getResidenceStreetId() {
        return residenceStreetId;
    }

    public void setResidenceStreetId(Integer residenceStreetId) {
        this.residenceStreetId = residenceStreetId;
    }

    public String getResidenceStreetName() {
        return residenceStreetName;
    }

    public void setResidenceStreetName(String residenceStreetName) {
        this.residenceStreetName = residenceStreetName;
    }

    public Integer getResidenceDistrictId() {
        return residenceDistrictId;
    }

    public void setResidenceDistrictId(Integer residenceDistrictId) {
        this.residenceDistrictId = residenceDistrictId;
    }

    public String getResidenceDistrictName() {
        return residenceDistrictName;
    }

    public void setResidenceDistrictName(String residenceDistrictName) {
        this.residenceDistrictName = residenceDistrictName;
    }

    public Integer getResidenceCityId() {
        return residenceCityId;
    }

    public void setResidenceCityId(Integer residenceCityId) {
        this.residenceCityId = residenceCityId;
    }

    public String getResidenceCityName() {
        return residenceCityName;
    }

    public void setResidenceCityName(String residenceCityName) {
        this.residenceCityName = residenceCityName;
    }

    public Integer getResidenceProvinceId() {
        return residenceProvinceId;
    }

    public void setResidenceProvinceId(Integer residenceProvinceId) {
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

    public Integer getLivingStreetId() {
        return livingStreetId;
    }

    public void setLivingStreetId(Integer livingStreetId) {
        this.livingStreetId = livingStreetId;
    }

    public String getLivingStreetName() {
        return livingStreetName;
    }

    public void setLivingStreetName(String livingStreetName) {
        this.livingStreetName = livingStreetName;
    }

    public Integer getLivingDistrictId() {
        return livingDistrictId;
    }

    public void setLivingDistrictId(Integer livingDistrictId) {
        this.livingDistrictId = livingDistrictId;
    }

    public String getLivingDistrictName() {
        return livingDistrictName;
    }

    public void setLivingDistrictName(String livingDistrictName) {
        this.livingDistrictName = livingDistrictName;
    }

    public Integer getLivingCityId() {
        return livingCityId;
    }

    public void setLivingCityId(Integer livingCityId) {
        this.livingCityId = livingCityId;
    }

    public String getLivingCityName() {
        return livingCityName;
    }

    public void setLivingCityName(String livingCityName) {
        this.livingCityName = livingCityName;
    }

    public Integer getLivingProvinceId() {
        return livingProvinceId;
    }

    public void setLivingProvinceId(Integer livingProvinceId) {
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

    public GuardUserVo getGuardUserInfo() {
        return guardUserInfo;
    }

    public void setGuardUserInfo(GuardUserVo guardUserInfo) {
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
}
