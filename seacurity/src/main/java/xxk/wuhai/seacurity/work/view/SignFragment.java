package xxk.wuhai.seacurity.work.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.utils.DeviceUtils;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.work.presenter.ISignPresenter;
import xxk.wuhai.seacurity.work.view.itf.ISignView;

/**
 * @author 86936
 */
public class SignFragment extends Fragment implements View.OnClickListener,ISignView{
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
     * 日期
     */
    private TextView tvDate;



    public int layoutId() {
        return R.layout.fragment_sign;
    }


    /**
     * 代练
     */
    IBasePresenter presenter;

    public void initView() {
        ((ISignPresenter)presenter).initMap(mapView);
        time();
        presenter.init();
        if(!DeviceUtils.gpsIsOpen(getContext())){
            toast("请您打开定位（GPS）");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null){
            root = inflater.inflate(layoutId(),null);
            findViews();
            createPrensenter();
            mapView.onCreate(savedInstanceState);// 此方法必须重写
            initView();
            presenter.attachView(this);
        }
        return root;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        presenter.dettacheView();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        mapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public void findViews() {
        tvLocation = root.findViewById(R.id.location);
        tvAdjust = root.findViewById(R.id.tv_adjustment);
        mapView = root.findViewById(R.id.tv_map);
        btnTime = root.findViewById(R.id.btn_time);

        tvSignNumbers = root.findViewById(R.id.tv_sign_numbers);

        tvAdjust.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        tvDate = root.findViewById(R.id.date);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_adjustment:
                if(!DeviceUtils.gpsIsOpen(getContext())){
                    toast("请您打开定位（GPS）");
                    return;
                }
                LocationUpdateActivity.openActivity(getActivity(),city,currentLatLng);
                break;
            case R.id.btn_time:
                if(!DeviceUtils.gpsIsOpen(getContext())){
                    toast("请您打开定位（GPS）");
                    return;
                }
                SignComfirmActivity.openActivity(getActivity(),location,new SimpleDateFormat("HH:mm").format(date),currentLatLng);
                break;
        }
    }


    public IBasePresenter createPrensenter(){
        presenter = new ISignPresenter(getContext());
        return presenter;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            getActivity().finish();
        }else if(requestCode == 2 && resultCode == Activity.RESULT_OK){
            currentLatLng = data.getParcelableExtra("latlng");
            location = data.getStringExtra("address");
            ((ISignPresenter)presenter).refresh(currentLatLng);
            locaionSuccess(city,location,currentLatLng);
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
    public void locaionSuccess(String city,String adrss, LatLng latLng) {
        this.location = adrss;
        this.city = city;
        this.currentLatLng = latLng;
        this.tvLocation.setText(this.location);
    }
    Date date;
    @Override
    public void time() {
         date = new Date();
        tvDate.setText(new SimpleDateFormat("yyyy年MM月dd日").format(date));
        btnTime.setText("签到\n"+new SimpleDateFormat("HH:mm").format(date));
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void toast(String msg) {
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_LONG);
    }
}
