package xxk.wuhai.seacurity.work.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.work.bean.AplyUser;
import xxk.wuhai.seacurity.work.bean.AplyUserResult;
import xxk.wuhai.seacurity.work.bean.ApproverUser;
import xxk.wuhai.seacurity.work.vo.ApplyLeaveVo;
import xxk.wuhai.seacurity.work.vo.GetSchedulingByUserIdVo;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public interface WorkDutyApi {

    @POST("/client-api/commonScheduling/getClockRateForEmp")
    Observable<Result<String>> getClockRateForEmp(@Body GetSchedulingByUserIdVo vo);


    @POST("/client-api/approvalProcess/getLeaverList")
    Observable<Result<AplyUserResult>> getLeaverList();

    @POST("/client-api/approvalProcess/getApprover")
    Observable<Result<ApproverUser>> getApprover();


    @POST("/client-api/approvalProcess/applyLeave ")
    Observable<Result<String>> applyLeave(@Body ApplyLeaveVo applyLeaveVo);
}
