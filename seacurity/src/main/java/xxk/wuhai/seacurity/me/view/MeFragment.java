package xxk.wuhai.seacurity.me.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.contact.view.AddImpressionActivity;
import xxk.wuhai.seacurity.contact.view.UserInfoActivity;
import xxk.wuhai.seacurity.login.api.UserApi;
import xxk.wuhai.seacurity.login.bean.UserDetailInfo;
import xxk.wuhai.seacurity.login.bean.UserInfoBean;
import xxk.wuhai.seacurity.login.result.TagResult;
import xxk.wuhai.seacurity.login.vo.GetPraiseAndLabelVo;
import xxk.wuhai.seacurity.main.view.MainActivity;
import xxk.wuhai.seacurity.me.adapter.GridSpacingItemDecoration;
import xxk.wuhai.seacurity.me.adapter.TagAdapter;
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


    private TextView tvInfo;

    private TextView idCard;


    TagAdapter tagAdapter;
    RecyclerView recyclerView;
    List<TagResult.ResultBean.LabelVoListBean> datas = new ArrayList<>();

    public void setDada(UserInfoBean userDetailInfo){
        name.setText(userDetailInfo.getName()==null?"":userDetailInfo.getName().length()<2?userDetailInfo.getName():userDetailInfo.getName().substring(userDetailInfo.getName().length()-2,userDetailInfo.getName().length()));
        userName.setText(userDetailInfo.getName()+"");
        tvZhiwei.setText(MyApplication.userDetailInfo.getUserInfo().getRelUserDeptOrgVo()==null?"":
                MyApplication.userDetailInfo.getUserInfo().getRelUserDeptOrgVo().getRank()+"");
        sex.setText(userDetailInfo.getSex().equals("0")?"女":"男");
        age.setText(userDetailInfo.getAge()+"岁");
        phone.setText(userDetailInfo.getPhone()+"");
        tvZan.setText(String.format("赞：%s","0"));
        tvCai.setText(String.format("踩：%s","0"));
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
        tvInfo = root.findViewById(R.id.userinfo);
        idCard = root.findViewById(R.id.idcard);
        recyclerView = root.findViewById(R.id.recylview);
        tagAdapter = new TagAdapter(datas);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getContext().getResources().getDisplayMetrics()),false));
        recyclerView.setAdapter(tagAdapter);
        root.findViewById(R.id.left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openOrClose();
            }
        });

        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeInfoActivity.openActivity((Activity) getContext(),MeInfoActivity.class,200);
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

        tagAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(datas.get(position).getLabelName().equals("查看更多")){
                    startActivity(new Intent(getContext(),MeTAgActivity.class).putExtra("id",MyApplication.userDetailInfo.getUserInfo().getUserId()));
                }
            }
        });

//        see.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MeTAgActivity.openActivity(getContext(),MeTAgActivity.class);
//            }
//        });
        getTags(MyApplication.userDetailInfo.getUserInfo().getUserId());
    }



    public void getTags(int userId){
        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .getPraiseAndLabel(new GetPraiseAndLabelVo(userId))
            .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<TagResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TagResult s) {
                if(!s.getCode().equals("200")){
                    ToastUtils.showShort(s.getMessage());
                    return;
                }
                datas.clear();
                tvZan.setText("赞："+s.getResult().getPraised());
                tvCai.setText("踩："+s.getResult().getTrampled());
                if(s.getResult().getLabelVoList().size()>=9){
                    datas.addAll(s.getResult().getLabelVoList().subList(0,8));
                }
                datas.add(new TagResult.ResultBean.LabelVoListBean(0,"查看更多",1));
                tagAdapter.notifyDataSetChanged();
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
