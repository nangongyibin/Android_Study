package com.ngyb.serve;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/1 19:45
 */
public class MusicSerivce extends Service {
    private static final String TAG = "MusicSerivce";
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void Play() {
        try {
            String path = Environment.getExternalStorageDirectory().getPath() + "/data/xpg.mp3";
            Log.e(TAG, "Play: " + path);
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            Toast.makeText(this, "播放", Toast.LENGTH_SHORT).show();
            updataProgress();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updataProgress() {
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int duration = mMediaPlayer.getDuration();
                int currentPosition = mMediaPlayer.getCurrentPosition();
                Bundle bundle = new Bundle();
                bundle.putInt("max",duration);
                bundle.putInt("current",currentPosition);
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                MainActivity.mHandler.sendMessage(obtain);
            }
        };
        timer.schedule(timerTask,1000,1000);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                timer.cancel();
            }
        });
    }

    public void Pause() {
        mMediaPlayer.pause();
        Toast.makeText(this, "暂停", Toast.LENGTH_SHORT).show();
    }

    public void ContinuePlay() {
        mMediaPlayer.start();
        Toast.makeText(this, "继续播放", Toast.LENGTH_SHORT).show();
    }

    public class MyBinder extends Binder implements MusicServer {

        @Override
        public void play() {
            Play();
        }

        @Override
        public void pause() {
            Pause();
        }

        @Override
        public void continuePlay() {
            ContinuePlay();
        }

        @Override
        public void playPosition(int position) {
            PlayPosition(position);
        }
    }

    private void PlayPosition(int position) {
        mMediaPlayer.seekTo(position);
    }
}
