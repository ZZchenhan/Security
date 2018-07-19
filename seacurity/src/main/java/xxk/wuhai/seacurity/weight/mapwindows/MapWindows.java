package xxk.wuhai.seacurity.weight.mapwindows;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.Marker;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/7/20.
 *
 * @QQ 869360026
 */

public class MapWindows extends LinearLayout implements AMap.InfoWindowAdapter {
    public MapWindows(Context context) {
        this(context,null);
    }

    public MapWindows(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_map_windows,null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return this;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return this;
    }
}
