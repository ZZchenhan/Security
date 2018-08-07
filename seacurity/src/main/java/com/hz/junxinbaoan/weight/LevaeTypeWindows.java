package com.hz.junxinbaoan.weight;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hz.junxinbaoan.R;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */
public class LevaeTypeWindows extends PopupWindow implements View.OnClickListener{
    View root;

    public LevaeTypeWindows(Context context) {
        this(context, null);
    }

    public LevaeTypeWindows(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        root = layoutInflater.inflate(R.layout.popwindows_levate_type, null);
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
        root.findViewById(R.id.tv1).setOnClickListener(this);
        root.findViewById(R.id.tv2).setOnClickListener(this);
        root.findViewById(R.id.tv3).setOnClickListener(this);
        root.findViewById(R.id.tv4).setOnClickListener(this);
        root.findViewById(R.id.tv5).setOnClickListener(this);
        root.findViewById(R.id.tv6).setOnClickListener(this);
        root.findViewById(R.id.tv7).setOnClickListener(this);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    @Override
    public void onClick(View view) {
        if(onItemClickListener!=null){
            TextView textView = (TextView) view;
            onItemClickListener.onItemnClick(textView.getText().toString(), (String) textView.getTag());
        }
    }

    public interface OnItemClickListener {
        void onItemnClick(String string, String type);
    }
}
