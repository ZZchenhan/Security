package com.hz.junxinbaoan.work.bean.scheduling;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class GetPersonSchedulingByDateResponse {

    /**
     * schedulingInfoAttVo : {"attTimes":0,"dailySchedulingInfoVoList":[{"attendanceInfoVoList":[{"attendanceLat":"string","attendanceLatExpect":"string","attendanceLocation":"string","attendanceLocationExpect":"string","attendanceLon":"string","attendanceLonExpect":"string","attendanceSetId":0,"attendanceTime":"string","attendanceTimeExpect":"string","attendanceType":"string","id":"string","imageUrls":["string"],"postLimit":0,"preLimit":0,"range":"string","schedulingId":"string","status":"string"}],"beginTime":"string","color":"string","depId":0,"endTime":"string","id":"string","isCrossDay":"string","scheduleId":0,"scheduleName":"string","scheduleShortName":"string","schedulingDate":"string","status":0,"type":"string","wordTime":"string"}],"notAttTimes":0,"overtimeSchedulingInfoVoList":[{"attendanceInfoVoList":[{"attendanceLat":"string","attendanceLatExpect":"string","attendanceLocation":"string","attendanceLocationExpect":"string","attendanceLon":"string","attendanceLonExpect":"string","attendanceSetId":0,"attendanceTime":"string","attendanceTimeExpect":"string","attendanceType":"string","id":"string","imageUrls":["string"],"postLimit":0,"preLimit":0,"range":"string","schedulingId":"string","status":"string"}],"beginTime":"string","color":"string","depId":0,"endTime":"string","id":"string","isCrossDay":"string","scheduleId":0,"scheduleName":"string","scheduleShortName":"string","schedulingDate":"string","status":0,"type":"string","wordTime":"string"}],"signInfoVoList":[{"attendanceLat":"string","attendanceLocation":"string","attendanceLon":"string","id":"string","imageUrls":["string"],"poiName":"string","remark":"string","signTime":"string","userId":0}],"temporarySchedulingInfoVoList":[{"attendanceInfoVoList":[{"attendanceLat":"string","attendanceLatExpect":"string","attendanceLocation":"string","attendanceLocationExpect":"string","attendanceLon":"string","attendanceLonExpect":"string","attendanceSetId":0,"attendanceTime":"string","attendanceTimeExpect":"string","attendanceType":"string","id":"string","imageUrls":["string"],"postLimit":0,"preLimit":0,"range":"string","schedulingId":"string","status":"string"}],"beginTime":"string","color":"string","depId":0,"endTime":"string","id":"string","isCrossDay":"string","scheduleId":0,"scheduleName":"string","scheduleShortName":"string","schedulingDate":"string","status":0,"type":"string","wordTime":"string"}],"workTime":0}
     */

    private SchedulingInfoAttVoBean schedulingInfoAttVo;

    public SchedulingInfoAttVoBean getSchedulingInfoAttVo() {
        return schedulingInfoAttVo;
    }

    public void setSchedulingInfoAttVo(SchedulingInfoAttVoBean schedulingInfoAttVo) {
        this.schedulingInfoAttVo = schedulingInfoAttVo;
    }

}
