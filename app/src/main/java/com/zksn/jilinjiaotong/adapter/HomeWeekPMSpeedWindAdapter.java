package com.zksn.jilinjiaotong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;

import java.util.ArrayList;

public class HomeWeekPMSpeedWindAdapter extends BaseAdapter {
    private ArrayList<String> mWindSpeedLst;
    private Context mContext;

    public HomeWeekPMSpeedWindAdapter(ArrayList<String> mWindSpeedLst, Context mContext) {

        this.mWindSpeedLst = mWindSpeedLst;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mWindSpeedLst.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();

            convertView = View.inflate(mContext, R.layout.home_item, null);
            holder.mTvHome = (TextView) convertView.findViewById(R.id.tv_home);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTvHome.setText(mWindSpeedLst.get(position));

        return convertView;
    }

    public class ViewHolder {
        public TextView mTvHome;
    }
}