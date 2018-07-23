package xxk.wuhai.seacurity.weight;

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

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */
public class AplyStausPopWindows extends PopupWindow implements View.OnClickListener {
    View root;
    int type = 0;//我提交的

    public AplyStausPopWindows(Context context, int type) {
        this(context, null,type);
    }

    public AplyStausPopWindows(Context context, AttributeSet attrs,int type) {
        super(context, attrs);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        root = layoutInflater.inflate(R.layout.popwindows_aply_status, null);
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
        root.findViewById(R.id.tag1).setOnClickListener(this);

        if (type == 0) {
            root.findViewById(R.id.tag2).setOnClickListener(this);
            root.findViewById(R.id.tag3).setOnClickListener(this);
            root.findViewById(R.id.tag4).setOnClickListener(this);
            root.findViewById(R.id.tag5).setVisibility(View.GONE);
            root.findViewById(R.id.tag6).setVisibility(View.GONE);
        } else {
            root.findViewById(R.id.tag2).setVisibility(View.GONE);
            root.findViewById(R.id.tag3).setVisibility(View.GONE);
            root.findViewById(R.id.tag4).setVisibility(View.GONE);
            root.findViewById(R.id.tag5).setOnClickListener(this);
            root.findViewById(R.id.tag6).setOnClickListener(this);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    @Override
    public void onClick(View view) {
        TextView textView = (TextView) view;
        if (onItemClickListener != null) {
            onItemClickListener.onItemnClick(textView.getText().toString(), (String) textView.getTag());
        }
    }

    public interface OnItemClickListener {
        void onItemnClick(String string, String type);
    }
}
