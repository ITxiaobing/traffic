package com.zksn.jilinjiaotong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

import com.zksn.jilinjiaotong.R;

/**
 * 登陆页面
 * 
 * @author 袁浩然:
 * @date 创建时间：2016-4-1 下午4:33:52
 * @version 1.0
 * 
 */
public class SplashActivity extends Activity {

	private boolean isClose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		login();// 登陆
	}

	private void login() {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(2000);// 1500
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				if (!isClose) {
					Intent loginIntent = new Intent(SplashActivity.this,
							HomeActity.class);
					startActivity(loginIntent);
					SplashActivity.this.finish();
				}
			}
		}.execute();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			isClose = true;
			SplashActivity.this.finish();
			return false;
		}
		return false;
	}
}
