package xxk.wuhai.seacurity.login.bean;

/**
 * Created by 86936 on 2018/7/23.
 *
 * @QQ 869360026
 */

public class Elec {

    /**
     * code : 200
     * message : success
     * errorType : null
     * result : {"userCertificateInfoVo":{"userType":"4","name":"管理员二","sex":"1","phone":"18129851846","birthday":"2018-06-30","residenceStreetId":0,"residenceStreetName":null,"residenceDistrictId":2659,"residenceDistrictName":"吐鲁番市","residenceCityId":2658,"residenceCityName":"吐鲁番地区","residenceProvinceId":31,"residenceProvinceName":"新疆","residenceAddress":"天津东丽区全境","livingStreetId":0,"livingStreetName":null,"livingDistrictId":699,"livingDistrictName":"阿城区","livingCityId":698,"livingCityName":"哈尔滨市","livingProvinceId":10,"livingProvinceName":"黑龙江","livingAddress":"天津东丽区全境天津东丽区全境","photoUrl":"http://www.baidu.com/","certificateNo":null,"companyName":"科技公司"}}
     */

    private String code;
    private String message;
    private Object errorType;
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

    public Object getErrorType() {
        return errorType;
    }

    public void setErrorType(Object errorType) {
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
         * userCertificateInfoVo : {"userType":"4","name":"管理员二","sex":"1","phone":"18129851846","birthday":"2018-06-30","residenceStreetId":0,"residenceStreetName":null,"residenceDistrictId":2659,"residenceDistrictName":"吐鲁番市","residenceCityId":2658,"residenceCityName":"吐鲁番地区","residenceProvinceId":31,"residenceProvinceName":"新疆","residenceAddress":"天津东丽区全境","livingStreetId":0,"livingStreetName":null,"livingDistrictId":699,"livingDistrictName":"阿城区","livingCityId":698,"livingCityName":"哈尔滨市","livingProvinceId":10,"livingProvinceName":"黑龙江","livingAddress":"天津东丽区全境天津东丽区全境","photoUrl":"http://www.baidu.com/","certificateNo":null,"companyName":"科技公司"}
         */

        private UserCertificateInfoVoBean userCertificateInfoVo;

        public UserCertificateInfoVoBean getUserCertificateInfoVo() {
            return userCertificateInfoVo;
        }

        public void setUserCertificateInfoVo(UserCertificateInfoVoBean userCertificateInfoVo) {
            this.userCertificateInfoVo = userCertificateInfoVo;
        }

        public static class UserCertificateInfoVoBean {
            /**
             * userType : 4
             * name : 管理员二
             * sex : 1
             * phone : 18129851846
             * birthday : 2018-06-30
             * residenceStreetId : 0
             * residenceStreetName : null
             * residenceDistrictId : 2659
             * residenceDistrictName : 吐鲁番市
             * residenceCityId : 2658
             * residenceCityName : 吐鲁番地区
             * residenceProvinceId : 31
             * residenceProvinceName : 新疆
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
             * certificateNo : null
             * companyName : 科技公司
             */

            private String userType;
            private String name;
            private String sex;
            private String phone;
            private String birthday;
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
            private String certificateNo;
            private String companyName;

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

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
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

            public String getCertificateNo() {
                return certificateNo;
            }

            public void setCertificateNo(String certificateNo) {
                this.certificateNo = certificateNo;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }
        }
    }
}
