package com.zksn.jilinjiaotong.city;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.model.UpdateDataModel;


public class UpdateView {
    private View mView;

    public View getView() {
        return mView;
    }

    public UpdateView(Context context, UpdateDataModel.YubaosBean alst) {

        mView = View.inflate(context, R.layout.update_data_item, null);
        TextView mTime = (TextView) mView.findViewById(R.id.time);
        TextView mWenDu = (TextView) mView.findViewById(R.id.wendu);
        TextView mShiDu = (TextView) mView.findViewById(R.id.shidu);
        TextView mJiangShui = (TextView) mView.findViewById(R.id.jiangshui);
        TextView mFengXiang = (TextView) mView.findViewById(R.id.fengxiang);
        TextView mFengli = (TextView) mView.findViewById(R.id.fengli);
        mTime.setText(alst.getRiQi());
        mWenDu.setText(alst.getWenDu());
        mShiDu.setText(alst.getShiDu());
        mJiangShui.setText(alst.getJiangShui());
        mFengXiang.setText(alst.getFengXiang());
        mFengli.setText(alst.getFengSu());
    }
}
