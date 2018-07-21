package xxk.wuhai.seacurity.work.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.presenter.IBasePresenter;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.CommonNavagation;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;
import xxk.wuhai.seacurity.work.adapter.PoiAdapter;
import xxk.wuhai.seacurity.work.presenter.IUpdateLocaionPresenter;
import xxk.wuhai.seacurity.work.view.itf.IUpdateLocationView;

/**
 * 地址调整
 */
public class LocationUpdateActivity extends BaseActivity implements IUpdateLocationView{

    /**
     * 数据
     */
    List<PoiItem> datas = new ArrayList<>();

    /**
     * 输入
     */
    private EditText editText;

    /**
     * 地图
     */
    private MapView mapView;

    /**
     * 搜索结果列表
     */
    private RecyclerView recyclerView;

    private IUpdateLocaionPresenter updateLocaionPresenter;

    @Override
    public IBasePresenter createPrensenter() {
        updateLocaionPresenter = new IUpdateLocaionPresenter(this);
        return updateLocaionPresenter;
    }

    @Override
    public int layoutId() {
        return R.layout.activity_location_update;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        mapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public IBaseNavagation navagation() {
        CommonNavagation leftIconNavagation = new CommonNavagation(this) {
            @Override
            public String title() {
                return "地址微调";
            }
        }.setRight("确定").setRight(R.drawable.bg_item).setRight(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectLatLng == null){
                    finish();
                }else {
                    Intent data = new Intent();
                    data.putExtra("address", selectAddress);
                    data.putExtra("latlng", selectLatLng);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        }).setRightColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        return leftIconNavagation;
    }
    PoiAdapter poiAdapter;
    @Override
    public void initView() {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    datas.clear();
                    InputMethodManager imm = (InputMethodManager) LocationUpdateActivity.this
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    updateLocaionPresenter.search(editText,city);
                    return true;
                }
                return false;
            }
        });
        updateLocaionPresenter.initMap(mapView);
        poiAdapter = new PoiAdapter(R.layout.item_poi,datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        poiAdapter.bindToRecyclerView(recyclerView);
        //poiAdapter.setEnableLoadMore(true);
//        poiAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                updateLocaionPresenter.loadMore();
//            }
//        },recyclerView);
        poiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PoiItem item = datas.get(position);
                LatLng latlng = new LatLng(item.getLatLonPoint().getLatitude(),item.getLatLonPoint().getLongitude());
                if(AMapUtils.calculateLineDistance(locionLatlng,latlng)<=200) {
                    updateLocaionPresenter.onItemClick(datas.get(position), mapView);
                    selectLatLng =  latlng;
                    selectAddress =item.getProvinceName()+item.getCityName()+item.getBusinessArea()+item.getTitle();
                    poiAdapter.setChckPostion(position);
                    poiAdapter.notifyDataSetChanged();
                }else{
                    toast("不在打卡范围之类");
                    selectLatLng =null;
                    selectAddress = null;
                    return;
                }
            }
        });
        TextView empty = new TextView(this);
        ViewGroup.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        empty.setGravity(Gravity.CENTER);
        empty.setBackgroundColor(Color.WHITE);
        empty.setLayoutParams(params);
        empty.setText("请输入搜索地址");
        poiAdapter.setEmptyView(empty);
    }

    @Override
    public void findViews() {
        mapView = findViewById(R.id.map_view);
        editText = findViewById(R.id.et_input);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void poiResult(List<PoiItem> poiItems) {
        poiAdapter.loadMoreComplete();
        datas.clear();
        poiAdapter.setChckPostion(-1);
        if(poiItems != null && poiItems.size() !=0){
            datas.addAll(poiItems);
        }
        poiAdapter.notifyDataSetChanged();
    }


    public static String city;
    private static LatLng locionLatlng;
    private LatLng selectLatLng;
    private String selectAddress;

    public static void openActivity(Activity activity,String city,LatLng latLng){
        LocationUpdateActivity.city = city;
        LocationUpdateActivity.locionLatlng = latLng;
        openActivity(activity,LocationUpdateActivity.class,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
