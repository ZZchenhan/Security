package com.hz.junxinbaoan.work.vo;

/**
 * 项目名称:Security
 * 类描述
 *
 * @author ch
 * @email 869360026@qq.com
 * 创建时间:2018/7/11 17:21
 */
public class GetUserSignVo {

    public GetUserSignVo(String month,int pageNum,int pagaSize) {
        this.month = month;
        this.pageNum = pageNum;
        this.pagaSize = pagaSize;
    }

    /**
     * month : string
     * pagaSize : 0
     * pageNum : 0
     */



    private String month;
    private int pagaSize;
    private int pageNum;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPagaSize() {
        return pagaSize;
    }

    public void setPagaSize(int pagaSize) {
        this.pagaSize = pagaSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
