package xxk.wuhai.seacurity.login.bean;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class UserDetailInfo {
    /**
     * userInfo : {"userId":21,"userType":"4","name":"管理员二","accountName":"CE5D55AE79D4F853B3F5EFFF1A1F0EAF","openId":"","idCard":"410101199104222146","sex":"1","phone":"18129851846","email":"13750876192@qq.com","nation":"汉族","maritalStatus":null,"birthday":"2018-06-30T00:00:00.000+0000","politicsType":"01","residenceStreetId":null,"residenceStreetName":null,"residenceDistrictId":null,"residenceDistrictName":null,"residenceCityId":null,"residenceCityName":null,"residenceProvinceId":null,"residenceProvinceName":null,"residenceAddress":"杭州","livingStreetId":null,"livingStreetName":null,"livingDistrictId":null,"livingDistrictName":null,"livingCityId":null,"livingCityName":null,"livingProvinceId":null,"livingProvinceName":null,"livingAddress":"浙江杭州","photoUrl":"http://www.baidu.com/","education":0,"height":175,"weight":150,"age":18,"bloodType":"0","workYear":"1","remark":"","status":"0","namePinyin":"guanliyuan","gmtCreate":"2018-07-07T13:59:52.000+0000","deviceNumber":"RJCDU17616001737","deviceType":"2","clientId":"","activeTime":"2018-07-07T13:59:53.000+0000","guardUserInfo":null,"orgId":19,"deptId":15,"relUserDeptOrgVo":{"deptId":15,"orgId":19,"level":"0","status":"0","joinTime":"2018-07-04T13:37:56.000+0000","quitTime":"2018-07-04T13:37:56.000+0000","beOfficialTime":"2018-07-04T13:37:56.000+0000","rank":null}}
     * orgVo : null
     * deptVo : {"deptId":15,"deptName":"未分组","orgId":19,"sortNum":0}
     */

    private UserInfoBean userInfo;
    private OrgVo orgVo;
    private DeptVoBean deptVo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public OrgVo getOrgVo() {
        return orgVo;
    }

    public void setOrgVo(OrgVo orgVo) {
        this.orgVo = orgVo;
    }

    public DeptVoBean getDeptVo() {
        return deptVo;
    }

    public void setDeptVo(DeptVoBean deptVo) {
        this.deptVo = deptVo;
    }

}



