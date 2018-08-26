package com.hz.junxinbaoan.msg.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.utils.ToastUtils;
import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.main.view.MainActivity;
import com.hz.junxinbaoan.msg.MsgApi;
import com.hz.junxinbaoan.msg.adapter.MsgAdapter;
import com.hz.junxinbaoan.msg.bean.GetMessageDetailResult;
import com.hz.junxinbaoan.msg.bean.MessageInfoListBean;
import com.hz.junxinbaoan.msg.bean.MsgResult;
import com.hz.junxinbaoan.msg.vo.GetMessageDetailVO;
import com.hz.junxinbaoan.msg.vo.GetMessageListVo;
import com.hz.junxinbaoan.weight.MyPopWindows;
import com.hz.junxinbaoan.work.view.RecordActivity;

/**
 * 描述 消息fragment
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 22:22
 */
public class MsgFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;

    private TextView tvSelect;

    private LinearLayout llSelect;
    private RecyclerView recyclerView;

    private View root;

    private List<MessageInfoListBean> datas = new ArrayList<>();

    private MsgAdapter msgAdapter;

    private TextView hinit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_msg, null);
            findViews();
        }
        return root;
    }

    MyPopWindows popWindows;

    private void findViews() {
        llSelect = root.findViewById(R.id.ll_select);
        tvSelect = root.findViewById(R.id.select_text);
        recyclerView = root.findViewById(R.id.recyclerView);
        hinit = root.findViewById(R.id.hinit);
        msgAdapter = new MsgAdapter(datas);
        root.findViewById(R.id.left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openOrClose();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        msgAdapter.bindToRecyclerView(recyclerView);
        msgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (datas.get(position).getMessageTypeId()) {
                    // 2：排班变动 3：审批提醒消息 4：提醒打卡消息
                    case "1":
                        CompanyMsgActivity.openActivity(getContext(), datas.get(position).getMessageTypeId(), datas.get(position).getMessageId());
                        break;
                    case "2":
                        startActivity(new Intent(getContext(), DutyMsgActivity.class)
                                .putExtra("msgId", datas.get(position).getMessageId()).putExtra("msgType", datas.get(position).getMessageTypeId()));
                        break;
                    case "3":
                        ExamineActivity2.openActivity(getContext(), Integer.parseInt(datas.get(position).getAssociatedId()), datas.get(position).getMessageId());
                        break;
                    case "4":
                        //NotifyMsgActivity.openActivity(getContext(),datas.get(position));
//                        RecordActivity.openActivity(getContext(),RecordActivity.class);
                        startActivity(new Intent(getContext(),RecordActivity.class).putExtra("messageId",datas.get(position).getMessageId()));
//                        getNotifyMsg(datas.get(position).getMessageId(),datas.get(position).getMessageTypeId());
                        break;
                    case "6":
                        CompanyMsgActivity.openActivity(getContext(), datas.get(position).getMessageTypeId(), datas.get(position).getMessageId());
                        break;
                }
//                if(datas.get(position).getMessageTypeId() == 0){
//                    NotifyMsgActivity.openActivity(getContext(),NotifyMsgActivity.class);
//                }else if(position == 1){
//                    ExamineActivity.openActivity(getContext(),ExamineActivity.class);
//                }else if(position == 2){
//                    LeaveActivity.openActivity(getContext(),LeaveActivity.class);
//                }else if(position ==3){
//                    DutyMsgActivity.openActivity(getContext(),DutyMsgActivity.class);
//                }
            }
        });
        swipeRefreshLayout = root.findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(root.getContext().getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getMsg(page);
            }
        });
        msgAdapter.setEnableLoadMore(true);
        msgAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getMsg(page);
            }
        }, recyclerView);
        llSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popWindows == null) {
                    popWindows = new MyPopWindows(getContext());
                    popWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            darkenBackground(1f);
                        }
                    });
                    popWindows.setOnItemClickListener(new MyPopWindows.OnItemClickListener() {
                        @Override
                        public void onItemnClick(String string, String type) {
                            tvSelect.setText(string);
                            popWindows.dismiss();
                            MsgFragment.this.type = type;
                            MsgFragment.this.page = 1;
                            getMsg(MsgFragment.this.page);
                        }
                    });
                }
                darkenBackground(0.4f);
                popWindows.showAsDropDown(llSelect);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        datas.clear();
        page = 1;
        getMsg(page);
    }

    private String type = "";
    private int page = 1;

    public void getMsg(int page) {
        if (page == 1) {
            datas.clear();
        }
        msgAdapter.notifyDataSetChanged();
        MyApplication.retrofitClient.getRetrofit().create(MsgApi.class)
                .getMsgList(new GetMessageListVo(type, page, 20, "")).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<MsgResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<MsgResult> stringResult) {
                        if (getActivity().isFinishing()) {
                            return;
                        }
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        if (stringResult.getCode().equals("200")) {
                            if (stringResult.getResult().getUnreadNum() >= 0) {
                                hinit.setText("最近有" + stringResult.getResult().getUnreadNum() + "条消息未读");
                                hinit.setVisibility(View.VISIBLE);
                                hinit.postDelayed(() -> hinit.setVisibility(View.GONE), 3000);
                            }

                            if (stringResult.getResult().getMessageInfoList() != null && stringResult.getResult().getMessageInfoList().size() != 0) {
                                datas.addAll(stringResult.getResult().getMessageInfoList());
                                MsgFragment.this.page = MsgFragment.this.page + 1;
                            } else {
                                msgAdapter.loadMoreEnd();
                            }
                            msgAdapter.notifyDataSetChanged();
                            if (stringResult.getResult().isHaveNext()) {
                                msgAdapter.loadMoreComplete();
                            } else {
                                msgAdapter.loadMoreEnd();
                            }
                        } else {
                            msgAdapter.loadMoreFail();
                            toast(stringResult.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (getActivity()== null|| getActivity().isFinishing()) {
                            return;
                        }
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        msgAdapter.loadMoreFail();
                        toast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgcolor;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }


    private void getNotifyMsg(int msgId, String msgType) {
        MyApplication.retrofitClient.getRetrofit().create(MsgApi.class)
                .getMsgDetails(
                        new GetMessageDetailVO(msgId,
                                msgType))

                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<GetMessageDetailResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<GetMessageDetailResult> getMessageDetailResultResult) {
                        if (!getMessageDetailResultResult.getCode().equals("200")) {
                           toast(getMessageDetailResultResult.getMessage());
                            return;
                        }
                        datas.clear();
                        page = 1;
                        getMsg(page);
                    }

                    @Override
                    public void onError(Throwable e) {
                       toast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void toast(String msg) {
        ToastUtils.makeText(getContext(), msg, ToastUtils.LENGTH_LONG).show();
    }
}
