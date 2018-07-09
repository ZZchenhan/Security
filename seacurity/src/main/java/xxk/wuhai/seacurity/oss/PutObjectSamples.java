package xxk.wuhai.seacurity.oss;

import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

/**
 * Created by zhouzhuo on 12/3/15.
 */
public class PutObjectSamples extends BaseSamples {

    private String testBucket;
    private String testObject;
    private String uploadFilePath;

    public PutObjectSamples(OSS client, String testBucket, String testObject, String uploadFilePath) {
        this.oss = client;
        this.testBucket = testBucket;
        this.testObject = testObject;
        this.uploadFilePath = uploadFilePath;
    }

    // upload from local files. Use synchronous API
    public void putObjectFromLocalFile() throws ClientException, ServiceException {
        // Creates the upload request
        PutObjectRequest put = new PutObjectRequest(testBucket, testObject, uploadFilePath);
        PutObjectResult putResult = oss.putObject(put);
    }
}
