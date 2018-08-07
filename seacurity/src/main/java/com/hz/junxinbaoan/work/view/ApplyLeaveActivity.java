package com.hz.junxinbaoan.work.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.hz.junxinbaoan.databinding.ActivityApplyLeaveBinding;
import com.hz.junxinbaoan.oss.PutObjectSamples;
import com.hz.junxinbaoan.weight.ApplyUsersWindows;
import com.hz.junxinbaoan.weight.LevaeTypeWindows;
import com.hz.junxinbaoan.weight.date.DatePickerDialogFragment;
import com.hz.junxinbaoan.weight.dialog.ActionSheetDialog;
import com.hz.junxinbaoan.work.api.WorkDutyApi;
import com.hz.junxinbaoan.work.bean.AplyUser;
import com.hz.junxinbaoan.work.bean.ApproverUser;
import com.hz.junxinbaoan.work.vo.ApplyLeaveVo;
import com.hz.junxinbaoan.work.vo.GetApproverVo;

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
                return "请假申请";
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
        if((MyApplication.userDetailInfo.getUserInfo().getRelUserDeptOrgVo()!=null &&
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
                choosePic();
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
                       Calendar calendar =  Calendar.getInstance();
                       if(calendar.get(Calendar.YEAR)>year){
                           toast("请假日期不能在当日之前");
                           return;
                       }
                       if(calendar.get(Calendar.MONTH)+1 > month){
                           toast("请假日期不能在当日之前");
                           return;
                       }
                        if(calendar.get(Calendar.DAY_OF_MONTH) > day){
                            toast("请假日期不能在当日之前");
                            return;
                        }
                        if(!binding.endTime.getText().equals("") && binding.endTime.getText().toString().compareTo(String.format("%02d-%02d-%02d",year,month,day))<0){
                            toast("开始日期不能小于结束日期");
                            return;
                        }
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
                        Calendar calendar =  Calendar.getInstance();
                        if(calendar.get(Calendar.YEAR)>year){
                            toast("请假日期不能在当日之前");
                            return;
                        }
                        if(calendar.get(Calendar.MONTH)+1 > month){
                            toast("请假日期不能在当日之前");
                            return;
                        }
                        if(calendar.get(Calendar.DAY_OF_MONTH) > day){
                            toast("请假日期不能在当日之前");
                            return;
                        }
                        if(!binding.startTime.getText().equals("") && binding.startTime.getText().toString().compareTo(String.format("%02d-%02d-%02d",year,month,day))>0){
                            toast("开始日期不能大于结束日期");
                            return;
                        }
                        binding.endTime.setText(String.format("%02d-%02d-%02d",year,month,day));
                        cacultDays();
                    }
                });
                datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");
            }
        });
        binding.submit.setOnClickListener(view -> {
            if(progrossDialog == null){
                progrossDialog = new ProgrossDialog(ApplyLeaveActivity.this);
            }
            progrossDialog.show();
            subimit();
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


    private void choosePic() {
        ActionSheetDialog dialog = new ActionSheetDialog(this).builder()
                .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue, which -> PictureSelector.create(ApplyLeaveActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(3-imagesUrl.size())
                        .enableCrop(true)
                        .withAspectRatio(1, 1)
                        .isCamera(false)
                        .forResult(PictureConfig.CHOOSE_REQUEST))
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, which -> PictureSelector.create(ApplyLeaveActivity.this)
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


    private void initData(){
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getLeaverList().subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(aplyUserResultResult -> {
                    if(!aplyUserResultResult.getCode().equals("200")){
                        toast(aplyUserResultResult.getMessage());
                        throw new NullPointerException("获取请假人错误");
                    }
                    aplyUserList.addAll(aplyUserResultResult.getResult().getLerverInfoList());

                    return  MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class).getApprover(new GetApproverVo(MyApplication.userDetailInfo.getUserInfo().getUserId()));
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
                Drawable drawable = ApplyLeaveActivity.this.getResources().getDrawable(R.mipmap.icon_leave_down_arrow);
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
    ProgrossDialog progrossDialog;
    private void subimit(){
        if(binding.days.getText().toString().equals("")){
            toast("请选择开始结束时间");
            if(progrossDialog!=null){
                progrossDialog.dismiss();
            }
            return;
        }
        if(Integer.parseInt(binding.days.getText().toString())<0){
            toast("请设置正确的请假时间");
            if(progrossDialog!=null){
                progrossDialog.dismiss();
            }
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
        PutObjectSamples.upLoadFils(imagesUrl).flatMap(strings -> {
            applyLeaveVo.setLrPictureUrls(strings);
            return  MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                    .applyLeave(applyLeaveVo);
        }) .subscribeOn(Schedulers.newThread())
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
                        if(stringResult.getCode().equals("200")){
                            toast("提交成功");
                            finish();
                        }else{
                            toast(stringResult.getMessage());
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
