package xxk.wuhai.seacurity.work.view;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.amap.api.maps2d.MapView;

import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;
import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.common.navagation.CommonNavagation;
import xxk.wuhai.seacurity.common.navagation.LeftIconNavagation;

/**
 * 地址调整
 */
public class LocationUpdateActivity extends BaseActivity {

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
                toast("确认");
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

    @Override
    public void initView() {
        mapView = findViewById(R.id.map_view);
        editText = findViewById(R.id.et_input);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void findViews() {

    }
}
