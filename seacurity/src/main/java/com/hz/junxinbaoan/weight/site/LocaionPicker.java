package com.hz.junxinbaoan.weight.site;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.List;

import com.hz.junxinbaoan.weight.WheelPicker;

/**
 * 日期选择
 * Created by ycuwq on 17-12-28.
 */
public class LocaionPicker<T> extends WheelPicker<T> {


    private T item;


    public LocaionPicker(Context context) {
        this(context, null);
    }

    public LocaionPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LocaionPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
	    setItemMaximumWidthText("00");
	    setDataFormat(null);

	    setOnWheelChangeListener(new OnWheelChangeListener<T>() {
            @Override
            public void onWheelSelected(T item, int position) {
                LocaionPicker.this.item = item;
            }
        });
    }

    public T getmSelectedText() {
        return item;
    }

    @Override
    public void setDataList(@NonNull List<T> dataList) {
        super.setDataList(dataList);
        setCurrentPosition(0);
        if(dataList.size() == 0){
           return;
        }
        item = dataList.get(0);
    }
}
