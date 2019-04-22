package com.arcgis.mymap.Geological;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arcgis.mymap.Geological.Voice.VoiceToText;
import com.arcgis.mymap.MainActivity;
import com.arcgis.mymap.MainMenuActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.adapter.DDDAdapter;
import com.arcgis.mymap.adapter.DzstAdapter;
import com.arcgis.mymap.adapter.FloorAdapter;
import com.arcgis.mymap.adapter.Tsxmadapter;
import com.arcgis.mymap.bean.JsonBean;
import com.arcgis.mymap.contacts.DicengyanxingPoint;
import com.arcgis.mymap.contacts.DixingdimaoPoint;
import com.arcgis.mymap.contacts.GouzhuwuPoint;
import com.arcgis.mymap.contacts.Lines;
import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.contacts.ShuiwendizhiPoint;
import com.arcgis.mymap.contacts.SurFace;
import com.arcgis.mymap.dialogActivity.Buliangdizhi;
import com.arcgis.mymap.dialogActivity.Dixingdimao;
import com.arcgis.mymap.dialogActivity.Dizhigouzhao;
import com.arcgis.mymap.dialogActivity.DizhixinxiActivity;
import com.arcgis.mymap.dialogActivity.Gouzhuwudian;
import com.arcgis.mymap.dialogActivity.Shuiwendizhi;
import com.arcgis.mymap.dialogActivity.Teshuxingtu;
import com.arcgis.mymap.utils.DataManagerActivity;
import com.arcgis.mymap.utils.Degree;
import com.arcgis.mymap.utils.DistanceUtils;
import com.arcgis.mymap.utils.GeoShowPointUtils;
import com.arcgis.mymap.utils.GetJsonDataUtil;
import com.arcgis.mymap.utils.HscaleView;
import com.arcgis.mymap.utils.LogUtils;
import com.arcgis.mymap.utils.ScaleView;
import com.arcgis.mymap.utils.ToastNotRepeat;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.esri.arcgisruntime.data.TileCache;
import com.esri.arcgisruntime.geometry.GeodeticCurveType;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.LinearUnit;
import com.esri.arcgisruntime.geometry.LinearUnitId;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.BackgroundGrid;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedEvent;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedListener;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.LineSymbol;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.json.JSONArray;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.arcgis.mymap.R.id.name;
import static com.arcgis.mymap.R.id.yr;

/**
 * Created by Administrator on 2018/4/13.
 */

