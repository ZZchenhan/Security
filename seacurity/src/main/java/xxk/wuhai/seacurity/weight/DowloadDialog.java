package xxk.wuhai.seacurity.weight;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class DowloadDialog extends Dialog {

    private TextView cancel;
    private TextView progrossText;
    private View viewProgross;
    private float progroosTextWithd;
    private float viewWidth;
    public DowloadDialog(@NonNull Context context) {
        this(context,0);
    }

    public DowloadDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_dowload);

        Window window = getWindow();
        window.setGravity(Gravity.CENTER);

        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p); //设置生效
        setCancelable(false);

        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onCancelListener != null){
                    onCancelListener.onCancelListener();
                }
            }
        });
        progrossText = findViewById(R.id.text);
        viewProgross = findViewById(R.id.progross_view);
    }

    @SuppressLint("WrongViewCast")
    @Override
    public void show() {
        super.show();
        progroosTextWithd =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,210,getContext().getResources().getDisplayMetrics());
        viewWidth =   TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,190,getContext().getResources().getDisplayMetrics());
        setProgross(0);

    }


    public void setProgross(final float progross){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if(DowloadDialog.this.isShowing()) {
                    progrossText.setText((int) (progross) + "");
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) progrossText.getLayoutParams();
                    layoutParams.setMargins((int) (progross/100F * progroosTextWithd), 0, 0, 0);
                    progrossText.setLayoutParams(layoutParams);
                    viewProgross.setLayoutParams(new FrameLayout.LayoutParams((int) (progross/100F * viewWidth), FrameLayout.LayoutParams.MATCH_PARENT));
                }
            }
        });
    }


    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    OnCancelListener onCancelListener;
    public interface OnCancelListener{
        void onCancelListener();
    }
}
