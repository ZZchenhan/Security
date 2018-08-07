package com.hz.junxinbaoan.work.vo;

import java.util.List;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/11 16:46
 */
public class UserSignVo {

    /**
     * attendanceLat : string
     * attendanceLocation : string
     * attendanceLon : string
     * imageUrls : ["string"]
     * poiName : string
     * remark : string
     */

    private String attendanceLat;
    private String attendanceLocation;
    private String attendanceLon;
    private String poiName;
    private String remark;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
