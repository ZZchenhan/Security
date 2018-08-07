package com.hz.junxinbaoan.login.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.contact.bean.StaffResult;
import com.hz.junxinbaoan.login.bean.CodeBean;
import com.hz.junxinbaoan.login.bean.Elec;
import com.hz.junxinbaoan.login.bean.UpdateBean;
import com.hz.junxinbaoan.login.bean.UserDetailInfo;
import com.hz.junxinbaoan.login.result.LoginResult;
import com.hz.junxinbaoan.login.result.TagResult;
import com.hz.junxinbaoan.login.vo.AddLabelInfoApiVo;
import com.hz.junxinbaoan.login.vo.ForgetPassVo;
import com.hz.junxinbaoan.login.vo.GetCodeVo;
import com.hz.junxinbaoan.login.vo.GetElecCard;
import com.hz.junxinbaoan.login.vo.GetPraiseAndLabelVo;
import com.hz.junxinbaoan.login.vo.GetUserInfoVo;
import com.hz.junxinbaoan.login.vo.LoginBean;
import com.hz.junxinbaoan.login.vo.UpdateUsers;
import com.hz.junxinbaoan.work.bean.RecordBean;

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


    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String   fileUrl);

    @POST("/client-api/user/getCertificateInfo ")
    Observable<Elec> getElectCard(@Body GetElecCard getElecCard);
}
