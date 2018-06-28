package xxk.wuhai.seacurity.work.adapter;

import android.support.annotation.Nullable;

import com.amap.api.services.core.PoiItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/6/28.
 *
 * @QQ 869360026
 */

public class PoiAdapter extends BaseQuickAdapter<PoiItem,BaseViewHolder> {

    public PoiAdapter(int layoutResId, @Nullable List<PoiItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiItem item) {
        helper.setText(R.id.name,item.getTitle())
                .setText(R.id.tv_location,item.getProvinceName()+item.getCityName()+item.getBusinessArea()+item.getTitle());
    }
}
