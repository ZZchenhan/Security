package xxk.wuhai.seacurity.work.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by 86936 on 2018/7/11.
 *
 * @QQ 869360026
 */

public class ScheduingResult {


    /**
     * code : 200
     * message : success
     * errorType : null
     * result : {"personSchedulingList":[{"userId":26,"msisdn":"15557196991","name":"老王","idCard":null,"schedulingList":{"2018-07-05":null,"2018-07-27":null,"2018-07-04":null,"2018-07-26":null,"2018-07-03":null,"2018-07-25":null,"2018-07-02":null,"2018-07-24":null,"2018-07-09":null,"2018-07-08":null,"2018-07-07":null,"2018-07-29":null,"2018-07-06":null,"2018-07-28":null,"2018-07-30":null,"2018-07-12":[{"id":"5b4612eff899095abf2198a0","scheduleId":42,"depId":19,"scheduleName":"正常白班","scheduleShortName":"正白","color":"1","schedulingDate":"2018-07-12","type":"0","isCrossDay":"0","wordTime":"0","lateTime":5,"earlyRetreatTime":5,"beginTime":"2018-07-12 08:00:00","endTime":"2018-07-12 17:00:00","status":0}],"2018-07-11":null,"2018-07-10":null,"2018-07-31":null,"2018-07-16":null,"2018-07-15":null,"2018-07-14":null,"2018-07-13":null,"2018-07-19":null,"2018-07-18":null,"2018-07-17":null,"2018-07-01":null,"2018-07-23":null,"2018-07-22":null,"2018-07-21":null,"2018-07-20":null}}],"count":0,"pageCount":0}
     */

    private String code;
    private String message;
    private Object errorType;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrorType() {
        return errorType;
    }

