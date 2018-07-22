package xxk.wuhai.seacurity.work.view;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
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
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.databinding.ActivitySupplementSignBinding;
import xxk.wuhai.seacurity.oss.PutObjectSamples;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.ApproverUser;
import xxk.wuhai.seacurity.work.vo.ApplyLeaveVo;
import xxk.wuhai.seacurity.work.vo.GetApproverVo;
import xxk.wuhai.seacurity.work.vo.SupplementApplyVo;

/**
 * 描述
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/26 23:40
 */
public class SupplementSignActivity extends BaseActivity{


    /**
     * 图片地址
     */
    private List<String> imagesUrl = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.activity_supplement_sign;
    }
    ActivitySupplementSignBinding binding;
    @Override
    protected View databindViews() {
        binding =  DataBindingUtil.inflate(LayoutInflater.from(this),layoutId(),null,false);
        return binding.getRoot();
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "补签审批";
            }
        };
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        return leftIconNavagation;
    }
    private String date = "";
    @Override
    public void initView() {
        getAppro();
        binding.tvTime.setText(getIntent().getStringExtra("time"));
        date = getIntent().getStringExtra("time");
        binding.etName.setText(MyApplication.userDetailInfo.getUserInfo().getName());
        binding.ivCamero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imagesUrl.size()>=3){
                    toast("最多只能拍三张招片");
                    return;
                }
                openCamera(SupplementSignActivity.this);
            }
        });
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subimit();
            }
        });
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
                        .into(binding.ivPic1);
            }

            if(imagesUrl.size() == 2){
                Glide.with(this)
                        .load(imagesUrl.get(1))
                        .apply(RequestOptions.placeholderOf(R.color.gray))
                        .into(binding.ivPic2);
            }

            if(imagesUrl.size() == 3){
                Glide.with(this)
                        .load(imagesUrl.get(2))
                        .apply(RequestOptions.placeholderOf(R.color.gray))
                        .into(binding.ivPic3);
            }

        }
    }

    @Override
    public void findViews() {

    }


    private void subimit(){
        if(binding.result.getText().length() == 0){
            toast("请输入补签原因");
            return;
        }

        SupplementApplyVo supplementApplyVo = new SupplementApplyVo();
        supplementApplyVo.setApproverID(approverUser != null && approverUser.getApproverId() !=0
                ?approverUser.getApproverId():
                MyApplication.userDetailInfo.getUserInfo().getUserId());
        supplementApplyVo.setAttendanceId(getIntent().getStringExtra("id"));
        supplementApplyVo.setPatchTime(date);
        supplementApplyVo.setSupplement(binding.result.getText().toString());
        List<String> subImags = new ArrayList<>();
        for(int i=0;i<imagesUrl.size();i++){
            String objName = MyApplication.userDetailInfo.getUserInfo().getUserId()+System.currentTimeMillis()+"";
            subImags.add(MyApplication.aluyun+objName);
            PutObjectSamples putObjectSamples = new PutObjectSamples(MyApplication.oss,objName,imagesUrl.get(i));
            try {
                putObjectSamples.putObjectFromLocalFile();
            } catch (ClientException e) {
                e.printStackTrace();
                toast(e.getMessage());
                return;
            } catch (ServiceException e) {
                e.printStackTrace();
                toast(e.getMessage());
                return;
            }catch (Exception e){
                toast(e.getMessage());
                return;
            }
        }
        supplementApplyVo.setPaPictureUrls(subImags);
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .supplementApply(supplementApplyVo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> stringResult) {
                        if(stringResult.getCode().equals("200")){
                            toast("提交申请成功");
                            finish();
                            return;
                        }else{
                            toast(stringResult.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e!=null){
                           toast(e.getMessage());
                            return;
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    ApproverUser approverUser;
    private void getAppro(){
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class).getApprover(new GetApproverVo(MyApplication.userDetailInfo.getUserInfo().getUserId())).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<ApproverUser>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ApproverUser> approverUserResult) {
                        if(!approverUserResult.getCode().equals("200")){
                            toast(approverUserResult.getMessage());
                            return;
                        }
                        approverUser = approverUserResult.getResult();
                        binding.approve.setText(approverUserResult.getResult() == null || approverUserResult.getResult().getApproverId() == 0 ?
                                MyApplication.userDetailInfo.getUserInfo().getName():approverUserResult.getResult().getApproverName());
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
