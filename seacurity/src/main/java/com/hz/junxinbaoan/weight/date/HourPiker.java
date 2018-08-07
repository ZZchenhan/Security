package com.hz.junxinbaoan.weight.date;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hz.junxinbaoan.weight.WheelPicker;

/**
 * 日期选择
 * Created by ycuwq on 17-12-28.
 */
public class HourPiker extends WheelPicker<Integer> {


    private int mSelectedHour;

    private OnHourSelectedListener mOnHourSelectedListener;

    public HourPiker(Context context) {
        this(context, null);
    }

    public HourPiker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HourPiker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItemMaximumWidthText("00");
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(2);
        setDataFormat(numberFormat);

        Calendar.getInstance().clear();
        mSelectedHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        updateMonth();
        setSelectedMonth(mSelectedHour, false);
        mSelectedHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        setOnWheelChangeListener(new OnWheelChangeListener<Integer>() {
            @Override
            public void onWheelSelected(Integer item, int position) {
                mSelectedHour = item;
                if (mOnHourSelectedListener != null) {
                    mOnHourSelectedListener.onHourselect(item);
                }
            }
        });
    }

    public void updateMonth() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 23; i++) {
            list.add(i);
        }
        list.add(0);
        setDataList(list);
    }

    public int getSelectedMonth() {
        return mSelectedHour;
    }

    public void setSelectedMonth(int selectedMonth) {
        setSelectedMonth(selectedMonth, true);
    }

    public void setSelectedMonth(int selectedMonth, boolean smoothScroll) {

        setCurrentPosition(selectedMonth - 1, smoothScroll);
    }

    public void setmOnHourSelectedListener(OnHourSelectedListener mOnHourSelectedListener) {
        this.mOnHourSelectedListener = mOnHourSelectedListener;
    }

    public interface OnHourSelectedListener {
        void onHourselect(int month);
    }
}
