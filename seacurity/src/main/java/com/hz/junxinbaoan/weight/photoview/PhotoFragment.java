package com.hz.junxinbaoan.weight.photoview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.photoview.PhotoView;

import com.hz.junxinbaoan.R;

/**
 * Created by 86936 on 2018/7/26.
 *
 * @QQ 869360026
 */

public class PhotoFragment extends Fragment {
    private String url;
    private PhotoView mPhotoView;

    /**
     * 获取这个fragment需要展示图片的url
     * @param url
     * @return
     */
    public static PhotoFragment newInstance(String url) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString("url");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_img, container, false);
        mPhotoView = view.findViewById(R.id.photoview);
        //设置缩放类型，默认ScaleType.CENTER（可以不设置）
        mPhotoView.setScaleType(ImageView.ScaleType.CENTER);
        mPhotoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        mPhotoView.setOnPhotoTapListener((imageView, v, v1) -> {});
        Glide.with(getContext())
                .setDefaultRequestOptions(new RequestOptions().fitCenter())
                .load(url)
                .into(mPhotoView);
        return view;
    }

}
