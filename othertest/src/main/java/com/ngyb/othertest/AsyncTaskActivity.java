package com.ngyb.othertest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/8/5 21:36
 */
public class AsyncTaskActivity extends AppCompatActivity {
    private static final String TAG = "AsyncTaskActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
    }

    public void start(View view) {
        new MyAsyncTask("#1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        new MyAsyncTask("#2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        new MyAsyncTask("#3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        new MyAsyncTask("#4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
        new MyAsyncTask("#5").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
    }

    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {
        private String name = null;

        public MyAsyncTask(String name) {
            this.name = name;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 在主线程执行，在doInBackground
        }

        @Override
        protected String doInBackground(String... strings) {
            // 子线程 需要在子线程执行的代码放到这里
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            //主线程，doInBackground之后
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            Log.e(TAG, "onPostExecute:" + s + "执行结束" + df.format(new Date()));

        }
    }
}
