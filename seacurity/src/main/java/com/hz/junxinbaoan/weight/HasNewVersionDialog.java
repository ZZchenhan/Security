package com.hz.junxinbaoan.weight;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hz.junxinbaoan.R;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class HasNewVersionDialog extends Dialog {

    private TextView tvContent;
    private TextView tvUpdate;
    private TextView cancel;

    public HasNewVersionDialog(@NonNull Context context) {
        this(context,0);
    }

    public HasNewVersionDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_version_update);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);

        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p); //设置生效
        setCancelable(false);
        findViews();
    }

    private void findViews(){
        tvContent = findViewById(R.id.cotent);
        tvUpdate = findViewById(R.id.accept);
        cancel = findViewById(R.id.unaccept);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onCofirmClickListener != null){
                    onCofirmClickListener.onConfirmClickListener();
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    public void setContextString(String content){
        this.tvContent.setText(content);
    }
    OnCofirmClickListener onCofirmClickListener;

    public void setOnCofirmClickListener(OnCofirmClickListener onCofirmClickListener) {
        this.onCofirmClickListener = onCofirmClickListener;
    }

    public interface OnCofirmClickListener{
        void onConfirmClickListener();
    }
}
