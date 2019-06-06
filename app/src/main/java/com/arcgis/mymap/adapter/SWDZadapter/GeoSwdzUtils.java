package com.arcgis.mymap.adapter.SWDZadapter;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import com.arcgis.mymap.contacts.DicengyanxingPoint;
import com.arcgis.mymap.contacts.DixingdimaoPoint;
import com.arcgis.mymap.contacts.ShuiwendizhiPoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class GeoSwdzUtils {
    //内存地址
    public static String root = Environment.getExternalStorageDirectory()
            .getPath();
    public static void writeExcel(Context context, List<ShuiwendizhiPoint> list, String fileName,String exportpath) throws IOException, WriteException {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&getAvailableStorage()>1000000) {
            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
            return;
        }
        String[] title = { "点名","编码", "经度", "纬度","水流类型","水面宽度","水深","流速","流量","水质","描述" };
        File file;
        File dir=new File(exportpath+"/地勘/Export");
        file = new File(dir, fileName + ".xls");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 创建Excel工作表
        WritableWorkbook wwb;
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        WritableSheet sheet = wwb.createSheet("订单", 0);
        Label label;
        for (int i=0;i<title.length;i++){
            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
            // 在Label对象的子对象中指明单元格的位置和内容
            label = new Label(i, 0, title[i], getHeader());
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
        }
        for (int i=0;i<list.size();i++){
            ShuiwendizhiPoint shuiwendizhiPoint = list.get(i);
            Label dianming=new Label(0,i+1,shuiwendizhiPoint.getName());
            Label code=new Label(1,i+1,shuiwendizhiPoint.getCode());
            Label jingdu=new Label(2,i+1,shuiwendizhiPoint.getLa());
            Label weidu=new Label(3,i+1,shuiwendizhiPoint.getLn());
            Label sllx=new Label(4,i+1,shuiwendizhiPoint.getSllx());
            Label smkd=new Label(5,i+1,shuiwendizhiPoint.getSmkd());
            Label ss=new Label(6,i+1,shuiwendizhiPoint.getSs());
            Label ls=new Label(7,i+1,shuiwendizhiPoint.getLs());
            Label ll=new Label(8,i+1,shuiwendizhiPoint.getLl());
            Label sz=new Label(9,i+1,shuiwendizhiPoint.getSz());
            Label miaoshu=new Label(10,i+1,shuiwendizhiPoint.getDes());

            sheet.addCell(dianming);
            sheet.addCell(code);
            sheet.addCell(jingdu);
            sheet.addCell(weidu);
            sheet.addCell(sllx);
            sheet.addCell(smkd);
            sheet.addCell(ss);
            sheet.addCell(ls);
            sheet.addCell(ll);
            sheet.addCell(sz);
            sheet.addCell(miaoshu);
        }
        // 写入数据
        wwb.write();
        // 关闭文件
        wwb.close();
    }
    public static WritableCellFormat getHeader(){
        WritableFont font = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);// 定义字体
        try {
            font.setColour(Colour.BLACK);// 黑色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            format.setBorder(Border.ALL, BorderLineStyle.THIN,
                    Colour.BLACK);// 黑色边框
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }
    /** 获取SD可用容量 */
    private static long getAvailableStorage() {

        StatFs statFs = new StatFs(root);
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        long availableSize = blockSize * availableBlocks;
        // Formatter.formatFileSize(context, availableSize);
        return availableSize;
    }
}
