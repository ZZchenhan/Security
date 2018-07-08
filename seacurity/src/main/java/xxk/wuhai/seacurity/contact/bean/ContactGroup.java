package xxk.wuhai.seacurity.contact.bean;

import com.chad.library.adapter.base.entity.SectionEntity;


/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */

public class ContactGroup extends SectionEntity<DirectoryVo> {

    public ContactGroup(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ContactGroup(DirectoryVo contactBean) {
        super(contactBean);
    }
}
