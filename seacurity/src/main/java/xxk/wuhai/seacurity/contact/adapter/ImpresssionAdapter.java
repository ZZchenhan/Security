package xxk.wuhai.seacurity.contact.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.contact.bean.TagBean;

/**
 * Created by 86936 on 2018/6/30.
 *
 * @QQ 869360026
 */

public class ImpresssionAdapter extends BaseQuickAdapter<TagBean,BaseViewHolder> {

    public ImpresssionAdapter(@Nullable List<TagBean> data) {
        super(R.layout.item_impression,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagBean item) {
        if(item.isBlue()){
            helper.setBackgroundRes(R.id.tag,R.drawable.bg_info_blue);
            helper.setTextColor(R.id.tag, Color.WHITE);
        }else{
            helper.setBackgroundRes(R.id.tag,R.drawable.bg_info_normal);
            helper.setTextColor(R.id.tag,mContext.getResources().getColor(R.color.text_color7));
        }
        helper.setText(R.id.tag,item.getTag());
    }
}
