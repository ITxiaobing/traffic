package com.zksn.jilinjiaotong.widget;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.adapter.HomeWeekPMSpeedWindAdapter;
import com.zksn.jilinjiaotong.adapter.HomeWeekPMWeatherAdapter;
import com.zksn.jilinjiaotong.adapter.HomeWeekPMWindAdapter;
import com.zksn.jilinjiaotong.adapter.HomeWeekWeatherAMTemperatureAdapter;
import com.zksn.jilinjiaotong.adapter.HomeWeekWeatherAdapter;
import com.zksn.jilinjiaotong.adapter.HomeWeekWeatherPMTemperatureAdapter;
import com.zksn.jilinjiaotong.adapter.HomeWeekWeatherTimeAdapter;
import com.zksn.jilinjiaotong.city.event.RefreshCityDone;
import com.zksn.jilinjiaotong.city.event.RefreshTwoDone;
import com.zksn.jilinjiaotong.model.Yubao;
import com.zksn.jilinjiaotong.net.Neturl;
import com.zksn.jilinjiaotong.utils.DensityUtil;
import com.zksn.jilinjiaotong.utils.SpUtils;
import com.zksn.jilinjiaotong.widget.picture.TrendView;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class FiveWeather extends RelativeLayout {
    private String TAG = getClass().getSimpleName();
    private Context mContext;
    private View mView;
    private GridView mGridViewWeekWeatherTime;
    private GridView mGridViewWeekWeather;
    private GridView mGridViewWeekWeatherPicture;
    private GridView mGridViewWeekAMWeatherTemperature;
    private GridView mGfWeatherTemperature;
    private GridView mGridViewWeekPMWeatherPicture;
    private GridView mGridViewWeekPMWeather;
    private GridView mGridViewWeekPMWeatherTemperature;
    private GridView mGridViewWeekPMWeatherWind;
    private GridView mGridViewWeekPMWeatherWindSpeed;
    // 7天上午
    private List<String> mWeekDayData = new ArrayList<String>();
    ArrayList<String> mWindWeekWeatherLst = new ArrayList<String>();
    ArrayList<String> mWeekWeatherAMtemperatureLst = new ArrayList<String>();
    ArrayList<String> mWeekWeatherPictureLst = new ArrayList<String>();
    // 7天下午
    ArrayList<String> mWindWeekPMWeatherLst = new ArrayList<String>();
    ArrayList<String> mWeekWeatherPMtemperatureLst = new ArrayList<String>();
    ArrayList<String> mWindWeekPMWindLst = new ArrayList<String>();
    ArrayList<String> mWindSpeedLst = new ArrayList<String>();
    ArrayList<String> mWeekPMWeatherPictureLst = new ArrayList<String>();
    private TrendView mWeatherLine;

    public FiveWeather(Context context) {
        super(context);
        initView(context);
    }

    public FiveWeather(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FiveWeather(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mContext = context;
        View mView = LayoutInflater.from(context).inflate(R.layout.five_day_weather, this, true);
        mGridViewWeekWeatherTime = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_weather_time);
        mGridViewWeekWeather = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_weather);
        mGridViewWeekWeatherPicture = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_weather_picture);
        mGridViewWeekAMWeatherTemperature = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_weather_temperature);
        mGfWeatherTemperature = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_weather_temperature);
        mGridViewWeekPMWeatherPicture = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_weather_pm_picture);
        mGridViewWeekPMWeather = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_pm_weather);
        mGridViewWeekPMWeatherTemperature = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_weather_pm_temperature);
        mGridViewWeekPMWeatherWind = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_weather_pm_wind);
        mGridViewWeekPMWeatherWindSpeed = (GridViewForScrollView) mView
                .findViewById(R.id.gf_week_weather_pm_wind_speed);
        mWeatherLine = (TrendView) mView.findViewById(R.id.weather);
        initRequestData();
    }

    private void initRequestData() {
        String code;
        if (TextUtils.isEmpty(SpUtils.getCurrentCity(mContext))) {
            code = "54161";
        } else {
            code = SpUtils.getCurrentCity(mContext);
        }
        HttpUtils httpUtils = new HttpUtils();
        Log.d(TAG, Neturl.YU_BAO + "?id=" + code);
        httpUtils.send(HttpRequest.HttpMethod.GET, Neturl.YU_BAO + "?id=" + code, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Yubao yuBao = JSONObject.parseObject(responseInfo.result, Yubao.class);
                resolveYBData(yuBao);
            }

            @Override
            public void onFailure(HttpException e, String s) {


            }
        });
    }

    private void resolveYBData(Yubao yuBao) {
        if (yuBao.getYubaos() != null && yuBao.getYubaos().size() > 0) {
            updateData(yuBao);
            EventBus.getDefault().post(new RefreshTwoDone(yuBao));

        }
    }

    private void updateData(Yubao yuBao) {
        mWeekDayData.clear();
        mWindWeekWeatherLst.clear();
        mWeekWeatherAMtemperatureLst.clear();
        mWindWeekPMWeatherLst.clear();
        mWeekWeatherPMtemperatureLst.clear();
        mWindWeekPMWindLst.clear();
        mWindSpeedLst.clear();
        for (int i = 0; i < 5; i++) {
            mWeekDayData.add(yuBao.getYubaos().get(i).getRiqi());
            mWindWeekWeatherLst.add(yuBao.getYubaos().get(i).getTianqi1());
            mWeekWeatherAMtemperatureLst.add(yuBao.getYubaos().get(i).getWendu1());
            //下午
            mWindWeekPMWeatherLst.add(yuBao.getYubaos().get(i).getTianqi2());
            mWeekWeatherPMtemperatureLst.add(yuBao.getYubaos().get(i).getWendu2());
            mWindWeekPMWindLst.add(yuBao.getYubaos().get(i).getFengxiang2());
            mWindSpeedLst.add(yuBao.getYubaos().get(i).getFengli2());
        }

        // 七天上午
        initWeekWeatherTime();

        initWeekWeather();

        // initWeekWeatherPicture();
        initWeekWeatherAMTemperature();

        //    initWeatherData();
        initMultiLine();
        // 七天下午
        // initPMWeatherPicture();
        initWeekPMWeather();

        initWeekPMWeatherTemperature();

        initWeekPMWeatherWind();

        initWeekPMWeatherWindSpeed();
    }

    private void initMultiLine() {
        mWeatherLine.setWidthHeight(DensityUtil.getDeviceInfo(mContext)[0], DensityUtil.getDeviceInfo(mContext)[1]);
        mWeatherLine.setTemperature(mWeekWeatherAMtemperatureLst, mWeekWeatherPMtemperatureLst);
    }

    /**
     * 七天天气时间
     */
    private void initWeekWeatherTime() {
        mGridViewWeekWeatherTime.setAdapter(new HomeWeekWeatherTimeAdapter(
                mWeekDayData, mContext));
    }

    /**
     * 七天上午天气情况
     */
    private void initWeekWeather() {
        mGridViewWeekWeather.setAdapter(new HomeWeekWeatherAdapter(
                mWindWeekWeatherLst, mContext));
    }

    /**
     * 一周上午温度
     */
    private void initWeekWeatherAMTemperature() {
        mGridViewWeekAMWeatherTemperature
                .setAdapter(new HomeWeekWeatherAMTemperatureAdapter(
                        mWeekWeatherAMtemperatureLst, mContext));
    }

    /**
     * 一周下午天气情况
     */
    private void initWeekPMWeather() {

        mGridViewWeekPMWeather.setAdapter(new HomeWeekPMWeatherAdapter(
                mWindWeekPMWeatherLst, mContext));
    }

    /**
     * 一周下午天气温度
     */
    private void initWeekPMWeatherTemperature() {

        mGridViewWeekPMWeatherTemperature
                .setAdapter(new HomeWeekWeatherPMTemperatureAdapter(
                        mWeekWeatherPMtemperatureLst, mContext));
    }

    /**
     * 一周下午风的强度
     */
    private void initWeekPMWeatherWind() {
        mGridViewWeekPMWeatherWind.setAdapter(new HomeWeekPMWindAdapter(
                mWindWeekPMWindLst, mContext));
    }

    /**
     * 风速情况
     */
    private void initWeekPMWeatherWindSpeed() {

        mGridViewWeekPMWeatherWindSpeed
                .setAdapter(new HomeWeekPMSpeedWindAdapter(mWindSpeedLst, mContext));
    }

    public void onEventMainThread(RefreshCityDone event) {
        initRequestData();
    }
}
