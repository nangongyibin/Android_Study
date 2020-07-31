package com.ngyb.othertest;

import java.util.concurrent.CountDownLatch;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 09:45
 */
public class UploadListener implements Runnable {
    private CountDownLatch downLatch;
    private OnAllThreadResultListener listener;

    public UploadListener(CountDownLatch countDownLatch,OnAllThreadResultListener listener){
        this.downLatch=countDownLatch;
        this.listener=listener;
    }

    @Override
    public void run() {
        try {
            downLatch.await();
            listener.onSuccess();//顺利完成
        } catch (InterruptedException e) {
            listener.onFailed();
        }
    }
}