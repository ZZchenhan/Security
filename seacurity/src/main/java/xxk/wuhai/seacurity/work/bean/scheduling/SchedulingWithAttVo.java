package xxk.wuhai.seacurity.work.bean.scheduling;

import java.util.List;

/**
 * Created by 86936 on 2018/7/19.
 *
 * @QQ 869360026
 */
public class SchedulingWithAttVo {
    /**
     * attendanceInfoVoList : [{"attendanceLat":"string","attendanceLatExpect":"string","attendanceLocation":"string","attendanceLocationExpect":"string","attendanceLon":"string","attendanceLonExpect":"string","attendanceSetId":0,"attendanceTime":"string","attendanceTimeExpect":"string","attendanceType":"string","id":"string","imageUrls":["string"],"postLimit":0,"preLimit":0,"range":"string","schedulingId":"string","status":"string"}]
     * beginTime : string
     * color : string
     * depId : 0
     * endTime : string
     * id : string
     * isCrossDay : string
     * scheduleId : 0
     * scheduleName : string
     * scheduleShortName : string
     * schedulingDate : string
     * status : 0
     * type : string
     * wordTime : string
     */
    //打卡详情
    private List<AttendanceInfoVoListBean> attendanceInfoVoList;
    private String beginTime;
    private String color;
    private int depId;
    private String endTime;
    private String id;
    private String isCrossDay;
    private int scheduleId;
    private String scheduleName;
    private String scheduleShortName;
    private String schedulingDate;
    private int status;
    private String type;
    private String wordTime;


    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsCrossDay() {
        return isCrossDay;
    }

    public void setIsCrossDay(String isCrossDay) {
        this.isCrossDay = isCrossDay;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getScheduleShortName() {
        return scheduleShortName;
    }

    public void setScheduleShortName(String scheduleShortName) {
        this.scheduleShortName = scheduleShortName;
    }

    public String getSchedulingDate() {
        return schedulingDate;
    }

    public void setSchedulingDate(String schedulingDate) {
        this.schedulingDate = schedulingDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWordTime() {
        return wordTime;
    }

    public void setWordTime(String wordTime) {
        this.wordTime = wordTime;
    }

    public List<AttendanceInfoVoListBean> getAttendanceInfoVoList() {
        return attendanceInfoVoList;
    }

    public void setAttendanceInfoVoList(List<AttendanceInfoVoListBean> attendanceInfoVoList) {
        this.attendanceInfoVoList = attendanceInfoVoList;
    }

}
