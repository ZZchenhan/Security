package xxk.wuhai.seacurity.work.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.RecoderBean;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.adapter.RecordAdapter;

/**
 * 用户打卡页面
 */
public class RecordActivity extends BaseActivity {
    /**
     * 导航栏
     */
    LeftIconNavagation leftIconNavagation;


    private RecyclerView recyclerView;


    private RecordAdapter recordAdapter;

    private List<RecoderBean> recoderBeans = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.activity_record;
    }

    @Override
    public IBaseNavagation navagation() {
        leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this);
        leftIconNavagation.setTvTitle(new SimpleDateFormat("上班打卡 MM月dd日").format(new Date()));
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return leftIconNavagation;
    }

    @Override
    public void initView() {
        recordAdapter = new RecordAdapter(recoderBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recordAdapter.bindToRecyclerView(recyclerView);
        recordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_record:
                        SupplementSignActivity.openActivity(RecordActivity.this,SupplementSignActivity.class);
                        break;
                }
            }
        });

        recoderBeans.add(new RecoderBean(0));
        recoderBeans.add(new RecoderBean(1));
        recoderBeans.add(new RecoderBean(0));
        recoderBeans.add(new RecoderBean(0));
        recoderBeans.add(new RecoderBean(1));
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

}
