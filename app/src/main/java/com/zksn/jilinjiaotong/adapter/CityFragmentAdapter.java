package com.zksn.jilinjiaotong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


public class CityFragmentAdapter extends FragmentStatePagerAdapter {
    public ArrayList<Fragment> mItemInfos;


    public CityFragmentAdapter(FragmentManager fm, ArrayList<Fragment> mItemInfos) {
        super(fm);
        this.mItemInfos = mItemInfos;
    }


    @Override
    public Fragment getItem(int position) {

        return mItemInfos.get(position);
    }

    @Override
    public int getCount() {
        return mItemInfos.size();
    }

}