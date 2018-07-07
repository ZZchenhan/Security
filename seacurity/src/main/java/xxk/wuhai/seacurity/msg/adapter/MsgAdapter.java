package xxk.wuhai.seacurity.msg.adapter;

import android.graphics.Color;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.msg.bean.MessageInfoListBean;
import xxk.wuhai.seacurity.msg.bean.MsgResult;

/**
 * Created by 86936 on 2018/7/3.
 *
 * @QQ 869360026
 */

public class MsgAdapter extends BaseMultiItemQuickAdapter<MessageInfoListBean,BaseViewHolder> {
    public MsgAdapter(List<MessageInfoListBean> data) {
        super(data);
        addItemType(0, R.layout.item_msg_txt);
        addItemType(1, R.layout.item_msg_pic);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageInfoListBean item) {
        helper.setText(R.id.title,item.getMessageTitle()+"")
                .setText(R.id.summary,item.getMessageSummary())
                .setText(R.id.time,item.getMessageCreateTime())
                .setText(R.id.status,"1".equals(item.getStatus())?"已读":"未读")
                .setTextColor(R.id.status,"1".equals(item.getStatus())? Color.parseColor("#888888")
                        :Color.parseColor("#00C200"));
        if(helper.getItemViewType() == 1){
            helper.setBackgroundColor(R.id.pic1,Color.parseColor("#00C200"));
            helper.setBackgroundColor(R.id.pic2,Color.parseColor("#00C200"));
            helper.setBackgroundColor(R.id.pic3,Color.parseColor("#00C200"));
            try {
                Glide.with(mContext).asBitmap().load(item.getMessagePictureUrls().get(0)).into((ImageView) helper.getView(R.id.pic1));
                Glide.with(mContext).asBitmap().load(item.getMessagePictureUrls().get(1)).into((ImageView) helper.getView(R.id.pic2));
                Glide.with(mContext).asBitmap().load(item.getMessagePictureUrls().get(2)).into((ImageView) helper.getView(R.id.pic3));
            }catch (Exception e){

            }
        }
    }
}
