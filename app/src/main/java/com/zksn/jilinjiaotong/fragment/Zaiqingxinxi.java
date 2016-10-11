package com.zksn.jilinjiaotong.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.activity.PublicWeatherActivity;

public class Zaiqingxinxi extends Fragment {
    private View view;
    private Button mBtPublicWeather;
    private WebView mWvPic;
    private ProgressBar mPb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.zaiqingxinxi, container, false);
        initView();
        initWebView();
        return view;
    }

    private void initView() {
        mBtPublicWeather = (Button) view.findViewById(R.id.bt_public_weather);
        mWvPic = (WebView) view.findViewById(R.id.pic_wv);
        mPb = (ProgressBar) view.findViewById(R.id.wv_pb);

        mBtPublicWeather.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),
                        PublicWeatherActivity.class));
            }
        });

    }

    private void initWebView() {
        initSettings();
        mWvPic.loadUrl("http://www.weather.com.cn/weather/101060101.shtml");
        mWvPic.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mPb.setVisibility(View.VISIBLE);
                mWvPic.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPb.setVisibility(View.GONE);
                mWvPic.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

        mWvPic.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mPb.setProgress(newProgress);
                if (newProgress == 100) {
                    mPb.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void initSettings() {
        WebSettings settings = mWvPic.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setDomStorageEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
    }
}
