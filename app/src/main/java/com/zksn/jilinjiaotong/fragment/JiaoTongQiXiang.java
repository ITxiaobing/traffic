package com.zksn.jilinjiaotong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.zksn.jilinjiaotong.R;


public class JiaoTongQiXiang extends Fragment {
    private View view;
    private ImageView sousuo_chaxun;
    private TextView shikuang_txt, zhushi_txt, wutian_txt;
    private LinearLayout yubaoInfo;
    private LinearLayout mMap;
    private MapView mMapView;
    private RoutePlanSearch mSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.jiaotongqixiang, container, false);
        initview();
        initMapView();
        return view;
    }


    private void initMapView() {
        mMapView = new MapView(getActivity());
        mMap.addView(mMapView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        BaiduMap mBaidumap = mMapView.getMap();
        LatLng cenpt = new LatLng(43.8868593, 125.3247893);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaidumap.setMapStatus(mMapStatusUpdate);
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(listener);
    }

    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        public void onGetWalkingRouteResult(WalkingRouteResult result) {
            //
        }

        public void onGetTransitRouteResult(TransitRouteResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
            }
            if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                //result.getSuggestAddrInfo()
                return;
            }
            if (result.error == SearchResult.ERRORNO.NO_ERROR) {
               /* TransitRouteOverlay overlay = new MyTransitRouteOverlay(mBaidumap);
                mBaidumap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();*/
            }
        }

        public void onGetDrivingRouteResult(DrivingRouteResult result) {
            //
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    };

    private void initview() {
        sousuo_chaxun = (ImageView) view.findViewById(R.id.sousuo);
        yubaoInfo = (LinearLayout) view.findViewById(R.id.yubao_info);
        sousuo_chaxun.setOnClickListener(onClickLis);
        shikuang_txt = (TextView) view.findViewById(R.id.jt_shikuang_txt);
        shikuang_txt.setOnClickListener(onClickLis);
        zhushi_txt = (TextView) view.findViewById(R.id.jt_zhushi_txt);
        mMap = (LinearLayout) view.findViewById(R.id.map);
        zhushi_txt.setOnClickListener(onClickLis);
        wutian_txt = (TextView) view.findViewById(R.id.jt_wutian_txt);
        wutian_txt.setOnClickListener(onClickLis);

    }


    private View.OnClickListener onClickLis = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sousuo:
                    yubaoInfo.setVisibility(View.GONE);
                    searchRout();

                    break;
                case R.id.jt_shikuang_txt:
                    shikuang_txt.setTextColor(Color.GRAY);
                    wutian_txt.setTextColor(Color.WHITE);
                    zhushi_txt.setTextColor(Color.WHITE);
                    break;
                case R.id.jt_zhushi_txt:
                    shikuang_txt.setTextColor(Color.WHITE);
                    zhushi_txt.setTextColor(Color.GRAY);
                    wutian_txt.setTextColor(Color.WHITE);
                    break;
                case R.id.jt_wutian_txt:
                    shikuang_txt.setTextColor(Color.WHITE);
                    zhushi_txt.setTextColor(Color.WHITE);
                    wutian_txt.setTextColor(Color.GRAY);
                    break;
                default:
                    break;
            }
        }
    };

    private void searchRout() {
        PlanNode stNode = PlanNode.withCityNameAndPlaceName("长春市", "长春市体育馆");
        PlanNode enNode = PlanNode.withCityNameAndPlaceName("长春市", "长春市公安局");
        mSearch.transitSearch((new TransitRoutePlanOption())
                .from(stNode)
                .city("长春市")
                .to(enNode));
        // mSearch.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapView != null) {
            mMapView.onDestroy();
        }
        if (mSearch != null) {
            mSearch.destroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        if (mMapView != null) {
            mMapView.onPause();
        }
    }
}
