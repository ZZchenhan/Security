package xxk.wuhai.seacurity.work.view;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import xxk.wuhai.seacurity.common.navagation.CommonNavagation;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.databinding.ActivityCulBinding;
import xxk.wuhai.seacurity.oss.PutObjectSamples;
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
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftIconNavagation.setTitleColor(R.color.white);
        leftIconNavagation.setRightImageResouce(R.mipmap.ic_cul_history);
        leftIconNavagation.setRight("");
        leftIconNavagation.setRightOnclickListner(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CulListActivity.openActivity(CulActivity.this,CulListActivity.class);
            }
        });
        return leftIconNavagation;
    }

    @Override
    public void initView() {

    }

    @Override
    public void findViews() {
        binding.ivCamero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imagesUrl.size()>=3){
                    toast("最多只能拍三张招片");
                    return;
                }
                openCamera(CulActivity.this);
            }
        });
        mediaPlayer = new MediaPlayer();
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    submit();
                } catch (Exception e) {
                    toast(e.getMessage());
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

    private void submit() throws Exception{
        if(binding.content.getText().toString().length() == 0){
            toast("请输入爆料类容");
            return;
        }
        if(binding.content.getText().length()>60){
            toast("爆料内容长度超过限制");
            return;
        }
        AddClueBurstVo addClueBurstVo = new AddClueBurstVo();
        addClueBurstVo.setClueBurstContent(binding.content.getText().toString());
        addClueBurstVo.setAddress(binding.location.getText().toString());
        addClueBurstVo.setLatitude(currentLatLng == null?"0":currentLatLng.latitude+"");
        addClueBurstVo.setLongitude(currentLatLng == null?"0":currentLatLng.longitude+"");
        if(voiceUrl == null || voiceUrl.equals("")){

        }else{
            String objName = MyApplication.userDetailInfo.getUserInfo().getUserId()+System.currentTimeMillis()+"";
            PutObjectSamples putObjectSamples = new PutObjectSamples(MyApplication.oss,objName,voiceUrl);
            putObjectSamples.putObjectFromLocalFile();
            addClueBurstVo.setTapeUrl(MyApplication.aluyun+objName);
        }

        List<String> subImags = new ArrayList<>();
        for(int i=0;i<imagesUrl.size();i++){
            String objName = MyApplication.userDetailInfo.getUserInfo().getUserId()+System.currentTimeMillis()+""+i;
            subImags.add(MyApplication.aluyun+objName);
            PutObjectSamples putObjectSamples = new PutObjectSamples(MyApplication.oss,objName,imagesUrl.get(i));
            putObjectSamples.putObjectFromLocalFile();
        }
        addClueBurstVo.setPictureUrls(subImags);
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .addClueBurst(addClueBurstVo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> stringResult) {
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
                        if(e == null){
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    private void upLoadMp3(String path){

    }

    private File tempFile;
    private Uri imageUri;
    public void openCamera(Activity activity) {
        //獲取系統版本
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if ( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                    "yyyy_MM_dd_HH_mm_ss");
            String filename = timeStampFormat.format(new Date());
            tempFile = new File(Environment.getExternalStorageDirectory(),
                    filename + ".jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri
                imageUri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            } else {
                //兼容android7.0 使用共享文件的形式
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
                //检查是否有存储权限，以免崩溃
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    Toast.makeText(this,"请开启存储权限",Toast.LENGTH_SHORT).show();
                    return;
                }
                imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        activity.startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (resultCode == RESULT_OK && requestCode == 1) {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(imageUri, "image/*");
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, 2); // 启动裁剪程序
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            imagesUrl.add(tempFile.getAbsolutePath());
            if(imagesUrl.size() == 1){
                Glide.with(this)
                        .load(imagesUrl.get(0))
                        .apply(RequestOptions.placeholderOf(R.color.gray))
                        .into(binding.pic1);
            }

            if(imagesUrl.size() == 2){
                Glide.with(this)
                        .load(imagesUrl.get(1))
                        .apply(RequestOptions.placeholderOf(R.color.gray))
                        .into(binding.pic2);
            }

            if(imagesUrl.size() == 3){
                Glide.with(this)
                        .load(imagesUrl.get(2))
                        .apply(RequestOptions.placeholderOf(R.color.gray))
                        .into(binding.pic3);
            }

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
            mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
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
    Marker marker;
    @Override
    public void onLocationChanged(final AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                currentLatLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.location.setText(aMapLocation.getCity()+aMapLocation.getPoiName());
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
                binding.lvPlayer.setVisibility(View.VISIBLE);
                binding.recordVoice.setVisibility(View.GONE);
                binding.lvPlayer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        animator = (AnimationDrawable) binding.voiceImg.getBackground();
                        animator.start();
                        mediaPlayer.start();
                    }
                });
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        animator.stop();
                        binding.voiceImg.setBackgroundResource(R.drawable.voice_animation);
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
