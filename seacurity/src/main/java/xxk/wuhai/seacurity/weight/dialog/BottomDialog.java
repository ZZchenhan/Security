package xxk.wuhai.seacurity.weight.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class BottomDialog extends Dialog{

    private TextView btnCancel;

    private TextView btnConfirm;

    private TextPicker textPicker;

    public BottomDialog(@NonNull Context context) {
        this(context,R.style.bootomDialog);
    }

    public BottomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_bottom,null,false));
        btnCancel = findViewById(R.id.btn_dialog_date_cancel);
        btnConfirm = findViewById(R.id.btn_dialog_date_decide);
        textPicker = findViewById(R.id.monthPicker_layout_date);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        setType(TypeHelp.Type.BLOOD);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);

        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = d.getWidth(); //宽度设置为屏幕
        getWindow().setAttributes(p); //设置生效
    }


    public TypeHelp.Values getSelectValues(){
        return textPicker.getmSelectedText();
    }


    public BottomDialog setConfirmClick(View.OnClickListener onClickListener){
        btnConfirm.setOnClickListener(onClickListener);
        return this;
    }

    public void setType(TypeHelp.Type type){
        textPicker.setDataList(TypeHelp.getString(type));
    }
}
