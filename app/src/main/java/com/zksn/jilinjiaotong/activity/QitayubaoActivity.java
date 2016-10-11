package com.zksn.jilinjiaotong.activity;

import android.os.Bundle;
import android.view.View;

import com.zksn.jilinjiaotong.R;

public class QitayubaoActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        View mView = View.inflate(this, R.layout.activity_qitayubao, null);
        setMainView(mView);
        setMiddleTitle(R.string.actitle_title_qitayubao);
    }
}
