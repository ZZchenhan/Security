package xxk.wuhai.seacurity.work.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.work.vo.GetSchedulingByUserIdVo;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public interface WorkDutyApi {

    @POST("/client-api/commonScheduling/getClockRateForEmp")
    Observable<Result<String>> getClockRateForEmp(@Body GetSchedulingByUserIdVo vo);

}
