package com.zksn.jilinjiaotong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;
/**
 * ��¼ע�����
 * @author wlc
 *
 */
public class LoginActivity extends Activity implements OnClickListener{
	private EditText yonghu_name;
	private EditText yonghu_mima;
	private String yonghuming;
	private String mima;
	private ImageView yonghuming_pic;
	private ImageView mima_pic;
	private Button button_zhuce;
	private Button button_denglu;
	private TextView tv_wangjimima;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		yonghu_name=(EditText) findViewById(R.id.yonghuming);
		yonghu_mima=(EditText) findViewById(R.id.mima);
		
		button_zhuce=(Button) findViewById(R.id.zhuce);
		button_denglu=(Button) findViewById(R.id.denglu);
		tv_wangjimima = (TextView) findViewById(R.id.textView3);
		
		yonghuming=yonghu_name.getText().toString();
		mima=yonghu_mima.getText().toString();
		button_zhuce.setOnClickListener(this);
		button_denglu.setOnClickListener(this);
		tv_wangjimima.setOnClickListener(this);;
	}

	@Override
	public void onClick(View arg0) {
		Intent intent=new Intent();
		switch (arg0.getId()) {
		case R.id.zhuce:
			intent.setClass(this, RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.denglu:
			intent.setClass(this, HomeActity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.textView3:
			intent.setClass(this, XiugaimimaActivity.class);
			startActivity(intent);
		default:
			break;
		}
	}


}
