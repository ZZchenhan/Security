package com.hz.junxinbaoan.me.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.databinding.FragmentMeInfoBinding;
import com.hz.junxinbaoan.login.bean.UserDetailInfo;
import com.hz.junxinbaoan.utils.PesonInfoHelper;

/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */

public class MeInfoFragment extends Fragment {
    View root;
    FragmentMeInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_me_info, container, false);
            root = binding.getRoot();
            findViews();
        }
        return binding.getRoot();
    }

    private void findViews() {
        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MeInfoActivity) getActivity()).change();
            }
        });
        setData(MyApplication.userDetailInfo);
    }

    public void setData(UserDetailInfo userDetailInfo) {
        binding.name.setText(userDetailInfo.getUserInfo().getName());
        binding.phone.setText(userDetailInfo.getUserInfo().getPhone());
        binding.idcard.setText(userDetailInfo.getUserInfo().getIdCard());
        if(userDetailInfo.getUserInfo().getResidenceProvinceName()
                == null
                ||userDetailInfo.getUserInfo().getResidenceCityName() == null
                || userDetailInfo.getUserInfo().getResidenceAddress() == null){
            binding.nativePlace.setText(
                    userDetailInfo.getUserInfo().getResidenceAddress() == null?
                            "未设置":userDetailInfo.getUserInfo().getResidenceAddress());
        }else{
            binding.nativePlace.setText(
                    userDetailInfo.getUserInfo().getResidenceProvinceName()
                    +userDetailInfo.getUserInfo().getResidenceCityName()
                    +userDetailInfo.getUserInfo().getResidenceDistrictName()
            );
        }
        binding.nativePlaceDetail.setText(
                userDetailInfo.getUserInfo().getResidenceAddress() == null?
                        "未设置":userDetailInfo.getUserInfo().getResidenceAddress());
        binding.nation.setText(userDetailInfo.getUserInfo().getNation());
        binding.sex.setText(userDetailInfo.getUserInfo().getSex().equals("0") ? "女" : "男");
        binding.department.setText(userDetailInfo.getDeptVo().getDeptName());
        binding.joinTime.setText(PesonInfoHelper.changeTimes(userDetailInfo.getUserInfo().getRelUserDeptOrgVo().getJoinTime()));
        binding.brithday.setText(PesonInfoHelper.changeTimes(userDetailInfo.getUserInfo().getBirthday()));
        binding.education.setText(PesonInfoHelper.edction(userDetailInfo.getUserInfo().getEducation()));
        if(userDetailInfo.getUserInfo().getLivingProvinceName()
                == null
                ||userDetailInfo.getUserInfo().getLivingCityName() == null
                || userDetailInfo.getUserInfo().getLivingAddress() == null){
            binding.live.setText("未设置");
        }else{
            binding.live.setText(
                    userDetailInfo.getUserInfo().getLivingProvinceName()
                            +userDetailInfo.getUserInfo().getLivingCityName()
                            +userDetailInfo.getUserInfo().getLivingDistrictName()
            );
        }
        if(userDetailInfo.getUserInfo().getLivingAddress() != null) {
            binding.adress.setText(userDetailInfo.getUserInfo().getLivingAddress());
        }else{
            binding.adress.setText("未设置");
        }
        binding.marry.setText(PesonInfoHelper.marryStatus(userDetailInfo.getUserInfo().getMaritalStatus()));
        binding.political.setText(PesonInfoHelper.politicsType(userDetailInfo.getUserInfo().getPoliticsType()));
        binding.height.setText(userDetailInfo.getUserInfo().getHeight()+"CM");
        binding.age.setText(userDetailInfo.getUserInfo().getAge()+"岁");
        binding.workAge.setText(PesonInfoHelper.getWorkAge(userDetailInfo.getUserInfo().getWorkYear()));
        binding.weight.setText(userDetailInfo.getUserInfo().getWeight()+"KG");
        binding.blood.setText(PesonInfoHelper.bloodType(userDetailInfo.getUserInfo().getBloodType()));
    }


}
