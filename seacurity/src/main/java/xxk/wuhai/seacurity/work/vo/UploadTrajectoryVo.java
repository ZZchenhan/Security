package xxk.wuhai.seacurity.work.vo;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/11 18:10
 */
public class UploadTrajectoryVo {

    /**
     * attendanceLat : string
     * attendanceLon : string
     */

    private String attendanceLat;
    private String attendanceLon;

    public UploadTrajectoryVo(String attendanceLat, String attendanceLon) {
        this.attendanceLat = attendanceLat;
        this.attendanceLon = attendanceLon;
    }

    public String getAttendanceLat() {
        return attendanceLat;
    }

    public void setAttendanceLat(String attendanceLat) {
        this.attendanceLat = attendanceLat;
    }

    public String getAttendanceLon() {
        return attendanceLon;
    }

    public void setAttendanceLon(String attendanceLon) {
        this.attendanceLon = attendanceLon;
    }
}
