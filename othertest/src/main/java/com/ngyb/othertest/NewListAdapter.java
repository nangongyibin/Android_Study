package com.ngyb.othertest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 14:33
 */
public class NewListAdapter extends BaseAdapter {
    private Context context;
    private List<NewListBean.DataBean.NewsBean> list;
    private int TYPE_ZERO = 0;
    private int TYPE_ONE = 1;
    private String baseUrl = "http://it.nangongyibin.com:8080/BeiJingWisdom/";

    public NewListAdapter(Context context, List<NewListBean.DataBean.NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        NewListBean.DataBean.NewsBean newsBean = list.get(position);
        if (getItemViewType(position) == TYPE_ZERO) {
            ZeroViewHolder zeroViewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.type_item_zero, null);
                zeroViewHolder = new ZeroViewHolder(convertView);
                convertView.setTag(zeroViewHolder);
            } else {
                zeroViewHolder = (ZeroViewHolder) convertView.getTag();
            }
            zeroViewHolder.tv1.setText(newsBean.getTitle());
            zeroViewHolder.tv2.setText(newsBean.getPubdate());
            Glide.with(context).load(baseUrl + newsBean.getListimage()).into(zeroViewHolder.iv1);
        } else {
            OneViewHolder oneViewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.type_item_one, null);
                oneViewHolder = new OneViewHolder(convertView);
                convertView.setTag(oneViewHolder);
            } else {
                oneViewHolder = (OneViewHolder) convertView.getTag();
            }
            oneViewHolder.tv3.setText(newsBean.getTitle());
            oneViewHolder.tv4.setText(newsBean.getPubdate());
            Glide.with(context).load(baseUrl + newsBean.getListimage1()).into(oneViewHolder.iv2);
            Glide.with(context).load(baseUrl + newsBean.getListimage2()).into(oneViewHolder.iv3);
            Glide.with(context).load(baseUrl + newsBean.getListimage3()).into(oneViewHolder.iv4);
        }
        ScaleAnimation animation = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setInterpolator(new OvershootInterpolator());
        convertView.startAnimation(animation);
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        String type = list.get(position).getType();
        return Integer.parseInt(type);
    }

    class ZeroViewHolder {

        private final ImageView iv1;
        private final TextView tv2;
        private final TextView tv1;

        public ZeroViewHolder(View view) {
            iv1 = view.findViewById(R.id.iv1);
            tv1 = view.findViewById(R.id.tv1);
            tv2 = view.findViewById(R.id.tv2);
        }
    }

    class OneViewHolder {

        private final ImageView iv4;
        private final ImageView iv3;
        private final ImageView iv2;
        private final TextView tv4;
        private final TextView tv3;

        public OneViewHolder(View view) {
            tv3 = view.findViewById(R.id.tv3);
            tv4 = view.findViewById(R.id.tv4);
            iv2 = view.findViewById(R.id.iv2);
            iv3 = view.findViewById(R.id.iv3);
            iv4 = view.findViewById(R.id.iv4);
        }
    }
}
