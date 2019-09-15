package com.ngyb.sms;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/9/15 20:14
 */
public class SmsActivity extends AppCompatActivity {
    private String[] smss = {"欲，寄一份情，陪在远方的你。欲，借一首曲，陪若在眼前的你。欲，给一颗心，陪在我心底的你，欲，用这一生，爱慕只在乎的你。",
            "白色情人节，送你七彩画卷：红色是爱恋，热情只增不减；橙色是希望，放飞共同梦想；黄色是幸福，执手浪漫一生；绿色是信任，忠于彼此不变；蓝色是思念，时刻心儿相连；靛蓝是自由，用责任去守侯；紫色是关爱，体贴照顾不烦。亲爱的，情人节快乐！",
            "偷我情，盗我心，决定告你上法庭，判你终生嫁给我；懂我心，明我意，跟我办理结婚证；爱上你，恋上你，天天都想拥有你！",
            "一条红线把缘牵，两情相悦把爱恋，三顾茅庐把你约，四面楚歌情敌战，五花八门哄你欢，六神无主看见你，七上八下想娶你，九死一生都爱你，十全十美来联姻。嫁给我，你就是我的唯一。",
            "不经意间，相遇；不经意间，相惜；不经意间，刻骨；不经意间，铭记；不经意间，爱上了你。看似不经意，但我真的很在意。",
            "等待一次日出，需要一天；等待一次月圆，需要一月；等待一次花开，需要一年；等待一次“爱你一生一世”，需要一辈子。亲爱的，你的一辈子，请让我来承担。"};
    private ListView mLv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mLv = findViewById(R.id.lv);
        init();
    }

    private void init() {
        mLv.setAdapter(new MyAdapter());
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Uri uri = Uri.parse("smsto:18534867219");
//                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                intent.putExtra("sms_body", smss[position]);
//                startActivity(intent);
                Intent intent = new Intent();
                intent.putExtra("content", smss[position]);
                setResult(520, intent);
                finish();
            }
        });
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return smss.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(SmsActivity.this);
            }
            ((TextView) convertView).setText(smss[position]);
            ((TextView) convertView).setTextSize(23f);
            ((TextView) convertView).setTextColor(Color.BLACK);
            return convertView;
        }
    }
}
