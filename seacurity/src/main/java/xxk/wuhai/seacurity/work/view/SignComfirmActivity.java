package xxk.wuhai.seacurity.work.view;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.amap.api.maps2d.model.LatLng;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.ObservableSource;
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
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.oss.PutObjectSamples;
import xxk.wuhai.seacurity.utils.SignUtils;
import xxk.wuhai.seacurity.weight.dialog.ActionSheetDialog;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.UserSignResult;
import xxk.wuhai.seacurity.work.vo.UserSignVo;

/**
 * 签到信息提交
 */
public class SignComfirmActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 时间
     */
    private TextView tvTime;

    /**
     * 地址
     */
    private TextView tvAddress;

    /**
     * 备注消息
     */
    private TextView tvExtra;

    /**
     * 拍照按钮
     */
    private ImageView ivCamero;


    private ImageView pic1;

    private ImageView pic2;

    private ImageView pic3;

    private ImageView ivDe1;
    private ImageView ivDe2;
    private ImageView ivDe3;

    /**
     * 提交
     */
    private TextView btnConfirm;

    /**
     * 图片地址
     */
    private List<String> imagesUrl = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.activity_sign_comfirm;
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
        tvTime.setText("签到时间:" + time);
        tvAddress.setText("签到地址:" + adrress);
    }

    @Override
    public void findViews() {
        tvTime = findViewById(R.id.date);
        tvAddress = findViewById(R.id.adress);

        tvExtra = findViewById(R.id.extra);
        ivCamero = findViewById(R.id.iv_camero);
        pic1 = findViewById(R.id.iv_pic1);
        pic2 = findViewById(R.id.iv_pic2);
        pic3 = findViewById(R.id.iv_pic3);

        ivDe1 = findViewById(R.id.iv_de1);
        ivDe2 = findViewById(R.id.iv_de2);
        ivDe3 = findViewById(R.id.iv_de3);
        btnConfirm = findViewById(R.id.btn_confirm);

        ivCamero.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    private static String adrress;
    private static String time;
    private static LatLng latlng;

    public static void openActivity(Activity activity, String adrress, String time, LatLng latLng) {
        SignComfirmActivity.adrress = adrress;
        SignComfirmActivity.time = time;
        SignComfirmActivity.latlng = latLng;
        openActivity(activity, SignComfirmActivity.class, 1);
    }

    String path = Environment.getExternalStorageDirectory() +
            File.separator + Environment.DIRECTORY_DCIM + File.separator;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_camero:
                if (imagesUrl.size() >= 3) {
                    toast("最多只能拍三张招片");
                    return;
                }
                choosePic();
                break;
            case R.id.btn_confirm:
                if(progrossDialog == null){
                    progrossDialog = new ProgrossDialog(SignComfirmActivity.this);
                }
                progrossDialog.show();
                submit();
                break;
        }
    }

    private void choosePic() {
        ActionSheetDialog dialog = new ActionSheetDialog(this).builder()
                .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue, which -> PictureSelector.create(SignComfirmActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(3-imagesUrl.size())
                        .enableCrop(true)
                        .withAspectRatio(1, 1)
                        .isCamera(false)
                        .forResult(PictureConfig.CHOOSE_REQUEST))
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, which -> PictureSelector.create(SignComfirmActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .enableCrop(true)
                        .withAspectRatio(1, 1)
                        .forResult(PictureConfig.CHOOSE_REQUEST))
                .setCancelable(true);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
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
        ivDe1.setVisibility(View.GONE);
        ivDe2.setVisibility(View.GONE);
        ivDe3.setVisibility(View.GONE);
        pic1.setImageResource(0);
        pic2.setImageResource(0);
        pic3.setImageResource(0);
        ivDe1.setOnClickListener(view -> {
            imagesUrl.remove(0);
            showImageUrl();
        });
        ivDe2.setOnClickListener(view -> {
            imagesUrl.remove(1);
            showImageUrl();
        });
        ivDe3.setOnClickListener(view -> {
            imagesUrl.remove(2);
            showImageUrl();
        });

        if(imagesUrl.size() >=1){
            Glide.with(this)
                    .load(imagesUrl.get(0))
                    .apply(RequestOptions.placeholderOf(R.color.gray))
                    .into(pic1);
            ivDe1.setVisibility(View.VISIBLE);
        }

        if(imagesUrl.size() >=2){
            Glide.with(this)
                    .load(imagesUrl.get(1))
                    .apply(RequestOptions.placeholderOf(R.color.gray))
                    .into(pic2);
            ivDe2.setVisibility(View.VISIBLE);
        }

        if(imagesUrl.size()>=3){
            Glide.with(this)
                    .load(imagesUrl.get(2))
                    .apply(RequestOptions.placeholderOf(R.color.gray))
                    .into(pic3);
            ivDe3.setVisibility(View.VISIBLE);
        }
    }

    private File tempFile;
    private Uri imageUri;

    public void openCamera(Activity activity) {
        //獲取系統版本
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // 激活相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
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
                    Toast.makeText(this, "请开启存储权限", Toast.LENGTH_SHORT).show();
                    return;
                }
                imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        activity.startActivityForResult(intent, 1);
    }

    ProgrossDialog progrossDialog;
    private void submit() {
        if (tvExtra.getText().toString().length() == 0 && imagesUrl.size()==0) {
            toast("图片和备注选填");
            if(progrossDialog!=null){
                progrossDialog.dismiss();
            }
            return;
        }
        UserSignVo signVo = new UserSignVo();
        signVo.setPoiName(adrress);
        signVo.setAttendanceLat(latlng!=null?latlng.latitude + "":"0");
        signVo.setAttendanceLon(latlng!=null?latlng.longitude + "":"0");
        signVo.setAttendanceLocation(adrress);
        signVo.setRemark(tvExtra.getText().toString());

        PutObjectSamples.upLoadFils(imagesUrl)
                .flatMap(strings -> {
                    signVo.setImageUrls(strings);
                    return    MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                            .userSign(signVo);
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<UserSignResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserSignResult> userSignResultResult) {
                        if(progrossDialog!=null){
                            progrossDialog.dismiss();
                        }
                            if(!userSignResultResult.getCode().equals("200")){
                                toast(userSignResultResult.getMessage());
                                return;
                            }
                            if(userSignResultResult.getResult().getStatus() == null
                                    || userSignResultResult.getResult().getStatus().equals("0")){
                                toast("签到失败");
                                RecordFaileActivity.openActivity(SignComfirmActivity.this, RecordFaileActivity.class);
                                finish();
                            }else{
                                toast("签到成功");
                                SignUtils.add(System.currentTimeMillis(),SignComfirmActivity.this);
                                finish();
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(progrossDialog!=null){
                            progrossDialog.dismiss();
                        }
                        if(e!=null){
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
