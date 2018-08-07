package com.hz.junxinbaoan.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 86936 on 2018/7/19.
 *
 * @QQ 869360026
 */

public class SignUtils {
    public static final String NUMBERS = "NUMBERS";
    public static void add(long time, Context context){
        int numbers = context.getSharedPreferences("sign",0).getInt(NUMBERS,0);
        long systim = context.getSharedPreferences("sign",0).getLong("time",0);
        SharedPreferences.Editor editor =  context.getSharedPreferences("sign",0).edit();
        if(systim/(1000*60*60*24) != time/(1000*60*60*24) && systim!=0){
            editor.putInt(NUMBERS,0);
            editor.putLong("time",time);
        }else{
            editor.putInt(NUMBERS,numbers+1);
            editor.putLong("time",time);
        }
        editor.commit();
    }

    public static int getNumbers(Context context){
        int numbers = context.getSharedPreferences("sign",0).getInt(NUMBERS,0);
        long systim = context.getSharedPreferences("sign",0).getLong("time",0);
        if(systim/(1000*60*60*24) != System.currentTimeMillis()/(1000*60*60*24)){
            return 0;
        }else{
           return numbers;
        }
    }
}
