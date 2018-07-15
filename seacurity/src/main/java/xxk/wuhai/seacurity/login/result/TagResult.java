package xxk.wuhai.seacurity.login.result;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class TagResult {

    /**
     * code : 200
     * message : success
     * errorType : null
     * result : {"praised":0,"trampled":0,"labelVoList":[{"labelId":1,"labelName":"劳动模范","labelNum":1},{"labelId":2,"labelName":"真诚待人","labelNum":1},{"labelId":3,"labelName":"诚实守信","labelNum":1},{"labelId":4,"labelName":"频繁旷工","labelNum":1},{"labelId":5,"labelName":"见义勇为","labelNum":1},{"labelId":6,"labelName":"迟到早退","labelNum":0},{"labelId":7,"labelName":"助人为乐","labelNum":1},{"labelId":8,"labelName":"常忘打卡","labelNum":0},{"labelId":9,"labelName":"平易近人","labelNum":1},{"labelId":10,"labelName":"严于律己","labelNum":0},{"labelId":11,"labelName":"知错必改","labelNum":0}]}
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
         * praised : 0
         * trampled : 0
         * labelVoList : [{"labelId":1,"labelName":"劳动模范","labelNum":1},{"labelId":2,"labelName":"真诚待人","labelNum":1},{"labelId":3,"labelName":"诚实守信","labelNum":1},{"labelId":4,"labelName":"频繁旷工","labelNum":1},{"labelId":5,"labelName":"见义勇为","labelNum":1},{"labelId":6,"labelName":"迟到早退","labelNum":0},{"labelId":7,"labelName":"助人为乐","labelNum":1},{"labelId":8,"labelName":"常忘打卡","labelNum":0},{"labelId":9,"labelName":"平易近人","labelNum":1},{"labelId":10,"labelName":"严于律己","labelNum":0},{"labelId":11,"labelName":"知错必改","labelNum":0}]
         */

        private int praised;
        private int trampled;
        private List<LabelVoListBean> labelVoList;

        public int getPraised() {
            return praised;
        }

        public void setPraised(int praised) {
            this.praised = praised;
        }

        public int getTrampled() {
            return trampled;
        }

        public void setTrampled(int trampled) {
            this.trampled = trampled;
        }

        public List<LabelVoListBean> getLabelVoList() {
            return labelVoList;
        }

        public void setLabelVoList(List<LabelVoListBean> labelVoList) {
            this.labelVoList = labelVoList;
        }

        public ResultBean() {
        }

        public static class LabelVoListBean implements MultiItemEntity{
            public LabelVoListBean(int labelId, String labelName, int labelNum) {
                this.labelId = labelId;
                this.labelName = labelName;
                this.labelNum = labelNum;
            }

            /**
             * labelId : 1
             * labelName : 劳动模范
             * labelNum : 1
             */


            private int labelId;
            private String labelName;
            private int labelNum;

            public int getLabelId() {
                return labelId;
            }

            public void setLabelId(int labelId) {
                this.labelId = labelId;
            }

            public String getLabelName() {
                return labelName;
            }

            public void setLabelName(String labelName) {
                this.labelName = labelName;
            }

            public int getLabelNum() {
                return labelNum;
            }

            public void setLabelNum(int labelNum) {
                this.labelNum = labelNum;
            }

            @Override
            public int getItemType() {
                return labelName.equals("查看更多")?1:0;
            }
        }
    }
}