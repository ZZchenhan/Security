package xxk.wuhai.seacurity.msg.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by 86936 on 2018/7/3.
 *
 * @QQ 869360026
 */

public class MsgBean implements MultiItemEntity{

    public int isPic;

    public MsgBean(int isPic) {
        this.isPic = isPic;
    }

    private String title;

    private List<String> pics;

    private String status;

    private String time;

    public int getIsPic() {
        return isPic;
    }

    public void setIsPic(int isPic) {
        this.isPic = isPic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int getItemType() {
        return isPic;
    }
}
