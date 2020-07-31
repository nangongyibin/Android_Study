package com.ngyb.othertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 14:30
 */
public class MultipleEntriesActivity extends AppCompatActivity {

    private ListView lv;
    private NewListAdapter newListAdapter;
    private List<NewListBean.DataBean.NewsBean> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_entries);
        lv = findViewById(R.id.lv);
        newListAdapter = new NewListAdapter(this,list);
        lv.setAdapter(newListAdapter);
        loadNetData();
    }

    private void loadNetData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("http://it.nangongyibin.com:8080/BeiJingWisdom/10007/list_3.json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                assert response.body() != null;
                NewListBean newListBean = new Gson().fromJson(response.body().string(), NewListBean.class);
                if (newListBean!=null && newListBean.getData()!=null &&newListBean.getData().getNews()!=null){
                    list.addAll(newListBean.getData().getNews());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            newListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }
}
