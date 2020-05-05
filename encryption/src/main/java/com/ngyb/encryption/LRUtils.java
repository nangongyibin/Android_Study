package com.ngyb.encryption;

import android.util.Log;

import com.ngyb.utils.StreamUtils;
import com.ngyb.utils.encryption.MD5;
import com.ngyb.utils.encryption.RSA;
import com.ngyb.utils.encryption.SignatureUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/5 18:08
 */
public class LRUtils {
    private static final String TAG = "LRUtils";

    public static void register() {
        String username = Constant.username;
        String password = "123456";
        try {
            String url = Constant.base_url + "register/userRegister?username=" + username + "&password=" + MD5.Md5Str("MD5", password + "+aa/rewc");
            Log.e(TAG, "register: " + url);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            Log.e(TAG, "register: " + conn.getResponseCode());
            InputStream ins = conn.getInputStream();
            String s1 = StreamUtils.StreamToString(ins);
//            String s = StreamUtils.convertStreamToString(ins);
//            Log.e(TAG, "register: " + s);
            Log.e(TAG, "register: " + s1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void login1() {
        String username = Constant.username;
        String password = "123456";
        try {
            String url = Constant.base_url + "login/login?username=" + username + "&password=" + MD5.Md5Str("MD5", password + "+aa/rewc");
            Log.e(TAG, "login1: " + url);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            InputStream ins = conn.getInputStream();
            String s = StreamUtils.StreamToString(ins);
            Log.e(TAG, "login1: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login2() {
        String username = Constant.username;
        String password = "123456";
        try {
            String url = Constant.base_url + "login/login_v2?username=" + username + "&password=" + MD5.Md5Str("MD5", password + "+aa/rewc") + "&timestamp=" + System.currentTimeMillis();
            Log.e(TAG, "login2: " + url);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            InputStream ins = conn.getInputStream();
            String s = StreamUtils.StreamToString(ins);
            Log.e(TAG, "login2: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login3() {
        String username = Constant.username;
        String password = "123456";
        String k = "username=" + username + "&password=" + MD5.Md5Str("MD5", password + "+aa/rewc") + "&timestamp=" + System.currentTimeMillis();
        String sign = SignatureUtils.sign("SHA256withRSA", k, RSA.getPrivateKey("RSA", null));
        try {
            String url = Constant.base_url + "login/login_v3?" + k + "&sign=" + sign;
            Log.e(TAG, "login3: " + url);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            InputStream ins = conn.getInputStream();
            String s = StreamUtils.StreamToString(ins);
            Log.e(TAG, "login3: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login4() {
        String username = Constant.username;
        String password = "123456";
        String k = "username=" + username + "&password=" + MD5.Md5Str("MD5", password + "+aa/rewc") + "&timestamp=" + System.currentTimeMillis();
        String sign = SignatureUtils.sign("SHA256withRSA", k, RSA.getPrivateKey("RSA", null));
        try {
            String url = Constant.base_url + "login/login_v4?" + k + "&sign=" + sign;
            Log.e(TAG, "login4: " + url);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            InputStream ins = conn.getInputStream();
            String s = StreamUtils.StreamToString(ins);
            Log.e(TAG, "login4: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login5() {
        String username = Constant.username;
        String password = "123456";
        String k = "username=" + username + "&password=" + MD5.Md5Str("MD5", password + "+aa/rewc") + "&timestamp=" + System.currentTimeMillis();
        String sign = SignatureUtils.sign("SHA256withRSA", k, RSA.getPrivateKey("RSA", null));
        try {
            String url = Constant.base_url + "login/login_v5?" + k + "&sign=" + sign;
            Log.e(TAG, "login5: " + url);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            InputStream ins = conn.getInputStream();
            String s = StreamUtils.StreamToString(ins);
            Log.e(TAG, "login5: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login6() {
        String username = Constant.username;
        String password = "123456";
        String k = "username=" + username + "&password=" + MD5.Md5Str("MD5", password + "+aa/rewc") + "&timestamp=" + System.currentTimeMillis();
        String sign = SignatureUtils.sign("SHA256withRSA", k, RSA.getPrivateKey("RSA", null));
        try {
            String url = Constant.base_url + "login/login_v6?" + k + "&sign=" + sign;
            Log.e(TAG, "login6: " + url);
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            InputStream ins = conn.getInputStream();
            String s = StreamUtils.StreamToString(ins);
            Log.e(TAG, "login6: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
