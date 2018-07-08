package xxk.wuhai.seacurity.contact.bean;

import java.io.Serializable;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */
public class DirectoryVo implements Serializable{
    /**
     * email : string
     * livingAddress : string
     * name : string
     * namePinyin : string
     * phone : string
     * photoUrl : string
     * rank : string
     * sex : string
     * status : string
     * userId : 0
     * userType : string
     */

    private String email;
    private String livingAddress;
    private String name;
    private String namePinyin;
    private String phone;
    private String photoUrl;
    private String rank;
    private String sex;
    private String status;
    private int userId;
    private String userType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLivingAddress() {
        return livingAddress;
    }

    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public static void sort(List<DirectoryVo> directoryVos){
        Collections.sort(directoryVos, new Comparator<DirectoryVo>() {
            Collator collator = Collator.getInstance(java.util.Locale.CHINA);
            public int compare(DirectoryVo arg0, DirectoryVo arg1) {
                String name0 = arg0.getName();
                String name1  = arg1.getName();
                if(collator.compare(name0,name1)>0)
                    return 1;
                else
                    return -1;
            }
        });
    }
}
