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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.work.bean.PersonSchedulingResult;

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
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    public int getSchName(PersonSchedulingResult.Recode recode){
        if (recode!=null) {
            if (recode.getHasDaily()!=null && recode.getHasDaily().equals("0")
                    &&recode.getHasOvertime()!=null && recode.getHasOvertime().equals("0")
                    && recode.getHasTemporary() !=null&&recode.getHasTemporary().equals("0")){
                return 1;
            }else if (recode.getHasDaily()!=null && recode.getHasDaily().equals("1")
                    &&recode.getHasOvertime()!=null && recode.getHasOvertime().equals("0")
                    && recode.getHasTemporary() !=null&&recode.getHasTemporary().equals("0")){
                return 2;
            }else if (recode.getHasDaily()!=null && recode.getHasDaily().equals("1")
                    &&recode.getHasOvertime()!=null && recode.getHasOvertime().equals("1")
                    && recode.getHasTemporary() !=null&&recode.getHasTemporary().equals("1")){
                return 4;
            }else{
                return 3;
            }
        }else{
            return 0;
        }
    }

}