public class GeologicalMapActivity  extends Activity implements SensorEventListener{
    static MapView mMapView;
    ListView lv;
    ImageView compass;
    float currentDegree = 0f;
    SensorManager mSensormanager;
    EditText et1,et2,editText3,editText4,etdc,pet1,pet2,aet1,aet2;
    ArcGISMap mMap;
    ImageButton measure,zoomIn,zoomOut,dw,qx,showpoint,clear,close,camera,next;
    TextView ddd,tsxt,tsxtm,bldz,bldzm,cz,jl,dc,bxzz,ht,yr;
    ScaleView scale1;
    HscaleView scale2;
    View view1,view2;
    RadioGroup radioGroup2;
    RadioButton add,save,backdelate;
    List<GraphicsOverlay> list=new ArrayList<>();
    List<GraphicsOverlay> list2=new ArrayList<>();
    public  DDDAdapter dadapter;
    public InputMethodManager imm;
    private int number = 0;
    public int pp,pid;
    public BitmapDrawable bitmapDrawablePoint;
    public Point dwmp;
    public GeoDataPointActivity geoDataPointActivity;
    public GeoDataLineActivity geoDataLineActivity;
    public GeoDataSurfaceActivity geoDataSurfaceActivity;
    public LocalReceiver4 receiver4;
    public static GraphicsOverlay graphicsOverlay_2;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    public final static int CANSHUA= 1;
    public final static int CANSHUB= 2;
    public final static int CANSHUC= 3;
    private String[] data2 = {"黄土","冻土","膨胀性岩土","盐渍土","软土","花岗岩残积土","红黏土"};
    private String[] data3 = {"岩溶","滑坡","危岩崩塌与岩堆","泥石流","积雪","雪崩","风沙","采空区","水库坍岸","强震区","地震液化","涎流冰"};
    private String[] data = {"Q4al+pl","Q4el+dl","Q4col","Q4ml","Q4el","Q3","Q2","Q1","N1","N2","E1","E2","E3","K1z","K2w","J3p","J3sn","J2s","J2x","J1zl",
    "J1z","T3xj","T3jx","T2l","T2b","T1j","T1f","T1d","P3c","P3d","P3l","P3w","P2m","P2q","P1l","C2w","D3s","D2x","S2hx","S2h","S2s","S1xh","S1s","S1lr","S1x"
    ,"S1l","O3w","O3l","O2b","O2s","O2g","O1m","O1d","O1h","O1f","O1t","O1n","∈3m","∈3s","∈3bx","∈3g","∈2p","∈2q","∈2b","∈2s","∈2m","∈2g","∈1q","∈1sl"
    ,"∈1t","∈1m","∈1sp","∈1lj","∈1n","∈1s","∈1b","Z2d","Z2s","Z1ds","Z1g","Z1w","Nh2n","Nh2m","Nh2q","Qb2m","Qb2h","Qb2l"};
    private String[] datacode = {"730m","731m","732m","733m","734m","735m","736m","737m","738m","739m","740m","741m","742m","743m","744m","745m","746m","747m","748m","749m","750m"
            ,"751m","752m","753m","754m","755m","756m","757m","758m","759m","760m","761m","762m","763m","764m","765m","766m","767m","768m","769m","770m"
            ,"771m","772m","773m","774m","775m","776m","777m","778m","779m","780m","781m","782m","783m","784m","785m","786m","787m","788m","789m","790m"
            ,"791m","792m","793m","794m","795m","796m","797m","798m","799m","800m","801m","802m","803m","804m","805m","806m","807m","808m","809m","810m","811m","812m","813m","814m","815m","816m"};
    private List<SpannableString> data4 =new ArrayList<>();
    List<Graphic> listline = new ArrayList<>();
    List<Graphic> listfill = new ArrayList<>();
    List<Graphic> listtext = new ArrayList<>();
    List<Graphic> listtext2 = new ArrayList<>();
    List<String> linela = new ArrayList<>();
    List<String> linelax = new ArrayList<>();
    List<String> lineln = new ArrayList<>();
    List<String> linelnx = new ArrayList<>();
    List<String> surfacela = new ArrayList<>();
    List<String> surfaceln = new ArrayList<>();
    public static List<Graphic> listph = new ArrayList<>();
    List<LitepalPoints> pointlist=new ArrayList<>();
    List<LitepalPoints> pointsList2 = new ArrayList<>();
    List<MoreLines> linelist = new ArrayList<>();
    List<SurFace> surfacelist = new ArrayList<>();
    List<String> sublistline = new ArrayList<>();
    List<String> sublistsurface = new ArrayList<>();
    public PointCollection centerpoint,centerpoint1,seekack,centerpoint2,centerpoint3,centerpoint4;
    public ExecutorService threadPool;
    private GoogleApiClient client;
    private int i1=1;
    public String item2="",item3="";
    public String set1;
    public String set2;
    public String Dcode,dzgz_des,dzgz_name,tsxt_des,tsxt_name,bldz_des,bldz_name;
    public SpannableString item4;
    static Point mp;
    public android.graphics.Point p;
    public SQLiteDatabase db;
    public MyDatabaseHelper dbHelper;
    private String num,str,projectname;
    public String pposition;
    public static boolean ali1=false;
    public static boolean ali2=false;
    public static boolean ali3=false;
    public static boolean ali4=false;
    private static boolean isLoaded = false;
    public  BitmapDrawable bitmapDrawable4;
    public  PictureMarkerSymbol pictureMarkerSymbol4;
    private List<NewProject> projects=new ArrayList<>();
    private int requestCode = 2;
    List<String> permissionList = new ArrayList<>();
    public MapchangeListener mapchangeListener;
    public LocationDisplay locationDisplay;
    public static final int TAKE_PHOTO = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geologicalmap);
        intViews();
        setData();
        permission();
        setBackground();
        addlistener();
    }

    private void setData() {
        for (int i=0;i<5;i++){
            SpannableString mi =new SpannableString(data[i]);
            mi.setSpan(new SubscriptSpan(),1,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mi.setSpan(new AbsoluteSizeSpan(30),1,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mi.setSpan(new SuperscriptSpan(),2,data[i].length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mi.setSpan(new AbsoluteSizeSpan(30),2,data[i].length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            data4.add(mi);
        }
        for (int n=5;n<=data.length-7;n++){
            SpannableString mn = new SpannableString(data[n]);
            mn.setSpan(new SubscriptSpan(),1,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mn.setSpan(new AbsoluteSizeSpan(30),1,2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            data4.add(mn);
        }
        for (int k=data.length-6;k<=data.length-1;k++){
            SpannableString mk = new SpannableString(data[k]);
            mk.setSpan(new SubscriptSpan(),1,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mk.setSpan(new AbsoluteSizeSpan(30),1,3,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            data4.add(mk);
        }
    }

    private void addlistener() {
        Clicklistener listener = new Clicklistener();
        LongClickListener longClickListener = new LongClickListener();
        measure.setOnClickListener(listener);
        close.setOnClickListener(listener);
        add.setOnClickListener(listener);
        clear.setOnClickListener(listener);
        backdelate.setOnClickListener(listener);
        save.setOnClickListener(listener);
        dw.setOnClickListener(listener);
        qx.setOnClickListener(listener);
        zoomIn.setOnClickListener(listener);
        zoomOut.setOnClickListener(listener);
        showpoint.setOnClickListener(listener);
        camera.setOnClickListener(listener);
        next.setOnClickListener(listener);
        ddd.setOnClickListener(listener);
        tsxt.setOnClickListener(listener);
        tsxtm.setOnClickListener(listener);
        bldz.setOnClickListener(listener);
        //bldzm.setOnClickListener(listener);
        cz.setOnClickListener(listener);
        jl.setOnClickListener(listener);
        dc.setOnClickListener(listener);
        bxzz.setOnClickListener(listener);
        ht.setOnClickListener(listener);
        yr.setOnClickListener(listener);
        dc.setOnLongClickListener(longClickListener);
        ddd.setOnLongClickListener(longClickListener);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    /**
     * 设置网格背景
     */
    private void setBackground() {
        BackgroundGrid mainBackground = new BackgroundGrid();
        mainBackground.setGridLineWidth(1);
        mainBackground.setGridSize(10);
        mainBackground.setColor(Color.WHITE);
        mainBackground.setGridLineColor(Color.GRAY);
        mMapView.setBackgroundGrid(mainBackground);
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        mSensormanager.unregisterListener(this);
        super.onStop();
    }
    private void intViews() {
        initJsonData();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        compass = (ImageView) findViewById(R.id.compass);
        mSensormanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ddd= (TextView) findViewById(R.id.ddd);
        tsxt= (TextView) findViewById(R.id.tsxt);
        tsxtm= (TextView) findViewById(R.id.tsxtm);
        bldz= (TextView) findViewById(R.id.bldz);
        bldzm= (TextView) findViewById(R.id.bldzm);
        bxzz = (TextView) findViewById(R.id.bxzz);
        ht = (TextView) findViewById(R.id.ht);
        yr = (TextView) findViewById(R.id.yr);
        cz = (TextView) findViewById(R.id.cz);
        jl = (TextView) findViewById(R.id.jl);
        dc = (TextView) findViewById(R.id.dc);
        mMapView = (MapView) findViewById(R.id.mapView);
        mapchangeListener=new MapchangeListener();
        mMapView.addMapScaleChangedListener(mapchangeListener);
        showpoint= (ImageButton) findViewById(R.id.showpoint);
        measure = (ImageButton) findViewById(R.id.measure);
        camera= (ImageButton) findViewById(R.id.camera);
        next= (ImageButton) findViewById(R.id.next);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        scale1= (ScaleView) findViewById(R.id.scale1);
        scale2= (HscaleView) findViewById(R.id.scale2);
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        close = (ImageButton) findViewById(R.id.close);
        zoomIn = (ImageButton) findViewById(R.id.zoonIn);
        zoomOut = (ImageButton) findViewById(R.id.zoonOut);
        backdelate = (RadioButton) findViewById(R.id.backdelate);
        clear = (ImageButton) findViewById(R.id.clear);
        add = (RadioButton) findViewById(R.id.add);
        save= (RadioButton) findViewById(R.id.save);
        dw= (ImageButton) findViewById(R.id.dw);
        qx= (ImageButton) findViewById(R.id.qx);
        dbHelper=new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        centerpoint = new PointCollection(SpatialReferences.getWgs84());
        centerpoint1 = new PointCollection(SpatialReferences.getWgs84());
        centerpoint2 = new PointCollection(SpatialReferences.getWgs84());
        centerpoint3 = new PointCollection(SpatialReferences.getWgs84());
        centerpoint4 = new PointCollection(SpatialReferences.getWgs84());
        seekack = new PointCollection(SpatialReferences.getWgs84());
        bitmapDrawable4 = (BitmapDrawable) getResources().getDrawable(R.mipmap.point1);
        pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
        geoDataPointActivity =new GeoDataPointActivity();
        geoDataLineActivity = new GeoDataLineActivity();
        geoDataSurfaceActivity = new GeoDataSurfaceActivity();
        threadPool = Executors.newCachedThreadPool();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.delatedatas.broadcast");
        receiver4 = new LocalReceiver4();
        registerReceiver(receiver4,intentFilter);
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"=5c74ebde");

        //获取表位置
        GeoGetTable geoGetTable=new GeoGetTable();
        pposition=geoGetTable.getTableposition(GeologicalMapActivity.this,db,dbHelper,projects);
        int sposition=Integer.parseInt(geoGetTable.getPpposition(GeologicalMapActivity.this,db,dbHelper));
        str=projects.get(sposition).getStrname();
        projectname=projects.get(sposition).getProjectname();

        Cursor c=db.rawQuery("select*from Geodxdmpoints"+pposition,null);
        number=c.getCount();
        Cursor cursor1 = db.query("Geodxdmpoints"+pposition, null, null, null, null, null, null);
        Cursor cursor2 = db.query("Geomorelines"+pposition, null, null, null, null, null, null);
        Cursor cursor3 = db.query("Geosurface"+pposition, null, null, null, null, null, null);
        Cursor cursor4 = db.query("Geodxdmpoints"+pposition,null,"gclassification = ?",new String[]{"地调点"},null,null,null);
        try {
            pointlist=geoDataPointActivity.getData(pointlist,cursor1);
            pointsList2 = geoDataPointActivity.getData(pointsList2,cursor4);
            linelist=geoDataLineActivity.getData(linelist,cursor2);
            surfacelist=geoDataSurfaceActivity.getData(surfacelist,cursor3);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public String setGText(){
        String tc = "";
        Cursor c = db.query("Geopoints"+pposition,null,"gclassification = ?",new String[]{"地调点"},null,null,null );
        if (c.moveToLast()){
            tc = c.getString(c.getColumnIndex("name"));
        }
        c.close();
        if (tc==""){
            tc = "SP0";
        }
        if (isContainNumber(tc)){
            int x = getInt(tc);
            tc=tc.replace(String.valueOf(x),String.valueOf(x+1));
            return tc;
        }else {
            tc = tc + "1";
            return tc;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        setAinameit();
        mSensormanager.registerListener(this,mSensormanager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        mSensormanager.unregisterListener(this);
        super.onPause();
    }

    /**
     * 根据ali1,ali2,ali3,ali4传入的boolean值，显示不同的元素
     */
    private void setAinameit() {
        Intent intent=getIntent();
        if (intent!=null){
            if (ali1){//显示全部
                List<LitepalPoints> list1 = new ArrayList<>();
                list1 = (List<LitepalPoints>) getIntent().getSerializableExtra("list1");
                List<MoreLines> list2 = new ArrayList<>();
                list2 = (List<MoreLines>) getIntent().getSerializableExtra("list2");
                List<SurFace> list3 =new ArrayList<>();
                list3 = (List<SurFace>) getIntent().getSerializableExtra("list3");
                if (list1==null){
                    list1= new ArrayList<>();
                }
                if (list2==null){
                    list2=new ArrayList<>();
                }
                if (list3==null){
                    list3=new ArrayList<>();
                }
                GeoShowPointUtils.ShowAllPointsUtils(GeologicalMapActivity.this,threadPool,list1,list2,list3,mMapView);
                ali1=false;
             }
            if (ali2){
                double x=Double.parseDouble(intent.getStringExtra("jingdu"));
                double y=Double.parseDouble(intent.getStringExtra("weidu"));
                double z=Double.parseDouble(intent.getStringExtra("gaodu"));
                String classification=intent.getStringExtra("classification");
                String name = intent.getStringExtra("name");
                if (classification.equals("地调点")){
                    bitmapDrawablePoint = (BitmapDrawable) getResources().getDrawable(R.mipmap.didiaodian);
                }else if (classification.equals("产状")){
                    bitmapDrawablePoint = (BitmapDrawable) getResources().getDrawable(R.mipmap.cz);
                }else if (classification.equals("节理")){
                    bitmapDrawablePoint = (BitmapDrawable) getResources().getDrawable(R.mipmap.jl);
                }else if (classification.equals("地质时代")){
                    bitmapDrawablePoint = (BitmapDrawable) getResources().getDrawable(R.mipmap.point1);
                }else if (classification.indexOf("p")!=-1){
                    bitmapDrawablePoint = (BitmapDrawable) getResources().getDrawable(R.mipmap.camera2);
                }
                final Point p=new Point(x,y,z,SpatialReferences.getWgs84());
                mMapView.setViewpointCenterAsync(p);
                final GraphicsOverlay graphicsOverlay=new GraphicsOverlay();
                mMapView.getGraphicsOverlays().add(graphicsOverlay);
                final PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(bitmapDrawablePoint);
                pictureMarkerSymbol.loadAsync();
                pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
                    @Override
                    public void run() {
                        Graphic ph = new Graphic(p, pictureMarkerSymbol);
                        graphicsOverlay.getGraphics().add(ph);
                    }
                });
                TextSymbol textSymbolh = new TextSymbol(12f, name, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                graphicsOverlay.getGraphics().add(new Graphic(p,textSymbolh));
                ali2=false;
            }
            if (ali3){
                List<String> gla = intent.getStringArrayListExtra("gla");
                List<String> gln = intent.getStringArrayListExtra("gln");
                String clas = intent.getStringExtra("clas");
                if (!useList(data3,clas)){
                    final GraphicsOverlay graphicsOverlayshowH=new GraphicsOverlay();
                    mMapView.getGraphicsOverlays().add(graphicsOverlayshowH);
                    SimpleLineSymbol simpleLineSymbolH = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH_DOT_DOT, Color.RED, 2);
                    PointCollection pointCollection=new PointCollection(SpatialReferences.getWgs84());
                    for (int a = 0 ; a<= gla.size()-1 ; a++){
                        pointCollection.add(Double.parseDouble(gla.get(a)),Double.parseDouble(gln.get(a)));
                    }
                    Polyline polyline=new Polyline(pointCollection,SpatialReferences.getWgs84());
                    Graphic line = new Graphic(polyline, simpleLineSymbolH);
                    graphicsOverlayshowH.getGraphics().add(line);
                    TextSymbol textSymbol = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.TOP  );
                    graphicsOverlayshowH.getGraphics().add(new Graphic(pointCollection.get(0),textSymbol));
                    graphicsOverlayshowH.getGraphics().add(new Graphic(pointCollection.get(1),textSymbol));
                }else {
                    final GraphicsOverlay graphicsOverlayshowH=new GraphicsOverlay();
                    mMapView.getGraphicsOverlays().add(graphicsOverlayshowH);
                    SimpleLineSymbol simpleLineSymbolH = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                    PointCollection pointCollection=new PointCollection(SpatialReferences.getWgs84());
                    for (int a = 0 ; a<= gla.size()-1 ; a++){
                        pointCollection.add(Double.parseDouble(gla.get(a)),Double.parseDouble(gln.get(a)));
                    }
                    Polyline polyline=new Polyline(pointCollection,SpatialReferences.getWgs84());
                    Graphic line = new Graphic(polyline, simpleLineSymbolH);
                    graphicsOverlayshowH.getGraphics().add(line);
                    TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.TOP  );
                    graphicsOverlayshowH.getGraphics().add(new Graphic(polyline,textSymbolh2));
                }
                ali3=false;
            }
            if (ali4){
                List<String> mgla = intent.getStringArrayListExtra("mgla");
                List<String> mgln = intent.getStringArrayListExtra("mgln");
                String mclas = intent.getStringExtra("mclas");
                final GraphicsOverlay graphicsOverlayshowM=new GraphicsOverlay();
                mMapView.getGraphicsOverlays().add(graphicsOverlayshowM);
                LineSymbol outline = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.BLUE,1);
                SimpleFillSymbol simpleFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID,R.color.color29,outline);
                PointCollection pointCollectionM=new PointCollection(SpatialReferences.getWgs84());
                for (int a = 0 ; a<= mgla.size()-1 ; a++){
                    pointCollectionM.add(Double.parseDouble(mgla.get(a)),Double.parseDouble(mgln.get(a)));
                }
                Polygon polygon=new Polygon(pointCollectionM,SpatialReferences.getWgs84());
                Graphic fill = new Graphic(polygon, simpleFillSymbol);
                graphicsOverlayshowM.getGraphics().add(fill);
                TextSymbol textSymbolh3 = new TextSymbol(12f, mclas, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.TOP  );
                graphicsOverlayshowM.getGraphics().add(new Graphic(polygon,textSymbolh3));
                ali4=false;
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver4);
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            //loadArcgismap("/storage/emulated/0/321.tpk");
            loadArcgismap(str);
            
        }
    }
    //加载图层
    private void loadArcgismap(String str){
        mMap = new ArcGISMap();
        TileCache mainTileCache = new TileCache(str);
        ArcGISTiledLayer arcGISTiledLayer = new ArcGISTiledLayer(mainTileCache);
        final Basemap basemap = new Basemap(arcGISTiledLayer);
        mMap.setBasemap(basemap);
        mMapView.setMap(mMap);
        locationDisplay=mMapView.getLocationDisplay();
        locationDisplay.addDataSourceStatusChangedListener(new LocationDisplay.DataSourceStatusChangedListener() {
            @Override
            public void onStatusChanged(LocationDisplay.DataSourceStatusChangedEvent dataSourceStatusChangedEvent) {
                if (dataSourceStatusChangedEvent.isStarted())
                    return;
                if (dataSourceStatusChangedEvent.getError() == null)
                    return;
                boolean permissionCheck1= ContextCompat.checkSelfPermission(GeologicalMapActivity.this,PERMISSIONS_STORAGE[0])==
                        PackageManager.PERMISSION_GRANTED;
                boolean permissionCheck2= ContextCompat.checkSelfPermission(GeologicalMapActivity.this,PERMISSIONS_STORAGE[1])==
                        PackageManager.PERMISSION_GRANTED;
                if (!(permissionCheck1 && permissionCheck2)) {
                    ActivityCompat.requestPermissions(GeologicalMapActivity.this, PERMISSIONS_STORAGE, requestCode);
                } else {
                    String message = String.format("Error in DataSourceStatusChangedListener: %s", dataSourceStatusChangedEvent
                            .getSource().getLocationDataSource().getError().getMessage());
                    Toast.makeText(GeologicalMapActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 真机上获取触发event的传感器类型
        int sensorType = event.sensor.getType();
        // 模拟器上获取触发event的传感器类型
        switch(sensorType){
            case Sensor.TYPE_ORIENTATION:
                // 获取绕Z轴转过的角度。
                float degree = event.values[0];
                // 创建旋转动画（反向转过degree度）
                RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                // 设置动画的持续时间
                ra.setDuration(200);
                // 运行动画
                compass.startAnimation(ra);
                currentDegree = -degree;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private class LongClickListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            switch(v.getId()){
                case R.id.dc:
                    LinearLayout li = (LinearLayout) getLayoutInflater().inflate(R.layout.duancenglayout,null);
                    AlertDialog dialog = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("断层：")
                            .setView(li)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dc.setText(etdc.getText().toString());
                                }
                            }).show();
                    etdc = (EditText) dialog.findViewById(R.id.dc);
                    Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    btnpositive.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    break;
                case R.id.ddd:
                    LinearLayout li2 = (LinearLayout) getLayoutInflater().inflate(R.layout.didiaodianlayout,null);
                    final AlertDialog dialog2 = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("地调点：")
                            .setView(li2)
                            .setNegativeButton("编辑", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setPositiveButton("确定", null).show();
                    lv= (ListView) dialog2.findViewById(R.id.listview);
                    dadapter = new DDDAdapter(pointsList2,GeologicalMapActivity.this,R.layout.ddd_listitem_layout);
                    lv.setAdapter(dadapter);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            dadapter.setSelectItem(position);
                            dadapter.notifyDataSetChanged();
                            pp = position;
                            pid = pointsList2.get(position).getId();
                        }
                    });
                    Button btnpositive2=dialog2.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative2=dialog2.getButton(AlertDialog.BUTTON_NEGATIVE);
                    btnpositive2.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative2.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout li3 = (LinearLayout) getLayoutInflater().inflate(R.layout.didiaodian_layout,null);
                            AlertDialog dialog3 = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("编辑：")
                                    .setView(li3)
                                    .setNegativeButton("取消",null)
                                    .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String text1 = aet1.getText().toString();
                                            String text2 = aet2.getText().toString();
                                            ContentValues values = new ContentValues();
                                            values.put("name", text1);
                                            values.put("gdescription", text2);
                                            db.update("Geopoints"+pposition, values, "id=?", new String[]{String.valueOf(pid)});
                                            pointsList2.get(pp).setName(text1);
                                            pointsList2.get(pp).setDescription(text2);
                                            if (imm!=null){
                                                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                                            }
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    dadapter.notifyDataSetChanged();
                                                    ToastNotRepeat.show(GeologicalMapActivity.this,"保存成功");
                                                }
                                            }, 400);
                                            Intent a=new Intent();
                                            a.setAction("com.delatedatas.broadcast");
                                            sendBroadcast(a);
                                        }
                                    }).show();
                            Button btnpositive3=dialog3.getButton(AlertDialog.BUTTON_POSITIVE);
                            Button btnnegative3=dialog3.getButton(AlertDialog.BUTTON_NEGATIVE);
                            btnpositive3.setTextColor(getResources().getColor(R.color.color29));
                            btnnegative3.setTextColor(getResources().getColor(R.color.color29));
                            aet1 = (EditText) dialog3.findViewById(R.id.et1);
                            aet2 = (EditText) dialog3.findViewById(R.id.et2);
                            aet1.setText(pointsList2.get(pp).getName());
                            aet2.setText(pointsList2.get(pp).getDescription());
                        }
                    });
                    break;
            }
            return true;
        }
    }
    /**
     * 为各个控件写点击监听
     */
    private class Clicklistener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.jl:
