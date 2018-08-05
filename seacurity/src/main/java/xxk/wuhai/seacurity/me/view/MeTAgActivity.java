package xxk.wuhai.seacurity.me.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.utils.ToastUtils;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.contact.adapter.ImpresssionAdapter;
import xxk.wuhai.seacurity.contact.bean.TagBean;
import xxk.wuhai.seacurity.contact.view.UserInfoActivity;
import xxk.wuhai.seacurity.login.api.UserApi;
import xxk.wuhai.seacurity.login.result.TagResult;
import xxk.wuhai.seacurity.login.vo.AddLabelInfoApiVo;
import xxk.wuhai.seacurity.login.vo.GetPraiseAndLabelVo;
import xxk.wuhai.seacurity.me.adapter.GridSpacingItemDecoration;
import xxk.wuhai.seacurity.me.adapter.TagAdapter;

public class MeTAgActivity extends BaseActivity {


    private TextView tvNumbers;

    private RecyclerView recyclerView;
    List<TagResult.ResultBean.LabelVoListBean> datas = new ArrayList<>();
    TagAdapter tagAdapter;

    private List<TagBean> tagBeans = new ArrayList<>();


    @Override
    public int layoutId() {
        return R.layout.activity_me_tag;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "个人标签";
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
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        tagAdapter = new TagAdapter(datas);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, this.getResources().getDisplayMetrics()), false));
        recyclerView.setAdapter(tagAdapter);

        tagAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (getIntent().getIntExtra("id", 0) == MyApplication.userDetailInfo.getUserInfo().getUserId()
                        ) {
                    return;
                }
                if (datas.get(position).getIsLightUp().equals("1")) {
                    toast("已经点过，不能再点了哦");
                    return;
                }
                if(getIntent().getIntExtra("id", 0) == MyApplication.userDetailInfo.getUserInfo().getUserId()){
                    toast("不能给自己点赞哦");
                }else {
                    TextView textView = (TextView) view;
                    textView.setBackground(getResources().getDrawable(R.drawable.bg_info_blue));
                    textView.setTextColor(Color.WHITE);
                    textView.setText(datas.get(position).getLabelName() + " " + (datas.get(position).getLabelNum() + 1));
                    addTags(datas.get(position).getLabelId(), getIntent().getIntExtra("id", 0));
                }
            }
        });
        getTags(getIntent().getIntExtra("id", 0));
    }

    @Override
    public void findViews() {
        tvNumbers = findViewById(R.id.numbers);
    }

    public void getTags(int userId) {
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
                        if (!s.getCode().equals("200")) {
                           toast(s.getMessage());
                            return;
                        }
                        datas.clear();
                        datas.addAll(s.getResult().getLabelVoList());
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


    public void addTags(int labelid, int usrId) {
        MyApplication.retrofitClient.getRetrofit().create(UserApi.class)
                .doPraise(new AddLabelInfoApiVo(labelid, usrId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<String> stringResult) {
                        if (!stringResult.getCode().equals("200")) {
                            toast(stringResult.getMessage());
                            return;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e != null) {
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        ;
    }
}
