package com.zksn.jilinjiaotong.fragment;

import android.content.ContentResolver;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.model.WeixingLeidaModel;

import java.util.Timer;
import java.util.TimerTask;

public class WeiXingleida extends Fragment {
	private View mView;
	private RadioButton weixing;
	private RadioButton leida;
	private AnimationDrawable anim;
	private ImageView pic;
	private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
	private Button open;
	boolean bol = true;
	boolean bool = false;
	private WeixingLeidaModel js = new WeixingLeidaModel();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.weixingleida, null);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		//getData();
		init();
		if (js!=null) {

		}
		donghua();
		changeTextColor(1);
	}

	private void init() {
		weixing = (RadioButton) mView.findViewById(R.id.weixing);
		leida = (RadioButton) mView.findViewById(R.id.leida);
		weixing.setOnClickListener(listener);
		leida.setOnClickListener(listener);
		pic = (ImageView) mView.findViewById(R.id.pic);
		open = (Button) mView.findViewById(R.id.open);
		tv1 = (TextView) mView.findViewById(R.id.textView1);
		tv2 = (TextView) mView.findViewById(R.id.textView2);
		tv3 = (TextView) mView.findViewById(R.id.textView3);
		tv4 = (TextView) mView.findViewById(R.id.textView4);
		tv5 = (TextView) mView.findViewById(R.id.textView5);
		tv6 = (TextView) mView.findViewById(R.id.textView6);
		tv7 = (TextView) mView.findViewById(R.id.textView7);
		tv8 = (TextView) mView.findViewById(R.id.textView8);
		open.setBackgroundResource(R.drawable.zanting);
		open.setOnClickListener(listener);
	}

	private View.OnClickListener listener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.weixing:
					open.setBackgroundResource(R.drawable.zanting);
					bol = true;
					donghua();
					changeTextColor(1);
					anim.stop();
					time=1;
					click();
					break;
				case R.id.leida:
					open.setBackgroundResource(R.drawable.zanting);
					bol = true;
					donghua2();
					changeTextColor(1);
					anim.stop();
					time=1;
					click();
					break;
				case R.id.open:
					if (bol) {
						// ����
						bool = true;
						open.setBackgroundResource(R.drawable.bofang);
						bol = false;
						anim.start();
						click();

					} else {
						// ֹͣ
						open.setBackgroundResource(R.drawable.zanting);
						bol = true;
						anim.stop();
						click();
					}
					break;
				default:
					break;
			}
		}

	};

	private void donghua() {
		anim = new AnimationDrawable();
		ContentResolver cr = getActivity().getContentResolver();
		for (int i = 1; i <= 8; i++) {
//			Uri uu = Uri.parse(js.getWeiXing().get(i).getImgUrl());
//			Bitmap bitmap =null;;
//			try {
//				bitmap = BitmapFactory.decodeStream(cr
//						.openInputStream( uu));
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//			BitmapDrawable drawable = new BitmapDrawable(bitmap);
			int id = getResources().getIdentifier("g" + i, "drawable",
					getActivity().getPackageName());
			Drawable drawable = getResources().getDrawable(id);

			anim.addFrame(drawable, 1000);
		}
		// �����ַ��ظ����ţ�falseΪ�ظ�
		anim.setOneShot(false);
		pic.setImageDrawable(anim);
		anim.stop();
	}

	private void donghua2() {
		anim = new AnimationDrawable();
		// ���ÿһ֡����
		for (int i = 1; i <= 8; i++) {
			int id = getResources().getIdentifier("f" + i, "drawable",
					getActivity().getPackageName());
			Drawable drawable = getResources().getDrawable(id);
			anim.addFrame(drawable, 1000);
		}
		// �����ַ��ظ����ţ�falseΪ�ظ�
		anim.setOneShot(false);
		pic.setImageDrawable(anim);
		anim.stop();
	}

	static int time = 1;
	private Timer timer;

	public void click() {
		if (bol) {
			if(bool){
				timer.cancel();
			}
		} else {
			timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					handler.sendEmptyMessage(1);
				}
			}, 0, 1000);
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (time < 8) {
					time++;
				} else {
					time = 1;
				}
				changeTextColor(time);

			}
		};
	};
	//����֡����ʱʱ��������ɫ�仯����
	private void changeTextColor(int i) {
		switch (i) {
			case 1:
				tv1.setTextColor(Color.RED);
				tv2.setTextColor(Color.WHITE);
				tv3.setTextColor(Color.WHITE);
				tv4.setTextColor(Color.WHITE);
				tv5.setTextColor(Color.WHITE);
				tv6.setTextColor(Color.WHITE);
				tv7.setTextColor(Color.WHITE);
				tv8.setTextColor(Color.WHITE);
				break;
			case 2:
				tv1.setTextColor(Color.WHITE);
				tv2.setTextColor(Color.RED);
				tv3.setTextColor(Color.WHITE);
				tv4.setTextColor(Color.WHITE);
				tv5.setTextColor(Color.WHITE);
				tv6.setTextColor(Color.WHITE);
				tv7.setTextColor(Color.WHITE);
				tv8.setTextColor(Color.WHITE);
				break;
			case 3:
				tv1.setTextColor(Color.WHITE);
				tv2.setTextColor(Color.WHITE);
				tv3.setTextColor(Color.RED);
				tv4.setTextColor(Color.WHITE);
				tv5.setTextColor(Color.WHITE);
				tv6.setTextColor(Color.WHITE);
				tv7.setTextColor(Color.WHITE);
				tv8.setTextColor(Color.WHITE);
				break;
			case 4:
				tv1.setTextColor(Color.WHITE);
				tv2.setTextColor(Color.WHITE);
				tv3.setTextColor(Color.WHITE);
				tv4.setTextColor(Color.RED);
				tv5.setTextColor(Color.WHITE);
				tv6.setTextColor(Color.WHITE);
				tv7.setTextColor(Color.WHITE);
				tv8.setTextColor(Color.WHITE);
				break;
			case 5:
				tv1.setTextColor(Color.WHITE);
				tv2.setTextColor(Color.WHITE);
				tv3.setTextColor(Color.WHITE);
				tv4.setTextColor(Color.WHITE);
				tv5.setTextColor(Color.RED);
				tv6.setTextColor(Color.WHITE);
				tv7.setTextColor(Color.WHITE);
				tv8.setTextColor(Color.WHITE);
				break;
			case 6:
				tv1.setTextColor(Color.WHITE);
				tv2.setTextColor(Color.WHITE);
				tv3.setTextColor(Color.WHITE);
				tv4.setTextColor(Color.WHITE);
				tv5.setTextColor(Color.WHITE);
				tv6.setTextColor(Color.RED);
				tv7.setTextColor(Color.WHITE);
				tv8.setTextColor(Color.WHITE);
				break;
			case 7:
				tv1.setTextColor(Color.WHITE);
				tv2.setTextColor(Color.WHITE);
				tv3.setTextColor(Color.WHITE);
				tv4.setTextColor(Color.WHITE);
				tv5.setTextColor(Color.WHITE);
				tv6.setTextColor(Color.WHITE);
				tv7.setTextColor(Color.RED);
				tv8.setTextColor(Color.WHITE);
				break;
			case 8:
				tv1.setTextColor(Color.WHITE);
				tv2.setTextColor(Color.WHITE);
				tv3.setTextColor(Color.WHITE);
				tv4.setTextColor(Color.WHITE);
				tv5.setTextColor(Color.WHITE);
				tv6.setTextColor(Color.WHITE);
				tv7.setTextColor(Color.WHITE);
				tv8.setTextColor(Color.RED);
				break;
			default:
				break;
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (mView != null) {
			ViewGroup parent = (ViewGroup) mView.getParent();
			parent.removeView(mView);
		}
	}
}
