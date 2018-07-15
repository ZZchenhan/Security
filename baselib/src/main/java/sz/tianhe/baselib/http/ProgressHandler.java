package sz.tianhe.baselib.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by 86936 on 2018/7/15.
 *
 * @QQ 869360026
 */

public abstract  class ProgressHandler {
    protected abstract void sendMessage(ProgressBean progressBean);

    protected abstract void handleMessage(Message message);

    protected abstract void onProgress(long progress, long total, boolean done);

    protected static class ResponseHandler extends Handler {

        private ProgressHandler mProgressHandler;
        public ResponseHandler(ProgressHandler mProgressHandler, Looper looper) {
            super(looper);
            this.mProgressHandler = mProgressHandler;
        }

        @Override
        public void handleMessage(Message msg) {
            mProgressHandler.handleMessage(msg);
        }
    }
}
