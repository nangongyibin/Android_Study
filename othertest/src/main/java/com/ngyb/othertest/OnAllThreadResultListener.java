package com.ngyb.othertest;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 09:44
 */
public interface OnAllThreadResultListener {
    void onSuccess();//所有线程执行完毕

    void onFailed();//所有线程执行出现问题
}
