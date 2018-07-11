package xxk.wuhai.seacurity.work.bean;

import java.util.List;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/11 17:21
 */
public class UserSignListResult {

    /**
     * count : 0
     * userSignInfoVos : [{"attendanceLat":"string","attendanceLocation":"string","attendanceLon":"string","id":"string","imageUrls":["string"],"poiName":"string","remark":"string","signTime":"string","userId":0}]
     */

    private int count;
    private List<UserSignInfoVosBean> userSignInfoVos;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserSignInfoVosBean> getUserSignInfoVos() {
        return userSignInfoVos;
    }

    public void setUserSignInfoVos(List<UserSignInfoVosBean> userSignInfoVos) {
        this.userSignInfoVos = userSignInfoVos;
    }

    public static class UserSignInfoVosBean {
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
        private int userId;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<String> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }
    }
}
