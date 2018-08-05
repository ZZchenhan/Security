package xxk.wuhai.seacurity.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by 86936 on 2018/8/5.
 *
 * @QQ 869360026
 */

public class MapContainer extends RelativeLayout {
    public MapContainer(Context context) {
        super(context);
    }
    public MapContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            requestDisallowInterceptTouchEvent(false);
        } else {
            requestDisallowInterceptTouchEvent(true);//告诉父View不要拦截我的事件
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//onTouchListener中返回false此方法才会被调用
        return true;
    }
}
