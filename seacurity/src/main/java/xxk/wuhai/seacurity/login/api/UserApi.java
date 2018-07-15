package xxk.wuhai.seacurity.login.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.contact.bean.StaffResult;
import xxk.wuhai.seacurity.login.bean.CodeBean;
import xxk.wuhai.seacurity.login.bean.UpdateBean;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.bean.UserInfoBean;
import xxk.wuhai.seacurity.login.result.LoginResult;
import xxk.wuhai.seacurity.login.result.TagResult;
import xxk.wuhai.seacurity.login.vo.AddLabelInfoApiVo;
import xxk.wuhai.seacurity.login.vo.ForgetPassVo;
import xxk.wuhai.seacurity.login.vo.GetCodeVo;
import xxk.wuhai.seacurity.login.vo.GetPraiseAndLabelVo;
import xxk.wuhai.seacurity.login.vo.GetUserInfoVo;
import xxk.wuhai.seacurity.login.vo.LoginBean;
import xxk.wuhai.seacurity.login.vo.UpdateUsers;
import xxk.wuhai.seacurity.work.bean.RecordBean;

/**
 * Created by 86936 on 2018/7/1.
 *
 * @QQ 869360026
 */
public interface UserApi {

    @POST("/client-api/user/login")
    Observable<Result<LoginResult>> login(@Body LoginBean requestBody);

    @POST("/client-api/user/getVerCode")
    Observable<Result<CodeBean>> getCode(@Body GetCodeVo getCodeVo);

    @POST("/client-api/user/forgetModifyPassword")
    Observable<Result<UpdateBean>> updatePass(@Body ForgetPassVo passVo);

    @POST("/client-api/user/getUserInfo")
    Observable<Result<UserDetailInfo>> getUserInfo(@Body GetUserInfoVo userInfoVo);


    @POST("/client-api/user/modify ")
    Observable<Result<RecordBean>> modify(@Body UpdateUsers userInfoBean);


    @POST("/client-api/customer/getPraiseAndLabel")
    Observable<TagResult> getPraiseAndLabel(@Body GetPraiseAndLabelVo getPraiseAndLabelVo);


    @POST("/client-api/staff/getStaffInfo")
    Observable<StaffResult> getStaffInfo(@Body GetPraiseAndLabelVo getPraiseAndLabelVo);

    @POST("/client-api/customer/doLabel")
    Observable<Result<String>> doPraise(@Body AddLabelInfoApiVo addLabelInfoApiVo);
}
