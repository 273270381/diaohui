package com.arcgis.mymap.Export;

import android.app.Activity;
import android.os.Environment;

import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.utils.LogUtils;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.internal.jni.CoreSpatialReference;
import com.esri.arcgisruntime.mapping.view.MapView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by Administrator on 2018/2/4.
 */

public class WriteCASS {
    public void creatWgs84(String filename, List<LitepalPoints> list){
            String path=createFile(filename);
        try {
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(3395));
                String context=list.get(i).getClassification()+","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                //outputStream.write(context.getBytes());
                //outputStream.flush();
                oStreamWriter.append(context);
            }
            /*for (int i = 0;i<10 ; i++){
                //String context = "D0000"+i+","+"b00"+i+","+"0.000,"+"节理"+",3401011,RESPT"+"\r\n";
                //String context = "D0000"+i+",DLDW,2,b00"+i+",0,节理"+"\r\n";
                String context = String.valueOf(361+i)+"d"+","+"D0000"+i+"\r\n";
                oStreamWriter.append(context);
            }
            for (int  i =10;i<100;i++){
                //String context = "D000"+i+","+"b0"+i+","+"0.000,"+"节理"+",3401011,RESPT"+"\r\n";
                //String context = "D000"+i+",DLDW,2,b0"+i+",0,节理"+"\r\n";
                String context =String.valueOf(361+i)+"d"+","+"D000"+i+"\r\n";
                oStreamWriter.append(context);
            }
            for (int i =100;i<=360;i++){
                //String context = "D00"+i+","+"b"+i+","+"0.000,"+"节理"+",3401011,RESPT"+"\r\n";
                //String context = "D00"+i+",DLDW,2,b"+i+",0,节理"+"\r\n";
                String context = String.valueOf(361+i)+"d"+","+"D00"+i+"\r\n";
                oStreamWriter.append(context);
            }*/
            oStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createbeijing54(String filename, List<LitepalPoints> list){
        String path=createFile(filename);
        /*try{
            FileOutputStream outputStream=new FileOutputStream(path);
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(2435));
                //String context=list.get(i).getName()+","+String.valueOf(list.get(i).getId()+","+mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                String context=list.get(i).getName()+","+list.get(i).getClassification()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                outputStream.write(context.getBytes());
                outputStream.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(2435));
                //String context=list.get(i).getName()+","+String.valueOf(list.get(i).getId()+","+mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                String context=list.get(i).getClassification()+","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createxian80(String filename,List<LitepalPoints> list){
        String path=createFile(filename);
       /* try{
            FileOutputStream outputStream=new FileOutputStream(path);
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(2383));
                //String context=list.get(i).getName()+","+String.valueOf(list.get(i).getId()+","+mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                String context=list.get(i).getClassification()+","list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                outputStream.write(context.getBytes());
                outputStream.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(2383));
                //String context=list.get(i).getName()+","+String.valueOf(list.get(i).getId()+","+mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                String context=list.get(i).getClassification()+","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createguojia2000(String filename,List<LitepalPoints> list){
        String path=createFile(filename);
        /*try{
            FileOutputStream outputStream=new FileOutputStream(path);
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(4547));
                //String context=list.get(i).getName()+","+String.valueOf(list.get(i).getId()+","+mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                String context=list.get(i).getClassification()+","list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                outputStream.write(context.getBytes());
                outputStream.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(4547));
                //String context=list.get(i).getName()+","+String.valueOf(list.get(i).getId()+","+mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                String context=list.get(i).getClassification()+","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String createFile(String filename){
        File file=new File(Environment.getExternalStorageDirectory()+"/MyMap/航测/Export");
        if (!file.exists()){
            file.mkdirs();
        }
        String path=Environment.getExternalStorageDirectory()+"/Mymap/航测/Export/"+filename+".dat";
        File file1=new File(path);
        if (file1.exists()){
            file1.delete();
        }
        return path;
    }
}
