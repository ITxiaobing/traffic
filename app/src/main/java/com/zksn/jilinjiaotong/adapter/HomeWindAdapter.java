package com.zksn.jilinjiaotong.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeWindAdapter extends BaseAdapter {
	private ArrayList<String> mWindLst;

	public HomeWindAdapter(ArrayList<String> mWindLst) {
		this.mWindLst = mWindLst;
	}

	@Override
	public int getCount() {
		return mWindLst.size();
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
//		if (convertView == null) {
//			holder = new ViewHolder();
//
//			convertView = View.inflate(JDBApplication.getContext(), R.layout.home_wind_speed_item, null);
//			holder.mTvHome = (TextView) convertView.findViewById(R.id.tv_home);
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}

		holder.mTvHome.setText(mWindLst.get(position));

		return convertView;
	}

	public class ViewHolder {
		public TextView mTvHome;
	}

}
