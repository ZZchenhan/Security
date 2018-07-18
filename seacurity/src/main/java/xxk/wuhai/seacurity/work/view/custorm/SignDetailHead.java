package xxk.wuhai.seacurity.work.view.custorm;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.work.bean.UserSignListResult;
import xxk.wuhai.seacurity.work.view.CulDetailActivity;

/**
 * Created by 86936 on 2018/6/29.
 *
 * @QQ 869360026
 */

public class SignDetailHead extends LinearLayout {
    View root;

    public MapView mapView;
    public TextView name;
    public TextView userName;
    public TextView signNumbers;
    public TextView tvDate;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
    public SignDetailHead(Context context) {
        super(context);
        root = LayoutInflater.from(context).inflate(R.layout.item_sign_list_head,null);
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(root);
        mapView = root.findViewById(R.id.tv_map);
        name = root.findViewById(R.id.name);
        userName = root.findViewById(R.id.user_name);
        signNumbers = root.findViewById(R.id.sign_num);
        tvDate = root.findViewById(R.id.date);
        tvDate.setText(simpleDateFormat.format(new Date()));
        tvDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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


    /**
     * 设置地图标记，并且唯一
     * @param datas
     */
    public void setMarkes(List<UserSignListResult.UserSignInfoVosBean> datas){
        mapView.getMap().clear();
        boolean zoom = true;
        for(UserSignListResult.UserSignInfoVosBean info:datas){
            if(info.getAttendanceLocation() == null || info.getAttendanceLocation().equals("")){
                continue;
            }
            LatLng latLng = new LatLng(Double.parseDouble(info.getAttendanceLat()),Double.parseDouble(info.getAttendanceLon()));
            Marker marker = mapView.getMap().addMarker(new MarkerOptions().position(latLng)
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getContext().getResources(), R.mipmap.icon_poi_select))));
            if(zoom){
                zoom = false;
                mapView.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));
            }
        }
    }
}
