package xxk.wuhai.seacurity.work.view;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.utils.DeviceUtils;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.presenter.ISignPresenter;
import xxk.wuhai.seacurity.work.view.itf.ISignView;

/**
 * @author 86936
 */
public class SignActivity extends BaseActivity implements View.OnClickListener,ISignView{
    /**
     * 定位地址
     */
    private TextView tvLocation;
    /**
     * 地址微调
     */
    private TextView tvAdjust;
    /**
     * 地图
     */
    private MapView mapView;
    /**
     * 签到时间
     */
    private TextView btnTime;

    /**
     * 今日签到次数
     */
    private TextView tvSignNumbers;
    /**
     * 跳转到信息提交
     */
    private TextView btnSign;
    /**
     * 签到详情
     */
    private TextView btnSignInfo;

    /**
     * 日期
     */
    private TextView tvDate;


    @Override
    public int layoutId() {
        return R.layout.activity_sign;
    }


    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "签到";
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
        ((ISignPresenter)presenter).initMap(mapView);
        presenter.init();
        if(!DeviceUtils.gpsIsOpen(this)){
            toast("请您打开定位（GPS）");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void findViews() {
        tvLocation = findViewById(R.id.location);
        tvAdjust = findViewById(R.id.tv_adjustment);
        mapView = findViewById(R.id.tv_map);
        btnTime = findViewById(R.id.btn_time);

        tvSignNumbers = findViewById(R.id.tv_sign_numbers);
        btnSign = findViewById(R.id.btn_sign);
        btnSignInfo = findViewById(R.id.btn_sign_info);


        tvAdjust.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnSignInfo.setOnClickListener(this);
        tvDate = findViewById(R.id.date);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_adjustment:
                if(!DeviceUtils.gpsIsOpen(this)){
                    toast("请您打开定位（GPS）");
                    return;
                }
                LocationUpdateActivity.openActivity(this,location,city,new SimpleDateFormat("HH:mm").format(new Date()),currentLatLng);
                break;
            case R.id.btn_time:
                if(!DeviceUtils.gpsIsOpen(this)){
                    toast("请您打开定位（GPS）");
                    return;
                }
                SignComfirmActivity.openActivity(this,location,new SimpleDateFormat("HH:mm").format(new Date()),currentLatLng);
                break;
            case R.id.btn_sign_info:
                toast("跳转到签到详情页面");
                break;
        }
    }

    public IBasePresenter createPrensenter(){
        return new ISignPresenter(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            finish();
        }
    }

    /**
     * 当前经纬度
     */
    private LatLng currentLatLng;
    /**
     * 当前地址
     */
    private String location;
    private String city;
    @Override
    public void locaionSuccess(String city, LatLng latLng) {
        this.location = city;
        this.city = city;
        this.currentLatLng = latLng;
        this.tvLocation.setText(this.location);
    }

    @Override
    public void time() {
        Date date = new Date();
        tvDate.setText(new SimpleDateFormat("yyyy年MM月dd日").format(date));
        btnTime.setText("签到\n"+new SimpleDateFormat("HH:mm").format(date));
    }
}
