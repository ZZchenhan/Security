package com.hz.junxinbaoan.work.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.utils.DeviceUtils;
import sz.tianhe.baselib.utils.ToastUtils;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.work.api.WorkDutyApi;
import com.hz.junxinbaoan.work.bean.SignResult;
import com.hz.junxinbaoan.work.presenter.ISignPresenter;
import com.hz.junxinbaoan.work.view.itf.ISignView;
import com.hz.junxinbaoan.work.vo.ApiGetUserSignByDayVO;

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
        getData();
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
        mapView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            ViewGroup child = (ViewGroup)  mapView.getChildAt(0);//地图框架
            child.getChildAt(2).setVisibility(View.GONE);//logo
        });
        tvDate = root.findViewById(R.id.date);
        getNumbers(0);
    }

    private void getNumbers(int posion){
        tvSignNumbers.setText("今天你已签到"+ posion+"次");
        SpannableStringBuilder builder = new SpannableStringBuilder(tvSignNumbers.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
        builder.setSpan(redSpan, 6, tvSignNumbers.getText().toString().length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tvSignNumbers.setText(builder);
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
                if(location == null || location.equals("")){
                    toast("由于未知原因获取定位失败，请稍后再试。");
                    return;
                }
                SignComfirmActivity.openActivity(getActivity(),poi,location,new SimpleDateFormat("HH:mm").format(date),currentLatLng);
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
            location = data.getStringExtra("address") == null ?"":data.getStringExtra("address");
            ((ISignPresenter)presenter).refresh(currentLatLng);
            locaionSuccess(city,poi,location,currentLatLng);
        }
    }

    /**
     * 当前经纬度
     */
    private LatLng currentLatLng;
    /**
     * 当前地址
     */
    private String location = "";
    private String poi = "";
    private String city;
    @Override
    public void locaionSuccess(String city,String poi,String adrss, LatLng latLng) {
        this.location = adrss;
        this.city = city;
        this.poi = poi;
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
        ToastUtils.makeText(getActivity(),msg,ToastUtils.LENGTH_LONG).show();
    }
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public void getData(){
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getTodaySingList(new ApiGetUserSignByDayVO(simpleDateFormat.format(new Date())))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<SignResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<SignResult> result) {
                        if(!result.getCode().equals("200")){
                            toast(result.getMessage());
                            return;
                        }
                        getNumbers(result.getResult().getCount());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
