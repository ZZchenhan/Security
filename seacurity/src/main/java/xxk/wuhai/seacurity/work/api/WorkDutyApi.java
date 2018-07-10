package xxk.wuhai.seacurity.work.api;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.work.bean.ApDetailResult;
import xxk.wuhai.seacurity.work.bean.AplyResult;
import xxk.wuhai.seacurity.work.bean.AplyUser;
import xxk.wuhai.seacurity.work.bean.AplyUserResult;
import xxk.wuhai.seacurity.work.bean.ApproverUser;
import xxk.wuhai.seacurity.work.vo.ApDetailVo;
import xxk.wuhai.seacurity.work.vo.ApListVo;
import xxk.wuhai.seacurity.work.vo.ApplyLeaveVo;
import xxk.wuhai.seacurity.work.vo.GetSchedulingByUserIdVo;
import xxk.wuhai.seacurity.work.vo.SupplementApplyVo;

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

    /**
     * 请假
     * @param applyLeaveVo
     * @return
     */
    @POST("/client-api/approvalProcess/applyLeave ")
    Observable<Result<String>> applyLeave(@Body ApplyLeaveVo applyLeaveVo);


    /**
     * 补签
     * @return
     */
    @POST("/client-api/approvalProcess/supplementApply")
    Observable<Result<String>> supplementApply(@Body SupplementApplyVo supplementApplyVo);

    /**
     * 审核列表
     * @param apListVo
     * @return
     */
    @POST("/client-api//aPDetails/apList")
    Observable<Result<AplyResult>> apList(@Body ApListVo apListVo);

    @POST("/aPDetails/apDetail")
    Observable<Result<ApDetailResult>> apDetail(ApDetailVo apDetailVo);
}
