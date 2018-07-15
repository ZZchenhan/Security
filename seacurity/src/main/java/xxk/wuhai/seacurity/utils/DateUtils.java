package xxk.wuhai.seacurity.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 86936 on 2018/7/11.
 *
 * @QQ 869360026
 */

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 获取月份的第一天
     */
    public static String getMonthFistDay(int year,int moth){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,moth);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return simpleDateFormat.format(calendar.getTime());
    }


    /**
     * 获取月份的第一天
     */
    public static String getMonthLastDay(int year,int moth){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,moth+1);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        return simpleDateFormat.format(calendar.getTime());
    }


    /**
     * 获取月份的第一天
     */
    public static Calendar getWekFisrstDay(){
        Calendar calendar = Calendar.getInstance();
        int d = 0;
        if(calendar.get(Calendar.DAY_OF_WEEK)==1){
            d = -6;
        }else{
            d = 2-calendar.get(Calendar.DAY_OF_WEEK);
        }
        calendar.add(Calendar.DAY_OF_WEEK, d);
        return calendar;
    }

    public static Calendar getWekLastDay(){
        Calendar calendar = Calendar.getInstance();
        int d = 0;
        if(calendar.get(Calendar.DAY_OF_WEEK)==1){
            d = -6;
        }else{
            d = 2-calendar.get(Calendar.DAY_OF_WEEK);
        }
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        return calendar;
    }
}
