package xxk.wuhai.seacurity.work.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import sz.tianhe.baselib.weight.ProgrossDialog;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.databinding.ActivitySupplementSignBinding;
import xxk.wuhai.seacurity.oss.PutObjectSamples;
import xxk.wuhai.seacurity.weight.dialog.ActionSheetDialog;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.ApproverUser;
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
                choosePic();
            }
        });
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(progrossDialog == null){
                    progrossDialog = new ProgrossDialog(SupplementSignActivity.this);
                }
                progrossDialog.show();
                subimit();
            }
        });
    }
    ProgrossDialog progrossDialog;
    private void choosePic() {
        ActionSheetDialog dialog = new ActionSheetDialog(this).builder()
                .addSheetItem("相册", ActionSheetDialog.SheetItemColor.Blue, which -> PictureSelector.create(SupplementSignActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(3-imagesUrl.size())
                        .enableCrop(true)
                        .withAspectRatio(1, 1)
                        .isCamera(false)
                        .forResult(PictureConfig.CHOOSE_REQUEST))
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue, which -> PictureSelector.create(SupplementSignActivity.this)
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

    @Override
    public void findViews() {

    }


    private void subimit(){
        if(binding.result.getText().length() == 0){
            toast("请输入补签原因");
            if(progrossDialog!=null){
                progrossDialog.dismiss();
            }
            return;
        }

        SupplementApplyVo supplementApplyVo = new SupplementApplyVo();
        supplementApplyVo.setApproverID(approverUser != null && approverUser.getApproverId() !=0
                ?approverUser.getApproverId():
                MyApplication.userDetailInfo.getUserInfo().getUserId());
        supplementApplyVo.setAttendanceId(getIntent().getStringExtra("id"));
        supplementApplyVo.setPatchTime(date);
        supplementApplyVo.setSupplement(binding.result.getText().toString());

        PutObjectSamples.upLoadFils(imagesUrl)
                .flatMap(strings -> {
                    supplementApplyVo.setPaPictureUrls(strings);
                    return     MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                            .supplementApply(supplementApplyVo);
                })
                .subscribeOn(Schedulers.newThread())
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
                            toast("提交申请成功");
                            finish();
                            return;
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
