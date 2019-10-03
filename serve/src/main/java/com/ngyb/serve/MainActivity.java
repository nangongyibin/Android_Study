package com.ngyb.serve;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ngyb.service.AidlInterface;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Intent mIntent;
    private myServiceConnection mMyServiceConnection;
    private String[] str =
            {
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
            };
    private IServer mIServer;
    private MusicServer mMusicServer;
    private AidlInterface mAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, str[0]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                str[1]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, str[2]) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, str[3]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, str, 1);
        }
//        Intent intent = new Intent(this, CertificateService.class);
//        bindService(intent, new myServiceConnection(), BIND_AUTO_CREATE);
//        Intent intent1 = new Intent(this, MusicSerivce.class);
////        startService(intent1);
//        bindService(intent1, new myServiceConnection(), BIND_AUTO_CREATE);
        Intent intent2 = new Intent();
        intent2.setAction("ngyb.ltz");
        intent2.setPackage("com.ngyb.service");
        bindService(intent2, new myServiceConnection(), BIND_AUTO_CREATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                switch (permissions[i]) {
                    case Manifest.permission.READ_PHONE_STATE:
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case Manifest.permission.READ_EXTERNAL_STORAGE:
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case Manifest.permission.RECORD_AUDIO:
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        }
    }

    public void click1(View view) {
        mIntent = new Intent(this, PhoneService.class);
        startService(mIntent);
    }

    public void click2(View view) {
        stopService(mIntent);
    }

    public void click3(View view) {
        mIntent = new Intent(this, PhoneService.class);
        mMyServiceConnection = new myServiceConnection();
        bindService(mIntent, mMyServiceConnection, BIND_AUTO_CREATE);
    }

    public void click4(View view) {
        unbindService(mMyServiceConnection);
    }

    public void click5(View view) {
        Intent intent = new Intent(this, ScreenService.class);
        startService(intent);
    }

    public void click6(View view) {
        mMusicServer.play();
    }

    public void click7(View view) {
        mMusicServer.pause();
    }

    public void click8(View view) {
        mMusicServer.continuePlay();
    }

    public void Certificate(View view) {
        mIServer.Certificate(300);
    }

    public void click9(View view) {
        Log.e(TAG, "click9: ");
        try {
            mAidlInterface.callTest();
        } catch (RemoteException e) {
            Log.e(TAG, "click9: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public void click10(View view) {
        try {
            boolean isSuccess = mAidlInterface.buyBeans("ngyb", "18534867219", 1000);
            if (isSuccess){
                Toast.makeText(this, "买豆成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "买豆失败", Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public class myServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            mIServer = (IServer) service;
//            mMusicServer = (MusicServer) service;
            mAidlInterface = AidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
