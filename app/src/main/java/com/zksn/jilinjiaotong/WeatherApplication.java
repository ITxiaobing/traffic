package com.zksn.jilinjiaotong;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.baidu.mapapi.SDKInitializer;

import java.io.File;

public class WeatherApplication extends Application {
	public static String TAG = "MyApplication";
	private static WeatherApplication mInstance = null;
	public static final String appName = "notify";

	private static File appCachePicDir;
	private static File appCacheVoiceDir;
	private static File appCacheVedioDir;
	private static Context iContext;

	@Override
	public void onCreate() {
		SDKInitializer.initialize(getApplicationContext());
		initAppDir();
		iContext = this;
		mInstance = this;
		super.onCreate();
	}

	public static WeatherApplication getInstance() {

		return mInstance;
	}

	public static Context getContext() {
		return iContext;

	}


	private void initAppDir() {

		appCachePicDir = new File(Environment.getExternalStorageDirectory()
				+ "/" + appName + "/pic");
		appCacheVoiceDir = new File(Environment.getExternalStorageDirectory()
				+ "/" + appName + "/voice");
		appCacheVedioDir = new File(Environment.getExternalStorageDirectory()
				+ "/" + appName + "/vedio");
		if (!appCachePicDir.exists()) {
			try {
				appCachePicDir.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!appCacheVoiceDir.exists()) {
			try {
				appCacheVoiceDir.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!appCacheVedioDir.exists()) {
			try {
				appCacheVedioDir.mkdirs();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}