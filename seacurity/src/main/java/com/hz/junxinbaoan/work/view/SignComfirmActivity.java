package com.hz.junxinbaoan.work.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps2d.model.LatLng;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import sz.tianhe.baselib.weight.ProgrossDialog;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;
import com.hz.junxinbaoan.oss.PutObjectSamples;
import com.hz.junxinbaoan.utils.SignUtils;
import com.hz.junxinbaoan.weight.dialog.ActionSheetDialog;
import com.hz.junxinbaoan.weight.photoview.PhoneDialog;
import com.hz.junxinbaoan.work.api.WorkDutyApi;
import com.hz.junxinbaoan.work.bean.UserSignResult;
import com.hz.junxinbaoan.work.vo.UserSignVo;

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
            pic1.setOnClickListener(view -> {
                if(imagesUrl.size()>0) {
                    PhoneDialog.seePic(SignComfirmActivity.this, imagesUrl, 0);
                }
            });
            ivDe1.setVisibility(View.VISIBLE);
        }

        if(imagesUrl.size() >=2){
            Glide.with(this)
                    .load(imagesUrl.get(1))
                    .apply(RequestOptions.placeholderOf(R.color.gray))
                    .into(pic2);
            ivDe2.setVisibility(View.VISIBLE);
            pic2.setOnClickListener(view -> {
                if(imagesUrl.size()>1) {
                    PhoneDialog.seePic(SignComfirmActivity.this, imagesUrl, 1);
                }
            });
        }

        if(imagesUrl.size()>=3){
            Glide.with(this)
                    .load(imagesUrl.get(2))
                    .apply(RequestOptions.placeholderOf(R.color.gray))
                    .into(pic3);
            ivDe3.setVisibility(View.VISIBLE);
            pic3.setOnClickListener(view -> {
                if(imagesUrl.size()>2) {
                    PhoneDialog.seePic(SignComfirmActivity.this, imagesUrl, 2);
                }
            });
        }
    }


    ProgrossDialog progrossDialog;
    private void submit() {
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
