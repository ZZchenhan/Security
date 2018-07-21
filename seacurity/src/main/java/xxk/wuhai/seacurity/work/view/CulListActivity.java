package xxk.wuhai.seacurity.work.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.utils.DeviceUtils;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.CommonNavagation;
import xxk.wuhai.seacurity.work.adapter.CulAdapter;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.ClueBursList;
import xxk.wuhai.seacurity.work.vo.ClueBurstListVo;

public class CulListActivity extends BaseActivity {
    private List<ClueBursList.ClueBurstListBean> data = new ArrayList<>();

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
                return "我提交的信息";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftIconNavagation.setTitleColor(R.color.white);
        leftIconNavagation.setRight("");

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
                CulDetailActivity.openActivity(CulListActivity.this,data.get(position).getClueBurstId());
            }
        });
        culAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(page);
            }
        },recyclerView);
        getData(page);
    }

    private int page = 1;
    private void getData(final int page){
        if(page == 1 &&data.size()>0){
            data.clear();
            culAdapter.notifyDataSetChanged();
        }
        final ClueBurstListVo clueBursList = new ClueBurstListVo();
        clueBursList.setPageNum(page);
        clueBursList.setPageSize(20);
        clueBursList.setType("1");
        clueBursList.setStatus("0");
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .clueBurstList(clueBursList)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<ClueBursList>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ClueBursList> clueBursListResult) {
                        if(!clueBursListResult.getCode().equals("200")){
                            toast(clueBursListResult.getMessage());
                            return;
                        }
                        CulListActivity.this.page = page+1;
                        if(clueBursListResult.getResult().getClueBurstList() == null){
                            toast("没有更多数据");
                            return;
                        }
                        data.addAll(clueBursListResult.getResult().getClueBurstList());
                        culAdapter.notifyDataSetChanged();
                        if(!clueBursListResult.getResult().isHaveNext()){
                            culAdapter.loadMoreEnd();
                        }else{
                            culAdapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e!=null){
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
