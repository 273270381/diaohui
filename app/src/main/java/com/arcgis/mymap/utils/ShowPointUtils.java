package com.arcgis.mymap.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.arcgis.mymap.MainActivity;
import com.arcgis.mymap.NewDataActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.Lines;
import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.contacts.MoreLines;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2018/3/6.
 */

public class ShowPointUtils {
    final static int CANSHU = 2;
    final static int CANSHUL= 3;

    public static void ShowAllPointUtils(final Context context, ExecutorService threadPool2, final List<LitepalPoints> pointlist,
                                         final List<MoreLines> linelist, final String[] titles, final Integer[] imager, final MapView mMapView,List<GraphicsOverlay> graphicsOverlays_line,
                                         List<Graphic> listph_point,List<Graphic> listline){
        final NewDataActivity newDataActivity=new NewDataActivity();
        final Handler pHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg!=null){
                    switch(msg.what){
                        case CANSHU:
                            double la=msg.getData().getDouble("la");
                            double ln=msg.getData().getDouble("ln");
                            double high=msg.getData().getDouble("high");
                            String classification=msg.getData().getString("classification");
                            String description=msg.getData().getString("description");
                            String time = msg.getData().getString("time");
                            if (Arrays.asList(titles).contains(classification)){
                                int index=newDataActivity.printArrayto(titles,classification);
                                final Point showpoint=new Point(la,ln,high);
                                final GraphicsOverlay graphicsOverlayshow=new GraphicsOverlay();
                                mMapView.getGraphicsOverlays().add(graphicsOverlayshow);
                                BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(imager[index]);
                                final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                pictureMarkerSymbol2.loadAsync();
                                pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        Map<String,Object> map = new HashMap<>();
                                        map.put("style","point");
                                        map.put("time",time);
                                        Graphic ph = new Graphic(showpoint,map, pictureMarkerSymbol2);
                                        graphicsOverlayshow.getGraphics().add(ph);
                                    }
                                });
                                TextSymbol textSymbolh = new TextSymbol(12f, classification, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                Map<String,Object> map2 = new HashMap<>();
                                map2.put("style","text");
                                map2.put("time",time);
                                Graphic graphic_text = new Graphic(showpoint,map2,textSymbolh);
                                graphicsOverlayshow.getGraphics().add(graphic_text);
                            }else {
                                final Point showpoint=new Point(la,ln,high);
                                final GraphicsOverlay graphicsOverlayshow=new GraphicsOverlay();
                                mMapView.getGraphicsOverlays().add(graphicsOverlayshow);
                                if (classification.indexOf("檐")!=-1){
                                    BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.p35);
                                    final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                    pictureMarkerSymbol2.loadAsync();
                                    pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                        @Override
                                        public void run() {
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("style","point");
                                            map.put("time",time);
                                            Graphic ph = new Graphic(showpoint,map,pictureMarkerSymbol2);
                                            graphicsOverlayshow.getGraphics().add(ph);
                                        }
                                    });
                                    TextSymbol textSymbolh = new TextSymbol(12f, classification, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                    Map<String,Object> map2 = new HashMap<>();
                                    map2.put("style","text");
                                    map2.put("time",time);
                                    Graphic graphic_text = new Graphic(showpoint,map2,textSymbolh);
                                    graphicsOverlayshow.getGraphics().add(graphic_text);
                                }else if (classification.indexOf("混")!=-1||classification.indexOf("砖")!=-1||classification.indexOf("简")!=-1||classification.indexOf("砼")!=-1||classification.indexOf("棚")!=-1){
                                    BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.p36);
                                    final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                    pictureMarkerSymbol2.loadAsync();
                                    pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                        @Override
                                        public void run() {
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("style","point");
                                            map.put("time",time);
                                            Graphic ph = new Graphic(showpoint,map, pictureMarkerSymbol2);
                                            graphicsOverlayshow.getGraphics().add(ph);
                                        }
                                    });
                                    TextSymbol textSymbolh = new TextSymbol(12f, classification, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                    Map<String,Object> map2 = new HashMap<>();
                                    map2.put("style","text");
                                    map2.put("time",time);
                                    Graphic graphic_text = new Graphic(showpoint,map2,textSymbolh);
                                    graphicsOverlayshow.getGraphics().add(graphic_text);
                                }else if (classification.indexOf("p")!=-1){
                                    BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.camera2);
                                    final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                    pictureMarkerSymbol2.loadAsync();
                                    pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                        @Override
                                        public void run() {
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("style","point");
                                            map.put("time",time);
                                            Graphic ph = new Graphic(showpoint,map, pictureMarkerSymbol2);
                                            graphicsOverlayshow.getGraphics().add(ph);
                                        }
                                    });
                                    TextSymbol textSymbolh = new TextSymbol(12f, classification, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                    Map<String,Object> map2 = new HashMap<>();
                                    map2.put("style","text");
                                    map2.put("time",time);
                                    Graphic graphic_text = new Graphic(showpoint,map2,textSymbolh);
                                    graphicsOverlayshow.getGraphics().add(graphic_text);
                                } else {
                                    BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.p25);
                                    final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                    pictureMarkerSymbol2.loadAsync();
                                    pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                        @Override
                                        public void run() {
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("style","point");
                                            map.put("time",time);
                                            Graphic ph = new Graphic(showpoint,map, pictureMarkerSymbol2);
                                            graphicsOverlayshow.getGraphics().add(ph);
                                        }
                                    });
                                    TextSymbol textSymbolh = new TextSymbol(12f, classification, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                    Map<String,Object> map2 = new HashMap<>();
                                    map2.put("style","text");
                                    map2.put("time",time);
                                    Graphic graphic_text = new Graphic(showpoint,map2,textSymbolh);
                                    graphicsOverlayshow.getGraphics().add(graphic_text);
                                }
                            }
                            break;
                        case CANSHUL:
                            List<String> xla = (List<String>) msg.getData().get("xla");
                            List<String> xln = (List<String>) msg.getData().get("xln");
                            List<String> linetime = (List<String>) msg.getData().get("linetime");
                            Log.i("TAG","linetime="+linetime);
                            String clas=msg.getData().getString("clas");
                            String time2 = msg.getData().getString("time");
                            BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.mipmap.point1);
                            final PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(bitmapDrawable);
                            if (clas.equals("10kv")||clas.equals("220v")||clas.equals("35kv")){
                                SimpleLineSymbol simpleLineSymbolH = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                                final PointCollection pointCollection=new PointCollection(mMapView.getSpatialReference());
                                for (int a = 0 ; a<= xla.size()-1 ; a++){
                                    pointCollection.add(Double.parseDouble(xla.get(a)),Double.parseDouble(xln.get(a)));
                                }
                                pictureMarkerSymbol.loadAsync();
                                pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int a = 0;a<=pointCollection.size()-1;a++){
                                            GraphicsOverlay graphicsOverlay=new GraphicsOverlay();
                                            mMapView.getGraphicsOverlays().add(graphicsOverlay);
                                            graphicsOverlays_line.add(graphicsOverlay);
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("style","line");
                                            map.put("time",linetime.get(a));
                                            map.put("time2",time2);
                                            Graphic ph = new Graphic(pointCollection.get(a),map,pictureMarkerSymbol);
                                            listph_point.add(ph);
                                            graphicsOverlay.getGraphics().add(ph);
                                            if (a>=1){
                                                PointCollection pointCollection2=new PointCollection(mMapView.getSpatialReference());
                                                pointCollection2.add(pointCollection.get(a-1));
                                                pointCollection2.add(pointCollection.get(a));
                                                Polyline polyline=new Polyline(pointCollection2);
                                                Map<String,Object> map2 = new HashMap<>();
                                                map2.put("style","");
                                                map2.put("time",linetime.get(a));
                                                map2.put("time2",time2);
                                                Graphic line = new Graphic(polyline, map2,simpleLineSymbolH);
                                                listline.add(line);
                                                graphicsOverlay.getGraphics().add(line);
                                            }
                                        }
                                    }
                                });
                                GraphicsOverlay graphicsOverlayshowH=new GraphicsOverlay();
                                mMapView.getGraphicsOverlays().add(graphicsOverlayshowH);
                                Polyline polyline=new Polyline(pointCollection);
                                TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                Graphic graphic_tx = new Graphic(polyline,textSymbolh2);
                                graphicsOverlayshowH.getGraphics().add(graphic_tx);
                            }else if (clas.equals("通讯")){
                                SimpleLineSymbol simpleLineSymbolH = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 2);
                                final PointCollection pointCollection=new PointCollection(mMapView.getSpatialReference());
                                for (int a = 0 ; a<= xla.size()-1 ; a++){
                                    pointCollection.add(Double.parseDouble(xla.get(a)),Double.parseDouble(xln.get(a)));
                                }
                                pictureMarkerSymbol.loadAsync();
                                pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int a = 0;a<=pointCollection.size()-1;a++){
                                            GraphicsOverlay graphicsOverlay=new GraphicsOverlay();
                                            mMapView.getGraphicsOverlays().add(graphicsOverlay);
                                            graphicsOverlays_line.add(graphicsOverlay);
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("style","line");
                                            map.put("time",linetime.get(a));
                                            map.put("time2",time2);
                                            Graphic ph = new Graphic(pointCollection.get(a),map,pictureMarkerSymbol);
                                            listph_point.add(ph);
                                            graphicsOverlay.getGraphics().add(ph);
                                            if (a>=1){
                                                PointCollection pointCollection2=new PointCollection(mMapView.getSpatialReference());
                                                pointCollection2.add(pointCollection.get(a-1));
                                                pointCollection2.add(pointCollection.get(a));
                                                Polyline polyline=new Polyline(pointCollection2);
                                                Map<String,Object> map2 = new HashMap<>();
                                                map2.put("style","");
                                                map2.put("time",linetime.get(a));
                                                map2.put("time2",time2);
                                                Graphic line = new Graphic(polyline, map2,simpleLineSymbolH);
                                                listline.add(line);
                                                graphicsOverlay.getGraphics().add(line);
                                            }
                                        }
                                    }
                                });
                                GraphicsOverlay graphicsOverlayshowH=new GraphicsOverlay();
                                mMapView.getGraphicsOverlays().add(graphicsOverlayshowH);
                                Polyline polyline=new Polyline(pointCollection);
                                TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                Graphic graphic_tx = new Graphic(polyline,textSymbolh2);
                                graphicsOverlayshowH.getGraphics().add(graphic_tx);
                            }else if (clas.equals("单渠")||clas.equals("双渠")||clas.equals("单沟")||clas.equals("双沟")){
                                SimpleLineSymbol simpleLineSymbolH = new SimpleLineSymbol(SimpleLineSymbol.Style.DASH_DOT, Color.CYAN, 2);
                                final PointCollection pointCollection=new PointCollection(mMapView.getSpatialReference());
                                for (int a = 0 ; a<= xla.size()-1 ; a++){
                                    pointCollection.add(Double.parseDouble(xla.get(a)),Double.parseDouble(xln.get(a)));
                                }
                                pictureMarkerSymbol.loadAsync();
                                pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int a = 0;a<=pointCollection.size()-1;a++){
                                            GraphicsOverlay graphicsOverlay=new GraphicsOverlay();
                                            mMapView.getGraphicsOverlays().add(graphicsOverlay);
                                            graphicsOverlays_line.add(graphicsOverlay);
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("style","line");
                                            map.put("time",linetime.get(a));
                                            map.put("time2",time2);
                                            Graphic ph = new Graphic(pointCollection.get(a),map,pictureMarkerSymbol);
                                            listph_point.add(ph);
                                            graphicsOverlay.getGraphics().add(ph);
                                            if (a>=1){
                                                PointCollection pointCollection2=new PointCollection(mMapView.getSpatialReference());
                                                pointCollection2.add(pointCollection.get(a-1));
                                                pointCollection2.add(pointCollection.get(a));
                                                Polyline polyline=new Polyline(pointCollection2);
                                                Map<String,Object> map2 = new HashMap<>();
                                                map2.put("style","");
                                                map2.put("time",linetime.get(a));
                                                map2.put("time2",time2);
                                                Graphic line = new Graphic(polyline, map2,simpleLineSymbolH);
                                                listline.add(line);
                                                graphicsOverlay.getGraphics().add(line);
                                            }
                                        }
                                    }
                                });
                                GraphicsOverlay graphicsOverlayshowH=new GraphicsOverlay();
                                mMapView.getGraphicsOverlays().add(graphicsOverlayshowH);
                                Polyline polyline=new Polyline(pointCollection);
                                TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                Graphic graphic_tx = new Graphic(polyline,textSymbolh2);
                                graphicsOverlayshowH.getGraphics().add(graphic_tx);
                            } else {
                                SimpleLineSymbol simpleLineSymbolH = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                                final PointCollection pointCollection=new PointCollection(mMapView.getSpatialReference());
                                for (int a = 0 ; a<= xla.size()-1 ; a++){
                                    pointCollection.add(Double.parseDouble(xla.get(a)),Double.parseDouble(xln.get(a)));
                                }
                                BitmapDrawable b = (BitmapDrawable) context.getResources().getDrawable(R.mipmap.point1_sel);
                                PictureMarkerSymbol p = new PictureMarkerSymbol(b);
                                p.loadAsync();
                                p.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int a = 0;a<=pointCollection.size()-1;a++){
                                            GraphicsOverlay graphicsOverlay=new GraphicsOverlay();
                                            mMapView.getGraphicsOverlays().add(graphicsOverlay);
                                            graphicsOverlays_line.add(graphicsOverlay);
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("style","line");
                                            map.put("time",linetime.get(a));
                                            map.put("time2",time2);
                                            Graphic ph = new Graphic(pointCollection.get(a),map,p);
                                            listph_point.add(ph);
                                            graphicsOverlay.getGraphics().add(ph);
                                            if (a>=1){
                                                PointCollection pointCollection2=new PointCollection(mMapView.getSpatialReference());
                                                pointCollection2.add(pointCollection.get(a-1));
                                                pointCollection2.add(pointCollection.get(a));
                                                Polyline polyline=new Polyline(pointCollection2);
                                                Map<String,Object> map2 = new HashMap<>();
                                                map2.put("style","");
                                                map2.put("time",linetime.get(a));
                                                map2.put("time2",time2);
                                                Graphic line = new Graphic(polyline, map2,simpleLineSymbolH);
                                                listline.add(line);
                                                graphicsOverlay.getGraphics().add(line);
                                            }
                                        }
                                    }
                                });
                                GraphicsOverlay graphicsOverlayshowH=new GraphicsOverlay();
                                mMapView.getGraphicsOverlays().add(graphicsOverlayshowH);
                                Polyline polyline=new Polyline(pointCollection);
                                TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                Graphic graphic_tx = new Graphic(polyline,textSymbolh2);
                                graphicsOverlayshowH.getGraphics().add(graphic_tx);
                            }
                            break;
                    }
                }
            }
        };
        threadPool2.execute(new Runnable() {
            @Override
            public void run() {
                if (pointlist.size()!=0&&linelist.size()!=0){
                    for (int a=0;a<=pointlist.size()-1;a++){
                        double la=Double.parseDouble(pointlist.get(a).getLa());
                        double ln=Double.parseDouble(pointlist.get(a).getLn());
                        double high=Double.parseDouble(pointlist.get(a).getHigh());
                        String time = pointlist.get(a).getDatetime();
                        String classification=pointlist.get(a).getClassification();
                        String description=pointlist.get(a).getDescription();
                        Message msg=new Message();
                        msg.what= MainActivity.CANSHU;
                        Bundle bundle=new Bundle();
                        bundle.putDouble("la",la);
                        bundle.putDouble("ln",ln);
                        bundle.putDouble("high",high);
                        bundle.putString("classification",classification);
                        bundle.putString("description",description);
                        bundle.putString("time",time);
                        msg.setData(bundle);
                        pHandler.sendMessage(msg);
                        if (a==pointlist.size()-1){
                            for (int b=0;b<=linelist.size()-1;b++){
                                List<String> xla = new ArrayList<>(linelist.get(b).getListla());
                                List<String> xln = new ArrayList<>(linelist.get(b).getListln());
                                List<String> linetime = new ArrayList<>(linelist.get(b).getLinetime());
                                String time2 = linelist.get(b).getDatatime();
                                String  clas=linelist.get(b).getClassification();
                                Message msg2=new Message();
                                msg2.what=MainActivity.CANSHUL;
                                Bundle bundle2=new Bundle();
                                bundle2.putStringArrayList("xla", (ArrayList<String>) xla);
                                bundle2.putStringArrayList("xln", (ArrayList<String>) xln);
                                bundle2.putStringArrayList("linetime", (ArrayList<String>) linetime);
                                bundle2.putString("clas",clas);
                                bundle2.putString("time2",time2);
                                msg2.setData(bundle2);
                                pHandler.sendMessage(msg2);
                            }
                        }
                    }
                }
                if (pointlist.size()==0){
                    for (int b=0;b<=linelist.size()-1;b++){
                        List<String> xla = new ArrayList<>(linelist.get(b).getListla());
                        List<String> xln = new ArrayList<>(linelist.get(b).getListln());
                        List<String> linetime = new ArrayList<>(linelist.get(b).getLinetime());
                        String  clas=linelist.get(b).getClassification();
                        String time = linelist.get(b).getDatatime();
                        Message msg2=new Message();
                        msg2.what=MainActivity.CANSHUL;
                        Bundle bundle2=new Bundle();
                        bundle2.putStringArrayList("xla", (ArrayList<String>) xla);
                        bundle2.putStringArrayList("xln", (ArrayList<String>) xln);
                        bundle2.putStringArrayList("linetime", (ArrayList<String>) linetime);
                        bundle2.putString("clas",clas);
                        bundle2.putString("time",time);
                        msg2.setData(bundle2);
                        pHandler.sendMessage(msg2);
                    }
                }
                if (linelist.size()==0){
                    for (int a=0;a<=pointlist.size()-1;a++) {
                        double la = Double.parseDouble(pointlist.get(a).getLa());
                        double ln = Double.parseDouble(pointlist.get(a).getLn());
                        double high = Double.parseDouble(pointlist.get(a).getHigh());
                        String classification = pointlist.get(a).getClassification();
                        String description = pointlist.get(a).getDescription();
                        String time = pointlist.get(a).getDatetime();
                        Message msg = new Message();
                        msg.what = MainActivity.CANSHU;
                        Bundle bundle = new Bundle();
                        bundle.putDouble("la", la);
                        bundle.putDouble("ln", ln);
                        bundle.putDouble("high", high);
                        bundle.putString("classification", classification);
                        bundle.putString("description", description);
                        bundle.putString("time",time);
                        msg.setData(bundle);
                        pHandler.sendMessage(msg);
                    }
                }
            }
        });
    }
}
