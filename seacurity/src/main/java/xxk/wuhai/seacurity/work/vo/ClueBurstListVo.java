package xxk.wuhai.seacurity.work.vo;

/**
 * Created by 86936 on 2018/7/10.
 *
 * @QQ 869360026
 */

public class ClueBurstListVo {

    /**
     * beginTime : string
     * endTime : string
     * pageNum : 0
     * pageSize : 0
     * searchContent : string
     * status : string
     * type : string
     */

    private String beginTime;
    private String endTime;
    private int pageNum;
    private int pageSize;
    private String searchContent;
    private String status;
    private String type;



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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
