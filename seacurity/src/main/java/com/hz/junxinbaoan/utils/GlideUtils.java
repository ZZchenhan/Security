package com.hz.junxinbaoan.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import com.hz.junxinbaoan.R;

/**
 * Created by 86936 on 2018/7/11.
 *
 * @QQ 869360026
 */

public class GlideUtils {
    public static void loadImage(Context context, String url, ImageView imageView){
        loadImage(context,url,getDefaultOptions(),imageView);
    }

    public static void loadImage(Context context, String url, RequestOptions options, final ImageView imageView){
        RequestListener mRequestListener = new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                Log.d("Load", "onException: " + e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
                imageView.setImageResource(R.mipmap.ic_launcher);
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                Log.e("Load",  "model:"+model+" isFirstResource: "+isFirstResource);
                return false;
            }


        };

        Glide.with(context).applyDefaultRequestOptions(options).load(url).listener(mRequestListener).into(imageView);
    }



    private static RequestOptions getDefaultOptions() {
        return new RequestOptions()
                .dontAnimate()//不执行动画
                .placeholder(R.color.gray)//占位图
                .diskCacheStrategy(DiskCacheStrategy.NONE)//缓存机制
                ;
    }
}
