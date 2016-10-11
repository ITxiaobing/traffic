package com.zksn.jilinjiaotong.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.bumptech.glide.Glide;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.zksn.jilinjiaotong.R;
import com.zksn.jilinjiaotong.utils.ImageFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PublicWeatherActivity extends BaseActivity implements OnClickListener {
    private TextView title;
    private int PICK_PICTURE = 4;
    private SimpleDateFormat formatter;
    public LocationClient mLocationClient = null;
    private BDLocationListener myListener;
    public String PICTURE_PATH;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMapAddress();
        InitView();
    }

    /**
     * 初始化地图
     */
    private void initMapAddress() {

        mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类

        myListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        mLocationClient.start();// 定位SDK

    }

    /**
     * 配置定位SDK参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);// 可选，默认false,设置是否使用gps
        option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.e("", location.getLocType() + "");
            mEtDistance.setText(location.getAddrStr());
            if (location.getCity() != null) {
                mLocationClient.stop();
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void InitView() {
        setContentView(R.layout.activity_public_weather);
        formatter = new SimpleDateFormat("yyyyMMddHH:mm:ss");
        mEtDistance = (EditText) findViewById(R.id.et_distance);
        mPublicContent = (EditText) findViewById(R.id.publish_content);
        mBtPublic = (Button) findViewById(R.id.bt_public);
        mPublishImage = (ImageView) findViewById(R.id.publish_image);
        mBtPublic.setOnClickListener(this);
        LinearLayout mSelectWeatherPublish = (LinearLayout) findViewById(R.id.select_weather_public);
        mSelectWeatherPublish.setOnClickListener(this);
        title = (TextView) findViewById(R.id.title);
        title.setText(R.string.tianqi_fabu);
        initToolBar();
    }

    private void initToolBar() {
        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.backicon);
        mToolBar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // 控件的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_weather_public:

                new PopupWindows(PublicWeatherActivity.this, v);
                break;
            case R.id.bt_public:
                // 上传图片
                upLoadPicture();
                break;
            default:
                break;
        }

    }

    private void upLoadPicture() {
        if (TextUtils.isEmpty(mEtDistance.getText().toString().trim())) {
            Snackbar.make(mEtDistance, getString(R.string.empty_area), Snackbar.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(mPublicContent.getText().toString())) {
            Snackbar.make(mEtDistance,getString(R.string.empty_content), Snackbar.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(PICTURE_PATH)) {
            Snackbar.make(mEtDistance, getString(R.string.empty_picture), Snackbar.LENGTH_LONG).show();
            return;
        } else {
            sendPic(PICTURE_PATH, mEtDistance.getText().toString(),
                    mPublicContent.getText().toString());
        }

    }

    public class PopupWindows extends PopupWindow {

        @SuppressLint("InlinedApi")
        @SuppressWarnings("deprecation")
        public PopupWindows(Context mContext, View parent) {

            View view = View
                    .inflate(mContext, R.layout.item_popupwindows, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view
                    .findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.push_bottom_in_2));
            setHeight(250);
            setWidth(LayoutParams.MATCH_PARENT);
            setHeight(LayoutParams.MATCH_PARENT);
            setBackgroundDrawable(new BitmapDrawable());
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();

            Button bt1 = (Button) view
                    .findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view
                    .findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view
                    .findViewById(R.id.item_popupwindows_cancel);
            bt1.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    takePhoto();
                    dismiss();
                }
            });
            // 选择照片
            bt2.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent,getString(R.string.select_picture)), PICK_PICTURE);
                    dismiss();
                }
            });
            bt3.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });

        }

    }

    private static final int TAKE_PICTURE = 0x000000;
    private String imagePath;
    private File mFileName;
    private EditText mEtDistance;
    private Button mBtPublic;
    private EditText mPublicContent;
    private ImageView mPublishImage;

    /**
     * 选择照片
     */
    @SuppressLint("NewApi")
    public void takePhoto() {
        Date curDate = new Date(System.currentTimeMillis());
        imagePath = formatter.format(curDate);
        imagePath += ".jpg";
        imagePath = this.getCacheDir().getAbsolutePath() + "/" + imagePath;
        Intent getImageByCamera = new Intent(
                "android.media.action.IMAGE_CAPTURE");
        mFileName = new File(imagePath);
        try {
            mFileName.createNewFile();
            mFileName.setWritable(true, false);
        } catch (IOException ignored) {
        }
        getImageByCamera.putExtra("return-data", false);
        getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(mFileName));
        getImageByCamera.putExtra("outputFormat",
                Bitmap.CompressFormat.JPEG.toString());
        getImageByCamera.putExtra("noFaceDetection", true);
        startActivityForResult(getImageByCamera, TAKE_PICTURE);

    }

    @SuppressWarnings("static-access")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PICTURE) {
            if (resultCode == RESULT_OK) {

                PICTURE_PATH = mFileName.getPath();
                Glide.with(this).load("file://" + PICTURE_PATH).into(mPublishImage);
            }

        } else if (PICK_PICTURE == requestCode) {
            if (resultCode == RESULT_OK) {
                PICTURE_PATH = selectImage(this, data);
                Glide.with(this).load("file://" + PICTURE_PATH).into(mPublishImage);
            }

        }
    }


    private String formatFileName() {
        StringBuilder sb = new StringBuilder();
        Date curDate = new Date(System.currentTimeMillis());
        sb.append(formatter.format(curDate));
        sb.append(".jpg");
        return sb.toString();
    }

    private void sendPic(String path, String distance, String content) {
        ImageFactory.compressPicture(PICTURE_PATH, PICTURE_PATH);
        String IMAGE_TYPE = "image/*";
        HttpUtils httpUtils = new HttpUtils(5000 * 60);
        RequestParams requestParams = new RequestParams();
        requestParams.addQueryStringParameter("Content-Type",
                "application/x-www-form-urlencoded");
        // requestParams.addBodyParameter("zaiqingAddress", distance);
        // requestParams.addBodyParameter("description", content);
        requestParams.addBodyParameter("file", new File(path));
        // /storage/emulated/0/Pictures/Screenshots/Screenshot_20160607-114401.png
        // http://172.27.35.3:8080/ NetUrl.UP_LOAD_PICTURE
        httpUtils.send(HttpMethod.POST,
                "http://172.27.35.2:8080/UploadServlet", requestParams,
                new com.lidroid.xutils.http.callback.RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException httpException,
                                          String arg1) {
                        Toast.makeText(PublicWeatherActivity.this, arg1,
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Toast.makeText(PublicWeatherActivity.this, "fff",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    public static String selectImage(Context context, Intent data) {
        Uri selectedImage = data.getData();
        // Log.e(TAG, selectedImage.toString());
        if (selectedImage != null) {
            String uriStr = selectedImage.toString();
            String path = uriStr.substring(10, uriStr.length());
            if (path.startsWith("com.sec.android.gallery3d")) {
                Log.e("",
                        "It's auto backup pic path:" + selectedImage.toString());
                return null;
            }
        }
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

}
