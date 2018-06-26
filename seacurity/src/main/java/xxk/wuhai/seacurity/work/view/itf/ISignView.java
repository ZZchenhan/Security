package xxk.wuhai.seacurity.work.view.itf;

import com.amap.api.maps2d.model.LatLng;

import sz.tianhe.baselib.view.IBaseView;

/**
 * Created by 86936 on 2018/6/26.
 *
 * @QQ 869360026
 */

public interface ISignView extends IBaseView {
    /**
     * 定位成功后显示
     * @param city
     * @param latLng
     */
    void locaionSuccess(String city, LatLng latLng);
}
