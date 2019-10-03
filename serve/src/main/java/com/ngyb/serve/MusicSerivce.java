package com.ngyb.serve;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/1 19:45
 */
public class MusicSerivce extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public void Play() {
        Toast.makeText(this, "播放", Toast.LENGTH_SHORT).show();
    }

    public void Pause() {
        Toast.makeText(this, "暂停", Toast.LENGTH_SHORT).show();
    }

    public void ContinuePlay() {
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
    }
}
