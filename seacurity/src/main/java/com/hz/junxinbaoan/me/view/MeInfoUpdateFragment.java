package com.hz.junxinbaoan.me.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.utils.ToastUtils;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.databinding.FragmentMeInfoUpdateBinding;
import com.hz.junxinbaoan.login.api.UserApi;
import com.hz.junxinbaoan.login.bean.UserDetailInfo;
import com.hz.junxinbaoan.login.vo.UpdateUsers;
import com.hz.junxinbaoan.utils.PesonInfoHelper;
import com.hz.junxinbaoan.weight.dialog.BottomDialog;
import com.hz.junxinbaoan.weight.dialog.TypeHelp;
import com.hz.junxinbaoan.weight.site.SiteDialogFragment;
import com.hz.junxinbaoan.work.bean.RecordBean;

/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */

public class MeInfoUpdateFragment extends Fragment {
    private View root;
    FragmentMeInfoUpdateBinding binding;

    private BottomDialog sexDialog;
    private BottomDialog eductaoinDialog;
    private BottomDialog marridDialog;
    private BottomDialog bloodDialog;
    private BottomDialog wokeAgeDialog;
    private BottomDialog politicalDialog;
    private SiteDialogFragment siteDialogFragment;
    private String sexCode = "";
    private String eductionType = "";
    private String marriedType = "";
    private String politicalType = "";
    private String bloodType = "";

