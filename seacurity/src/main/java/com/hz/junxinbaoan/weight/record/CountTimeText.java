package com.hz.junxinbaoan.weight.record;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by 86936 on 2018/8/6.
 *
 * @QQ 869360026
 */

public class CountTimeText extends android.support.v7.widget.AppCompatButton {

    //观察者
    Observable observable = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());

    //订阅足额
    Observer observer = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Object o) {
            if (timeChangeListener != null)
                timeChangeListener.timeChanges();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    public CountTimeText(Context context) {
        this(context, null);
    }

    public CountTimeText(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public CountTimeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void changesTimer() {
        observable.subscribe(observer);
    }

    public void changeNomarl() {
        observable.unsubscribeOn(AndroidSchedulers.mainThread());
    }

    public void setTimeChangeListener(OnTimeChangeListener timeChangeListener) {
        this.timeChangeListener = timeChangeListener;
    }

    OnTimeChangeListener timeChangeListener;

    public interface OnTimeChangeListener {
        void timeChanges();
    }
}
