package com.hz.junxinbaoan.work.view.custorm;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.work.bean.PersonSchedulingResult;

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
    int mYear = 0;
    public DuyteHead(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public void scrollNext(){
        calendarView.scrollToNext(true);
    }

    public void scrollToPre(){
        calendarView.scrollToPre(true);
    }

    public DuyteHead(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_duty_head,null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(view);
        calendarView = findViewById(R.id.calendarView);
//        initView();

    }

    public void showYear(){
        mYear = calendarView.getCurYear();
        calendarView.showYearSelectLayout(mYear);
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
    public void setSchemes(Map<String,PersonSchedulingResult.Recode> data){
        List<Calendar> calendars = new ArrayList<>();
        Set<Map.Entry<String,PersonSchedulingResult.Recode>> entrys = data.entrySet();
        Iterator<Map.Entry<String,PersonSchedulingResult.Recode>> iterator = entrys.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,PersonSchedulingResult.Recode> entry =iterator.next();
            Calendar calendar = new Calendar();
            try {
                String date = entry.getKey();
                String times[] = date.split("-");
                calendar.setYear(Integer.parseInt(times[0]));
                calendar.setMonth(Integer.parseInt(times[1]));
                calendar.setDay(Integer.parseInt(times[2]));
                calendar.setScheme(getSchName(entry.getValue())+"");
                calendar.setSchemeColor(getScheColor(entry.getValue()));
                calendars.add(calendar);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        calendarView.setSchemeDate(calendars);
    }



    ////0 ：未排班
    //1；修
    //2 白
    //3 白+
    //4 白++
    //5 夜班
    // 夜+
    // 夜色
    public String getSchName(PersonSchedulingResult.Recode recode){
           if(recode !=null) {
               StringBuffer sb = new StringBuffer("");
               if (recode.getHasDaily() != null && recode.getHasDaily().equals("0")
                       && recode.getHasOvertime() != null && recode.getHasOvertime().equals("0")
                       && recode.getHasTemporary() != null && recode.getHasTemporary().equals("0")) {
                   sb = sb.append("休");
               }

               if (recode.getHasDaily() != null && recode.getHasDaily().equals("1")) {
                   sb =sb.append(recode.getScheduleShortName());
               }

               if (recode.getHasOvertime() != null
                       && recode.getHasOvertime().equals("1")) {
                   sb = sb.append("+");
               }

               if (recode.getHasTemporary() != null && recode.getHasTemporary().equals("1")) {
                   sb = sb.append("+");
               }
               return sb.toString();
           } else {
               return " ";
           }
    }

    /**
     * /0未排
     //1 休
     //2 考勤失败
     //3 正常
     * @param recode
     * @return
     */
    public int getScheColor(PersonSchedulingResult.Recode recode){
        if (recode != null) {
            if (recode.getHasDaily()!=null && recode.getHasDaily().equals("0")
                 && recode.getHasOvertime()!=null && recode.getHasOvertime().equals("0")
                 && recode.getHasTemporary()!=null && recode.getHasTemporary().equals("0")) {
                //休息
                  return Color.LTGRAY;
            }
            //4未打卡
            if (recode.getStatus() == 2
                || recode.getStatus() == 3
                    || recode.getStatus() == 4
                || recode.getStatus() == 5) {
                //考勤失败
                return Color.parseColor("#F43530");
            }
            return Color.parseColor("#49B1FA");
        }else{
            return Color.parseColor("#49B1FA");
        }
    }
}
