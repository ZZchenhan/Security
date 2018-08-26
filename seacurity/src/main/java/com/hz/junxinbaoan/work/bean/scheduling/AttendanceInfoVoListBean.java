package com.hz.junxinbaoan.work.bean.scheduling;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by 86936 on 2018/7/19.
 *
 * @QQ 869360026
 */
public class AttendanceInfoVoListBean implements MultiItemEntity{
    /**
     * attendanceLat : string
     * attendanceLatExpect : string
     * attendanceLocation : string
     * attendanceLocationExpect : string
     * attendanceLon : string
     * attendanceLonExpect : string
     * attendanceSetId : 0
     * attendanceTime : string
     * attendanceTimeExpect : string
     * attendanceType : string
     * id : string
     * imageUrls : ["string"]
     * postLimit : 0
     * preLimit : 0
     * range : string
     * schedulingId : string
     * status : string
     */
    private String attendanceLat;
    private String attendanceLatExpect;
    private String attendanceLocation;
    private String attendanceLocationExpect;
    private String attendanceLon;
    private String attendanceLonExpect;
    private int attendanceSetId;
    private String attendanceTime;
    private String attendanceTimeExpect;
    private String attendanceType;
    private String id;
    private int postLimit;
    private int preLimit;
    private String range;
    private String schedulingId;

    /**
     * 0-日常 1-加班 2-临时
     */
    private String type;
    /**
     * 真实打卡日期
     */
    private boolean isLastDay;

    public boolean isLastDay() {
        return isLastDay;
    }

    public void setLastDay(boolean lastDay) {
        isLastDay = lastDay;
    }

    /**
     * 班次名称
     */
    private String scheduleName;

    /**
     * 班次简称
     */
    private String scheduleShortName;

    /**
     * 打卡状态，0 初始 1.补卡 2.迟到 3.早退 4.正常 5.缺卡 6请假
     */
    private String status;
    private List<String> imageUrls;

    public String getAttendanceLat() {
        return attendanceLat;
    }

    public void setAttendanceLat(String attendanceLat) {
        this.attendanceLat = attendanceLat;
    }

    public String getAttendanceLatExpect() {
        return attendanceLatExpect;
    }

    public void setAttendanceLatExpect(String attendanceLatExpect) {
        this.attendanceLatExpect = attendanceLatExpect;
    }

    public String getAttendanceLocation() {
        return attendanceLocation;
    }

    public void setAttendanceLocation(String attendanceLocation) {
        this.attendanceLocation = attendanceLocation;
    }

    public String getAttendanceLocationExpect() {
        return attendanceLocationExpect;
    }

    public void setAttendanceLocationExpect(String attendanceLocationExpect) {
        this.attendanceLocationExpect = attendanceLocationExpect;
    }

    public String getAttendanceLon() {
        return attendanceLon;
    }

    public void setAttendanceLon(String attendanceLon) {
        this.attendanceLon = attendanceLon;
    }

    public String getAttendanceLonExpect() {
        return attendanceLonExpect;
    }

    public void setAttendanceLonExpect(String attendanceLonExpect) {
        this.attendanceLonExpect = attendanceLonExpect;
    }

    public int getAttendanceSetId() {
        return attendanceSetId;
    }

    public void setAttendanceSetId(int attendanceSetId) {
        this.attendanceSetId = attendanceSetId;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getAttendanceTimeExpect() {
        return attendanceTimeExpect;
    }

    public void setAttendanceTimeExpect(String attendanceTimeExpect) {
        this.attendanceTimeExpect = attendanceTimeExpect;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPostLimit() {
        return postLimit;
    }

    public void setPostLimit(int postLimit) {
        this.postLimit = postLimit;
    }

    public int getPreLimit() {
        return preLimit;
    }

    public void setPreLimit(int preLimit) {
        this.preLimit = preLimit;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(String schedulingId) {
        this.schedulingId = schedulingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public int getItemType() {
        return (this.getAttendanceLatExpect() == null
                || this.getAttendanceLatExpect().equals(""))
                || (this.getAttendanceLonExpect() == null
                        || this.getAttendanceLonExpect().equals("") ||
                this.getAttendanceLocationExpect().equals("任意位置"))?0:1;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
