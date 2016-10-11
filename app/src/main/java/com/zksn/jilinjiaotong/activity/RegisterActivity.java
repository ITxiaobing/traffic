package com.zksn.jilinjiaotong.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zksn.jilinjiaotong.R;

public class RegisterActivity extends BaseActivity{
	private Button button_huoqu;
	private Button button_register;
	private EditText et_phone;//�ֻ��
	private EditText et_yanzheng;//��֤��
	private EditText et_mima;   //������
	private EditText et_querenmima; //ȷ��������
	private String phone_num,yanzhengma_num,xinmima_num,querenmima_num;
    private int result;
    private int time = 60;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiugaimima);
		initView();
	}


	private void initView() {
		View mView = View.inflate(this, R.layout.activity_xiugaimima, null);
		setMainView(mView);
		setMiddleTitle(R.string.yonghuzhuce_title);
		et_phone=(EditText) findViewById(R.id.phone_num);
		et_yanzheng=(EditText) findViewById(R.id.yanzhwngma);
		et_mima=(EditText) findViewById(R.id.xinmima);
		et_querenmima=(EditText) findViewById(R.id.querenmima);		
		button_huoqu=(Button) findViewById(R.id.huoquyanzhengma);
		button_register=(Button) findViewById(R.id.button_tijiao);
		//��ȡ�ؼ����
		phone_num = et_phone.getText().toString();
		yanzhengma_num=et_yanzheng.getText().toString();
		et_mima.setHint(R.string.xiugaimim_mim);
		et_phone.setHint(R.string.xiugaimim_yonghuming);
		button_register.setText(R.string.xiugaimim_lijizhuce);
		Drawable drawable= getResources().getDrawable(R.drawable.dengluicon_btn);
		/// ��һ������Ҫ��,���򲻻���ʾ.
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		et_phone.setCompoundDrawables(drawable,null,null,null);
		button_huoqu.setOnClickListener(onclickLis);
		button_register.setOnClickListener(onclickLis);
	}
	private View.OnClickListener onclickLis = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.huoquyanzhengma:
            	phone_num=et_phone.getText().toString();
            	if(phone_num==null){
            		Toast.makeText(RegisterActivity.this, R.string.xiugaimim_qingshuruphone,
        					Toast.LENGTH_SHORT).show();
        			return;
            	}else if(!phone_num.matches("^[1][3578]\\d{9}$")){
            		Toast.makeText(RegisterActivity.this, R.string.xiugaimim_qingzaicishurumima,
        					Toast.LENGTH_SHORT).show();
        			return;
            	}else{
            		handlerText.sendEmptyMessageAtTime(1, 1000);
            	}
            	break;
			case R.id.button_tijiao:
				xinmima_num=et_mima.getText().toString();
				querenmima_num=et_querenmima.getText().toString();
				if (xinmima_num == null||xinmima_num.equals("")) {
					Toast.makeText(RegisterActivity.this, R.string.xiugaimima_mima_no_null,
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (querenmima_num == null||xinmima_num.equals("")) {
					Toast.makeText(RegisterActivity.this, R.string.xiugaimima_erci_mima_no_null,
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (xinmima_num.equals(querenmima_num)) {
				//	setMima();
				} else {
					Toast.makeText(RegisterActivity.this, R.string.xiugaimima_mima_no_tong,
							Toast.LENGTH_SHORT).show();
					return;
				}
				break;

			default:
				break;
			}
        }
    };
    Handler handlerText =new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(time>0){
                	button_huoqu.setText("��֤���ѷ���"+time+"��");
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                    button_huoqu.setEnabled(false);
                }else{
                	button_huoqu.setText("��ȡ��֤��");
                    time = 60;
                    button_huoqu.setEnabled(true);
                }
            }else{
            	button_huoqu.setText("��ȡ��֤��");
                time = 60;
                button_huoqu.setEnabled(true);
            }
        };
    };


}
