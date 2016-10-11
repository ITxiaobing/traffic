package com.zksn.jilinjiaotong.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.city.SelectCity;
import com.zksn.jilinjiaotong.city.UpdateView;
import com.zksn.jilinjiaotong.city.event.RefreshCityDone;
import com.zksn.jilinjiaotong.model.UpdateDataModel;
import com.zksn.jilinjiaotong.net.Neturl;
import com.zksn.jilinjiaotong.utils.SpUtils;
import com.zksn.jilinjiaotong.utils.TimeUtils;

import de.greenrobot.event.EventBus;

public class DangQianqixiang extends android.support.v4.app.Fragment {
    private String TAG = getClass().getSimpleName();
    private View mView;
    private Context mContext;
    private LinearLayout mContainer;
    private TextView mRealTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dangqianzhu, null);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        InitHomePagerData();

    }

    private void InitHomePagerData() {
        String code;
        if (TextUtils.isEmpty(SpUtils.getCurrentCity(mContext))) {
            code = "54161";
        } else {
            code = SpUtils.getCurrentCity(mContext);
        }
        mRealTime.setText(SelectCity.getSelectCity(code));
        getUpdateData(code);
    }

    private void getUpdateData(String code) {
        // String id = "54161";
        HttpUtils httpUtils = new HttpUtils();
        Log.d("tag", Neturl.UPDATE_DATA + "?zhanhao=" + code);
        httpUtils.send(HttpRequest.HttpMethod.GET, Neturl.UPDATE_DATA + "?zhanhao=" + code, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                UpdateDataModel update = JSONObject.parseObject(responseInfo.result, UpdateDataModel.class);
                resolveUpdateData(update);
            }

            @Override
            public void onFailure(HttpException e, String s) {


            }
        });
    }

    private void resolveUpdateData(UpdateDataModel update) {
        if (update.getYubaos() != null) {
            mContainer.removeAllViews();
            for (int i = 0; i < update.getYubaos().size(); i++) {
                mContainer.addView(new UpdateView(mContext, update.getYubaos().get(i)).getView(), new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
            }
        }
    }


    @SuppressWarnings("deprecation")
    private void initView() {
        mContainer = (LinearLayout) mView.findViewById(R.id.ll_container);
        mRealTime = (TextView) mView.findViewById(R.id.real_time_city);
        ((TextView) mView.findViewById(R.id.zhishu_update_time)).setText(String.format(getString(R.string.zhishu_update_time), TimeUtils.getCurrentTime()));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            parent.removeView(mView);
        }
    }

    public void onEventMainThread(RefreshCityDone event) {
        Log.d(TAG, "RefreshCityDone");
        InitHomePagerData();
    }
}
