package xxk.wuhai.seacurity.work.view.custorm;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/7/1.
 *
 * @QQ 869360026
 */
public class DuyteHead extends LinearLayout{

    private CalendarView calendarView;

    public DuyteHead(Context context) {
        this(context,null);
    }

    public DuyteHead(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DuyteHead(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_duty_head,null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(view);
        calendarView = findViewById(R.id.calendarView);
//        initView();
    }

    //设置标签
    public Calendar getSchemeCalendar(int year, int month, int day, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        if(text.equals("休")) {
            calendar.setSchemeColor(0xffF43530);//如果单独标记颜色、则会使用这个颜色
        }else{
            calendar.setSchemeColor(0xff49B1FA);//如果单独标记颜色、则会使用这个颜色
        }
        calendar.setScheme(text);
        calendar.addScheme(new Calendar.Scheme());
        calendar.addScheme(0xffF43530, "休");
        calendar.addScheme(0xff49B1FA, "班");
        return calendar;
    }

    public void setDataSelect(CalendarView.OnDateSelectedListener listener){
        calendarView.setOnDateSelectedListener(listener);
    }

    public void setMonthChange(CalendarView.OnMonthChangeListener monthChange){
        calendarView.setOnMonthChangeListener(monthChange);
    }

    public void setSchemes( List<Calendar> schemes){
        calendarView.setSchemeDate(schemes);
    }
}
