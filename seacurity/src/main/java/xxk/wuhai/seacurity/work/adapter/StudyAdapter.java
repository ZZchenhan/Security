package xxk.wuhai.seacurity.work.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class StudyAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public StudyAdapter(@Nullable List<String> data) {
        super(R.layout.item_study,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
