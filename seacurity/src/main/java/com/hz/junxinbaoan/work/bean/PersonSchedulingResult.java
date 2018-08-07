package com.hz.junxinbaoan.work.bean;

import java.util.Map;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class PersonSchedulingResult {
    private Map<String,Recode> personSchedulingMap;

    public Map<String, Recode> getPersonSchedulingMap() {
        return personSchedulingMap;
    }

    public void setPersonSchedulingMap(Map<String, Recode> personSchedulingMap) {
        this.personSchedulingMap = personSchedulingMap;
    }

    public static class Recode{
//        "scheduleName": "测试白班",
//                "scheduleShortName": "白",
//                "color": "3",
//                "overTimeScheduleName": null,
//                "overTimeScheduleShortName": null,
//                "temporaryScheduleName": "临时",
//                "temporaryScheduleShortName": "临",
//                "isCrossDay": null,
//                "status": 0,
//                "hasDaily": "1",
//                "hasOvertime": "0",
//                "hasTemporary": "1"

        private String scheduleName;
        private String scheduleShortName;
        private String color;
        private String overTimeScheduleName;
        private String overTimeScheduleShortName;
        private String temporaryScheduleName;
        private String temporaryScheduleShortName;
        private int status;
        private String hasDaily;  //是否日常
        private String hasOvertime;//是否加班
        private String hasTemporary;//是否包含临时班次

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

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getOverTimeScheduleName() {
            return overTimeScheduleName;
        }

        public void setOverTimeScheduleName(String overTimeScheduleName) {
            this.overTimeScheduleName = overTimeScheduleName;
        }

        public String getOverTimeScheduleShortName() {
            return overTimeScheduleShortName;
        }

        public void setOverTimeScheduleShortName(String overTimeScheduleShortName) {
            this.overTimeScheduleShortName = overTimeScheduleShortName;
        }

        public String getTemporaryScheduleName() {
            return temporaryScheduleName;
        }

        public void setTemporaryScheduleName(String temporaryScheduleName) {
            this.temporaryScheduleName = temporaryScheduleName;
        }

        public String getTemporaryScheduleShortName() {
            return temporaryScheduleShortName;
        }

        public void setTemporaryScheduleShortName(String temporaryScheduleShortName) {
            this.temporaryScheduleShortName = temporaryScheduleShortName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHasDaily() {
            return hasDaily;
        }

        public void setHasDaily(String hasDaily) {
            this.hasDaily = hasDaily;
        }

        public String getHasOvertime() {
            return hasOvertime;
        }

        public void setHasOvertime(String hasOvertime) {
            this.hasOvertime = hasOvertime;
        }

        public String getHasTemporary() {
            return hasTemporary;
        }

        public void setHasTemporary(String hasTemporary) {
            this.hasTemporary = hasTemporary;
        }
    }
}
