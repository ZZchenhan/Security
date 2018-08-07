package com.hz.junxinbaoan.msg.bean;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 * 人事消息详情
 */
public class PersonnelDetails {
    private String gmtCreate;
    private String name;
    private String post;
    private String time;
    private String type;

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
