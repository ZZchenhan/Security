package com.hz.junxinbaoan.work.vo;

import java.util.List;

/**
 * Created by 86936 on 2018/7/10.
 *
 * @QQ 869360026
 */

public class AddClueBurstVo {

    /**
     * address : string
     * clueBurstContent : string
     * latitude : string
     * longitude : string
     * pictureUrls : ["string"]
     * tapeUrl : string
     */

    private String address;
    private String clueBurstContent;
    private String latitude;
    private String longitude;
    private String tapeUrl;
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

    public String getTapeUrl() {
        return tapeUrl;
    }

    public void setTapeUrl(String tapeUrl) {
        this.tapeUrl = tapeUrl;
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }
}
