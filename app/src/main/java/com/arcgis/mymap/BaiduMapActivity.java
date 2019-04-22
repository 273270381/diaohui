package com.arcgis.mymap;

import android.Manifest;
import android.app.Service;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ZoomControls;
import com.arcgis.mymap.service.LocationService;
import com.arcgis.mymap.utils.Degree;
import com.arcgis.mymap.utils.DistanceUtils;
import com.arcgis.mymap.utils.MeasureArea;
import com.arcgis.mymap.utils.ToastNotRepeat;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/1/21.
 */
public class BaiduMapActivity extends AppCompatActivity implements SensorEventListener {
    ImageButton menu,zoomIn,zoomOut,measure,restart,laycheck,dire,dsPosition;
    TextView textView1,jingdu,weidu,lengthline,position,perimeter,areas;
    View view1,view2;
    Button close,clear;
    RadioGroup radioGroup1,radioGroup2;
    RadioButton interest,distance,area,add,save,backdelate;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    Point p;
    LatLng latLng,Xp;
    Handler mHandler;
    BitmapDescriptor mCurrentMarker;
    public String length;
    public ExecutorService threadPool;
    public boolean aBoolean=false;
    private MapView mapView;
    private BaiduMap baiduMap;
    private Double lastX = 0.0;
    private float mCurrentAccracy;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    public double degree;
    private MyLocationData locData;
    private int mCurrentDirection = 0;
    private boolean isFirstLocate = true;
    List<String> permissionList = new ArrayList<>();
    private LocationService locService;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private SensorManager mSensorManager;
    public Vibrator mVibrator;
    private UiSettings mUiSettings;
    public MylocationListener mlistener = new MylocationListener();
    private ArrayList<LatLng> listPoints = new ArrayList<LatLng>();
    private ArrayList<LatLng> listPoints1 = new ArrayList<LatLng>();
    private ArrayList<LatLng> listPointsXp = new ArrayList<LatLng>();
    private ArrayList<LatLng> listPoints3 ;
    List<Overlay> overlayList1=new ArrayList<>();
    List<Overlay> overlayList2=new ArrayList<>();
    List<Overlay> overlayListXp=new ArrayList<>();
    List<Overlay> overlayListMp=new ArrayList<>();
    List<Overlay> overlayPh=new ArrayList<>();
    List<Overlay> overlayLine1=new ArrayList<>();
    List<Overlay> overlayText1=new ArrayList<>();
    int a=1;
    int b=1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidumap);
        intView();
        intBaiduView();
        setlistener();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void intBaiduView() {
        locService = new LocationService(this);
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        LocationClientOption mOption = locService.getDefaultLocationClientOption();
        locService.setLocationOption(mOption);
        //地图初始化
        baiduMap = mapView.getMap();
        //开启定位图层
        baiduMap.setMyLocationEnabled(true);
        //定位初始化
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        baiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
        locService.registerListener(mlistener);
        //开启指南针
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        dire.setImageResource(R.mipmap.aub);
        mCurrentMode= MyLocationConfiguration.LocationMode.NORMAL;
        baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode,true,mCurrentMarker));
        MapStatus.Builder builder1=new MapStatus.Builder();
        builder1.overlook(0);
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder1.build()));
        permission();
        // 隐藏百度的LOGO
        View child = mapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        // 不显示地图缩放控件（按钮控制栏）
        mapView.showZoomControls(false);

    }
    //初始化控件
    private void intView() {
        mapView = (MapView) findViewById(R.id.bmapView);
        menu = (ImageButton) findViewById(R.id.menu);
        zoomIn = (ImageButton) findViewById(R.id.zoonIn);
        zoomOut = (ImageButton) findViewById(R.id.zoonOut);
        measure = (ImageButton) findViewById(R.id.measure);
        textView1 = (TextView) findViewById(R.id.zuobiao1);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        jingdu = (TextView) findViewById(R.id.jingdu);
        weidu = (TextView) findViewById(R.id.weidu);
        lengthline = (TextView) findViewById(R.id.juli);
        position = (TextView) findViewById(R.id.fangwei);
        perimeter = (TextView) findViewById(R.id.zhouchang);
        areas = (TextView) findViewById(R.id.mianji);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        backdelate= (RadioButton) findViewById(R.id.backdelate);
        add= (RadioButton) findViewById(R.id.add);
        save= (RadioButton) findViewById(R.id.save);
        relativeLayout= (RelativeLayout) findViewById(R.id.relativeLayout);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearlayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearlayout2);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearlayout3);
        close = (Button) findViewById(R.id.close);
        clear = (Button) findViewById(R.id.clear);
        restart = (ImageButton) findViewById(R.id.restart);
        interest= (RadioButton) findViewById(R.id.interest);
        distance= (RadioButton) findViewById(R.id.distance);
        area= (RadioButton) findViewById(R.id.area);
        laycheck= (ImageButton) findViewById(R.id.laycheck);
        dire= (ImageButton) findViewById(R.id.au);
        dsPosition= (ImageButton) findViewById(R.id.dingwei);
        threadPool = Executors.newCachedThreadPool();
    }
    //添加监听
    private void setlistener() {
        Clicklistener listener = new Clicklistener();
        Mapchangelistener maplistener=new Mapchangelistener();
        baiduMap.setOnMapTouchListener(maplistener);
        menu.setOnClickListener(listener);
        zoomIn.setOnClickListener(listener);
        zoomOut.setOnClickListener(listener);
        measure.setOnClickListener(listener);
        interest.setOnClickListener(listener);
        distance.setOnClickListener(listener);
        area.setOnClickListener(listener);
        add.setOnClickListener(listener);
        clear.setOnClickListener(listener);
        close.setOnClickListener(listener);
        restart.setOnClickListener(listener);
        backdelate.setOnClickListener(listener);
        laycheck.setOnClickListener(listener);
        dire.setOnClickListener(listener);
        dsPosition.setOnClickListener(listener);
    }

    private void requestLocation() {
        locService.start();
    }
    //地图状态监听
    public class Mapchangelistener implements BaiduMap.OnMapTouchListener{
        @Override
        public void onTouch(MotionEvent motionEvent) {
            if (aBoolean){
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE:
                mHandler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg!=null){
                            switch(msg.what){
                                case 1:
                                    try{
                                        Xp = (LatLng) msg.obj;
                                        listPointsXp.add(Xp);
                                        listPointsXp.add(latLng);
                                        OverlayOptions lineOptions=new PolylineOptions().points(listPointsXp).color(Color.YELLOW).dottedLine(true);
                                        length=DistanceUtils.dataformat(DistanceUtils.GetShortDistance(Xp.longitude,Xp.latitude,latLng.longitude,latLng.latitude)/1000);
                                        String degreeXp=DistanceUtils.dataformat(Degree.getDegree(Xp.longitude,Xp.latitude,latLng.longitude,latLng.latitude));
                                        //添加文本
                                        OverlayOptions XpOptions=new TextOptions().bgColor(0xA7C0DC).text(length+"m"+"，"+degreeXp+"度")
                                                .position(Xp).fontSize(30).align(4,16).fontColor(Color.BLACK);
                                        jingdu.setText("经度：" + DistanceUtils.dataformatX(Xp.longitude));
                                        weidu.setText("纬度：" + DistanceUtils.dataformatX(Xp.latitude));
                                        if (overlayListXp.size()>=2){
                                            overlayListXp.get(overlayListXp.size()-1).remove();
                                            overlayListXp.get(overlayListXp.size()-2).remove();
                                        }
                                        if (overlayListMp.size()>=2){
                                            overlayListMp.get(overlayListMp.size()-1).remove();
                                        }
                                        Overlay o1=baiduMap.addOverlay(lineOptions);
                                        Overlay o2=baiduMap.addOverlay(XpOptions);
                                        overlayListXp.add(o1);
                                        overlayListXp.add(o2);
                                        listPointsXp.clear();
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                        }
                    }
                };
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        android.graphics.Point p = new android.graphics.Point(mapView.getWidth() / 2, mapView.getHeight() / 2);
                        LatLng latLng2=baiduMap.getProjection().fromScreenLocation(p);
                        try{
                            Message msg = new Message();
                            msg.obj =latLng2;
                            msg.what = 1;
                            mHandler.sendMessage(msg);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                        break;
                }
            }
        }
    }
    //功能监听
    public class Clicklistener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dingwei:
                            isFirstLocate=true;
                            requestLocation();
                    break;
                case R.id.au:
                    switch(mCurrentMode){
                        case NORMAL:
                            dire.setImageResource(R.mipmap.auc);
                            mCurrentMode= MyLocationConfiguration.LocationMode.COMPASS;
                            baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode,true, mCurrentMarker));
                            MapStatus.Builder builder=new MapStatus.Builder();
                            builder.overlook(0);
                            baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                            break;
                        case COMPASS:
                            dire.setImageResource(R.mipmap.aub);
                            mCurrentMode= MyLocationConfiguration.LocationMode.NORMAL;
                            baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode,true,mCurrentMarker));
                            MapStatus.Builder builder1=new MapStatus.Builder();
                            builder1.overlook(0);
                            baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder1.build()));
                            break;
                    }
                    break;
                case R.id.menu:
                    finish();
                    break;
                case R.id.zoonIn:
                    float zoonLevel = baiduMap.getMapStatus().zoom;
                    if (zoonLevel <= 20) {
                        baiduMap.setMapStatus(MapStatusUpdateFactory.zoomIn());
                        zoomOut.setEnabled(true);
                    } else {
                        ToastNotRepeat.show(BaiduMapActivity.this, "已经放大到最大!");
                        zoomIn.setEnabled(false);
                    }
                    break;
                case R.id.zoonOut:
                    float zooLevel = baiduMap.getMapStatus().zoom;
                    if (zooLevel > 4) {
                        baiduMap.setMapStatus(MapStatusUpdateFactory.zoomOut());
                        zoomIn.setEnabled(true);
                    } else {
                        zoomOut.setEnabled(false);
                        ToastNotRepeat.show(BaiduMapActivity.this, "已经缩至最小!");
                    }
                    break;
                case R.id.laycheck:
                    int type = baiduMap.getMapType();
                    if (type==1){
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    }else {
                        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    }
                    break;
                case R.id.measure:
                    aBoolean=false;
                    textView1.setVisibility(View.GONE);
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.GONE);
                    radioGroup1.setVisibility(View.VISIBLE);
                    radioGroup2.setVisibility(View.VISIBLE);
                    clear.setVisibility(View.VISIBLE);
                    close.setVisibility(View.VISIBLE);
                    restart.setVisibility(View.VISIBLE);
                    measure.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    break;
                case R.id.interest:
                    aBoolean=false;
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    listPoints.clear();
                    baiduMap.clear();
                    break;
                case R.id.distance:
                    aBoolean=false;
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    listPoints.clear();
                    baiduMap.clear();
                    break;
                case R.id.area:
                    aBoolean=false;
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.GONE);
                    listPoints1.clear();
                    baiduMap.clear();
                    break;
                case R.id.clear:
                    a=1;
                    b=1;
                    aBoolean=false;
                    overlayListMp.clear();
                    overlayListXp.clear();
                    overlayPh.clear();
                    overlayLine1.clear();
                    overlayText1.clear();
                    listPoints.clear();
                    listPoints1.clear();
                    listPointsXp.clear();
                    overlayList1.clear();
                    overlayList2.clear();
                    baiduMap.clear();
                    lengthline.setText("距离：");
                    position.setText("方位：");
                    perimeter.setText("周长：");
                    areas.setText("面积 :");
                    jingdu.setText("经度：");
                    weidu.setText("纬度：");
                    break;
                case R.id.close:
                    aBoolean=false;
                    restart.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    radioGroup1.setVisibility(View.GONE);
                    radioGroup2.setVisibility(View.GONE);
                    clear.setVisibility(View.GONE);
                    close.setVisibility(View.GONE);
                    measure.setVisibility(View.VISIBLE);
                    zoomIn.setVisibility(View.VISIBLE);
                    zoomOut.setVisibility(View.VISIBLE);
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    textView1.setVisibility(View.VISIBLE);
                    listPoints.clear();
                    overlayList1.clear();
                    overlayList2.clear();
                    baiduMap.clear();
                    break;
                case R.id.restart:
                    if (distance.isChecked()){
                        listPoints.clear();
                        listPointsXp.clear();
                        aBoolean = false;
                    }
                    if (area.isChecked()) {
                        listPoints1.clear();
                        listPointsXp.clear();
                        overlayList1.clear();
                        overlayList2.clear();
                        aBoolean = false;
                    }
                    break;
                case R.id.backdelate:
                    try{
                        if (interest.isChecked()&&overlayPh.size()>1){
                            overlayPh.get(overlayPh.size()-a).remove();
                            goback(listPoints);
                            a++;
                        }
                        if (distance.isChecked()&&overlayPh.size() > 1){
                            goback(listPoints);
                            overlayPh.get(overlayPh.size()-b).remove();
                            overlayLine1.get(overlayLine1.size()-b).remove();
                            overlayText1.get(overlayText1.size()-b).remove();
                            b++;
                        }
                        if (area.isChecked()){
                            goback(listPoints1);
                            overlayPh.get(overlayPh.size()-1).remove();
                            overlayList1.get(overlayList1.size()-1).remove();
                            overlayList2.get(overlayList2.size()-1).remove();
                            Stroke stroke=new Stroke(2,Color.BLUE);
                            OverlayOptions fillOptions1=new PolygonOptions().points(listPoints1).fillColor(R.color.color20).stroke(stroke);
                            Overlay overlay1=baiduMap.addOverlay(fillOptions1);
                            overlayList1.add(overlay1);
                            showperimeter(listPoints1);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.add:
                    a=1;
                    b=1;
                    aBoolean=true;
                    p = new android.graphics.Point(mapView.getWidth() / 2, mapView.getHeight() / 2);
                    latLng=baiduMap.getProjection().fromScreenLocation(p);
                    listPoints.add(latLng);
                    BitmapDescriptor bdA;
                    if (interest.isChecked()){
                        bdA= BitmapDescriptorFactory.fromResource(R.mipmap.marker_default);
                        jingdu.setText("经度：" + DistanceUtils.dataformatX(latLng.longitude));
                        weidu.setText("纬度：" + DistanceUtils.dataformatX(latLng.latitude));
                    }else {
                        bdA= BitmapDescriptorFactory.fromResource(R.mipmap.point1);
                    }
                    MarkerOptions ooA=new MarkerOptions().position(latLng).icon(bdA).anchor(0.5f,0.5f);
                    Overlay ph=baiduMap.addOverlay(ooA);
                    overlayPh.add(ph);
                    if (distance.isChecked()){
                        if (listPoints.size()>=2){
                            //画线
                            OverlayOptions lineOptions=new PolylineOptions().points(listPoints).color(Color.BLUE);
                            //把线显示在地图上
                            Overlay overlayline=baiduMap.addOverlay(lineOptions);
                            overlayLine1.add(overlayline);
                            showRecorder(listPoints);
                        }
                    }
                    if (area.isChecked()){
                        listPoints1.add(latLng);
                        if (listPoints1.size()>=3){
                            Stroke stroke=new Stroke(2,Color.BLUE);
                            OverlayOptions fillOptions1=new PolygonOptions().points(listPoints1).fillColor(R.color.color20).stroke(stroke);
                            Overlay overlay1=baiduMap.addOverlay(fillOptions1);
                            overlayList1.add(overlay1);
                            showperimeter(listPoints1);
                            if (overlayList1.size()>=2){
                                overlayList1.get(overlayList1.size()-2).remove();
                            }

                        }
                    }
                    break;
            }
        }
    }

    /**
     * 计算周长
     */
    private void showperimeter(final List<LatLng> list){
                try{
                    double distance = 0;
                    if (list.size()>=2){
                        for (int i=0;i<list.size()-1;i++){
                            LatLng point1=list.get(i);
                            LatLng point2=list.get(i+1);
                            distance=distance+ DistanceUtils.GetShortDistance( point1.longitude, point1.latitude,
                                    point2.longitude, point2.latitude);
                        }
                        LatLng point3=list.get(0);
                        LatLng point4=list.get(list.size()-1);
                        distance= distance+DistanceUtils.GetShortDistance(point3.longitude,point3.latitude,point4.longitude,point4.latitude);
                        String strDistance = String.valueOf(distance / 1000);
                        if (strDistance.contains(".")) {
                            strDistance = strDistance.substring(0,
                                    strDistance.indexOf(".") + 3);
                        }
                        MeasureArea me=new MeasureArea();
                        String areaPolygon=me.getArea(list);
                        perimeter.setText("周长："+strDistance+"m");
                        areas.setText("面积："+areaPolygon+" m²");
                        //添加文本
                        OverlayOptions lineOptions2=new TextOptions().bgColor(0xA7C0DC).text(strDistance+"m，"+areaPolygon+" m²")
                                .position(list.get(list.size()-1)).fontSize(30).align(4,16).fontColor(Color.BLACK);
                        Overlay overlay2=baiduMap.addOverlay(lineOptions2);
                        overlayList2.add(overlay2);
                        if (overlayList2.size()>=2){
                            overlayList2.get(overlayList2.size()-2).remove();
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    /**
     * 计算距离
     */
    private void showRecorder(final List<LatLng> list){
                try{
                    double distance = 0;
                    if (list.size()>=2){
                        for (int i=0;i<list.size()-1;i++){
                            LatLng point1=list.get(i);
                            LatLng point2=list.get(i+1);
                            distance=distance+ DistanceUtils.GetShortDistance( point1.longitude, point1.latitude,
                                    point2.longitude, point2.latitude);
                        }
                        String strDistance = String.valueOf(distance / 1000);
                        if (strDistance.contains(".")) {
                            strDistance = strDistance.substring(0,
                                    strDistance.indexOf(".") + 3);
                        }
                        degree = Degree.getDegree(list.get(list.size() - 1).longitude, list.get(list.size() - 1).latitude,
                                list.get(list.size() - 2).longitude, list.get(list.size() - 2).latitude);
                        position.setText("方位：" + DistanceUtils.dataformat(degree) + "度");
                        lengthline.setText("距离："+strDistance+"米");
                        //添加文本
                        OverlayOptions lineOptions=new TextOptions().bgColor(0xA7C0DC).text(length+"m"+"，"+DistanceUtils.dataformat(degree)+"度")
                                .position(list.get(list.size()-1)).fontSize(30).align(4,16).fontColor(Color.BLACK);
                        Overlay overlaytext=baiduMap.addOverlay(lineOptions);
                        overlayText1.add(overlaytext);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    public void goback(List<LatLng> pc) {
        overlayListXp.get(overlayListXp.size()-2).remove();
        listPoints3=new ArrayList<>();
        listPoints3.add(pc.get(pc.size()-1));
        listPoints3.add(Xp);
        //画线
        OverlayOptions lineOptions=new PolylineOptions().points(listPoints3).color(Color.YELLOW).dottedLine(true);
        //把线显示在地图上
        Overlay overlay=baiduMap.addOverlay(lineOptions);
        if (overlayListMp.size()>0){
            overlayListMp.get(overlayListMp.size()-1).remove();
        }
        overlayListMp.add(overlay);
        latLng=pc.get(pc.size()-1);
    }
    /**
     * 权限管理
     */
    private void permission() {
        if (ContextCompat.checkSelfPermission(BaiduMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(BaiduMapActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(BaiduMapActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(BaiduMapActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }
    public class MylocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation Location) {
            if (Location.getLocType() == BDLocation.TypeGpsLocation || Location.getLocType() == BDLocation.TypeNetWorkLocation) {
                // map view 销毁后不在处理新接收的位置
                if (Location == null || mapView == null) {
                    return;
                }
                mCurrentLat = Location.getLatitude();
                mCurrentLon = Location.getLongitude();
                mCurrentAccracy = Location.getRadius();
                locData = new MyLocationData.Builder()
                        .accuracy(Location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(mCurrentDirection).latitude(Location.getLatitude())
                        .longitude(Location.getLongitude()).build();
                baiduMap.setMyLocationData(locData);
                if (isFirstLocate) {
                    LatLng ll = new LatLng(Location.getLatitude(), Location.getLongitude());
                    MapStatus.Builder builder = new MapStatus.Builder()
                            .target(ll)
                            .zoom(18.0f);
                    baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                    isFirstLocate = false;
                    Log.i("TAG", "经度：" + Location.getLatitude());
                    Log.i("TAG", "纬度：" + Location.getLongitude());
                }
                MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
                locService.stop();
            }
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.AXIS_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            baiduMap.setMyLocationData(locData);
        }
        lastX = x;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    protected void onDestroy() {
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        locService.unregisterListener(mlistener);
        locService.stop();
        mapView.onDestroy();
        //mapView = null;
        //baiduMap.setMyLocationEnabled(false);
        super.onDestroy();
    }
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener((SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    protected void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener((SensorEventListener) this);
        super.onStop();
    }
}
