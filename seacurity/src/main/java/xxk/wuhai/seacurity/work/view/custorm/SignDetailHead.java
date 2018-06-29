package xxk.wuhai.seacurity.work.view.custorm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.MapView;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/6/29.
 *
 * @QQ 869360026
 */

public class SignDetailHead extends LinearLayout {
    View root;

    private MapView mapView;
    private TextView name;
    private TextView userName;
    private TextView signNumbers;
    private TextView tvDate;


    public SignDetailHead(Context context) {
        super(context);
        root = LayoutInflater.from(context).inflate(R.layout.item_sign_list_head,null);
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(root);

        mapView = root.findViewById(R.id.tv_map);
        name = root.findViewById(R.id.name);
        userName = root.findViewById(R.id.user_name);
        signNumbers = root.findViewById(R.id.user_name);
        tvDate = root.findViewById(R.id.date);

        initMap(mapView);
    }

    public void onCreate(Bundle bundle){
        mapView.onCreate(bundle);
    }


    public void onResume() {
        mapView.onResume();
    }


    public void onDestroy() {
        mapView.onDestroy();
    }


    public void onPause() {
        mapView.onPause();

    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        mapView.onSaveInstanceState(outState);
    }



    /**
     * 初始化道德地图
     *
     * @param mapView
     */
    public void initMap(MapView mapView) {
        mapView.getMap().getUiSettings().setZoomControlsEnabled(false);//缩放按钮
        mapView.getMap().getUiSettings().setCompassEnabled(false);//指南针
        mapView.getMap().getUiSettings().setMyLocationButtonEnabled(false); //显示默认的定位按钮
        mapView.getMap().getUiSettings().setScaleControlsEnabled(false); //控制比例尺控件是否显示
    }

}
