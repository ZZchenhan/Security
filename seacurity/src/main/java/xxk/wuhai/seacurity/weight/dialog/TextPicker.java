package xxk.wuhai.seacurity.weight.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import xxk.wuhai.seacurity.weight.WheelPicker;

/**
 * 日期选择
 * Created by ycuwq on 17-12-28.
 */
public class TextPicker extends WheelPicker<TypeHelp.Values> {


    private TypeHelp.Values item;


    public TextPicker(Context context) {
        this(context, null);
    }

    public TextPicker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
	    setItemMaximumWidthText("00");
	    setDataFormat(null);

	    setOnWheelChangeListener(new OnWheelChangeListener<TypeHelp.Values>() {
            @Override
            public void onWheelSelected(TypeHelp.Values item, int position) {
                TextPicker.this.item = item;
            }
        });
    }

    public TypeHelp.Values getmSelectedText() {
        return item;
    }

    @Override
    public void setDataList(@NonNull List<TypeHelp.Values> dataList) {
        super.setDataList(dataList);
        setCurrentPosition(0);
        item = dataList.get(0);
    }
}
