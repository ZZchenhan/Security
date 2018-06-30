package xxk.wuhai.seacurity.bean;

import com.amap.api.maps2d.model.LatLng;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by 86936 on 2018/6/29.
 *
 * @QQ 869360026
 */

public class RecoderBean implements MultiItemEntity {

    public static LatLng currentLatLng = null;
    /**
     * 0 不需要位置 1需要位置
     */
    private int type;

    /**
     * 打开类型 上班打卡 下班打卡 加班打卡等
     */
    private String recordType;


    /**
     * 班次名称
     */
    private String clsName;


    /**
     * 打卡状态
     * 0 未打卡
     * 1 已打卡
     */
    private int state = 0;

    /**
     * 所需要时间
     */
    private long time;


    /**
     * 所需要经纬度
     */
    private LatLng latLng;

    /**
     * 所需位置
     */
    private String location;

    public RecoderBean(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

}
