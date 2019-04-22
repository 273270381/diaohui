package com.arcgis.mymap.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.arcgis.mymap.Geological.GeologicalMapActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.SurFace;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.LineSymbol;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2018/5/1.
 */

public class GeoShowPointUtils {
    final static int CANSHUA= 1;
    final static int CANSHUB= 2;
    final static int CANSHUC= 3;

    public static void ShowAllPointsUtils(final Context context, ExecutorService threadpool, final List<LitepalPoints> pointsList,
                                          final List<MoreLines> linesList, final List<SurFace> surFaces, final MapView mapView){
         final String[] data3 = {"岩溶","滑坡","危岩崩塌与岩堆","泥石流","积雪","雪崩","风沙","采空区","水库坍岸","强震区","地震液化","涎流冰"};
        final  Handler phander = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg!=null){
                    switch (msg.what){
                        case CANSHUA:
                            String name = msg.getData().getString("name");
                            double la= msg.getData().getDouble("la");
                            double ln = msg.getData().getDouble("ln");
                            double high = msg.getData().getDouble("high");
                            String classification = msg.getData().getString("classification");
                            String des = msg.getData().getString("description");
                            final Point showpoint=new Point(la,ln,high, SpatialReferences.getWgs84());
                            final GraphicsOverlay graphicsOverlayshow=new GraphicsOverlay();
                            mapView.getGraphicsOverlays().add(graphicsOverlayshow);
                            if (classification.equals("地调点")){
                                BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.didiaodian);
                                final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                pictureMarkerSymbol2.loadAsync();
                                pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        Graphic ph = new Graphic(showpoint, pictureMarkerSymbol2);
                                        graphicsOverlayshow.getGraphics().add(ph);
                                    }
                                });
                                TextSymbol textSymbolh = new TextSymbol(12f, name, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                graphicsOverlayshow.getGraphics().add(new Graphic(showpoint,textSymbolh));
                            }
                            if (classification.equals("产状")){
                                BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.cz);
                                final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                pictureMarkerSymbol2.loadAsync();
                                pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        Graphic ph = new Graphic(showpoint, pictureMarkerSymbol2);
                                        graphicsOverlayshow.getGraphics().add(ph);
                                    }
                                });
                                TextSymbol textSymbolh = new TextSymbol(12f, name, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                graphicsOverlayshow.getGraphics().add(new Graphic(showpoint,textSymbolh));
                            }
                            if (classification.equals("节理")){
                                BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.jl);
                                final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                pictureMarkerSymbol2.loadAsync();
                                pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        Graphic ph = new Graphic(showpoint, pictureMarkerSymbol2);
                                        graphicsOverlayshow.getGraphics().add(ph);
                                    }
                                });
                                TextSymbol textSymbolh = new TextSymbol(12f, name, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                graphicsOverlayshow.getGraphics().add(new Graphic(showpoint,textSymbolh));
                            }
                            if (classification.equals("地质时代")){
                                BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.point1);
                                final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                pictureMarkerSymbol2.loadAsync();
                                pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        Graphic ph = new Graphic(showpoint, pictureMarkerSymbol2);
                                        graphicsOverlayshow.getGraphics().add(ph);
                                    }
                                });
                                TextSymbol textSymbolh = new TextSymbol(12f, name, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                graphicsOverlayshow.getGraphics().add(new Graphic(showpoint,textSymbolh));
                            }
                            if (classification.indexOf("p")!=-1){
                                BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.camera2);
                                final PictureMarkerSymbol pictureMarkerSymbol2 = new PictureMarkerSymbol(bitmapDrawable);
                                pictureMarkerSymbol2.loadAsync();
                                pictureMarkerSymbol2.addDoneLoadingListener(new Runnable() {
                                    @Override
                                    public void run() {
                                        Graphic ph = new Graphic(showpoint, pictureMarkerSymbol2);
                                        graphicsOverlayshow.getGraphics().add(ph);
                                    }
                                });
                                TextSymbol textSymbolh = new TextSymbol(12f, name, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                                graphicsOverlayshow.getGraphics().add(new Graphic(showpoint,textSymbolh));
                            }
                            break;
                        case CANSHUB:
                            List<String> gla = (List<String>) msg.getData().get("gla");
                            List<String> gln = (List<String>) msg.getData().get("gln");
                            String clas = msg.getData().getString("classification");
                            if (!useList(data3,clas)){
                                final GraphicsOverlay graphicsOverlayshowH=new GraphicsOverlay();
                                mapView.getGraphicsOverlays().add(graphicsOverlayshowH);
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
                                mapView.getGraphicsOverlays().add(graphicsOverlayshowH);
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
                            break;
                        case CANSHUC:
                            List<String> mgla = (List<String>) msg.getData().get("gla");
                            List<String> mgln = (List<String>) msg.getData().get("gln");
                            String mclas = msg.getData().getString("classification");
                            final GraphicsOverlay graphicsOverlayshowM=new GraphicsOverlay();
                            mapView.getGraphicsOverlays().add(graphicsOverlayshowM);
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
                            break;
                    }
                }
            }
        };
        threadpool.execute(new Runnable() {
            @Override
            public void run() {
                if (pointsList.size()!=0){
                    for (int a = 0 ; a <= pointsList.size()-1 ; a ++ ){
                        String name = pointsList.get(a).getName();
                        double la = Double.parseDouble(pointsList.get(a).getLa());
                        double ln = Double.parseDouble(pointsList.get(a).getLn());
                        double high = Double.parseDouble(pointsList.get(a).getHigh());
                        String classification = pointsList.get(a).getClassification();
                        String des = pointsList.get(a).getDescription();
                        Message msg = new Message();
                        msg.what = GeologicalMapActivity.CANSHUA;
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putDouble("la",la);
                        bundle.putDouble("ln",ln);
                        bundle.putDouble("high",high);
                        bundle.putString("classification",classification);
                        bundle.putString("description",des);
                        msg.setData(bundle);
                        phander.sendMessage(msg);
                    }
                }
                if (linesList.size()!=0){
                    for (int a = 0 ; a <= linesList.size()-1 ; a ++){
                        List<String> gla = new ArrayList<>(linesList.get(a).getListla());
                        List<String> gln = new ArrayList<>(linesList.get(a).getListln());
                        String classification = linesList.get(a).getClassification();
                        String des = linesList.get(a).getDescription();
                        Message msg = new Message();
                        msg.what = GeologicalMapActivity.CANSHUB;
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("gla", (ArrayList<String>) gla);
                        bundle.putStringArrayList("gln", (ArrayList<String>) gln);
                        bundle.putString("classification",classification);
                        bundle.putString("des",des);
                        msg.setData(bundle);
                        phander.sendMessage(msg);
                    }
                }
                if (surFaces.size()!=0){
                    for (int a = 0 ; a <= surFaces.size()-1 ; a ++){
                        List<String> gla = new ArrayList<>(surFaces.get(a).getListla());
                        List<String> gln = new ArrayList<>(surFaces.get(a).getListln());
                        String classification = surFaces.get(a).getClassification();
                        String des = surFaces.get(a).getDescription();
                        Message msg = new Message();
                        msg.what = GeologicalMapActivity.CANSHUC;
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("gla", (ArrayList<String>) gla);
                        bundle.putStringArrayList("gln", (ArrayList<String>) gln);
                        bundle.putString("classification",classification);
                        bundle.putString("des",des);
                        msg.setData(bundle);
                        phander.sendMessage(msg);
                    }
                }
            }
        });
    }
    public static boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
}
