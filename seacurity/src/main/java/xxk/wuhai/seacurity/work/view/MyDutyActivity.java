package xxk.wuhai.seacurity.work.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.RecoderBean;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.msg.view.ExamineActivity;
import xxk.wuhai.seacurity.utils.DateUtils;
import xxk.wuhai.seacurity.work.TrajectoryActivity;
import xxk.wuhai.seacurity.work.adapter.RecordAdapter;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.DayDutyBean;
import xxk.wuhai.seacurity.work.bean.RecordBean;
import xxk.wuhai.seacurity.work.bean.ScheduingResult;
import xxk.wuhai.seacurity.work.view.custorm.DuyteHead;
import xxk.wuhai.seacurity.work.vo.GetPersonScheduingVo;
import xxk.wuhai.seacurity.work.vo.GetSchedulingByUserIdVo;
import xxk.wuhai.seacurity.work.vo.GetSchedulingVo;
import xxk.wuhai.seacurity.work.vo.RecordVo;

/**
 *我的值班
 */
public class MyDutyActivity extends BaseActivity implements AMapLocationListener{

    RecyclerView recyclerView;
    RecordAdapter adapter;
    List<DayDutyBean.SchedulingInfoListBean> data = new ArrayList<>();
    private TextView tvDate;
    private TextView tvDay;
    private Button btnTrajectory;
    private DuyteHead duyteHead;

    @Override
    public int layoutId() {
        return R.layout.activity_my_duty;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
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
        adapter = new RecordAdapter(data);
        duyteHead = new DuyteHead(this);
        View head1 = LayoutInflater.from(this).inflate(R.layout.layout_duty_head3,null);
        tvDate = head1.findViewById(R.id.tv_month);
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
                getOneDayDuty(String.format("%d-%02d-%02d",calendar.getYear(),calendar.getMonth(),calendar.getDay()));
            }
        });
        duyteHead.setMonthChange(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                page = 1;
                tvDate.setText(year+"年"+month+"月");

                getOneMoneyDuty(page,year,month);
            }
        });
       final java.util.Calendar calendar =  java.util.Calendar.getInstance();
        getOneMoneyDuty(page,calendar.get(java.util.Calendar.YEAR),calendar.get(java.util.Calendar.MONTH)+1);
        tvDate.setText(calendar.get(java.util.Calendar.YEAR)+"年"+(calendar.get(java.util.Calendar.MONTH)+1)+"月" );
        startLocaion();
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_record:
                        record(data.get(position).getAttendanceInfoVoList().get(0).getScheduleId(),
                                data.get(position).getAttendanceInfoVoList().get(0).getAttendanceSetId());
                        break;
                    case R.id.tv_apply:
                        startActivity(new Intent(MyDutyActivity.this,ExamineActivity.class)
                        .putExtra("id", data.get(position).getAttendanceInfoVoList().get(0).getAttendanceSetId()));
                        break;
                }
            }
        });
    }

    private int page = 1;

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    private String poi;
    Marker marker;
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
    protected void onDestroy() {
        mlocationClient.stopLocation();
        super.onDestroy();
    }


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private void getOneMoneyDuty(int page, final int year, final int month){
        GetPersonScheduingVo getPersonScheduingVo = new GetPersonScheduingVo();
        getPersonScheduingVo.setPageNum(page);
        getPersonScheduingVo.setStartDay(DateUtils.getMonthFistDay(year,month-1));
        getPersonScheduingVo.setEndDay(DateUtils.getMonthLastDay(year,month-1));
        getPersonScheduingVo.setPageSize(20);
        getPersonScheduingVo.setEmpId(MyApplication.userDetailInfo.getUserInfo().getDeptId());
        getPersonScheduingVo.setStaffId(MyApplication.userDetailInfo.getUserInfo().getUserId());

        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getPersonSchedulingByEmpId(getPersonScheduingVo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScheduingResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScheduingResult scheduingResultResult) {
                        if(!scheduingResultResult.getCode().equals("200")){
                            toast(scheduingResultResult.getMessage());
                            return;
                        }

                        if(scheduingResultResult.getResult().getPersonSchedulingList() == null
                                ||scheduingResultResult.getResult().getPersonSchedulingList().size() == 0
                                ){
                           toast("没有更多数据了");
                            return;
                        }
                        List<Calendar> schemes = new ArrayList<>();
                        for(ScheduingResult.ResultBean.PersonSchedulingListBean personSchedulingListBean :scheduingResultResult.getResult().getPersonSchedulingList()){
                            Map<String,List<ScheduingResult.ResultBean.PersonSchedulingListBean.ScheduingDetails>>  map =    personSchedulingListBean.getSchedulingList();
                            Set<String> keys = map.keySet();
                            for (String key : keys){
                                List<ScheduingResult.ResultBean.PersonSchedulingListBean.ScheduingDetails> scheduingDetailsList = map.get(key);
                                if(scheduingDetailsList!=null) {
                                    try {
                                       Date date =  simpleDateFormat.parse(key);schemes.add(duyteHead.getSchemeCalendar(year, month, date.getDay(), "班"));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        duyteHead.setSchemes(schemes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e != null){
                            toast(e.getMessage());
                            return;
                        }
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

                data.clear();
                if(result.getResult().getSchedulingInfoList() !=null)
                    data.addAll(result.getResult().getSchedulingInfoList());
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

    public void record(int scheId,int id){
        RecordVo recordVo = new RecordVo();
        recordVo.setAttendanceId(id+"");
        try {
            recordVo.setAttendanceLat(RecoderBean.currentLatLng.latitude + "");
            recordVo.setAttendanceLon(RecoderBean.currentLatLng.longitude + "");
            recordVo.setAttendanceLocation(poi);
        }catch (Exception e){}
        recordVo.setSchedulingId(scheId+"");
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
                            startActivity(new Intent(MyDutyActivity.this,RecordFaileActivity.class));
                            return;
                        }
                        toast("打卡成功");
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
