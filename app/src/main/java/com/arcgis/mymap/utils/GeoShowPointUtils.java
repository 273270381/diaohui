package com.arcgis.mymap.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.arcgis.mymap.Geological.GeologicalMapActivity;
import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.DicengyanxingPoint;
import com.arcgis.mymap.contacts.DixingdimaoPoint;
import com.arcgis.mymap.contacts.GouzhuwuPoint;
import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.ShuiwendizhiPoint;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2018/5/1.
 */

public class GeoShowPointUtils {
    final static int DXDM= 1;
    final static int DCYX= 2;
    final static int SWDZ= 3;
    final static int GZW= 4;
    final static int CANSHUB= 5;
    final static int CANSHUC= 6;
    final static int POINT= 7;

    public static void ShowAllPointsUtils(final Context context, ExecutorService threadpool,final List<LitepalPoints> pointsList, final List<DixingdimaoPoint> list_dxdm,final List<DicengyanxingPoint> list_dcyx,
            final List<ShuiwendizhiPoint> list_swdz,final List<GouzhuwuPoint> list_gzw, final List<MoreLines> linesList, final List<SurFace> surFaces, final MapView mapView,List<GraphicsOverlay> graphicsOverlays_line,
                                          List<Graphic> listph_point,List<Graphic> listline){
        final String[] data3 = {"岩溶","滑坡","危岩崩塌与岩堆","泥石流","积雪","雪崩","风沙","采空区","水库坍岸","强震区","地震液化","涎流冰"};
        final  Handler phander = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg!=null){
                    switch (msg.what){
                        case DXDM:
                            String name = msg.getData().getString("name");
                            double la= msg.getData().getDouble("la");
                            double ln = msg.getData().getDouble("ln");
                            double high = msg.getData().getDouble("high");
                            String time = msg.getData().getString("time");
                            final Point showpoint=new Point(la,ln,high);
                            final GraphicsOverlay graphicsOverlayshow=new GraphicsOverlay();
                            mapView.getGraphicsOverlays().add(graphicsOverlayshow);
                            BitmapDrawable bitmapDrawable = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.didiaodian);
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
                            TextSymbol textSymbolh = new TextSymbol(12f, name, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                            Map<String,Object> map2 = new HashMap<>();
                            map2.put("style","text");
                            map2.put("time",time);
                            Graphic graphic_text = new Graphic(showpoint,map2,textSymbolh);
                            graphicsOverlayshow.getGraphics().add(graphic_text);
                            break;
                        case DCYX:
                            String name_dcyx = msg.getData().getString("name");
                            double la_dcyx= msg.getData().getDouble("la");
                            double ln_dcyx = msg.getData().getDouble("ln");
                            double high_dcyx = msg.getData().getDouble("high");
                            String time_dcyx = msg.getData().getString("time");
                            final Point showpoint_dcyx=new Point(la_dcyx,ln_dcyx,high_dcyx);
                            final GraphicsOverlay graphicsOverlayshow_dcyx=new GraphicsOverlay();
                            mapView.getGraphicsOverlays().add(graphicsOverlayshow_dcyx);
                            BitmapDrawable bitmapDrawable_dcyx = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.didiaodian);
                            final PictureMarkerSymbol pictureMarkerSymbol_dcyx = new PictureMarkerSymbol(bitmapDrawable_dcyx);
                            pictureMarkerSymbol_dcyx.loadAsync();
                            pictureMarkerSymbol_dcyx.addDoneLoadingListener(new Runnable() {
                                @Override
                                public void run() {
                                    Map<String,Object> map = new HashMap<>();
                                    map.put("style","point");
                                    map.put("time",time_dcyx);
                                    Graphic ph = new Graphic(showpoint_dcyx,map, pictureMarkerSymbol_dcyx);
                                    graphicsOverlayshow_dcyx.getGraphics().add(ph);
                                }
                            });
                            TextSymbol textSymbolh_dcyx = new TextSymbol(12f, name_dcyx, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                            Map<String,Object> map_dcyx = new HashMap<>();
                            map_dcyx.put("style","text");
                            map_dcyx.put("time",time_dcyx);
                            Graphic graphic_text_dcyx = new Graphic(showpoint_dcyx,map_dcyx,textSymbolh_dcyx);
                            graphicsOverlayshow_dcyx.getGraphics().add(graphic_text_dcyx);
                            break;
                        case SWDZ:
                            String name_swdz = msg.getData().getString("name");
                            double la_swdz= msg.getData().getDouble("la");
                            double ln_swdz = msg.getData().getDouble("ln");
                            double high_swdz = msg.getData().getDouble("high");
                            String time_swdz = msg.getData().getString("time");
                            final Point showpoint_swdz=new Point(la_swdz,ln_swdz,high_swdz);
                            final GraphicsOverlay graphicsOverlayshow_swdz=new GraphicsOverlay();
                            mapView.getGraphicsOverlays().add(graphicsOverlayshow_swdz);
                            BitmapDrawable bitmapDrawable_swdz = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.didiaodian);
                            final PictureMarkerSymbol pictureMarkerSymbol_swdz = new PictureMarkerSymbol(bitmapDrawable_swdz);
                            pictureMarkerSymbol_swdz.loadAsync();
                            pictureMarkerSymbol_swdz.addDoneLoadingListener(new Runnable() {
                                @Override
                                public void run() {
                                    Map<String,Object> map = new HashMap<>();
                                    map.put("style","point");
                                    map.put("time",time_swdz);
                                    Graphic ph = new Graphic(showpoint_swdz,map, pictureMarkerSymbol_swdz);
                                    graphicsOverlayshow_swdz.getGraphics().add(ph);
                                }
                            });
                            TextSymbol textSymbolh_swdz = new TextSymbol(12f, name_swdz, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                            Map<String,Object> map_swdz = new HashMap<>();
                            map_swdz.put("style","text");
                            map_swdz.put("time",time_swdz);
                            Graphic graphic_text_swdz = new Graphic(showpoint_swdz,map_swdz,textSymbolh_swdz);
                            graphicsOverlayshow_swdz.getGraphics().add(graphic_text_swdz);
                            break;
                        case GZW:
                            String name_gzw = msg.getData().getString("name");
                            double la_gzw= msg.getData().getDouble("la");
                            double ln_gzw = msg.getData().getDouble("ln");
                            double high_gzw = msg.getData().getDouble("high");
                            String time_gzw = msg.getData().getString("time");
                            final Point showpoint_gzw=new Point(la_gzw,ln_gzw,high_gzw);
                            final GraphicsOverlay graphicsOverlayshow_gzw=new GraphicsOverlay();
                            mapView.getGraphicsOverlays().add(graphicsOverlayshow_gzw);
                            BitmapDrawable bitmapDrawable_gzw = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.didiaodian);
                            final PictureMarkerSymbol pictureMarkerSymbol_gzw = new PictureMarkerSymbol(bitmapDrawable_gzw);
                            pictureMarkerSymbol_gzw.loadAsync();
                            pictureMarkerSymbol_gzw.addDoneLoadingListener(new Runnable() {
                                @Override
                                public void run() {
                                    Map<String,Object> map = new HashMap<>();
                                    map.put("style","point");
                                    map.put("time",time_gzw);
                                    Graphic ph = new Graphic(showpoint_gzw,map, pictureMarkerSymbol_gzw);
                                    graphicsOverlayshow_gzw.getGraphics().add(ph);
                                }
                            });
                            TextSymbol textSymbolh_gzw = new TextSymbol(12f, name_gzw, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                            Map<String,Object> map_gzw = new HashMap<>();
                            map_gzw.put("style","text");
                            map_gzw.put("time",time_gzw);
                            Graphic graphic_text_gzw = new Graphic(showpoint_gzw,map_gzw,textSymbolh_gzw);
                            graphicsOverlayshow_gzw.getGraphics().add(graphic_text_gzw);
                            break;
                        case POINT:
                            String name_p = msg.getData().getString("name");
                            double la_p= msg.getData().getDouble("la");
                            double ln_p = msg.getData().getDouble("ln");
                            double high_p = msg.getData().getDouble("high");
                            String time_p = msg.getData().getString("time");
                            final Point showpoint_p=new Point(la_p,ln_p,high_p);
                            final GraphicsOverlay graphicsOverlayshow_p=new GraphicsOverlay();
                            mapView.getGraphicsOverlays().add(graphicsOverlayshow_p);
                            BitmapDrawable bitmapDrawable_p = (BitmapDrawable)context. getResources().getDrawable(R.mipmap.camera2);
                            final PictureMarkerSymbol pictureMarkerSymbol_p = new PictureMarkerSymbol(bitmapDrawable_p);
                            pictureMarkerSymbol_p.loadAsync();
                            pictureMarkerSymbol_p.addDoneLoadingListener(new Runnable() {
                                @Override
                                public void run() {
                                    Map<String,Object> map = new HashMap<>();
                                    map.put("style","point");
                                    map.put("time",time_p);
                                    Graphic ph = new Graphic(showpoint_p,map, pictureMarkerSymbol_p);
                                    graphicsOverlayshow_p.getGraphics().add(ph);
                                }
                            });
                            TextSymbol textSymbolh_p = new TextSymbol(12f, name_p, Color.GREEN, TextSymbol.HorizontalAlignment.LEFT, TextSymbol.VerticalAlignment.TOP  );
                            Map<String,Object> map_p = new HashMap<>();
                            map_p.put("style","text");
                            map_p.put("time",time_p);
                            Graphic graphic_text_p = new Graphic(showpoint_p,map_p,textSymbolh_p);
                            graphicsOverlayshow_p.getGraphics().add(graphic_text_p);
                            break;
                        case CANSHUB:
                            List<String> gla = (List<String>) msg.getData().get("gla");
                            List<String> gln = (List<String>) msg.getData().get("gln");
                            List<String> linetime = (List<String>) msg.getData().get("linetime");
                            Log.i("TAG","linetime="+linetime.toString());
                            String clas = msg.getData().getString("classification");
                            String time2 = msg.getData().getString("time");
                            BitmapDrawable b = (BitmapDrawable) context.getResources().getDrawable(R.mipmap.point1);
                            final PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(b);
                            SimpleLineSymbol simpleLineSymbolH = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2);
                            final PointCollection pointCollection=new PointCollection(mapView.getSpatialReference());
                            for (int a = 0 ; a<= gla.size()-1 ; a++){
                                pointCollection.add(Double.parseDouble(gla.get(a)),Double.parseDouble(gln.get(a)));
                            }
                            pictureMarkerSymbol.loadAsync();
                            pictureMarkerSymbol.addDoneLoadingListener(new Runnable() {
                                @Override
                                public void run() {
                                    for (int a = 0;a<=pointCollection.size()-1;a++){
                                        GraphicsOverlay graphicsOverlay=new GraphicsOverlay();
                                        mapView.getGraphicsOverlays().add(graphicsOverlay);
                                        graphicsOverlays_line.add(graphicsOverlay);
                                        Map<String,Object> map = new HashMap<>();
                                        map.put("style","line");
                                        map.put("time",linetime.get(a));
                                        map.put("time2",time2);
                                        Graphic ph = new Graphic(pointCollection.get(a),map,pictureMarkerSymbol);
                                        listph_point.add(ph);
                                        graphicsOverlay.getGraphics().add(ph);
                                        if (a>=1){
                                            PointCollection pointCollection2=new PointCollection(mapView.getSpatialReference());
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
                            mapView.getGraphicsOverlays().add(graphicsOverlayshowH);
                            Polyline polyline=new Polyline(pointCollection);
                            TextSymbol textSymbolh2 = new TextSymbol(12f, clas, Color.GREEN, TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.TOP  );
                            Graphic graphic_tx = new Graphic(polyline,textSymbolh2);
                            graphicsOverlayshowH.getGraphics().add(graphic_tx);
                            break;
                        case CANSHUC:
                            List<String> mgla = (List<String>) msg.getData().get("gla");
                            List<String> mgln = (List<String>) msg.getData().get("gln");
                            Log.i("TAG","mgla="+mgla.toString());
                            Log.i("TAG","mgln="+mgln.toString());
                            String mclas = msg.getData().getString("classification");
                            final GraphicsOverlay graphicsOverlayshowM=new GraphicsOverlay();
                            mapView.getGraphicsOverlays().add(graphicsOverlayshowM);
                            LineSymbol outline = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID,Color.BLUE,1);
                            SimpleFillSymbol simpleFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID,R.color.color29,outline);
                            PointCollection pointCollectionM=new PointCollection(mapView.getSpatialReference());
                            for (int a = 0 ; a<= mgla.size()-1 ; a++){
                                pointCollectionM.add(Double.parseDouble(mgla.get(a)),Double.parseDouble(mgln.get(a)));
                            }
                            pointCollectionM = GeologicalMapActivity.OrderPoint(pointCollectionM);
                            Polygon polygon=new Polygon(pointCollectionM);
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
                        String time = pointsList.get(a).getDatetime();
                        String des = pointsList.get(a).getDescription();
                        Message msg = new Message();
                        msg.what = GeoShowPointUtils.POINT;
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putDouble("la",la);
                        bundle.putDouble("ln",ln);
                        bundle.putDouble("high",high);
                        bundle.putString("classification",classification);
                        bundle.putString("description",des);
                        bundle.putString("time",time);
                        msg.setData(bundle);
                        phander.sendMessage(msg);
                    }
                }
                if (list_dxdm.size()!=0){
                    for (int a = 0 ; a <= list_dxdm.size()-1 ; a ++ ){
                        String name = list_dxdm.get(a).getName();
                        double la = Double.parseDouble(list_dxdm.get(a).getLa());
                        double ln = Double.parseDouble(list_dxdm.get(a).getLn());
                        double high = Double.parseDouble(list_dxdm.get(a).getHigh());
                        String classification = list_dxdm.get(a).getClassification();
                        String time = list_dxdm.get(a).getTime();
                        String des = list_dxdm.get(a).getDescription();
                        Message msg = new Message();
                        msg.what = GeoShowPointUtils.DXDM;
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putDouble("la",la);
                        bundle.putDouble("ln",ln);
                        bundle.putDouble("high",high);
                        bundle.putString("classification",classification);
                        bundle.putString("description",des);
                        bundle.putString("time",time);
                        msg.setData(bundle);
                        phander.sendMessage(msg);
                    }
                }
                if (list_dcyx.size()!=0){
                    for (int a = 0 ; a <= list_dcyx.size()-1 ; a ++ ){
                        String name = list_dcyx.get(a).getName();
                        double la = Double.parseDouble(list_dcyx.get(a).getLa());
                        double ln = Double.parseDouble(list_dcyx.get(a).getLn());
                        double high = Double.parseDouble(list_dcyx.get(a).getHigh());
                        String time = list_dcyx.get(a).getTime();
                        String classification = list_dcyx.get(a).getClassification();
                        String des = list_dcyx.get(a).getDescription();
                        Message msg = new Message();
                        msg.what = GeoShowPointUtils.DCYX;
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putDouble("la",la);
                        bundle.putDouble("ln",ln);
                        bundle.putDouble("high",high);
                        bundle.putString("classification",classification);
                        bundle.putString("description",des);
                        bundle.putString("time",time);
                        msg.setData(bundle);
                        phander.sendMessage(msg);
                    }
                }
                if (list_swdz.size()!=0){
                    for (int a = 0 ; a <= list_swdz.size()-1 ; a ++ ){
                        String name = list_swdz.get(a).getName();
                        double la = Double.parseDouble(list_swdz.get(a).getLa());
                        double ln = Double.parseDouble(list_swdz.get(a).getLn());
                        double high = Double.parseDouble(list_swdz.get(a).getHigh());
                        String time = list_swdz.get(a).getTime();
                        String classification = list_swdz.get(a).getSllx();
                        String des = list_swdz.get(a).getDes();
                        Message msg = new Message();
                        msg.what = GeoShowPointUtils.SWDZ;
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putDouble("la",la);
                        bundle.putDouble("ln",ln);
                        bundle.putDouble("high",high);
                        bundle.putString("classification",classification);
                        bundle.putString("description",des);
                        bundle.putString("time",time);
                        msg.setData(bundle);
                        phander.sendMessage(msg);
                    }
                }
                if (list_gzw.size()!=0){
                    for (int a = 0 ; a <= list_gzw.size()-1 ; a ++ ){
                        String name = list_gzw.get(a).getName();
                        double la = Double.parseDouble(list_gzw.get(a).getLa());
                        double ln = Double.parseDouble(list_gzw.get(a).getLn());
                        double high = Double.parseDouble(list_gzw.get(a).getHigh());
                        String classification = list_gzw.get(a).getClassification();
                        String time = list_gzw.get(a).getTime();
                        String des = list_gzw.get(a).getDescription();
                        Message msg = new Message();
                        msg.what = GeoShowPointUtils.GZW;
                        Bundle bundle = new Bundle();
                        bundle.putString("name",name);
                        bundle.putDouble("la",la);
                        bundle.putDouble("ln",ln);
                        bundle.putDouble("high",high);
                        bundle.putString("classification",classification);
                        bundle.putString("description",des);
                        bundle.putString("time",time);
                        msg.setData(bundle);
                        phander.sendMessage(msg);
                    }
                }
                if (linesList.size()!=0){
                    for (int a = 0 ; a <= linesList.size()-1 ; a ++){
                        List<String> gla = new ArrayList<>(linesList.get(a).getListla());
                        List<String> gln = new ArrayList<>(linesList.get(a).getListln());
                        List<String> linetime = new ArrayList<>(linesList.get(a).getLinetime());
                        String classification = linesList.get(a).getClassification();
                        String des = linesList.get(a).getDescription();
                        String time = linesList.get(a).getDatatime();
                        Message msg = new Message();
                        msg.what = GeoShowPointUtils.CANSHUB;
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("gla", (ArrayList<String>) gla);
                        bundle.putStringArrayList("gln", (ArrayList<String>) gln);
                        bundle.putStringArrayList("linetime", (ArrayList<String>) linetime);
                        bundle.putString("classification",classification);
                        bundle.putString("des",des);
                        bundle.putString("time",time);
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
                        msg.what = GeoShowPointUtils.CANSHUC;
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
