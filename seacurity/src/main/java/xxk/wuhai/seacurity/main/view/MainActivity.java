package xxk.wuhai.seacurity.main.view;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

import io.reactivex.schedulers.Schedulers;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftTitleNavagation;
import xxk.wuhai.seacurity.contact.view.ContactFragment;
import xxk.wuhai.seacurity.main.view.custorm.MyNormalItem;
import xxk.wuhai.seacurity.me.view.MeFragment;
import xxk.wuhai.seacurity.msg.view.MsgFragment;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.view.ApplyActivity;
import xxk.wuhai.seacurity.work.view.CulActivity;
import xxk.wuhai.seacurity.work.view.CulListActivity;
import xxk.wuhai.seacurity.work.view.MyDutyActivity;
import xxk.wuhai.seacurity.work.view.RecordActivity;
import xxk.wuhai.seacurity.work.view.SignActivity;
import xxk.wuhai.seacurity.work.view.StudyActivity;
import xxk.wuhai.seacurity.work.vo.UploadTrajectoryVo;

public class MainActivity extends BaseActivity implements OnTabItemSelectedListener,AMapLocationListener {

    private String[] tabTitle = new String[]{"工作台","消息","通讯录","我的"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLocaion();
    }

    @Override
    protected void onDestroy() {
        mlocationClient.onDestroy();
        super.onDestroy();
    }

    FrameLayout fragmentContainer;
    PageNavigationView tab;
    private NavigationController controller;


    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment1 = new WorkFragment();
        transaction.add(R.id.fragment_container, fragment1, tabTitle[0]);
        transaction.commit();
    }


    private void initBottom() {
        controller = tab.custom()
                .addItem(newItem(R.mipmap.icon_work_nomarl, R.mipmap.icon_nomal_select, tabTitle[0]))
                .addItem(newItem(R.mipmap.icon_msg_normal, R.mipmap.icon_msg_select, tabTitle[1]))
                .addItem(newItem(R.mipmap.icon_contact_normal, R.mipmap.icon_contac_select, tabTitle[2]))
                .addItem(newItem(R.mipmap.icon_me_normal, R.mipmap.icon_me_select, tabTitle[3]))
                .build();
        controller.addTabItemSelectedListener(this);
    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text){
        MyNormalItem normalItemView = new MyNormalItem(this);
        normalItemView.initialize(drawable,checkedDrawable,text);
        normalItemView.setTextDefaultColor(0xff7F8389);
        normalItemView.setTextCheckedColor(0xff49B1FA);
        return normalItemView;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }
    LeftTitleNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
        return leftIconNavagation = new LeftTitleNavagation(this) {
            @Override
            public String title() {
                return "工作台";
            }
        };
    }

    @Override
    public void initView() {
        initBottom();
        initFragment();
    }

    @Override
    public void findViews() {
        fragmentContainer = findViewById(R.id.fragment_container);
        tab = findViewById(R.id.tab);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_sign:
                SignActivity.openActivity(this,SignActivity.class);
                break;
            case R.id.tv_work:
                RecordActivity.openActivity(this,RecordActivity.class);
                break;
            case R.id.tv_duty:
                MyDutyActivity.openActivity(this,MyDutyActivity.class);
                break;
            case R.id.tv_clue:
                CulListActivity.openActivity(this,CulListActivity.class);
                break;
            case R.id.tv_study:
                StudyActivity.openActivity(this,StudyActivity.class);
                break;
            case R.id.tv_apply:
                ApplyActivity.openActivity(this,ApplyActivity.class);
                break;
        }
    }

    @Override
    public void onSelected(int index, int old) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment oldFragment= fragmentManager.findFragmentByTag(tabTitle[old]);
        Fragment newFragment = fragmentManager.findFragmentByTag(tabTitle[index]);

        if(newFragment == null){
            if(index==0){
                newFragment = new WorkFragment();
            }
            if(index==1){
                newFragment = new MsgFragment();
            }
            if(index==2){
                newFragment = new ContactFragment();
            }
            if(index==3){
                newFragment = new MeFragment();
            }

            transaction.add(R.id.fragment_container, newFragment, tabTitle[index]);
        }
        leftIconNavagation.setTvTitle(tabTitle[index]);
        transaction.hide(oldFragment).show(newFragment);
        transaction.commit();
    }

    @Override
    public void onRepeat(int index) {

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
            mLocationOption.setInterval(1000*30);
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
    LatLng currentLatLng;

    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                currentLatLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                        .uploadTrajectory(new UploadTrajectoryVo(currentLatLng.latitude+"",
                                currentLatLng.longitude+""))
                .subscribeOn(Schedulers.newThread())
                .subscribe();
            }
        }
    }
}
