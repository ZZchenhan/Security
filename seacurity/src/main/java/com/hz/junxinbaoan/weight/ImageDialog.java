package com.hz.junxinbaoan.weight;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;

/**
 * Created by 86936 on 2018/7/8.
 *
 * @QQ 869360026
 */

public class ImageDialog extends Dialog {
    String pic;
    public ImageDialog(@NonNull Context context,String qrPic) {
        this(context,R.style.bootomDialog,qrPic);
    }

    public ImageDialog(@NonNull Context context, int themeResId,String qrPic) {
        super(context, themeResId);
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_qr_code,null,false));
        ImageView ivCode = findViewById(R.id.qrcode);
        Glide.with(getContext())
                .setDefaultRequestOptions(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .load(qrPic)
                .into(ivCode);
        TextView tvNam1 = findViewById(R.id.tv_name1);
        TextView tvNam2 = findViewById(R.id.tv_name2);
        TextView rank = findViewById(R.id.rank);
        if(MyApplication.userDetailInfo.getUserInfo() == null && MyApplication.userDetailInfo.getUserInfo().getName() == null){

        }else {
            if (MyApplication.userDetailInfo.getUserInfo().getName() != null && MyApplication.userDetailInfo.getUserInfo().getName().length() < 2) {
                tvNam1.setText(MyApplication.userDetailInfo.getUserInfo().getName());
            }else{
                tvNam1.setText(
                        MyApplication.userDetailInfo.getUserInfo().getName().
                                substring(MyApplication.userDetailInfo.getUserInfo().getName().length()-2,
                                        MyApplication.userDetailInfo.getUserInfo().getName().length()));
            }
        }
        tvNam2.setText(MyApplication.userDetailInfo.getUserInfo().getName());
        try {
            rank.setText(MyApplication.userDetailInfo.getUserInfo().getRelUserDeptOrgVo().getRank());
        }catch (RuntimeException e){}

        Window window = getWindow();
        window.setGravity(Gravity.CENTER);

        WindowManager m = window.getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.width =  WindowManager.LayoutParams.MATCH_PARENT;
        p.height =  WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(p); //设置生效
    }
}
