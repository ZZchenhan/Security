package com.hz.junxinbaoan.weight;

import android.content.Context;
import android.view.LayoutInflater;

import com.haibin.calendarview.WeekBar;

import com.hz.junxinbaoan.R;

/**
 * Created by 86936 on 2018/7/1.
 *
 * @QQ 869360026
 */

public class MeWeekBar extends WeekBar {
    public MeWeekBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.me_week_bar, this, true);
    }

    /**
     * 当周起始发生变化，使用自定义布局需要重写这个方法，避免出问题
     *
     * @param weekStart 周起始
     */
    @Override
    protected void onWeekStartChange(int weekStart) {

    }
}
