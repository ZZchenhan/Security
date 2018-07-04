package xxk.wuhai.seacurity.login.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.login.bean.CodeBean;
import xxk.wuhai.seacurity.login.bean.UpdateBean;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.result.LoginResult;
import xxk.wuhai.seacurity.login.vo.ForgetPassVo;
import xxk.wuhai.seacurity.login.vo.GetCodeVo;
import xxk.wuhai.seacurity.login.vo.GetUserInfoVo;
import xxk.wuhai.seacurity.login.vo.LoginBean;

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

}
