package xxk.wuhai.seacurity.weight.date;

import android.content.Context;
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
public class MinutePiker extends WheelPicker<Integer> {


    private int mSelectedMinute;

    private OnMinSelectedListener mOnMinSelectedListener;

    public MinutePiker(Context context) {
        this(context, null);
    }

    public MinutePiker(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MinutePiker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setItemMaximumWidthText("00");
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumIntegerDigits(2);
        setDataFormat(numberFormat);

        Calendar.getInstance().clear();
        mSelectedMinute = Calendar.getInstance().get(Calendar.MONTH) + 1;
        updateMonth();
        setSelectedMonth(mSelectedMinute, false);
        setOnWheelChangeListener(new OnWheelChangeListener<Integer>() {
            @Override
            public void onWheelSelected(Integer item, int position) {
                mSelectedMinute = item;
                if (mOnMinSelectedListener != null) {
                    mOnMinSelectedListener.onMinselect(item);
                }
            }
        });
    }

    public void updateMonth() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 59; i++) {
            list.add(i);
        }
        setDataList(list);
    }

    public int getSelectedMonth() {
        return mSelectedMinute;
    }

    public void setSelectedMonth(int selectedMonth) {
        setSelectedMonth(selectedMonth, true);
    }

    public void setSelectedMonth(int selectedMonth, boolean smoothScroll) {

        setCurrentPosition(selectedMonth - 1, smoothScroll);
    }

    public void setmOnMinSelectedListener(OnMinSelectedListener mOnMinSelectedListener) {
        this.mOnMinSelectedListener = mOnMinSelectedListener;
    }

    public interface OnMinSelectedListener {
        void onMinselect(int month);
    }
}
