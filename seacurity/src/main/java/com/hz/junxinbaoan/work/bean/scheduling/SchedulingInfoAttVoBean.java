package com.hz.junxinbaoan.work.bean.scheduling;

import java.util.List;

/**
 * Created by 86936 on 2018/7/19.
 *
 * @QQ 869360026
 * 打卡实体类
 */
public class SchedulingInfoAttVoBean {
    /**
     * attTimes : 0
     * dailySchedulingInfoVoList : [{"attendanceInfoVoList":[{"attendanceLat":"string","attendanceLatExpect":"string","attendanceLocation":"string","attendanceLocationExpect":"string","attendanceLon":"string","attendanceLonExpect":"string","attendanceSetId":0,"attendanceTime":"string","attendanceTimeExpect":"string","attendanceType":"string","id":"string","imageUrls":["string"],"postLimit":0,"preLimit":0,"range":"string","schedulingId":"string","status":"string"}],"beginTime":"string","color":"string","depId":0,"endTime":"string","id":"string","isCrossDay":"string","scheduleId":0,"scheduleName":"string","scheduleShortName":"string","schedulingDate":"string","status":0,"type":"string","wordTime":"string"}]
     * notAttTimes : 0
     * overtimeSchedulingInfoVoList : [{"attendanceInfoVoList":[{"attendanceLat":"string","attendanceLatExpect":"string","attendanceLocation":"string","attendanceLocationExpect":"string","attendanceLon":"string","attendanceLonExpect":"string","attendanceSetId":0,"attendanceTime":"string","attendanceTimeExpect":"string","attendanceType":"string","id":"string","imageUrls":["string"],"postLimit":0,"preLimit":0,"range":"string","schedulingId":"string","status":"string"}],"beginTime":"string","color":"string","depId":0,"endTime":"string","id":"string","isCrossDay":"string","scheduleId":0,"scheduleName":"string","scheduleShortName":"string","schedulingDate":"string","status":0,"type":"string","wordTime":"string"}]
     * signInfoVoList : [{"attendanceLat":"string","attendanceLocation":"string","attendanceLon":"string","id":"string","imageUrls":["string"],"poiName":"string","remark":"string","signTime":"string","userId":0}]
     * temporarySchedulingInfoVoList : [{"attendanceInfoVoList":[{"attendanceLat":"string","attendanceLatExpect":"string","attendanceLocation":"string","attendanceLocationExpect":"string","attendanceLon":"string","attendanceLonExpect":"string","attendanceSetId":0,"attendanceTime":"string","attendanceTimeExpect":"string","attendanceType":"string","id":"string","imageUrls":["string"],"postLimit":0,"preLimit":0,"range":"string","schedulingId":"string","status":"string"}],"beginTime":"string","color":"string","depId":0,"endTime":"string","id":"string","isCrossDay":"string","scheduleId":0,"scheduleName":"string","scheduleShortName":"string","schedulingDate":"string","status":0,"type":"string","wordTime":"string"}]
     * workTime : 0
     */

    private double attTimes;
    private double notAttTimes;
    private double workTime;
    //日常班班次
    private List<SchedulingWithAttVo> dailySchedulingInfoVoList;
    //加班班次
    private List<SchedulingWithAttVo> overtimeSchedulingInfoVoList;
    //随手签列表
    private List<SignInfoVoListBean> signInfoVoList;
    // 临时排班列表
    private List<SchedulingWithAttVo> temporarySchedulingInfoVoList;

    public double getAttTimes() {
        return attTimes;
    }

    public void setAttTimes(int attTimes) {
        this.attTimes = attTimes;
    }

    public double getNotAttTimes() {
        return notAttTimes;
    }

    public void setNotAttTimes(double notAttTimes) {
        this.notAttTimes = notAttTimes;
    }

    public double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public List<SchedulingWithAttVo> getDailySchedulingInfoVoList() {
        return dailySchedulingInfoVoList;
    }

    public void setDailySchedulingInfoVoList(List<SchedulingWithAttVo> dailySchedulingInfoVoList) {
        this.dailySchedulingInfoVoList = dailySchedulingInfoVoList;
    }

    public List<SchedulingWithAttVo> getOvertimeSchedulingInfoVoList() {
        return overtimeSchedulingInfoVoList;
    }

    public void setOvertimeSchedulingInfoVoList(List<SchedulingWithAttVo> overtimeSchedulingInfoVoList) {
        this.overtimeSchedulingInfoVoList = overtimeSchedulingInfoVoList;
    }

    public List<SignInfoVoListBean> getSignInfoVoList() {
        return signInfoVoList;
    }

    public void setSignInfoVoList(List<SignInfoVoListBean> signInfoVoList) {
        this.signInfoVoList = signInfoVoList;
    }

    public List<SchedulingWithAttVo> getTemporarySchedulingInfoVoList() {
        return temporarySchedulingInfoVoList;
    }

    public void setTemporarySchedulingInfoVoList(List<SchedulingWithAttVo> temporarySchedulingInfoVoList) {
        this.temporarySchedulingInfoVoList = temporarySchedulingInfoVoList;
    }
}
