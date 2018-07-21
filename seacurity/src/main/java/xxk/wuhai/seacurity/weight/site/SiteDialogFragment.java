package xxk.wuhai.seacurity.weight.site;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.weight.WheelPicker;


/**
 * 时间选择器，弹出框
 * Created by ycuwq on 2018/1/6.
 */
public class SiteDialogFragment extends Dialog {

    static List<Province> provinces = new ArrayList<>();
    List<City> cities = new ArrayList<>();
    List<District> districts = new ArrayList<>();
    private TextView btnCancel;

    private TextView btnConfirm;
    private LocaionPicker<Province> provinceWheel;

    private LocaionPicker<City> cityWhel;

    private LocaionPicker<District> coutryWhell;

    public SiteDialogFragment(@NonNull Context context) {
        this(context, R.style.bootomDialog);
    }

    private Province province;
    private City city;
    private District district;

    public SiteDialogFragment(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_site, null, false));
        btnCancel = findViewById(R.id.btn_dialog_date_cancel);
        btnConfirm = findViewById(R.id.btn_dialog_date_decide);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        provinceWheel = findViewById(R.id.province);
        cityWhel = findViewById(R.id.city);
        coutryWhell = findViewById(R.id.country);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onSiteComfirmListener!=null){
                    if(province == null){
                        return;
                    }
                    if(city == null){
                        city = cities.size()>0?cities.get(0):null;
                        if(city == null){
                            return;
                        }
                    }
                    if(district == null){
                        district = districts.size()>0?districts.get(0):null;
                        if(district == null){
                            return;
                        }
                    }
                    onSiteComfirmListener.onConfimr(
                            province.getProvinceName()+city.getCityName()+district.getDistrictName()
                            ,province.getProvinceId(),city.getCityId(),district.getDistrictId()
                    );
                }
                dismiss();
            }
        });
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);

        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = d.getWidth(); //宽度设置为屏幕
        getWindow().setAttributes(p); //设置生效


        provinceWheel.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener<Province>() {
            @Override
            public void onWheelSelected(Province item, int position) {
                province = item;
                provinceChange(item.getProvinceId());
            }
        });
        cityWhel.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener<City>() {
            @Override
            public void onWheelSelected(City item, int position) {
                city = item;
                cityChange(item.getCityId());
            }
        });
        coutryWhell.setOnWheelChangeListener(new WheelPicker.OnWheelChangeListener<District>() {
            @Override
            public void onWheelSelected(District item, int position) {
                district = item;
            }
        });
        initData();
    }

    Observable<Result<List<Province>>> provincesOberable;

    public void initData() {
        if (provinces.size() == 0) {
            provincesOberable = MyApplication.retrofitClient.getRetrofit().create(Api.class)
                    .getAllProvince().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());

            provincesOberable.subscribe(new Observer<Result<List<Province>>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Result<List<Province>> listResult) {
                    if (!listResult.getCode().equals("200")) {
                        throw new RuntimeException("");
                    }
                    provinces.clear();
                    provinces.addAll(listResult.getResult());
                    provinceWheel.setDataList(provinces);
                    provinceChange(provinces.get(0).getProvinceId());
                }

                @Override
                public void onError(Throwable e) {
                    cancel();
                }

                @Override
                public void onComplete() {

                }
            });
        }else{
            provinceWheel.setDataList(provinces);
            provinceChange(provinces.get(0).getProvinceId());
        }
    }

    Observable<Result<List<District>>> districtObservable;

    public void provinceChange(int code) {
        if (districtObservable != null) {
            districtObservable.unsubscribeOn(AndroidSchedulers.mainThread());
        }
        cities.clear();
        districts.clear();
        districtObservable = MyApplication.retrofitClient.getRetrofit().create(Api.class)
                .getAllCity(code).flatMap(new Function<Result<List<City>>, ObservableSource<Result<List<District>>>>() {

                    @Override
                    public ObservableSource<Result<List<District>>> apply(Result<List<City>> listResult) throws Exception {
                        if (!listResult.getCode().equals("200")) {
                            throw new Exception("获取城市列表出错");
                        }

                        cities.addAll(listResult.getResult());
                        return MyApplication.retrofitClient.getRetrofit().create(Api.class).getAllDistrict(listResult.getResult().get(0).getCityId());
                    }
                });

        districtObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<List<District>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<List<District>> listResult) {
                        if (!listResult.getCode().equals("200")) {
                            cancel();
                            return;
                        }
                        districts.addAll(listResult.getResult());
                        cityWhel.setDataList(cities);
                        coutryWhell.setDataList(districts);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void cityChange(int code) {
        districts.clear();
        if (districtObservable != null) {
            districtObservable.unsubscribeOn(AndroidSchedulers.mainThread());
        }
        districtObservable = MyApplication.retrofitClient.getRetrofit().create(Api.class).getAllDistrict(code)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        districtObservable.subscribe(new Observer<Result<List<District>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<List<District>> listResult) {
                if (!listResult.getCode().equals("200")) {
                    cancel();
                    return;
                }
                districts.addAll(listResult.getResult());
                coutryWhell.setDataList(districts);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    OnSiteComfirmListener onSiteComfirmListener;

    public void setOnSiteComfirmListener(OnSiteComfirmListener onSiteComfirmListener) {
        this.onSiteComfirmListener = onSiteComfirmListener;
    }

    public interface OnSiteComfirmListener{
        void onConfimr(String addrss,int provinceCode,int cityCode,int disCode);
    }
}
