package xxk.wuhai.seacurity.weight.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class TypeHelp {
    private static List<Values> nation = new ArrayList<>();
    private static List<Values> sex = new ArrayList<>();
    private static List<Values> eductions = new ArrayList<>();
    private static List<Values> marries = new ArrayList<>();
    private static List<Values> politics = new ArrayList<>();
    private static List<Values> blood = new ArrayList<>();
    private static List<Values> workAges = new ArrayList<>();

    public static List<Values> getString(Type type){
        switch (type){
            case NATION:
                if(nation.size() == 0){
                    initNation();
                }
                return nation;
            case SEX:
                if (sex.size() == 0){
                    sex.add(new Values("0","女"));
                    sex.add(new Values("1","男"));
                }
                return sex;
            case EDUCATION:
                if (eductions.size() == 0){
                    eductions.add(new Values("0","无"));
                    eductions.add(new Values("1","小学"));
                    eductions.add(new Values("2","初中"));
                    eductions.add(new Values("3","高中"));
                    eductions.add(new Values("4","大专"));
                    eductions.add(new Values("5","本科"));
                    eductions.add(new Values("6","研究生"));
                    eductions.add(new Values("7","硕士"));
                    eductions.add(new Values("8","博士"));
                }
                return eductions;
            case MARRIES:
                if (marries.size() == 0) {
                    marries.add(new Values("0","未婚"));
                    marries.add(new Values("1","已婚"));
                    marries.add(new Values("2","离异"));
                    marries.add(new Values("3","丧偶"));
                }
                return marries;
            case POLITICS:
                if (politics.size() == 0) {
                    politics.add(new Values("01","中共党员"));
                    politics.add(new Values("02","中共预备党员"));
                    politics.add(new Values("03","共青团员"));
                    politics.add(new Values("04","民革党员"));
                    politics.add(new Values("05","民盟盟员"));
                    politics.add(new Values("06","民建会员"));
                    politics.add(new Values("07","民进会员"));
                    politics.add(new Values("08","农工党员"));
                    politics.add(new Values("09","致公党党员"));
                    politics.add(new Values("10","九三学社社员"));
                    politics.add(new Values("11","台盟盟员"));
                    politics.add(new Values("12","无党派人士"));
                    politics.add(new Values("13","群众"));
                }
                return politics;
            case BLOOD:
                if(blood.size() == 0){
                    blood.add(new Values("0","A型"));
                    blood.add(new Values("1","B型"));
                    blood.add(new Values("2","O型"));
                    blood.add(new Values("3","AB型"));
                    blood.add(new Values("99","其他"));
                }
                return blood;
            case WORK_AGE:
                if(workAges.size() == 0){
                    workAges.add(new Values("0","0-1年以内"));
                    workAges.add(new Values("1","1-3年"));
                    workAges.add(new Values("2","3-5年"));
                    workAges.add(new Values("3","5-10年"));
                    workAges.add(new Values("4","10年以上"));
                }
                return workAges;
        }
        return nation;
    }




    public static class Values{
        private String code;
        private String values;

        public Values(String code, String values) {
            this.code = code;
            this.values = values;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValues() {
            return values;
        }

        public void setValues(String values) {
            this.values = values;
        }

        @Override
        public String toString() {
          return values;
        }
    }

    public enum Type{
        NATION,SEX,EDUCATION,MARRIES,POLITICS,BLOOD,WORK_AGE;
    }

    private static void initNation(){
        nation.add(new Values("0","汉族"));
        nation.add(new Values("1","满族"));
        nation.add(new Values("2","蒙古族"));
        nation.add(new Values("3","回族"));
        nation.add(new Values("4","藏族"));
        nation.add(new Values("5","维吾尔族"));
        nation.add(new Values("6","苗族"));
        nation.add(new Values("7","彝族"));
        nation.add(new Values("8","壮族"));
        nation.add(new Values("9","布依族"));

          nation.add(new Values("10","侗族"));
        nation.add(new Values("11","瑶族"));
        nation.add(new Values("12","白族"));
        nation.add(new Values("13","土家族"));
        nation.add(new Values("14","哈尼族"));
        nation.add(new Values("15","哈萨克族"));
        nation.add(new Values("16","傣族"));
        nation.add(new Values("17","傈僳族"));
        nation.add(new Values("18","佤族"));

        nation.add(new Values("20","畲族"));
        nation.add(new Values("21","高山族"));
        nation.add(new Values("22","拉祜族"));
        nation.add(new Values("23","水族"));
        nation.add(new Values("24","东乡族"));
        nation.add(new Values("25","纳西族"));
        nation.add(new Values("26","景颇族"));
        nation.add(new Values("27","柯尔克孜族"));
        nation.add(new Values("28","土族"));
        nation.add(new Values("29","达斡尔族"));

        nation.add(new Values("30","仫佬族"));
        nation.add(new Values("31","羌族"));
        nation.add(new Values("32","布朗族"));
        nation.add(new Values("33","撒拉族"));
        nation.add(new Values("34","毛南族"));
        nation.add(new Values("35","仡佬族"));
        nation.add(new Values("36","锡伯族"));
        nation.add(new Values("37","阿昌族"));
        nation.add(new Values("38","普米族"));
        nation.add(new Values("39","朝鲜族"));

        nation.add(new Values("40","塔吉克族"));
        nation.add(new Values("41","怒族"));
        nation.add(new Values("42","乌孜别克族"));
        nation.add(new Values("43","俄罗斯族"));
        nation.add(new Values("44","鄂温克族"));
        nation.add(new Values("45","德昂族"));
        nation.add(new Values("46","保安族"));
        nation.add(new Values("47","裕固族"));
        nation.add(new Values("48","京族"));
        nation.add(new Values("49","塔塔尔族"));

        nation.add(new Values("50","独龙族"));
        nation.add(new Values("51","鄂伦春族"));
        nation.add(new Values("52","赫哲族"));
        nation.add(new Values("53","门巴族"));
        nation.add(new Values("54","珞巴族"));
        nation.add(new Values("55","基诺族"));
    }
}
