package com.hz.junxinbaoan.msg.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import sz.tianhe.baselib.navagation.IBaseNavagation;
import sz.tianhe.baselib.view.activity.BaseActivity;

import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.R;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.common.navagation.LeftIconNavagation;
import com.hz.junxinbaoan.msg.MsgApi;
import com.hz.junxinbaoan.msg.bean.GetMessageDetailResult;
import com.hz.junxinbaoan.msg.vo.GetMessageDetailVO;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CompanyMsgActivity extends BaseActivity {

    private TextView tvTile;

    private TextView tvTimes;

    private TextView tvContext;

    private TextView tvFrom;

    @Override
    public int layoutId() {
        return R.layout.activity_compnay_msg;
    }
    LeftIconNavagation leftIconNavagation;
    @Override
    public IBaseNavagation navagation() {
         leftIconNavagation = (LeftIconNavagation) new LeftIconNavagation(this) {
            @Override
            public String title() {
                return "公司信息";
            }
        }.setNavagationBackgroudColor(R.color.colorPrimary);
        leftIconNavagation.setIconClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        leftIconNavagation.setTitleColor(R.color.white);
        return leftIconNavagation;
    }


    @Override
    public void initView() {
        MyApplication.retrofitClient.getRetrofit().create(MsgApi.class)
                .getMsgDetails(
                        new GetMessageDetailVO(getIntent().
                                getIntExtra("msgId",0),
                                getIntent().getStringExtra("msgType")))

        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Result<GetMessageDetailResult>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<GetMessageDetailResult> getMessageDetailResultResult) {
                if(isFinishing()){
                    return;
                }
                if(!getMessageDetailResultResult.getCode().equals("200")){
                    toast(getMessageDetailResultResult.getMessage());
                    return;
                }
                setData(getMessageDetailResultResult.getResult());
            }

            @Override
            public void onError(Throwable e) {
                if(!isFinishing()){
                    toast(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void setData(GetMessageDetailResult result){
        if(result.getMessageDetail() != null){
            tvTile.setText(result.getMessageDetail().getMessageTitle());
            tvTimes.setText("发送时间:"+result.getMessageDetail().getMessageCreateTime());
            tvContext.setText(Html.fromHtml(result.getMessageDetail().getMessageContent(),new URLImageGetter(tvContext,this),null));
            tvFrom.setText("来源:"+result.getMessageDetail().getCompanyName());
            return;
        }
        if(result.getPersonnelDetails() != null){
            tvTile.setText("人事变动提醒");
            tvTimes.setText("发送时间:"+result.getPersonnelDetails().getGmtCreate());

            tvContext.setText(Html.fromHtml(result.getPersonnelDetails().getPost(),new URLImageGetter(tvContext,this),null)
            );
            tvFrom.setText("来源:人事部");
            return;
        }
    }

    @Override
    public void findViews() {
        tvTile = findViewById(R.id.title);
        tvTimes = findViewById(R.id.time);
        tvContext = findViewById(R.id.content);
        tvFrom = findViewById(R.id.from);
    }

    public static void openActivity(Context context,String msgType,int msgId){
        Intent intent = new Intent(context,CompanyMsgActivity.class);
        intent.putExtra("msgType",msgType);
        intent.putExtra("msgId",msgId);
        context.startActivity(intent);
    }




    public static class URLImageGetter implements Html.ImageGetter {
        Context c;
        TextView tv_image;
        private List<Target> targets = new ArrayList<>();

        public URLImageGetter(TextView t, Context c) {
            this.tv_image = t;
            this.c = c;
            tv_image.setTag(targets);
        }

        @Override
        public Drawable getDrawable(final String source) {
            final URLDrawable urlDrawable = new URLDrawable();
            final Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Drawable drawable = new BitmapDrawable(bitmap);
                    drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    urlDrawable.setDrawable(drawable);
                    urlDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    tv_image.invalidate();
                    tv_image.setText(tv_image.getText());
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
//                    errorDrawable.setBounds(0, 0, errorDrawable.getIntrinsicWidth(), errorDrawable.getIntrinsicHeight());
//                    urlDrawable.setBounds(0, 0, errorDrawable.getIntrinsicWidth(), errorDrawable.getIntrinsicHeight());
//                    urlDrawable.setDrawable(errorDrawable);
//                    tv_image.invalidate();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
//                    placeHolderDrawable.setBounds(0, 0, placeHolderDrawable.getIntrinsicWidth(), placeHolderDrawable.getIntrinsicHeight());
//                    urlDrawable.setBounds(0, 0, placeHolderDrawable.getIntrinsicWidth(), placeHolderDrawable.getIntrinsicHeight());
//                    urlDrawable.setDrawable(placeHolderDrawable);
//                    tv_image.invalidate();
                }
            };

            targets.add(target);
            Picasso.with(c).load(source).into(target);
            return urlDrawable;
        }
    }
    public static class URLDrawable extends BitmapDrawable {
        private Drawable drawable;

        @Override
        public void draw(Canvas canvas) {
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }

    }


}
