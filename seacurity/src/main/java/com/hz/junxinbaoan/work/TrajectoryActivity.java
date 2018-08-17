package com.hz.junxinbaoan.work;

import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.RecoderBean;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;
import com.hz.junxinbaoan.work.api.WorkDutyApi;
import com.hz.junxinbaoan.work.view.GetTrajectoryResponse;
import com.hz.junxinbaoan.work.vo.GetTrajectoryVo;

public class TrajectoryActivity extends BaseActivity {
    private MapView mapView;
    private TextView tvStart;
    private TextView tvEnd;
    private TextView tvEmpty;
    private FrameLayout fragmentContainer;
    @Override
    public int layoutId() {
        return R.layout.activity_trajectory;
    }
    LeftIconNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
         leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "我的排班";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftIconNavagation.setTitleColor(R.color.white);
        return leftIconNavagation;
    }

    @Override
    public void initView() {
        leftIconNavagation.setTvTitle(getIntent().getStringExtra("time"));
        getData();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onPause();
        super.onDestroy();
    }

    @Override
    public void findViews() {
        mapView = findViewById(R.id.map);
        tvStart = findViewById(R.id.start);
        tvEnd = findViewById(R.id.end);
        tvEmpty = findViewById(R.id.empty);
        fragmentContainer = findViewById(R.id.fragment_container);
    }


    public void getData(){
        MyApplication.retrofitClient.getRetrofit()
                .create(WorkDutyApi.class)
                .getTrajectory(new GetTrajectoryVo(getIntent().getStringExtra("time"),MyApplication.userDetailInfo.getUserInfo().getUserId()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<GetTrajectoryResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GetTrajectoryResponse> getTrajectoryResponseResult) {
                            if(!getTrajectoryResponseResult.getCode().equals("200")){
                                toast(getTrajectoryResponseResult.getMessage());
                                return;
                            }
                            if(getTrajectoryResponseResult.getResult().getTrajectoryVoList() == null
                                    || getTrajectoryResponseResult.getResult().getTrajectoryVoList().size() == 0){
                                tvEmpty.setVisibility(View.VISIBLE);
                                fragmentContainer.setVisibility(View.GONE);
                                return;
                            }
                        tvEmpty.setVisibility(View.GONE);
                        fragmentContainer.setVisibility(View.VISIBLE);
                        List<LatLng> latLngs = new ArrayList<LatLng>();
                         for(GetTrajectoryResponse.TrajectoryVoListBean item:getTrajectoryResponseResult.getResult().getTrajectoryVoList()){
                             try {
                                 latLngs.add(new LatLng(Float.parseFloat(item.getLat()), Float.parseFloat(item.getLon())));
                             }catch (Exception e){}
                         }
                         mapView.getMap().clear();
                        mapView.getMap().addPolyline(new PolylineOptions().
                                addAll(latLngs).width(10).color(TrajectoryActivity.this.getResources().getColor(R.color.colorPrimary)));
                        try {
                            mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLngs.get(0), 15));
                            getStart(new LatLonPoint(latLngs.get(0).latitude,latLngs.get(0).longitude));
                            getEnd(new LatLonPoint(latLngs.get(latLngs.size()-1).latitude,latLngs.get(latLngs.size()-1).longitude));
                        }catch (RuntimeException e){}
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getStart(LatLonPoint latLonPoint){
        GeocodeSearch geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                if(regeocodeResult.getRegeocodeAddress().getPois().size()>0){
                    tvStart.setText("起点：" + regeocodeResult.getRegeocodeAddress().getPois().get(0).getTitle());
                }else {
                    tvStart.setText("起点：" + regeocodeResult.getRegeocodeAddress().getTownship());
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);

       Marker marker = mapView.getMap().addMarker(new MarkerOptions().position(new LatLng(latLonPoint.getLatitude(),latLonPoint.getLongitude())).title("起始地址").snippet("起始地址").
                icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(this.getResources(), R.mipmap.ic_start))));
    }

    private void getEnd(LatLonPoint latLonPoint){
        GeocodeSearch geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

                if(regeocodeResult.getRegeocodeAddress().getPois().size()>0){
                    tvEnd.setText("终点：" + regeocodeResult.getRegeocodeAddress().getPois().get(0).getTitle());
                }else {
                    tvEnd.setText("终点：" + regeocodeResult.getRegeocodeAddress().getTownship());
                }
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);
        Marker marker = mapView.getMap().addMarker(new MarkerOptions().position(new LatLng(latLonPoint.getLatitude(),latLonPoint.getLongitude())).title("结束地址").snippet("结束地址").
                icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(this.getResources(), R.mipmap.ic_end))));
    }
}
