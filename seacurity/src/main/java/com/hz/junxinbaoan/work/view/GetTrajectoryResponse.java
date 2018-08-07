package com.hz.junxinbaoan.work.view;

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
         * 现在变成{
         lat = "31.303366";
         lon = "121.439633";
         time = "2018-08-07 21:33:15";
         },
         * userId : 0
         */

        private String lat ;
        private String lon;
        private String time;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
