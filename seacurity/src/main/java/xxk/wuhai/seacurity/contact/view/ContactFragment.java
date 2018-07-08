package xxk.wuhai.seacurity.contact.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.bean.Result;
import xxk.wuhai.seacurity.contact.ContactApi;
import xxk.wuhai.seacurity.contact.adapter.ContactAdapter;
import xxk.wuhai.seacurity.contact.bean.ContactGroup;
import xxk.wuhai.seacurity.contact.bean.ContanctResult;
import xxk.wuhai.seacurity.contact.bean.DirDeptVoListBean;
import xxk.wuhai.seacurity.contact.bean.DirectoryVo;
import xxk.wuhai.seacurity.contact.utils.PinYin2Abbreviation;
import xxk.wuhai.seacurity.contact.vo.ApiGetDirectoryVo;
import xxk.wuhai.seacurity.weight.SideLetterBar;

/**
 * 描述 通讯录
 *
 * @author ch
 * @微信 xrbswo
 * @qq 869360026
 * @email 869360026@qq.com
 * @创建时间 2018/6/24 22:22
 */
public class ContactFragment extends Fragment {
    private List<String> strings = new ArrayList();
    private Map<String, Integer> maps = new HashMap<>();
    private RecyclerView recyclerView;

    private View root;

    private EditText etSearch;

    private ImageView ivSearch;

    private TextView currentDepartment;

    private SideLetterBar sideLetterBar;

    private ContactAdapter adapter;

    private TextView tvHint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(root == null) {
            root = inflater.inflate(R.layout.fragment_contact, null);
            fidViews();
        }
        return root;
    }

    private void fidViews(){
        recyclerView = root.findViewById(R.id.recyclerView);
        etSearch = root.findViewById(R.id.et_action);
        ivSearch = root.findViewById(R.id.iv_search);
        currentDepartment = root.findViewById(R.id.tv_current_depart);
        sideLetterBar = root.findViewById(R.id.side_letter_bar);
        tvHint = root.findViewById(R.id.tv_letter_overlay);
        initView();
    }

    private void initView(){
        sideLetterBar.setOverlay(tvHint);
        adapter = new ContactAdapter(datas);
        sideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                Integer location = maps.get(letter);
                if(null == location){
                    return;
                }
                Log.i("TAG","Location"+location);
                if(location!=-1) {
                    recyclerView.scrollToPosition(adapter.getHeaderLayoutCount() + location);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) recyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(adapter.getHeaderLayoutCount() + location, 0);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(!datas.get(position).isHeader) {
                    UserInfoActivity.openActivity(getContext(), datas.get(position).t);
                }
            }
        });
        currentDepartment.setText(getArguments() == null ||getArguments().getString("title") == null?"公司":getArguments().getString("title"));
        getData();
    }
    List<ContactGroup> datas = new ArrayList<>();


    private void getData(){
        MyApplication.retrofitClient.getRetrofit().create(ContactApi.class)
                .getDirectory(new ApiGetDirectoryVo(getArguments() != null?getArguments().getInt("depId",0):0,""))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<ContanctResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Result<ContanctResult> contanctResultResult) {
                        if(!contanctResultResult.getCode().equals("200")){
                            Toast.makeText(getContext(),contanctResultResult.getMessage(),Toast.LENGTH_LONG).show();
                            return;
                        }
                        setData(contanctResultResult.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setData(ContanctResult contanctResult){
        setHead(contanctResult.getDirDeptVoList());
        setList(contanctResult.getDirectoryVoList());
        adapter.notifyDataSetChanged();
    }

    private void setList(List<DirectoryVo> directoryVos){
        strings.clear();
        maps.clear();
        if(directoryVos == null){
            return;
        }
        int postion = 0;
        DirectoryVo.sort(directoryVos);
        String lastPinying = "";
        for(int i=0; i<directoryVos.size();i++){
            DirectoryVo contactBean =directoryVos.get(i);
            if(contactBean.getName() != null && contactBean.getName().length() !=0) {
                String curent = PinYin2Abbreviation.cn2py(contactBean.getName().substring(0,1));
                if(curent.equals(lastPinying)){
                    datas.add(new ContactGroup(contactBean));
                }else{
                    strings.add(curent);
                    maps.put(curent,postion);
                    datas.add(new ContactGroup(true,curent));
                    datas.add(new ContactGroup(contactBean));
                    postion++;
                }
                lastPinying = curent;
                postion++;
            }else{
                postion++;
                datas.add(new ContactGroup(contactBean));
            }
        }
        adapter.notifyDataSetChanged();
//        sideLetterBar.b = strings;
//        sideLetterBar.invalidate();
    }

    private void setHead(List<DirDeptVoListBean> dirDeptVoListBeans){
        if(dirDeptVoListBeans == null){
            return;
        }
        for (DirDeptVoListBean vo:dirDeptVoListBeans){
            initHeadItem(vo);
        }
    }


    private void initHeadItem(final DirDeptVoListBean deptVoListBean){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_contact_head,null);
        TextView tvName = view.findViewById(R.id.name);
        tvName.setText(deptVoListBean.getDeptName()+"");
        adapter.addHeaderView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactActivity.openActivity(getContext(),deptVoListBean.getDeptName(),deptVoListBean.getDeptId());
            }
        });
    }


}
