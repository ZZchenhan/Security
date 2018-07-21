package xxk.wuhai.seacurity.work.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haibin.calendarview.BaseView;

import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.work.bean.ClueBursList;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class CulAdapter extends BaseQuickAdapter<ClueBursList.ClueBurstListBean,BaseViewHolder> {

    public CulAdapter(@Nullable List<ClueBursList.ClueBurstListBean> data) {
        super(R.layout.item_cul,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClueBursList.ClueBurstListBean item) {
        try {
            Glide.with(mContext)
                    .load(item.getPictureUrls().get(0))
                    .into((ImageView) helper.getView(R.id.image));
        }catch (Exception e){

        }
        helper.setText(R.id.times,item.getClueBurstCreate())
                .setText(R.id.summary,item.getClueBurstContent());
    }

}
