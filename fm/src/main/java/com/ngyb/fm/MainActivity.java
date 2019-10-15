package com.ngyb.fm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        int width = display.getWidth();
//        int height = display.getHeight();
//        FragmentManager fm = getSupportFragmentManager();
//        if (width > height) {
//            fm.beginTransaction().replace(R.id.content, new OneFragment()).commit();
//        } else {
//            fm.beginTransaction().replace(R.id.content, new TwoFragment()).commit();
//        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fm1, new OneFragment(), "fm1");
        ft.replace(R.id.fm2, new TwoFragment(), "fm2");
        ft.commit();
    }

//    public void wechat(View view) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.replace(R.id.ll, new WechatFragment()).commit();
//    }
//
//    public void ipa(View view) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.replace(R.id.ll, new IpaFragment()).commit();
//    }
//
//    public void discover(View view) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.replace(R.id.ll, new DiscoverFragment()).commit();
//    }
//
//    public void mime(View view) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.replace(R.id.ll, new MimeFragment()).commit();
//    }
}
