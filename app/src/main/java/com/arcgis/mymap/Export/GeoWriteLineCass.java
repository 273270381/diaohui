package com.arcgis.mymap.Export;

import android.os.Environment;

import com.arcgis.mymap.contacts.Lines;
import com.arcgis.mymap.contacts.MoreLines;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class GeoWriteLineCass {
    public void create(String filename, List<MoreLines> list,String exportpath){
        String path=createFile(filename,exportpath);
        try {
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                String context=list.get(i).getClassification()+","+list.get(i).getCode()+","+list.get(i).getListla().get(0)+","+list.get(i).getListln().get(0)+",0.0"+"\r\n";
                for (int k = 1;k<list.get(i).getListla().size();k++){
                    context = context+list.get(i).getClassification()+",+,"+list.get(i).getListla().get(k)+","+list.get(i).getListln().get(k)+",0.0"+"\r\n";
                }
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void creatWgs84(String filename, List<MoreLines> list,String exportpath){
        String path=createFile(filename,exportpath);
        try {
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                String context=list.get(i).getClassification()+","+list.get(i).getCode()+","+list.get(i).getListla().get(0)+","+list.get(i).getListln().get(0)+",0.0"+"\r\n";
                for (int k = 1;k<list.get(i).getListla().size();k++){
                    context = context+list.get(i).getClassification()+",+,"+list.get(i).getListla().get(k)+","+list.get(i).getListln().get(k)+",0.0"+"\r\n";
                }
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createbeijing54(String filename, List<MoreLines> list,String exportpath){
        String path=createFile(filename,exportpath);
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                PointCollection pointCollection = new PointCollection(SpatialReference.create(2435));
                for (int k = 0;k<list.get(i).getListla().size();k++){
                    Point p = new Point(Double.parseDouble(list.get(i).getListla().get(k)),Double.parseDouble(list.get(i).getListln().get(k)),SpatialReferences.getWgs84());
                    Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(2435));
                    pointCollection.add(mp);
                }
                String context = list.get(i).getClassification()+","+list.get(i).getCode()+","+String.valueOf(pointCollection.get(0).getX())+","+String.valueOf(pointCollection.get(0).getY())+",0.0"+"\r\n";
                for (int j = 1;j<pointCollection.size();j++){
                    context = context + list.get(i).getClassification()+",+,"+String.valueOf(pointCollection.get(j).getX())+","+String.valueOf(pointCollection.get(j).getY())+",0.0"+"\r\n";
                }
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createxian80(String filename,List<MoreLines> list,String exportpath){
        String path=createFile(filename,exportpath);
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), Charset.forName("GB2312"));
            for (int i=0;i<list.size();i++){
                PointCollection pointCollection = new PointCollection(SpatialReference.create(2383));
                for (int k = 0;k<list.get(i).getListla().size();k++){
                    Point p = new Point(Double.parseDouble(list.get(i).getListla().get(k)),Double.parseDouble(list.get(i).getListln().get(k)),SpatialReferences.getWgs84());
                    Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(2383));
                    pointCollection.add(mp);
                }
                String context = list.get(i).getClassification()+","+list.get(i).getCode()+","+String.valueOf(pointCollection.get(0).getX())+","+String.valueOf(pointCollection.get(0).getY())+",0.0"+"\r\n";
                for (int j = 1;j<pointCollection.size();j++){
                    context = context + list.get(i).getClassification()+",+,"+String.valueOf(pointCollection.get(j).getX())+","+String.valueOf(pointCollection.get(j).getY())+",0.0"+"\r\n";
                }
                oStreamWriter.append(context);

               /* Point p1=new Point(Double.parseDouble(list.get(i).getXla()),Double.parseDouble(list.get(i).getXln()), SpatialReferences.getWgs84());
                Point mp1= (Point) GeometryEngine.project(p1, SpatialReference.create(2383));
                Point p2=new Point(Double.parseDouble(list.get(i).getYla()),Double.parseDouble(list.get(i).getYln()), SpatialReferences.getWgs84());
                Point mp2= (Point) GeometryEngine.project(p2, SpatialReference.create(2383));
                String context=list.get(i).getClassification()+","+list.get(i).getCode()+","+String.valueOf(mp1.getX()+","+mp1.getY()+","+mp1.getZ()+"\n")+
                        list.get(i).getClassification()+",+,"+String.valueOf(mp2.getX()+","+mp2.getY()+","+mp2.getZ()+"\n");
                oStreamWriter.flush();
                oStreamWriter.append(context);*/
            }
            oStreamWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createguojia2000(String filename,List<MoreLines> list,String exportpath){
        String path=createFile(filename,exportpath);
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                PointCollection pointCollection = new PointCollection(SpatialReference.create(4547));
                for (int k = 0;k<list.get(i).getListla().size();k++){
                    Point p = new Point(Double.parseDouble(list.get(i).getListla().get(k)),Double.parseDouble(list.get(i).getListln().get(k)),SpatialReferences.getWgs84());
                    Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(4547));
                    pointCollection.add(mp);
                }
                String context = list.get(i).getClassification()+","+list.get(i).getCode()+","+String.valueOf(pointCollection.get(0).getX())+","+String.valueOf(pointCollection.get(0).getY())+",0.0"+"\r\n";
                for (int j = 1;j<pointCollection.size();j++){
                    context = context + list.get(i).getClassification()+",+,"+String.valueOf(pointCollection.get(j).getX())+","+String.valueOf(pointCollection.get(j).getY())+",0.0"+"\r\n";
                }
                oStreamWriter.append(context);

                /*Point p1=new Point(Double.parseDouble(list.get(i).getXla()),Double.parseDouble(list.get(i).getXln()), SpatialReferences.getWgs84());
                Point mp1= (Point) GeometryEngine.project(p1, SpatialReference.create(4547));
                Point p2=new Point(Double.parseDouble(list.get(i).getYla()),Double.parseDouble(list.get(i).getYln()), SpatialReferences.getWgs84());
                Point mp2= (Point) GeometryEngine.project(p2, SpatialReference.create(4547));
                String context=list.get(i).getClassification()+","+list.get(i).getCode()+","+String.valueOf(mp1.getX()+","+mp1.getY()+","+mp1.getZ()+"\n")+
                        list.get(i).getClassification()+",+,"+String.valueOf(mp2.getX()+","+mp2.getY()+","+mp2.getZ()+"\n");
                oStreamWriter.append(context);*/
            }
            oStreamWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String createFile(String filename,String exportpath){
        File file=new File(exportpath+"/地勘/Export");
        if (!file.exists()){
            file.mkdirs();
        }
        String path=exportpath+"/地勘/Export/"+filename+".dat";
        File file1=new File(path);
        if (file1.exists()){
            file1.delete();
        }
        return path;
    }
}
