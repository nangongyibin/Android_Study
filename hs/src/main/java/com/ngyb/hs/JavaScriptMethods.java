package com.ngyb.hs;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.ngyb.utils.StreamUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/5 22:33
 */
public class JavaScriptMethods {
    private Context context;
    private WebView webView;
    private static final String TAG = "JavaScriptMethods";

    public JavaScriptMethods(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
    }

    @JavascriptInterface
    public void showToast(String json) {
        Log.e(TAG, "showToast: ");
        Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void getNetData(final String json) {
        Log.e(TAG, "getNetData: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL("http://it.nangongyibin.com:8080/resource/mobicop.json").openConnection();
                    InputStream ins = conn.getInputStream();
                    final String result = StreamUtils.StreamToString(ins);
                    Log.e(TAG, "run: " + result);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String callback = jsonObject.optString("callback");
                                Log.e(TAG, "run: " + callback);
                                webView.loadUrl("javascript:" + callback + "(" + result + ")");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Handler handler = new Handler();
}
