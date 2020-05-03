package com.ngyb.leaks;

import android.content.Context;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/3 13:55
 */
public class HttpUtils {
    private static HttpUtils httpUtils;
    private static Context ngyb;

    public HttpUtils() {
    }

    public static HttpUtils getInstance(Context context){
        if (httpUtils ==null){
            synchronized (HttpUtils.class){
                if (httpUtils ==null){
                    httpUtils = new HttpUtils();
                    ngyb = context;
                }
            }
        }
        return httpUtils;
    }
}