//                    LinearLayout ljl = (LinearLayout) getLayoutInflater().inflate(R.layout.cz_layout,null);
//                    AlertDialog dialogjl = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("节理：")
//                            .setView(ljl)
//                            .setNegativeButton("取消",null)
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    String testjl = editText3.getText().toString()+"°"+"∠" + editText4.getText().toString()+"°";
//                                    BitmapDrawable b= (BitmapDrawable) getResources().getDrawable(R.mipmap.jl);
//                                    TextSymbol ts1 = new TextSymbol(12f, testjl, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT,TextSymbol.VerticalAlignment.MIDDLE);
//                                    String code = String.valueOf(Integer.parseInt(editText3.getText().toString())+361)+"d";
//                                    DrawPoint(b,ts1,"节理",testjl,code,null);
//                                }
//                            }).show();
//                    editText3 = (EditText) dialogjl.findViewById(R.id.et1);
//                    editText4 = (EditText) dialogjl.findViewById(R.id.et2);
//                    Button btnpositivejl=dialogjl.getButton(AlertDialog.BUTTON_POSITIVE);
//                    Button btnnegativejl=dialogjl.getButton(AlertDialog.BUTTON_NEGATIVE);
//                    btnpositivejl.setTextColor(getResources().getColor(R.color.color29));
//                    btnnegativejl.setTextColor(getResources().getColor(R.color.color29));
                    Intent gz = new Intent(GeologicalMapActivity.this, Gouzhuwudian.class);
                    gz.putExtra("position",pposition);
                    startActivityForResult(gz,10004);
                    break;
                case R.id.cz:
