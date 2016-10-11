package com.zksn.jilinjiaotong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.activity.DuanqiActivity;
import com.zksn.jilinjiaotong.activity.DuanshiwenziyubaoActivity;
import com.zksn.jilinjiaotong.activity.JingxihuaActivity;
import com.zksn.jilinjiaotong.activity.JucefuwuActivity;
import com.zksn.jilinjiaotong.activity.NongqiyubaoActivity;
import com.zksn.jilinjiaotong.activity.QitayubaoActivity;
import com.zksn.jilinjiaotong.activity.XunyubaoActivity;

public class Juececailiao extends Fragment implements OnClickListener {
	private View mView;
	private RelativeLayout duanshiwenzi;
	private TextView  juece, duanqiwenzi, jingxihua, xuyubao,
			nongqi, qita;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.juececailiao, null);
		initView();
		return mView;
	}

	private void initView() {
		duanshiwenzi = (RelativeLayout) mView.findViewById(R.id.ll_short_time_text);
		juece = (TextView) mView.findViewById(R.id.juce_txt);
		duanqiwenzi = (TextView) mView.findViewById(R.id.duanqi_txt);
		jingxihua = (TextView) mView.findViewById(R.id.jingxihua_txt);
		xuyubao = (TextView) mView.findViewById(R.id.xunyubao_txt);
		nongqi = (TextView) mView.findViewById(R.id.nongqiyubao_txt);
		qita = (TextView) mView.findViewById(R.id.qita_txt);
		duanshiwenzi.setOnClickListener(this);
		juece.setOnClickListener(this);
		duanqiwenzi.setOnClickListener(this);
		jingxihua.setOnClickListener(this);
		xuyubao.setOnClickListener(this);
		nongqi.setOnClickListener(this);
		qita.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent();
		switch (arg0.getId()) {
		case R.id.ll_short_time_text:
			intent.setClass(getActivity(), DuanshiwenziyubaoActivity.class);
			startActivity(intent);
			break;
		case R.id.juce_txt:
			intent.setClass(getActivity(), JucefuwuActivity.class);
			startActivity(intent);
			break;
		case R.id.duanqi_txt:
			intent.setClass(getActivity(), DuanqiActivity.class);
			startActivity(intent);
			break;
		case R.id.jingxihua_txt:
			intent.setClass(getActivity(), JingxihuaActivity.class);
			startActivity(intent);
			break;
		case R.id.xunyubao_txt:
			intent.setClass(getActivity(), XunyubaoActivity.class);
			startActivity(intent);
			break;
		case R.id.nongqiyubao_txt:
			intent.setClass(getActivity(), NongqiyubaoActivity.class);
			startActivity(intent);
			break;
		case R.id.qita_txt:
			intent.setClass(getActivity(), QitayubaoActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (mView != null) {
			ViewGroup parent = (ViewGroup) mView.getParent();
			parent.removeView(mView);
		}
	}
}
