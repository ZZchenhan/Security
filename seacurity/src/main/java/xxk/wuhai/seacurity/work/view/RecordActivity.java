package xxk.wuhai.seacurity.work.view;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.RecoderBean;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.adapter.RecordAdapter;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.DayDutyBean;
import xxk.wuhai.seacurity.work.vo.GetSchedulingVo;

/**
 * 用户打卡页面
 */
public class RecordActivity extends BaseActivity implements AMapLocationListener{
    /**
     * 导航栏
     */
    LeftIconNavagation leftIconNavagation;


    private RecyclerView recyclerView;


    private RecordAdapter recordAdapter;

    private List<DayDutyBean.SchedulingInfoListBean> recoderBeans = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.activity_record;
    }

    @Override
    public IBaseNavagation navagation() {
        leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this);
        leftIconNavagation.setTvTitle(new SimpleDateFormat("上班打卡 MM月dd日").format(new Date()));
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return leftIconNavagation;
    }

    @Override
    public void initView() {
        recordAdapter = new RecordAdapter(recoderBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recordAdapter.bindToRecyclerView(recyclerView);
        recordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_record:
                        SupplementSignActivity.openActivity(RecordActivity.this,SupplementSignActivity.class);
                        break;
                }
            }
        });
        startLocaion();
    }

    private String date;
    private void getOneDayDuty(final String date){
        this.date = date;
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getOwnScheduling(new GetSchedulingVo(date,MyApplication.userDetailInfo.getUserInfo().getUserId()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<DayDutyBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<DayDutyBean> result) {
                        if(!result.getCode().equals("200")){
                            toast(result.getMessage());
                            return;
                        }
                        if(result.getResult() == null){
                            return;
                        }

                        recoderBeans.clear();
                        if(result.getResult().getSchedulingInfoList() !=null)
                            recoderBeans.addAll(result.getResult().getSchedulingInfoList());
                        recordAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e!=null){
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        mlocationClient.onDestroy();
        super.onDestroy();
    }

    LatLng latLng;
    String addrrss;
    /***
     * 开始定位
     */
    private void startLocaion() {
        //启动定位获取详细位置
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(this);
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置未签到模式
            mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Sport);
            //指定位一次
//            mLocationOption.setOnceLocation(true);
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
            mlocationClient.stopLocation();
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
               RecoderBean.currentLatLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());;
               RecordAdapter.timeLong = System.currentTimeMillis();
               recordAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

}
