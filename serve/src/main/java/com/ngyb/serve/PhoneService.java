package com.ngyb.serve;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/9/28 16:12
 */
public class PhoneService extends Service {
    private static final String TAG = "PhoneService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart: ");
        super.onStart(intent, startId);
    }

    @Override
    public void onCreate() {
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        tm.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    private class MyPhoneStateListener extends PhoneStateListener {

        private MediaRecorder mRecorder;

        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE://空闲
                    if (mRecorder != null) {
                        mRecorder.stop();//停止录音
                        mRecorder.reset();
                        mRecorder.release();
                    }
                    Log.e(TAG, "onCallStateChanged: CALL_STATE_IDLE");
                    break;
                case TelephonyManager.CALL_STATE_RINGING://响铃
                    mRecorder = new MediaRecorder();
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置音频来源
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//设置输出文件
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//设置音频编码方式
                    mRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+System.currentTimeMillis()+".mp3");//指定文件的存放路径
                    try {
                        mRecorder.prepare();//准备录音
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "onCallStateChanged: CALL_STATE_RINGING");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://通话
                    if (mRecorder == null) {
                        mRecorder = new MediaRecorder();
                        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置音频来源
                        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//设置输出文件
                        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//设置音频编码方式
                        mRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+System.currentTimeMillis()+".mp3");//指定文件的存放路径
                        try {
                            mRecorder.prepare();//准备录音
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    mRecorder.start();
                    Log.e(TAG, "onCallStateChanged: CALL_STATE_OFFHOOK");
                    break;
            }
            super.onCallStateChanged(state, phoneNumber);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