    public void setErrorType(Object errorType) {
        this.errorType = errorType;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * personSchedulingList : [{"userId":26,"msisdn":"15557196991","name":"老王","idCard":null,"schedulingList":{"2018-07-05":null,"2018-07-27":null,"2018-07-04":null,"2018-07-26":null,"2018-07-03":null,"2018-07-25":null,"2018-07-02":null,"2018-07-24":null,"2018-07-09":null,"2018-07-08":null,"2018-07-07":null,"2018-07-29":null,"2018-07-06":null,"2018-07-28":null,"2018-07-30":null,"2018-07-12":[{"id":"5b4612eff899095abf2198a0","scheduleId":42,"depId":19,"scheduleName":"正常白班","scheduleShortName":"正白","color":"1","schedulingDate":"2018-07-12","type":"0","isCrossDay":"0","wordTime":"0","lateTime":5,"earlyRetreatTime":5,"beginTime":"2018-07-12 08:00:00","endTime":"2018-07-12 17:00:00","status":0}],"2018-07-11":null,"2018-07-10":null,"2018-07-31":null,"2018-07-16":null,"2018-07-15":null,"2018-07-14":null,"2018-07-13":null,"2018-07-19":null,"2018-07-18":null,"2018-07-17":null,"2018-07-01":null,"2018-07-23":null,"2018-07-22":null,"2018-07-21":null,"2018-07-20":null}}]
         * count : 0
         * pageCount : 0
         */

        private int count;
        private int pageCount;
        private List<PersonSchedulingListBean> personSchedulingList;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<PersonSchedulingListBean> getPersonSchedulingList() {
            return personSchedulingList;
        }

        public void setPersonSchedulingList(List<PersonSchedulingListBean> personSchedulingList) {
            this.personSchedulingList = personSchedulingList;
        }

        public static class PersonSchedulingListBean {
            /**
             * userId : 26
             * msisdn : 15557196991
             * name : 老王
             * idCard : null
             * schedulingList : {"2018-07-05":null,"2018-07-27":null,"2018-07-04":null,"2018-07-26":null,"2018-07-03":null,"2018-07-25":null,"2018-07-02":null,"2018-07-24":null,"2018-07-09":null,"2018-07-08":null,"2018-07-07":null,"2018-07-29":null,"2018-07-06":null,"2018-07-28":null,"2018-07-30":null,"2018-07-12":[{"id":"5b4612eff899095abf2198a0","scheduleId":42,"depId":19,"scheduleName":"正常白班","scheduleShortName":"正白","color":"1","schedulingDate":"2018-07-12","type":"0","isCrossDay":"0","wordTime":"0","lateTime":5,"earlyRetreatTime":5,"beginTime":"2018-07-12 08:00:00","endTime":"2018-07-12 17:00:00","status":0}],"2018-07-11":null,"2018-07-10":null,"2018-07-31":null,"2018-07-16":null,"2018-07-15":null,"2018-07-14":null,"2018-07-13":null,"2018-07-19":null,"2018-07-18":null,"2018-07-17":null,"2018-07-01":null,"2018-07-23":null,"2018-07-22":null,"2018-07-21":null,"2018-07-20":null}
             */

            private int userId;
            private String msisdn;
            private String name;
            private Object idCard;
            private Map<String,List<ScheduingDetails>> schedulingList;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getMsisdn() {
                return msisdn;
            }

            public void setMsisdn(String msisdn) {
                this.msisdn = msisdn;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getIdCard() {
                return idCard;
            }

            public void setIdCard(Object idCard) {
                this.idCard = idCard;
            }

            public Map<String,List<ScheduingDetails>>  getSchedulingList() {
                return schedulingList;
            }

            public void setSchedulingList(Map<String,List<ScheduingDetails>> schedulingList) {
                this.schedulingList = schedulingList;
            }

            public static class ScheduingDetails {
                /**
                 * id : 5b4612eff899095abf2198a0
                 * scheduleId : 42
                 * depId : 19
                 * scheduleName : 正常白班
                 * scheduleShortName : 正白
                 * color : 1
                 * schedulingDate : 2018-07-12
                 * type : 0
                 * isCrossDay : 0
                 * wordTime : 0
                 * lateTime : 5
                 * earlyRetreatTime : 5
                 * beginTime : 2018-07-12 08:00:00
                 * endTime : 2018-07-12 17:00:00
                 * status : 0
                 */

                private String id;
                private int scheduleId;
                private int depId;
                private String scheduleName;
                private String scheduleShortName;
                private String color;
                private String schedulingDate;
                private String type;
                private String isCrossDay;
                private String wordTime;
                private int lateTime;
                private int earlyRetreatTime;
                private String beginTime;
                private String endTime;
                private int status;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getScheduleId() {
                    return scheduleId;
                }

                public void setScheduleId(int scheduleId) {
                    this.scheduleId = scheduleId;
                }

                public int getDepId() {
                    return depId;
                }

                public void setDepId(int depId) {
                    this.depId = depId;
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

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public String getSchedulingDate() {
                    return schedulingDate;
                }

                public void setSchedulingDate(String schedulingDate) {
                    this.schedulingDate = schedulingDate;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getIsCrossDay() {
                    return isCrossDay;
                }

                public void setIsCrossDay(String isCrossDay) {
                    this.isCrossDay = isCrossDay;
                }

                public String getWordTime() {
                    return wordTime;
                }

                public void setWordTime(String wordTime) {
                    this.wordTime = wordTime;
                }

                public int getLateTime() {
                    return lateTime;
                }

                public void setLateTime(int lateTime) {
                    this.lateTime = lateTime;
                }

                public int getEarlyRetreatTime() {
                    return earlyRetreatTime;
                }

                public void setEarlyRetreatTime(int earlyRetreatTime) {
                    this.earlyRetreatTime = earlyRetreatTime;
                }

                public String getBeginTime() {
                    return beginTime;
                }

                public void setBeginTime(String beginTime) {
                    this.beginTime = beginTime;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}
