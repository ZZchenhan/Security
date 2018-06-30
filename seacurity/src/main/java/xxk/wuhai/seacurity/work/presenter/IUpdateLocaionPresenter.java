package xxk.wuhai.seacurity.work.presenter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.widget.EditText;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.IBaseView;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.work.view.itf.IUpdateLocationView;

/**
 * Created by 86936 on 2018/6/28.
 *
 * @QQ 869360026
 */

public class IUpdateLocaionPresenter implements IBasePresenter,PoiSearch.OnPoiSearchListener{

    private Context mContext;

    IBaseView iUpdateLocationView;

    public IUpdateLocaionPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void attachView(IBaseView view) {
        this.iUpdateLocationView  =view;
    }

    @Override
    public void dettacheView() {
        iUpdateLocationView = null;
    }

    @Override
    public void init() {

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

    LatLng latLng;
    String addrrss;
    /***
     * 开始定位
     */
    private void startLocaion(final AMap aMap) {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.showMyLocation(false);
        myLocationStyle.interval(20000);//设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.radiusFillColor(Color.argb(0x80, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                latLng = new LatLng(location.getLatitude(),location.getLongitude());
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
            }
        });
    }

    PoiSearch.Query query;

    private int currentPage = 1;
    /**
     *
     * @param editText
     * @param city
     */
    public void search(EditText editText,String city){
        if(editText.getText().toString().equals("")){
            iUpdateLocationView.toast("请输入搜索关键字");
            return;
        }
        currentPage = 1;
        query = new PoiSearch.Query(editText.getText().toString(), "", city);
        query.setPageSize(10);
        query.setPageNum(currentPage);
        search(query);
    }

    public void loadMore(){
        currentPage++;
        query.setPageNum(currentPage);
        search(query);
    }


    Marker lastMarket;
    public void onItemClick(PoiItem poiItem,MapView mapView){
        LatLng poi = new LatLng(poiItem.getLatLonPoint().getLatitude(),poiItem.getLatLonPoint().getLongitude());
        mapView.getMap().moveCamera(CameraUpdateFactory.newLatLng(poi));
        mapView.getMap().moveCamera(CameraUpdateFactory.zoomTo(18));
        Marker marker = mapView.getMap().addMarker(new MarkerOptions().position(poi).title(poiItem.getTitle()).snippet("DefaultMarker").icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(), R.mipmap.icon_poi_select))));
        if(lastMarket != null){
            lastMarket.remove();
        }
        lastMarket = marker;
    }


    private void search(PoiSearch.Query query){
        PoiSearch poiSearch = new PoiSearch(mContext,query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if(iUpdateLocationView!=null)
        ((IUpdateLocationView)iUpdateLocationView).poiResult(poiResult.getPois());
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
