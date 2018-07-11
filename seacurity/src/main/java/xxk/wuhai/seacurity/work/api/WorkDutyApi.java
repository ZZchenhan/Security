package xxk.wuhai.seacurity.work.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.work.bean.ApDetailResult;
import xxk.wuhai.seacurity.work.bean.AplyResult;
import xxk.wuhai.seacurity.work.bean.AplyUserResult;
import xxk.wuhai.seacurity.work.bean.ApproverUser;
import xxk.wuhai.seacurity.work.bean.ClueBursList;
import xxk.wuhai.seacurity.work.bean.ClueBurstDetailResult;
import xxk.wuhai.seacurity.work.bean.StudyDetail;
import xxk.wuhai.seacurity.work.vo.AddClueBurstVo;
import xxk.wuhai.seacurity.work.vo.ApDetailVo;
import xxk.wuhai.seacurity.work.vo.ApListVo;
import xxk.wuhai.seacurity.work.vo.ApplyLeaveVo;
import xxk.wuhai.seacurity.work.vo.ClueBurstListVo;
import xxk.wuhai.seacurity.work.vo.GetClueBurstDetailsVo;
import xxk.wuhai.seacurity.work.vo.GetSchedulingByUserIdVo;
import xxk.wuhai.seacurity.work.bean.StudyListResult;
import xxk.wuhai.seacurity.work.vo.GetStudyNoticeListVo;
import xxk.wuhai.seacurity.work.vo.GetStudyNoticeVo;
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
    @POST("/client-api/aPDetails/apList")
    Observable<Result<AplyResult>> apList(@Body ApListVo apListVo);

    /**
     * 审核详情
     * @param apDetailVo
     * @return
     */
    @POST("/client-api/aPDetails/apDetail")
    Observable<Result<ApDetailResult>> apDetail(@Body ApDetailVo apDetailVo);

    /**
     * 获取学习中心列表
     */
    @POST("/client-api/studyProcess/getStudyNoticeList")
    Observable<Result<StudyListResult>> getStudyNoticeList(@Body GetStudyNoticeListVo studyNoticeListVo);

    /**
     * 学习中心详情
     * @param studyNoticeVo
     * @return
     */
    @POST("/client-api/studyProcess/getStudyNoticeDetails")
    Observable<Result<StudyDetail>> getStudyDetails(@Body GetStudyNoticeVo studyNoticeVo);

    /**
     * 列表
     * @param clueBurstListVo
     * @return
     */
    @POST("/client-api/clueBurstProcess/clueBurstList")
    Observable<Result<ClueBursList>> clueBurstList(@Body ClueBurstListVo clueBurstListVo);

    /**
     * 添加爆料信息
     * @param addClueBurstVo
     * @return
     */
    @POST("/client-api/clueBurstProcess/addClueBurst")
    Observable<Result<String>> addClueBurst(@Body AddClueBurstVo addClueBurstVo);

    /**
     * 添加爆料信息
     * @param  getClueBurstDetailsVo 获取爆料详情
     * @return
     */
    @POST("/client-api/clueBurstProcess/clueBurstDetails")
    Observable<Result<ClueBurstDetailResult>> clueBurstDetails(@Body GetClueBurstDetailsVo getClueBurstDetailsVo);
}
