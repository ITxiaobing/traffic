package com.zksn.jilinjiaotong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import java.util.ArrayList;

public class HomeWeekPMWeatherPictureAdapter extends BaseAdapter {
	private ArrayList<String> mWeekPMWeatherPictureLst;
	private Context mContext;

	public HomeWeekPMWeatherPictureAdapter(ArrayList<String> mWeekPMWeatherPictureLst, Context context) {
		this.mWeekPMWeatherPictureLst = mWeekPMWeatherPictureLst;
		this.mContext = context;

	}

	@Override
	public int getCount() {
		return mWeekPMWeatherPictureLst.size();
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
//		ViewHolder holder = null;
//		if (convertView == null) {
//			holder = new ViewHolder();
//
//			convertView = View.inflate(JDBApplication.getContext(), R.layout.home_weather_picture_item, null);
//			holder.mTvHome = (ImageView) convertView.findViewById(R.id.tv_home);
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//		holder.mTvHome.setImageResource(ImageUtil.getImg(mWeekPMWeatherPictureLst.get(position)));

		return convertView;
	}

	public class ViewHolder {
		public ImageView mTvHome;
	}
}
