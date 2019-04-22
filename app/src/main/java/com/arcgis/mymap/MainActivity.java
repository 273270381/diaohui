package com.arcgis.mymap;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arcgis.mymap.adapter.FloorAdapter;
import com.arcgis.mymap.contacts.ContactDB;
import com.arcgis.mymap.contacts.Lines;
import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.utils.DataManagerActivity;
import com.arcgis.mymap.utils.Degree;
import com.arcgis.mymap.utils.DisplayUtil;
import com.arcgis.mymap.utils.DistanceUtils;
import com.arcgis.mymap.utils.GetTable;
import com.arcgis.mymap.utils.HscaleView;
import com.arcgis.mymap.utils.LogUtils;
import com.arcgis.mymap.utils.ScaleView;
import com.arcgis.mymap.utils.ShowPointUtils;
import com.arcgis.mymap.utils.ToastNotRepeat;
import com.esri.arcgisruntime.data.TileCache;
import com.esri.arcgisruntime.geometry.GeodeticCurveType;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.LinearUnit;
import com.esri.arcgisruntime.geometry.LinearUnitId;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.BackgroundGrid;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedEvent;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedListener;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;
import com.esri.arcgisruntime.util.ListenableList;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ipaulpro.afilechooser.FileChooserActivity;
import com.ipaulpro.afilechooser.utils.FileUtils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.arcgis.mymap.utils.DistanceUtils.UseList;
public class MainActivity extends Activity{
    static MapView mMapView;
    ArcGISMap mMap;
    TextView xtx1,xtx2,xtx3,xtx4,xtx5,xtx6,xtx7,dtx1,dtx2,dtx3,dtx4,dtx5,FY,fy;
    ImageButton lymenu,measure,zoomIn,zoomOut,dw,qx,showpoint,clear,close,camera,next;
    EditText dt,et;
    View viewt1,viewt2,viewt3,viewt4,viewt5,viewt6,viewt7,view1,view2;
    ScaleView scale1;
    HscaleView scale2;
    RadioGroup radioGroup2;
    RadioButton add,save,backdelate;
    LinearLayout li1,li2;
    RelativeLayout relativeLayout;
    static Point mp;
    GridView gridView;
    ListView listView1,listView2,listView3,listView4,listView5,listView6,listView7;
    List<String> listnum=new ArrayList<>();
    List<String> listConum=new ArrayList<>();
    List<String> listfy=new ArrayList<>();
    List<Graphic> listline = new ArrayList<>();
    List<Graphic> listtext = new ArrayList<>();
    List<String> permissionList = new ArrayList<>();
    List<LitepalPoints> pointlist=new ArrayList<>();
    List<MoreLines> linelist=new ArrayList<>();
    List<GraphicsOverlay> list2=new ArrayList<>();
    List<String> sublistline = new ArrayList<>();
    List<String> linela = new ArrayList<>();
    List<String> lineln = new ArrayList<>();
    public String name="";
    public String text="";
    public  BitmapDrawable bitmapDrawable4;
    public  PictureMarkerSymbol pictureMarkerSymbol4;
    private List<NewProject> projects=new ArrayList<>();
    private String num,str,projectname;
    private String fangyan;
    private ArrayAdapter<String> adapter1,adapter2,adapter3;
    public SQLiteDatabase db;
    public MyDatabaseHelper dbHelper;
    public NewDataActivity newDataActivity;
    public DataLineActivity dataLineActivity;
    public static List<Graphic> listph = new ArrayList<>();
    public static boolean ali1=false;
    public static boolean ali2=false;
    public static boolean ali3=false;
    public static int ap=0;
    public static String  atotitle=null;
    public String pposition;
    ToastNotRepeat toastNotRepeat;//自定义Toast
    public MapchangeListener mapchangeListener;
    public GraphicsOverlay overlay;
    public static GraphicsOverlay graphicsOverlay_2;
    public ExecutorService threadPool,threadPool2;
    public android.graphics.Point p;
    public Point dwmp;
    public List<android.graphics.Point> listP;
    public BitmapDrawable bitmapDrawablePoint;
    public PointCollection centerpoint,centerpoint1,seekack;
    public SimpleLineSymbol simpleLineSymbolX;
    public LocationDisplay locationDisplay;
    private int requestCode = 2;
    public static final int TAKE_PHOTO = 1;
    public final static int CANSHU = 2;
    public final static int CANSHUL= 3;
    Alert_dialogActivity alert_dialogActivity;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private List<String> popdy1=new ArrayList<>();
    private List<String> popContents = new ArrayList<>();
    private List<String> popContent2 = new ArrayList<>();
    private List<String> popNum1=new ArrayList<>();
    private List<String> popNum2=new ArrayList<>();
    private List<String> popNum3=new ArrayList<>();
    private List<String> popNum4=new ArrayList<>();
    private List<String> popConum1=new ArrayList<>();
    private String[] titles= ContactDB.getStrings();
    private Integer[] imager=ContactDB.getInters();
    private String[] codes=ContactDB.getCodes();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private EditText fydt;
    private int i1=1;
    private LocalReceiver4 receiver4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intview();
        threadPool = Executors.newCachedThreadPool();
        threadPool2 = Executors.newCachedThreadPool();
        permission();
        setBackground();
        setSpiners();
        alert_dialogActivity=new Alert_dialogActivity();
        listP=new ArrayList<>();
        centerpoint = new PointCollection(SpatialReferences.getWgs84());
        centerpoint1 = new PointCollection(SpatialReferences.getWgs84());
        seekack = new PointCollection(SpatialReferences.getWgs84());
        overlay = new GraphicsOverlay();
        Clicklistener listener = new Clicklistener();
        mapchangeListener=new MapchangeListener();
        mMapView.addMapScaleChangedListener(mapchangeListener);
        lymenu.setOnClickListener(listener);
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
        FY.setOnClickListener(listener);
        xtx1.setOnClickListener(listener);
        xtx2.setOnClickListener(listener);
        xtx3.setOnClickListener(listener);
        xtx4.setOnClickListener(listener);
        xtx5.setOnClickListener(listener);
        xtx6.setOnClickListener(listener);
        xtx7.setOnClickListener(listener);
        dtx1.setOnClickListener(listener);
        dtx2.setOnClickListener(listener);
        dtx3.setOnClickListener(listener);
        dtx4.setOnClickListener(listener);
        dtx5.setOnClickListener(listener);
        LongClickListener longClickListener=new LongClickListener();
        dtx2.setOnLongClickListener(longClickListener);
        dtx3.setOnLongClickListener(longClickListener);
        dtx4.setOnLongClickListener(longClickListener);
        dtx5.setOnLongClickListener(longClickListener);
        xtx6.setOnLongClickListener(longClickListener);
        xtx7.setOnLongClickListener(longClickListener);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setAinameit();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
    /**
     * 根据ali1,ali2,ali3传入的boolean值，显示不同的元素
     */
    private void setAinameit() {
            Intent intent=getIntent();
            if (intent!=null){
                if (ali3){
                    List<LitepalPoints> list=new ArrayList<>();
                    list= (List<LitepalPoints>) getIntent().getSerializableExtra("list");
                    List<MoreLines> list1=new ArrayList<>();
                    list1= (List<MoreLines>) getIntent().getSerializableExtra("list1");
                    if (list==null){
                        list=new ArrayList<>();
                    }
                    if (list1==null){
                        list1=new ArrayList<>();
                    }
                    ShowPointUtils.ShowAllPointUtils(MainActivity.this,threadPool2,list,list1,titles,imager,mMapView);
                    ali3=false;
                }
                if (ali2){
                    double x=Double.parseDouble(intent.getStringExtra("jingdu"));
                    double y=Double.parseDouble(intent.getStringExtra("weidu"));
                    double z=Double.parseDouble(intent.getStringExtra("gaodu"));
                    String classification=intent.getStringExtra("classification");
                    if (Arrays.asList(titles).contains(classification)){
                        int index = Alert_dialogActivity.printArray(titles,classification);
                        bitmapDrawablePoint = (BitmapDrawable) getResources().getDrawable(imager[index]);
                    }else if (classification.indexOf("檐")!=-1){
                        bitmapDrawablePoint=(BitmapDrawable) getResources().getDrawable(R.mipmap.p35);
                    }else if (classification.indexOf("混")!=-1||classification.indexOf("砖")!=-1||classification.indexOf("简")!=-1||classification.indexOf("砼")!=-1){
                        bitmapDrawablePoint=(BitmapDrawable) getResources().getDrawable(R.mipmap.p36);
                    }else if (classification.indexOf("p")!=-1){
                        bitmapDrawablePoint= (BitmapDrawable) getResources().getDrawable(R.mipmap.camera2);
                    } else {
                        bitmapDrawablePoint=(BitmapDrawable) getResources().getDrawable(R.mipmap.p25);
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
                    TextSymbol textSymbolh = new TextSymbol(12f, classification, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                    graphicsOverlay.getGraphics().add(new Graphic(p,textSymbolh));
                    ali2=false;
                }
                if (ali1){
                    List<String> xla = intent.getStringArrayListExtra("xla");
                    List<String> xln = intent.getStringArrayListExtra("xln");
                    String clas=intent.getStringExtra("clas");
                    final GraphicsOverlay graphicsOverlay=new GraphicsOverlay();
                    mMapView.getGraphicsOverlays().add(graphicsOverlay);
                    BitmapDrawable bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.mipmap.point1);
                    final PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(bitmapDrawable);
                    final PointCollection pointCollection=new PointCollection(SpatialReferences.getWgs84());
                    for (int a = 0 ; a<= xla.size()-1 ; a++){
                        pointCollection.add(Double.parseDouble(xla.get(a)),Double.parseDouble(xln.get(a)));
                    }
                    pictureMarkerSymbol.loadAsync();
                    pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
                        @Override
                        public void run() {
                            for (int a = 0;a<=pointCollection.size()-1;a++){
                                Graphic ph = new Graphic(pointCollection.get(a),pictureMarkerSymbol);
                                graphicsOverlay.getGraphics().add(ph);
                            }
                        }
                    });
                   /* pointCollection.add(xla,xln);
                    pointCollection.add(yla,yln);
                    pictureMarkerSymbol.loadAsync();
                    pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
                        @Override
                        public void run() {
                            Graphic ph = new Graphic(pointCollection.get(0), pictureMarkerSymbol);
                            Graphic ph1 = new Graphic(pointCollection.get(1), pictureMarkerSymbol);
                            graphicsOverlay.getGraphics().add(ph);
                            graphicsOverlay.getGraphics().add(ph1);
                        }
                    });*/
                    if (clas.equals("10kv")||clas.equals("220v")||clas.equals("通讯")){
                        SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                        Polyline polyline=new Polyline(pointCollection,SpatialReferences.getWgs84());
                        Graphic line = new Graphic(polyline, lineSymbol);
                        graphicsOverlay.getGraphics().add(line);
                        TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                        graphicsOverlay.getGraphics().add(new Graphic(polyline,textSymbolh2));
                    }else {
                        SimpleLineSymbol lineSymbol= new SimpleLineSymbol(SimpleLineSymbol.Style.DASH, Color.YELLOW, 2);
                        Polyline polyline=new Polyline(pointCollection,SpatialReferences.getWgs84());
                        Graphic line = new Graphic(polyline, lineSymbol);
                        graphicsOverlay.getGraphics().add(line);
                        TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                        graphicsOverlay.getGraphics().add(new Graphic(polyline,textSymbolh2));
                    }
                }
                ali1=false;
            }
    }
    //添加房檐Spiner
    private void setSpiners() {
        final PopupWindow popupWindow3=new PopupWindow(viewt3);
        popupWindow3.setHeight(Math.round(DisplayUtil.sp2px(this,288.0f)));
        popupWindow3.setWidth(Math.round(DisplayUtil.sp2px(this,38.0f)));
        popupWindow3.setBackgroundDrawable(new BitmapDrawable());
        popupWindow3.setOutsideTouchable(true);
        popupWindow3.setFocusable(true);
        for (int i=1;i<=9;i++){
            popConum1.add("0."+String.valueOf(i));
        }
        popConum1.add("...");
        adapter3=new ArrayAdapter<>(this,R.layout.list_content3,popConum1);
        listView3.setAdapter(adapter3);
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position!=popConum1.size()-1){
                    fy.setText(popConum1.get(position));
                    popupWindow3.dismiss();
                }else {
                    LinearLayout linearLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.fangyan,null);
                    AlertDialog dialog=new AlertDialog.Builder(MainActivity.this)
                            .setTitle("房檐：")
                            .setView(linearLayout)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String dt=fydt.getText().toString();
                                    if (TextUtils.isEmpty(dt)){
                                        fy.setText(fangyan);
                                    }else {
                                        fy.setText(dt);
                                        if (!popConum1.contains(dt)){
                                            popConum1.add(popConum1.size()-1,dt);
                                            adapter3.notifyDataSetChanged();
                                        }
                                    }
                                }
                            }).show();
                    Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    btnpositive.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    fydt = (EditText) dialog.findViewById(R.id.fytext);
                    GridView gridView2= (GridView) dialog.findViewById(R.id.gridView2);
                    final FloorAdapter adapter=new FloorAdapter(listConum,MainActivity.this);
                    gridView2.setAdapter(adapter);
                    popupWindow3.dismiss();
                    gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            fangyan=listConum.get(position);
                            adapter.setSelectItem(position);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        fy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow3.showAsDropDown(v);
            }
        });
    }
    /**
     * 权限管理
     */
    private void permission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
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
                boolean permissionCheck1= ContextCompat.checkSelfPermission(MainActivity.this,PERMISSIONS_STORAGE[0])==
                        PackageManager.PERMISSION_GRANTED;
                boolean permissionCheck2= ContextCompat.checkSelfPermission(MainActivity.this,PERMISSIONS_STORAGE[1])==
                        PackageManager.PERMISSION_GRANTED;
                if (!(permissionCheck1 && permissionCheck2)) {
                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, requestCode);
                } else {
                    String message = String.format("Error in DataSourceStatusChangedListener: %s", dataSourceStatusChangedEvent
                            .getSource().getLocationDataSource().getError().getMessage());
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    /**
     * 初始化控件
     */
    private void intview() {
        xtx1= (TextView) findViewById(R.id.xtx1);
        xtx2= (TextView) findViewById(R.id.xtx2);
        xtx3= (TextView) findViewById(R.id.xtx3);
        xtx4= (TextView) findViewById(R.id.xtx4);
        xtx5= (TextView) findViewById(R.id.xtx5);
        xtx6= (TextView) findViewById(R.id.xtx6);
        xtx7= (TextView) findViewById(R.id.xtx7);
        dtx1= (TextView) findViewById(R.id.dtx1);
        dtx2= (TextView) findViewById(R.id.dtx2);
        dtx3= (TextView) findViewById(R.id.dtx3);
        dtx4= (TextView) findViewById(R.id.dtx4);
        dtx5= (TextView) findViewById(R.id.dtx5);
        bitmapDrawable4 = (BitmapDrawable) getResources().getDrawable(R.mipmap.point1);
        pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
        viewt1 =LayoutInflater.from(this).inflate(R.layout.pop_list,null);
        viewt2 =LayoutInflater.from(this).inflate(R.layout.pop_list2,null);
        viewt3 =LayoutInflater.from(this).inflate(R.layout.pop_list3,null);
        viewt4 =LayoutInflater.from(this).inflate(R.layout.pop_list4,null);
        viewt5 =LayoutInflater.from(this).inflate(R.layout.pop_list5,null);
        viewt6 =LayoutInflater.from(this).inflate(R.layout.pop_list5,null);
        viewt7 =LayoutInflater.from(this).inflate(R.layout.pop_list5,null);
        listView1= (ListView) viewt1.findViewById(R.id.pop_lv);
        listView2= (ListView) viewt2.findViewById(R.id.pop_lv2);
        listView3= (ListView) viewt3.findViewById(R.id.pop_lv3);
        listView4= (ListView) viewt4.findViewById(R.id.pop_lv4);
        listView5= (ListView) viewt5.findViewById(R.id.pop_lv5);
        listView6= (ListView) viewt6.findViewById(R.id.pop_lv5);
        listView7= (ListView) viewt7.findViewById(R.id.pop_lv5);
        FY= (TextView) findViewById(R.id.fy);
        fy= (TextView) findViewById(R.id.num_with_yan);
        showpoint= (ImageButton) findViewById(R.id.showpoint);
        mMapView = (MapView) findViewById(R.id.mapView);
        lymenu = (ImageButton) findViewById(R.id.menu);
        measure = (ImageButton) findViewById(R.id.measure);
        camera= (ImageButton) findViewById(R.id.camera);
        next= (ImageButton) findViewById(R.id.next);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        scale1= (ScaleView) findViewById(R.id.scale1);
        scale2= (HscaleView) findViewById(R.id.scale2);
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        li1 = (LinearLayout) findViewById(R.id.li1);
        li2 = (LinearLayout) findViewById(R.id.li2);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
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
        newDataActivity=new NewDataActivity();
        dataLineActivity=new DataLineActivity();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("com.delatedatas.broadcast");
        receiver4=new LocalReceiver4();
        registerReceiver(receiver4,intentFilter);
        //获取表位置
        GetTable getTable=new GetTable();
        pposition=getTable.getTableposition(MainActivity.this,db,dbHelper,projects);
        int sposition=Integer.parseInt(getTable.getPpposition(MainActivity.this,db,dbHelper));
        str=projects.get(sposition).getStrname();
        projectname=projects.get(sposition).getProjectname();
        for (int i=8;i<=40;i++){
            listnum.add(String.valueOf(i));
        }
        for (int i=0;i<=9;i++){
            listConum.add("1."+String.valueOf(i));
        }
        for (int i=0;i<=9;i++){
            listfy.add("飘楼1."+String.valueOf(i));
        }
        for (int i=1;i<=7;i++){
            popNum1.add(String.valueOf(i));
            popNum2.add(String.valueOf(i));
            popNum3.add(String.valueOf(i));
            popNum4.add(String.valueOf(i));
        }
        popNum1.add("...");
        popNum2.add("...");
        popNum3.add("...");
        popNum4.add("...");
        popdy1.add("飘楼0.5");
        popdy1.add("飘楼0.6");
        popdy1.add("飘楼0.7");
        popdy1.add("飘楼0.8");
        popdy1.add("飘楼0.9");
        popdy1.add("...");
        popContents.add("混");
        popContents.add("砼");
        popContents.add("砖");
        popContents.add("简");
        popContents.add("棚");
        popContents.add("");
        popContents.add("...");
        popContent2.add("单渠");
        popContent2.add("双渠");
        popContent2.add("单沟");
        popContent2.add("双沟");
        popContent2.add("...");
        Cursor cursor1 = db.query("Newpoints"+pposition, null, null, null, null, null, null);
        Cursor cursor2 = db.query("Newlines"+pposition, null, null, null, null, null, null);
        try {
            pointlist=newDataActivity.getData(pointlist,cursor1);
            linelist=dataLineActivity.getData(linelist,cursor2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver4);
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
        super.onStop();
    }
    /**
     * 为各个控件写长按监听
     */
    private class LongClickListener implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            switch(v.getId()){
                case R.id.dtx2:
                    final PopupWindow popupWindow7=new PopupWindow(viewt2);
                    ShowAlertPopupWindows(popupWindow7,dtx2,listView2,popNum1,listnum);
                    popupWindow7.showAsDropDown(v);
                break;
                case R.id.dtx3:
                    final PopupWindow popupWindow8=new PopupWindow(viewt3);
                    ShowAlertPopupWindows(popupWindow8,dtx3,listView3,popNum2,listnum);
                    popupWindow8.showAsDropDown(v);
                    break;
                case R.id.dtx4:
                    final PopupWindow popupWindow9=new PopupWindow(viewt4);
                    ShowAlertPopupWindows(popupWindow9,dtx4,listView4,popNum3,listnum);
                    popupWindow9.showAsDropDown(v);
                    break;
                case R.id.dtx5:
                    final PopupWindow popupWindow10=new PopupWindow(viewt5);
                    ShowAlertPopupWindows(popupWindow10,dtx5,listView5,popNum4,listnum);
                    popupWindow10.showAsDropDown(v);
                    break;
                case R.id.xtx6:
                    final PopupWindow popupWindow11=new PopupWindow(viewt6);
                    ShowAlertPopupWindows(popupWindow11,xtx6,listView6,popdy1,listfy);
                    popupWindow11.showAsDropDown(v);
                    break;
                case R.id.xtx7:
                    final PopupWindow popupWindow12=new PopupWindow(viewt7);
                    ShowPopupWindows(popupWindow12,listView7,xtx7,popContent2);
                    popupWindow12.showAsDropDown(v);
                    break;
            }
            return true;
        }
    }
    /**
     * 为各个控件写点击监听
     */
    private class Clicklistener implements View.OnClickListener {
        @SuppressLint("HandlerLeak")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.camera:
                    p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                    mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
                    int amount=0;
                    String la = "";
                    String cla="";
                    File outputImage = null;
                    Cursor c=db.rawQuery("select*from Photopoints"+pposition,null);
                    amount=c.getCount();
                    if (amount==0){
                        text="p1";
                    }else {
                        Cursor c1=db.query("Photopoints"+pposition,null,null,null,null,null,null);
                        if (c1.moveToLast()){
                            la=c1.getString(c1.getColumnIndex("la"));
                            cla=c1.getString(c1.getColumnIndex("classification"));
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
                        File file=new File(Environment.getExternalStorageDirectory()+"/MyMap/航测/Pictrue/"+projectname+"/"+text);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        String path=Environment.getExternalStorageDirectory()+"/MyMap/航测/Pictrue/"+projectname+"/"+text+"/"+text+".jpg";
                        outputImage=new File(path);
                    }else {
                        int index=text.indexOf("-");
                        String text1=text.substring(0,index);
                        File file=new File(Environment.getExternalStorageDirectory()+"/MyMap/航测/Pictrue/"+projectname+"/"+text1);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        String path=Environment.getExternalStorageDirectory()+"/MyMap/航测/Pictrue/"+projectname+"/"+text1+"/"+text+".jpg";
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
                        intent.putExtra(MediaStore.EXTRA_OUTPUT , FileProvider.getUriForFile(MainActivity.this,"mymap.fileprovider",outputImage));
                    }else {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
                    }
                    startActivityForResult(intent, TAKE_PHOTO);
                    break;
                case R.id.xtx1:
                    String text1 =xtx1.getText().toString();
                    SimpleLineSymbol s1 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                    TextSymbol t1 = new TextSymbol(12f, text1, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                    DrawLine(s1,t1,text1,"D0");
                    break;
                case R.id.xtx2:
                    String text2=xtx2.getText().toString();
                    SimpleLineSymbol s2 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                    TextSymbol t2 = new TextSymbol(12f, text2, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                    DrawLine(s2,t2,text2,"D2");
                    break;
                case R.id.xtx3:
                    String text3=xtx3.getText().toString();
                    SimpleLineSymbol s3 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                    TextSymbol t3 = new TextSymbol(12f, text3, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                    DrawLine(s3,t3,text3,"D2");
                    break;
                case R.id.xtx4:
                    String text4=xtx4.getText().toString();
                    SimpleLineSymbol s4 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2);
                    TextSymbol t4 = new TextSymbol(12f, text4, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                    DrawLine(s4,t4,text4,"D3");
                    break;
                case R.id.xtx5:
                    String text5=xtx5.getText().toString();
                    BitmapDrawable b1= (BitmapDrawable) getResources().getDrawable(R.mipmap.p25);
                    TextSymbol t5 = new TextSymbol(12f, text5, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                    DrawPoint(b1,t5,text5,"R","null");
                    break;
                case R.id.xtx6:
                    String text6=xtx6.getText().toString();
                    SimpleLineSymbol s6 = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH, Color.YELLOW, 2);
                    TextSymbol t6 = new TextSymbol(12f, text6, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                    DrawLine(s6,t6,text6,"X0");
                    break;
                case R.id.xtx7:
                    String text7=xtx7.getText().toString();
                    SimpleLineSymbol s7 = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH_DOT, Color.CYAN, 2);
                    TextSymbol t7 = new TextSymbol(12f, text7, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                    DrawLine(s7,t7,text7,"U6");
                    break;
                case R.id.dtx1:
                    final PopupWindow popupWindow6=new PopupWindow(viewt1);
                    ShowPopupWindows(popupWindow6,listView1,dtx1,popContents);
                    popupWindow6.showAsDropDown(v);
                    break;
                case R.id.dtx2:
                    String stext2=dtx1.getText().toString()+dtx2.getText().toString();
                    if (dtx1.getText().toString().equals("砖")){
                        name="Z";
                    }else if (dtx1.getText().toString().equals("混")){
                        name="H";
                    }else if (dtx1.getText().toString().equals("砼")){
                        name="T";
                    }else if (dtx1.getText().toString().equals("简")){
                        name="J";
                    }else {
                        name="P";
                    }
                    BitmapDrawable b2= (BitmapDrawable) getResources().getDrawable(R.mipmap.p36);
                    TextSymbol st2 = new TextSymbol(12f, stext2, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                    DrawPoint(b2,st2,stext2,name,"null");
                    break;
                case R.id.dtx3:
                    String stext3=dtx1.getText().toString()+dtx3.getText().toString();
                    if (dtx1.getText().toString().equals("砖")){
                        name="Z";
                    }else if (dtx1.getText().toString().equals("混")){
                        name="H";
                    }else if (dtx1.getText().toString().equals("砼")){
                        name="T";
                    }else if (dtx1.getText().toString().equals("简")){
                        name="J";
                    }else {
                        name="P";
                    }
                    BitmapDrawable b3= (BitmapDrawable) getResources().getDrawable(R.mipmap.p36);
                    TextSymbol st3 = new TextSymbol(12f, stext3, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                    DrawPoint(b3,st3,stext3,name,"null");
                    break;
                case R.id.dtx4:
                    String stext4=dtx1.getText().toString()+dtx4.getText().toString();
                    if (dtx1.getText().toString().equals("砖")){
                        name="Z";
                    }else if (dtx1.getText().toString().equals("混")){
                        name="H";
                    }else if (dtx1.getText().toString().equals("砼")){
                        name="T";
                    }else if (dtx1.getText().toString().equals("简")){
                        name="J";
                    }else {
                        name="P";
                    }
                    BitmapDrawable b4= (BitmapDrawable) getResources().getDrawable(R.mipmap.p36);
                    TextSymbol st4 = new TextSymbol(12f, stext4, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                    DrawPoint(b4,st4,stext4,name,"null");
                    break;
                case R.id.dtx5:
                    String stext5=dtx1.getText().toString()+dtx5.getText().toString();
                    if (dtx1.getText().toString().equals("砖")){
                        name="Z";
                    }else if (dtx1.getText().toString().equals("混")){
                        name="H";
                    }else if (dtx1.getText().toString().equals("砼")){
                        name="T";
                    }else if (dtx1.getText().toString().equals("简")){
                        name="J";
                    }else {
                        name="P";
                    }
                    BitmapDrawable b5= (BitmapDrawable) getResources().getDrawable(R.mipmap.p36);
                    TextSymbol st5 = new TextSymbol(12f, stext5, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                    DrawPoint(b5,st5,stext5,name,"null");
                    break;
                case R.id.fy:
                    i1=1;
                    graphicsOverlay_2 = new GraphicsOverlay();
                    mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
                    BitmapDrawable bitmapDrawable3;
                    p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                    mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
                    centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
                    seekack.add(mp.getX(),mp.getY(),mp.getZ());
                    bitmapDrawable3 = (BitmapDrawable) getResources().getDrawable(R.mipmap.fy);
                    final PictureMarkerSymbol pictureMarkerSymbol3 = new PictureMarkerSymbol(bitmapDrawable3);
                    pictureMarkerSymbol3.loadAsync();
                    pictureMarkerSymbol3.addDoneLoadingListener(new Runnable() {
                        @Override
                        public void run() {
                            Graphic ph = new Graphic(mp, pictureMarkerSymbol3);
                            listph.add(ph);
                            graphicsOverlay_2.getGraphics().add(listph.get(listph.size() - 1));
                        }
                    });
                    String text=fy.getText().toString();
                    TextSymbol textSymbol = new TextSymbol(12f, text, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                    graphicsOverlay_2.getGraphics().add(new Graphic(mp,textSymbol));
                    //获得当前时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date= new Date(System.currentTimeMillis());
                    String time=simpleDateFormat.format(date);
                    ContentValues values=new ContentValues();
                    values.put("name","FY");
                    values.put("la",String.valueOf(mp.getX()));
                    values.put("ln",String.valueOf(mp.getY()));
                    values.put("high",String.valueOf(mp.getZ()));
                    values.put("time",time);
                    values.put("classification",text+"房檐");
                    values.put("code","null");
                    db.insert("Newpoints"+pposition,null,values);
                    values.clear();
                    LitepalPoints litepalPoints=new LitepalPoints();
                    litepalPoints.setLa(String.valueOf(mp.getX()));
                    litepalPoints.setLn(String.valueOf(mp.getY()));
                    litepalPoints.setHigh(String.valueOf(mp.getZ()));
                    litepalPoints.setClassification(text+"房檐");
                    litepalPoints.setDatetime(time);
                    litepalPoints.setCode("null");
                    pointlist.add(litepalPoints);
                    break;
                case R.id.showpoint:
                    ShowPointUtils.ShowAllPointUtils(MainActivity.this,threadPool2,pointlist,linelist,titles,imager,mMapView);
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
                                        ToastNotRepeat.show(MainActivity.this,"已超出工作范围！");
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
                    Intent i=new Intent(MainActivity.this, DataManagerActivity.class);
                    startActivity(i);
                    break;
                case R.id.clear:
                    mMapView.getGraphicsOverlays().clear();
                    centerpoint.clear();
                    showpoint.setVisibility(View.VISIBLE);
                    clear.setVisibility(View.GONE);
                    list2.clear();
                    linela.clear();
                    lineln.clear();
                    sublistline.clear();
                    break;
                case R.id.menu:
                    Intent m=new Intent(MainActivity.this,MainMenuActivity.class);
                    startActivity(m);
                    break;
                case R.id.measure:
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    scale1.setVisibility(View.VISIBLE);
                    scale2.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.GONE);
                    radioGroup2.setVisibility(View.VISIBLE);
                    close.setVisibility(View.VISIBLE);
                    measure.setVisibility(View.GONE);
                    mMapView.getGraphicsOverlays().clear();
                    FY.setVisibility(View.VISIBLE);
                    fy.setVisibility(View.VISIBLE);
                    li1.setVisibility(View.VISIBLE);
                    li2.setVisibility(View.VISIBLE);
                    xtx5.setVisibility(View.VISIBLE);
                    xtx6.setVisibility(View.VISIBLE);
                    camera.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    break;
                case R.id.next:
                    centerpoint.clear();
                    list2.clear();
                    linela.clear();
                    lineln.clear();
                    sublistline.clear();
                    break;
                case R.id.close:
                    next.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    scale1.setVisibility(View.GONE);
                    scale2.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    radioGroup2.setVisibility(View.GONE);
                    close.setVisibility(View.GONE);
                    measure.setVisibility(View.VISIBLE);
                    mMapView.getGraphicsOverlays().clear();
                    centerpoint.clear();
                    linela.clear();
                    lineln.clear();
                    sublistline.clear();
                    FY.setVisibility(View.GONE);
                    fy.setVisibility(View.GONE);
                    li1.setVisibility(View.GONE);
                    li2.setVisibility(View.GONE);
                    xtx5.setVisibility(View.GONE);
                    xtx6.setVisibility(View.GONE);
                    camera.setVisibility(View.GONE);
                    break;
                case R.id.backdelate:
                    try{
                        mMapView.setViewpointCenterAsync(seekack.get(seekack.size()-i1));
                        i1++;
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    break;
                case R.id.add:
                    i1=1;
                    //画虚线
                    simpleLineSymbolX = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH, Color.BLUE, 2);
                    graphicsOverlay_2 = new GraphicsOverlay();
                    mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
                    p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                    mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
                    centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
                    seekack.add(mp.getX(),mp.getY(),mp.getZ());
                    if (mp==null){
                        toastNotRepeat.show(MainActivity.this,"请添加兴趣点");
                    }else {
                        //获得当前时间
                        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date3 = new Date(System.currentTimeMillis());
                        String time3=simpleDateFormat3.format(date3);
                        Intent intent2=new Intent(MainActivity.this,Alert_dialogActivity.class);
                        intent2.putExtra("jingdu",String.valueOf(mp.getX()));
                        intent2.putExtra("weidu",String.valueOf(mp.getY()));
                        intent2.putExtra("gaodu",String.valueOf(mp.getZ()));
                        intent2.putExtra("shijian",time3);
                        intent2.putExtra("xiabiao",atotitle);
                        startActivityForResult(intent2,0);
                    }
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
    //根据传入的参数，显示不同的PopupWindows
    private void ShowPopupWindows(final PopupWindow popupWindow,ListView listView1,final TextView tv, final List<String> pContents){
        popupWindow.setHeight(Math.round(DisplayUtil.sp2px(this,120.0f)));
        popupWindow.setWidth(Math.round(DisplayUtil.sp2px(this,34.0f)));
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        adapter1=new ArrayAdapter<>(this,R.layout.list_content,pContents);
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position!=pContents.size()-1){
                    tv.setText(pContents.get(position));
                    popupWindow.dismiss();
                }else {
                    LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.poplayout,null);
                    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("请输入：")
                            .setView(linearLayout)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String eting = et.getText().toString();
                                    tv.setText(eting);
                                    if (!pContents.contains(eting)){
                                        pContents.add(pContents.size()-1,eting);
                                        adapter1.notifyDataSetChanged();
                                    }
                                }
                            }).show();
                    et = (EditText) dialog.findViewById(R.id.et1);
                    Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    btnpositive.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative.setTextColor(getResources().getColor(R.color.color29));
                }
            }
        });
    }
    //根据传入的参数，显示不同的PopupWindows
    private void ShowAlertPopupWindows(final PopupWindow popupWindow, final TextView tv,ListView listView, final List<String> popNum1, final List<String> listnu){
        popupWindow.setHeight(Math.round(DisplayUtil.sp2px(this,160.0f)));
        popupWindow.setWidth(Math.round(DisplayUtil.sp2px(this,34.0f)));
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        adapter2=new ArrayAdapter<>(this,R.layout.list_content2,popNum1);
        listView.setAdapter(adapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position!=popNum1.size()-1){
                    tv.setText(popNum1.get(position));
                    popupWindow.dismiss();
                }else {
                    LinearLayout linearLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.louceng,null);
                    AlertDialog dialog=new AlertDialog.Builder(MainActivity.this)
                            .setTitle("楼层：")
                            .setView(linearLayout)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String sdt = dt.getText().toString();
                                    if (TextUtils.isEmpty(sdt)){
                                        tv.setText(num);
                                    }else {
                                        tv.setText(sdt);
                                        if (!popNum1.contains(sdt)){
                                            popNum1.add(popNum1.size()-1,sdt);
                                            adapter2.notifyDataSetChanged();
                                        }
                                    }
                                }
                            }).show();
                    dt= (EditText) dialog.findViewById(R.id.lctext);
                    gridView= (GridView) dialog.findViewById(R.id.gridView);
                    final FloorAdapter adapter=new FloorAdapter(listnu,MainActivity.this);
                    gridView.setAdapter(adapter);
                    popupWindow.dismiss();
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            num=listnu.get(position);
                            adapter.setSelectItem(position);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                    btnpositive.setTextColor(getResources().getColor(R.color.color29));
                    btnnegative.setTextColor(getResources().getColor(R.color.color29));
                }
            }
        });
    }
    /**
     * 在图层添加点元素
     * @param bitmapDrawable5
     * @param textSymbol
     * @param text
     * @param name
     * @param code
     */
    private void DrawPoint(BitmapDrawable bitmapDrawable5,TextSymbol textSymbol,String text,String name,String code){
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
        values.put("time",time);
        values.put("classification",text);
        values.put("code",code);
        db.insert("Newpoints"+pposition,null,values);
        values.clear();
        LitepalPoints litepalPoints=new LitepalPoints();
        litepalPoints.setLa(String.valueOf(mp.getX()));
        litepalPoints.setLn(String.valueOf(mp.getY()));
        litepalPoints.setHigh(String.valueOf(mp.getZ()));
        litepalPoints.setClassification(text);
        litepalPoints.setDatetime(time);
        litepalPoints.setCode(code);
        pointlist.add(litepalPoints);
    }
    /**
     * 在图层添加线元素
     * @param simpleLineSymbol
     * @param textSymbol
     * @param text
     * @param code
     */
    private void DrawLine(SimpleLineSymbol simpleLineSymbol,TextSymbol textSymbol,String text,String code){
        i1=1;
        graphicsOverlay_2 = new GraphicsOverlay();
        list2.add(graphicsOverlay_2);
        mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
        pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
        pictureMarkerSymbol4.loadAsync();
        pictureMarkerSymbol4.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol4);
                listph.add(ph);
                graphicsOverlay_2.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
        seekack.add(mp.getX(),mp.getY(),mp.getZ());
        centerpoint.add(mp.getX(), mp.getY(), mp.getZ());
        if (centerpoint.size() >= 2) {
            Polyline polyline = new Polyline(centerpoint, SpatialReferences.getWgs84());
            Graphic line = new Graphic(polyline, simpleLineSymbol);
            listline.add(line);
            graphicsOverlay_2.getGraphics().add(listline.get(listline.size() - 1));
            Graphic ts = new Graphic(polyline, textSymbol);
            listtext.add(ts);
            graphicsOverlay_2.getGraphics().add(listtext.get(listtext.size() - 1));
            if (centerpoint.size()>2){
                mMapView.getGraphicsOverlays().remove(list2.get(list2.size()-2));
            }
            sublistline.add(String.valueOf(centerpoint.get(centerpoint.size()-2).getX()));
            linela.add(String.valueOf(centerpoint.get(centerpoint.size()-1).getX()));
            lineln.add(String.valueOf(centerpoint.get(centerpoint.size()-1).getY()));
            ContentValues values = new ContentValues();
            values.put("xla", StringUtils.join(linela,","));
            values.put("xln",StringUtils.join(lineln,","));
            db.update("Newlines"+pposition,values,"xla = ?",new String[] {StringUtils.join(sublistline,",")});
            MoreLines moreLines = new MoreLines();
            moreLines.setListla(Arrays.asList(StringUtils.join(linela,",").split(",")));
            moreLines.setListln(Arrays.asList(StringUtils.join(lineln,",").split(",")));
            moreLines.setClassification(text);
            moreLines.setCode(code);
            if (centerpoint.size()>2){
                linelist.remove(linelist.size()-1);
            }
            linelist.add(moreLines);
        }else {
            linela.add(String.valueOf(centerpoint.get(0).getX()));
            lineln.add(String.valueOf(centerpoint.get(0).getY()));
            //获得当前时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String time=simpleDateFormat.format(date);
            ContentValues values=new ContentValues();
            values.put("classification",text);
            values.put("xla", StringUtils.join(linela,","));
            values.put("xln",StringUtils.join(lineln,","));
            values.put("time",time);
            values.put("code",code);
            db.insert("Newlines"+pposition,null,values);
        }
    }
    /**
     * 地图比例尺监听
     */
    protected class MapchangeListener implements MapScaleChangedListener{
        @Override
        public void mapScaleChanged(MapScaleChangedEvent mapScaleChangedEvent) {
            try{
                android.graphics.Point sp = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                Point smp= (Point) GeometryEngine.project(mMapView.screenToLocation(sp),SpatialReference.create(4326));
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
                String text1=DistanceUtils.dataformatY(lengthPolyline1);
                String text2=DistanceUtils.dataformatY(lengthPolyline2);
                scale1.setText(text1);
                scale2.setText(text2);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * Activity回调，根据传回不同的requestCode，执行不同的动作
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    i1 = 1;
                    String textp="";
                    if (text.contains("-")){
                        int index=text.indexOf("-");
                        textp=text.substring(0,index);
                    }else {
                        textp=text;
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
                    values.put("name","p");
                    values.put("la", String.valueOf(mp.getX()));
                    values.put("ln", String.valueOf(mp.getY()));
                    values.put("high", String.valueOf(mp.getZ()));
                    values.put("time", time);
                    values.put("classification", text);
                    values.put("code", "C7");
                    db.insert("Photopoints" + pposition, null, values);
                    values.clear();

                    LitepalPoints litepalPoints = new LitepalPoints();
                    litepalPoints.setLa(String.valueOf(mp.getX()));
                    litepalPoints.setName("p");
                    litepalPoints.setLn(String.valueOf(mp.getY()));
                    litepalPoints.setHigh(String.valueOf(mp.getZ()));
                    litepalPoints.setClassification(text);
                    litepalPoints.setDatetime(time);
                    litepalPoints.setCode("C7");
                    pointlist.add(litepalPoints);
                }
                break;
            case 0:
                if (resultCode == RESULT_OK){
                    Bundle bundle=data.getExtras();
                    if (bundle!=null);
                    atotitle=bundle.getString("second");
                    if (!UseList(titles,atotitle)){
                        BitmapDrawable bitmapDrawable= (BitmapDrawable)getResources().getDrawable(R.mipmap.p25);
                        final PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(bitmapDrawable);
                        pictureMarkerSymbol.loadAsync();
                        pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
                            @Override
                            public void run() {
                                Graphic ph2 = new Graphic(mp, pictureMarkerSymbol);
                                listph.add(ph2);
                                graphicsOverlay_2.getGraphics().add(ph2);
                            }
                        });
                        TextSymbol textSymboly = new TextSymbol(12f, atotitle, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                        graphicsOverlay_2.getGraphics().add(new Graphic(mp,textSymboly));

                        LitepalPoints litepalPoints2=new LitepalPoints();
                        litepalPoints2.setLa(String.valueOf(mp.getX()));
                        litepalPoints2.setLn(String.valueOf(mp.getY()));
                        litepalPoints2.setHigh(String.valueOf(mp.getZ()));
                        litepalPoints2.setClassification(atotitle);
                        litepalPoints2.setCode("null");
                        pointlist.add(litepalPoints2);
                    }else {
                        for (int i=0;i<titles.length;i++){
                            if (titles[i].equals(atotitle)){
                                ap=i;
                            }
                        }
                        BitmapDrawable bitmapDrawable= (BitmapDrawable)getResources().getDrawable(imager[ap]);
                        final PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(bitmapDrawable);
                        pictureMarkerSymbol.loadAsync();
                        pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
                            @Override
                            public void run() {
                                Graphic ph2 = new Graphic(mp, pictureMarkerSymbol);
                                listph.add(ph2);
                                graphicsOverlay_2.getGraphics().add(ph2);
                            }
                        });
                        TextSymbol textSymboly = new TextSymbol(12f, atotitle, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                        graphicsOverlay_2.getGraphics().add(new Graphic(mp,textSymboly));

                        LitepalPoints litepalPoints2=new LitepalPoints();
                        litepalPoints2.setLa(String.valueOf(mp.getX()));
                        litepalPoints2.setLn(String.valueOf(mp.getY()));
                        litepalPoints2.setHigh(String.valueOf(mp.getZ()));
                        litepalPoints2.setClassification(atotitle);
                        litepalPoints2.setCode(codes[ap]);
                        pointlist.add(litepalPoints2);
                    }
                    mMapView.setViewpointCenterAsync(mp);
                }
        }
    }
    public static class LocalReceiver1 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ali1=true;
            ali2=false;
        }
    }
    public static class LocalReceiver2 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ali1=false;
            ali2=true;
        }
    }
    public static class LocalReceiver3 extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ali3=true;
        }
    }
    public class LocalReceiver4 extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if (action.equals("com.delatedatas.broadcast")){
                Cursor cursor3= db.query("Newpoints"+pposition, null, null, null, null, null, null);
                Cursor cursor4 = db.query("Newlines"+pposition, null, null, null, null, null, null);
                try {
                    pointlist.clear();
                    linelist.clear();
                    pointlist=newDataActivity.getData(pointlist,cursor3);
                    linelist=dataLineActivity.getData(linelist,cursor4);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
