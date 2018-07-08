package xxk.wuhai.seacurity.contact;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.contact.bean.ContanctResult;
import xxk.wuhai.seacurity.contact.vo.ApiGetDirectoryVo;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public interface ContactApi {

    @POST("/client-api/staff/getDirectory")
    Observable<Result<ContanctResult>> getDirectory(@Body ApiGetDirectoryVo vo);
}
