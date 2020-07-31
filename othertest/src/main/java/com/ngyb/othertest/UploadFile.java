package com.ngyb.othertest;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 09:45
 */
public class UploadFile implements Runnable {
    private CountDownLatch downLatch;//计数器
    private String fileName;//文件名
    private OnThreadResultListener listener;//任务线程回调接口
    private int percent = 0;//进度
    private Random mRandom;//随机数 模拟上传

    public UploadFile(CountDownLatch downLatch, String fileName, OnThreadResultListener listener) {
        this.downLatch = downLatch;
        this.fileName = fileName;
        this.listener = listener;

        mRandom = new Random();
    }

    @Override
    public void run() {
        try {
            while (percent <= 100) {
                listener.onProgressChange(percent);
                percent += 1;
                Thread.sleep(mRandom.nextInt(60) + 30);//模拟延迟
            }
            this.downLatch.countDown();
            listener.onFinish();//顺利完成
        } catch (InterruptedException e) {
            listener.onInterrupted();//被中断
        }
    }
}
