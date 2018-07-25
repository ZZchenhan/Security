package xxk.wuhai.seacurity.work.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import sz.tianhe.baselib.weight.ProgrossDialog;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.CommonNavagation;
import xxk.wuhai.seacurity.databinding.ActivityCulBinding;
import xxk.wuhai.seacurity.oss.PutObjectSamples;
import xxk.wuhai.seacurity.weight.dialog.ActionSheetDialog;
import xxk.wuhai.seacurity.weight.record.AudioRecorder;
import xxk.wuhai.seacurity.weight.record.RecordButton;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.vo.AddClueBurstVo;

/**
 * 线索爆料添加页面
 */
public class CulActivity extends BaseActivity implements AMapLocationListener {

    private String voiceUrl;

    ActivityCulBinding binding;

    @Override
    public int layoutId() {
        return R.layout.activity_cul;
    }

    @Override
    protected View databindViews() {
        binding=  DataBindingUtil.inflate(LayoutInflater.from(this),layoutId(),null,false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.mapview.onCreate(savedInstanceState);
        startLocaion();
        binding.mapview.getMap().getUiSettings().setZoomControlsEnabled(false);
        binding.mapview.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            ViewGroup child = (ViewGroup)  binding.mapview.getChildAt(0);//地图框架
            child.getChildAt(2).setVisibility(View.GONE);//logo
        });
    }

    @Override
    protected void onResume() {
        if(binding.mapview!=null){
            binding.mapview.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if(binding.mapview!=null){
            binding.mapview.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(binding.mapview!=null){
            binding.mapview.onDestroy();
            mlocationClient.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public IBaseNavagation navagation() {
        CommonNavagation leftIconNavagation = (CommonNavagation) new CommonNavagation(this) {
            @Override
            public String title() {
                return "线索爆料";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(view -> finish());
        leftIconNavagation.setTitleColor(R.color.white);
        leftIconNavagation.setRightImageResouce(R.mipmap.ic_cul_history);
        leftIconNavagation.setRight("");
        leftIconNavagation.setRightOnclickListner(view -> CulListActivity.openActivity(CulActivity.this,CulListActivity.class));
        return leftIconNavagation;
    }

    @Override
    public void initView() {

    }

    ActionSheetDialog actionSheetDialog;
    @Override
    public void findViews() {
        binding.ivCamero.setOnClickListener(view -> {
            if(imagesUrl.size()>=3){
                toast("最多只能选择三张图片");
                return;
            }
            choosePic();
        });
        mediaPlayer = new MediaPlayer();
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(progrossDialog == null){
                        progrossDialog = new ProgrossDialog(CulActivity.this);
                    }
                    progrossDialog.show();
                    submit();
                } catch (Exception e) {
                    toast(e.getMessage());
                    if(progrossDialog!=null){
                        progrossDialog.dismiss();
                    }
                }
            }
        });
        binding.recordVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.recordVoice.setAudioRecord(new AudioRecorder());
        binding.recordVoice.setRecordStatusListener(new RecordButton.RecordStatusListener() {
            @Override
            public void status(int status) {
                switch (status) {
                    case RecordButton.RECORD_OFF:  //RECORD_OFF:0
                        setVoiceShow(true, false, false);
                        break;
                    case RecordButton.RECORD_ON: // RECORD_ON:1
                        setVoiceShow(false, true, false);
                        break;
                    case RecordButton.RECORD_Done:  //RECORD_Done:2
                        String filePath = binding.recordVoice.getFilePath();

                        //写入sd卡后刷新显示
                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        intent.setData(Uri.fromFile(new File(filePath)));
                        sendBroadcast(intent);
                        try {
                            mediaPlayer.reset();
                            mediaPlayer.setDataSource( binding.recordVoice.getFilePath());
                            mediaPlayer.prepare();
                            if (mediaPlayer.getDuration() / 1000 > 0) {
                                  voiceUrl = binding.recordVoice.getFilePath();
                                  refreRidioView();
                            } else {
                                setVoiceShow(true, false, false);
                            }
                        } catch (IOException e) {
                            setVoiceShow(true, false, false);
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });
    }

    private void choosePic() {
        ActionSheetDialog dialog = new ActionSheetDialog(this).builder()
                .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue, which -> PictureSelector.create(CulActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(3-imagesUrl.size())
                        .enableCrop(true)
                        .withAspectRatio(1, 1)
                        .isCamera(false)
                        .forResult(PictureConfig.CHOOSE_REQUEST))
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, which -> PictureSelector.create(CulActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .enableCrop(true)
                        .withAspectRatio(1, 1)
                        .forResult(PictureConfig.CHOOSE_REQUEST))
                .setCancelable(true);
        dialog.show();
    }
    ProgrossDialog progrossDialog;
    private void submit() throws Exception{
        if(binding.content.getText().toString().length() == 0){
            toast("请输入爆料内容");
            if(progrossDialog!=null){
                progrossDialog.dismiss();
            }
            return;
        }
        if(binding.content.getText().length()>60){
            toast("爆料内容长度超过限制");
            if(progrossDialog!=null){
                progrossDialog.dismiss();
            }
            return;
        }
        AddClueBurstVo addClueBurstVo = new AddClueBurstVo();
        addClueBurstVo.setClueBurstContent(binding.content.getText().toString());
        addClueBurstVo.setAddress(binding.location.getText().toString());
        addClueBurstVo.setLatitude(currentLatLng == null?"0":currentLatLng.latitude+"");
        addClueBurstVo.setLongitude(currentLatLng == null?"0":currentLatLng.longitude+"");
        PutObjectSamples.upLoadFile(MyApplication.userDetailInfo.getUserInfo().getUserId()+System.currentTimeMillis()+"",voiceUrl)
                .flatMap(string -> {
                    addClueBurstVo.setTapeUrl(string);
                    return   PutObjectSamples.upLoadFils(imagesUrl);
                }).flatMap(strings -> {
                    addClueBurstVo.setPictureUrls(strings);
                    return    MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                            .addClueBurst(addClueBurstVo);
                })  .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> stringResult) {
                        if(progrossDialog!=null){
                            progrossDialog.dismiss();
                        }
                        if(!stringResult.getCode().equals("200")){
                            toast(stringResult.getMessage());
                            return;
                        }else{
                            toast("添加线索成功");
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(progrossDialog!=null){
                            progrossDialog.dismiss();
                        }
                        if(e == null){
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });;
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                // 图片选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                for(int i=0;i<selectList.size();i++) {
                    LocalMedia media = selectList.get(i);
                    String headImgPath = "";
                    if (media.isCut()) {
                        headImgPath = media.getCutPath();
                    } else {
                        headImgPath = media.getPath();
                    }
                    imagesUrl.add(headImgPath);
                }
                showImageUrl();
            }
        }
    }

    private void showImageUrl(){
        binding.ivDe1.setVisibility(View.GONE);
        binding.ivDe2.setVisibility(View.GONE);
        binding.ivDe3.setVisibility(View.GONE);
        binding.ivPic1.setImageResource(0);
        binding.ivPic2.setImageResource(0);
        binding.ivPic3.setImageResource(0);
        binding.ivDe1.setOnClickListener(view -> {
            imagesUrl.remove(0);
            showImageUrl();
        });
        binding.ivDe2.setOnClickListener(view -> {
            imagesUrl.remove(1);
            showImageUrl();
        });
        binding.ivDe3.setOnClickListener(view -> {
            imagesUrl.remove(2);
            showImageUrl();
        });

        if(imagesUrl.size() >=1){
            Glide.with(this)
                    .load(imagesUrl.get(0))
                    .apply(RequestOptions.placeholderOf(R.color.gray))
                    .into(binding.ivPic1);
            binding.ivDe1.setVisibility(View.VISIBLE);
        }

        if(imagesUrl.size() >=2){
            Glide.with(this)
                    .load(imagesUrl.get(1))
                    .apply(RequestOptions.placeholderOf(R.color.gray))
                    .into(binding.ivPic2);
            binding.ivDe2.setVisibility(View.VISIBLE);
        }

        if(imagesUrl.size()>=3){
            Glide.with(this)
                    .load(imagesUrl.get(2))
                    .apply(RequestOptions.placeholderOf(R.color.gray))
                    .into(binding.ivPic3);
            binding.ivDe3.setVisibility(View.VISIBLE);
        }
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
           // mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
            //指定位一次
           mLocationOption.setOnceLocation(true);
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
    Marker marker;
    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                currentLatLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.location.setText(aMapLocation.getAddress());
                        marker =  binding.mapview.getMap() .addMarker(new MarkerOptions().position(currentLatLng).title(aMapLocation.getPoiName()).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                .decodeResource(CulActivity.this.getResources(), R.mipmap.icon_poi_select))));
                        binding.mapview.getMap().moveCamera(  CameraUpdateFactory.newLatLngZoom(currentLatLng,17));
                    }
                });
            }
        }
    }



    /**
     * 图片地址
     */
    private List<String> imagesUrl = new ArrayList<>();

    private AnimationDrawable animator;
    private MediaPlayer mediaPlayer;
    private void refreRidioView(){
        if (!TextUtils.isEmpty(voiceUrl)) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(voiceUrl);
                mediaPlayer.setVolume(100f, 100f);
                mediaPlayer.prepare();
                mediaPlayer.getDuration();
                binding.ivRecode.setText((mediaPlayer.getDuration() / 1000)+2 + "\"");
                binding.ivRecode.setVisibility(View.VISIBLE);
                binding.lvPlayer.setVisibility(View.VISIBLE);
                binding.recordVoice.setVisibility(View.GONE);
                binding.lvPlayer.setOnClickListener(view -> {
                    animator = (AnimationDrawable) binding.voiceImg.getBackground();
                    animator.start();
                    mediaPlayer.start();
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        animator.stop();
                        animator.selectDrawable(0);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void setVoiceShow(boolean off, boolean on, boolean done) {

    }
}
