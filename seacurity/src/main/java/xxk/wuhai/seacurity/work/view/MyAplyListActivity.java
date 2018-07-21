package xxk.wuhai.seacurity.work.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

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
import xxk.wuhai.seacurity.main.view.MainActivity;
import xxk.wuhai.seacurity.msg.view.ExamineActivity;
import xxk.wuhai.seacurity.msg.view.LeaveActivity;
import xxk.wuhai.seacurity.weight.AplyStausPopWindows;
import xxk.wuhai.seacurity.work.adapter.MyApplyAdapter;
import xxk.wuhai.seacurity.work.api.WorkDutyApi;
import xxk.wuhai.seacurity.work.bean.AplyResult;
import xxk.wuhai.seacurity.work.bean.ApprovalRecordListBean;
import xxk.wuhai.seacurity.work.vo.ApListVo;

public class MyAplyListActivity extends BaseActivity implements TextView.OnEditorActionListener {

    RecyclerView recyclerView;
    MyApplyAdapter adapter;
    private TextView all;
    private List<ApprovalRecordListBean> datas = new ArrayList<>();
    private EditText editText;
    AplyStausPopWindows stausPopWindows;

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
                return type == 0 ? "我提交的" : "我审核的";
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
        adapter.loadMoreEnd(true);
        editText = findViewById(R.id.edit);
        editText.setOnEditorActionListener(this);
        all = findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stausPopWindows == null) {
                    stausPopWindows = new AplyStausPopWindows(MyAplyListActivity.this);
                    stausPopWindows.setOnItemClickListener(new AplyStausPopWindows.OnItemClickListener() {
                        @Override
                        public void onItemnClick(String string, String type) {
                            all.setText(string);
                            hand = type;
                            stausPopWindows.dismiss();
                            page = 1;
                            intData(page);
                        }
                    });

                    stausPopWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            darkenBackground(1f);
                        }
                    });
                }
                stausPopWindows.showAsDropDown(all);
                darkenBackground(0.4f);
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                intData(page);
            }
        }, recyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (datas.get(position).getPatchTime() != null) {
                    //补签
                    ExamineActivity.openActivity(MyAplyListActivity.this,
                            datas.get(position).getApprovalRecordId(), type);
                } else {
                    LeaveActivity.openActivity(MyAplyListActivity.this,
                            datas.get(position).getApprovalRecordId(), type);
                }
            }
        });
        intData(page);
    }

    private static int type;
    private String contenxt;
    private String hand = "0";

    public static void openActivity(Context context, int type) {
        MyAplyListActivity.type = type;
        context.startActivity(new Intent(context, MyAplyListActivity.class));
    }

    private int page = 1;

    public void intData(final int page) {
        if (page == 1 && datas.size() > 0) {
            datas.clear();
            adapter.notifyDataSetChanged();
        }
        ApListVo apListVo = new ApListVo();
        apListVo.setHandle(hand);
        apListVo.setPageNum(page + "");
        apListVo.setPageSize("20");
        apListVo.setType(type + "");
        apListVo.setSearchContext(contenxt);
        MyApplication.retrofitClient.getRetrofit()
                .create(WorkDutyApi.class)
                .apList(apListVo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<AplyResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<AplyResult> stringResult) {
                        if (!stringResult.getCode().equals("200")) {
                            toast(stringResult.getMessage());
                            return;
                        }
                        adapter.loadMoreComplete();
                        MyAplyListActivity.this.page = page + 1;
                        editText.setText("");
                        contenxt = null;
                        if (stringResult.getResult() == null || stringResult.getResult().getApprovalRecordList() == null) {
                            toast("没有更多数据了");
                            adapter.loadMoreEnd();
                            return;
                        }
                        datas.addAll(stringResult.getResult().getApprovalRecordList());
                        adapter.notifyDataSetChanged();

                        if (!stringResult.getResult().isHaveNext()) {
                            adapter.loadMoreEnd();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage() != null) {
                            toast(e.getMessage());
                            adapter.loadMoreFail();
                            return;
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            // 当按了搜索之后关闭软键盘
            ((InputMethodManager) editText.getContext().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    MyAplyListActivity.this.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            contenxt = editText.getText().toString();
            if (contenxt.length() == 0) {
                contenxt = null;
            }
            page = 1;
            intData(page);
            return true;
        }
        return false;
    }


    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
