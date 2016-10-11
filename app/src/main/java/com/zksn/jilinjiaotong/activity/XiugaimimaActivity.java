package com.zksn.jilinjiaotong.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zksn.jilinjiaotong.R;

public class XiugaimimaActivity extends BaseActivity {
    private Button button_huoqu;
    private Button button_tijiao;
    private EditText et_phone;//�ֻ��
    private EditText et_yanzheng;//��֤��
    private EditText et_xinmima;   //������
    private EditText et_querenmima; //ȷ��������
    private ImageView title_left;//������ͼƬ
    private String phone_num, yanzhengma_num, xinmima_num, querenmima_num;
    private String type;//�ж�����1���޸����룬2���û�ע��
    private int result;
    private int time = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        View mView = View.inflate(this, R.layout.activity_xiugaimima, null);
        setMainView(mView);
        setMiddleTitle(R.string.xiugaimim_updatemim);
        et_phone = (EditText) findViewById(R.id.phone_num);
        et_yanzheng = (EditText) findViewById(R.id.yanzhwngma);
        et_xinmima = (EditText) findViewById(R.id.xinmima);
        et_querenmima = (EditText) findViewById(R.id.querenmima);
        button_huoqu = (Button) findViewById(R.id.huoquyanzhengma);
        button_tijiao = (Button) findViewById(R.id.button_tijiao);
        //��ȡ�ؼ����
        phone_num = et_phone.getText().toString();
        yanzhengma_num = et_yanzheng.getText().toString();
        button_tijiao.setText(R.string.xiugaimim_queding);
        button_huoqu.setOnClickListener(onclickLis);
        button_tijiao.setOnClickListener(onclickLis);
    }

    private View.OnClickListener onclickLis = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.huoquyanzhengma:
                    phone_num = et_phone.getText().toString();
                    if (phone_num == null) {
                        Toast.makeText(XiugaimimaActivity.this, R.string.xiugaimim_qingshuruphone,
                                Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!phone_num.matches("^[1][3578]\\d{9}$")) {
                        Toast.makeText(XiugaimimaActivity.this, R.string.xiugaimim_qingzaicishurumima,
                                Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        handlerText.sendEmptyMessageAtTime(1, 1000);

                    }
                    break;
                case R.id.button_tijiao:
                    xinmima_num = et_xinmima.getText().toString();
                    querenmima_num = et_querenmima.getText().toString();
                    if (xinmima_num == null || xinmima_num.equals("")) {
                        Toast.makeText(XiugaimimaActivity.this, R.string.xiugaimima_mima_no_null,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (querenmima_num == null || xinmima_num.equals("")) {
                        Toast.makeText(XiugaimimaActivity.this, R.string.xiugaimima_erci_mima_no_null,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (xinmima_num.equals(querenmima_num)) {
                        //	setMima();
                    } else {
                        Toast.makeText(XiugaimimaActivity.this, R.string.xiugaimima_mima_no_tong,
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (type.equals("1") || type == "1") {

                    } else {

                    }

                    break;
                default:
                    break;
            }
        }
    };
    Handler handlerText = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (time > 0) {
                    button_huoqu.setText("��֤���ѷ���" + time + "��");
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                    button_huoqu.setEnabled(false);
                } else {
                    button_huoqu.setText("��ȡ��֤��");
                    time = 60;
                    button_huoqu.setEnabled(true);
                }
            } else {
                button_huoqu.setText("��ȡ��֤��");
                time = 60;
                button_huoqu.setEnabled(true);
            }
        }

        ;
    };
}
