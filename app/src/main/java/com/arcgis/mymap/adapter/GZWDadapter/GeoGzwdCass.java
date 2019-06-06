package com.arcgis.mymap.adapter.GZWDadapter;

import android.os.Environment;

import com.arcgis.mymap.contacts.DixingdimaoPoint;
import com.arcgis.mymap.contacts.GouzhuwuPoint;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class GeoGzwdCass {
    public void create(String filename, List<GouzhuwuPoint> list,String exportpath){
        String path=createFile(filename,exportpath);
        try {
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                Point mp=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()));
                String context = "";
                if (list.get(i).getClassification().equals("地质时代")){
                    context = ","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }else {
                    context=list.get(i).getName()+","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void creatWgs84(String filename, List<GouzhuwuPoint> list,String exportpath){
        String path=createFile(filename,exportpath);
        try {
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()), SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(3395));
                String context = "";
                if (list.get(i).getClassification().equals("地质时代")){
                    context = ","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }else {
                    context=list.get(i).getName()+","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createbeijing54(String filename, List<GouzhuwuPoint> list,String exportpath){
        String path=createFile(filename,exportpath);
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(2435));
                //String context=list.get(i).getName()+","+String.valueOf(list.get(i).getId()+","+mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                String context = "";
                if (list.get(i).getClassification().equals("地质时代")){
                    context = ","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }else {
                    context=list.get(i).getName()+","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createxian80(String filename,List<GouzhuwuPoint> list,String exportpath){
        String path=createFile(filename,exportpath);
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(2383));
                //String context=list.get(i).getName()+","+String.valueOf(list.get(i).getId()+","+mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                String context = "";
                if (list.get(i).getClassification().equals("地质时代")){
                    context = ","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }else {
                    context=list.get(i).getName()+","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }
                oStreamWriter.append(context);
            }
            oStreamWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void createguojia2000(String filename,List<GouzhuwuPoint> list,String exportpath){
        String path=createFile(filename,exportpath);
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "GB2312");
            for (int i=0;i<list.size();i++){
                Point p=new Point(Double.parseDouble(list.get(i).getLa()),Double.parseDouble(list.get(i).getLn()),Double.parseDouble(list.get(i).getHigh()),SpatialReferences.getWgs84());
                Point mp= (Point) GeometryEngine.project(p, SpatialReference.create(4547));
                //String context=list.get(i).getName()+","+String.valueOf(list.get(i).getId()+","+mp.getX()+","+mp.getY()+","+mp.getZ()+"\n");
                String context = "";
                if (list.get(i).getClassification().equals("地质时代")){
                    context = ","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }else {
                    context=list.get(i).getName()+","+list.get(i).getCode()+","+String.valueOf(mp.getX()+","+mp.getY()+","+mp.getZ()+"\r\n");
                }
                oStreamWriter.append(context);
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
