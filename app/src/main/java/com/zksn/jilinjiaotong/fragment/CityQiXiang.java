package com.zksn.jilinjiaotong.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.adapter.CityFragmentAdapter;

import java.util.ArrayList;

/**
 * 城市气象
 *
 * @author wlc
 */
public class CityQiXiang extends Fragment {
    private View view;
    private RadioGroup group;
    private DangQianqixiang dangqian;
    private WeiXingleida wxld;
    // -------定位------------
    private ArrayList<android.support.v4.app.Fragment> mFragmentLst;
    private ViewPager mContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.city_qixiang, null);

        initView();
        initData();

        return view;
    }

    private void initData() {
        mFragmentLst = new ArrayList<>();
        dangqian = new DangQianqixiang();
        wxld = new WeiXingleida();
        mFragmentLst.add(dangqian);
        mFragmentLst.add(wxld);
        mContainer.setAdapter(new CityFragmentAdapter(getChildFragmentManager(), mFragmentLst));
    }


    @SuppressLint("NewApi")
    private void initView() {
        group = (RadioGroup) view.findViewById(R.id.ggg);
        mContainer = (ViewPager) view.findViewById(R.id.container);
        group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                switch (arg1) {
                    case R.id.dangqianshikuang:
                        mContainer.setCurrentItem(0);
                        break;
                    case R.id.weixingleida:
                        mContainer.setCurrentItem(1);
                        break;
                }
            }
        });
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
