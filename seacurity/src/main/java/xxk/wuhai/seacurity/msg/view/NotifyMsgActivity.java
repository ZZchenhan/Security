package xxk.wuhai.seacurity.msg.view;

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
import xxk.wuhai.seacurity.msg.adapter.NotifyMsgAdapter;
import xxk.wuhai.seacurity.msg.bean.MessageInfoListBean;

public class NotifyMsgActivity extends BaseActivity {
    RecyclerView recyclerView;

    List<MessageInfoListBean> datas = new ArrayList<>();

    private NotifyMsgAdapter notifyMsgAdapter;

    @Override
    public int layoutId() {
        return R.layout.activity_notify_msg;
    }

    @Override
    public IBaseNavagation navagation() {
        LeftIconNavagation leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "提醒打卡消息";
            }
        };
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
        datas.add(messageInfoListBean);
        notifyMsgAdapter.notifyDataSetChanged();
    }

    @Override
    public void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
        notifyMsgAdapter = new NotifyMsgAdapter(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notifyMsgAdapter.bindToRecyclerView(recyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        messageInfoListBean = null;
    }

    private static MessageInfoListBean messageInfoListBean;
    public static void openActivity(Context context, MessageInfoListBean messageInfoListBean){
        NotifyMsgActivity.messageInfoListBean = messageInfoListBean;
        context.startActivity(new Intent(context,NotifyMsgActivity.class));
    }



}
