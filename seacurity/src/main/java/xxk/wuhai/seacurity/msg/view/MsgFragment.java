package xxk.wuhai.seacurity.msg.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.msg.adapter.MsgAdapter;
import xxk.wuhai.seacurity.msg.bean.MsgBean;

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

    private TextView tvSelect;

    private RecyclerView recyclerView;

    private View root;

    private List<MsgBean> datas = new ArrayList<>();

    private MsgAdapter msgAdapter;

    private TextView hinit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if(root ==null){
           root = inflater.inflate(R.layout.fragment_msg,null);
           findViews();
       }
        return root;
    }


    private void findViews(){
        tvSelect = root.findViewById(R.id.select_text);
        recyclerView = root.findViewById(R.id.recyclerView);
        hinit = root.findViewById(R.id.hinit);
        msgAdapter = new MsgAdapter(datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        msgAdapter.bindToRecyclerView(recyclerView);
        msgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(position == 0){
                    NotifyMsgActivity.openActivity(getContext(),NotifyMsgActivity.class);
                }else if(position == 1){
                    ExamineActivity.openActivity(getContext(),ExamineActivity.class);
                }else if(position == 2){
                    LeaveActivity.openActivity(getContext(),LeaveActivity.class);
                }else if(position ==3){
                    DutyMsgActivity.openActivity(getContext(),DutyMsgActivity.class);
                }
            }
        });
        similart();
    }

    private void similart(){
        datas.add(new MsgBean(0));
        datas.add(new MsgBean(0));
        datas.add(new MsgBean(0));
        datas.add(new MsgBean(1));
        datas.add(new MsgBean(1));
        datas.add(new MsgBean(1));
        datas.add(new MsgBean(0));
        datas.add(new MsgBean(0));
        datas.add(new MsgBean(1));
        datas.add(new MsgBean(1));
        datas.add(new MsgBean(1));
        msgAdapter.notifyDataSetChanged();
    }
}
