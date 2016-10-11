package com.zksn.jilinjiaotong.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.activity.AddCityActivity;
import com.zksn.jilinjiaotong.activity.XiugaimimaActivity;
import com.zksn.jilinjiaotong.city.SelectCity;
import com.zksn.jilinjiaotong.city.event.RefreshCityDone;
import com.zksn.jilinjiaotong.utils.SpUtils;

import net.colindodd.toggleimagebutton.ToggleImageButton;

import de.greenrobot.event.EventBus;

public class MeFragment extends Fragment implements OnClickListener, OnCheckedChangeListener {
    public static String LOCATION_BCR = "location_bcr";
    private View view;
    private TextView title;
    private ImageView touxiang;
    private TextView mima_update;
    private ToggleButton zidongqidong;
    private ToggleButton jianting;
    private ToggleButton tishiyin;
    private ToggleButton zhendong;
    private TextView City;
    private TextView jianchagengxin;
    private Vibrator vibrator;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wozhu, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        touxiang = (ImageView) view.findViewById(R.id.touxiang);
        mima_update = (TextView) view.findViewById(R.id.mima_update);
        ((ToggleImageButton) view.findViewById(R.id.togbt_zidongqidong)).setChecked(true);
        City = (TextView) view.findViewById(R.id.city);
        touxiang.setImageResource(R.drawable.wo_icon);
        jianchagengxin = (TextView) view.findViewById(R.id.jainchagengxin);
        zidongqidong = (ToggleButton) view.findViewById(R.id.togbt_zidongqidong);
        jianting = (ToggleButton) view.findViewById(R.id.togbt_jianting);
        tishiyin = (ToggleButton) view.findViewById(R.id.togbt_tishiyin);
        zhendong = (ToggleButton) view.findViewById(R.id.togbt_zhendong);
        ((TextView) view.findViewById(R.id.current_city)).setText(SelectCity.getSelectCity(SpUtils.getCurrentCity(mContext)));
        ((TextView) view.findViewById(R.id.auto_turn_on)).setText(SelectCity.getSelectCity(SpUtils.getCurrentCity(mContext)));
        mima_update.setOnClickListener(this);
        City.setOnClickListener(this);
        jianchagengxin.setOnClickListener(this);
        zidongqidong.setOnCheckedChangeListener(this);
        jianting.setOnCheckedChangeListener(this);
        tishiyin.setOnCheckedChangeListener(this);
        zhendong.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View arg0) {
        Intent intent = new Intent();
        switch (arg0.getId()) {
            case R.id.mima_update:
                intent.setClass(getActivity(), XiugaimimaActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.city:
                intent.setClass(getActivity(), AddCityActivity.class);
                startActivity(intent);
                break;
            case R.id.jainchagengxin:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setIcon(R.drawable.app_icon);
                builder2.setTitle(R.string.wo_banben);
                builder2.setMessage(R.string.wo_banben_content);
                builder2.setNegativeButton(R.string.wo_queding, null);
                builder2.create();
                builder2.show();
                break;

            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        switch (v.getId()) {
            case R.id.togbt_zidongqidong:

                break;
            case R.id.togbt_jianting:
                if (isChecked) {
                } else {
                }
                break;
            case R.id.togbt_tishiyin:

                break;
            case R.id.togbt_zhendong:
                if (isChecked) {
                    vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    //long [] pattern = {100,400,100,400};   // ֹͣ ���� ֹͣ ����
                    vibrator.vibrate(500);
                } else {

                }

                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
    }

    public void onEventMainThread(RefreshCityDone event) {
        ((TextView) view.findViewById(R.id.current_city)).setText(SelectCity.getSelectCity(SpUtils.getCurrentCity(mContext)));
        ((TextView) view.findViewById(R.id.auto_turn_on)).setText(SelectCity.getSelectCity(SpUtils.getCurrentCity(mContext)));
    }
}
