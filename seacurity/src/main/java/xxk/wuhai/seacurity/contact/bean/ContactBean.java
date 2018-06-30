package xxk.wuhai.seacurity.contact.bean;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */
public class ContactBean {
    private String name;

    public ContactBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void sort(List<ContactBean> contactBeans){
        Collections.sort(contactBeans, new Comparator<ContactBean>() {
            Collator collator = Collator.getInstance(java.util.Locale.CHINA);
            public int compare(ContactBean arg0, ContactBean arg1) {
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
