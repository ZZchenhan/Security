package xxk.wuhai.seacurity.weight;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import sz.tianhe.baselib.http.interceptor.BaseInterceptor;
import xxk.wuhai.seacurity.MyApplication;
import xxk.wuhai.seacurity.R;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class ImageDialog extends Dialog {
    public ImageDialog(@NonNull Context context) {
        this(context,0);
    }

    public ImageDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_qr_code,null,false));
        ImageView ivCode = findViewById(R.id.qrcode);
        Glide.with(getContext())
                .load(MyApplication.baseUrl +
                        "/client-api/user/getQRCodeImg" +
                        "?x-terminal-type=2" +
                        "&x-access-token=" +
                        BaseInterceptor.token +
                        "&x-username=" +
                        BaseInterceptor.name +
                        "&x-random=" +
                        BaseInterceptor.random)
                .into(ivCode);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);

        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.width =  WindowManager.LayoutParams.WRAP_CONTENT;
        p.height =  WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(p); //设置生效
    }
}
