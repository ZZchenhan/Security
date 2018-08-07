package com.hz.junxinbaoan.weight.mapwindows;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.Marker;

import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.work.view.RecordFaileActivity;

/**
 * Created by 86936 on 2018/7/20.
 *
 * @QQ 869360026
 */

public class MapWindows  implements AMap.InfoWindowAdapter {
    private Context mContext;


    public MapWindows(Context context){
        this.mContext = context;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        initData(marker);
        View view = initView();
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
    private void initData(Marker marker) {

    }

    private View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_map_windows, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,RecordFaileActivity.class);
                intent.putExtra("distance",distance);
                intent.putExtra("location",location);
                intent.putExtra("current",poi);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    private String distance;
    private String location;
    private String poi;

    public void initData(String distance,String location,String poi){
        this.distance = distance;
        this.location =location;
        this.poi = poi;
    }
}
