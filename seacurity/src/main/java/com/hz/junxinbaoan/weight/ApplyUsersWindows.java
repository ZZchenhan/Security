package com.hz.junxinbaoan.weight;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.weight.adapter.PopItemAdapter;
import com.hz.junxinbaoan.work.bean.AplyUser;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */
public class ApplyUsersWindows extends PopupWindow {
    View root;
    RecyclerView recyclerView;
    PopItemAdapter popItemAdapter;
    public ApplyUsersWindows(Context context) {
        this(context, null);
    }

    public ApplyUsersWindows(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        root = layoutInflater.inflate(R.layout.popwindows_list, null);
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(root);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
        setBackgroundDrawable(new ColorDrawable());

        /**
         * 设置可以获取集点
         */
        setFocusable(true);

        /**
         * 设置点击外边可以消失
         */
        setOutsideTouchable(true);

        /**
         *设置可以触摸
         */
        setTouchable(true);


        /**
         * 设置点击外部可以消失
         */

        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                /**
                 * 判断是不是点击了外部
                 */
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    return true;
                }
                //不是点击外部
                return false;
            }
        });
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        popItemAdapter = new PopItemAdapter(datas);
        recyclerView.setAdapter(popItemAdapter);
        popItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemnClick(datas.get(position));
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemnClick(AplyUser aplyUser);
    }
    List<AplyUser> datas = new ArrayList<>();
    public void setData(List<AplyUser> datas){
        this.datas.addAll(datas);
        popItemAdapter.notifyDataSetChanged();
    }


}
