package com.hz.junxinbaoan.login.bean;

import java.util.List;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class CodeBean {
    private List<String> imageBytes;

    public CodeBean() {
    }

    public CodeBean(List<String> imageBytes) {
        this.imageBytes = imageBytes;
    }

    public List<String> getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(List<String> imageBytes) {
        this.imageBytes = imageBytes;
    }
}
