package com.arcgis.mymap;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.AsyncQueryHandler;
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
import android.location.LocationManager;
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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arcgis.mymap.Geological.GeologicalMapActivity;
import com.arcgis.mymap.Geological.Voice.VoiceToText;
import com.arcgis.mymap.adapter.FloorAdapter;
import com.arcgis.mymap.adapter.LayoutlistAdapter;
import com.arcgis.mymap.adapter.RecyclerAdapter;
import com.arcgis.mymap.contacts.ContactDB;
import com.arcgis.mymap.contacts.LayoutAttributes;
import com.arcgis.mymap.contacts.Lines;
import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.contacts.PointGPS;
import com.arcgis.mymap.contacts.PointXYZ;
import com.arcgis.mymap.contacts.SevenPrams;
import com.arcgis.mymap.utils.ColorPickerView;
import com.arcgis.mymap.utils.CoordTransUtil;
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
import com.esri.arcgisruntime.data.Geodatabase;
import com.esri.arcgisruntime.data.GeodatabaseFeatureTable;
import com.esri.arcgisruntime.data.ShapefileFeatureTable;
import com.esri.arcgisruntime.data.TileCache;
import com.esri.arcgisruntime.data.VectorTileCache;
import com.esri.arcgisruntime.geometry.GeodeticCurveType;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.GeometryType;
import com.esri.arcgisruntime.geometry.LinearUnit;
import com.esri.arcgisruntime.geometry.LinearUnitId;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.internal.jni.CoreGraphic;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.layers.ArcGISVectorTiledLayer;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.layers.Layer;
import com.esri.arcgisruntime.layers.RasterLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.loadable.LoadStatusChangedEvent;
import com.esri.arcgisruntime.loadable.LoadStatusChangedListener;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.LayerList;
import com.esri.arcgisruntime.mapping.MobileMapPackage;
import com.esri.arcgisruntime.mapping.view.BackgroundGrid;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedEvent;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedListener;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.raster.Raster;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleRenderer;
import com.esri.arcgisruntime.symbology.TextSymbol;
import com.esri.arcgisruntime.util.ListenableList;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
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
import java.util.PrimitiveIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.arcgis.mymap.R.drawable.khc;
import static com.arcgis.mymap.R.drawable.loginbutton;
import static com.arcgis.mymap.utils.DistanceUtils.UseList;
public class MainActivity extends Activity{
    private ColorPickerView colorDisk=null;
    private Button btnColor;
    private TextView widthtext;
    public static boolean flagOfColorChange=false;
    private final static int COLOR_CHANGE=4;
    private String time;
    public InputMethodManager imm;
    static MapView mMapView;
    ArcGISMap mMap;
    TextView xtx1,xtx2,xtx3,xtx4,xtx5,xtx6,xtx7,dtx1,dtx2,dtx3,dtx4,dtx5,FY,fy;
    EditText WriteText,mset;
    Spinner spinner;
    Button msbt;
    ImageButton lymenu,measure,zoomIn,zoomOut,dw,qx,showpoint,clear,close,camera,next;
    EditText dt,et;
    View viewt1,viewt2,viewt3,viewt4,viewt5,viewt6,viewt7,viewt8,view1,view2;
    ListView listView;
    ScaleView scale1;
    HscaleView scale2;
    RadioGroup radioGroup2;
    RadioButton add,save,laymanager,draw,draw_back;
    LinearLayout li1,li2;
    RelativeLayout relativeLayout;
    Point mp;
    GridView gridView;
    ListView listView1,listView2,listView3,listView4,listView5,listView6,listView7,listView8;
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
    private SimpleRenderer renderer;
    private SimpleRenderer renderer2;
    private RecyclerAdapter recyclerAdapter;
    private String photopath;
    private RecyclerView recyclerView;
    private List<LayoutAttributes> listlayout = new ArrayList<>();
    private List<String> urllist = new ArrayList<>();
    private List<String> colorlist = new ArrayList<>();
    private List<String> widthlist = new ArrayList<>();
    private List<Layer> layers = new ArrayList<>();
    private List<FeatureLayer> featurelayer = new ArrayList<>();
    private LayoutlistAdapter adapter;
    public String name="";
    public String text="";
    public  BitmapDrawable bitmapDrawable4;
    public  PictureMarkerSymbol pictureMarkerSymbol4;
    private List<NewProject> projects=new ArrayList<>();
    private String num,str,projectname,color,width;
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
    private boolean hasmap = false;
    private boolean down = false;
    private boolean move = false;
    private boolean writeText = false;
    private boolean drawline = false;
    public static int ap=0;
    public static String  atotitle=null;
    public String pposition;
    ToastNotRepeat toastNotRepeat;//自定义Toast
    public MapchangeListener mapchangeListener;
    public GraphicsOverlay overlay;
    public static GraphicsOverlay graphicsOverlay_2;
    public static GraphicsOverlay graphicsOverlay_point;
    public static GraphicsOverlay graphicsOverlay_line;
    private List<GraphicsOverlay> graphicsOverlays_point = new ArrayList<>();
    private List<GraphicsOverlay> graphicsOverlays_line = new ArrayList<>();
    public static GraphicsOverlay locationOverlay;
    public ExecutorService threadPool2;
    public android.graphics.Point p;
    public Point dwmp;
    private AlertDialog dialog;
    public List<android.graphics.Point> listP;
    public BitmapDrawable bitmapDrawablePoint;
    public PointCollection centerpoint,centerpoint1,seeback_point,seeback_line,linepoint;
    private List<String> lineX = new ArrayList<>();
    private List<String> lineY = new ArrayList<>();
    public SimpleLineSymbol simpleLineSymbolX;
    public LocationDisplay locationDisplay;
    private int requestCode = 2;
    private boolean isLocation = true;
    private static final int REQUEST_CHOOSER = 123;
    public static final int TAKE_PHOTO = 1;
    public final static int CANSHU = 2;
    public final static int CANSHUL= 3;
    Alert_dialogActivity alert_dialogActivity;
    private List<String> popdy1=new ArrayList<>();
    private List<String> popContents = new ArrayList<>();
    private List<String> popContent2 = new ArrayList<>();
    private List<String> popContent3 = new ArrayList<>();
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        intview();
        threadPool2 = Executors.newCachedThreadPool();
        loadArcgismap(str,color,width);
        setBackground();
        setSpiners();
        alert_dialogActivity=new Alert_dialogActivity();
        listP=new ArrayList<>();
        //linepoint = new PointCollection(SpatialReferences.getWgs84());
        overlay = new GraphicsOverlay();
        Clicklistener listener = new Clicklistener();
        mapchangeListener=new MapchangeListener();
        mMapView.addMapScaleChangedListener(mapchangeListener);


