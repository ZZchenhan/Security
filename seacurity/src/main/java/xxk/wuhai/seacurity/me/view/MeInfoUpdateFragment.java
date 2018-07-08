package xxk.wuhai.seacurity.me.view;

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
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.databinding.FragmentMeInfoUpdateBinding;
import xxk.wuhai.seacurity.login.api.UserApi;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.bean.UserInfoBean;
import xxk.wuhai.seacurity.utils.PesonInfoHelper;
import xxk.wuhai.seacurity.weight.dialog.BottomDialog;
import xxk.wuhai.seacurity.weight.dialog.TypeHelp;
import xxk.wuhai.seacurity.weight.site.SiteDialogFragment;

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
    private BottomDialog politicalDialog;
    private SiteDialogFragment siteDialogFragment;
    private String sexCode = "";
    private String eductionType = "";
    private String marriedType = "";
    private String politicalType = "";
    private String bloodType = "";




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_me_info_update, container, false);
            root = binding.getRoot();
            setOnclick();
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

        sexCode = userDetailInfo.getUserInfo().getSex();
        eductionType = userDetailInfo.getUserInfo().getEducation()+"";
        marriedType = userDetailInfo.getUserInfo().getMaritalStatus();
        politicalType = userDetailInfo.getUserInfo().getPoliticsType();
        bloodType = userDetailInfo.getUserInfo().getBloodType();

        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(MyApplication.userDetailInfo.getUserInfo());
            }
        });
    }

    private void setOnclick(){
        binding.sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sexDialog == null){
                    sexDialog = new BottomDialog(getContext());
                    sexDialog.setType(TypeHelp.Type.SEX);
                    sexDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.sex.setText(sexDialog.getSelectValues().getValues());
                            sexCode = sexDialog.getSelectValues().getCode();
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
                if(eductaoinDialog == null){
                    eductaoinDialog = new BottomDialog(getContext());
                    eductaoinDialog.setType(TypeHelp.Type.EDUCATION);
                    eductaoinDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.education.setText(eductaoinDialog.getSelectValues().getValues());
                            eductionType = eductaoinDialog.getSelectValues().getCode();
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
                if(marridDialog == null){
                    marridDialog = new BottomDialog(getContext());
                    marridDialog.setType(TypeHelp.Type.MARRIES);
                    marridDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            marriedType = marridDialog.getSelectValues().getCode();
                            binding.marry.setText(marridDialog.getSelectValues().getValues());
                            marridDialog.dismiss();
                        }
                    });
                }
                marridDialog.show();
            }
        });

        binding.political.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(politicalDialog == null){
                    politicalDialog = new BottomDialog(getContext());
                    politicalDialog.setType(TypeHelp.Type.POLITICS);
                    politicalDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            politicalType = politicalDialog.getSelectValues().getCode();
                            binding.political.setText(politicalDialog.getSelectValues().getValues());
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
                if(bloodDialog == null){
                    bloodDialog = new BottomDialog(getContext());
                    bloodDialog.setType(TypeHelp.Type.BLOOD);
                    bloodDialog.setConfirmClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            bloodType = bloodDialog.getSelectValues().getCode();
                            binding.blood.setText(bloodDialog.getSelectValues().getValues());
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
                if(siteDialogFragment == null){
                    siteDialogFragment = new SiteDialogFragment(getContext());
                }
                siteDialogFragment.setOnSiteComfirmListener(new SiteDialogFragment.OnSiteComfirmListener() {
                    @Override
                    public void onConfimr(String addrss) {
                        binding.nativePlace.setText(addrss);
                    }
                });
                siteDialogFragment.show();
            }
        });

        binding.live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(siteDialogFragment == null){
                    siteDialogFragment = new SiteDialogFragment(getContext());
                }
                siteDialogFragment.setOnSiteComfirmListener(new SiteDialogFragment.OnSiteComfirmListener() {
                    @Override
                    public void onConfimr(String addrss) {
                        binding.live.setText(addrss);
                    }
                });
                siteDialogFragment.show();
            }
        });
    }



    public void update(UserInfoBean userDetailInfo){
        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .modify(userDetailInfo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result result) {

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
