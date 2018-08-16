package com.hz.junxinbaoan.main.view.custorm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sz.tianhe.baselib.http.DownloadProgressHandler;
import sz.tianhe.baselib.http.ProgressHelper;
import sz.tianhe.baselib.utils.ToastUtils;
import sz.tianhe.baselib.utils.VersionUtils;

import com.hz.junxinbaoan.MyApplication;
import com.hz.junxinbaoan.bean.Result;
import com.hz.junxinbaoan.guide.view.GuideActivity;
import com.hz.junxinbaoan.login.api.UserApi;
import com.hz.junxinbaoan.weight.DowloadDialog;
import com.hz.junxinbaoan.weight.HasNewVersionDialog;
import com.hz.junxinbaoan.weight.JumpDialog;
import com.hz.junxinbaoan.work.api.WorkDutyApi;
import com.hz.junxinbaoan.work.bean.DownBean;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public class UpdateUtils {
    private Context context;
    private HasNewVersionDialog hasNewVersionDialog;

    public UpdateUtils(Context context) {
        this.context = context;
    }

    public UpdateUtils chekVersion() {
        MyApplication.retrofitClient.getRetrofit().create(WorkDutyApi.class).getApkVersion().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Result<DownBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Result<DownBean> downBeanResult) {
                if (downBeanResult.getCode().equals("200")) {
                    if (VersionUtils.getLocalVersionName(context).compareTo(downBeanResult.getResult().getVersionNo()) < 0)
                        showVersionDialog(downBeanResult.getResult());
                } else {
                    ToastUtils.makeText(context, downBeanResult.getMessage(), ToastUtils.LENGTH_LONG).show();
                }

            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.makeText(context, e.getMessage(), ToastUtils.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {

            }
        });
        return this;
    }


    private void showVersionDialog(DownBean downBean) {
        if (hasNewVersionDialog == null) {
            hasNewVersionDialog = new HasNewVersionDialog(context);
            hasNewVersionDialog.setOnCofirmClickListener(new HasNewVersionDialog.OnCofirmClickListener() {
                @Override
                public void onConfirmClickListener() {
                    hasNewVersionDialog.dismiss();
                    showDownDialog(downBean.getDownloadUrl());
                }
            });
        }
        hasNewVersionDialog.setContextString(downBean.getUpdateContent());
        hasNewVersionDialog.show();
    }

    private DowloadDialog dowloadDialog;

    private void showDownDialog(String downUrl) {
        downFile(downUrl);
    }

    DownLoadManager downLoadManager;

    Observable observable;

    public void downFile(String file) {
        if (dowloadDialog == null) {
            dowloadDialog = new DowloadDialog(context);
            dowloadDialog.setOnCancelListener(new DowloadDialog.OnCancelListener() {
                @Override
                public void onCancelListener() {
                    if (observable != null)
                        observable.unsubscribeOn(AndroidSchedulers.mainThread());
                }
            });
        }
        dowloadDialog.show();
        if (downLoadManager == null)
            downLoadManager = new DownLoadManager(new DownLoadManager.IProgrossListener() {

                @Override
                public void progross(float progross) {
                    dowloadDialog.setProgross(progross);
                }
            });
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://oss-cn-hangzhou.aliyuncs.com/");
        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        UserApi userApi = retrofitBuilder
                .client(builder.build())
                .build().create(UserApi.class);

        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long bytesRead, long contentLength, boolean done) {
                Log.e("是否在主线程中运行", String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
                Log.e("onProgress", String.format("%d%% done\n", (100 * bytesRead) / contentLength));
                Log.e("done", "--->" + String.valueOf(done));
                if ((100 * bytesRead) / contentLength == 100) {
                    dowloadDialog.dismiss();
                    return;
                }
                dowloadDialog.setProgross((100 * bytesRead) / contentLength);
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
                    String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/12345.apk";

                    File file = new File(filePath);
                    if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
                        file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "12345.apk");
                    }
                    InputStream is = responseBody.byteStream();
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
                    Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                            emitter.onNext("");
                        }
                    }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String string) throws Exception {
                            ToastUtils.makeText(context,"下载失败，请骚后再试",ToastUtils.LENGTH_LONG).show();
                        }
                    });
                }
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("");
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String string) throws Exception {
                        checkIsAndroidO();
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean b = context.getPackageManager().canRequestPackageInstalls();
            if (b) {
                installApk();//安装应用的逻辑(写自己的就可以)
            } else {
                JumpDialog jumpDialog = new JumpDialog(context);
                jumpDialog.setOnCofirmClickListener(new JumpDialog.OnCofirmClickListener() {
                    @Override
                    public void onConfirmClickListener() {
                        Uri packageURI = Uri.parse("package:" + context.getPackageName());
                        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ((Activity) context).startActivityForResult(intent, 10086);
                        jumpDialog.dismiss();
                    }
                });
                jumpDialog.show();
            }
        } else {
            installApk();
        }
    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 10086) {
            checkIsAndroidO();
        }
    }


    private void installApk() {
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/12345.apk";
        File file = new File(filePath);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "12345.apk");
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(context, "com.hz.junxinbaoan.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);

    }
}
