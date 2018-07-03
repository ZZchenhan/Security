package xxk.wuhai.seacurity.work.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.adapter.StudyAdapter;

public class StudyActivity extends BaseActivity {

    RecyclerView recyclerView;

    StudyAdapter studyAdapter;

    List<String> datas = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.activity_study;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "学习中心";
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
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        studyAdapter.notifyDataSetChanged();
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        studyAdapter = new StudyAdapter(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studyAdapter.bindToRecyclerView(recyclerView);
        studyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebActivity.openActivity(StudyActivity.this,"学习中心");
            }
        });
    }

}
