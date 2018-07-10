package xxk.wuhai.seacurity.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 86936 on 2018/7/7.
 *
 * @QQ 869360026
 */

public class PesonInfoHelper {

    public static String edction(int code) {
        switch (code) {
            case 1:
                return "小学";
            case 2:
                return "初中";
            case 3:
                return "高中";
            case 4:
                return "大专";
            case 5:
                return "大本";
            case 6:
                return "研究生";
            case 7:
                return "硕士";
            case 8:
                return "博士";
        }
        return "无";
    }

    public static String marryStatus(String code){
        if(code == null){
            return "未婚";
        }
        switch (code) {
            case "1":
                return "已婚";
            case "2":
                return "离异";
            case "3":
                return "丧偶";
        }
        return "未婚";
    }

    public static String politicsType(String code){
        if(null == code){
            return "群众";
        }
        switch (code){
            case "01":
                return "中共党员";
            case "02":
                return "中共预备党员";
            case "03":
                return "共青团员";
            case "04":
                return "民革党员";
            case "05":
                return "民盟盟员";
            case "06":
                return "民建会员";
            case "07":
                return "民进会员";
            case "08":
                return "农工党党员";
            case "09":
                return "致公党党员";
            case "10":
                return "九三学社社员";
            case "11":
                return "台盟盟员";
            case "12":
                return "无党派人士";
        }
        return "群众";
    }

    public static String bloodType(String type){
        if(type == null){
            return "其他";
        }
        switch (type){
            case "0":
                return "A型";
            case "1":
                return "B型";
            case "2":
                return "O型";
            case "3":
                return "A型";
        }
        return "其他";
    }

    public static String changeTimes(String times){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date date = simpleDateFormat.parse(times);
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(date);
        }catch (Exception e){
            return "转换出错";
        }
    }


    public static String leaveType(String type){
        if(type == null){
            return "其他";
        }
        switch (type){
            case "1":
                return "事假";
            case "2":
                return "病假";
            case "3":
                return "年假";
            case "4":
                return "调休假";
            case "5":
                return "产假";
            case "6":
                return "陪产假";
        }
        return "其他";
    }

    //0：全部，1：待审核，2：已驳回 ，3：已通过,4：未处理 5：已处理 ,
    public static String aplyStatus(String status){
        if(status == null){
            return "全部";
        }
        switch (status){
            case "1":
                return "待审核";
            case "2":
                return "已驳回";
            case "3":
                return "已通过";
            case "4":
                return "未处理";
            case "5":
                return "已处理";
        }
        return "全部";
    }


    //0-未处理1-通过2-驳回 ,
    public static String detailStatus(String stauts){
        if(stauts == null){
            return "异常";
        }
        switch (stauts){
            case "0":
                return "未处理";
            case "1":
                return "通过";
            case "2":
                return "驳回";
        }
        return "未处理";
    }
}
