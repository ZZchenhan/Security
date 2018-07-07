package xxk.wuhai.seacurity.me.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.databinding.FragmentMeInfoUpdateBinding;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.utils.PesonInfoHelper;

/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */

public class MeInfoUpdateFragment extends Fragment {
    private View root;
    FragmentMeInfoUpdateBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_me_info_update, container, false);
            root = binding.getRoot();
            setData(MyApplication.userDetailInfo);
        }
        return root;
    }

    public void setData(UserDetailInfo userDetailInfo) {
        binding.name.setText(userDetailInfo.getUserInfo().getName());
        binding.phone.setText(userDetailInfo.getUserInfo().getPhone());
        binding.idcard.setText(userDetailInfo.getUserInfo().getIdCard());
        binding.nativePlace.setText(userDetailInfo.getUserInfo().getResidenceAddress());
        binding.nation.setText(userDetailInfo.getUserInfo().getNation());
        binding.sex.setText(userDetailInfo.getUserInfo().getSex().equals("0") ? "女" : "男");
        binding.department.setText(userDetailInfo.getDeptVo().getDeptName());
        binding.joinTime.setText(PesonInfoHelper.changeTimes(userDetailInfo.getUserInfo().getRelUserDeptOrgVo().getJoinTime()));
        binding.brithday.setText(PesonInfoHelper.changeTimes(userDetailInfo.getUserInfo().getBirthday()));
        binding.education.setText(PesonInfoHelper.edction(userDetailInfo.getUserInfo().getEducation()));
        binding.live.setText(userDetailInfo.getUserInfo().getLivingAddress());
        binding.adress.setText(userDetailInfo.getUserInfo().getLivingAddress());
        binding.marry.setText(PesonInfoHelper.marryStatus(userDetailInfo.getUserInfo().getMaritalStatus()));
        binding.political.setText(PesonInfoHelper.politicsType(userDetailInfo.getUserInfo().getPoliticsType()));
        binding.height.setText(userDetailInfo.getUserInfo().getHeight()+"");
        binding.age.setText(userDetailInfo.getUserInfo().getAge()+"");
        binding.workAge.setText(userDetailInfo.getUserInfo().getWorkYear());
        binding.weight.setText(userDetailInfo.getUserInfo().getWeight()+"");
        binding.blood.setText(PesonInfoHelper.bloodType(userDetailInfo.getUserInfo().getBloodType()));
    }

}
