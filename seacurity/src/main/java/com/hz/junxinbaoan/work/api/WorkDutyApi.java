package com.hz.junxinbaoan.work.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.login.vo.GetUserInfoVo;
import com.hz.junxinbaoan.work.bean.AddSuggetResult;
import com.hz.junxinbaoan.work.bean.ApDetailResult;
import com.hz.junxinbaoan.work.bean.AplyResult;
import com.hz.junxinbaoan.work.bean.AplyUserResult;
import com.hz.junxinbaoan.work.bean.ApproverUser;
import com.hz.junxinbaoan.work.bean.ClueBursList;
import com.hz.junxinbaoan.work.bean.ClueBurstDetailResult;
import com.hz.junxinbaoan.work.bean.SignResult;
import com.hz.junxinbaoan.work.bean.scheduling.GetPersonSchedulingByDateResponse;
import com.hz.junxinbaoan.work.bean.PersonSchedulingResult;
import com.hz.junxinbaoan.work.bean.RecordBean;
import com.hz.junxinbaoan.work.bean.ScheduingResult;
import com.hz.junxinbaoan.work.bean.StudyDetail;
import com.hz.junxinbaoan.work.bean.UserSignListResult;
import com.hz.junxinbaoan.work.bean.UserSignResult;
import com.hz.junxinbaoan.work.view.GetTrajectoryResponse;
import com.hz.junxinbaoan.work.vo.AddClueBurstVo;
import com.hz.junxinbaoan.work.vo.AddSusgetVO;
import com.hz.junxinbaoan.work.vo.ApDetailVo;
import com.hz.junxinbaoan.work.vo.ApListVo;
import com.hz.junxinbaoan.work.vo.ApiGetUserSignByDayVO;
import com.hz.junxinbaoan.work.vo.ApplyLeaveVo;
import com.hz.junxinbaoan.work.vo.ClueBurstListVo;
import com.hz.junxinbaoan.work.vo.GetApproverVo;
import com.hz.junxinbaoan.work.vo.GetClueBurstDetailsVo;
import com.hz.junxinbaoan.work.vo.GetPersonScheduingVo;
import com.hz.junxinbaoan.work.vo.GetSchedulingByTimeVo;
import com.hz.junxinbaoan.work.vo.GetSchedulingByUserIdVo;
import com.hz.junxinbaoan.work.bean.StudyListResult;
import com.hz.junxinbaoan.work.vo.GetSchedulingVo;
import com.hz.junxinbaoan.work.vo.GetStudyNoticeListVo;
import com.hz.junxinbaoan.work.vo.GetStudyNoticeVo;
import com.hz.junxinbaoan.work.vo.GetTrajectoryVo;
import com.hz.junxinbaoan.work.vo.GetUserSignVo;
import com.hz.junxinbaoan.work.vo.RecordVo;
import com.hz.junxinbaoan.work.vo.SupplementApplyVo;
import com.hz.junxinbaoan.work.vo.UploadTrajectoryVo;
import com.hz.junxinbaoan.work.vo.UserSignVo;

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
    Observable<Result<ApproverUser>> getApprover(@Body GetApproverVo getApproverVo);

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


    /**
     * 用户签到页面
     * @param userSignVo
     * @return
     */
    @POST("/client-api/attendance/userSign")
    Observable<Result<UserSignResult>> userSign(@Body UserSignVo userSignVo);

    /**
     * 获取用户随手签到记录
     * @param getUserSignVo
     * @return
     */
    @POST("/client-api/attendance/getUserSignList")
    Observable<Result<UserSignListResult>> userSignList(@Body GetUserSignVo getUserSignVo);

    @POST("/client-api/attendance/getUserSignByDay")
    Observable<Result<SignResult>> getTodaySingList(@Body  ApiGetUserSignByDayVO getUserSignByDayVO);

    /**
     * 1分钟上传一次轨迹
     * @return
     */
    @POST("/client-api/attendance/uploadTrajectory")
    Observable<Result<String>> uploadTrajectory(@Body UploadTrajectoryVo uploadTrajectoryVo);


    /**
     *按时间段查询排班
     */
    @POST("/client-api/commonScheduling/getPersonSchedulingByEmpId")
    Observable<ScheduingResult> getPersonSchedulingByEmpId(@Body GetPersonScheduingVo personScheduingVo);

    /**
     * 查询个人当日排班
     * @param getSchedulingVo
     * @return
     */
    @POST("/client-api//commonScheduling/getUserScheduling")
    Observable<Result<GetPersonSchedulingByDateResponse>> getOwnScheduling(@Body GetSchedulingVo getSchedulingVo);

    /**
     * 打卡
     * @param recordVo
     * @return
     */
    @POST("/client-api/attendance/userAttendance")
    Observable<Result<RecordBean>> record(@Body RecordVo recordVo);

    /**
     *
     * @param getTrajectoryVo
     * @return
     */
    @POST("/client-api/commonScheduling/getTrajectory")
    Observable<Result<GetTrajectoryResponse>> getTrajectory(@Body GetTrajectoryVo getTrajectoryVo);

    /**
     * 获得时间段靠请
     * @param getSchedulingByTimeVo
     * @return
     */
    @POST("/client-api/commonScheduling/getTimesScheduling")
    Observable<Result<PersonSchedulingResult>> getTimesScheduling(@Body GetSchedulingByTimeVo getSchedulingByTimeVo);

    /**
     * 添加评论
     * @param addSusgetVO
     * @return
     */
    @POST("/client-api/suggestion/addSuggestion ")
    Observable<AddSuggetResult>  addSuggestion(@Body AddSusgetVO addSusgetVO);

    /***
     * 登出
     * @return
     */
    @POST("/client-api/user/logout")
    Observable<Result<RecordBean>> logout(@Body GetUserInfoVo getUserInfoVo);


    @POST("/client-api/staff/getQRCodeImg")
    Observable<Result<String>> getQRCode();

}
