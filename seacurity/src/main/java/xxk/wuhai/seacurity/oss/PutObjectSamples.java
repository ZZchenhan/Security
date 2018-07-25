package xxk.wuhai.seacurity.oss;

import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import xxk.wuhai.seacurity.MyApplication;

/**
 * Created by zhouzhuo on 12/3/15.
 */
public class PutObjectSamples extends BaseSamples {

    public static Observable<String> upLoadFile(String testObject, String uploadFilePath){
        return Observable.create((ObservableOnSubscribe<String>) emitter -> {
            if(uploadFilePath != null) {
                PutObjectRequest put = new PutObjectRequest("tongyongbucket", testObject, uploadFilePath);
                PutObjectResult putResult = MyApplication.oss.putObject(put);
                emitter.onNext(MyApplication.aluyun + testObject);
            }else{
                emitter.onNext("");
            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<List<String>> upLoadFils(List<String> files){
        return Observable.create((ObservableOnSubscribe<List<String>>) emitter -> {
            List<String> subimags = new ArrayList<>();
            for(String file:files){
                UUID uuid = UUID.randomUUID();
                PutObjectRequest put = new PutObjectRequest("tongyongbucket", uuid.toString().replace("-",""), file);
                PutObjectResult putResult = MyApplication.oss.putObject(put);
                subimags.add(MyApplication.aluyun + uuid.toString().replace("-",""));
            }
            emitter.onNext(subimags);
            emitter.onComplete();
        }).subscribeOn(Schedulers.io());
    }
}
