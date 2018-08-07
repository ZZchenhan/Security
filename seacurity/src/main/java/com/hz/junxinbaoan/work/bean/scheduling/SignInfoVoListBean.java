package com.hz.junxinbaoan.work.bean.scheduling;

import java.util.List;

/**
 * Created by 86936 on 2018/7/19.
 *
 * @QQ 869360026
 */
public class SignInfoVoListBean {
    /**
     * attendanceLat : string
     * attendanceLocation : string
     * attendanceLon : string
     * id : string
     * imageUrls : ["string"]
     * poiName : string
     * remark : string
     * signTime : string
     * userId : 0
     */

    private String attendanceLat;
    private String attendanceLocation;
    private String attendanceLon;
    private String id;
    private String poiName;
    private String remark;
    private String signTime;
    private long userId;
    private List<String> imageUrls;

    public String getAttendanceLat() {
        return attendanceLat;
    }

    public void setAttendanceLat(String attendanceLat) {
        this.attendanceLat = attendanceLat;
    }

    public String getAttendanceLocation() {
        return attendanceLocation;
    }

    public void setAttendanceLocation(String attendanceLocation) {
        this.attendanceLocation = attendanceLocation;
    }

    public String getAttendanceLon() {
        return attendanceLon;
    }

    public void setAttendanceLon(String attendanceLon) {
        this.attendanceLon = attendanceLon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
