package com.zksn.jilinjiaotong.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;

public class NavigationBar extends RelativeLayout {
    private Context mContext;
    private View mView;

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public NavigationBar(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        mView = View.inflate(context, R.layout.navigation_item, null);
        mView.setBackgroundColor(getResources().getColor(R.color.black));
        iniListener();
    }

    private void iniListener() {
        mView.findViewById(R.id.navigation_back).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        ((Activity) mContext).finish();
                    }
                });
    }

    public void setTitle(int ResId) {
        ((TextView) mView.findViewById(R.id.title)).setText(ResId);
    }

}
