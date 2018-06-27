package xxk.wuhai.seacurity.work.presenter;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.IBaseView;
import xxk.wuhai.seacurity.work.view.itf.ISignView;

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
        this.iSignView = null;
        mlocationClient.onDestroy();
        observable.unsubscribeOn(AndroidSchedulers.mainThread());
    }
    Observable observable;
    @Override
    public void init() {
        observable = Observable.interval(1, TimeUnit.MINUTES);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                if(iSignView!=null){
                    iSignView.time();
                }
            }

            @Override
            public void onNext(Long aLong) {
                if(iSignView!=null){
                    iSignView.time();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

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

    LatLng latLng;
    String addrrss;
    /***
     * 开始定位
     */
    private void startLocaion(AMap aMap) {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(20000);//设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.radiusFillColor(Color.argb(0x80, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

                 latLng = new LatLng(location.getLatitude(),location.getLongitude());
            }
        });

        //启动定位获取详细位置
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(mContext);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
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
            mlocationClient.startLocation();//启动定位
        }
    }

    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                if (iSignView != null) {
                    LatLng latLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                    iSignView.locaionSuccess(aMapLocation.getCity()+aMapLocation.getCountry()+aMapLocation.getPoiName(),latLng);
                }
            }
        }
    }
}
