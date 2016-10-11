package com.zksn.jilinjiaotong.widget;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
import com.zksn.jilinjiaotong.city.event.RefreshCityDone;
import com.zksn.jilinjiaotong.model.ShiKuang;
import com.zksn.jilinjiaotong.net.Neturl;
import com.zksn.jilinjiaotong.utils.SpUtils;
import com.zksn.jilinjiaotong.utils.TimeUtils;

import de.greenrobot.event.EventBus;

public class ShiKuangWeather extends LinearLayout {
    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private View mView;
    private TextView mHumidityWeather;
    private TextView mPressureWeather;
    private TextView mUpdateTime;
    private TextView mTvWarn;
    private TextView mCurrentName;


    public ShiKuangWeather(Context context) {
        super(context);
        initView(context);
    }

    public ShiKuangWeather(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ShiKuangWeather(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.shikuang_weather, this, true);
        mHumidityWeather = (TextView) mView.findViewById(R.id.humidity_weather);
        mPressureWeather = (TextView) mView.findViewById(R.id.pressure_weather);
        mUpdateTime = (TextView) mView.findViewById(R.id.update_time);
        mTvWarn = (TextView) mView.findViewById(R.id.tv_warn);
        mCurrentName = (TextView) mView.findViewById(R.id.yubao_area);
        mTvWarn.setCompoundDrawablePadding(10);
        mUpdateTime.setText(String.format(mContext.getString(R.string.update_time), TimeUtils.getCurrentTime()));
        initRequestData();
    }

    private void initRequestData() {
        String code;
        if (TextUtils.isEmpty(SpUtils.getCurrentCity(mContext))) {
            code = "54161";
        } else {
            code = SpUtils.getCurrentCity(mContext);
        }
        mCurrentName.setText(String.format(mContext.getString(R.string.current_region), SelectCity.getSelectCity(code)));
        HttpUtils httpUtils = new HttpUtils();
        Log.d("tag", Neturl.SHI_KUANG + "?key=" + code);
        httpUtils.send(HttpRequest.HttpMethod.GET, Neturl.SHI_KUANG + "?key=" + code, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                ShiKuang shikuang = JSONObject.parseObject(responseInfo.result, ShiKuang.class);
                resolveData(shikuang);
            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }

    protected void resolveData(ShiKuang shiKuang) {
        if (shiKuang != null) {
            mHumidityWeather.setText(String.format(
                    getResources().getString(R.string.humidity_weather),
                    shiKuang.getShiDu() + "%"));
            mPressureWeather.setText(String.format(mContext.getString(R.string.current_qiwen), shiKuang.getWenDu()));
            ((TextView) mView.findViewById(R.id.tv_current_wendu)).setText(shiKuang.getWenDu());
        }
    }


    public void onEventMainThread(RefreshCityDone event) {

        initRequestData();
    }
}
