package com.zksn.jilinjiaotong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;

import java.util.List;


public class HomeWeekWeatherTimeAdapter extends BaseAdapter {

	private List<String> mWindWeekWeatherTimeLst;
	private Context mContext;

	public HomeWeekWeatherTimeAdapter(List<String> mWindWeekWeatherTimeLst, Context context) {
		this.mWindWeekWeatherTimeLst = mWindWeekWeatherTimeLst;
		this.mContext=context;

	}

	@Override
	public int getCount() {
		return mWindWeekWeatherTimeLst.size();
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

		holder.mTvHome.setText(mWindWeekWeatherTimeLst.get(position));
		return convertView;
	}

	public class ViewHolder {
		public TextView mTvHome;
	}
}