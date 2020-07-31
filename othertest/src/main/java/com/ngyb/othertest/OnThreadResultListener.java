package com.ngyb.othertest;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 09:44
 */
public interface OnThreadResultListener {
    void onProgressChange(int percent);//进度变化回调

    void onFinish();//线程完成时回调

    void onInterrupted();//线程被中断回调
}
