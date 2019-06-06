package com.arcgis.mymap.Export;

import android.os.Environment;

import com.arcgis.mymap.contacts.LitepalPoints;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */
public class WriteKml {
    boolean mExternalStorageAvailable = false;
    boolean mExternalStorageWriteable = false;

    /**
     * 传入两个参数，一个是kml名称，第二个是坐标点list
     */
    public void  createKml(String dirName,List<LitepalPoints> list,String exprotpath)throws Exception{

        Element root= DocumentHelper.createElement("kml");
        Document document=DocumentHelper.createDocument(root);
        //给根节点kml添加属性
        root.addAttribute("xmlns", "http://www.opengis.net/kml/2.2")
                .addAttribute("xmlns:gx", "http://www.google.com/kml/ext/2.2")
                .addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
                .addAttribute("xsi:schemaLocation", "http://www.opengis.net/kml/2.2 http://schemas.opengis.net/kml/2.2.0/ogckml22.xsd http://www.google.com/kml/ext/2.2 http://code.google.com/apis/kml/schema/kml22gx.xsd");
        //给根节点kml添加子节点  Document
        Element documentElement=root.addElement("Document");
        //循环添加每一个Placemark节点，有几个坐标点就有几个Placemark节点
        for (int i=0;i<list.size();i++){
            Element placemarkElement=documentElement.addElement("Placemark");
            placemarkElement.addElement("name").addText(list.get(i).getName());
            placemarkElement.addElement("description").addText(list.get(i).getDescription());
            Element pointElement=placemarkElement.addElement("Point");
            pointElement.addElement("coordinates").addText(String.valueOf(list.get(i).getLa())+","+String.valueOf(list.get(i).getLn())+","+String.valueOf(list.get(i).getHigh()));
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
        File file=new File(exprotpath+"/航测/Export");
        if (!file.exists()) {
            file.mkdirs();
        }
            String path=exprotpath+"/航测/Export/"+dirName+".kml";
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
