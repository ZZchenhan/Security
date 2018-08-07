package com.hz.junxinbaoan.me.view;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;
import com.hz.junxinbaoan.databinding.ActivityElectIdcardBinding;
import com.hz.junxinbaoan.login.api.UserApi;
import com.hz.junxinbaoan.login.bean.Elec;
import com.hz.junxinbaoan.login.bean.UserDetailInfo;
import com.hz.junxinbaoan.login.vo.GetElecCard;
import com.hz.junxinbaoan.utils.PesonInfoHelper;
import com.hz.junxinbaoan.weight.ImageDialog;
import com.hz.junxinbaoan.work.api.WorkDutyApi;

public class ElectIDcardActivity extends BaseActivity {
    ImageDialog imageDialog;

    @Override
    public int layoutId() {
        return R.layout.activity_elect_idcard;
    }

    LeftIconNavagation leftIconNavagation;

    @Override
    public IBaseNavagation navagation() {
        leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "电子保安证";
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
        setData(MyApplication.userDetailInfo);
    }

    @Override
    public void findViews() {
        getElect();
        getQRCode();
    }

    boolean isMin = true;

    private void setData(UserDetailInfo userDetailInfo) {
        binding.name.setText(userDetailInfo.getUserInfo().getName());
        binding.sex.setText(userDetailInfo.getUserInfo().getSex().equals("0") ? "女" : "男");
        binding.birthday.setText(PesonInfoHelper.changeTimes(userDetailInfo.getUserInfo().getBirthday()));
        if(userDetailInfo.getUserInfo().getResidenceProvinceName() !=null
            &&userDetailInfo.getUserInfo().getResidenceCityName() !=null
                &&userDetailInfo.getUserInfo().getResidenceDistrictName() !=null
                ) {
            binding.nativePlace.setText(userDetailInfo.getUserInfo().getResidenceProvinceName() + "" +
                    userDetailInfo.getUserInfo().getResidenceCityName()+
                    userDetailInfo.getUserInfo().getResidenceDistrictName() + userDetailInfo.getUserInfo().getResidenceAddress());
        }else{
            binding.nativePlace.setText(userDetailInfo.getUserInfo().getResidenceAddress());
        }
        //binding.idcard.setText(userDetailInfo.getUserInfo().getIdCard() + "");
        binding.company.setText(userDetailInfo.getOrgVo() == null ? "" : userDetailInfo.getOrgVo().getName() + "");
        binding.phone.setText(userDetailInfo.getUserInfo().getPhone());
        binding.liveaddress.setText(userDetailInfo.getUserInfo().getLivingProvinceName()+userDetailInfo.getUserInfo().getLivingCityName()+userDetailInfo.getUserInfo().getLivingDistrictName()+""+userDetailInfo.getUserInfo().getLivingAddress());
        binding.tvName.setText(userDetailInfo.getUserInfo().getName() == null ? "" : userDetailInfo.getUserInfo().getName().length() < 2 ? userDetailInfo.getUserInfo().getName()
                : userDetailInfo.getUserInfo().getName().substring(userDetailInfo.getUserInfo().getName().length() - 2, userDetailInfo.getUserInfo().getName().length()));
        //http://47.98.241.211//client-api/user/getQRCodeImg?x-terminal-type=5&x-access-token=837Yx8wSU6xYLXa16Q/+J0PlGlNo/E8GfbnoX7yfk7nGyPoceFa60NUORwLusyQj&x-username=CE5D55AE79D4F853B3F5EFFF1A1F0EAF&x-random=1532092291005
        //NSString *common = @"http://47.98.241.211/client-api/user/getQRCodeImg";

//        String url = String.format("%sclient-api/user/getQRCodeImg?x-terminal-type=%s&x-random=%s&x-access-token=%s&x-username=%s"
//                ,MyApplication.baseUrl,
//                URLEncoder.encode(BaseInterceptor.type), BaseInterceptor.random, URLEncoder.encode(BaseInterceptor.token), URLEncoder.encode(BaseInterceptor.name)
//        );
//        Glide.with(this)
//                .load(url)
//                .into(binding.qrcode);

        binding.qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qrPic == null || qrPic.equals("")){
                    toast("没有二维码");
                    return;
                }
                if (imageDialog == null) {
                    imageDialog = new ImageDialog(ElectIDcardActivity.this,qrPic);
                }
                imageDialog.show();
            }
        });

    }

    ActivityElectIdcardBinding binding;

    public View databindViews() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), layoutId(), null, false);
        return binding.getRoot();
    }


    public void getElect(){
        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .getElectCard(new GetElecCard(MyApplication.userDetailInfo.getUserInfo().getAccountName()))
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Elec>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Elec string) {
                if(!string.getCode().equals("200")){
                    toast(string.getMessage());
                    return;
                }
                if( string.getResult().getUserCertificateInfoVo() == null){
                    toast("电子保安证为空");
                    return;
                }
                setView(string.getResult().getUserCertificateInfoVo());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void setView(Elec.ResultBean.UserCertificateInfoVoBean userCertificateInfoVoBean){
        binding.birthday.setText(PesonInfoHelper.changeTimes(userCertificateInfoVoBean.getBirthday()));
        binding.birthday.setText(PesonInfoHelper.changeTimes(userCertificateInfoVoBean.getBirthday()));
        if(userCertificateInfoVoBean.getResidenceProvinceName() !=null
                &&userCertificateInfoVoBean.getResidenceCityName() !=null
                &&userCertificateInfoVoBean.getResidenceDistrictName() !=null
                ) {
            binding.nativePlace.setText(userCertificateInfoVoBean.getResidenceProvinceName() + "" +
                    userCertificateInfoVoBean.getResidenceCityName()+
                    userCertificateInfoVoBean.getResidenceDistrictName() + userCertificateInfoVoBean.getResidenceAddress());
        }else{
            binding.nativePlace.setText(userCertificateInfoVoBean.getResidenceAddress());
        }
        binding.liveaddress.setText(userCertificateInfoVoBean.getLivingProvinceName()+
                userCertificateInfoVoBean.getLivingCityName()+
                userCertificateInfoVoBean.getLivingDistrictName()+"" +
                userCertificateInfoVoBean.getLivingAddress());
    }
    private String qrPic=null;
    public void  getQRCode(){
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getQRCode().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> string) {
                        if(!string.getCode().equals("200")){
                            toast(string.getResult());
                            return;
                        }
                        qrPic = string.getResult();
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
