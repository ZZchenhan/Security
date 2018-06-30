package xxk.wuhai.seacurity.me.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.contact.view.AddImpressionActivity;
import xxk.wuhai.seacurity.contact.view.UserInfoActivity;

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
            }
        });

        idCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
