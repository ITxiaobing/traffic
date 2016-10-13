package com.zksn.jilinjiaotong.net;


import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zksn.jilinjiaotong.net.utils.NetUtils;
import com.zksn.jilinjiaotong.utils.ToastUtils;

public class WeatherNetRequest {
    private Context mContext;

    public WeatherNetRequest(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * get请求
     *
     * @param pathUrl
     * @param listener
     */
    public void getRequestData(String pathUrl, final RequestResultListener listener, final Class clazz) {
        if (!NetUtils.getNetState(mContext)) {
            ToastUtils.setShowTop(mContext,"当前网络不可用");
        } else {
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.GET,
                    pathUrl,
                    new RequestCallBack<String>() {
                        @Override
                        public void onLoading(long total, long current, boolean isUploading) {
                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            Object object = JSON.parseObject(responseInfo.result, clazz);
                            listener.requestSuccess(object);
                        }

                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {
                            listener.requestFail();
                        }
                    });

        }

    }

    /**
     * post请求
     *
     * @param pathUrl
     * @param params
     * @param listener
     */
    public void postRequestData(String pathUrl, RequestParams params, final RequestResultListener listener, final Class clazz) {
        if (!NetUtils.getNetState(mContext)) {
            ToastUtils.setShowTop(mContext,"当前网络不可用");
        } else {
            HttpUtils http = new HttpUtils();
            http.send(HttpRequest.HttpMethod.POST,
                    pathUrl, params,
                    new RequestCallBack<String>() {
                        @Override
                        public void onLoading(long total, long current, boolean isUploading) {
                        }

                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            Object object = JSON.parseObject(responseInfo.result, clazz);
                            listener.requestSuccess(object);
                        }

                        @Override
                        public void onFailure(HttpException error, String msg) {
                            listener.requestFail();
                        }
                    });

        }

    }


    public interface RequestResultListener {

        void requestFail();

        void requestSuccess(Object result);
    }
}
