package com.zksn.jilinjiaotong.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.city.event.RefreshTwoDone;

import de.greenrobot.event.EventBus;


public class TwoDayWeather extends RelativeLayout {
    private String TAG = getClass().getSimpleName();
    private View mView;
    private TextView mTodayWeather;
    private TextView mTodayWendu;
    private TextView mTomorrowWenDu;
    private TextView mTomorrowTianQi;
    private Context mContext;
    public TwoDayWeather(Context context) {
        super(context);
        initView(context);
    }

    public TwoDayWeather(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TwoDayWeather(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext=context;
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mView = LayoutInflater.from(context).inflate(R.layout.yubao_layout_jin_ming, this, true);
        mTodayWeather = (TextView) mView.findViewById(R.id.today_weather);
        mTodayWendu = (TextView) mView.findViewById(R.id.yubao_jintian_txt_duoyunzhuanqing);
        mTomorrowWenDu = (TextView) mView.findViewById(R.id.tomorrow_wendu);
        mTomorrowTianQi = (TextView) mView.findViewById(R.id.yubao_jintian_tianqi);
    }

    public void onEventMainThread(RefreshTwoDone event) {
        Log.d(TAG, "RefreshTwoDone"+event.mYubBao.getYubaos().get(0).getTianqi1());
        mTodayWeather.setText(event.mYubBao.getYubaos().get(0).getTianqi1());
        mTodayWendu.setText(String.format(mContext.getString(R.string.qiwen_weather),event.mYubBao.getYubaos().get(0).getWendu1()));
        mTomorrowWenDu.setText(String.format(mContext.getString(R.string.qiwen_weather),event.mYubBao.getYubaos().get(1).getWendu1()));
        mTomorrowTianQi.setText(event.mYubBao.getYubaos().get(1).getTianqi1());
    }
}
