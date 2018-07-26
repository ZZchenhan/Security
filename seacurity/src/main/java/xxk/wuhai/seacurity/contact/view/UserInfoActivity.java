package xxk.wuhai.seacurity.contact.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.contact.bean.DirectoryVo;
import xxk.wuhai.seacurity.contact.bean.StaffResult;
import xxk.wuhai.seacurity.login.api.UserApi;
import xxk.wuhai.seacurity.login.result.TagResult;
import xxk.wuhai.seacurity.login.vo.GetPraiseAndLabelVo;
import xxk.wuhai.seacurity.me.adapter.GridSpacingItemDecoration;
import xxk.wuhai.seacurity.me.adapter.TagAdapter;
import xxk.wuhai.seacurity.me.view.MeTAgActivity;

public class UserInfoActivity extends BaseActivity {

    private TextView name;

    private TextView userName;

    private TextView tvZhiwei;

    private TextView sex;

    private TextView age;

    private TextView phone;

    private TextView tvZan;

    private TextView tvCai;

    TagAdapter tagAdapter;
    RecyclerView recyclerView;
    List<TagResult.ResultBean.LabelVoListBean> datas = new ArrayList<>();

    DirectoryVo directoryVo;

    @Override
    public void findViews() {
        name = findViewById(R.id.name);
        userName = findViewById(R.id.user_name);
        tvZhiwei = findViewById(R.id.zhiwei);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        tvZan = findViewById(R.id.zan);
        tvCai = findViewById(R.id.cai);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone.getText().toString()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        recyclerView =findViewById(R.id.recylview);
        tagAdapter = new TagAdapter(datas);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,this.getResources().getDisplayMetrics()),false));
        recyclerView.setAdapter(tagAdapter);

        tagAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(datas.get(position).getLabelName().equals("查看更多")){
                    startActivity(new Intent(UserInfoActivity.this,MeTAgActivity.class).putExtra("id",directoryVo.getUserId()));
                }
            }
        });
    }

    @Override
    public int layoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "个人信息";
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        directoryVo = (DirectoryVo) getIntent().getSerializableExtra("data");
        if(directoryVo == null){
            return;
        }
        setDada(directoryVo);
    }

    private void setDada(DirectoryVo userDetailInfo){
        name.setText(userDetailInfo.getName()==null?"":userDetailInfo.getName().length()<2?userDetailInfo.getName():userDetailInfo.getName().substring(userDetailInfo.getName().length()-2,userDetailInfo.getName().length()));
        userName.setText(userDetailInfo.getName()+"");
        tvZhiwei.setText(userDetailInfo.getRank()== null?"":userDetailInfo.getRank());
        sex.setText(userDetailInfo.getSex().equals("0")?"女":"男");
        age.setText(userDetailInfo.getSex()+"岁");
        phone.setText(userDetailInfo.getPhone()+"");
        tvZan.setText(String.format("赞：%s","0"));
        tvCai.setText(String.format("踩：%s","0"));
        getTags(userDetailInfo.getUserId());
        getAge(userDetailInfo.getUserId());
    }


    public static void openActivity(Context context,DirectoryVo directoryVo){
        context.startActivity(new Intent(context,UserInfoActivity.class).putExtra("data",directoryVo));
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


    public void getAge(int userId){
        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .getStaffInfo(new GetPraiseAndLabelVo(userId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StaffResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StaffResult s) {
                        if(!s.getCode().equals("200")){
                            toast(s.getMessage());
                            return;
                        }
                        try {
                            age.setText(s.getResult().getStaffInfoVo().getAge() + "");
                        }catch (Exception e){}
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
