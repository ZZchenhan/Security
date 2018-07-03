package xxk.wuhai.seacurity.msg.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.msg.bean.MsgBean;

/**
 * Created by 86936 on 2018/7/3.
 *
 * @QQ 869360026
 */

public class MsgAdapter extends BaseMultiItemQuickAdapter<MsgBean,BaseViewHolder> {
    public MsgAdapter(List<MsgBean> data) {
        super(data);
        addItemType(0, R.layout.item_msg_txt);
        addItemType(1, R.layout.item_msg_pic);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgBean item) {

    }
}
