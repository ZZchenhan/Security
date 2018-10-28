package com.hz.junxinbaoan.work.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.text.SimpleDateFormat;
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
import com.hz.junxinbaoan.common.navagation.CommonNavagation;
import com.hz.junxinbaoan.utils.DateUtils;
import com.hz.junxinbaoan.work.TrajectoryActivity;
import com.hz.junxinbaoan.work.adapter.RecordAdapter;
import com.hz.junxinbaoan.work.api.WorkDutyApi;
import com.hz.junxinbaoan.work.bean.PersonSchedulingResult;
import com.hz.junxinbaoan.work.bean.RecordBean;
import com.hz.junxinbaoan.work.bean.scheduling.AttendanceInfoVoListBean;
import com.hz.junxinbaoan.work.bean.scheduling.GetPersonSchedulingByDateResponse;
import com.hz.junxinbaoan.work.bean.scheduling.SchedulingInfoAttVoBean;
import com.hz.junxinbaoan.work.bean.scheduling.SchedulingWithAttVo;
import com.hz.junxinbaoan.work.view.custorm.DuyteHead;
import com.hz.junxinbaoan.work.vo.GetSchedulingByTimeVo;
import com.hz.junxinbaoan.work.vo.GetSchedulingVo;
import com.hz.junxinbaoan.work.vo.RecordVo;

/**
 *我的值班
 */
public class MyDutyActivity extends BaseActivity implements AMapLocationListener{

    RecyclerView recyclerView;
    RecordAdapter adapter;
    List<AttendanceInfoVoListBean> data = new ArrayList<>();
    private TextView tvDate;
    private TextView tvDay;
    private RelativeLayout btnTrajectory;
    private DuyteHead duyteHead;
    private  View empty;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    private ImageView ivPre;

    private ImageView ivNext;

    @Override
    public int layoutId() {
        return R.layout.activity_my_duty;
    }

