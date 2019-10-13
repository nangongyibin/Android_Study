package com.ngyb.contentproviderhandle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2019/10/9 14:18
 */
public class MyAdapter extends BaseAdapter {
    private final Context context;
    private List<Contract> mContractList = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (mContractList != null && mContractList.size() > 0) {
            return mContractList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Contract getItem(int position) {
        return mContractList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.adapter_item, null);
        }
        TextView tv1 = convertView.findViewById(R.id.tv1);
        TextView tv2 = convertView.findViewById(R.id.tv2);
        TextView tv3 = convertView.findViewById(R.id.tv3);
        Contract contract = getItem(position);
        tv1.setText(contract.getName());
        tv2.setText(contract.getPhone());
        tv3.setText(contract.getEmail());
        return convertView;
    }

    public void setData(List<Contract> contracts) {
        if (mContractList != null && mContractList.size() > 0) {
            mContractList.clear();
        } else {
            mContractList = new ArrayList<>();
        }
        mContractList.addAll(contracts);
    }
}
