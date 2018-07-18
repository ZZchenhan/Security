package xxk.wuhai.seacurity.weight.record;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.io.File;
import java.util.List;

import xxk.wuhai.seacurity.R;

public class RecordButton extends android.support.v7.widget.AppCompatImageView {

    private static final int MIN_RECORD_TIME = 1; // 最短录音时间，单位秒
    public static final int RECORD_OFF = 0; // 不在录音
    public static final int RECORD_ON = 1; // 正在录音
    public static final int RECORD_Done = 2; // 录音结束

    private Dialog mRecordDialog;
    private RecordStrategy mAudioRecorder;
    private Thread mRecordThread;
    private RecordListener listener;
    private RecordStatusListener recordStatusListener;

    private int recordState = 0; // 录音状态
    private float recodeTime = 0.0f; // 录音时长，如果录音时间太短则录音失败
    private double voiceValue = 0.0; // 录音的音量值
    private boolean isCanceled = false; // 是否取消录音
    private float downY;

    private TextView dialogTextView;
    private ImageView dialogImg;
    private Context mContext;
    private Toast mToast;

    public RecordButton(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public RecordButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init(context);
    }

    public RecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    public void setAudioRecord(RecordStrategy record) {
        this.mAudioRecorder = record;
    }

    public void setRecordListener(RecordListener listener) {
        this.listener = listener;
    }

    // 录音时显示Dialog
    private void showVoiceDialog(int flag) {
        if (mToast != null){
            mToast.cancel();
        }
        if (mRecordDialog == null) {
            mRecordDialog = new Dialog(mContext, R.style.Dialogstyle);
            Window window = mRecordDialog.getWindow();
            window.setGravity(Gravity.TOP);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.y = CommonUtils.dip2px(getContext(),60);
            window.setAttributes(layoutParams);
            mRecordDialog.setContentView(R.layout.dialog_record);
            dialogImg = (ImageView) mRecordDialog
                    .findViewById(R.id.record_dialog_img);
            dialogTextView = (TextView) mRecordDialog
                    .findViewById(R.id.record_dialog_txt);
        }
        switch (flag) {
            case 0:
                dialogImg.setImageResource(R.drawable.record_animate_01);
                dialogTextView.setText("向上滑动可取消录音");
                break;
            case 1:
                dialogImg.setImageResource(R.drawable.record_cancel);
                dialogTextView.setText("松开手指可取消录音");
                break;
        }
        dialogTextView.setTextSize(14);
        mRecordDialog.show();
    }

    // 录音时间太短时Toast显示
    private void showWarnToast(String toastText) {
        mToast = new Toast(mContext);
        View warnView = LayoutInflater.from(mContext).inflate(
                R.layout.toast_warn, null);
        mToast.setView(warnView);
        mToast.setGravity(Gravity.TOP, 0, CommonUtils.dip2px(mContext,100));// 起点位置为中间
        mToast.show();
    }

    // 开启录音计时线程
    private void callRecordTimeThread() {
        mRecordThread = new Thread(recordThread);
        mRecordThread.start();
    }

    // 录音Dialog图片随录音音量大小切换
    private void setDialogImage() {
        if (voiceValue < 600.0) {
            dialogImg.setImageResource(R.drawable.record_animate_01);
        } else if (voiceValue > 600.0 && voiceValue < 1000.0) {
            dialogImg.setImageResource(R.drawable.record_animate_02);
        } else if (voiceValue > 1000.0 && voiceValue < 1200.0) {
            dialogImg.setImageResource(R.drawable.record_animate_03);
        } else if (voiceValue > 1200.0 && voiceValue < 1400.0) {
            dialogImg.setImageResource(R.drawable.record_animate_04);
        } else if (voiceValue > 1400.0 && voiceValue < 1600.0) {
            dialogImg.setImageResource(R.drawable.record_animate_05);
        } else if (voiceValue > 1600.0 && voiceValue < 1800.0) {
            dialogImg.setImageResource(R.drawable.record_animate_06);
        } else if (voiceValue > 1800.0 && voiceValue < 2000.0) {
            dialogImg.setImageResource(R.drawable.record_animate_07);
        } else if (voiceValue > 2000.0 && voiceValue < 3000.0) {
            dialogImg.setImageResource(R.drawable.record_animate_08);
        } else if (voiceValue > 3000.0 && voiceValue < 4000.0) {
            dialogImg.setImageResource(R.drawable.record_animate_09);
        } else if (voiceValue > 4000.0 && voiceValue < 6000.0) {
            dialogImg.setImageResource(R.drawable.record_animate_10);
        } else if (voiceValue > 6000.0 && voiceValue < 8000.0) {
            dialogImg.setImageResource(R.drawable.record_animate_11);
        } else if (voiceValue > 8000.0 && voiceValue < 10000.0) {
            dialogImg.setImageResource(R.drawable.record_animate_12);
        } else if (voiceValue > 10000.0 && voiceValue < 12000.0) {
            dialogImg.setImageResource(R.drawable.record_animate_13);
        } else if (voiceValue > 12000.0) {
            dialogImg.setImageResource(R.drawable.record_animate_14);
        }
    }

