package xxk.wuhai.seacurity.main.view;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.blankj.utilcode.util.ToastUtils;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;
import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftTitleNavagation;
import xxk.wuhai.seacurity.contact.view.ContactFragment;
import xxk.wuhai.seacurity.login.api.UserApi;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.view.LoginActivity;
import xxk.wuhai.seacurity.login.vo.GetUserInfoVo;
import xxk.wuhai.seacurity.main.view.custorm.MyNormalItem;
import xxk.wuhai.seacurity.main.view.custorm.UpdateUtils;
import xxk.wuhai.seacurity.me.view.MeFragment;
import xxk.wuhai.seacurity.msg.view.MsgFragment;
import xxk.wuhai.seacurity.utils.ShareUtlts;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.RecordBean;
import xxk.wuhai.seacurity.work.view.ApplyActivity;
import xxk.wuhai.seacurity.work.view.CulActivity;
import xxk.wuhai.seacurity.work.view.CulListActivity;
import xxk.wuhai.seacurity.work.view.MyDutyActivity;
import xxk.wuhai.seacurity.work.view.RecordActivity;
import xxk.wuhai.seacurity.work.view.SignActivity;
import xxk.wuhai.seacurity.work.view.StudyActivity;
import xxk.wuhai.seacurity.work.view.SuggestActivity;
import xxk.wuhai.seacurity.work.view.SystemSettinActivity;
import xxk.wuhai.seacurity.work.vo.UploadTrajectoryVo;

public class MainActivity extends BaseActivity implements OnTabItemSelectedListener,AMapLocationListener {

    private String[] tabTitle = new String[]{"工作台","消息","通讯录","我的"};

    private NavigationView nav_view;

    private TextView tvName1;
    private TextView tvName2;
    private TextView tvPhone;

    DrawerLayout drawerLayout;
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

    @Override
    public IBaseNavagation navagation() {
       return null;
    }

    @Override
    public void initView() {
        initBottom();
        initFragment();
//        new UpdateUtils(this).chekVersion();
    }

    @Override
    public void findViews() {
        fragmentContainer = findViewById(R.id.fragment_container);
        tab = findViewById(R.id.tab);
        drawerLayout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
       View headerView =  nav_view.getHeaderView(0);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
       headerView.setLayoutParams(params);
        headerView.findViewById(R.id.sugest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SuggestActivity.class));
                openOrClose();
            }
        });
        headerView.findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,SystemSettinActivity.class));
                openOrClose();
            }
        });
        headerView.findViewById(R.id.login_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        tvName1 = headerView.findViewById(R.id.person_icon);
        tvName2 = headerView.findViewById(R.id.user_name);
        tvPhone = headerView.findViewById(R.id.phone);
        try {
            tvName1.setText(MyApplication.userDetailInfo.getUserInfo().getName().length() > 2 ? MyApplication.userDetailInfo.getUserInfo().getName().substring(0, 2) : MyApplication.userDetailInfo.getUserInfo().getName());
            tvName2.setText(MyApplication.userDetailInfo.getUserInfo().getName());
            tvPhone.setText(MyApplication.userDetailInfo.getUserInfo().getPhone());
        }catch (Exception e){}
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
                CulActivity.openActivity(this,CulActivity.class);
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void openOrClose(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
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

    public void logout(){
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .logout(new GetUserInfoVo()).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<RecordBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RecordBean> recordBeanResult) {
                        if(!recordBeanResult.getCode().equals("200")){
                            toast(recordBeanResult.getMessage());
                            return;
                        }
                        if(recordBeanResult.getResult().getStatus().equals("1")){
                            ShareUtlts.clean(MainActivity.this);
                            BaseInterceptor.token = "";
                            BaseInterceptor.name = "";
                            BaseInterceptor.random = "";
                            JPushInterface.deleteAlias(getApplicationContext(),1);
                            Set<String> tags = new HashSet<>();
                            tags.add(MyApplication.userDetailInfo.getDeptVo().getOrgId()+"");
                            tags.add(MyApplication.userDetailInfo.getDeptVo().getDeptId()+"");
                            JPushInterface.deleteTags(getApplicationContext(),1,tags);
                            startActivity(new Intent(MainActivity.this,LoginActivity.class));
                            finish();
                        }else{
                            toast("注销失败");
                        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == RESULT_OK){
            MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                    .getUserInfo(new GetUserInfoVo())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<xxk.wuhai.seacurity.bean.Result<UserDetailInfo>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(xxk.wuhai.seacurity.bean.Result<UserDetailInfo> result) {
                            if(result.getCode().equals("200")){
                                MyApplication.userDetailInfo = result.getResult();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            ToastUtils.showShort(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            return;
        }
    }
}
