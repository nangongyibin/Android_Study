package com.ngyb.layout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/31 10:03
 */
public class ScrollbarActivity extends AppCompatActivity {
    private int[] imageResId = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.e};
    private String[] descs = {"巩俐不低俗，我就不能低俗", "朴树又回来了,再唱经典老歌..", "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀"};
    private ViewPager vp;
    private LinearLayout ll;
    private TextView tv;
    private List<ImageView> list;
    private int vpMax = 721 * imageResId.length;
    private View currentChild;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 10) {
                vp.setCurrentItem(vp.getCurrentItem() + 1);
                handler.sendEmptyMessageDelayed(10,3000);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollbar);
        initIv();
        vp = findViewById(R.id.vp);
        ll = findViewById(R.id.ll);
        tv = findViewById(R.id.tv);
        MyPagerAdapter adapter = new MyPagerAdapter();
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeUI(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initDot();
        changeUI(0);
    }

    @Override
    protected void onStart() {
        handler.sendEmptyMessageDelayed(10, 3000);
        super.onStart();
    }

    private void initDot() {
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = list.get(i);
            View view = new View(getApplicationContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(7, 7);
            if (i != 0) {
                layoutParams.leftMargin = 7;
            }
            view.setBackgroundResource(R.drawable.selctor_dot);
            view.setLayoutParams(layoutParams);
            ll.addView(view);
        }
    }

    private void changeUI(int position) {
        position = position % list.size();
        View child = ll.getChildAt(position);
        child.setSelected(true);
        tv.setText(descs[position]);
        if (currentChild != null) {
            currentChild.setSelected(false);
        }
        currentChild = child;
    }

    private void initIv() {
        list = new ArrayList<>();
        for (int i : imageResId) {
            ImageView iv = new ImageView(getApplicationContext());
            iv.setBackgroundResource(i);
            list.add(iv);
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return vpMax;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            position = position % imageResId.length;
            ImageView imageView = list.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeMessages(10);
        super.onDestroy();
    }
}
