package xxk.wuhai.seacurity.msg.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haibin.calendarview.BaseView;

import java.util.List;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/7/3.
 *
 * @QQ 869360026
 */

public class NotifyMsgAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public NotifyMsgAdapter(@Nullable List<String> data) {
        super(R.layout.item_notify_msg,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