//                    LinearLayout lcz = (LinearLayout) getLayoutInflater().inflate(R.layout.shuiwendizhidian,null);
//                    AlertDialog dialogcz = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("水文地质点：")
//                            .setView(lcz)
//                            .setNegativeButton("取消",null)
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
////                                    String testcz = editText1.getText().toString()+"°"+"∠"+ editText2.getText().toString()+"°";
////                                    BitmapDrawable b= (BitmapDrawable) getResources().getDrawable(R.mipmap.cz);
////                                    TextSymbol ts1 = new TextSymbol(12f, testcz, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT,TextSymbol.VerticalAlignment.MIDDLE);
////                                    String code = "";
////                                    if (Integer.parseInt(editText1.getText().toString())<10){
////                                        code = "00"+editText1.getText().toString()+"c";
////                                    }else if (Integer.parseInt(editText1.getText().toString())>=10&&Integer.parseInt(editText1.getText().toString())<100){
////                                        code = "0"+editText1.getText().toString()+"c";
////                                    }else {
////                                        code = editText1.getText().toString()+"c";
////                                    }
////                                    DrawPoint(b,ts1,"产状",testcz,code,null);
//
//                                }
//                            }).show();
//                    Button btnpositivecz=dialogcz.getButton(AlertDialog.BUTTON_POSITIVE);
//                    Button btnnegativecz=dialogcz.getButton(AlertDialog.BUTTON_NEGATIVE);
//                    btnpositivecz.setTextColor(getResources().getColor(R.color.color29));
//                    btnnegativecz.setTextColor(getResources().getColor(R.color.color29));
                    Intent sw = new Intent(GeologicalMapActivity.this, Shuiwendizhi.class);
                    sw.putExtra("position",pposition);
                    startActivityForResult(sw,10003);
                    break;
                case R.id.dc:
//                    String textx = dc.getText().toString();
//                    SimpleLineSymbol simpleLineSymbolx=new SimpleLineSymbol(SimpleLineSymbol.Style.DASH_DOT_DOT,Color.RED,2);
//                    TextSymbol tx = new TextSymbol(12f, textx, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.TOP);
//                    DrawLine(simpleLineSymbolx,tx,textx,"null");
                    //地层岩性
//                    LinearLayout dcyx = (LinearLayout) getLayoutInflater().inflate(R.layout.dicengyanxing,null);
//                    AlertDialog dialogdc = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("地层岩性：")
//                            .setView(dcyx)
//                            .setNegativeButton("取消",null)
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent i = new Intent(GeologicalMapActivity.this, DizhixinxiActivity.class);
//                                    startActivity(i);
//                                }
//                            }).show();
//                    dialogdc.setCanceledOnTouchOutside(false);
//                    final EditText et = (EditText) dialogdc.findViewById(R.id.et2);
//                    Button bt = (Button) dialogdc.findViewById(R.id.voice);
//                    bt.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            VoiceToText.startSpeechDialog(GeologicalMapActivity.this,et);
//                        }
//                    });
                    Intent dcyx = new Intent(GeologicalMapActivity.this, DizhixinxiActivity.class);
                    dcyx.putExtra("position",pposition);
                    startActivityForResult(dcyx,10002);
                    break;
                case R.id.add:
                    LinearLayout l4 = (LinearLayout) getLayoutInflater().inflate(R.layout.dizhishidai_layout,null);
                    AlertDialog dialog4 = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("地质时代：")
                            .setView(l4)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    BitmapDrawable b1= (BitmapDrawable) getResources().getDrawable(R.mipmap.point1);
                                    TextSymbol ts1 = new TextSymbol(12f, String.valueOf(item4), Color.GREEN, TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.TOP);
                                    //DrawPoint(b1,ts1,"地质时代",String.valueOf(item4),Dcode,null);
                                }
                            }).show();
                    GridView gridView = (GridView) dialog4.findViewById(R.id.gridView);
                    final DzstAdapter adapter=new DzstAdapter(data4,GeologicalMapActivity.this);
                    gridView.setAdapter(adapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            item4 = data4.get(position);
                            Dcode = datacode[position];
                            adapter.setSelectItem(position);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    Button btnpositive4=dialog4.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative4=dialog4.getButton(AlertDialog.BUTTON_NEGATIVE);
                    btnpositive4.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative4.setTextColor(getResources().getColor(R.color.color29));
                    break;
                case R.id.bldz:
//                    String text2 = bldzm.getText().toString();
//                    SimpleLineSymbol simpleLineSymbol1=new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.YELLOW,2);
//                    TextSymbol t2 = new TextSymbol(12f, text2, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
//                    DrawMoreLine(simpleLineSymbol1,t2,text2,"K2");
                    Intent bl = new Intent(GeologicalMapActivity.this, Buliangdizhi.class);
                    bl.putExtra("position",pposition);
                    startActivityForResult(bl,10007);
                    break;
                case R.id.yr:
//                    String str_yr = yr.getText().toString();
//                    LineSymbol outline = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.BLUE,1);
//                    SimpleFillSymbol simpleFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID,R.color.color29,outline);
//                    TextSymbol t1=new TextSymbol(12f,str_yr,Color.GREEN,TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.MIDDLE);
                    //添加点
                    final GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
                    list.add(graphicsOverlay);
                    mMapView.getGraphicsOverlays().add(graphicsOverlay);
                    p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                    mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
                    pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
                    pictureMarkerSymbol4.loadAsync();
                    pictureMarkerSymbol4.addDoneLoadingListener(new Runnable() {
                     @Override
                     public void run() {
                          Graphic ph = new Graphic(mp, pictureMarkerSymbol4);
                          listph.add(ph);
                          graphicsOverlay.getGraphics().add(listph.get(listph.size() - 1));
                      }
                    });
                    centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
                    seekack.add(mp.getX(),mp.getY(),mp.getZ());
                    centerpoint3.add(mp.getX(), mp.getY(), mp.getZ());

                    //Drawsurface(simpleFillSymbol,t1,str_yr,"K2",bldz_name,bldz_des);
                    break;
                case R.id.bldzm:
                    LinearLayout l3 = (LinearLayout) getLayoutInflater().inflate(R.layout.list_alert_layout,null);
                    AlertDialog dialog3 = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("不良地质现象：")
                            .setView(l3)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    bldzm.setText(item3);
                                }
                            }).show();
                    final Tsxmadapter tsxmadapter3 = new Tsxmadapter(data3,R.layout.tsxm_item,GeologicalMapActivity.this);
                    ListView listView3 = (ListView) dialog3.findViewById(R.id.listview);
                    listView3.setAdapter(tsxmadapter3);
                    listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            tsxmadapter3.setSelectItem(position);
                            tsxmadapter3.notifyDataSetChanged();
                            item3 = data3[position];
                        }
                    });
                    Button btnpositive3=dialog3.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative3=dialog3.getButton(AlertDialog.BUTTON_NEGATIVE);
                    btnpositive3.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative3.setTextColor(getResources().getColor(R.color.color29));
                    break;
                case R.id.tsxt:
//                    String textm = tsxtm.getText().toString();
//                    LineSymbol outline = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.BLUE,1);
//                    SimpleFillSymbol simpleFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID,R.color.color29,outline);
//                    TextSymbol t1=new TextSymbol(12f,textm,Color.GREEN,TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.MIDDLE);
//                    Drawsurface(simpleFillSymbol,t1,textm,"K2");
                    Intent dz = new Intent(GeologicalMapActivity.this, Dizhigouzhao.class);
                    dz.putExtra("position",pposition);
                    startActivityForResult(dz,10005);
                    break;
                case R.id.bxzz:
                    String str = bxzz.getText().toString();
                    SimpleLineSymbol simpleLineSymbol1=new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.YELLOW,2);
                    TextSymbol t2 = new TextSymbol(12f, str, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                    DrawMoreLine(simpleLineSymbol1,t2,str,"K2",dzgz_des,dzgz_name);
                    break;
                case R.id.tsxtm:
//                    LinearLayout l2 = (LinearLayout) getLayoutInflater().inflate(R.layout.list_alert_layout,null);
//                    AlertDialog dialog2 = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("特殊性土：")
//                            .setView(l2)
//                            .setNegativeButton("取消",null)
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    tsxtm.setText(item2);
//                                }
//                            }).show();
//                    final Tsxmadapter tsxmadapter = new Tsxmadapter(data2,R.layout.tsxm_item,GeologicalMapActivity.this);
//                    ListView listView2 = (ListView) dialog2.findViewById(R.id.listview);
//                    listView2.setAdapter(tsxmadapter);
//                    listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            tsxmadapter.setSelectItem(position);
//                            tsxmadapter.notifyDataSetChanged();
//                            item2 = data2[position];
//                        }
//                    });
//                    Button btnpositive2=dialog2.getButton(AlertDialog.BUTTON_POSITIVE);
//                    Button btnnegative2=dialog2.getButton(AlertDialog.BUTTON_NEGATIVE);
//                    btnpositive2.setTextColor(getResources().getColor(R.color.color29));
//                    btnnegative2.setTextColor(getResources().getColor(R.color.color29));
                    Intent ts = new Intent(GeologicalMapActivity.this, Teshuxingtu.class);
                    ts.putExtra("position",pposition);
                    startActivityForResult(ts,10006);
                    break;
                case R.id.ht:
                    String textm = ht.getText().toString();
                    LineSymbol outline2 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.BLUE,1);
                    SimpleFillSymbol simpleFillSymbol2 = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID,R.color.color29,outline2);
                    TextSymbol t3=new TextSymbol(12f,textm,Color.GREEN,TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.MIDDLE);
                    Drawsurface(simpleFillSymbol2,t3,textm,"K2",tsxt_name,tsxt_des);
                    break;
                case R.id.ddd:
