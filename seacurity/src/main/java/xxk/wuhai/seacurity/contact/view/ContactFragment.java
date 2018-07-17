package xxk.wuhai.seacurity.contact.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import xxk.wuhai.seacurity.main.view.MainActivity;
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



    private TextView currentDepartment;

    private SideLetterBar sideLetterBar;

    private ContactAdapter adapter;

    private TextView tvHint;

    private LinearLayout llHint;

    private TextView tvNumber;

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
        llHint = root.findViewById(R.id.ll_tint);
        currentDepartment = root.findViewById(R.id.tv_current_depart);
        sideLetterBar = root.findViewById(R.id.side_letter_bar);
        tvNumber = root.findViewById(R.id.tvHint);
        tvHint = root.findViewById(R.id.tv_letter_overlay);
        root.findViewById(R.id.left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openOrClose();
            }
        });
        if(getArguments() == null) {
            root.findViewById(R.id.frame_navigation)
                    .setVisibility(View.VISIBLE);
        }
        initView();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(etSearch.getText().length() == 0){
                    llHint.setVisibility(View.VISIBLE);
                }else{
                    llHint.setVisibility(View.GONE);
                }
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    getData();
                    closeBroad();
                    return true;
                }
                    return false;
            }
        });
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
                .getDirectory(new ApiGetDirectoryVo(getArguments() != null?getArguments().getInt("depId",0):0,etSearch.getText().toString()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<ContanctResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                            etSearch.setText("");
                            datas.clear();
                            adapter.notifyDataSetChanged();
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
        tvNumber.setText("在全部" + contanctResult.getDirectoryVoList()==null?"0": contanctResult.getDirectoryVoList().size() + "个人中搜索");
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
                String curent = PinYin2Abbreviation.cn2py(contactBean.getName().substring(0,1)).toUpperCase();
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


    public void closeBroad(){
        ((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).
                hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
