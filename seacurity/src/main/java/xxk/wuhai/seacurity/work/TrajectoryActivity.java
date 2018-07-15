package xxk.wuhai.seacurity.work;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.view.GetTrajectoryResponse;
import xxk.wuhai.seacurity.work.vo.GetTrajectoryVo;

public class TrajectoryActivity extends BaseActivity {
    private MapView mapView;
    private TextView tvStart;
    private TextView tvEnd;
    @Override
    public int layoutId() {
        return R.layout.activity_trajectory;
    }
    LeftIconNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
         leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
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
        leftIconNavagation.setTvTitle(getIntent().getStringExtra("time"));
        getData();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onPause();
        super.onDestroy();
    }

    @Override
    public void findViews() {
        mapView = findViewById(R.id.map);
        tvStart = findViewById(R.id.start);
        tvEnd = findViewById(R.id.end);
    }


    public void getData(){
        MyApplication.retrofitClient.getRetrofit()
                .create(WorkDutyApi.class)
                .getTrajectory(new GetTrajectoryVo(getIntent().getStringExtra("time"),MyApplication.userDetailInfo.getUserInfo().getUserId()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<GetTrajectoryResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GetTrajectoryResponse> getTrajectoryResponseResult) {
                            if(!getTrajectoryResponseResult.getResult().equals("200")){
                                toast(getTrajectoryResponseResult.getMessage());
                                return;
                            }
                            if(getTrajectoryResponseResult.getResult().getTrajectoryVoList() == null
                                    || getTrajectoryResponseResult.getResult().getTrajectoryVoList().size() == 0){
                                toast("没有更多轨迹");
                                return;
                            }


                        List<LatLng> latLngs = new ArrayList<LatLng>();
                            for(GetTrajectoryResponse.TrajectoryVoListBean item:getTrajectoryResponseResult.getResult().getTrajectoryVoList()){
                                try {
                                    latLngs.add(new LatLng(Long.parseLong(item.getAttendanceLat()), Long.parseLong(item.getAttendanceLon())));
                                }catch (Exception e){}
                            }
                            mapView.getMap().clear();
                        mapView.getMap().addPolyline(new PolylineOptions().
                                addAll(latLngs).width(10).color(TrajectoryActivity.this.getResources().getColor(R.color.colorPrimary)));
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
