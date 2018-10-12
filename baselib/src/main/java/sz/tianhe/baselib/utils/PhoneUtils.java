package sz.tianhe.baselib.utils;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class PhoneUtils {
    public static boolean isMobile0(String str){
        if(str == null || "".equals(str)){
            return false;
        }
        //return str.matches("^1[0-9]{10}$");
        //return str.matches("^1\\d{10}$");
        //return str.matches("^(13|14|15|18)\\d{9}$");
        //return str.matches("^((13[0-9])|(14[5|7])|(15[0-3])|(15[5-9])|(18[0,5-9]))\\d{8}$");
        return str.matches("^((13[0-9])|(14[0,1,4-7])|(15[0-9])|(16[5,6])|(17[0-8])|(18[0-9])|198|199|(147))\\d{8}$");
    }
}
