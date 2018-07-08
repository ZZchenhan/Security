package xxk.wuhai.seacurity.me.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.contact.view.AddImpressionActivity;
import xxk.wuhai.seacurity.contact.view.UserInfoActivity;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.bean.UserInfoBean;
import xxk.wuhai.seacurity.weight.site.SiteDialogFragment;

/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */

public class MeFragment extends Fragment {
    View root;

    private TextView name;

    private TextView userName;

    private TextView tvZhiwei;

    private TextView sex;

    private TextView age;

    private TextView phone;

    private TextView tvZan;

    private TextView tvCai;

    private TextView tag1;
    private TextView tag2;
    private TextView tag3;
    private TextView tag4;
    private TextView tag5;
    private TextView see;

    private TextView tvInfo;

    private TextView idCard;

    private void setDada(UserInfoBean userDetailInfo){
        name.setText(userDetailInfo.getName()==null?"":userDetailInfo.getName().length()<2?userDetailInfo.getName():userDetailInfo.getName().substring(userDetailInfo.getName().length()-2,userDetailInfo.getName().length()));
        userName.setText(userDetailInfo.getName()+"");
        tvZhiwei.setText(MyApplication.userDetailInfo.getDeptVo()==null?"":MyApplication.userDetailInfo.getDeptVo().getDeptName()+"");
        sex.setText(userDetailInfo.getSex().equals("0")?"女":"男");
        age.setText(userDetailInfo.getAge()+"");
        phone.setText(userDetailInfo.getPhone()+"");
        tvZan.setText(String.format("赞：%s","0"));
        tvCai.setText(String.format("踩：%s","0"));
        tag1.setVisibility(View.GONE);
        tag2.setVisibility(View.GONE);
        tag3.setVisibility(View.GONE);
        tag4.setVisibility(View.GONE);
        tag5.setVisibility(View.GONE);
        see.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null) {
            root = inflater.inflate(R.layout.fragment_me, null);
            findViews();
            setDada(MyApplication.userDetailInfo.getUserInfo());
        }
        return root;
    }


    public void findViews() {
        name = root.findViewById(R.id.name);
        userName = root.findViewById(R.id.user_name);
        tvZhiwei = root.findViewById(R.id.zhiwei);
        sex = root.findViewById(R.id.sex);
        age = root.findViewById(R.id.age);
        phone = root.findViewById(R.id.phone);
        tvZan = root.findViewById(R.id.zan);
        tvCai = root.findViewById(R.id.cai);
        tag1 = root.findViewById(R.id.tag1);
        tag2 = root.findViewById(R.id.tag2);
        tag3 = root.findViewById(R.id.tag3);
        tag4 = root.findViewById(R.id.tag4);
        tag5 = root.findViewById(R.id.tag5);
        see = root.findViewById(R.id.tv_see);
        tvInfo = root.findViewById(R.id.userinfo);
        idCard = root.findViewById(R.id.idcard);

        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeInfoActivity.openActivity(getContext(),MeInfoActivity.class);
//                SiteDialogFragment siteDialogFragment = new SiteDialogFragment(getContext());
//                siteDialogFragment.show();
            }
        });

        idCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ElectIDcardActivity.openActivity(getContext(),ElectIDcardActivity.class);
            }
        });

        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeTAgActivity.openActivity(getContext(),MeTAgActivity.class);
            }
        });
    }

}
