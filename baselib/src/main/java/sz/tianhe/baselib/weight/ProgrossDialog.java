package sz.tianhe.baselib.weight;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import sz.tianhe.baselib.R;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class ProgrossDialog extends Dialog{
    AVLoadingIndicatorView avi;

    public ProgrossDialog(@NonNull Context context) {
        this(context, R.style.progross);
    }

    public ProgrossDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog,null,false));
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        avi = findViewById(R.id.avi);
        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.width =  WindowManager.LayoutParams.WRAP_CONTENT;
        p.height =  WindowManager.LayoutParams.WRAP_CONTENT;
        p.gravity = Gravity.CENTER;
        getWindow().setAttributes(p); //设置生效
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                avi.hide();
            }
        });
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void show(){
        avi.show();
        super.show();
    }
}
