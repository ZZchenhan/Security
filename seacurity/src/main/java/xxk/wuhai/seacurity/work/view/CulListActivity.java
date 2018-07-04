package xxk.wuhai.seacurity.work.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.CommonNavagation;
import xxk.wuhai.seacurity.work.adapter.CulAdapter;

public class CulListActivity extends BaseActivity {
    private List<String> data = new ArrayList<>();

    CulAdapter culAdapter;

    RecyclerView recyclerView;
    @Override
    public int layoutId() {
        return R.layout.activity_cul_list;
    }

    @Override
    public IBaseNavagation navagation() {
        CommonNavagation leftIconNavagation = (CommonNavagation) new CommonNavagation(this) {
            @Override
            public String title() {
                return "我提交的爆料";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftIconNavagation.setTitleColor(R.color.white);
        leftIconNavagation.setRight("添加");
        leftIconNavagation.setRight(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CulListActivity.this, CulActivity.class));
            }
        });
        return leftIconNavagation;
    }

    @Override
    public void initView() {

    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        culAdapter = new CulAdapter(data);
        culAdapter.bindToRecyclerView(recyclerView);
        culAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CulDetailActivity.openActivity(CulListActivity.this,CulDetailActivity.class);
            }
        });
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        culAdapter.notifyDataSetChanged();
    }

}
