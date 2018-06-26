package xxk.wuhai.seacurity.work.view;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.presenter.IBasePresenter;
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
     * 照相
     */
    private Button btnCamero;
    /**
     * 图片1
     */
    private ImageView ivPic1;
    /**
     * 图片2
     */
    private ImageView ivPic2;
    /**
     * 图片3
     */
    private ImageView ivPic3;
    /**
     * 今日签到次数
     */
    private TextView tvSignNumbers;
    /**
     * 跳转到信息提交
     */
    private Button btnSign;
    /**
     * 签到详情
     */
    private Button btnSignInfo;

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
        btnCamero = findViewById(R.id.btn_camora);
        ivPic1 = findViewById(R.id.iv_pic1);
        ivPic2 = findViewById(R.id.iv_pic2);
        ivPic3 = findViewById(R.id.iv_pic3);
        tvSignNumbers = findViewById(R.id.tv_sign_numbers);
        btnSign = findViewById(R.id.btn_sign);
        btnSignInfo = findViewById(R.id.btn_sign_info);


        tvAdjust.setOnClickListener(this);
        btnCamero.setOnClickListener(this);
        btnSign.setOnClickListener(this);
        btnSignInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_adjustment:
                toast("跳转到地址微调页面");
                break;
            case R.id.btn_camora:
                toast("跳转到相机页面");
                break;
            case R.id.btn_sign:
                toast("跳转到签到页面");
                break;
            case R.id.btn_sign_info:
                toast("跳转到签到详情页面");
                break;
        }
    }

    public IBasePresenter createPrensenter(){
        return new ISignPresenter(this);
    }


    /**
     * 当前经纬度
     */
    private LatLng currentLatLng;
    /**
     * 当前地址
     */
    private String location;
    @Override
    public void locaionSuccess(String city, LatLng latLng) {
        this.location = city;
        this.currentLatLng = latLng;
        this.tvLocation.setText(this.location);
    }
}
