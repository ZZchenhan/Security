package com.hz.junxinbaoan.work.presenter;

import android.content.Context;
import android.graphics.BitmapFactory;

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

import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.IBaseView;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.work.view.itf.ISignView;

/**
 * Created by 86936 on 2018/6/26.
 *
 * @QQ 869360026
 */

public class ISignPresenter implements IBasePresenter, AMapLocationListener {

    ISignView iSignView;

    Context mContext;

    public ISignPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void attachView(IBaseView view) {
        this.iSignView = (ISignView) view;
    }

    @Override
    public void dettacheView() {
        mlocationClient.onDestroy();
        this.iSignView = null;

    }

    @Override
    public void init() {


    }
    MapView mapView;

    /**
     * 初始化道德地图
     *
     * @param mapView
     */
    public void initMap(MapView mapView) {
        this.mapView = mapView;
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
    private void startLocaion(AMap aMap) {
        //启动定位获取详细位置
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(mContext);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置未签到模式
//            mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
            //指定位一次
//            mLocationOption.setOnceLocation(true);
            mLocationOption.setInterval(5000);
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//            mlocationClient.stopLocation();
            mlocationClient.startLocation();//启动定位
        }
    }

    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    Marker marker;
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                LatLng latLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
                 marker = mapView.getMap().addMarker(new MarkerOptions().position(latLng).title(aMapLocation.getPoiName()).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(mContext.getResources(), R.mipmap.icon_poi_select))));
                 if(aMapLocation.getAddress()!=null && !aMapLocation.getAddress().equals("")){
                     mlocationClient.stopLocation();
                 }
                if (iSignView != null) {
                    iSignView.locaionSuccess(aMapLocation.getCity(),aMapLocation.getAddress(),latLng);
                }
            }
        }
    }

    public void refresh(LatLng latLng){
        if(marker != null){
            marker.remove();
        }
        marker = mapView.getMap().addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(mContext.getResources(), R.mipmap.icon_poi_select))));
    }
}
