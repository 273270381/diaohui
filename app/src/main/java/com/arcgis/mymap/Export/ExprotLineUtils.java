package com.arcgis.mymap.Export;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import com.arcgis.mymap.contacts.Lines;
import com.arcgis.mymap.contacts.MoreLines;

import org.apache.commons.lang3.StringUtils;

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


/**
 * Created by Administrator on 2018/3/1.
 */

public class ExprotLineUtils {
    //内存地址
    public static String root = Environment.getExternalStorageDirectory()
            .getPath();
    public static void writelineExcel(Context context, List<MoreLines> list, String fileName,String exportpath) throws IOException, WriteException {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&getAvailableStorage()>1000000) {
            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
            return;
        }
        String[] title = {  "代码","编码", "经度", "纬度","日期","描述"};
        File file;
        File dir=new File(exportpath+"/航测/Export");
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
            MoreLines lines = list.get(i);
            Label leibie=new Label(0,i+1,lines.getClassification());
            Label code=new Label(1,i+1,lines.getCode());
            Label jingdu=new Label(2,i+1, StringUtils.join(lines.getListla(),","));
            Label weidu=new Label(3,i+1,StringUtils.join(lines.getListln(),","));
            Label riqi=new Label(4,i+1,lines.getDatatime());
            Label miaoshu=new Label(5,i+1,lines.getDescription());

            sheet.addCell(leibie);
            sheet.addCell(code);
            sheet.addCell(jingdu);
            sheet.addCell(weidu);
            sheet.addCell(riqi);
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
