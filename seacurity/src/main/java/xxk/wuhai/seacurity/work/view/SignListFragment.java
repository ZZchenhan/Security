package xxk.wuhai.seacurity.work.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.weight.date.DatePickerDialogFragment;
import xxk.wuhai.seacurity.work.adapter.SignListAdapter;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.UserSignListResult;
import xxk.wuhai.seacurity.work.view.custorm.SignDetailHead;
import xxk.wuhai.seacurity.work.vo.GetUserSignVo;

public class SignListFragment extends Fragment {

    private RecyclerView recyclerView;
    private SignListAdapter signListAdapter;
    private List<UserSignListResult.UserSignInfoVosBean> datas = new ArrayList<>();
    private SignDetailHead signDetailHead;
    private void findViews(){
        recyclerView = root.findViewById(R.id.recyclerView);
        signDetailHead = new SignDetailHead(getContext());
        signDetailHead.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        signListAdapter = new SignListAdapter(R.layout.item_sign_detail,this.datas);
        signListAdapter.addHeaderView(signDetailHead);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        signListAdapter.bindToRecyclerView(recyclerView);
        signDetailHead.name.setText(MyApplication.userDetailInfo.getUserInfo().getName().length()<2?MyApplication.userDetailInfo.getUserInfo().getName():
                MyApplication.userDetailInfo.getUserInfo().getName().substring(MyApplication.userDetailInfo.getUserInfo().getName().length()-2,MyApplication.userDetailInfo.getUserInfo().getName().length()));
        signDetailHead.userName.setText(MyApplication.userDetailInfo.getUserInfo().getName());
        signListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getSignList(page,signDetailHead.tvDate.getText().toString());
            }
        },recyclerView);
        signDetailHead.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open();
            }
        });
    }
    private int page = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    View root;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null){
            root = inflater.inflate(R.layout.fragment_sign_list,null);
            findViews();
            signDetailHead.onCreate(savedInstanceState);
        }
        return root;
    }


    @Override
    public void onResume() {
        signDetailHead.onResume();
        super.onResume();
        page=1;
        getSignList(page,signDetailHead.tvDate.getText().toString());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        signDetailHead.onDestroy();
        if(datePickerDialogFragment!=null){
            datePickerDialogFragment.dismiss();
            isShow = false;
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        signDetailHead.onPause();
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        signDetailHead.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    private void  getSignList(final int page, String month){
        if(page == 1 && datas.size()>10){
            datas.clear();
            signListAdapter.notifyDataSetChanged();
        }
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class)
                .userSignList(new GetUserSignVo(month,page,20))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<UserSignListResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<UserSignListResult> userSignListResultResult) {
                        if(!userSignListResultResult.getCode().equals("200")){
                            ToastUtils.showLong(userSignListResultResult.getMessage());
                            return;
                        }
                        signDetailHead.signNumbers.setText(String.format("本月已签到%d次",userSignListResultResult.getResult().getCount()));
                        if(userSignListResultResult.getResult().getUserSignInfoVos()
                                ==null || userSignListResultResult.getResult().getUserSignInfoVos().size() == 0){
                            signListAdapter.loadMoreEnd();
                            return;
                        }
                        if(userSignListResultResult.getResult().getUserSignInfoVos().size()>20) {
                            signListAdapter.loadMoreComplete();
                        }else{
                            signListAdapter.loadMoreEnd();
                        }
                        SignListFragment.this.page++;
                        datas.addAll(userSignListResultResult.getResult().getUserSignInfoVos());
                        signDetailHead.setMarkes(datas);
                        signListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                            if(e != null){
                                ToastUtils.showShort(e.getMessage());
                            }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    DatePickerDialogFragment datePickerDialogFragment = null;
    private void open(){
        if(datePickerDialogFragment == null){
            isShow = true;
            datePickerDialogFragment = new DatePickerDialogFragment(true);
            datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
                @Override
                public void onDateChoose(int year, int month, int day) {
                    signDetailHead.tvDate.setText(year+"年"+month+"月");
                    getSignList(1,year+"-"+month);
                }
            });
        }
        FragmentManager fm = getFragmentManager();
        datePickerDialogFragment.show(fm, "dialog");
    }



    public static boolean isShow = false;
}