    UpdateUsers userInfoBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_me_info_update, container, false);
            root = binding.getRoot();
            setOnclick();
            userInfoBean = new UpdateUsers();
            userInfoBean.change(MyApplication.userDetailInfo);
            setData(MyApplication.userDetailInfo);
        }
        return root;
    }


    public void setData(UserDetailInfo userDetailInfo) {
        binding.name.setText(userDetailInfo.getUserInfo().getName());
        binding.phone.setText(userDetailInfo.getUserInfo().getPhone());
        binding.idcard.setText(userDetailInfo.getUserInfo().getIdCard());
        binding.age.setText(userDetailInfo.getUserInfo().getAge()+"岁");
        if (userDetailInfo.getUserInfo().getResidenceProvinceName()
                == null
                || userDetailInfo.getUserInfo().getResidenceCityName() == null
                || userDetailInfo.getUserInfo().getResidenceAddress() == null) {
            binding.nativePlace.setText(
                    userDetailInfo.getUserInfo().getResidenceAddress() == null ?
                            "未设置" : userDetailInfo.getUserInfo().getResidenceAddress());
        } else {
            binding.nativePlace.setText(
                    userDetailInfo.getUserInfo().getResidenceProvinceName()
                            + userDetailInfo.getUserInfo().getResidenceCityName()
                            + userDetailInfo.getUserInfo().getResidenceDistrictName()
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
        if (userDetailInfo.getUserInfo().getLivingProvinceName()
                == null
                || userDetailInfo.getUserInfo().getLivingCityName() == null
                || userDetailInfo.getUserInfo().getLivingAddress() == null) {
            binding.live.setText("未设置");
        } else {
            binding.live.setText(
                    userDetailInfo.getUserInfo().getLivingProvinceName()
                            + userDetailInfo.getUserInfo().getLivingCityName()
                            + userDetailInfo.getUserInfo().getLivingDistrictName()
            );
        }
        if (userDetailInfo.getUserInfo().getLivingAddress() != null) {
            binding.adress.setText(userDetailInfo.getUserInfo().getLivingAddress());
        } else {
            binding.adress.setText("未设置");
        }
        binding.marry.setText(PesonInfoHelper.marryStatus(userDetailInfo.getUserInfo().getMaritalStatus()));
        userInfoBean.setMaritalStatus(userDetailInfo.getUserInfo().getMaritalStatus() == null ? "0" : userDetailInfo.getUserInfo().getMaritalStatus());
        binding.political.setText(PesonInfoHelper.politicsType(userDetailInfo.getUserInfo().getPoliticsType()));
        binding.height.setText(userDetailInfo.getUserInfo().getHeight() + "CM");
        binding.workAge.setText(PesonInfoHelper.getWorkAge(userDetailInfo.getUserInfo().getWorkYear()));
        binding.weight.setText(userDetailInfo.getUserInfo().getWeight() + "KG");
        binding.blood.setText(PesonInfoHelper.bloodType(userDetailInfo.getUserInfo().getBloodType()));

        sexCode = userDetailInfo.getUserInfo().getSex();
        eductionType = userDetailInfo.getUserInfo().getEducation() + "";
        marriedType = userDetailInfo.getUserInfo().getMaritalStatus();
        politicalType = userDetailInfo.getUserInfo().getPoliticsType();
        bloodType = userDetailInfo.getUserInfo().getBloodType();

        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }

    private void setOnclick() {
        binding.sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sexDialog == null) {
                    sexDialog = new BottomDialog(getContext());
                    sexDialog.setType(TypeHelp.Type.SEX);
                    sexDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.sex.setText(sexDialog.getSelectValues().getValues());
                            sexCode = sexDialog.getSelectValues().getCode();
                            userInfoBean.setSex(sexCode);
                            sexDialog.dismiss();
                        }
                    });
                }
                sexDialog.show();
            }
        });
        binding.education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eductaoinDialog == null) {
                    eductaoinDialog = new BottomDialog(getContext());
                    eductaoinDialog.setType(TypeHelp.Type.EDUCATION);
                    eductaoinDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.education.setText(eductaoinDialog.getSelectValues().getValues());
                            eductionType = eductaoinDialog.getSelectValues().getCode();
                            userInfoBean.setEducation(Integer.parseInt(eductionType));
                            eductaoinDialog.dismiss();
                        }
                    });
                }
                eductaoinDialog.show();
            }
        });


        binding.marry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (marridDialog == null) {
                    marridDialog = new BottomDialog(getContext());
                    marridDialog.setType(TypeHelp.Type.MARRIES);
                    marridDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            marriedType = marridDialog.getSelectValues().getCode();
                            binding.marry.setText(marridDialog.getSelectValues().getValues());
                            userInfoBean.setMaritalStatus(marridDialog.getSelectValues().getCode());
                            marridDialog.dismiss();
                        }
                    });
                }
                marridDialog.show();
            }
        });

        binding.workAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wokeAgeDialog == null){
                    wokeAgeDialog = new BottomDialog(getContext());
                    wokeAgeDialog.setType(TypeHelp.Type.WORK_AGE);
                    wokeAgeDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.workAge.setText(wokeAgeDialog.getSelectValues().getValues());
                            userInfoBean.setWorkYear(wokeAgeDialog.getSelectValues().getCode());
                            wokeAgeDialog.dismiss();
                        }
                    });
                }
                wokeAgeDialog.show();
            }
        });

        binding.political.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (politicalDialog == null) {
                    politicalDialog = new BottomDialog(getContext());
                    politicalDialog.setType(TypeHelp.Type.POLITICS);
                    politicalDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            politicalType = politicalDialog.getSelectValues().getCode();
                            binding.political.setText(politicalDialog.getSelectValues().getValues());
                            userInfoBean.setPoliticsType(politicalDialog.getSelectValues().getCode());
                            politicalDialog.dismiss();
                        }
                    });
                }
                politicalDialog.show();
            }
        });

        binding.blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bloodDialog == null) {
                    bloodDialog = new BottomDialog(getContext());
                    bloodDialog.setType(TypeHelp.Type.BLOOD);
                    bloodDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bloodType = bloodDialog.getSelectValues().getCode();
                            binding.blood.setText(bloodDialog.getSelectValues().getValues());
                            userInfoBean.setBloodType(bloodDialog.getSelectValues().getCode());
                            bloodDialog.dismiss();
                        }
                    });
                }
                bloodDialog.show();
            }
        });


        binding.nativePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (siteDialogFragment == null) {
                    siteDialogFragment = new SiteDialogFragment(getContext());
                }
                siteDialogFragment.setOnSiteComfirmListener(new SiteDialogFragment.OnSiteComfirmListener() {
                    @Override
                    public void onConfimr(String addrss,int provice,int city,int dis) {
                        binding.nativePlace.setText(addrss);
                        userInfoBean.setResidenceProvinceId(provice);
                        userInfoBean.setResidenceCityId(city);
                        userInfoBean.setResidenceDistrictId(dis);
                    }
                });
                siteDialogFragment.show();
            }
        });

        binding.live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (siteDialogFragment == null) {
                    siteDialogFragment = new SiteDialogFragment(getContext());
                }
                siteDialogFragment.setOnSiteComfirmListener(new SiteDialogFragment.OnSiteComfirmListener() {
                    @Override
                    public void onConfimr(String addrss,int provice,int city,int dis) {
                        binding.live.setText(addrss);
                        userInfoBean.setLivingProvinceId(provice);
                        userInfoBean.setLivingCityId(city);
                        userInfoBean.setLivingDistrictId(dis);
                    }
                });
                siteDialogFragment.show();
            }
        });
    }


    public void update() {
        userInfoBean.setNation(binding.nation.getText().toString());
        userInfoBean.setLivingAddress(binding.adress.getText().toString());
        userInfoBean.setHeight(
                getIntValue(binding.height.getText().toString())
               );
//        userInfoBean.setAge(Integer.parseInt(binding.age.getText().toString()));
        userInfoBean.setWeight(getIntValue(binding.weight.getText().toString())
               );
        userInfoBean.setResidenceAddress(binding.nativePlaceDetail.getText().toString());
        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .modify(userInfoBean)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<RecordBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<RecordBean> result) {
                        if (result.getCode().equals("200")) {
                            if (result.getResult().getStatus().equals("1")) {
                                showMsg("修改成功");
                                getActivity().setResult(Activity.RESULT_OK);
                                getActivity().finish();
                            } else {
                                showMsg(result.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void showMsg(String msg){
        ToastUtils.makeText(getActivity(),msg,ToastUtils.LENGTH_LONG).show();
    }


    public static int getIntValue(String str){
        if(str==null){
            return 0;
        }
        try {
            String valueSection = str.split("[a-zA-Z]")[0].trim();
            if (valueSection.length() == 0) {
                return 0;
            }
            int value = 0;
            try {
                value = Integer.parseInt(valueSection);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }catch (Exception e){
            return 0;
        }
    }

}
