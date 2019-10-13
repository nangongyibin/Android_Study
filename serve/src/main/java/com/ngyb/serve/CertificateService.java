package com.ngyb.serve;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/1 19:10
 */
public class CertificateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends Binder implements IServer {

        @Override
        public void Certificate(int money) {
            if (money >50){
                Toast.makeText(CertificateService.this, "马上给你办证", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(CertificateService.this, "钱不够，办什么证", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
