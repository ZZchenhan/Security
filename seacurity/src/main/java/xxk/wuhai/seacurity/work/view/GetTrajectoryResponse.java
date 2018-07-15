package xxk.wuhai.seacurity.work.view;

import java.util.List;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class GetTrajectoryResponse {

    private List<TrajectoryVoListBean> trajectoryVoList;

    public List<TrajectoryVoListBean> getTrajectoryVoList() {
        return trajectoryVoList;
    }

    public void setTrajectoryVoList(List<TrajectoryVoListBean> trajectoryVoList) {
        this.trajectoryVoList = trajectoryVoList;
    }

    public static class TrajectoryVoListBean {
        /**
         * attendanceLat : string
         * attendanceLon : string
         * uploadTime : string
         * userId : 0
         */

        private String attendanceLat;
        private String attendanceLon;
        private String uploadTime;
        private int userId;

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

        public String getUploadTime() {
            return uploadTime;
        }

        public void setUploadTime(String uploadTime) {
            this.uploadTime = uploadTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
