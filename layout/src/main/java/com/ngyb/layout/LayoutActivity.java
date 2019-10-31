package com.ngyb.layout;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LayoutActivity extends AppCompatActivity {
    private static final String TAG = "LayoutActivity";
    private AutoCompleteTextView mActv;
    private EditText mEtNumber;
    private ImageButton mIb;
    private List<String> list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
//        initActv();
        initData();
    }

    private void initData() {
        mEtNumber = findViewById(R.id.etNumber);
        mIb = findViewById(R.id.ib);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("10086" + i);
        }
        mIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        ListView lv = initListView();
        final PopupWindow popupWindow = new PopupWindow(lv, mEtNumber.getWidth(), 1080, true);
        popupWindow.showAsDropDown(mEtNumber, 0, -20);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemClick: " + position);
                String str = list.get(position);
                mEtNumber.setText(str);
                popupWindow.dismiss();
            }
        });
    }

    private ListView initListView() {
        ListView lv = (ListView) View.inflate(this, R.layout.listviewbg, null);
        lv.setDivider(new ColorDrawable(Color.GRAY));
        lv.setDividerHeight(1);
        MyAdapter myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);
        return lv;
    }

    private void initActv() {
        String[] str = {"小周", "小青", "小成", "小闫"};
        mActv = findViewById(R.id.actv);
        mActv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, str));
    }

    public void start(View view) {
        final MyView mv = findViewById(R.id.mv);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 360; i++) {
                    Log.e(TAG, "run: "+i );
                    mv.setProgress(i);
                    SystemClock.sleep(2000);
                }
            }
        }).start();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (list != null && list.size() > 0) {
                return list.size();
            }
            return 0;
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.item, null);
            }
            TextView tv = convertView.findViewById(R.id.tv);
            ImageView iv = convertView.findViewById(R.id.iv);
            ImageButton ib = convertView.findViewById(R.id.ib);
            ib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(getItem(position));
                    notifyDataSetChanged();
                }
            });
            tv.setText(getItem(position));
            return convertView;
        }
    }
}