//                    LinearLayout l1 = (LinearLayout) getLayoutInflater().inflate(R.layout.didiaodian_layout,null);
//                    AlertDialog dialog = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("地形地貌:")
//                            .setView(l1)
//                            .setNegativeButton("取消",null)
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    BitmapDrawable b1= (BitmapDrawable) getResources().getDrawable(R.mipmap.didiaodian);
//                                    String t1 = et1.getText().toString();
//                                    String t2 = et2.getText().toString();
//                                    TextSymbol ts1 = new TextSymbol(12f, t1, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.TOP);
//                                    DrawPoint(b1,ts1,"地调点",t1,"722d",t2);
//                                }
//                            }).show();
//                    et1= (EditText) dialog.findViewById(R.id.et1);
//                    et2= (EditText) dialog.findViewById(R.id.et2);
//                    et1.setText(setGText());
//                    Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
//                    Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
//                    btnpositive.setTextColor(getResources().getColor(R.color.color29));
//                    btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    Intent dx = new Intent(GeologicalMapActivity.this, Dixingdimao.class);
                    dx.putExtra("position",pposition);
                    startActivityForResult(dx,10001);
                    break;
                case R.id.next:
                    String str_yr = yr.getText().toString();
                    LineSymbol outline = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.BLUE,1);
                    SimpleFillSymbol simpleFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID,R.color.color29,outline);
                    TextSymbol t1=new TextSymbol(12f,str_yr,Color.GREEN,TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.MIDDLE);
                    Drawsurface2(simpleFillSymbol,t1,str_yr,"K2",bldz_name,bldz_des);
                    centerpoint2.clear();
                    centerpoint3.clear();
                    list.clear();
                    list2.clear();
                    linela.clear();
                    lineln.clear();
                    surfacela.clear();
                    surfaceln.clear();
                    sublistline.clear();
                    sublistsurface.clear();
                    break;
                case R.id.camera:
                    LinearLayout li = (LinearLayout) getLayoutInflater().inflate(R.layout.didiaodian_layout,null);
                    AlertDialog dialog1 = new AlertDialog.Builder(GeologicalMapActivity.this).setTitle("拍照点:")
                            .setView(li)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    set1 = pet1.getText().toString();
                                    set2 = pet2.getText().toString();
                                    File outputImage = null;
                                    p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                                    mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
                                    if (!set1.contains("-")) {
                                        File file = new File(Environment.getExternalStorageDirectory() + "/MyMap/地勘/Pictrue/" + projectname + "/" + set1);
                                        if (!file.exists()) {
                                            file.mkdirs();
                                            String path = Environment.getExternalStorageDirectory() + "/MyMap/地勘/Pictrue/" + projectname + "/" + set1 + "/" + set1 + ".jpg";
                                            outputImage = new File(path);
                                            //创建file对象，用于存储拍照后的图片；
                                            try {
                                                if (outputImage.exists()) {
                                                    outputImage.delete();
                                                }
                                                outputImage.createNewFile();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            //启动相机程序
                                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                                                intent.putExtra(MediaStore.EXTRA_OUTPUT , FileProvider.getUriForFile(GeologicalMapActivity.this,"mymap.fileprovider",outputImage));
                                            }else {
                                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
                                            }
                                            startActivityForResult(intent, TAKE_PHOTO);
                                        }else {
                                            ToastNotRepeat.show(GeologicalMapActivity.this,"文件名已存在！");
                                        }
                                    }else {
                                        int index=set1.indexOf("-");
                                        String text1=set1.substring(0,index);
                                        File file=new File(Environment.getExternalStorageDirectory()+"/MyMap/地勘/Pictrue/"+projectname+"/"+text1);
                                        if (!file.exists()) {
                                            file.mkdirs();
                                        }
                                        String path=Environment.getExternalStorageDirectory()+"/MyMap/地勘/Pictrue/"+projectname+"/"+text1+"/"+set1+".jpg";
                                        outputImage=new File(path);
                                        //创建file对象，用于存储拍照后的图片；
                                        try {
                                            if (outputImage.exists()) {
                                                outputImage.delete();
                                            }
                                            outputImage.createNewFile();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        //启动相机程序
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                                            intent.putExtra(MediaStore.EXTRA_OUTPUT , FileProvider.getUriForFile(GeologicalMapActivity.this,"mymap.fileprovider",outputImage));
                                        }else {
                                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
                                        }
                                        startActivityForResult(intent, TAKE_PHOTO);
                                    }
                                }
                            }).show();
                    pet1 = (EditText) dialog1.findViewById(R.id.et1);
                    pet2 = (EditText) dialog1.findViewById(R.id.et2);

                    /*p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                    mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
                    int amount=0;
                    String la = "";
                    String cla="";
                    File outputImage = null;
                    Cursor c=db.rawQuery("select*from Geophotopoints"+pposition,null);
                    amount=c.getCount();
                    if (amount==0){
                        text="p1";
                    }else {
                        Cursor c1=db.query("Geophotopoints"+pposition,null,null,null,null,null,null);
                        if (c1.moveToLast()){
                            la=c1.getString(c1.getColumnIndex("la"));
                            cla=c1.getString(c1.getColumnIndex("gclassification"));
                        }
                        c1.close();
                        if (la.equals(String.valueOf(mp.getX()))){
                            if (!cla.contains("-")){
                                text=cla+"-1";
                            }else {
                                int x= Integer.parseInt(cla.substring(cla.length()-1));
                                String a=cla.substring(0,cla.length()-1);
                                text=a+String.valueOf(x+1);
                            }
                        }else {
                            if (!cla.contains("-")){
                                int x=getInt(cla);
                                text=cla.replace(String.valueOf(x),String.valueOf(x+1));
                            }else {
                                int index=cla.indexOf("-");
                                String text1=cla.substring(0,index);
                                int x=getInt(text1);
                                text=text1.replace(String.valueOf(x),String.valueOf(x+1));
                            }
                        }
                    }
                    if (!text.contains("-")){
                        File file=new File(Environment.getExternalStorageDirectory()+"/MyMap/地勘/Pictrue/"+projectname+"/"+text);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        String path=Environment.getExternalStorageDirectory()+"/MyMap/地勘/Pictrue/"+projectname+"/"+text+"/"+text+".jpg";
                        outputImage=new File(path);
                    }else {
                        int index=text.indexOf("-");
                        String text1=text.substring(0,index);
                        File file=new File(Environment.getExternalStorageDirectory()+"/MyMap/地勘/Pictrue/"+projectname+"/"+text1);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        String path=Environment.getExternalStorageDirectory()+"/MyMap/地勘/Pictrue/"+projectname+"/"+text1+"/"+text+".jpg";
                        outputImage=new File(path);
                    }
                    //创建file对象，用于存储拍照后的图片；
                    try {
                        if (outputImage.exists()) {
                            outputImage.delete();
                        }
                        outputImage.createNewFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //启动相机程序
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                        intent.putExtra(MediaStore.EXTRA_OUTPUT , FileProvider.getUriForFile(GeologicalMapActivity.this,"mymap.fileprovider",outputImage));
                    }else {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
                    }
                    startActivityForResult(intent, TAKE_PHOTO);*/
                    break;
                case R.id.showpoint:
                    GeoShowPointUtils.ShowAllPointsUtils(GeologicalMapActivity.this,threadPool,pointlist,linelist,surfacelist,mMapView);
                    showpoint.setVisibility(View.GONE);
                    clear.setVisibility(View.VISIBLE);
                    break;
                case R.id.zoonIn:
                    try{
                        Double scales=mMapView.getMapScale();
                        mMapView.setViewpointScaleAsync(scales*0.5);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.zoonOut:
                    try{
                        Double scalesl=mMapView.getMapScale();
                        mMapView.setViewpointScaleAsync(scalesl*2);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.dw:
                    try{
                        android.graphics.Point dp=new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                        dwmp= (Point) GeometryEngine.project(mMapView.screenToLocation(dp),SpatialReference.create(4326));
                        if (!locationDisplay.isStarted()){
                            Geometry g=mMapView.getMap().getInitialViewpoint().getTargetGeometry();
                            Geometry a=GeometryEngine.project(g,SpatialReference.create(4326));
                            Geometry p=GeometryEngine.project(locationDisplay.getMapLocation(),SpatialReference.create(4326));
                            boolean x=GeometryEngine.within(p,a);
                            if (!x){
                                locationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.OFF);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastNotRepeat.show(GeologicalMapActivity.this,"已超出工作范围！");
                                        locationDisplay.stop();
                                        dw.setVisibility(View.VISIBLE);
                                        qx.setVisibility(View.GONE);
                                    }
                                },2000);
                            }else {
                                locationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
                            }
                            locationDisplay.startAsync();
                        }
                        dw.setVisibility(View.GONE);
                        qx.setVisibility(View.VISIBLE);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.qx:
                    try{
                        locationDisplay.stop();
                        mMapView.setViewpointCenterAsync(dwmp);
                        dw.setVisibility(View.VISIBLE);
                        qx.setVisibility(View.GONE);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.save:
                    Intent i=new Intent(GeologicalMapActivity.this, GeoDataManagerActivity.class);
                    i.putExtra("pposition",String.valueOf(pposition));
                    startActivity(i);
                    break;
                case R.id.clear:
                    mMapView.getGraphicsOverlays().clear();
                    centerpoint.clear();
                    showpoint.setVisibility(View.VISIBLE);
                    clear.setVisibility(View.GONE);
                    centerpoint2.clear();
                    centerpoint3.clear();
                    list.clear();
                    list2.clear();
                    linela.clear();
                    lineln.clear();
                    surfacela.clear();
                    surfaceln.clear();
                    sublistline.clear();
                    sublistsurface.clear();
                    break;
                case R.id.measure:
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    scale1.setVisibility(View.VISIBLE);
                    scale2.setVisibility(View.VISIBLE);
                    radioGroup2.setVisibility(View.VISIBLE);
                    close.setVisibility(View.VISIBLE);
                    measure.setVisibility(View.GONE);
                    mMapView.getGraphicsOverlays().clear();
                    camera.setVisibility(View.VISIBLE);
                    ddd.setVisibility(View.VISIBLE);
                    dc.setVisibility(View.VISIBLE);
                    cz.setVisibility(View.VISIBLE);
                    jl.setVisibility(View.VISIBLE);
                    tsxt.setVisibility(View.VISIBLE);
                    tsxtm.setVisibility(View.VISIBLE);
                    bldz.setVisibility(View.VISIBLE);
                    bxzz.setVisibility(View.VISIBLE);
                    ht.setVisibility(View.VISIBLE);
                    yr.setVisibility(View.VISIBLE);
                    //bldzm.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    break;
                case R.id.close:
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    scale1.setVisibility(View.GONE);
                    scale2.setVisibility(View.GONE);
                    radioGroup2.setVisibility(View.GONE);
                    close.setVisibility(View.GONE);
                    measure.setVisibility(View.VISIBLE);
                    mMapView.getGraphicsOverlays().clear();
                    centerpoint.clear();
                    centerpoint2.clear();
                    centerpoint3.clear();
                    linela.clear();
                    lineln.clear();
                    surfacela.clear();
                    surfaceln.clear();
                    sublistline.clear();
                    sublistsurface.clear();
                    camera.setVisibility(View.GONE);
                    next.setVisibility(View.GONE);
                    ddd.setVisibility(View.GONE);
                    dc.setVisibility(View.GONE);
                    cz.setVisibility(View.GONE);
                    jl.setVisibility(View.GONE);
                    tsxt.setVisibility(View.GONE);
                    tsxtm.setVisibility(View.GONE);
                    bldz.setVisibility(View.GONE);
                    //bldzm.setVisibility(View.GONE);
                    bxzz.setVisibility(View.GONE);
                    ht.setVisibility(View.GONE);
                    yr.setVisibility(View.GONE);
                    break;
                case R.id.backdelate:
                    try{
                        mMapView.setViewpointCenterAsync(seekack.get(seekack.size()-i1));
                        i1++;
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
    /**
     * 在图层添加点元素
     * @param bitmapDrawable5
     * @param textSymbol
     * @param name
     * @param code
     */
    private void DrawPoint(BitmapDrawable bitmapDrawable5,TextSymbol textSymbol,String name,String classification,String code,String zhibeifayu,String des){
        i1=1;
        graphicsOverlay_2 = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
        seekack.add(mp.getX(),mp.getY(),mp.getZ());
        final PictureMarkerSymbol pictureMarkerSymbol5 = new PictureMarkerSymbol(bitmapDrawable5);
        pictureMarkerSymbol5.loadAsync();
        pictureMarkerSymbol5.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol5);
                listph.add(ph);
                graphicsOverlay_2.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        graphicsOverlay_2.getGraphics().add(new Graphic(mp,textSymbol));
        //获得当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= new Date(System.currentTimeMillis());
        String time=simpleDateFormat.format(date);
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("la",String.valueOf(mp.getX()));
        values.put("ln",String.valueOf(mp.getY()));
        values.put("high",String.valueOf(mp.getZ()));
        values.put("gclassification",classification);
        values.put("gcode",code);
        values.put("zhibeifayu",zhibeifayu);
        values.put("gdescription",des);
        db.insert("Geodxdmpoints"+pposition,null,values);
        values.clear();
        DixingdimaoPoint dixingdimaoPoint = new DixingdimaoPoint();
        dixingdimaoPoint.setName(name);
        dixingdimaoPoint.setLa(String.valueOf(mp.getX()));
        dixingdimaoPoint.setLn(String.valueOf(mp.getY()));
        dixingdimaoPoint.setHigh(String.valueOf(mp.getZ()));
        dixingdimaoPoint.setClassification(classification);
        dixingdimaoPoint.setCode(code);
        dixingdimaoPoint.setZhibeifayu(zhibeifayu);
        dixingdimaoPoint.setDescription(des);
        //pointlist.add(dixingdimaoPoint);
        //if (text.equals("地调点")){
           // pointsList2.add(litepalPoints);
        //}
    }
    /**
     *添加地层岩性点
     *
     */
    private void DrawDCYXPoint(BitmapDrawable bitmapDrawable5,TextSymbol textSymbol,String name,String dznd,String ytmc,String classification,String code,String fhcd,String czet,String jlet,String des){
        i1=1;
        graphicsOverlay_2 = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
        seekack.add(mp.getX(),mp.getY(),mp.getZ());
        final PictureMarkerSymbol pictureMarkerSymbol5 = new PictureMarkerSymbol(bitmapDrawable5);
        pictureMarkerSymbol5.loadAsync();
        pictureMarkerSymbol5.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol5);
                listph.add(ph);
                graphicsOverlay_2.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        graphicsOverlay_2.getGraphics().add(new Graphic(mp,textSymbol));
        //获得当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= new Date(System.currentTimeMillis());
        String time=simpleDateFormat.format(date);
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("la",String.valueOf(mp.getX()));
        values.put("ln",String.valueOf(mp.getY()));
        values.put("high",String.valueOf(mp.getZ()));
        values.put("dznd",dznd);
        values.put("ytmc",ytmc);
        values.put("gclassification",classification);
        values.put("gcode",code);
        values.put("fhcd",fhcd);
        values.put("cz",czet);
        values.put("jl",jlet);
        values.put("gdescription",des);
        db.insert("Geodcyxpoints"+pposition,null,values);
        values.clear();
        DicengyanxingPoint dicengyanxingPoint = new DicengyanxingPoint();
        dicengyanxingPoint.setName(name);
        dicengyanxingPoint.setLa(String.valueOf(mp.getX()));
        dicengyanxingPoint.setLn(String.valueOf(mp.getY()));
        dicengyanxingPoint.setHigh(String.valueOf(mp.getZ()));
        dicengyanxingPoint.setClassification(classification);
        dicengyanxingPoint.setCode(code);
        dicengyanxingPoint.setDescription(des);
        //pointlist.add(dixingdimaoPoint);
        //if (text.equals("地调点")){
        // pointsList2.add(litepalPoints);
        //}
    }

    /**
     * 水文地质点
     */
    private void DrawSWDZPoint(BitmapDrawable bitmapDrawable5,TextSymbol textSymbol,String name,String sllx,String smkd,String ss,String code,String ls,String ll,String sz,String des){
        i1=1;
        graphicsOverlay_2 = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
        seekack.add(mp.getX(),mp.getY(),mp.getZ());
        final PictureMarkerSymbol pictureMarkerSymbol5 = new PictureMarkerSymbol(bitmapDrawable5);
        pictureMarkerSymbol5.loadAsync();
        pictureMarkerSymbol5.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol5);
                listph.add(ph);
                graphicsOverlay_2.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        graphicsOverlay_2.getGraphics().add(new Graphic(mp,textSymbol));
        //获得当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= new Date(System.currentTimeMillis());
        //String time=simpleDateFormat.format(date);
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("la",String.valueOf(mp.getX()));
        values.put("ln",String.valueOf(mp.getY()));
        values.put("high",String.valueOf(mp.getZ()));
        values.put("sllx",sllx);
        values.put("smkd",smkd);
        values.put("ss",ss);
        values.put("ls",ls);
        values.put("ll",ll);
        values.put("sz",sz);
        values.put("code",code);
        values.put("gdescription",des);
        db.insert("Geoswdzpoints"+pposition,null,values);
        values.clear();
        ShuiwendizhiPoint shuiwendizhiPoint = new ShuiwendizhiPoint();
        shuiwendizhiPoint.setName(name);
        shuiwendizhiPoint.setLa(String.valueOf(mp.getX()));
        shuiwendizhiPoint.setLn(String.valueOf(mp.getY()));
        shuiwendizhiPoint.setHigh(String.valueOf(mp.getZ()));
        shuiwendizhiPoint.setCode(code);
        shuiwendizhiPoint.setDes(des);
        //pointlist.add(dixingdimaoPoint);
        //if (text.equals("地调点")){
        // pointsList2.add(litepalPoints);
        //}
    }

    /**
     *添加构筑物点
     */
    private void DrawGZWDPoint(BitmapDrawable bitmapDrawable5,TextSymbol textSymbol,String name,String lx,String code,String des){
        i1=1;
        graphicsOverlay_2 = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
        seekack.add(mp.getX(),mp.getY(),mp.getZ());
        final PictureMarkerSymbol pictureMarkerSymbol5 = new PictureMarkerSymbol(bitmapDrawable5);
        pictureMarkerSymbol5.loadAsync();
        pictureMarkerSymbol5.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol5);
                listph.add(ph);
                graphicsOverlay_2.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        graphicsOverlay_2.getGraphics().add(new Graphic(mp,textSymbol));
        //获得当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= new Date(System.currentTimeMillis());
        String time=simpleDateFormat.format(date);
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("la",String.valueOf(mp.getX()));
        values.put("ln",String.valueOf(mp.getY()));
        values.put("high",String.valueOf(mp.getZ()));
        values.put("lx",lx);
        values.put("code",code);
        values.put("gdescription",des);
        db.insert("Geogzwdpoints"+pposition,null,values);
        values.clear();
        GouzhuwuPoint point = new GouzhuwuPoint();
        point.setName(name);
        point.setLa(String.valueOf(mp.getX()));
        point.setLn(String.valueOf(mp.getY()));
        point.setHigh(String.valueOf(mp.getZ()));
        point.setCode(code);
        point.setClassification(lx);
        point.setDescription(des);
        //pointlist.add(dixingdimaoPoint);
        //if (text.equals("地调点")){
        // pointsList2.add(litepalPoints);
        //}
    }
    private void DrawLine(SimpleLineSymbol simpleLineSymbol,TextSymbol textSymbol,String text,String code){
        i1 = 1;
        final  GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(graphicsOverlay);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
        pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
        pictureMarkerSymbol4.loadAsync();
        pictureMarkerSymbol4.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol4);
                listph.add(ph);
                graphicsOverlay.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        graphicsOverlay.getGraphics().add(new Graphic(mp,textSymbol));
        centerpoint4.add(mp.getX(), mp.getY(), mp.getZ());
        seekack.add(mp.getX(),mp.getY(),mp.getZ());
        if (centerpoint4.size()==2){
            Polyline polyline = new Polyline(centerpoint4, SpatialReferences.getWgs84());
            Graphic line = new Graphic(polyline, simpleLineSymbol);
            graphicsOverlay.getGraphics().add(line);
            linelax.add(String.valueOf(centerpoint4.get(0).getX()));
            linelax.add(String.valueOf(centerpoint4.get(1).getX()));
            linelnx.add(String.valueOf(centerpoint4.get(0).getY()));
            linelnx.add(String.valueOf(centerpoint4.get(1).getY()));
            MoreLines moreLines = new MoreLines();
            moreLines.setListla(Arrays.asList(StringUtils.join(linelax,",").split(",")));
            moreLines.setListln(Arrays.asList(StringUtils.join(linelnx,",").split(",")));
            moreLines.setClassification(text);
            moreLines.setCode(code);
            //获得当前时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String time=simpleDateFormat.format(date);
            ContentValues values=new ContentValues();
            values.put("gclassification",text);
            values.put("gla", StringUtils.join(linelax,","));
            values.put("gln",StringUtils.join(linelnx,","));
            values.put("time",time);
            values.put("gcode",code);
            db.insert("Geomorelines"+pposition,null,values);
            centerpoint4.clear();
            linelax.clear();
            linelnx.clear();
        }
    }
    /**
     * 在图层添加多段线
     * @param simpleLineSymbol
     * @param textSymbol
     * @param text
     * @param code
     */
    private void DrawMoreLine(SimpleLineSymbol simpleLineSymbol, TextSymbol textSymbol, String text, String code,String des,String name){
        i1=1;
        final GraphicsOverlay graphicsOverlay_3 = new GraphicsOverlay();
        list2.add(graphicsOverlay_3);
        mMapView.getGraphicsOverlays().add(graphicsOverlay_3);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
        pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
        pictureMarkerSymbol4.loadAsync();
        pictureMarkerSymbol4.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol4);
                listph.add(ph);
                graphicsOverlay_3.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
        seekack.add(mp.getX(),mp.getY(),mp.getZ());
        centerpoint2.add(mp.getX(), mp.getY(), mp.getZ());
        if (centerpoint2.size() >= 2) {
            Polyline polyline = new Polyline(centerpoint2, SpatialReferences.getWgs84());
            Graphic line = new Graphic(polyline, simpleLineSymbol);
            listline.add(line);
            graphicsOverlay_3.getGraphics().add(listline.get(listline.size() - 1));
            Graphic ts = new Graphic(polyline, textSymbol);
            listtext.add(ts);
            graphicsOverlay_3.getGraphics().add(listtext.get(listtext.size() - 1));
            if (centerpoint2.size()>2){
                mMapView.getGraphicsOverlays().remove(list2.get(list2.size()-2));
            }
                sublistline.add(String.valueOf(centerpoint2.get(centerpoint2.size()-2).getX()));
                linela.add(String.valueOf(centerpoint2.get(centerpoint2.size()-1).getX()));
                lineln.add(String.valueOf(centerpoint2.get(centerpoint2.size()-1).getY()));
                ContentValues values = new ContentValues();
                values.put("gla",StringUtils.join(linela,","));
                values.put("gln",StringUtils.join(lineln,","));
                db.update("Geomorelines"+pposition,values,"gla = ?",new String[] {StringUtils.join(sublistline,",")});
            MoreLines moreLines = new MoreLines();
            moreLines.setListla(Arrays.asList(StringUtils.join(linela,",").split(",")));
            moreLines.setListln(Arrays.asList(StringUtils.join(lineln,",").split(",")));
            moreLines.setClassification(text);
            moreLines.setCode(code);
            if (centerpoint2.size()>2){
                linelist.remove(linelist.size()-1);
            }
            linelist.add(moreLines);
        }else {
            linela.add(String.valueOf(centerpoint2.get(0).getX()));
            lineln.add(String.valueOf(centerpoint2.get(0).getY()));

            //获得当前时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            //String time=simpleDateFormat.format(date);
            ContentValues values=new ContentValues();
            values.put("gclassification",text);
            values.put("gla", StringUtils.join(linela,","));
            values.put("gln",StringUtils.join(lineln,","));
            values.put("gdescription",des);
            values.put("name",name);
            //values.put("time",time);
            values.put("gcode",code);
            db.insert("Geomorelines"+pposition,null,values);
        }
    }

    /**
     * 点集合排序
     * @param collection
     * @return
     */
    private PointCollection OrderPoint(PointCollection collection){
        double plusX = 0, plusY = 0;
        for(int i  = 0;i < collection.size();i++){
            plusX += collection.get(i).getX();
            plusY += collection.get(i).getY();
        }
        Point p = new Point(plusX / collection.size(),plusY / collection.size());
        HashMap<Integer,ArrayList<Object>> mapAll = new HashMap<>();
        for(int i = 0; i < collection.size();i++){
            ArrayList<Object> objList = new ArrayList<>();
            objList.add(collection.get(i));
            objList.add(Degree.getAngle1(p.getX(),p.getY(),collection.get(i).getX(),collection.get(i).getY()));
            mapAll.put(i,objList);
        }
        //排序
        ArrayList<Object> temp = new ArrayList<>();
        int size = mapAll.size();
        for(int i = 0;i<size-1;i++){
            for (int j = 0;j<size - 1;i++){
                if (Double.parseDouble(mapAll.get(j).get(1).toString())>Double.parseDouble(mapAll.get(j+1).get(1).toString())){
                    temp = mapAll.get(j);
                    mapAll.put(j,mapAll.get(j+1));
                    mapAll.put(j+1,temp);
                }
            }
        }
        collection.clear();
        for(Integer integer:mapAll.keySet()){
            if(mapAll.get(integer).get(0) instanceof Point){
                collection.add((Point) mapAll.get(integer).get(0));
            }
        }
        return collection;
    }
    private void Drawsurface2(SimpleFillSymbol simpleFillSymbol, TextSymbol textSymbol, String text, String code,String name,String des){
        i1=1;
        final GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
//        list.add(graphicsOverlay);
        mMapView.getGraphicsOverlays().add(graphicsOverlay);
//        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
//        mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
//        pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
//        pictureMarkerSymbol4.loadAsync();
//        pictureMarkerSymbol4.addDoneLoadingListener(new Runnable() {
//            @Override
//            public void run() {
//                Graphic ph = new Graphic(mp, pictureMarkerSymbol4);
//                listph.add(ph);
//                graphicsOverlay.getGraphics().add(listph.get(listph.size() - 1));
//            }
//        });
//        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
//        seekack.add(mp.getX(),mp.getY(),mp.getZ());
//        centerpoint3.add(mp.getX(), mp.getY(), mp.getZ());
        if (centerpoint3.size() >= 3) {
            centerpoint3 = OrderPoint(centerpoint3);
            Polygon polygon=new Polygon(centerpoint3,SpatialReferences.getWgs84());
            Graphic fill = new Graphic(polygon, simpleFillSymbol);
            listfill.add(fill);
            graphicsOverlay.getGraphics().add(listfill.get(listfill.size() - 1));
            Graphic ts = new Graphic(polygon, textSymbol);
            listtext2.add(ts);
            graphicsOverlay.getGraphics().add(listtext2.get(listtext2.size() - 1));
            }
    }
    /**
     * 在图层添加面
     * @param simpleFillSymbol
     * @param textSymbol
     * @param text
     * @param code
     */
    private void Drawsurface(SimpleFillSymbol simpleFillSymbol, TextSymbol textSymbol, String text, String code,String name,String des){
        i1=1;
        final GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        list.add(graphicsOverlay);
        mMapView.getGraphicsOverlays().add(graphicsOverlay);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
        pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
        pictureMarkerSymbol4.loadAsync();
        pictureMarkerSymbol4.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol4);
                listph.add(ph);
                graphicsOverlay.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
        seekack.add(mp.getX(),mp.getY(),mp.getZ());
        centerpoint3.add(mp.getX(), mp.getY(), mp.getZ());
        if (centerpoint3.size() >= 3) {
            Polygon polygon=new Polygon(centerpoint3,SpatialReferences.getWgs84());
            Graphic fill = new Graphic(polygon, simpleFillSymbol);
            listfill.add(fill);
            graphicsOverlay.getGraphics().add(listfill.get(listfill.size() - 1));
            Graphic ts = new Graphic(polygon, textSymbol);
            listtext2.add(ts);
            graphicsOverlay.getGraphics().add(listtext2.get(listtext2.size() - 1));
            if (centerpoint3.size() > 3){
                mMapView.getGraphicsOverlays().remove(list.get(list.size()-2));
            }
            if (sublistsurface.size()==0){
                sublistsurface.add(String.valueOf(centerpoint3.get(0).getX()));
            }
                sublistsurface.add(String.valueOf(centerpoint3.get(centerpoint3.size()-2).getX()));
                surfacela.add(String.valueOf(centerpoint3.get(centerpoint3.size()-1).getX()));
                surfaceln.add(String.valueOf(centerpoint3.get(centerpoint3.size()-1).getY()));
            ContentValues values = new ContentValues();
            values.put("gla",StringUtils.join(surfacela,","));
            values.put("gln",StringUtils.join(surfaceln,","));
            db.update("Geosurface"+pposition,values,"gla = ?",new String[] {StringUtils.join(sublistsurface,",")});
            SurFace surFace = new SurFace();
            surFace.setListla(Arrays.asList(StringUtils.join(surfacela,",").split(",")));
            surFace.setListln(Arrays.asList(StringUtils.join(surfaceln,",").split(",")));
            surFace.setClassification(text);
            surFace.setCode(code);
            if (centerpoint3.size()>3){
                surfacelist.remove(surfacelist.size()-1);
            }
            surfacelist.add(surFace);
            centerpoint.clear();
        }
        if (centerpoint3.size()==2) {
            surfacela.add(String.valueOf(centerpoint3.get(0).getX()));
            surfacela.add(String.valueOf(centerpoint3.get(1).getX()));
            surfaceln.add(String.valueOf(centerpoint3.get(0).getY()));
            surfaceln.add(String.valueOf(centerpoint3.get(1).getY()));

            //获得当前时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            //String time=simpleDateFormat.format(date);
            ContentValues values=new ContentValues();
            values.put("gclassification",text);
            values.put("name",name);
            values.put("gdescription",des);
            values.put("gla",StringUtils.join(surfacela,","));
            values.put("gln",StringUtils.join(surfaceln,","));
            //values.put("time",time);
            values.put("gcode",code);
            db.insert("Geosurface"+pposition,null,values);
        }
    }
    public static boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
    /**
     * 地图比例尺监听
     */
    protected class MapchangeListener implements MapScaleChangedListener {
        @Override
        public void mapScaleChanged(MapScaleChangedEvent mapScaleChangedEvent) {
            try{
                android.graphics.Point sp = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                Point smp= (Point) GeometryEngine.project(mMapView.screenToLocation(sp), SpatialReference.create(4326));
                android.graphics.Point sp1=new android.graphics.Point(mMapView.getWidth()/2+180,mMapView.getHeight()/2);
                android.graphics.Point sp2=new android.graphics.Point(mMapView.getWidth()/2,mMapView.getHeight()/2+180);
                Point smp1= (Point) GeometryEngine.project(mMapView.screenToLocation(sp1),SpatialReference.create(4326));
                Point smp2= (Point) GeometryEngine.project(mMapView.screenToLocation(sp2),SpatialReference.create(4326));
                PointCollection p1=new PointCollection(SpatialReferences.getWgs84());
                PointCollection p2=new PointCollection(SpatialReferences.getWgs84());
                p1.add(smp);
                p1.add(smp1);
                p2.add(smp);
                p2.add(smp2);
                Polyline polyline1 = new Polyline(p1, SpatialReferences.getWgs84());
                Polyline polyline2 = new Polyline(p2, SpatialReferences.getWgs84());
                double lengthPolyline1 = GeometryEngine.lengthGeodetic(polyline1, new LinearUnit(LinearUnitId.METERS), GeodeticCurveType.GEODESIC);
                double lengthPolyline2 = GeometryEngine.lengthGeodetic(polyline2, new LinearUnit(LinearUnitId.METERS), GeodeticCurveType.GEODESIC);
                String text1= DistanceUtils.dataformatY(lengthPolyline1);
                String text2=DistanceUtils.dataformatY(lengthPolyline2);
                scale1.setText(text1);
                scale2.setText(text2);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    //字符串截取方法
    public int getInt(String str){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        int num=Integer.parseInt(m.replaceAll("").trim());
        return num;
    }
    /**
     * 判断字符串中是否包含数字
     */
    public static boolean isContainNumber(String company) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(company);
        if (m.find()) {
            return true;
        }
        return false;
    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "dizhishidai.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

    }
    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return detail;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    i1 = 1;
                    String textp="";
                    if (set1.contains("-")){
                        int index=set1.indexOf("-");
                        textp=set1.substring(0,index);
                    }else {
                        textp=set1;
                    }
                    p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                    mp = (Point) GeometryEngine.project(mMapView.screenToLocation(p), SpatialReference.create(4326));
                    graphicsOverlay_2 = new GraphicsOverlay();
                    mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
                    BitmapDrawable b = (BitmapDrawable) getResources().getDrawable(R.mipmap.camera2);
                    TextSymbol t = new TextSymbol(12f, textp, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                    centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
                    seekack.add(mp.getX(), mp.getY(), mp.getZ());
                    final PictureMarkerSymbol pictureMarkerSymbol5 = new PictureMarkerSymbol(b);
                    pictureMarkerSymbol5.loadAsync();
                    pictureMarkerSymbol5.addDoneLoadingListener(new Runnable() {
                        @Override
                        public void run() {
                            Graphic ph = new Graphic(mp, pictureMarkerSymbol5);
                            listph.add(ph);
                            graphicsOverlay_2.getGraphics().add(listph.get(listph.size() - 1));
                        }
                    });
                    graphicsOverlay_2.getGraphics().add(new Graphic(mp, t));
                    //获得当前时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    String time = simpleDateFormat.format(date);

                    ContentValues values = new ContentValues();
                    values.put("name",set1);
                    values.put("la", String.valueOf(mp.getX()));
                    values.put("ln", String.valueOf(mp.getY()));
                    values.put("high", String.valueOf(mp.getZ()));
                    values.put("time", time);
                    values.put("gclassification", "p");
                    values.put("gdescription",set2);
                    values.put("gcode", "C7");
                    db.insert("Geophotopoints" + pposition, null, values);
                    values.clear();

                    LitepalPoints litepalPoints = new LitepalPoints();
                    litepalPoints.setLa(String.valueOf(mp.getX()));
                    litepalPoints.setName(set1);
                    litepalPoints.setLn(String.valueOf(mp.getY()));
                    litepalPoints.setHigh(String.valueOf(mp.getZ()));
                    litepalPoints.setClassification("p");
                    litepalPoints.setDatetime(time);
                    litepalPoints.setCode("C7");
                    pointlist.add(litepalPoints);
                }
                break;
            case 10001:
                if (resultCode == 10001){
                    String str1 = data.getStringExtra("st1");
                    String str2 = data.getStringExtra("st2");
                    String str3 = data.getStringExtra("st3");
                    String str4 = data.getStringExtra("st4");
                    BitmapDrawable b1= (BitmapDrawable) getResources().getDrawable(R.mipmap.didiaodian);
                    TextSymbol ts1 = new TextSymbol(12f, str3, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.TOP);
                    DrawPoint(b1,ts1,str3,str4,"722d",str1,str2);
                }
                break;
            case 10002:
                if (resultCode == 10002){
                    BitmapDrawable b= (BitmapDrawable) getResources().getDrawable(R.mipmap.didiaodian);
                    TextSymbol ts = new TextSymbol(12f, data.getStringExtra("name"), Color.GREEN, TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.TOP);
                    DrawDCYXPoint(b,ts,data.getStringExtra("name"),data.getStringExtra("dznd"),data.getStringExtra("ytmc"),data.getStringExtra("cylx"),
                            "722d",data.getStringExtra("fhcd"),data.getStringExtra("cz"),data.getStringExtra("jl"),data.getStringExtra("ms"));
                }
                break;
            case 10003:
                if (resultCode == 10003){
                    BitmapDrawable b= (BitmapDrawable) getResources().getDrawable(R.mipmap.didiaodian);
                    TextSymbol ts = new TextSymbol(12f, data.getStringExtra("name"), Color.GREEN, TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.TOP);
                    DrawSWDZPoint(b,ts,data.getStringExtra("name"),data.getStringExtra("sllx"),data.getStringExtra("smkd"),data.getStringExtra("ss"),
                            "722d",data.getStringExtra("ls"),data.getStringExtra("ll"),data.getStringExtra("sz"),data.getStringExtra("ms"));
                }
                break;
            case 10004:
                if (resultCode == 10004){
                    BitmapDrawable b= (BitmapDrawable) getResources().getDrawable(R.mipmap.didiaodian);
                    TextSymbol ts = new TextSymbol(12f, data.getStringExtra("name"), Color.GREEN, TextSymbol.HorizontalAlignment.CENTER,TextSymbol.VerticalAlignment.TOP);
                    DrawGZWDPoint(b,ts,data.getStringExtra("name"),data.getStringExtra("lx"),"722d",data.getStringExtra("ms"));
                }
            case 10005:
                if(resultCode == 10005){
                    bxzz.setText(data.getStringExtra("lx"));
                    dzgz_des = data.getStringExtra("ms");
                    dzgz_name = data.getStringExtra("name");
                }
            case 10006:
                if(resultCode == 10006){
                    ht.setText(data.getStringExtra("lx"));
                    tsxt_des = data.getStringExtra("ms");
                    tsxt_name = data.getStringExtra("name");
                }
            case 10007:
                if(resultCode == 10007){
                    yr.setText(data.getStringExtra("lx"));
                    bldz_des = data.getStringExtra("ms");
                    bldz_name = data.getStringExtra("name");
                }
        }
    }
    public class LocalReceiver4 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if (action.equals("com.delatedatas.broadcast")){
                //Cursor cursor1 = db.query("Geopoints"+pposition, null, null, null, null, null, null);
                Cursor cursor2 = db.query("Geomorelines"+pposition, null, null, null, null, null, null);
                Cursor cursor3 = db.query("Geosurface"+pposition, null, null, null, null, null, null);
                try {
                    pointlist.clear();
                    linelist.clear();
                    surfacelist.clear();
                    //pointlist=geoDataPointActivity.getData(pointlist,cursor1);
                    linelist=geoDataLineActivity.getData(linelist,cursor2);
                    surfacelist=geoDataSurfaceActivity.getData(surfacelist,cursor3);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static class ShowAllReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ali1 = true;
        }
    }
    public static class ShowPointReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ali2 = true;
        }
    }
    public static class ShowLineReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ali3 = true;
        }
    }
    public static class ShowSurfaceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ali4 = true;
        }
    }
}
