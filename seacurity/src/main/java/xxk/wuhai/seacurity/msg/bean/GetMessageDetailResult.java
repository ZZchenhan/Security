package xxk.wuhai.seacurity.msg.bean;

import java.util.List;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class GetMessageDetailResult {
    private MessageDetail messageDetail;
    private PersonnelDetails personnelDetails;
    private CardPosition cardPosition;

    public CardPosition getCardPosition() {
        return cardPosition;
    }

    public void setCardPosition(CardPosition cardPosition) {
        this.cardPosition = cardPosition;
    }

    public MessageDetail getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(MessageDetail messageDetail) {
        this.messageDetail = messageDetail;
    }

    public PersonnelDetails getPersonnelDetails() {
        return personnelDetails;
    }

    public void setPersonnelDetails(PersonnelDetails personnelDetails) {
        this.personnelDetails = personnelDetails;
    }

    public static class CardPosition{

        /**
         * beginAttendanceTime : string
         * cardPositionInfoVoList : [{"attendanceLocation":"string","attendanceType":"string"}]
         * endAttendanceTime : string
         * name : string
         * scheduleName : string
         * scheduleTime : string
         * scheduleType : string
         * workTime : string
         */

        private String beginAttendanceTime;
        private String endAttendanceTime;
        private String name;
        private String scheduleName;
        private String scheduleTime;
        private String scheduleType;
        private String workTime;
        private List<CardPositionInfoVoListBean> cardPositionInfoVoList;

        public String getBeginAttendanceTime() {
            return beginAttendanceTime;
        }

        public void setBeginAttendanceTime(String beginAttendanceTime) {
            this.beginAttendanceTime = beginAttendanceTime;
        }

        public String getEndAttendanceTime() {
            return endAttendanceTime;
        }

        public void setEndAttendanceTime(String endAttendanceTime) {
            this.endAttendanceTime = endAttendanceTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getScheduleName() {
            return scheduleName;
        }

        public void setScheduleName(String scheduleName) {
            this.scheduleName = scheduleName;
        }

        public String getScheduleTime() {
            return scheduleTime;
        }

        public void setScheduleTime(String scheduleTime) {
            this.scheduleTime = scheduleTime;
        }

        public String getScheduleType() {
            return scheduleType;
        }

        public void setScheduleType(String scheduleType) {
            this.scheduleType = scheduleType;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        public List<CardPositionInfoVoListBean> getCardPositionInfoVoList() {
            return cardPositionInfoVoList;
        }

        public void setCardPositionInfoVoList(List<CardPositionInfoVoListBean> cardPositionInfoVoList) {
            this.cardPositionInfoVoList = cardPositionInfoVoList;
        }

        public static class CardPositionInfoVoListBean {
            /**
             * attendanceLocation : string
             * attendanceType : string
             */

            private String attendanceLocation;
            private String attendanceType;

            public String getAttendanceLocation() {
                return attendanceLocation;
            }

            public void setAttendanceLocation(String attendanceLocation) {
                this.attendanceLocation = attendanceLocation;
            }

            public String getAttendanceType() {
                return attendanceType;
            }

            public void setAttendanceType(String attendanceType) {
                this.attendanceType = attendanceType;
            }
        }
    }
}
