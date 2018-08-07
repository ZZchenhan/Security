package xxk.wuhai.seacurity.work.bean;

import java.util.List;

/**
 * Created by 86936 on 2018/7/10.
 *
 * @QQ 869360026
 */

public class ClueBursList {

    /**
     * clueBurstList : [{"clueBurstContent":"string","clueBurstCreate":"string","clueBurstId":0,"latitude":"string","longitude":"string","name":"string","pictureUrls":["string"],"status":"string","userId":0}]
     * haveNext : false
     * totalNum : 0
     */

    private boolean haveNext;
    private int totalNum;
    private List<ClueBurstListBean> clueBurstList;

    public boolean isHaveNext() {
        return haveNext;
    }

    public void setHaveNext(boolean haveNext) {
        this.haveNext = haveNext;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<ClueBurstListBean> getClueBurstList() {
        return clueBurstList;
    }

    public void setClueBurstList(List<ClueBurstListBean> clueBurstList) {
        this.clueBurstList = clueBurstList;
    }

    public static class ClueBurstListBean {
        /**
         * clueBurstContent : string
         * clueBurstCreate : string
         * clueBurstId : 0
         * latitude : string
         * longitude : string
         * name : string
         * pictureUrls : ["string"]
         * status : string
         * userId : 0
         */

        private String clueBurstContent;
        private String clueBurstCreate;
        private int clueBurstId;
        private String latitude;
        private String longitude;
        private String name;
        private String status;
        private long userId;
        private List<String> pictureUrls;

        public String getClueBurstContent() {
            return clueBurstContent;
        }

        public void setClueBurstContent(String clueBurstContent) {
            this.clueBurstContent = clueBurstContent;
        }

        public String getClueBurstCreate() {
            return clueBurstCreate;
        }

        public void setClueBurstCreate(String clueBurstCreate) {
            this.clueBurstCreate = clueBurstCreate;
        }

        public int getClueBurstId() {
            return clueBurstId;
        }

        public void setClueBurstId(int clueBurstId) {
            this.clueBurstId = clueBurstId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public List<String> getPictureUrls() {
            return pictureUrls;
        }

        public void setPictureUrls(List<String> pictureUrls) {
            this.pictureUrls = pictureUrls;
        }
    }
}
