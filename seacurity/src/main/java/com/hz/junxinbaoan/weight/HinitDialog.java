package com.hz.junxinbaoan.weight;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.hz.junxinbaoan.R;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class HinitDialog extends Dialog {

    public HinitDialog(@NonNull Context context) {
        this(context,R.style.bootomDialog);
    }

    public HinitDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.layout_no_permision);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);

        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p); //设置生效
        setCancelable(false);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void show() {
        super.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        },1000*5);
    }
}
