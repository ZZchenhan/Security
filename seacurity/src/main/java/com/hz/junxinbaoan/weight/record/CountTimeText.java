package com.hz.junxinbaoan.weight.record;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;


/**
 * Created by 86936 on 2018/8/6.
 *
 * @QQ 869360026
 */

public class CountTimeText extends android.support.v7.widget.AppCompatButton {
    boolean isStart = true;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case  100:
                if(timeChangeListener != null){
                    timeChangeListener.timeChanges();
                }
                if(isStart) {
                    mHandler.sendEmptyMessageDelayed(100, 1000);
                }
                break;
            }
        }
    };

    public CountTimeText(Context context) {
        this(context,null);
    }

    public CountTimeText(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public CountTimeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void changesTimer(){
        isStart =true;
        mHandler.sendEmptyMessageDelayed(100, 0);
    }
    public void changeNomarl(){
        isStart =false;
    }

    public void setTimeChangeListener(OnTimeChangeListener timeChangeListener) {
        this.timeChangeListener = timeChangeListener;
    }

    OnTimeChangeListener timeChangeListener;
    public interface  OnTimeChangeListener{
        void timeChanges();
    }
}
