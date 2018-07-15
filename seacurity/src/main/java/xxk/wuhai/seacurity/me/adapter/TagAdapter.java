package xxk.wuhai.seacurity.me.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haibin.calendarview.BaseView;

import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.login.result.TagResult;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class TagAdapter extends BaseMultiItemQuickAdapter<TagResult.ResultBean.LabelVoListBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TagAdapter(List<TagResult.ResultBean.LabelVoListBean> data) {
        super(data);
        addItemType(0,R.layout.layout_tag);
        addItemType(1,R.layout.layout_tag_see);
    }

    @Override
    protected void convert(BaseViewHolder helper, TagResult.ResultBean.LabelVoListBean item) {
        helper.addOnClickListener(R.id.tag);
        if(item.getItemType() == 0){
            helper.setText(R.id.tag,item.getLabelName() +" "+item.getLabelNum());
        }
    }
}
