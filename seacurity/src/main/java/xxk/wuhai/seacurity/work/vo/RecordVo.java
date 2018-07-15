package xxk.wuhai.seacurity.work.vo;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class RecordVo {

    /**
     * attendanceId : string
     * attendanceLat : string
     * attendanceLocation : string
     * attendanceLon : string
     * schedulingId : string
     */

    private String attendanceId;
    private String attendanceLat;
    private String attendanceLocation;
    private String attendanceLon;
    private String schedulingId;

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

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

    public String getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(String schedulingId) {
        this.schedulingId = schedulingId;
    }
}
