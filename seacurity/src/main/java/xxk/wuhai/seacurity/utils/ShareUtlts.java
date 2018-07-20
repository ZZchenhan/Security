package xxk.wuhai.seacurity.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class ShareUtlts {
    private static final String PERSON_INFO = "PERSON_INFO";
    public static void save(Context context,String userToken, String random, String name){
        SharedPreferences.Editor edit = context.getSharedPreferences(PERSON_INFO,0).edit();
        edit.putString("name",name);
        edit.putString("random",random);
        edit.putString("userToken",userToken);
        edit.commit();
    }

    public static void clean(Context context){
        context.getSharedPreferences(PERSON_INFO,0).edit().clear().commit();

    }

    public static String getToken(Context context){
        return context.getSharedPreferences(PERSON_INFO,0)
                .getString("userToken","");
    }
    public static String getName(Context context){
        return context.getSharedPreferences(PERSON_INFO,0)
                .getString("name","");
    }
    public static String getRandom(Context context){
        return context.getSharedPreferences(PERSON_INFO,0)
                .getString("random","");
    }
}
