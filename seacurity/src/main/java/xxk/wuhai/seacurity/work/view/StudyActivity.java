package xxk.wuhai.seacurity.work.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.adapter.StudyAdapter;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.StudyListResult;
import xxk.wuhai.seacurity.work.vo.GetStudyNoticeListVo;

public class StudyActivity extends BaseActivity {

    RecyclerView recyclerView;

    StudyAdapter studyAdapter;

    List<StudyListResult.StudyNoticeInfoListBean> datas = new ArrayList<>();

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
        studyAdapter.setEnableLoadMore(true);
        studyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initData(page);
            }
        },recyclerView);
        initData(page);
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
                WebActivity.openActivity(StudyActivity.this,"学习中心",datas.get(position).getStudyNoticeId());
            }
        });
    }
    private int page = 1;
    private void initData(final int page){
        GetStudyNoticeListVo getStudyNoticeListVo = new GetStudyNoticeListVo();
        getStudyNoticeListVo.setPageNum(page);
        getStudyNoticeListVo.setPageSize(20);
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .getStudyNoticeList(getStudyNoticeListVo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<StudyListResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<StudyListResult> studyListResultResult) {
                            if(!studyListResultResult.getCode().equals("200")){
                                toast(studyListResultResult.getMessage());
                                return;
                            }
                            studyAdapter.loadMoreComplete();
                            if(studyListResultResult.getResult().getStudyNoticeInfoList() == null){
                                studyAdapter.loadMoreEnd();
                                toast("当前没有更多数据了");
                                return;
                            }
                            StudyActivity.this.page = page +1;
                            if(!studyListResultResult.getResult().isHaveNext()){
                                studyAdapter.loadMoreEnd();
                            }
                            datas.addAll(studyListResultResult.getResult().getStudyNoticeInfoList());
                            studyAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e!=null){
                            studyAdapter.loadMoreFail();
                            toast(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
