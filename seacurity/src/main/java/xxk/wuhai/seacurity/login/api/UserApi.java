package xxk.wuhai.seacurity.login.api;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.login.result.LoginResult;
import xxk.wuhai.seacurity.login.vo.LoginBean;

/**
 * Created by 86936 on 2018/7/1.
 *
 * @QQ 869360026
 */
public interface UserApi {

    @POST("/client-api/user/login")
    Observable<Result<LoginResult>> login(@Body LoginBean requestBody);
}
