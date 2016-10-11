package com.zksn.jilinjiaotong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;
/**
 * ũ��Ԥ��
 * @author wlc
 *
 */
public class NongqiyubaoActivity extends BaseActivity {
	private ImageView finish;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		View mView = View.inflate(this, R.layout.activity_nongqiyubao, null);
		setMainView(mView);
		setMiddleTitle(R.string.actitle_title_nongqiyubao);
	}
}