    @Override
    public IBaseNavagation navagation() {
        CommonNavagation leftIconNavagation = (CommonNavagation) new CommonNavagation(this) {
            @Override
            public String title() {
                return "我的排班";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(view -> finish());
//        leftIconNavagation.setRight("切换");
//        leftIconNavagation.setRightOnclickListner(view -> {
//            duyteHead.showYear();
//        });
        leftIconNavagation.setTitleColor(R.color.white);
        return leftIconNavagation;
    }

    @Override
    public void initView() {
        adapter = new RecordAdapter(data);
        duyteHead = new DuyteHead(this);
        View head1 = LayoutInflater.from(this).inflate(R.layout.layout_duty_head3,null);
        tvDate = head1.findViewById(R.id.tv_month);
        ivPre = head1.findViewById(R.id.pre);
        ivNext = head1.findViewById(R.id.next);
        ivPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duyteHead.scrollToPre();
            }
        });
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duyteHead.scrollNext();
            }
        });

        adapter.addHeaderView(head1);
        adapter.addHeaderView(duyteHead);
        View headrview = LayoutInflater.from(this).inflate(R.layout.layout_duty_head2,null);
        headrview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvDay = headrview.findViewById(R.id.time);
        btnTrajectory = headrview.findViewById(R.id.btn_trajectory);
        btnTrajectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyDutyActivity.this, TrajectoryActivity.class).putExtra("time",date));
            }
        });
        adapter.addHeaderView(headrview);
        adapter.bindToRecyclerView(recyclerView);
        duyteHead.setDataSelect(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Calendar calendar, boolean isClick) {
                tvDay.setText(calendar.getMonth()+"月" + calendar.getDay()+"日");
                java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                calendar1.set(java.util.Calendar.YEAR,calendar.getYear());
                calendar1.set(java.util.Calendar.MONTH,calendar.getMonth()-1);
                calendar1.set(java.util.Calendar.DAY_OF_MONTH,calendar.getDay());
                adapter.setChooseDay(String.format("%d-%02d-%02d",calendar.getYear(),calendar.getMonth(),calendar.getDay()));
                getOneDayDuty(String.format("%d-%02d-%02d",calendar.getYear(),calendar.getMonth(),calendar.getDay()));
            }
        });
        duyteHead.setMonthChange(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                page = 1;
                tvDate.setText(year+"年"+month+"月");
                getData(year,month);
            }
        });
       final java.util.Calendar calendar =  java.util.Calendar.getInstance();
        getData(calendar.get(java.util.Calendar.YEAR),calendar.get(java.util.Calendar.MONTH)+1);
        tvDate.setText(calendar.get(java.util.Calendar.YEAR)+"年"+(calendar.get(java.util.Calendar.MONTH)+1)+"月" );
        startLocaion();
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_record:
                        AttendanceInfoVoListBean item =   data.get(position);
                        String distance = "未设置";

                        try {
                            if (item.getAttendanceLatExpect() != null || item.getAttendanceLonExpect() != null) {
                                LatLng latLng = new LatLng(Float.parseFloat(item.getAttendanceLatExpect()), Float.parseFloat(item.getAttendanceLonExpect()));
                                if (RecoderBean.currentLatLng == null) {
                                    distance = "未知";
                                } else {
                                    distance = AMapUtils.calculateLineDistance(RecoderBean.currentLatLng, latLng) + "米";
                                }

                            }
                        }catch (Exception e){}
                        record(data.get(position).getSchedulingId(),
                                data.get(position).getId(),distance,item.getAttendanceLocationExpect());
                        break;
                    case R.id.tv_apply:
                            startActivity(new Intent(MyDutyActivity.this, SupplementSignActivity.class)
                                    .putExtra("id", data.get(position).getId())
                                    .putExtra("time", date + " " + data.get(position).getAttendanceTimeExpect()));

                        break;
                    case R.id.refresh:
                        startLocaion();
                        break;
                }
            }
        });
        empty = LayoutInflater.from(this).inflate(R.layout.empty_view,null,false);
        empty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private int page = 1;

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(date != null) {
            getOneDayDuty(date);
        }
    }

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
            mLocationOption.setInterval(1000*5);
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
    private String poi;
    Marker marker;
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                RecoderBean.currentLatLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                RecordAdapter.timeLong = System.currentTimeMillis();
                poi =  aMapLocation.getPoiName();
                if(poi== null ||poi.equals("")){
                    poi = aMapLocation.getStreet();
                }
                if(poi == null || poi.equals("")){
                    mlocationClient.stopLocation();
                    startLocaion();
                }
                RecoderBean.poi = poi;
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        mlocationClient.stopLocation();
        super.onDestroy();
    }


    private void getData(final int year, final int month){
        MyApplication.retrofitClient.getRetrofit()
                .create(WorkDutyApi.class)
                .getTimesScheduling(new GetSchedulingByTimeVo(
                        DateUtils.getMonthLastDay(year,month-1),
                        DateUtils.getMonthFistDay(year,month-1),
                        MyApplication.userDetailInfo.getUserInfo().getUserId()
                )).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<PersonSchedulingResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<PersonSchedulingResult> personSchedulingResultResult) {
                        if(!personSchedulingResultResult.getCode().equals("200")){
                            ToastUtils.showShort(personSchedulingResultResult.getMessage());
                            return;
                        }
                        if(personSchedulingResultResult.getResult().getPersonSchedulingMap() == null && personSchedulingResultResult.getResult().getPersonSchedulingMap().size() == 0){
                            ToastUtils.showShort("没有更多数据");
                            return;
                        }
                        duyteHead.setSchemes(personSchedulingResultResult.getResult().getPersonSchedulingMap());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }


    private String date;
    private void getOneDayDuty(final String date){
        this.date = date;
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getOwnScheduling(new GetSchedulingVo(date,  MyApplication.userDetailInfo.getUserInfo().getUserId()))
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Result<GetPersonSchedulingByDateResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<GetPersonSchedulingByDateResponse> result) {
                adapter.removeHeaderView(empty);
                if(!result.getCode().equals("200")){
                    toast(result.getMessage());
                    return;
                }
                if(result.getResult() == null){
                    if(data.size() == 0){
                        adapter.addHeaderView(empty);
                    }
                    return;
                }

                data.clear();
                if(result.getResult().getSchedulingInfoAttVo()!=null)
                    data.addAll(mergeData(result.getResult().getSchedulingInfoAttVo()));
                adapter.notifyDataSetChanged();
                if(data.size() == 0){
                    adapter.addHeaderView(empty);
                }
            }

            @Override
            public void onError(Throwable e) {
                if(e!=null){
                    toast(e.getMessage());
                }
                if(data.size() == 0){
                    adapter.removeHeaderView(empty);
                    adapter.addHeaderView(empty);
                }
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

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    private List<AttendanceInfoVoListBean>  mergeData(List<SchedulingWithAttVo> withAttVos){
        List<AttendanceInfoVoListBean> attendanceInfoVoListBeans = new ArrayList<>();
        if(withAttVos == null){return attendanceInfoVoListBeans;}
        for(SchedulingWithAttVo schedulingWithAttVo:withAttVos){
            if(schedulingWithAttVo.getAttendanceInfoVoList() != null){
                String isLastPostime = null;
                boolean postionAfterIsCross = false;
                for(AttendanceInfoVoListBean attendanceInfoVoListBean:schedulingWithAttVo.getAttendanceInfoVoList() ){
                    attendanceInfoVoListBean.setScheduleName(schedulingWithAttVo.getScheduleName());
                    attendanceInfoVoListBean.setScheduleShortName(schedulingWithAttVo.getScheduleShortName());
                    attendanceInfoVoListBean.setType(schedulingWithAttVo.getType());
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

    private boolean compareProssIsCross(String last,String current){
        if(last.compareTo(current)>0){
            return true;
        }else{
            return false;
        }
    }

    public void record(String scheId, String id, final String distance, final String location){
//        if(poi == null || poi.equals("")){
//            toast("定位地址信息获取失败，请重试");
//            return;
//        }
        RecordVo recordVo = new RecordVo();
        recordVo.setAttendanceId(id+"");
        try {
            recordVo.setAttendanceLocation(poi);
            recordVo.setAttendanceLat(RecoderBean.currentLatLng.latitude + "");
            recordVo.setAttendanceLon(RecoderBean.currentLatLng.longitude + "");
        }catch (Exception e){}
        recordVo.setSchedulingId(scheId);
//        toast("上传数据:"+new Gson().toJson(recordVo));
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
                                Intent intent = new Intent(MyDutyActivity.this,RecordFaileActivity.class);
                                intent.putExtra("distance",distance);
                                intent.putExtra("location",location);
                                intent.putExtra("current",poi);
                                startActivity(intent);
                            }
                            return;
                        }
                        toast("打卡成功");
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
}
