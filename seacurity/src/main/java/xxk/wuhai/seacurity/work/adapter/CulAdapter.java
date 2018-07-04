package xxk.wuhai.seacurity.work.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haibin.calendarview.BaseView;

import java.util.List;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class CulAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public CulAdapter(@Nullable List<String> data) {
        super(R.layout.item_cul,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }

}
