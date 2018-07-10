package xxk.wuhai.seacurity.weight.record;

import android.os.Environment;


import com.czt.mp3recorder.MP3Recorder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AudioRecorder implements RecordStrategy {

    //    private MediaRecorder recorder;
    private String fileName;
    private String fileFolder = Environment.getExternalStorageDirectory().getPath() + "/xxk/";
    private String filePath = fileFolder + "luyin" + ".mp3";

    private boolean isRecording = false;
    private MP3Recorder mp3Recorder;

    @Override
    public void ready() {
        File file = new File(fileFolder);
        if (!file.exists()) {
            file.mkdir();
        }
        fileName = getCurrentDate();
//        recorder = new MediaRecorder();
//        recorder.setOutputFile(filePath);
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置MediaRecorder的音频源为麦克风
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);// 设置MediaRecorder录制的音频格式
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// 设置MediaRecorder录制音频的编码为amr
        mp3Recorder = new MP3Recorder(new File(filePath));
    }

    // 以当前时间作为文件名
    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        if (!isRecording) {
            try {
//                recorder.prepare();
//                recorder.start();
                mp3Recorder.startRecording();
            } catch (Exception e) {
                e.printStackTrace();
            }
            isRecording = true;
        }

    }

    @Override
    public void stop() {
        if (isRecording) {
//            recorder.stop();
//            recorder.release();
            mp3Recorder.stopRecording();
            isRecording = false;
        }

    }

    @Override
    public void deleteOldFile() {
        File file = new File(fileFolder);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            files[i].delete();
        }
    }

    @Override
    public double getAmplitude() {
        if (!isRecording) {
            return 0;
        }
        int volume = mp3Recorder.getVolume();
//        return recorder.getMaxAmplitude();
        return volume;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }


    @Override
    public String getParentPath() {
        return fileFolder;
    }


}
