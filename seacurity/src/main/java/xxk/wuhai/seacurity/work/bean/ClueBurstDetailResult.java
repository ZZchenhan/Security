package xxk.wuhai.seacurity.work.bean;

import java.util.List;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/11 15:24
 */
public class ClueBurstDetailResult {
    /**
     * clueBurstDetail : {"address":"string","clueBurstContent":"string","clueBurstCreate":"string","clueBurstId":0,"latitude":"string","longitude":"string","name":"string","pictureUrls":["string"],"status":"string","tapeUrl":"string","userId":0}
     */

    private ClueBurstDetailBean clueBurstDetail;

    public ClueBurstDetailBean getClueBurstDetail() {
        return clueBurstDetail;
    }

    public void setClueBurstDetail(ClueBurstDetailBean clueBurstDetail) {
        this.clueBurstDetail = clueBurstDetail;
    }

    public static class ClueBurstDetailBean {
        /**
         * address : string
         * clueBurstContent : string
         * clueBurstCreate : string
         * clueBurstId : 0
         * latitude : string
         * longitude : string
         * name : string
         * pictureUrls : ["string"]
         * status : string
         * tapeUrl : string
         * userId : 0
         */

        private String address;
        private String clueBurstContent;
        private String clueBurstCreate;
        private int clueBurstId;
        private String latitude;
        private String longitude;
        private String name;
        private String status;
        private String tapeUrl;
        private int userId;
        private List<String> pictureUrls;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

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

        public String getTapeUrl() {
            return tapeUrl;
        }

        public void setTapeUrl(String tapeUrl) {
            this.tapeUrl = tapeUrl;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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
