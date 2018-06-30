package xxk.wuhai.seacurity.contact.bean;

/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */

public class TagBean {
    private boolean isBlue;
    private String tag;

    public boolean isBlue() {
        return isBlue;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public TagBean(boolean isBlue, String tag) {
        this.isBlue = isBlue;
        this.tag = tag;
    }
}