    // 录音线程
    private Runnable recordThread = new Runnable() {

        @Override
        public void run() {
            recodeTime = 0.0f;
            while (recordState == RECORD_ON) {
                {
                    try {
                        Thread.sleep(100);
                        recodeTime += 0.1;
                        // 获取音量，更新dialog
                        if (!isCanceled) {
                            voiceValue = mAudioRecorder.getAmplitude();
                            recordHandler.sendEmptyMessage(1);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler recordHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setDialogImage();
        }
    };

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        Acp.getInstance(getContext()).request(new AcpOptions.Builder().setPermissions(Manifest
                        .permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN: // 按下按钮
                                getParent().requestDisallowInterceptTouchEvent(true);//屏蔽父控件拦截onTouch事件
                                if (recordState != RECORD_ON) {
                                    setSelected(true);
                                    showVoiceDialog(0);
                                    downY = event.getY();
                                    if (mAudioRecorder != null) {
                                        mAudioRecorder.ready();
                                        recordState = RECORD_ON;
                                        mAudioRecorder.start();
                                        callRecordTimeThread();
                                    }
                                    recordStatusListener.status(RECORD_ON);
                                }
                                break;
                            case MotionEvent.ACTION_MOVE: // 滑动手指
                                File file = new File(getFilePath());
                                if (file.exists()){
                                    float moveY = event.getY();
                                    if (downY - moveY > 50) {
                                        isCanceled = true;
                                        showVoiceDialog(1);
                                    }
                                    if (downY - moveY < 20) {
                                        isCanceled = false;
                                        showVoiceDialog(0);
                                    }
                                }
                                break;
                            case MotionEvent.ACTION_UP: // 松开手指
                                getParent().requestDisallowInterceptTouchEvent(true);//恢复父控件拦截onTouch事件
                                if (recordState == RECORD_ON) {
                                    setSelected(false);
                                    recordState = RECORD_Done;
                                    if (mRecordDialog.isShowing()) {
                                        mRecordDialog.dismiss();
                                    }
                                    mAudioRecorder.stop();
                                    mRecordThread.interrupt();
                                    voiceValue = 0.0;
                                    if (isCanceled) {
                                        mAudioRecorder.deleteOldFile();
                                    } else {
                                        if (recodeTime < MIN_RECORD_TIME) {
                                            showWarnToast("时间太短  录音失败");
                                            mAudioRecorder.deleteOldFile();
                                        } else {
                                            if (listener != null) {
                                                listener.recordEnd(mAudioRecorder.getFilePath());
                                            }
                                        }
                                    }
                                    isCanceled = false;
                                    recordStatusListener.status(RECORD_Done);
                                }
                                break;
                            case MotionEvent.ACTION_CANCEL:
                                if (mRecordDialog.isShowing()) {
                                    mRecordDialog.dismiss();
                                }
                                mAudioRecorder.stop();
                                mAudioRecorder.deleteOldFile();
                                recordState = RECORD_OFF;
                                recordStatusListener.status(RECORD_OFF);
                                break;
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions) {

                    }
                });

        return true;
    }

    public void deleteFile() {
        mAudioRecorder.deleteOldFile();
    }

    public String getFilePath() {
        return mAudioRecorder.getFilePath();
    }

    public String getParentPath() {
        return mAudioRecorder.getParentPath();
    }

    public interface RecordListener {
        public void recordEnd(String filePath);
    }

    public interface RecordStatusListener {
        public void status(int status);
    }

    public void setRecordStatusListener(RecordStatusListener recordStatusListener) {
        this.recordStatusListener = recordStatusListener;
    }


}
