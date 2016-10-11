package com.zksn.jilinjiaotong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.city.SelectCity;
import com.zksn.jilinjiaotong.utils.SpUtils;

import java.util.List;


public class AddCityAdapter extends BaseAdapter {
    private List<String> mCityLst;
    private Context mContext;

    public AddCityAdapter(Context mContext, List<String> mCityLst) {
        this.mCityLst = mCityLst;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mCityLst.size();
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
            convertView = View.inflate(mContext, R.layout.add_city_item, null);
            holder.mTvCity = (TextView) convertView.findViewById(R.id.city_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTvCity.setText(mCityLst.get(position));
        if (SelectCity.getSelectCity(SpUtils.getCurrentCity(mContext)).equals(mCityLst.get(position))) {
            holder.mTvCity.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            holder.mTvCity.setTextColor(mContext.getResources().getColor(R.color.black));
        }
        return convertView;
    }

    public class ViewHolder {
        public TextView mTvCity;
    }
}
