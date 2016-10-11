package com.zksn.jilinjiaotong.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.adapter.MainTabAdapter;
import com.zksn.jilinjiaotong.fragment.CityQiXiang;
import com.zksn.jilinjiaotong.fragment.JiaoTongQiXiang;
import com.zksn.jilinjiaotong.fragment.Juececailiao;
import com.zksn.jilinjiaotong.fragment.MeFragment;
import com.zksn.jilinjiaotong.fragment.Zaiqingxinxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActity extends FragmentActivity {
    private RadioGroup rgs;
    public List<Fragment> fragments = new ArrayList<Fragment>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_tab);
        setTitle(R.string.chengshiqixiang_title);
        fragments.add(new CityQiXiang());
        fragments.add(new JiaoTongQiXiang());
        fragments.add(new Zaiqingxinxi());
        fragments.add(new Juececailiao());
        fragments.add(new MeFragment());
        rgs = (RadioGroup) findViewById(R.id.main_tab_group);
        MainTabAdapter tabAdapter = new MainTabAdapter(this, fragments,
                R.id.tab_content, rgs);
        tabAdapter
                .setOnRgsExtraCheckedChangedListener(new MainTabAdapter.OnRgsExtraCheckedChangedListener() {
                    @Override
                    public void OnRgsExtraCheckedChanged(RadioGroup radioGroup,
                                                         int checkedId, int index) {
                        switch (checkedId) {
                            case R.id.chengshiqixiang:
                                setTitle(R.string.chengshiqixiang_title);
                                break;
                            case R.id.main_tab_yujing:
                                setTitle(R.string.jiaotongqixiang_title);
                                break;
                            case R.id.main_tab_shuzhi:
                                setTitle(R.string.zaiqing_title);
                                break;
                            case R.id.main_tab_tianqi:
                                setTitle(R.string.juececailiao_title);
                                break;
                            case R.id.main_tab_wo:
                                setTitle(R.string.wo_title);
                                break;
                        }

                    }
                });
    }

    public void setTitle(int resId) {
        ((TextView)findViewById(R.id.title)).setText(resId);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); // 调用双击退出函数
        }
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, getString(R.string.press_exit), Toast.LENGTH_SHORT).show();
            tExit = new Timer();

            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

}

