package xxk.wuhai.seacurity.msg.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haibin.calendarview.BaseView;

import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.msg.bean.MessageInfoListBean;

/**
 * Created by 86936 on 2018/7/3.
 *
 * @QQ 869360026
 */

public class NotifyMsgAdapter extends BaseQuickAdapter<MessageInfoListBean,BaseViewHolder> {
    public NotifyMsgAdapter(@Nullable List<MessageInfoListBean> data) {
        super(R.layout.item_notify_msg,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageInfoListBean item) {
        helper.setText(R.id.time,item.getMessageCreateTime())
                .setText(R.id.content,item.getMessageSummary());
    }
}
