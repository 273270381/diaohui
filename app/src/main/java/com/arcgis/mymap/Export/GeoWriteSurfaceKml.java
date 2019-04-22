package com.arcgis.mymap.Export;

import android.os.Environment;

import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.SurFace;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class GeoWriteSurfaceKml {
    boolean mExternalStorageAvailable = false;
    boolean mExternalStorageWriteable = false;
    public void  createKml(String dirName,List<SurFace> list)throws Exception{
        Element root= DocumentHelper.createElement("kml");
        Document document=DocumentHelper.createDocument(root);
        //给根节点kml添加属性
        root.addAttribute("xmlns", "http://www.opengis.net/kml/2.2")
                .addAttribute("xmlns:gx", "http://www.google.com/kml/ext/2.2")
                .addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
                .addAttribute("xsi:schemaLocation", "http://www.opengis.net/kml/2.2 http://schemas.opengis.net/kml/2.2.0/ogckml22.xsd http://www.google.com/kml/ext/2.2 http://code.google.com/apis/kml/schema/kml22gx.xsd");
        //给根节点kml添加子节点  Document
        Element documentElement=root.addElement("Document");
        Element styleElement = documentElement.addElement("Style");
        styleElement.addAttribute("id","0");
        Element linestyle = styleElement.addElement("LineStyle");
        linestyle.addElement("color").addText("ff0000ff");
        linestyle.addElement("width").addText("1");
        Element polystyle = styleElement.addElement("PolyStyle");
        polystyle.addElement("color").addText("40ff0000");
        Element folder =documentElement.addElement("Folder");
        folder.addElement("name").addText("任务范围");
        //循环添加每一个Placemark节点，有几个坐标点就有几个Placemark节点
        for (int i=0;i<list.size();i++){
            String text = "";
            for (int k = 0;k<list.get(i).getListla().size();k++){
                text = text +list.get(i).getListla().get(k)+","+list.get(i).getListln().get(k)+","+"0"+",";
            }
            text = text + list.get(i).getListla().get(0)+","+list.get(i).getListln().get(0)+","+"0";
            Element placemarkElement=folder.addElement("Placemark");
            placemarkElement.addElement("name").addText(list.get(i).getDescription());
            placemarkElement.addElement("description").addText(list.get(i).getClassification());
            placemarkElement.addElement("styleUrl").addText("#0");
            Element Polygon = placemarkElement.addElement("Polygon");
            Polygon.addElement("tessellate").addText("1");
            Element outer = Polygon.addElement("outerBoundaryIs");
            Element linear = outer.addElement("LinearRing");
            linear.addElement("coordinates").addText(text);
        }
        String state= Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            mExternalStorageAvailable=mExternalStorageWriteable=true;
        }else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            mExternalStorageAvailable=true;
            mExternalStorageWriteable=false;
        }else {
            mExternalStorageWriteable=mExternalStorageAvailable=false;
        }
        //将生成的kml写出本地
        OutputFormat format=OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");//设置编码格式
        format.setNewlines(true);//设置换行
        //将doc.kml写入到/mnt/sdcard/Mymap/doc.kml目录
        File file=new File(Environment.getExternalStorageDirectory()+"/MyMap/地勘/Export");
        if (!file.exists()) {
            file.mkdirs();
        }
        String path=Environment.getExternalStorageDirectory()+"/Mymap/地勘/Export/"+dirName+".kml";
        File file1=new File(path);
        if (file1.exists()){
            file1.delete();
        }
        file1.createNewFile();
        FileOutputStream outputStream=new FileOutputStream(path);
        XMLWriter xmlWriter = new XMLWriter(outputStream,format);
        xmlWriter.write(document);
        xmlWriter.close();
    }
}
