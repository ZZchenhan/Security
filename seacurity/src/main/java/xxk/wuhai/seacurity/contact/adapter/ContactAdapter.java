package xxk.wuhai.seacurity.contact.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.contact.bean.ContactGroup;
import xxk.wuhai.seacurity.contact.bean.DirectoryVo;

/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */

public class ContactAdapter extends BaseSectionQuickAdapter<ContactGroup, BaseViewHolder> {
    public ContactAdapter(List<ContactGroup> data) {
        this(R.layout.item_contanct, R.layout.item_contanct_section, data);
    }

    public ContactAdapter(int layoutResId, int sectionHeadResId, List<ContactGroup> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Deprecated
    protected void convertHead(BaseViewHolder helper, ContactGroup item) {
        helper.setText(R.id.section, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContactGroup item) {
        DirectoryVo contactBean = item.t;
        if (contactBean.getName() != null && contactBean.getName().length() > 0) {
            helper.setText(R.id.tv_name, contactBean.getName().substring(contactBean.getName().length() - 1, contactBean.getName().length()));
        }
        helper.setText(R.id.name, contactBean.getName());
        helper.setText(R.id.zhiwei, contactBean.getName());
    }

}
