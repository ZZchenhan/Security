package xxk.wuhai.seacurity.work.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 86936 on 2018/6/29.
 *
 * @QQ 869360026
 */

public class SignListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public SignListAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
