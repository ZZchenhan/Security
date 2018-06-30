package xxk.wuhai.seacurity.me.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.contact.adapter.ImpresssionAdapter;
import xxk.wuhai.seacurity.contact.bean.TagBean;

public class MeTAgActivity extends BaseActivity {


    private TextView tvNumbers;

    private RecyclerView recyclerView;

    private ImpresssionAdapter impresssionAdapter;

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
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        impresssionAdapter = new ImpresssionAdapter(tagBeans);
        impresssionAdapter.bindToRecyclerView(recyclerView);
        init();
    }

    @Override
    public void findViews() {
        tvNumbers = findViewById(R.id.numbers);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void init(){
        tagBeans.add(new TagBean(true,"我是你"));
        tagBeans.add(new TagBean(false,"我是你"));
        tagBeans.add(new TagBean(true,"我是你"));
        tagBeans.add(new TagBean(true,"我4是你"));
        tagBeans.add(new TagBean(true,"我是3你"));
        tagBeans.add(new TagBean(false,"我3是你"));
        tagBeans.add(new TagBean(false,"我3是你"));
        tagBeans.add(new TagBean(false,"我3是你"));
        tagBeans.add(new TagBean(true,"我3是你"));
        impresssionAdapter.notifyDataSetChanged();
    }
}
