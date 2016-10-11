package com.zksn.jilinjiaotong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.model.ZaiqingModel;

import java.util.ArrayList;
import java.util.List;

public class ZaiqingAdpater extends BaseAdapter{
	private LayoutInflater mInflater;
	private List<ZaiqingModel> datas;
	private int type;
	public ZaiqingAdpater(Context context ,List<ZaiqingModel> datas,int type){
		this.mInflater=LayoutInflater.from(context);
		if(datas==null){
			this.datas=new ArrayList<ZaiqingModel>();
		}else{
			this.datas = datas;
		}
		this.type=type;
	}
	public void AddData(List<ZaiqingModel> list,boolean isClear){
		if(isClear){
			this.datas.clear();
		}
		this.datas.addAll(list);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return datas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHouder viewHouder;
		if(arg1==null){
			arg1=mInflater.inflate(R.layout.item_zaiqing, null);
			viewHouder =new ViewHouder(arg1);
			arg1.setTag(viewHouder);
		}else{
			viewHouder=(ViewHouder) arg1.getTag();
		}
		ZaiqingModel a=datas.get(arg0);
		if(type==1){
			viewHouder.relativeLayout1.setVisibility(View.VISIBLE);
			viewHouder.relativeLayout2.setVisibility(View.GONE);
			viewHouder.icon1.setImageDrawable(a.icon);
			viewHouder.tqzhuangkuang1.setText(a.tianqizhuangkuang);
			viewHouder.time1.setText(a.time);
			viewHouder.laiyuan1.setText(a.laiyuan);
		}else{
			viewHouder.relativeLayout2.setVisibility(View.VISIBLE);
			viewHouder.relativeLayout1.setVisibility(View.GONE);
			viewHouder.icon2.setImageDrawable(a.icon);
			viewHouder.tqzhuangkuang2.setText(a.tianqizhuangkuang);
			viewHouder.time2.setText(a.time);
			viewHouder.laiyuan2.setText(a.laiyuan);
		}		
		return arg1;
	}
	
	class ViewHouder{
		RelativeLayout relativeLayout1;
		ImageView icon1;
		TextView  tqzhuangkuang1;
		TextView  time1;
		TextView  laiyuan1;
		
		RelativeLayout relativeLayout2;
		ImageView icon2;
		TextView  tqzhuangkuang2;
		TextView  time2;
		TextView  laiyuan2;
		public ViewHouder(View view){
			relativeLayout1 = (RelativeLayout) view.findViewById(R.id.zaiqing_layout1);
			icon1=(ImageView) view.findViewById(R.id.zaiqing_pic);
			tqzhuangkuang1=(TextView) view.findViewById(R.id.zaiqing_zhuangkuang);
			time1=(TextView) view.findViewById(R.id.zaiqing_time);
			laiyuan1=(TextView) view.findViewById(R.id.zaiqing_laiyuan);
			
			relativeLayout2 = (RelativeLayout) view.findViewById(R.id.zaiqing_layout2);
			icon2=(ImageView) view.findViewById(R.id.zaiqing_pic2);
			tqzhuangkuang2=(TextView) view.findViewById(R.id.zaiqing_zhuangkuang2);
			time2=(TextView) view.findViewById(R.id.zaiqing_time2);
			laiyuan2=(TextView) view.findViewById(R.id.zaiqing_laiyuan2);
		}
	}

}
