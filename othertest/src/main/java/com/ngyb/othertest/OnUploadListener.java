package com.ngyb.othertest;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 09:43
 */
public interface OnUploadListener {
    void onAllSuccess();

    void onAllFailed();

    void onThreadProgressChange(int position, int percent);

    void onThreadFinish(int position);

    void onThreadInterrupted(int position);
}
