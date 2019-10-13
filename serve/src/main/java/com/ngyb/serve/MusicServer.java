package com.ngyb.serve;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/1 19:43
 */
public interface MusicServer {
    public void play();

    public void pause();

    public void continuePlay();

    public void playPosition(int position);
}
