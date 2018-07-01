package xxk.wuhai.seacurity.work.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.RecoderBean;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.adapter.RecordAdapter;
import xxk.wuhai.seacurity.work.view.custorm.DuyteHead;

/**
 *我的值班
 */
public class MyDutyActivity extends BaseActivity implements AMapLocationListener{

    RecyclerView recyclerView;
    RecordAdapter adapter;
    List<RecoderBean> data = new ArrayList<RecoderBean>();
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
        adapter.addHeaderView(duyteHead);
        View headrview = LayoutInflater.from(this).inflate(R.layout.layout_duty_head2,null);
        headrview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tvDay = headrview.findViewById(R.id.time);
        btnTrajectory = headrview.findViewById(R.id.btn_trajectory);
        btnTrajectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("查看轨迹");
            }
        });
        adapter.addHeaderView(headrview);
        adapter.bindToRecyclerView(recyclerView);
        duyteHead.setDataSelect(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Calendar calendar, boolean isClick) {
                tvDay.setText(calendar.getMonth()+"月" + calendar.getDay()+"日");
            }
        });
        duyteHead.setMonthChange(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                tvDate.setText(year+"年"+month+"月" );
            }
        });
        similarData();
        startLocaion();
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tvDate = findViewById(R.id.tv_month);
    }

    private void similarData(){
        List<Calendar> schemes = new ArrayList<>();
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)+1;
        schemes.add(duyteHead.getSchemeCalendar(year, month, 3, "白"));
        schemes.add(duyteHead.getSchemeCalendar(year, month, 6, "晚"));
        schemes.add(duyteHead.getSchemeCalendar(year, month, 10,"晚"));
        schemes.add(duyteHead.getSchemeCalendar(year, month, 11,"白"));
        schemes.add(duyteHead.getSchemeCalendar(year, month, 14,"白"));
        schemes.add(duyteHead.getSchemeCalendar(year, month, 15,"休"));
        schemes.add(duyteHead.getSchemeCalendar(year, month, 18,"休"));
        duyteHead.setSchemes(schemes);

        data.add(new RecoderBean(0));
        data.add(new RecoderBean(1));
        data.add(new RecoderBean(0));
        data.add(new RecoderBean(0));
        data.add(new RecoderBean(1));
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
            mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Transport);
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
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy() {
        mlocationClient.stopLocation();
        super.onDestroy();
    }
}
