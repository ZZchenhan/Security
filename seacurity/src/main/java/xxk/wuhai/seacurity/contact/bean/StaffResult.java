package xxk.wuhai.seacurity.contact.bean;

import java.util.List;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class StaffResult {

    /**
     * code : 200
     * message : success
     * errorType : null
     * result : {"staffInfoVo":{"userId":7,"userType":"1","name":"三胖","accountName":"1D27D16598AAA707F87014B0BB58D094","openId":"","idCard":"410101199104112015","sex":"1","phone":"15557196983","email":"23617330@qq.com","nation":"汉族","maritalStatus":null,"birthday":"2018-06-30","politicsType":"01","residenceStreetId":null,"residenceStreetName":null,"residenceDistrictId":null,"residenceDistrictName":null,"residenceCityId":null,"residenceCityName":null,"residenceProvinceId":null,"residenceProvinceName":null,"residenceAddress":"河南","livingStreetId":null,"livingStreetName":null,"livingDistrictId":null,"livingDistrictName":null,"livingCityId":null,"livingCityName":null,"livingProvinceId":null,"livingProvinceName":null,"livingAddress":"浙江杭州","photoUrl":"http://www.baidu.com/","education":0,"height":175,"weight":150,"age":18,"bloodType":"0","workYear":"1","remark":"","status":"0","namePinyin":"sanpang","gmtCreate":"2018-07-01 14:20:06","deviceNumber":"85aa7f40d75b46b58a857b3cebb4c1fd","deviceType":null,"clientId":"asdfadsfasfafasfda","activeTime":null,"guardUserInfo":{"guardId":6,"qualificationId":6,"certificateNo":"12254225","guardQualificationInfo":{"qualificationId":6,"qualificationNo":"26654665","issueDate":"2018-06-30T00:00:00.000+0000","issueOrganization":"浙江公安"}},"orgId":19,"deptId":15,"relUserDeptOrgVo":{"deptId":15,"orgId":19,"level":"0","status":"2","joinTime":"2018-07-04 14:41:43","quitTime":"2018-07-04 14:41:43","beOfficialTime":"2018-07-04 14:41:43","rank":null},"relRoles":[{"roleId":1}],"deptVo":{"deptId":15,"deptName":"未分组","parentId":0,"orgId":19,"sortNum":0,"completeName":null,"deptVoList":null}}}
     */

    private String code;
    private String message;
    private String errorType;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * staffInfoVo : {"userId":7,"userType":"1","name":"三胖","accountName":"1D27D16598AAA707F87014B0BB58D094","openId":"","idCard":"410101199104112015","sex":"1","phone":"15557196983","email":"23617330@qq.com","nation":"汉族","maritalStatus":null,"birthday":"2018-06-30","politicsType":"01","residenceStreetId":null,"residenceStreetName":null,"residenceDistrictId":null,"residenceDistrictName":null,"residenceCityId":null,"residenceCityName":null,"residenceProvinceId":null,"residenceProvinceName":null,"residenceAddress":"河南","livingStreetId":null,"livingStreetName":null,"livingDistrictId":null,"livingDistrictName":null,"livingCityId":null,"livingCityName":null,"livingProvinceId":null,"livingProvinceName":null,"livingAddress":"浙江杭州","photoUrl":"http://www.baidu.com/","education":0,"height":175,"weight":150,"age":18,"bloodType":"0","workYear":"1","remark":"","status":"0","namePinyin":"sanpang","gmtCreate":"2018-07-01 14:20:06","deviceNumber":"85aa7f40d75b46b58a857b3cebb4c1fd","deviceType":null,"clientId":"asdfadsfasfafasfda","activeTime":null,"guardUserInfo":{"guardId":6,"qualificationId":6,"certificateNo":"12254225","guardQualificationInfo":{"qualificationId":6,"qualificationNo":"26654665","issueDate":"2018-06-30T00:00:00.000+0000","issueOrganization":"浙江公安"}},"orgId":19,"deptId":15,"relUserDeptOrgVo":{"deptId":15,"orgId":19,"level":"0","status":"2","joinTime":"2018-07-04 14:41:43","quitTime":"2018-07-04 14:41:43","beOfficialTime":"2018-07-04 14:41:43","rank":null},"relRoles":[{"roleId":1}],"deptVo":{"deptId":15,"deptName":"未分组","parentId":0,"orgId":19,"sortNum":0,"completeName":null,"deptVoList":null}}
         */

        private StaffInfoVoBean staffInfoVo;

        public StaffInfoVoBean getStaffInfoVo() {
            return staffInfoVo;
        }

        public void setStaffInfoVo(StaffInfoVoBean staffInfoVo) {
            this.staffInfoVo = staffInfoVo;
        }

        public static class StaffInfoVoBean {
            /**
             * userId : 7
             * userType : 1
             * name : 三胖
             * accountName : 1D27D16598AAA707F87014B0BB58D094
             * openId :
             * idCard : 410101199104112015
             * sex : 1
             * phone : 15557196983
             * email : 23617330@qq.com
             * nation : 汉族
             * maritalStatus : null
             * birthday : 2018-06-30
             * politicsType : 01
             * residenceStreetId : null
             * residenceStreetName : null
             * residenceDistrictId : null
             * residenceDistrictName : null
             * residenceCityId : null
             * residenceCityName : null
             * residenceProvinceId : null
             * residenceProvinceName : null
             * residenceAddress : 河南
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
             * namePinyin : sanpang
             * gmtCreate : 2018-07-01 14:20:06
             * deviceNumber : 85aa7f40d75b46b58a857b3cebb4c1fd
             * deviceType : null
             * clientId : asdfadsfasfafasfda
             * activeTime : null
             * guardUserInfo : {"guardId":6,"qualificationId":6,"certificateNo":"12254225","guardQualificationInfo":{"qualificationId":6,"qualificationNo":"26654665","issueDate":"2018-06-30T00:00:00.000+0000","issueOrganization":"浙江公安"}}
             * orgId : 19
             * deptId : 15
             * relUserDeptOrgVo : {"deptId":15,"orgId":19,"level":"0","status":"2","joinTime":"2018-07-04 14:41:43","quitTime":"2018-07-04 14:41:43","beOfficialTime":"2018-07-04 14:41:43","rank":null}
             * relRoles : [{"roleId":1}]
             * deptVo : {"deptId":15,"deptName":"未分组","parentId":0,"orgId":19,"sortNum":0,"completeName":null,"deptVoList":null}
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
            private Object maritalStatus;
            private String birthday;
            private String politicsType;
            private Object residenceStreetId;
            private Object residenceStreetName;
            private Object residenceDistrictId;
            private Object residenceDistrictName;
            private Object residenceCityId;
            private Object residenceCityName;
            private Object residenceProvinceId;
            private Object residenceProvinceName;
            private String residenceAddress;
            private Object livingStreetId;
            private Object livingStreetName;
            private Object livingDistrictId;
            private Object livingDistrictName;
            private Object livingCityId;
            private Object livingCityName;
            private Object livingProvinceId;
            private Object livingProvinceName;
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
            private Object deviceType;
            private String clientId;
            private Object activeTime;
            private GuardUserInfoBean guardUserInfo;
            private int orgId;
            private int deptId;
            private RelUserDeptOrgVoBean relUserDeptOrgVo;
            private DeptVoBean deptVo;
            private List<RelRolesBean> relRoles;

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

            public Object getMaritalStatus() {
                return maritalStatus;
            }

            public void setMaritalStatus(Object maritalStatus) {
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

            public Object getResidenceStreetId() {
                return residenceStreetId;
            }

            public void setResidenceStreetId(Object residenceStreetId) {
                this.residenceStreetId = residenceStreetId;
            }

            public Object getResidenceStreetName() {
                return residenceStreetName;
            }

            public void setResidenceStreetName(Object residenceStreetName) {
                this.residenceStreetName = residenceStreetName;
            }

            public Object getResidenceDistrictId() {
                return residenceDistrictId;
            }

            public void setResidenceDistrictId(Object residenceDistrictId) {
                this.residenceDistrictId = residenceDistrictId;
            }

            public Object getResidenceDistrictName() {
                return residenceDistrictName;
            }

            public void setResidenceDistrictName(Object residenceDistrictName) {
                this.residenceDistrictName = residenceDistrictName;
            }

            public Object getResidenceCityId() {
                return residenceCityId;
            }

            public void setResidenceCityId(Object residenceCityId) {
                this.residenceCityId = residenceCityId;
            }

            public Object getResidenceCityName() {
                return residenceCityName;
            }

            public void setResidenceCityName(Object residenceCityName) {
                this.residenceCityName = residenceCityName;
            }

            public Object getResidenceProvinceId() {
                return residenceProvinceId;
            }

            public void setResidenceProvinceId(Object residenceProvinceId) {
                this.residenceProvinceId = residenceProvinceId;
            }

            public Object getResidenceProvinceName() {
                return residenceProvinceName;
            }

            public void setResidenceProvinceName(Object residenceProvinceName) {
                this.residenceProvinceName = residenceProvinceName;
            }

            public String getResidenceAddress() {
                return residenceAddress;
            }

            public void setResidenceAddress(String residenceAddress) {
                this.residenceAddress = residenceAddress;
            }

            public Object getLivingStreetId() {
                return livingStreetId;
            }

            public void setLivingStreetId(Object livingStreetId) {
                this.livingStreetId = livingStreetId;
            }

            public Object getLivingStreetName() {
                return livingStreetName;
            }

            public void setLivingStreetName(Object livingStreetName) {
                this.livingStreetName = livingStreetName;
            }

            public Object getLivingDistrictId() {
                return livingDistrictId;
            }

            public void setLivingDistrictId(Object livingDistrictId) {
                this.livingDistrictId = livingDistrictId;
            }

            public Object getLivingDistrictName() {
                return livingDistrictName;
            }

            public void setLivingDistrictName(Object livingDistrictName) {
                this.livingDistrictName = livingDistrictName;
            }

            public Object getLivingCityId() {
                return livingCityId;
            }

            public void setLivingCityId(Object livingCityId) {
                this.livingCityId = livingCityId;
            }

            public Object getLivingCityName() {
                return livingCityName;
            }

            public void setLivingCityName(Object livingCityName) {
                this.livingCityName = livingCityName;
            }

            public Object getLivingProvinceId() {
                return livingProvinceId;
            }

            public void setLivingProvinceId(Object livingProvinceId) {
                this.livingProvinceId = livingProvinceId;
            }

            public Object getLivingProvinceName() {
                return livingProvinceName;
            }

            public void setLivingProvinceName(Object livingProvinceName) {
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

            public Object getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(Object deviceType) {
                this.deviceType = deviceType;
            }

            public String getClientId() {
                return clientId;
            }

            public void setClientId(String clientId) {
                this.clientId = clientId;
            }

            public Object getActiveTime() {
                return activeTime;
            }

            public void setActiveTime(Object activeTime) {
                this.activeTime = activeTime;
            }

            public GuardUserInfoBean getGuardUserInfo() {
                return guardUserInfo;
            }

            public void setGuardUserInfo(GuardUserInfoBean guardUserInfo) {
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

            public List<RelRolesBean> getRelRoles() {
                return relRoles;
            }

            public void setRelRoles(List<RelRolesBean> relRoles) {
                this.relRoles = relRoles;
            }

            public static class GuardUserInfoBean {
                /**
                 * guardId : 6
                 * qualificationId : 6
                 * certificateNo : 12254225
                 * guardQualificationInfo : {"qualificationId":6,"qualificationNo":"26654665","issueDate":"2018-06-30T00:00:00.000+0000","issueOrganization":"浙江公安"}
                 */

                private int guardId;
                private int qualificationId;
                private String certificateNo;
                private GuardQualificationInfoBean guardQualificationInfo;

                public int getGuardId() {
                    return guardId;
                }

                public void setGuardId(int guardId) {
                    this.guardId = guardId;
                }

                public int getQualificationId() {
                    return qualificationId;
                }

                public void setQualificationId(int qualificationId) {
                    this.qualificationId = qualificationId;
                }

                public String getCertificateNo() {
                    return certificateNo;
                }

                public void setCertificateNo(String certificateNo) {
                    this.certificateNo = certificateNo;
                }

                public GuardQualificationInfoBean getGuardQualificationInfo() {
                    return guardQualificationInfo;
                }

                public void setGuardQualificationInfo(GuardQualificationInfoBean guardQualificationInfo) {
                    this.guardQualificationInfo = guardQualificationInfo;
                }

                public static class GuardQualificationInfoBean {
                    /**
                     * qualificationId : 6
                     * qualificationNo : 26654665
                     * issueDate : 2018-06-30T00:00:00.000+0000
                     * issueOrganization : 浙江公安
                     */

                    private int qualificationId;
                    private String qualificationNo;
                    private String issueDate;
                    private String issueOrganization;

                    public int getQualificationId() {
                        return qualificationId;
                    }

                    public void setQualificationId(int qualificationId) {
                        this.qualificationId = qualificationId;
                    }

                    public String getQualificationNo() {
                        return qualificationNo;
                    }

                    public void setQualificationNo(String qualificationNo) {
                        this.qualificationNo = qualificationNo;
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
                }
            }

            public static class RelUserDeptOrgVoBean {
                /**
                 * deptId : 15
                 * orgId : 19
                 * level : 0
                 * status : 2
                 * joinTime : 2018-07-04 14:41:43
                 * quitTime : 2018-07-04 14:41:43
                 * beOfficialTime : 2018-07-04 14:41:43
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
            }

            public static class DeptVoBean {
                /**
                 * deptId : 15
                 * deptName : 未分组
                 * parentId : 0
                 * orgId : 19
                 * sortNum : 0
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

            public static class RelRolesBean {
                /**
                 * roleId : 1
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
    }
}
