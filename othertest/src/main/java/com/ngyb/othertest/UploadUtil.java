package com.ngyb.othertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 09:46
 */
public class UploadUtil {
    private final static int THREAD_PROGRESS_CODE = 100;//线程进度回调
    private final static int THREAD_FINISH_CODE = 101;//线程完成
    private final static int THREAD_INTERRUPT_CODE = 102;//线程被中断
    private final static int THREAD_ALL_SUCCESS_CODE = 103;//所有线程完成
    private final static int THREAD_ALL_FAILED_CODE = 104;//所有线程执行失败
    private final static String THREAD_PERCENT = "THREAD_PERCENT";
    private final static String THREAD_POSITION = "THREAD_POSITION";

    private int threadCount = 0;//任务数量
    private int threadCore = 2;//线程池核心数

    private ExecutorService executor;//线程池
    private CountDownLatch downLatch;//计数器

    private OnUploadListener uploadListener;
    private UploadHandler handler;

    public UploadUtil() {
        init();
    }

    public UploadUtil(int threadCore) {
        this.threadCore = threadCore;
        init();
    }

    public void setOnUploadListener(OnUploadListener uploadListener) {
        this.uploadListener = uploadListener;
    }

    public void init() {
        handler = new UploadHandler(this);
    }

    public void shutDownNow() {
        executor.shutdownNow();//中断所有线程的执行
    }

    public void submitAll(ArrayList<String> fileName) {
        threadCount = fileName.size();
//        CountDownLatch的用法
//        CountDownLatch典型用法：1、某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为new CountDownLatch(n)，每当一个任务线程执行完毕，就将计数器减1 countdownLatch.countDown()，当计数器的值变为0时，在CountDownLatch上await()的线程就会被唤醒。一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
//        CountDownLatch典型用法：2、实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个共享的CountDownLatch(1)，将其计算器初始化为1，多个线程在开始执行任务前首先countdownlatch.await()，当主线程调用countDown()时，计数器变为0，多个线程同时被唤醒。
//        CountDownLatch的不足
//        CountDownLatch是一次性的，计算器的值只能在构造方法中初始化一次，之后没有任何机制再次对其设置值，当CountDownLatch使用完毕后，它不能再次被使用。
        downLatch = new CountDownLatch(threadCount);
        executor = Executors.newFixedThreadPool(threadCore + 1);

        executor.submit(new UploadListener(downLatch, new OnAllThreadResultListener() {//创建一个监听线程
            @Override
            public void onSuccess() {
                handler.sendEmptyMessage(THREAD_ALL_SUCCESS_CODE);
            }

            @Override
            public void onFailed() {
                handler.sendEmptyMessage(THREAD_ALL_FAILED_CODE);
            }
        }));

        for (int i = 0; i < threadCount; i++) {//模拟生成任务线程
            final Bundle bundle = new Bundle();
            bundle.putInt(THREAD_POSITION, i);
            executor.submit(new UploadFile(downLatch, fileName.get(i), new OnThreadResultListener() {

                @Override
                public void onProgressChange(final int percent) {
                    bundle.putInt(THREAD_PERCENT, percent);
                    Message.obtain(handler, THREAD_PROGRESS_CODE, bundle).sendToTarget();
                }

                @Override
                public void onFinish() {
                    Message.obtain(handler, THREAD_FINISH_CODE, bundle).sendToTarget();
                }

                @Override
                public void onInterrupted() {
                    Message.obtain(handler, THREAD_INTERRUPT_CODE, bundle).sendToTarget();
                }
            }));
        }
        executor.shutdown();//关闭线程池
    }

    private static class UploadHandler extends Handler {//静态内部类+弱引用防止内存泄漏
        private WeakReference<UploadUtil> weakReference;

        private UploadHandler(UploadUtil object) {
            super(Looper.getMainLooper());//执行在UI线程
            weakReference = new WeakReference<>(object);
        }

        @Override
        public void handleMessage(Message msg) {
            UploadUtil uploadUtil = weakReference.get();
            if (uploadUtil != null) {
                Bundle data = (Bundle) msg.obj;
                int position;
                int percent;

                switch (msg.what) {
                    case THREAD_PROGRESS_CODE:
                        position = data.getInt(THREAD_POSITION);
                        percent = data.getInt(THREAD_PERCENT);
                        uploadUtil.uploadListener.onThreadProgressChange(position, percent);
                        break;
                    case THREAD_FINISH_CODE:
                        position = data.getInt(THREAD_POSITION);
                        uploadUtil.uploadListener.onThreadFinish(position);
                        break;
                    case THREAD_INTERRUPT_CODE:
                        position = data.getInt(THREAD_POSITION);
                        uploadUtil.uploadListener.onThreadInterrupted(position);
                        break;
                    case THREAD_ALL_SUCCESS_CODE:
                        uploadUtil.uploadListener.onAllSuccess();
                        break;
                    case THREAD_ALL_FAILED_CODE:
                        uploadUtil.uploadListener.onAllFailed();
                        break;
                }
            }
        }
    }
}
