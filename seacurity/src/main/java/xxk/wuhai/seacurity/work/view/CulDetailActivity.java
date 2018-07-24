package xxk.wuhai.seacurity.work.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.CommonNavagation;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.databinding.ActivityApplyLeaveBinding;
import xxk.wuhai.seacurity.databinding.ActivityCulDetailBinding;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.ClueBurstDetailResult;
import xxk.wuhai.seacurity.work.vo.GetClueBurstDetailsVo;

public class CulDetailActivity extends BaseActivity {

    ActivityCulDetailBinding binding;
    MediaPlayer mediaPlayer;
    private AnimationDrawable animator;


    @Override
    public int layoutId() {
        return R.layout.activity_cul_detail;
    }

    @Override
    protected View databindViews() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this),layoutId(),null,false);
        return binding.getRoot();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.mapview.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        binding.mapview.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        binding.mapview.onPause();
        super.onPause();
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "我提交的爆料";
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

    }

    @Override
    public void findViews() {
        binding.lvPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animator = (AnimationDrawable) binding.voiceImg.getBackground();
                animator.start();
                mediaPlayer.start();
            }
        });
        initData();
        binding.mapview.getMap().getUiSettings().setZoomControlsEnabled(false);
    }

    public static void openActivity(Context context,int id){
        context.startActivity(new Intent(context,CulDetailActivity.class).putExtra("id",id));
    }
    private void initData(){
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .clueBurstDetails(new GetClueBurstDetailsVo(getIntent().getIntExtra("id",0)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<ClueBurstDetailResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ClueBurstDetailResult> clueBurstDetailResultResult) {
                            if(!clueBurstDetailResultResult.getCode().equals("200")){
                                toast(clueBurstDetailResultResult.getMessage());
                                return;
                            }
                        binding.summary.setText(clueBurstDetailResultResult.getResult().getClueBurstDetail().getClueBurstContent());
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                animator.stop();
                                animator.selectDrawable(0);
                            }
                        });
                        if(clueBurstDetailResultResult.getResult().getClueBurstDetail().getTapeUrl()
                                != null && clueBurstDetailResultResult.getResult().getClueBurstDetail().getTapeUrl().length()>0) {
                           try {
                               mediaPlayer.reset();
                               mediaPlayer.setDataSource(clueBurstDetailResultResult.getResult().getClueBurstDetail().getTapeUrl());
                               mediaPlayer.setVolume(100f, 100f);
                               mediaPlayer.prepare();
                               mediaPlayer.getDuration();
                               binding.ivRecode.setText((mediaPlayer.getDuration() / 1000)+2 + "\"");
                           }catch (IOException e){}
                        }else{
                            binding.ivRecode.setText("无录音");
                            binding.lvPlayer.setVisibility(View.GONE);
                        }
                        binding.location.setText(clueBurstDetailResultResult.getResult().getClueBurstDetail().getAddress()
                                ==null?"":clueBurstDetailResultResult.getResult().getClueBurstDetail().getAddress());

                        try{
                            if(clueBurstDetailResultResult.getResult().getClueBurstDetail().getPictureUrls() == null || clueBurstDetailResultResult.getResult().getClueBurstDetail().getPictureUrls().size() == 0){
                                binding.noPic.setVisibility(View.VISIBLE);
                                binding.llPicc.setVisibility(View.GONE);
                            }else {
                                Glide.with(CulDetailActivity.this)
                                        .load(clueBurstDetailResultResult.getResult().getClueBurstDetail().getPictureUrls().get(0))
                                        .into(binding.ivPic1);
                                Glide.with(CulDetailActivity.this)
                                        .load(clueBurstDetailResultResult.getResult().getClueBurstDetail().getPictureUrls().get(1))
                                        .into(binding.ivPic2);
                                Glide.with(CulDetailActivity.this)
                                        .load(clueBurstDetailResultResult.getResult().getClueBurstDetail().getPictureUrls().get(2))
                                        .into(binding.ivPic3);
                            }
                        }catch (Exception e){

                        }

                        try {
                            LatLng latLng = new LatLng(Double.parseDouble(clueBurstDetailResultResult.getResult().getClueBurstDetail().getLatitude()),
                                    Double.parseDouble(clueBurstDetailResultResult.getResult().getClueBurstDetail().getLongitude()));
                            Marker marker = binding.mapview.getMap().addMarker(new MarkerOptions().position(latLng)
                                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                    .decodeResource(CulDetailActivity.this.getResources(), R.mipmap.icon_poi_select))));
                            binding.mapview.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                        }catch (Exception e){
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e != null){
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