        lymenu.setOnClickListener(listener);
        measure.setOnClickListener(listener);
        close.setOnClickListener(listener);
        add.setOnClickListener(listener);
        clear.setOnClickListener(listener);
        laymanager.setOnClickListener(listener);
        draw.setOnClickListener(listener);
        draw_back.setOnClickListener(listener);
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
        xtx1.setOnLongClickListener(longClickListener);
        xtx2.setOnLongClickListener(longClickListener);
        xtx3.setOnLongClickListener(longClickListener);
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
                    final Point p=new Point(x,y,z);
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
                    final PointCollection pointCollection=new PointCollection(mMapView.getSpatialReference());
                    for (int a = 0 ; a<= xla.size()-1 ; a++){
                        pointCollection.add(Double.parseDouble(xla.get(a)),Double.parseDouble(xln.get(a)));
                    }
                    if (xla.size()<=4){
                        pictureMarkerSymbol.loadAsync();
                    }
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
                    if (clas.equals("10kv")||clas.equals("220v")){
                        SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                        Polyline polyline=new Polyline(pointCollection);
                        Graphic line = new Graphic(polyline, lineSymbol);
                        graphicsOverlay.getGraphics().add(line);
                        TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                        graphicsOverlay.getGraphics().add(new Graphic(polyline,textSymbolh2));
                    }else if (clas.equals("通讯")){
                        SimpleLineSymbol lineSymbol= new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2);
                        Polyline polyline=new Polyline(pointCollection);
                        Graphic line = new Graphic(polyline, lineSymbol);
                        graphicsOverlay.getGraphics().add(line);
                        TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                        graphicsOverlay.getGraphics().add(new Graphic(polyline,textSymbolh2));
                    }else if(clas.equals("单渠")||clas.equals("双渠")||clas.equals("单沟")||clas.equals("双沟")){
                        SimpleLineSymbol lineSymbol= new SimpleLineSymbol(SimpleLineSymbol.Style.DASH, Color.CYAN, 2);
                        Polyline polyline=new Polyline(pointCollection);
                        Graphic line = new Graphic(polyline, lineSymbol);
                        graphicsOverlay.getGraphics().add(line);
                        TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                        graphicsOverlay.getGraphics().add(new Graphic(polyline,textSymbolh2));
                    }else{
                        SimpleLineSymbol lineSymbol= new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 1);
                        Polyline polyline=new Polyline(pointCollection);
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

    //加载图层
    @SuppressLint("ResourceAsColor")
    private void loadArcgismap(String str,String color,String width){
        if (str!=null){
            str = str.substring(1,str.length()-1).trim();
            color = color.substring(1,color.length()-1).trim();
            width = width.substring(1,width.length()-1).trim();
            Log.i("TAG","str="+str);
            Log.i("TAG","color="+color);
            List<String> list = Arrays.asList(str.split(","));
            List<String> list2 = Arrays.asList(color.split(","));
            List<String> list3 = Arrays.asList(width.split(","));
            urllist.addAll(list);
            colorlist.addAll(list2);
            widthlist.addAll(list3);
            String s = list.get(list.size()-1).toString();
            if (s.substring(s.indexOf(".")).equals(".shp")){
                Log.i("TAG","123");
                mMap = new ArcGISMap(new Basemap());
                mMapView.setMap(mMap);
            }
            for (int i = 0 ; i<list.size();i++){
                LayoutAttributes layoutAttributes = new LayoutAttributes();
                layoutAttributes.setName(list.get(i));
                layoutAttributes.setColor(Integer.parseInt(list2.get(i).toString().trim()));
                listlayout.add(layoutAttributes);
                loadLayer(list.get(list.size()-1-i).toString().trim(),list2.get(list2.size()-1-i).toString().trim(),list3.get(list3.size()-1-i).toString().trim());
            }
            hasmap = true;
        }else{
            mMap = new ArcGISMap(new Basemap());
            mMapView.setMap(mMap);
        }
        locationOverlay = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(locationOverlay);
        locationDisplay=mMapView.getLocationDisplay();
        Log.i("TAG","location="+locationDisplay.getLocation().getPosition());
    }
    private void loadLayer(String path,String color,String width){
        //path="/storage/emulated/0/0507.tpk"
        String str = path.substring(path.indexOf("."));
        Log.i("TAG","path="+path);

        if (str.equals(".tpk")){
            //加载TPK
            TileCache mainTileCache = new TileCache(path);
            ArcGISTiledLayer arcGISTiledLayer = new ArcGISTiledLayer(mainTileCache);
            arcGISTiledLayer.setDescription("MainLayer");
            mMap = new ArcGISMap(new Basemap(arcGISTiledLayer));
            mMapView.setMap(mMap);
            layers.clear();
            featurelayer.clear();
            layers.add(arcGISTiledLayer);
        }else if(str.equals(".vtpk")){
            //加载vtpk
            VectorTileCache vectorTileCache = new VectorTileCache(path);

            ArcGISVectorTiledLayer tiledLayer = new ArcGISVectorTiledLayer(vectorTileCache);
            tiledLayer.setDescription("MainLayer");
            mMap = new ArcGISMap(new Basemap(tiledLayer));
            //mMap.getOperationalLayers().add(tiledLayer);
            mMapView.setMap(mMap);
            layers.clear();
            featurelayer.clear();
            listView.setVisibility(View.GONE);
            layers.add(tiledLayer);
        }else if(str.equals(".tif")){
            //加载tif
            Raster raster = new Raster(path);
            RasterLayer rasterLayer = new RasterLayer(raster);
            rasterLayer.setDescription("MainLayer");
            mMap = new ArcGISMap(new Basemap(rasterLayer));
            //mMap.getOperationalLayers().add(rasterLayer);
            mMapView.setMap(mMap);
            rasterLayer.addDoneLoadingListener(new Runnable() {
                @Override
                public void run() {
                    mMapView.setViewpointGeometryAsync(rasterLayer.getFullExtent(),50);
                }
            });
            layers.clear();
            featurelayer.clear();
            layers.add(rasterLayer);
        }else if(str.equals(".shp")){
            //加载shp(业务图层)
            ShapefileFeatureTable shapefileFeatureTable = new ShapefileFeatureTable(path);
            shapefileFeatureTable.loadAsync();
            FeatureLayer featureLayer = new FeatureLayer(shapefileFeatureTable);

            if (featureLayer.getFeatureTable().getGeometryType().name().equals("POLYLINE")){
                // 设置Shapefile文件的渲染方式
                SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Integer.parseInt(color), Float.parseFloat(width));
                renderer = new SimpleRenderer(lineSymbol);
            }else if(featureLayer.getFeatureTable().getGeometryType().name().equals("POLYGON")){
                SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.WHITE,Float.parseFloat(width));
                SimpleFillSymbol simpleFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID,Integer.parseInt(color),lineSymbol);
                renderer = new SimpleRenderer(simpleFillSymbol);
            }else if(featureLayer.getFeatureTable().getGeometryType().name().equals("POINT")){
                Log.i("TAG","color1="+color);
                SimpleMarkerSymbol markerSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.DIAMOND,Integer.parseInt(color),Float.parseFloat(width));
                renderer = new SimpleRenderer(markerSymbol);
            }
            featureLayer.setRenderer(renderer);

            mMap.getOperationalLayers().add(featureLayer);
            mMapView.setMap(mMap);
            listView.setVisibility(View.GONE);
            layers.add(0,featureLayer);
            featurelayer.add(0,featureLayer);
        }else if (str.equals(".mmpk")){
            MobileMapPackage mobileMapPackage = new MobileMapPackage(path);
            mobileMapPackage.loadAsync();
            mobileMapPackage.addDoneLoadingListener(new Runnable() {
                @Override
                public void run() {
                    LoadStatus loadStatus= mobileMapPackage.getLoadStatus();
                    if (loadStatus == LoadStatus.LOADED){
                        List<ArcGISMap> arcGISMaps = mobileMapPackage.getMaps();
                        ArcGISMap arcGISMapMMPK = arcGISMaps.get(0);
                        mMapView.setMap(arcGISMapMMPK);
                        layers.clear();
                        featurelayer.clear();
                        layers.add(arcGISMapMMPK.getOperationalLayers().get(0));
                    }
                }
            });
        }else if(str.equals(".geodatabase")){
            final Geodatabase localGdb=new Geodatabase(path);
            localGdb.loadAsync();
            localGdb.addDoneLoadingListener(new Runnable() {
                @Override
                public void run() {
                    LayerList mainLayerList = mMap.getOperationalLayers();
                    for (GeodatabaseFeatureTable gdbFeatureTable : localGdb.getGeodatabaseFeatureTables()){
                        FeatureLayer dataFeatureLayer = new FeatureLayer(gdbFeatureTable);
                        dataFeatureLayer.setDescription("MainLayer");
                        mainLayerList.add(dataFeatureLayer);

                    }
                    layers.add(0,mMap.getOperationalLayers().get(mMap.getOperationalLayers().size()-1));
                    listView.setVisibility(View.GONE);
                }
            });
        }
        mMap.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                centerpoint = new PointCollection(mMapView.getSpatialReference());
                centerpoint1 = new PointCollection(mMapView.getSpatialReference());
                seeback_point = new PointCollection(mMapView.getSpatialReference());
                seeback_line = new PointCollection(mMapView.getSpatialReference());
                linepoint = new PointCollection(mMapView.getSpatialReference());
            }
        });
        hasmap = true;
        //SetSpatialReference();
        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this,mMapView){
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (down){
                    if (drawline){
                        android.graphics.Point p = new android.graphics.Point((int)e.getX(),(int)e.getY());
                        //点画线
                        DotLine(p);
                    }
                    if (writeText){
                        android.graphics.Point p = new android.graphics.Point((int)e.getX(),(int)e.getY());
                        //添加文字
                        RelativeLayout relativeLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.alert_dialog_writetext,null);
                        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("添加文字:")
                                .setView(relativeLayout)
                                .setNegativeButton("取消",null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String name  = WriteText.getText().toString();
                                        TextSymbol t = new TextSymbol(18f, name, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                                        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
                                        mMapView.getGraphicsOverlays().add(graphicsOverlay);
                                        graphicsOverlays_point.add(graphicsOverlay);
                                        Point mp= mMapView.screenToLocation(p);
                                        seeback_point.add(mp.getX(),mp.getY(),mp.getZ());
                                        seeback_line.clear();
                                        graphicsOverlay.getGraphics().add(new Graphic(mp,t));
                                        //获得当前时间
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date date= new Date(System.currentTimeMillis());
                                        String time=simpleDateFormat.format(date);
                                        ContentValues values=new ContentValues();
                                        values.put("name","null");
                                        values.put("la",String.valueOf(mp.getX()));
                                        values.put("ln",String.valueOf(mp.getY()));
                                        values.put("high",String.valueOf(mp.getZ()));
                                        values.put("time",time);
                                        values.put("classification",name);
                                        values.put("code","null");
                                        db.insert("Newpoints"+pposition,null,values);
                                        LitepalPoints litepalPoints=new LitepalPoints();
                                        litepalPoints.setLa(String.valueOf(mp.getX()));
                                        litepalPoints.setLn(String.valueOf(mp.getY()));
                                        litepalPoints.setHigh(String.valueOf(mp.getZ()));
                                        litepalPoints.setClassification(name);
                                        litepalPoints.setDatetime(time);
                                        pointlist.add(litepalPoints);
                                    }
                                }).show();
                        WriteText = (EditText) dialog.findViewById(R.id.text);
                    }
                }
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (move){
                    //手画线
                    GraphicsOverlay overlay = new GraphicsOverlay();
                    mMapView.getGraphicsOverlays().add(overlay);
                    android.graphics.Point p1 = new android.graphics.Point((int)e1.getX(),(int)e1.getY());
                    android.graphics.Point p2 = new android.graphics.Point((int)e2.getX(),(int)e2.getY());
                    Log.i("TAG","p1="+p1);
                    Log.i("TAG","p2="+p2);
                    Point mp1= mMapView.screenToLocation(p1);
                    Point mp2= mMapView.screenToLocation(p2);
                    PointCollection collection = new PointCollection(mMapView.getSpatialReference());
                    collection.add(mp1);
                    collection.add(mp2);
                    linepoint.add(mp1);
                    lineX.add(String.valueOf(mp1.getX()));
                    lineY.add(String.valueOf(mp1.getY()));
                    SimpleLineSymbol s1 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 1);
                    Polyline polyline = new Polyline(collection);
                    Graphic line = new Graphic(polyline, s1);
                    overlay.getGraphics().add(line);
                    return false;
                }else{
                    return super.onScroll(e1, e2, distanceX, distanceY);
                }
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (move){
                    //保存数据库
                    //获得当前时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    time=simpleDateFormat.format(date);
                    ContentValues values=new ContentValues();
                    values.put("classification","手绘线");
                    values.put("xla", StringUtils.join(lineX,","));
                    values.put("xln",StringUtils.join(lineY,","));
                    values.put("time",time);
                    values.put("code","Q2");
                    db.insert("Newlines"+pposition,null,values);
                    MoreLines moreLines = new MoreLines();
                    moreLines.setListla(Arrays.asList(StringUtils.join(lineX,",").split(",")));
                    moreLines.setListln(Arrays.asList(StringUtils.join(lineY,",").split(",")));
                    moreLines.setClassification("手绘线");
                    linelist.add(moreLines);


                    linepoint.clear();
                    lineX.clear();
                    lineY.clear();
                    return false;
                }else{
                    return true;
                }
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (drawline==true||move==true){
                    LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialog_line_atribite,null);
                    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("添加属性:")
                            .setView(linearLayout)
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String name = ContactDB.getLines()[spinner.getSelectedItemPosition()];
                                    String code = ContactDB.getlinecode()[spinner.getSelectedItemPosition()];
                                    String ms = mset.getText().toString();
                                    //添加文字
                                    TextSymbol t = new TextSymbol(12f, name, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                                    GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
                                    mMapView.getGraphicsOverlays().add(graphicsOverlay);
                                    if (linepoint.size()>0){
                                        graphicsOverlay.getGraphics().add(new Graphic(linepoint.get(linepoint.size()/2),t));
                                    }else{
                                        MoreLines moreLines = linelist.get(linelist.size()-1);
                                        Point p = new Point(Double.parseDouble(moreLines.getListla().get(moreLines.getListla().size()/2)),Double.parseDouble(moreLines.getListln().get(moreLines.getListln().size()/2)));
                                        graphicsOverlay.getGraphics().add(new Graphic(p,t));
                                    }

                                    ContentValues values = new ContentValues();
                                    values.put("classification",name);
                                    values.put("description",ms);
                                    values.put("code",code);
                                    db.update("Newlines"+pposition,values,"time = ?",new String[] {time});
                                    linelist.get(linelist.size()-1).setClassification(name);
                                    linelist.get(linelist.size()-1).setDescription(ms);
                                    centerpoint.clear();
                                    seeback_point.clear();
                                    seeback_line.clear();
                                    graphicsOverlays_point.clear();
                                    graphicsOverlays_line.clear();
                                    listline.clear();
                                    listtext.clear();
                                    list2.clear();
                                    linela.clear();
                                    lineln.clear();
                                    sublistline.clear();
                                }
                            }).show();
                    spinner = (Spinner) dialog.findViewById(R.id.spinner);
                    mset  = (EditText) dialog.findViewById(R.id.mset);
                    msbt = (Button) dialog.findViewById(R.id.msbt);
                    msbt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            VoiceToText.startSpeechDialog(dialog.getContext(),mset);
                        }
                    });
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,ContactDB.getLines());
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            TextView tv = (TextView) view;
                            tv.setGravity(Gravity.CENTER_HORIZONTAL);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                return true;
            }
        });
    }
    /**
     * 设置空间参考
     */
    private void SetSpatialReference(){
//        android.graphics.Point dp=new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
//        Point p = (Point) GeometryEngine.project(mMapView.screenToLocation(dp),SpatialReference.create(4326));

    }
    /**
     * 初始化控件
     */
    private void intview() {
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"=5c74ebde");
        listView = (ListView) findViewById(R.id.layoutlist);
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
        viewt8 =LayoutInflater.from(this).inflate(R.layout.pop_list5,null);
        listView1= (ListView) viewt1.findViewById(R.id.pop_lv);
        listView2= (ListView) viewt2.findViewById(R.id.pop_lv2);
        listView3= (ListView) viewt3.findViewById(R.id.pop_lv3);
        listView4= (ListView) viewt4.findViewById(R.id.pop_lv4);
        listView5= (ListView) viewt5.findViewById(R.id.pop_lv5);
        listView6= (ListView) viewt6.findViewById(R.id.pop_lv5);
        listView7= (ListView) viewt7.findViewById(R.id.pop_lv5);
        listView8= (ListView) viewt8.findViewById(R.id.pop_lv5);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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
        laymanager = (RadioButton) findViewById(R.id.laymanager);
        draw = (RadioButton) findViewById(R.id.drawing);
        draw_back = (RadioButton) findViewById(R.id.draw_back);
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
        //横向滚动recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                recyclerAdapter.setSelectItem(Integer.parseInt(view.getTag().toString()));
                recyclerAdapter.notifyDataSetChanged();
                switch (Integer.parseInt(view.getTag().toString())){
                    case 0:
                        //点绘线
                        down = true;
                        move = false;
                        writeText = false;
                        drawline = true;
                        break;
                    case 1:
                        //手绘线
                        down = false;
                        move = true;
                        writeText = false;
                        drawline = false;
                        break;
                    case 2:
                        //文字
                        down = true;
                        move = false;
                        writeText = true;
                        drawline = false;
                        break;
                }
            }
        });
        //获取表位置
        GetTable getTable=new GetTable();
        pposition=getTable.getTableposition(MainActivity.this,db,dbHelper,projects);
        int sposition=Integer.parseInt(getTable.getPpposition(MainActivity.this,db,dbHelper));
        str=projects.get(sposition).getStrname();
        color = projects.get(sposition).getColor();
        width = projects.get(sposition).getWidth();
        projectname=projects.get(sposition).getProjectname();
        photopath = projects.get(Integer.parseInt(pposition)).getPath();
        adapter = new LayoutlistAdapter(listlayout,R.layout.layoutlist,MainActivity.this);
        listView.setAdapter(adapter);
        adapter.setAddlayerListener(new LayoutlistAdapter.AddlayerListener() {
            @Override
            public void addLayer() {
                FileUtils.mFileFileterBySuffixs.acceptSuffixs("tif|tpk|shp|vtpk|mmpk|geodatabase");
                Intent f=new Intent(MainActivity.this,FileChooserActivity.class);
                startActivityForResult(f, REQUEST_CHOOSER);
            }
        });
        adapter.setDellayerListener(new LayoutlistAdapter.DellayerListener() {
            @Override
            public void dellayer(View v) {
                mMap.getOperationalLayers().remove(layers.size()-2-Integer.parseInt(v.getTag().toString()));
                layers.remove(Integer.parseInt(v.getTag().toString()));
                listlayout.remove(Integer.parseInt(v.getTag().toString()));
                urllist.remove(Integer.parseInt(v.getTag().toString()));
                colorlist.remove(Integer.parseInt(v.getTag().toString()));
                widthlist.remove(Integer.parseInt(v.getTag().toString()));
                adapter.notifyDataSetChanged();
                UpdateUrl(urllist.toString());
                UpdateColor(colorlist.toString(),widthlist.toString());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();
                LayoutlistAdapter.MyViewHoler holer = (LayoutlistAdapter.MyViewHoler) view.getTag();
                if(holer.getTextView().getCurrentTextColor()==getResources().getColor(R.color.color22)){
                    layers.get(position).setVisible(false);
                }else {
                    layers.get(position).setVisible(true);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout layout = (RelativeLayout) getLayoutInflater().inflate(R.layout.dialog_select_color,null);
                Log.i("TAG","description="+layers.get(position).getDescription());
                if (!layers.get(position).getDescription().equals("MainLayer")){
                    dialog = new AlertDialog.Builder(MainActivity.this)
                            .setView(layout)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    width = widthtext.getText().toString();
                                        // 设置Shapefile文件的渲染方式
                                        if (featurelayer.get(position).getFeatureTable().getGeometryType().name().equals("POLYLINE")){
                                            // 设置Shapefile文件的渲染方式
                                            SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, ColorPickerView.ColorText, Float.parseFloat(width));
                                            renderer = new SimpleRenderer(lineSymbol);
                                        }else if(featurelayer.get(position).getFeatureTable().getGeometryType().name().equals("POLYGON")){
                                            SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.WHITE,Float.parseFloat(width));
                                            SimpleFillSymbol simpleFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID,ColorPickerView.ColorText,lineSymbol);
                                            renderer = new SimpleRenderer(simpleFillSymbol);
                                        }else if(featurelayer.get(position).getFeatureTable().getGeometryType().name().equals("POINT")){
                                            SimpleMarkerSymbol markerSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.DIAMOND,ColorPickerView.ColorText,Float.parseFloat(width));
                                            renderer = new SimpleRenderer(markerSymbol);
                                        }
                                        featurelayer.get(position).setRenderer(renderer);
                                        featurelayer.get(position).setRenderingMode(FeatureLayer.RenderingMode.DYNAMIC);
                                        featurelayer.get(position).setRefreshInterval(1000);
                                        listlayout.get(position).setColor(ColorPickerView.ColorText);
                                        colorlist.set(position,String.valueOf(ColorPickerView.ColorText));
                                        widthlist.set(position,width);
                                        UpdateColor(colorlist.toString(),widthlist.toString());
                                }
                            }).show();
                    Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    btnColor = (Button) dialog.findViewById(R.id.colorButton);
                    widthtext = (TextView) dialog.findViewById(R.id.editText);
                    LinearLayout.LayoutParams cancelBtnPara = (LinearLayout.LayoutParams) button.getLayoutParams();
                    //设置按钮的大小
                    cancelBtnPara.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    cancelBtnPara.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    //设置文字居中
                    cancelBtnPara.gravity = Gravity.CENTER;
                    //设置按钮左上右下的距离
                    cancelBtnPara.setMargins(100, 20, 100, 20);
                    button.setLayoutParams(cancelBtnPara);
                    button.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.color_subsidy_bg));
                    button.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color1));
                    button.setTextSize(16);
                }
                if (!layers.get(position).getDescription().equals("MainLayer")){
                    btnColor.setBackgroundColor(Integer.parseInt(colorlist.get(position).trim()));
                    widthtext.setText(widthlist.get(position));
                }


                Handler mColorhandler=new Handler()
                {
                    public void handleMessage(Message msg)
                    {
                        switch(msg.what)
                        {
                            case COLOR_CHANGE:
                                btnColor.setBackgroundColor(ColorPickerView.ColorText);
                                Log.i("TAG","color="+ColorPickerView.ColorText);
                                break;

                            default:
                                break;
                        }
                    };
                };

                //用线程监听 是否颜色已经改变
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        while(true)
                        {
                            //当色盘颜色改变时，也就是松手时，把flagOfColorChange置为true
                            //然后handler 发送消息，button改变字体

                            //此变量为全局变量，破坏了封装性。但是实现了功能，有更好的方式可以留言
                            if(flagOfColorChange)
                            {

                                System.out.println("color change!!!");
                                flagOfColorChange=false;
                                mColorhandler.sendEmptyMessage(COLOR_CHANGE);
                            }
                        }

                    }
                }).start();

                return true;
            }
        });
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
        popContent3.add("110v");
        popContent3.add("...");
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
        isLocation = false;
        unregisterReceiver(receiver4);
    }
    /**
     * 设置网格背景
     */
    private void setBackground() {
        BackgroundGrid mainBackground = new BackgroundGrid();
        mainBackground.setGridLineWidth(0);
        mainBackground.setGridSize(10);
        mainBackground.setColor(this.getResources().getColor(R.color.color5));
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
     *更新地图url
     */
    private void UpdateUrl(String str){
        ContentValues values = new ContentValues();
        values.put("sname",str);
        db.update("Newproject",values,"position = ?",new String[]{String.valueOf(pposition)});
    }
    /**
     *更新图层颜色
     */
    private void UpdateColor(String str,String width){
        ContentValues values = new ContentValues();
        values.put("layercolor",str);
        values.put("width",width);
        db.update("Newproject",values,"position = ?",new String[]{String.valueOf(pposition)});
    }
    /**
     * 点绘线
     */
    private void DotLine(android.graphics.Point p ){
        i1=1;
        graphicsOverlay_line = new GraphicsOverlay();
        graphicsOverlays_line.add(graphicsOverlay_line);
        mMapView.getGraphicsOverlays().add(graphicsOverlay_line);
        mp= mMapView.screenToLocation(p);
        pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
        pictureMarkerSymbol4.loadAsync();
        pictureMarkerSymbol4.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol4);
                listph.add(ph);
                graphicsOverlay_line.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
        centerpoint.add(mp.getX(), mp.getY(), mp.getZ());
        seeback_line.add(mp.getX(),mp.getY(),mp.getZ());
        seeback_point.clear();
        SimpleLineSymbol s1 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 1);
        //TextSymbol t1 = new TextSymbol(12f, "", Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
        if (centerpoint.size() >= 2) {
            Polyline polyline = new Polyline(centerpoint);
            Graphic line = new Graphic(polyline, s1);
            listline.add(line);
            graphicsOverlay_line.getGraphics().add(listline.get(listline.size() - 1));
            //Graphic ts = new Graphic(polyline);
            //listtext.add(ts);
            //graphicsOverlay_line.getGraphics().add(listtext.get(listtext.size() - 1));
            if (centerpoint.size()>2){
                mMapView.getGraphicsOverlays().remove(graphicsOverlays_line.get(graphicsOverlays_line.size()-2));
                graphicsOverlays_line.remove(graphicsOverlays_line.size()-2);
            }
            linela.add(String.valueOf(centerpoint.get(centerpoint.size()-1).getX()));
            lineln.add(String.valueOf(centerpoint.get(centerpoint.size()-1).getY()));
            ContentValues values = new ContentValues();
            values.put("xla", StringUtils.join(linela,","));
            values.put("xln",StringUtils.join(lineln,","));
            db.update("Newlines"+pposition,values,"xla = ?",new String[] {StringUtils.join(linelist.get(linelist.size()-1).getListla(),",")});
            MoreLines moreLines = new MoreLines();
            moreLines.setListla(Arrays.asList(StringUtils.join(linela,",").split(",")));
            moreLines.setListln(Arrays.asList(StringUtils.join(lineln,",").split(",")));
            moreLines.setClassification(text);
            moreLines.setCode("Q2");
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
            time=simpleDateFormat.format(date);
            ContentValues values=new ContentValues();
            values.put("classification","点绘线");
            values.put("xla", StringUtils.join(linela,","));
            values.put("xln",StringUtils.join(lineln,","));
            values.put("time",time);
            values.put("code","Q2");
            db.insert("Newlines"+pposition,null,values);
            MoreLines moreLines = new MoreLines();
            moreLines.setListla(Arrays.asList(StringUtils.join(linela,",").split(",")));
            moreLines.setListln(Arrays.asList(StringUtils.join(lineln,",").split(",")));
            linelist.add(moreLines);
    }
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
                case R.id.xtx1:
                    final PopupWindow popupWindow13=new PopupWindow(viewt8);
                    ShowPopupWindows(popupWindow13,listView8,xtx1,popContent3);
                    popupWindow13.showAsDropDown(v);
                    break;
                case R.id.xtx2:
                    final PopupWindow popupWindow14=new PopupWindow(viewt8);
                    ShowPopupWindows(popupWindow14,listView8,xtx2,popContent3);
                    popupWindow14.showAsDropDown(v);
                    break;
                case R.id.xtx3:
                    final PopupWindow popupWindow15=new PopupWindow(viewt8);
                    ShowPopupWindows(popupWindow15,listView8,xtx3,popContent3);
                    popupWindow15.showAsDropDown(v);
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
                    if (hasmap){
                        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                        mp= mMapView.screenToLocation(p);
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
                            File file=new File(photopath+"/航测/Pictrue/"+projectname+"/"+text);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            String path=photopath+"/航测/Pictrue/"+projectname+"/"+text+"/"+text+".jpg";
                            outputImage=new File(path);
                        }else {
                            int index=text.indexOf("-");
                            String text1=text.substring(0,index);
                            File file=new File(photopath+"/航测/Pictrue/"+projectname+"/"+text1);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            String path=photopath+"/航测/Pictrue/"+projectname+"/"+text1+"/"+text+".jpg";
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
                    }
                    break;
                case R.id.xtx1:
                    if (hasmap){
                        String text1 =xtx1.getText().toString();
                        SimpleLineSymbol s1 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                        TextSymbol t1 = new TextSymbol(12f, text1, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                        DrawLine(s1,t1,text1,"D0");
                    }
                    break;
                case R.id.xtx2:
                    if (hasmap){
                        String text2=xtx2.getText().toString();
                        SimpleLineSymbol s2 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                        TextSymbol t2 = new TextSymbol(12f, text2, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                        DrawLine(s2,t2,text2,"D2");
                    }
                    break;
                case R.id.xtx3:
                    if (hasmap){
                        String text3=xtx3.getText().toString();
                        SimpleLineSymbol s3 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                        TextSymbol t3 = new TextSymbol(12f, text3, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                        DrawLine(s3,t3,text3,"D2");
                    }
                    break;
                case R.id.xtx4:
                    if (hasmap){
                        String text4=xtx4.getText().toString();
                        SimpleLineSymbol s4 = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2);
                        TextSymbol t4 = new TextSymbol(12f, text4, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                        DrawLine(s4,t4,text4,"D3");
                    }
                    break;
                case R.id.xtx5:
                    if (hasmap){
                        String text5=xtx5.getText().toString();
                        BitmapDrawable b1= (BitmapDrawable) getResources().getDrawable(R.mipmap.p25);
                        TextSymbol t5 = new TextSymbol(12f, text5, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                        DrawPoint(b1,t5,text5,"R","null");
                    }
                    break;
                case R.id.xtx6:
                    if (hasmap){
                        String text6=xtx6.getText().toString();
                        SimpleLineSymbol s6 = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH, Color.YELLOW, 2);
                        TextSymbol t6 = new TextSymbol(12f, text6, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                        DrawLine(s6,t6,text6,"X0");
                    }
                    break;
                case R.id.xtx7:
                    if (hasmap){
                        String text7=xtx7.getText().toString();
                        SimpleLineSymbol s7 = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH_DOT, Color.CYAN, 2);
                        TextSymbol t7 = new TextSymbol(12f, text7, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.MIDDLE);
                        DrawLine(s7,t7,text7,"U6");
                    }
                    break;
                case R.id.dtx1:
                    final PopupWindow popupWindow6=new PopupWindow(viewt1);
                    ShowPopupWindows(popupWindow6,listView1,dtx1,popContents);
                    popupWindow6.showAsDropDown(v);
                    break;
                case R.id.dtx2:
                    String stext2=dtx1.getText().toString()+dtx2.getText().toString();
                    String code = "null";
                    if (dtx1.getText().toString().equals("砖")){
                        name="Z";
                        code = "F0";
                    }else if (dtx1.getText().toString().equals("混")){
                        name="H";
                        code = "F3";
                    }else if (dtx1.getText().toString().equals("砼")){
                        name="T";
                        code = "F2";
                    }else if (dtx1.getText().toString().equals("简")){
                        name="J";
                        code = "F6";
                    }else {
                        name="P";
                        code = "F5";
                    }
                    if (hasmap){
                        BitmapDrawable b2= (BitmapDrawable) getResources().getDrawable(R.mipmap.p36);
                        TextSymbol st2 = new TextSymbol(12f, stext2, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                        DrawPoint(b2,st2,stext2,name,code);
                    }
                    break;
                case R.id.dtx3:
                    String stext3=dtx1.getText().toString()+dtx3.getText().toString();
                    String code1 = "null";
                    if (dtx1.getText().toString().equals("砖")){
                        name="Z";
                        code1 = "F0";
                    }else if (dtx1.getText().toString().equals("混")){
                        name="H";
                        code1 = "F3";
                    }else if (dtx1.getText().toString().equals("砼")){
                        name="T";
                        code1 = "F2";
                    }else if (dtx1.getText().toString().equals("简")){
                        name="J";
                        code1 = "F6";
                    }else {
                        name="P";
                        code1 = "F5";
                    }
                    if (hasmap){
                        BitmapDrawable b3= (BitmapDrawable) getResources().getDrawable(R.mipmap.p36);
                        TextSymbol st3 = new TextSymbol(12f, stext3, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                        DrawPoint(b3,st3,stext3,name,code1);
                    }
                    break;
                case R.id.dtx4:
                    String stext4=dtx1.getText().toString()+dtx4.getText().toString();
                    String code2 = "null";
                    if (dtx1.getText().toString().equals("砖")){
                        name="Z";
                        code2 = "F0";
                    }else if (dtx1.getText().toString().equals("混")){
                        name="H";
                        code2 = "F3";
                    }else if (dtx1.getText().toString().equals("砼")){
                        name="T";
                        code2 = "F2";
                    }else if (dtx1.getText().toString().equals("简")){
                        name="J";
                        code2 = "F6";
                    }else {
                        name="P";
                        code2 = "F5";
                    }
                    if (hasmap){
                        BitmapDrawable b4= (BitmapDrawable) getResources().getDrawable(R.mipmap.p36);
                        TextSymbol st4 = new TextSymbol(12f, stext4, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                        DrawPoint(b4,st4,stext4,name,code2);
                    }
                    break;
                case R.id.dtx5:
                    String stext5=dtx1.getText().toString()+dtx5.getText().toString();
                    String code3 = "null";
                    if (dtx1.getText().toString().equals("砖")){
                        name="Z";
                        code3 = "F0";
                    }else if (dtx1.getText().toString().equals("混")){
                        name="H";
                        code3 = "F3";
                    }else if (dtx1.getText().toString().equals("砼")){
                        name="T";
                        code3 = "F2";
                    }else if (dtx1.getText().toString().equals("简")){
                        name="J";
                        code3 = "F6";
                    }else {
                        name="P";
                        code3 = "F5";
                    }
                    if (hasmap){
                        BitmapDrawable b5= (BitmapDrawable) getResources().getDrawable(R.mipmap.p36);
                        TextSymbol st5 = new TextSymbol(12f, stext5, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                        DrawPoint(b5,st5,stext5,name,code3);
                    }
                    break;
                case R.id.fy:
                    i1=1;
                    if (hasmap){
                        graphicsOverlay_2 = new GraphicsOverlay();
                        mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
                        graphicsOverlays_point.add(graphicsOverlay_2);
                        BitmapDrawable bitmapDrawable3;
                        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                        mp= mMapView.screenToLocation(p);
                        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
                        seeback_point.add(mp.getX(),mp.getY(),mp.getZ());
                        seeback_line.clear();
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
                    }
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
//                case R.id.dw:
//                    String s = mMapView.getSpatialReference().getWKText();
//                    Log.i("TAG","s="+s);
//                    ContentValues values = new ContentValues();
//                    if (s.contains("_1984")){
//                        values.put("c1code", "WGS84");
//                        db.update("Newproject", values, "position=?", new String[]{String.valueOf(pposition)});
//                        Log.i("TAG","84");
//                    }else if(s.contains("_1980")){
//                        values.put("c1code", "China Xian 80");
//                        db.update("Newproject", values, "position=?", new String[]{String.valueOf(pposition)});
//                        Log.i("TAG","80");
//                    }else if(s.contains("_1954")){
//                        values.put("c1code", "China Beijing 54");
//                        db.update("Newproject", values, "position=?", new String[]{String.valueOf(pposition)});
//                        Log.i("TAG","54");
//                    }else if(s.contains("CGCS2000")){
//                        values.put("c1code", "China CGCS 2000");
//                        db.update("Newproject", values, "position=?", new String[]{String.valueOf(pposition)});
//                        Log.i("TAG","2000");
//                    }else{
//                        values.put("c1code", "unknown");
//                        db.update("Newproject", values, "position=?", new String[]{String.valueOf(pposition)});
//                        Log.i("TAG","unknown");
//                    }
//
//
//                    locationDisplay.startAsync();
//                    LocationManager locationManager = (LocationManager)MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
//                    boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//                    boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//                    if (gps || network) {
//                        //开始定位
//                        ToastNotRepeat.show(MainActivity.this, "定位中");
//                        isLocation = true;
//                        NewProject vo = projects.get(Integer.parseInt(pposition));//当前工程
//                        SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
//                        BitmapDrawable b= (BitmapDrawable) getResources().getDrawable(R.mipmap.p25);
//                        new Thread(){
//                            @Override
//                            public void run() {
//                                while (isLocation){
//                                    try {
//                                        sleep(1000);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                    PointGPS GpsPoint = new PointGPS(locationDisplay.getLocation().getPosition().getX(),locationDisplay.getLocation().getPosition().getY());
//                                    PointXYZ ptOut;
//                                    if (vo.getC1code().equals(CoordTransUtil.WGS84)) {
//                                        ptOut = CoordTransUtil.DatumTransWGSBLH2XYZ(GpsPoint, vo.getC2zyzwx(), st, vo.getC2djcs());
//                                    }else if (vo.getC1code().equals(CoordTransUtil.XIAN)) {
//                                        ptOut = CoordTransUtil.DatumTransWGS2XiAn(GpsPoint, vo.getC2zyzwx(), st, vo.getC2djcs());
//                                    }else if (vo.getC1code().equals(CoordTransUtil.BEIJING54)) {
//                                        ptOut = CoordTransUtil.DatumTransWGS2BJ(GpsPoint, vo.getC2zyzwx(), st, vo.getC2djcs());
//                                    }else if (vo.getC1code().equals(CoordTransUtil.CGCS2000)) {
//                                        ptOut = CoordTransUtil.DatumTransWGS2CGCS(GpsPoint, vo.getC2zyzwx(), st, vo.getC2djcs());
//                                    }else {
//                                        //自定义坐标系
//                                        ptOut = CoordTransUtil.DatumTransWGS2USER(GpsPoint, vo.getC2zyzwx(), st, vo.getC2djcs(), vo.getC1cbz(), vo.getC1plds());
//                                    }
//                                    if (ptOut.getX()!=0||ptOut.getY()!=0){
//                                        Log.i("TAG","ptout="+ptOut.toString());
//                                        Point p = new Point(ptOut.getX(),ptOut.getY(),ptOut.getZ());
//                                        locationOverlay.getGraphics().clear();
//                                        final PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(b);
//                                        pictureMarkerSymbol.loadAsync();
//                                        pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Graphic ph = new Graphic(p,pictureMarkerSymbol);
//                                                locationOverlay.getGraphics().add(ph);
//                                            }
//                                        });
//                                    }else{
//                                        isLocation = false;
//                                        new Handler().postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                dw.setVisibility(View.VISIBLE);
//                                                qx.setVisibility(View.GONE);
//                                            }
//                                        },2000);
//                                    }
//                                }
//                                super.run();
//                            }
//                        }.start();
//                    }else{
//                        ToastNotRepeat.show(MainActivity.this, "GPS未准备好！");
//                    }
//                    break;
                case R.id.dw:
                    locationDisplay=mMapView.getLocationDisplay();
                    Log.i("TAG","location="+locationDisplay.getLocation().getPosition());
                    locationDisplay.addDataSourceStatusChangedListener(new LocationDisplay.DataSourceStatusChangedListener() {
                        @Override
                        public void onStatusChanged(LocationDisplay.DataSourceStatusChangedEvent dataSourceStatusChangedEvent) {
                            if (dataSourceStatusChangedEvent.isStarted())
                                return;
                            if (dataSourceStatusChangedEvent.getError() == null)
                                return;
                        }
                    });
                    locationDisplay.addLocationChangedListener(new LocationDisplay.LocationChangedListener() {
                        @Override
                        public void onLocationChanged(LocationDisplay.LocationChangedEvent locationChangedEvent) {

                        }
                    });
                    try{
                        android.graphics.Point dp=new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                        PointXYZ ptInXYZ= new PointXYZ(mMapView.screenToLocation(dp).getX(),mMapView.screenToLocation(dp).getY(),mMapView.screenToLocation(dp).getZ());
                        NewProject vo = projects.get(Integer.parseInt(pposition));//当前工程
                        if (vo.getC1code().equals(CoordTransUtil.WGS84)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransWGSXYZ2BLH(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs());
                        }else if (vo.getC1code().equals(CoordTransUtil.XIAN)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransXiAn2WGS(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs());
                        }else if (vo.getC1code().equals(CoordTransUtil.BEIJING54)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransBJ2WGS(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs());
                        }else if (vo.getC1code().equals(CoordTransUtil.CGCS2000)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransCGCS2WGS(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs());
                        }else {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransUSER2WGS(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs(), vo.getC1cbz(), vo.getC1plds());
                        }
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
                        //locationDisplay.stop();
                        //mMapView.setViewpointCenterAsync(dwmp);
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
                    seeback_point.clear();
                    seeback_line.clear();
                    graphicsOverlays_point.clear();
                    graphicsOverlays_line.clear();
                    listline.clear();
                    listtext.clear();
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
                    recyclerView.setVisibility(View.GONE);
                    recyclerAdapter.setSelectItem(-1);
                    recyclerAdapter.notifyDataSetChanged();
                    break;
                case R.id.next:
                    centerpoint.clear();
                    seeback_point.clear();
                    seeback_line.clear();
                    graphicsOverlays_point.clear();
                    graphicsOverlays_line.clear();
                    listline.clear();
                    listtext.clear();
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
                    listView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    mMapView.getGraphicsOverlays().clear();
                    centerpoint.clear();
                    seeback_point.clear();
                    seeback_line.clear();
                    graphicsOverlays_point.clear();
                    graphicsOverlays_line.clear();
                    listline.clear();
                    listtext.clear();
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
                case R.id.draw_back:
                    if (hasmap){
                        if (seeback_point.size()!=0&&graphicsOverlays_point.size()>0){
                            mMapView.getGraphicsOverlays().remove(graphicsOverlays_point.get(graphicsOverlays_point.size()-1));
                            graphicsOverlays_point.remove(graphicsOverlays_point.size()-1);
                            db.delete("Newpoints"+pposition, "la=?", new String[]{String.valueOf(seeback_point.get(seeback_point.size()-1).getX())});
                            if (seeback_point.size()>=2){
                                mMapView.setViewpointCenterAsync(seeback_point.get(seeback_point.size()-2));
                                seeback_point.remove(seeback_point.size()-1);
                            }
                        }
                        if (seeback_line.size()!=0&&graphicsOverlays_line.size()>0&&listline.size()>0){
                            mMapView.getGraphicsOverlays().remove(graphicsOverlays_line.get(graphicsOverlays_line.size()-1));
                            graphicsOverlays_line.remove(graphicsOverlays_line.size()-1);
                            centerpoint.remove(centerpoint.size()-1);
                            listline.remove(listline.size()-1);
                            if (listtext.size()>0){
                                listtext.remove(listtext.size()-1);
                            }
                            graphicsOverlay_line = new GraphicsOverlay();
                            mMapView.getGraphicsOverlays().add(graphicsOverlay_line);
                            graphicsOverlays_line.add(graphicsOverlay_line);
                            if (seeback_line.size()>2){
                                pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
                                pictureMarkerSymbol4.loadAsync();
                                pictureMarkerSymbol4.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        Graphic ph = new Graphic(seeback_line.get(seeback_line.size()-1), pictureMarkerSymbol4);
                                        graphicsOverlay_line.getGraphics().add(ph);
                                    }
                                });
                                Graphic line = new Graphic(listline.get(listline.size()-1).getGeometry(),listline.get(listline.size()-1).getSymbol());
                                graphicsOverlay_line.getGraphics().add(line);
                                if (listtext.size()>0&&listtext.get(listtext.size()-1).getSymbol()!=null){
                                    Graphic ts = new Graphic(listtext.get(listtext.size()-1).getGeometry(),listtext.get(listtext.size()-1).getSymbol());
                                    graphicsOverlay_line.getGraphics().add(ts);
                                }
                                mMapView.setViewpointCenterAsync(seeback_line.get(seeback_line.size()-2));
                                seeback_line.remove(seeback_line.size()-1);
                                linela.remove(linela.size()-1);
                                lineln.remove(lineln.size()-1);
                                ContentValues values = new ContentValues();
                                List<String> linela = new ArrayList<>();
                                List<String> lineln = new ArrayList<>();
                                for (int a = 0;a<seeback_line.size();a++){
                                    linela.add(String.valueOf(seeback_line.get(a).getX()));
                                    lineln.add(String.valueOf(seeback_line.get(a).getY()));
                                }
                                values.put("xla", StringUtils.join(linela,","));
                                values.put("xln",StringUtils.join(lineln,","));
                                db.update("Newlines"+pposition,values,"xla = ?",new String[] {StringUtils.join(linelist.get(linelist.size()-1).getListla(),",")});
                                linelist.get(linelist.size()-1).setListla(Arrays.asList(StringUtils.join(linela,",").split(",")));
                                linelist.get(linelist.size()-1).setListln(Arrays.asList(StringUtils.join(lineln,",").split(",")));
                                Log.i("TAG","la1="+StringUtils.join(linelist.get(linelist.size()-1).getListla(),","));
                            }else if(seeback_line.size()==2){
                                ContentValues values = new ContentValues();
                                List<String> linela = new ArrayList<>();
                                List<String> lineln = new ArrayList<>();
                                linela.add(String.valueOf(centerpoint.get(0).getX()));
                                lineln.add(String.valueOf(centerpoint.get(0).getY()));
                                values.put("xla", StringUtils.join(linela,","));
                                values.put("xln",StringUtils.join(lineln,","));
                                db.update("Newlines"+pposition,values,"xla = ?",new String[] {StringUtils.join(linelist.get(linelist.size()-1).getListla(),",")});
                                linelist.get(linelist.size()-1).setListla(Arrays.asList(StringUtils.join(linela,",").split(",")));
                                linelist.get(linelist.size()-1).setListln(Arrays.asList(StringUtils.join(lineln,",").split(",")));
                                mMapView.setViewpointCenterAsync(seeback_line.get(seeback_line.size()-2));
                                seeback_line.remove(seeback_line.size()-1);
                            }
                        }
                    }
                    break;
                case R.id.laymanager:
                    if (listlayout.size()==0){
                        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).setTitle("图层:")
                                .setMessage("是否添加图层")
                                .setNegativeButton("取消",null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FileUtils.mFileFileterBySuffixs.acceptSuffixs("tif|tpk|shp|vtpk|mmpk|geodatabase");
                                        Intent f=new Intent(MainActivity.this,FileChooserActivity.class);
                                        startActivityForResult(f, REQUEST_CHOOSER);
                                    }
                                }).show();
                        Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }else{
                        if (listView.getVisibility() == View.GONE){
                            listView.setVisibility(View.VISIBLE);
                        }else{
                            listView.setVisibility(View.GONE);
                        }
                    }
                    recyclerView.setVisibility(View.GONE);
                    break;
                case R.id.drawing:
                    if (hasmap){
                        if (recyclerView.getVisibility() == View.GONE){
                            recyclerView.setVisibility(View.VISIBLE);
                        }else{
                            recyclerView.setVisibility(View.GONE);
                        }
                        down = false;
                        move = false;
                        writeText = false;
                        drawline = false;
                        next.setVisibility(View.VISIBLE);
                        recyclerAdapter.setSelectItem(-1);
                        recyclerAdapter.notifyDataSetChanged();
                        listView.setVisibility(View.GONE);
                        view1.setVisibility(View.GONE);
                        view2.setVisibility(View.GONE);
                        scale1.setVisibility(View.GONE);
                        scale2.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.VISIBLE);
                        close.setVisibility(View.GONE);
                        measure.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                        FY.setVisibility(View.GONE);
                        fy.setVisibility(View.GONE);
                        li1.setVisibility(View.GONE);
                        li2.setVisibility(View.GONE);
                        xtx5.setVisibility(View.GONE);
                        xtx6.setVisibility(View.GONE);
                        camera.setVisibility(View.GONE);
                    }
                    break;
                case R.id.add:
                    if (hasmap == true){
                        i1=1;
                        //画虚线
                        simpleLineSymbolX = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH, Color.BLUE, 2);
                        graphicsOverlay_2 = new GraphicsOverlay();
                        mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
                        graphicsOverlays_point.add(graphicsOverlay_2);
                        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
                        mp= mMapView.screenToLocation(p);
                        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
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
                                    if (imm!=null){
                                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
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
        graphicsOverlay_point = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(graphicsOverlay_point);
        graphicsOverlays_point.add(graphicsOverlay_point);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        //mp= (Point) GeometryEngine.project(mMapView.screenToLocation(p),SpatialReference.create(4326));
        mp= (Point) mMapView.screenToLocation(p);
        centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
        seeback_point.add(mp.getX(),mp.getY(),mp.getZ());
        seeback_line.clear();
        final PictureMarkerSymbol pictureMarkerSymbol5 = new PictureMarkerSymbol(bitmapDrawable5);
        pictureMarkerSymbol5.loadAsync();

        pictureMarkerSymbol5.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol5);
                listph.add(ph);
                graphicsOverlay_point.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        graphicsOverlay_point.getGraphics().add(new Graphic(mp,textSymbol));
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
        graphicsOverlay_line = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(graphicsOverlay_line);
        graphicsOverlays_line.add(graphicsOverlay_line);
        p = new android.graphics.Point(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        mp= mMapView.screenToLocation(p);
        pictureMarkerSymbol4 = new PictureMarkerSymbol(bitmapDrawable4);
        pictureMarkerSymbol4.loadAsync();
        pictureMarkerSymbol4.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                Graphic ph = new Graphic(mp, pictureMarkerSymbol4);
                listph.add(ph);
                graphicsOverlay_line.getGraphics().add(listph.get(listph.size() - 1));
            }
        });
        centerpoint1.add(mp.getX(),mp.getY(),mp.getZ());
        centerpoint.add(mp.getX(),mp.getY(),mp.getZ());
        seeback_line.add(mp.getX(),mp.getY(),mp.getZ());
        seeback_point.clear();
        Log.i("TAG","size="+centerpoint.size());
        if (centerpoint.size() >= 2) {
            Polyline polyline = new Polyline(centerpoint);
            Graphic line = new Graphic(polyline, simpleLineSymbol);
            listline.add(line);
            graphicsOverlay_line.getGraphics().add(listline.get(listline.size() - 1));
            Graphic ts = new Graphic(polyline, textSymbol);
            listtext.add(ts);
            graphicsOverlay_line.getGraphics().add(listtext.get(listtext.size() - 1));
            if (centerpoint.size()>2){
                mMapView.getGraphicsOverlays().remove(graphicsOverlays_line.get(graphicsOverlays_line.size()-2));
                graphicsOverlays_line.remove(graphicsOverlays_line.size()-2);
            }
            linela.add(String.valueOf(centerpoint.get(centerpoint.size()-1).getX()));
            lineln.add(String.valueOf(centerpoint.get(centerpoint.size()-1).getY()));
            ContentValues values = new ContentValues();
            values.put("xla", StringUtils.join(linela,","));
            values.put("xln",StringUtils.join(lineln,","));
            db.update("Newlines"+pposition,values,"xla = ?",new String[] {StringUtils.join(linelist.get(linelist.size()-1).getListla(),",")});
            Log.i("TAG","la2="+StringUtils.join(linelist.get(linelist.size()-1).getListla(),","));
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
            MoreLines moreLines = new MoreLines();
            moreLines.setListla(Arrays.asList(StringUtils.join(linela,",").split(",")));
            moreLines.setListln(Arrays.asList(StringUtils.join(lineln,",").split(",")));
            linelist.add(moreLines);
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
                Polyline polyline1 = new Polyline(p1);
                Polyline polyline2 = new Polyline(p2);
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
                    mp = mMapView.screenToLocation(p);
                    graphicsOverlay_2 = new GraphicsOverlay();
                    mMapView.getGraphicsOverlays().add(graphicsOverlay_2);
                    BitmapDrawable b = (BitmapDrawable) getResources().getDrawable(R.mipmap.camera2);
                    TextSymbol t = new TextSymbol(12f, textp, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP);
                    centerpoint1.add(mp.getX(), mp.getY(), mp.getZ());
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
                    seeback_point.add(mp.getX(),mp.getY(),mp.getZ());
                    seeback_line.clear();
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
                break;
            case REQUEST_CHOOSER:
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    String path = FileUtils.getPath(this, uri);
                    if (path != null && FileUtils.isLocal(path)) {
                        File file = new File(path);
                        if (file.toString().indexOf(".")!=-1){
                            loadLayer(file.toString(),String.valueOf(Color.BLACK),"1");
                            LayoutAttributes layoutAttributes = new LayoutAttributes();
                            layoutAttributes.setName(file.toString());
                            layoutAttributes.setColor(Color.BLACK);
                            if (listlayout.size()>0){
                                if (file.toString().substring(file.toString().indexOf(".")).equals(".shp")||file.toString().substring(file.toString().indexOf(".")).equals(".geodatabase")){
                                    listlayout.add(0,layoutAttributes);
                                    urllist.add(0,layoutAttributes.getName());
                                    colorlist.add(0,String.valueOf(layoutAttributes.getColor()));
                                    widthlist.add(0,"1");
                                }else {
                                    listlayout.clear();
                                    urllist.clear();
                                    colorlist.clear();
                                    widthlist.clear();
                                    listlayout.add(layoutAttributes);
                                    urllist.add(layoutAttributes.getName());
                                    colorlist.add(String.valueOf(layoutAttributes.getColor()));
                                    widthlist.add(0,"1");
                                }
                            }else {
                                listlayout.add(layoutAttributes);
                                urllist.add(layoutAttributes.getName());
                                colorlist.add(String.valueOf(layoutAttributes.getColor()));
                                widthlist.add(0,"1");
                            }
                            adapter.notifyDataSetChanged();
                            UpdateUrl(urllist.toString());
                            UpdateColor(colorlist.toString(),widthlist.toString());
                        }else{
                            Toast.makeText(MainActivity.this, "请选择有效文件！！！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
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
