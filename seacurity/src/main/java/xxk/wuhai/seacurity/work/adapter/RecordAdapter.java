package xxk.wuhai.seacurity.work.adapter;

import android.graphics.Color;
import android.location.Location;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.RecoderBean;

/**
 * Created by 86936 on 2018/6/29.
 *
 * @QQ 869360026
 */

public class RecordAdapter extends BaseMultiItemQuickAdapter<RecoderBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RecordAdapter(List<RecoderBean> data) {
        super(data);
        addItemType(0, R.layout.item_record_no_locion_issu);
        addItemType(1, R.layout.item_record_locatopm);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecoderBean item) {
        helper.addOnClickListener(R.id.btn_record);
        if(item.getItemType() == 0){
            //无打卡位置
        }else{
            //有打卡位置
            MapView mapView = helper.getView(R.id.map_view);
            if(mapView.getTag() ==null) {
                initMap(mapView);
                mapView.setTag(item);
                mapView.onCreate(null);
                startLocaion(mapView.getMap());
            }
        }
    }

    /***
     * 开始定位
     */
    private void startLocaion(final AMap aMap) {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(20000);//设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.radiusFillColor(Color.argb(0x80, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
            }
        });
    }

    /**
     * 初始化道德地图
     *
     * @param mapView
     */
    public void initMap(MapView mapView) {
        mapView.getMap().getUiSettings().setZoomControlsEnabled(false);//缩放按钮
        mapView.getMap().getUiSettings().setCompassEnabled(false);//指南针
        mapView.getMap().getUiSettings().setMyLocationButtonEnabled(false); //显示默认的定位按钮
        mapView.getMap().getUiSettings().setScaleControlsEnabled(false); //控制比例尺控件是否显示
        startLocaion(mapView.getMap());
    }
}
