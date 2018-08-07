package com.hz.junxinbaoan.work.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
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
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.RecoderBean;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.common.navagation.CommonNavagation;
import com.hz.junxinbaoan.work.adapter.RecordAdapter;
import com.hz.junxinbaoan.work.api.WorkDutyApi;
import com.hz.junxinbaoan.work.bean.RecordBean;
import com.hz.junxinbaoan.work.bean.scheduling.AttendanceInfoVoListBean;
import com.hz.junxinbaoan.work.bean.scheduling.GetPersonSchedulingByDateResponse;
import com.hz.junxinbaoan.work.bean.scheduling.SchedulingInfoAttVoBean;
import com.hz.junxinbaoan.work.bean.scheduling.SchedulingWithAttVo;
import com.hz.junxinbaoan.work.vo.GetSchedulingVo;
import com.hz.junxinbaoan.work.vo.RecordVo;

/**
 * 用户打卡页面
 */
public class RecordActivity extends BaseActivity implements AMapLocationListener{
    /**
     * 导航栏
     */
    CommonNavagation leftIconNavagation;


    private RecyclerView recyclerView;


    private RecordAdapter adapter;

    private  View empty;
    private List<AttendanceInfoVoListBean> data = new ArrayList<>();


    @Override
    public int layoutId() {
        return R.layout.activity_record;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public IBaseNavagation navagation() {
        leftIconNavagation = (CommonNavagation) new CommonNavagation(this){

            @Override
            public String title() {
                return new SimpleDateFormat("上班打卡 MM月dd日").format(new Date());
            }
        };
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftIconNavagation.setRight("排班");
        leftIconNavagation.setRightOnclickListner(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDutyActivity.openActivity(RecordActivity.this,MyDutyActivity.class);
            }
        });
        return leftIconNavagation;
    }

    @Override
    public void initView() {
        adapter = new RecordAdapter(data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_record:
                        AttendanceInfoVoListBean item =   data.get(position);
                        String distance = "未设置";
                        if(item.getAttendanceLatExpect() != null|| item.getAttendanceLonExpect() != null){
                            LatLng latLng = new LatLng(Float.parseFloat(item.getAttendanceLatExpect()),Float.parseFloat(item.getAttendanceLonExpect()));
                            if(RecoderBean.currentLatLng == null){
                                distance = "未知";
                            }else{
                                distance = AMapUtils.calculateLineDistance(RecoderBean.currentLatLng,latLng)+"米";
                            }

                        }
                        record(data.get(position).getSchedulingId(),
                                data.get(position).getId(),distance,item.getAttendanceLocationExpect());
                        break;
                    case R.id.tv_apply:
                        startActivity(new Intent(RecordActivity.this,SupplementSignActivity.class)
                                .putExtra("id", data.get(position).getId())
                                .putExtra("time", date + " " + data.get(position).getAttendanceTimeExpect()));
                        break;
                }
            }
        });
        empty = LayoutInflater.from(this).inflate(R.layout.empty_view,null,false);
        empty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        adapter.setEmptyView(empty);
        startLocaion();
        adapter.setChooseDay(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getOneDayDuty(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private String date;
    private void getOneDayDuty(final String date){
        this.date = date;
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getOwnScheduling(new GetSchedulingVo(date,MyApplication.userDetailInfo.getUserInfo().getUserId()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<GetPersonSchedulingByDateResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GetPersonSchedulingByDateResponse> result) {
                        if(!result.getCode().equals("200")){
                            toast(result.getMessage());
                            return;
                        }
                        if(result.getResult() == null){
                            return;
                        }

                        data.clear();
                        if(result.getResult().getSchedulingInfoAttVo()!=null) {
                            data.addAll(mergeData(result.getResult().getSchedulingInfoAttVo()));
                        }
                        adapter.notifyDataSetChanged();
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



    private boolean compareProssIsCross(String last,String current){
        if(last.compareTo(current)>0){
            return true;
        }else{
            return false;
        }
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
            mLocationOption.setInterval(30*1000);
            //设置未签到模式
//            mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Sport);
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
    String poi="";
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
               RecoderBean.currentLatLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());;
               RecordAdapter.timeLong = System.currentTimeMillis();
                poi =  aMapLocation.getPoiName();
               adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }


    public void record(String scheId, String id, final String distance, final String location){
        RecordVo recordVo = new RecordVo();
        recordVo.setAttendanceId(id+"");
        try {
            recordVo.setAttendanceLat(RecoderBean.currentLatLng.latitude + "");
            recordVo.setAttendanceLon(RecoderBean.currentLatLng.longitude + "");
            recordVo.setAttendanceLocation(poi);
        }catch (Exception e){}
        recordVo.setSchedulingId(scheId);
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .record(recordVo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<RecordBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RecordBean> recoderBeanResult) {
                        if(!recoderBeanResult.getCode().equals("200")){
                            toast(recoderBeanResult.getMessage());
                            if(!recoderBeanResult.getMessage().equals("不在打卡范围")){
                                Intent intent = new Intent(RecordActivity.this,RecordFaileActivity.class);
                                intent.putExtra("distance",distance);
                                intent.putExtra("location",location);
                                intent.putExtra("current",poi);
                                startActivity(intent);
                            }
                            return;
                        }
                        toast("打卡成功");
                        data.clear();
                        adapter.notifyDataSetChanged();
                        getOneDayDuty(date);
                    }

                    @Override
                    public void onError(Throwable e) {
                        toast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private  List<AttendanceInfoVoListBean> mergeData(SchedulingInfoAttVoBean schedulingInfoAttVoBean){
        List<AttendanceInfoVoListBean> attendanceInfoVoListBeans = new ArrayList<>();
        attendanceInfoVoListBeans.addAll(mergeData(schedulingInfoAttVoBean.getDailySchedulingInfoVoList()));
        attendanceInfoVoListBeans.addAll(mergeData(schedulingInfoAttVoBean.getOvertimeSchedulingInfoVoList()));
        attendanceInfoVoListBeans.addAll(mergeData(schedulingInfoAttVoBean.getTemporarySchedulingInfoVoList()));
        return attendanceInfoVoListBeans;
    }

    private List<AttendanceInfoVoListBean>  mergeData(List<SchedulingWithAttVo> withAttVos){
        List<AttendanceInfoVoListBean> attendanceInfoVoListBeans = new ArrayList<>();
        if(withAttVos == null){return attendanceInfoVoListBeans;}
        for(SchedulingWithAttVo schedulingWithAttVo:withAttVos){
            if(schedulingWithAttVo.getAttendanceInfoVoList() != null){
                String isLastPostime = null;
                boolean postionAfterIsCross = false;
                for(AttendanceInfoVoListBean attendanceInfoVoListBean:schedulingWithAttVo.getAttendanceInfoVoList() ){
                    attendanceInfoVoListBean.setScheduleName(schedulingWithAttVo.getScheduleName());
                    attendanceInfoVoListBeans.add(attendanceInfoVoListBean);
                    if(postionAfterIsCross!=true && isLastPostime != null && compareProssIsCross(isLastPostime,attendanceInfoVoListBean.getAttendanceTimeExpect())){
                        attendanceInfoVoListBean.setLastDay(true);
                        postionAfterIsCross = true;
                    }else if(postionAfterIsCross){
                        attendanceInfoVoListBean.setLastDay(true);
                    }
                    isLastPostime = attendanceInfoVoListBean.getAttendanceTimeExpect();
                }
            }
        }
        return attendanceInfoVoListBeans;
    }
}
