package com.zksn.jilinjiaotong.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;


public class DuanshiwenziyubaoActivity extends BaseActivity {


    private SwipeRefreshLayout refreshLayout;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            for (int i = 0; i < 20; i++) {
                TextView textView = new TextView(DuanshiwenziyubaoActivity.this);
                textView.setText("今天天气是" + i + "℃");
                textView.setPadding(10,10,10,10);
                mContainer.addView(textView);
            }
            refreshLayout.setRefreshing(false);
        }
    };
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getData();
    }

    private void initView() {
        View mView = View.inflate(this, R.layout.activity_duanshiwenziyubao, null);
        setMainView(mView);
        setMiddleTitle(R.string.actitle_title_duanshiwenziyubao);
        refreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipe_refresh);
        mContainer = (LinearLayout) mView.findViewById(R.id.container);
        //设置颜色
        setSwipeRefresh();
        refreshLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_blue_bright, android.R.color.holo_blue_bright, android.R.color.black);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }


    private void setSwipeRefresh() {
        TypedValue typed_value = new TypedValue();
        this.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, typed_value, true);
        refreshLayout.setProgressViewOffset(false, 0, getResources().getDimensionPixelSize(typed_value.resourceId));
        refreshLayout.setRefreshing(true);
    }


}
