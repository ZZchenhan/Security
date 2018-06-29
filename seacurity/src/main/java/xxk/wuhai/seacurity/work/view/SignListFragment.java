package xxk.wuhai.seacurity.work.view;

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

import com.amap.api.maps2d.MapView;

import java.util.ArrayList;
import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.work.adapter.SignListAdapter;
import xxk.wuhai.seacurity.work.view.custorm.SignDetailHead;

public class SignListFragment extends Fragment {

    private RecyclerView recyclerView;
    private SignListAdapter signListAdapter;
    private List<String> datas = new ArrayList<>();
    private SignDetailHead signDetailHead;
    private void findViews(){
        recyclerView = root.findViewById(R.id.recyclerView);
        signDetailHead = new SignDetailHead(getContext());
        signDetailHead.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        signListAdapter = new SignListAdapter(R.layout.item_sign_detail,this.datas);
        signListAdapter.addHeaderView(signDetailHead);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        signListAdapter.bindToRecyclerView(recyclerView);


        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        signListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    View root;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null){
            root = inflater.inflate(R.layout.activity_sign_list,null);
            findViews();
            signDetailHead.onCreate(savedInstanceState);
        }
        return root;
    }


    @Override
    public void onResume() {
        signDetailHead.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        signDetailHead.onDestroy();
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
}
