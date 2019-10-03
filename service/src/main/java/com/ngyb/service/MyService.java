package com.ngyb.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/3 15:24
 */
public class MyService extends Service {
    private static final String TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    class MyBind extends AidlInterface.Stub {

        @Override
        public void callTest() throws RemoteException {
            Test();
        }

        @Override
        public boolean buyBeans(String name, String phone, int money) throws RemoteException {
            return pay(name, phone, money);
        }
    }

    private boolean pay(String name, String phone, int money) {
        return name.equals("ngyb") && phone.equals("18534867219");
    }

    private void Test() {
        Log.e(TAG, "Test: 测试打电话");
    }
}
