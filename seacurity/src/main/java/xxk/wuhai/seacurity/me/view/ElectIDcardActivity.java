package xxk.wuhai.seacurity.me.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import sz.tianhe.baselib.http.RetrofitClient;
import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.utils.DeviceUtils;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.databinding.ActivityElectIdcardBinding;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.utils.PesonInfoHelper;
import xxk.wuhai.seacurity.weight.ImageDialog;

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

    }

    boolean isMin = true;
    private void setData(UserDetailInfo userDetailInfo){
        binding.name.setText(userDetailInfo.getUserInfo().getName());
        binding.sex.setText(userDetailInfo.getUserInfo().getSex().equals("0")?"女":"男");
        binding.birthday.setText(PesonInfoHelper.changeTimes(userDetailInfo.getUserInfo().getBirthday()));
        binding.nativePlace.setText(userDetailInfo.getUserInfo().getResidenceAddress()+"");
        binding.idcard.setText(userDetailInfo.getUserInfo().getIdCard()+"");
        binding.company.setText(userDetailInfo.getOrgVo() == null?"":userDetailInfo.getOrgVo().getName()+"");
        binding.phone.setText(userDetailInfo.getUserInfo().getPhone());
        binding.liveaddress.setText(userDetailInfo.getUserInfo().getLivingAddress());
        binding.tvName.setText(userDetailInfo.getUserInfo().getName()==null?"":userDetailInfo.getUserInfo().getName().length()<2?userDetailInfo.getUserInfo().getName()
                :userDetailInfo.getUserInfo().getName().substring(userDetailInfo.getUserInfo().getName().length()-2,userDetailInfo.getUserInfo().getName().length()));

        Glide.with(this)
                .load(MyApplication.baseUrl +
                        "/client-api/user/getQRCodeImg" +
                        "?x-terminal-type=2" +
                        "&x-access-token=" +
                        BaseInterceptor.token +
                        "&x-username=" +
                        BaseInterceptor.name +
                        "&x-random=" +
                        BaseInterceptor.random)
                .into(binding.qrcode);

        binding.qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageDialog == null){
                    imageDialog = new ImageDialog(ElectIDcardActivity.this);
                }
                imageDialog.show();
            }
        });

    }

    ActivityElectIdcardBinding binding;
    public View databindViews(){
        binding =  DataBindingUtil.inflate(LayoutInflater.from(this),layoutId(),null,false);
        return binding.getRoot();
    }
}