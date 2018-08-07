package com.hz.junxinbaoan.main.view.custorm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sz.tianhe.baselib.http.DownloadProgressHandler;
import sz.tianhe.baselib.http.ProgressHelper;
import com.hz.junxinbaoan.guide.view.GuideActivity;
import com.hz.junxinbaoan.login.api.UserApi;
import com.hz.junxinbaoan.weight.DowloadDialog;
import com.hz.junxinbaoan.weight.HasNewVersionDialog;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class UpdateUtils {
    private Context context;
    private HasNewVersionDialog hasNewVersionDialog;
    public UpdateUtils(Context context){
        this.context = context;
    }

    public  void chekVersion(){
        showVersionDialog("1、哎更新不更新\n2、这里是一个提差");
    }


    private void showVersionDialog(String string){
        if(hasNewVersionDialog==null){
            hasNewVersionDialog = new HasNewVersionDialog(context);
            hasNewVersionDialog.setOnCofirmClickListener(new HasNewVersionDialog.OnCofirmClickListener() {
                @Override
                public void onConfirmClickListener() {
                    showDownDialog();
                }
            });
        }
        hasNewVersionDialog.setContextString(string);
        hasNewVersionDialog.show();
    }

    private DowloadDialog dowloadDialog;
    private void showDownDialog(){
        downFile("http://gdown.baidu.com/data/wisegame/72bd39d791bc2357/QQ_872.apk");
    }
    DownLoadManager downLoadManager;

    Observable observable;
    public void downFile(String file){
        if(dowloadDialog == null){
            dowloadDialog = new DowloadDialog(context);
            dowloadDialog.setOnCancelListener(new DowloadDialog.OnCancelListener() {
                @Override
                public void onCancelListener() {
                    if(observable!=null)
                        observable.unsubscribeOn(AndroidSchedulers.mainThread());
                    dowloadDialog.dismiss();
                }
            });
        }
        dowloadDialog.show();
        if(downLoadManager == null)
            downLoadManager = new DownLoadManager(new DownLoadManager.IProgrossListener(){

                @Override
                public void progross(float progross) {
                    dowloadDialog.setProgross(progross);
                }
            });
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://gdown.baidu.com/");
        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        UserApi userApi =  retrofitBuilder
                .client(builder.build())
                .build().create(UserApi.class);

        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long bytesRead, long contentLength, boolean done) {
                Log.e("是否在主线程中运行", String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
                Log.e("onProgress",String.format("%d%% done\n",(100 * bytesRead) / contentLength));
                Log.e("done","--->" + String.valueOf(done));
                if((100 * bytesRead) / contentLength == 100){
                    dowloadDialog.dismiss();
                    return;
                }
                dowloadDialog.setProgross((100 *bytesRead) / contentLength);
            }
        });
        observable = userApi.downloadFile(file).subscribeOn(Schedulers.newThread());
        observable.subscribe(new Observer<ResponseBody>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(ResponseBody responseBody) {
              try {
                  InputStream is = responseBody.byteStream();
                  File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "12345.apk");
                  FileOutputStream fos = new FileOutputStream(file);
                  BufferedInputStream bis = new BufferedInputStream(is);
                  byte[] buffer = new byte[1024];
                  int len;
                  while ((len = bis.read(buffer)) != -1) {
                      fos.write(buffer, 0, len);
                      fos.flush();
                  }
                  fos.close();
                  bis.close();
                  is.close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {
              checkIsAndroidO();
          }
      });
    }


    private void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = context.getPackageManager().canRequestPackageInstalls();
            if (b) {
                installApk();//安装应用的逻辑(写自己的就可以)
            } else {
                context.startActivity(new Intent(context,GuideActivity.class));
                ((Activity)context).finish();

            }
        } else {
            installApk();
        }

    }

    private void installApk(){
        File file = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                , "12345.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(Build.VERSION.SDK_INT>=24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(context, "xxk.wuhai.seacurity.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);

    }
}
