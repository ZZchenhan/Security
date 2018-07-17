package xxk.wuhai.seacurity.work.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import xxk.wuhai.seacurity.R;
import xxk.wuhai.seacurity.utils.PesonInfoHelper;
import xxk.wuhai.seacurity.work.bean.ApprovalRecordListBean;

/**
 * Created by 86936 on 2018/7/4.
 *
 * @QQ 869360026
 */

public class MyApplyAdapter extends BaseQuickAdapter<ApprovalRecordListBean,BaseViewHolder> {
    public MyApplyAdapter(@Nullable List<ApprovalRecordListBean> data) {
        super(R.layout.item_my_aply,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApprovalRecordListBean item) {
            helper.setText(R.id.type, item.getPatchTime()!=null?"补签申请":"请假申请")
                    .setText(R.id.status,PesonInfoHelper.aplyStatus(item.getStatus()))
                    .setText(R.id.name,"姓名:"+item.getApName())
                    .setText(R.id.time, item.getPatchTime()!=null?"补签时间："+
                            item.getPatchTime().replace("-","."):
                            "请假时间:"+item.getLrBeginTime().replace("-",".")+
                                    "-"+item.getLrEndTime().replace("-","."))
                    .setText(R.id.reson,"事由："+item.getSupplement());
            if(!PesonInfoHelper.aplyStatus(item.getStatus()).equals("已驳回")){
                helper.setTextColor(R.id.status, Color.parseColor("#49B1FA"));
            }else{
                helper.setTextColor(R.id.status, Color.parseColor("#F43530"));
            }
    }
}
