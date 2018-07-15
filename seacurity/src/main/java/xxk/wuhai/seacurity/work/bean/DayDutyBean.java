package xxk.wuhai.seacurity.work.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class DayDutyBean {

    /**
     * attendanceCount : 0
     * attendanceExpectCount : 0
     * schedulingInfoList : [{"attendanceInfoVoList":[{"attendanceBeginTime":"string","attendanceEndTime":"string","attendanceLat":"string","attendanceLatExpect":"string","attendanceLocation":"string","attendanceLocationExpect":"string","attendanceLon":"string","attendanceLonExpect":"string","attendanceSetId":0,"attendanceTime":"string","attendanceTimeExpect":"string","attendanceType":"string","id":"string","imageUrls":["string"],"range":"string","scheduleId":0,"scheduleName":"string","schedulingId":"string","status":"string"}],"beginTime":"string","color":"string","depId":0,"earlyRetreatTime":0,"endTime":"string","id":"string","isCrossDay":"string","lateTime":0,"scheduleId":0,"scheduleName":"string","scheduleShortName":"string","schedulingDate":"string","status":0,"type":"string","wordTime":"string"}]
     */

    private int attendanceCount;
    private int attendanceExpectCount;
    private List<SchedulingInfoListBean> schedulingInfoList;

    public int getAttendanceCount() {
        return attendanceCount;
    }

    public void setAttendanceCount(int attendanceCount) {
        this.attendanceCount = attendanceCount;
    }

    public int getAttendanceExpectCount() {
        return attendanceExpectCount;
    }

    public void setAttendanceExpectCount(int attendanceExpectCount) {
        this.attendanceExpectCount = attendanceExpectCount;
    }

    public List<SchedulingInfoListBean> getSchedulingInfoList() {
        return schedulingInfoList;
    }

    public void setSchedulingInfoList(List<SchedulingInfoListBean> schedulingInfoList) {
        this.schedulingInfoList = schedulingInfoList;
    }

    public static class SchedulingInfoListBean implements MultiItemEntity {
        /**
         * attendanceInfoVoList : [{"attendanceBeginTime":"string","attendanceEndTime":"string","attendanceLat":"string","attendanceLatExpect":"string","attendanceLocation":"string","attendanceLocationExpect":"string","attendanceLon":"string","attendanceLonExpect":"string","attendanceSetId":0,"attendanceTime":"string","attendanceTimeExpect":"string","attendanceType":"string","id":"string","imageUrls":["string"],"range":"string","scheduleId":0,"scheduleName":"string","schedulingId":"string","status":"string"}]
         * beginTime : string
         * color : string
         * depId : 0
         * earlyRetreatTime : 0
         * endTime : string
         * id : string
         * isCrossDay : string
         * lateTime : 0
         * scheduleId : 0
         * scheduleName : string
         * scheduleShortName : string
         * schedulingDate : string
         * status : 0
         * type : string
         * wordTime : string
         */

        private String beginTime;
        private String color;
        private int depId;
        private int earlyRetreatTime;
        private String endTime;
        private String id;
        private String isCrossDay;
        private int lateTime;
        private int scheduleId;
        private String scheduleName;
        private String scheduleShortName;
        private String schedulingDate;
        private int status;
        private String type;
        private String wordTime;
        private List<AttendanceInfoVoListBean> attendanceInfoVoList;

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

        public int getEarlyRetreatTime() {
            return earlyRetreatTime;
        }

        public void setEarlyRetreatTime(int earlyRetreatTime) {
            this.earlyRetreatTime = earlyRetreatTime;
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

        public int getLateTime() {
            return lateTime;
        }

        public void setLateTime(int lateTime) {
            this.lateTime = lateTime;
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

        @Override
        public int getItemType() {
            return attendanceInfoVoList == null || attendanceInfoVoList.size() == 0?0:1;
        }

        public static class AttendanceInfoVoListBean {
            /**
             * attendanceBeginTime : string
             * attendanceEndTime : string
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
             * range : string
             * scheduleId : 0
             * scheduleName : string
             * schedulingId : string
             * status : string
             */

            private String attendanceBeginTime;
            private String attendanceEndTime;
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
            private String range;
            private int scheduleId;
            private String scheduleName;
            private String schedulingId;
            private String status;
            private List<String> imageUrls;

            public String getAttendanceBeginTime() {
                return attendanceBeginTime;
            }

            public void setAttendanceBeginTime(String attendanceBeginTime) {
                this.attendanceBeginTime = attendanceBeginTime;
            }

            public String getAttendanceEndTime() {
                return attendanceEndTime;
            }

            public void setAttendanceEndTime(String attendanceEndTime) {
                this.attendanceEndTime = attendanceEndTime;
            }

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

            public String getRange() {
                return range;
            }

            public void setRange(String range) {
                this.range = range;
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
        }
    }
}
