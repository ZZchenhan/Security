package xxk.wuhai.seacurity.work.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.adapter.MyApplyAdapter;

public class MyAplyListActivity extends BaseActivity {

    RecyclerView recyclerView;
    MyApplyAdapter adapter;
    private List<String> datas = new ArrayList<>();
    @Override
    public int layoutId() {
        return R.layout.activity_my_aply_list;
    }
    LeftIconNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
         leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return type == 0?"我审核的":"我提交的";
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
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyApplyAdapter(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.bindToRecyclerView(recyclerView);
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        adapter.notifyDataSetChanged();
    }

    private static int type;
    public static void openActivity(Context context,int type){
        MyAplyListActivity.type = type;
        context.startActivity(new Intent(context,MyAplyListActivity.class));
    }

}
