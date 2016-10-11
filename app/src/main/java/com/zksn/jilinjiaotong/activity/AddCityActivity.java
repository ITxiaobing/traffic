package com.zksn.jilinjiaotong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.adapter.AddCityAdapter;
import com.zksn.jilinjiaotong.city.SelectCity;
import com.zksn.jilinjiaotong.city.event.RefreshCityDone;
import com.zksn.jilinjiaotong.utils.SpUtils;
import com.zksn.jilinjiaotong.widget.GridViewForScrollView;

import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

public class AddCityActivity extends BaseActivity {


    private GridViewForScrollView mCityGs;
    private AddCityAdapter mCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        List<String> mCityLst = Arrays.asList(getResources().getStringArray(R.array.add_city));
        mCityAdapter = new AddCityAdapter(this, mCityLst);
        mCityGs.setAdapter(mCityAdapter);

    }

    private void initView() {
        View mView = View.inflate(this, R.layout.add_city, null);
        setMainView(mView);
        setMiddleTitle(R.string.actitle_title_add_city);
        ((TextView) findViewById(R.id.current_city)).setText(String.format(getString(R.string.current_city), SelectCity.getSelectCity(SpUtils.getCurrentCity(this))));
        mCityGs = (GridViewForScrollView) mView.findViewById(R.id.city_gs);
        mCityGs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SelectCity.setSelectCity(AddCityActivity.this, position);
                ((TextView) findViewById(R.id.current_city)).setText(String.format(getString(R.string.current_city), SelectCity.getSelectCity(SpUtils.getCurrentCity(AddCityActivity.this))));
                mCityAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(new RefreshCityDone());
            }
        });
    }
}
