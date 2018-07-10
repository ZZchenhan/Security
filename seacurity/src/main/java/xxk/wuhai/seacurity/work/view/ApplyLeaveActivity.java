package xxk.wuhai.seacurity.work.view;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.text.ParseException;
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
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.databinding.ActivityApplyLeaveBinding;
import xxk.wuhai.seacurity.oss.PutObjectSamples;
import xxk.wuhai.seacurity.weight.ApplyUsersWindows;
import xxk.wuhai.seacurity.weight.LevaeTypeWindows;
import xxk.wuhai.seacurity.weight.date.DatePickerDialogFragment;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.AplyUser;
import xxk.wuhai.seacurity.work.bean.AplyUserResult;
import xxk.wuhai.seacurity.work.bean.ApproverUser;
import xxk.wuhai.seacurity.work.vo.ApplyLeaveVo;

public class ApplyLeaveActivity extends BaseActivity {
    /**
     * 图片地址
     */
    private List<String> imagesUrl = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.activity_apply_leave;
    }

    List<AplyUser> aplyUserList = new ArrayList<>();

    ApplyUsersWindows aplyUsersDialog;
    LevaeTypeWindows levaeTypeWindows;


    ApproverUser approverUser;
    AplyUser aplyUser;//请假用户
    String leaveString="事假";
    String leaveTye="1";

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "请假审批";
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
        if(!(MyApplication.userDetailInfo.getUserInfo().getRelUserDeptOrgVo()!=null &&
                MyApplication.userDetailInfo.getUserInfo().getRelUserDeptOrgVo().getLevel() != null
                && MyApplication.userDetailInfo.getUserInfo().getRelUserDeptOrgVo().getLevel().equals("1"))){
            initData();
        }else{
            getAppro();
        }
        binding.name.setText(MyApplication.userDetailInfo.getUserInfo().getName());
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
                openCamera(ApplyLeaveActivity.this);
            }
        });
        binding.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(levaeTypeWindows == null){
                    levaeTypeWindows = new LevaeTypeWindows(ApplyLeaveActivity.this);
                    levaeTypeWindows.setOnItemClickListener(new LevaeTypeWindows.OnItemClickListener() {
                        @Override
                        public void onItemnClick(String string, String type) {
                            leaveString = string;
                            binding.type.setText(string);
                            leaveTye = type;
                            levaeTypeWindows.dismiss();
                        }
                    });
                    levaeTypeWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            darkenBackground(1f);
                        }
                    });
                }
                levaeTypeWindows.showAsDropDown(binding.type,0,0, Gravity.RIGHT);
                darkenBackground(0.4f);
            }
        });

        binding.startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
                datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
                    @Override
                    public void onDateChoose(int year, int month, int day) {
                        binding.startTime.setText(String.format("%02d-%02d-%02d",year,month,day));
                        cacultDays();
                    }
                });
                datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");
            }
        });

        binding.endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
                datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
                    @Override
                    public void onDateChoose(int year, int month, int day) {
                        binding.endTime.setText(String.format("%02d-%02d-%02d",year,month,day));
                        cacultDays();
                    }
                });
                datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");
            }
        });
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subimit();
            }
        });
    }

    ActivityApplyLeaveBinding binding;
    @Override
    protected View databindViews() {
         binding = DataBindingUtil.inflate(LayoutInflater.from(this),layoutId(),null,false);
        return binding.getRoot();
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


    private void initData(){
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getLeaverList().subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<Result<AplyUserResult>, ObservableSource<Result<ApproverUser>>>() {
                    @Override
                    public ObservableSource<Result<ApproverUser>> apply(Result<AplyUserResult> aplyUserResultResult) throws Exception {
                        if(!aplyUserResultResult.getCode().equals("200")){
                            toast(aplyUserResultResult.getMessage());
                            throw new NullPointerException("获取请假人错误");
                        }
                        aplyUserList.addAll(aplyUserResultResult.getResult().getLerverInfoList());

                        return  MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class).getApprover();
                    }
                })
         .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Result<ApproverUser>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final Result<ApproverUser> aplyUserResult) {
                if(!aplyUserResult.getCode().equals("200")){
                    toast(aplyUserResult.getMessage());
                    return;
                }
                Drawable drawable = ApplyLeaveActivity.this.getDrawable(R.mipmap.icon_leave_down_arrow);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                binding.name.setCompoundDrawables(null,null,drawable,null);

                binding.name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(aplyUsersDialog==null){
                            aplyUsersDialog = new ApplyUsersWindows(ApplyLeaveActivity.this);
                            aplyUsersDialog.setData(aplyUserList);
                            aplyUsersDialog.setOnItemClickListener(new ApplyUsersWindows.OnItemClickListener() {
                                @Override
                                public void onItemnClick(AplyUser aplyUser) {
                                    ApplyLeaveActivity.this.aplyUser = aplyUser;
                                    aplyUsersDialog.dismiss();
                                    binding.name.setText(aplyUser.getName());
                                }
                            });
                            aplyUsersDialog.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    darkenBackground(1f);
                                }
                            });
                        }
                        aplyUsersDialog.showAsDropDown(binding.name,0,0, Gravity.RIGHT);
                        darkenBackground(0.4f);
                    }
                });
                approverUser = aplyUserResult.getResult();
                binding.name1.setText(aplyUserResult.getResult() == null || aplyUserResult.getResult().getApproverId() == 0
                        ?MyApplication.userDetailInfo.getUserInfo().getName():aplyUserResult.getResult().getApproverName());
            }

            @Override
            public void onError(Throwable e) {
                if(e!= null){
                    toast(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void getAppro(){
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class).getApprover().subscribeOn(Schedulers.newThread())
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
                        binding.name1.setText(approverUserResult.getResult() == null || approverUserResult.getResult().getApproverId() == 0 ?
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

    private void darkenBackground(Float bgcolor){
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.alpha = bgcolor;
       getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
       getWindow().setAttributes(lp);
    }


    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private void cacultDays(){
        if(binding.startTime.getText().toString().length()==0 || binding.endTime.getText().toString().length()==0){
            return;
        }
        try {
            Date start = simpleDateFormat.parse(binding.startTime.getText().toString());
            Date end = simpleDateFormat.parse(binding.endTime.getText().toString());
            int days = (int) (end.getTime() - start.getTime())/(1000*60*60*24)+1;
            binding.days.setText(days+"");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void subimit(){
        if(binding.days.getText().toString().equals("")){
            toast("请选择开始结束时间");
            return;
        }
        if(Integer.parseInt(binding.days.getText().toString())<0){
            toast("请设置正确的请假时间");
            return;
        }
        ApplyLeaveVo applyLeaveVo = new ApplyLeaveVo();
        applyLeaveVo.setApproverID(approverUser == null || approverUser.getApproverId() == 0?MyApplication.userDetailInfo.getUserInfo().getUserId():approverUser.getApproverId());
        applyLeaveVo.setLeaveRequestBeginTime(binding.startTime.getText().toString());
        applyLeaveVo.setLeaveRequestDays(Integer.parseInt(binding.days.getText().toString()));
        applyLeaveVo.setLeaveRequestEndTime(binding.endTime.getText().toString());
        applyLeaveVo.setLeaveRequestTypeId(leaveTye);
        applyLeaveVo.setSupplement(binding.result.getText().toString());
        applyLeaveVo.setLeaveUserId(aplyUser == null?MyApplication.userDetailInfo.getUserInfo().getUserId()+"":aplyUser.getUserId()+"");
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
        applyLeaveVo.setLrPictureUrls(subImags);

        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .applyLeave(applyLeaveVo)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Result<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<String> stringResult) {
                if(stringResult.getCode().equals("200")){
                    toast("提交成功");
                    finish();
                }else{
                    toast(stringResult.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
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
