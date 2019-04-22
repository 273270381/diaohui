package com.arcgis.mymap.contacts;

import android.text.SpannableStringBuilder;

import com.arcgis.mymap.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */

public class ContactDB {
    private static String[] alltitles=new String[]{
            "旱地","稻田","花圃","天然草地","有林地","菜地","果园","水生经济作物地","大面积灌木林","大面积竹林","旱地","稻田","花圃","天然草地",
            "有林地","菜地","果园","水生经济作物地","大面积灌木林","大面积竹林","不埋石图根点","埋石图根点","导线点","小三角点","三角点","土堆上的三角点",
            "土堆上的小三角点","天文点","水准点","GPS控制点","土堆上的导线点","水文站","停泊场","航行灯塔","航行灯桩","航行灯船","左岸航行浮标","右岸航行浮标",
            "系船浮筒","急流","过江管线标","信号杆","露出的沉船","淹没的沉船","泉","水井","不依比例石堆","学校","不依比例肥气池","卫生所","地上窑洞不依比例",
            "电视发射塔","地下窑洞","窑","蒙古包","上水检修井","下水.雨水检修井","污水蓖子园形","下水暗井","煤气.天然气检修井","热力检修井","电信人孔","电信手孔",
            "电力检修井","工业.石油检修井","不依比例液体.气体储存设备","不明用途的检修井","消火栓","阀门","水龙头","污水蓖子长形","不依比例变电室","无线电杆.塔",
            "电杆","旧碉堡","雷达站","里程碑","坡度表","路标","汽车站","臂板信号机","阔叶独立树","针叶独立树","果树独立树","椰子.槟榔独立树","烟囱","露天设备",
            "地磅","起重机","探井","钻孔","石油.天然气井","盐井","废弃的小矿井","废弃的平洞洞口","废弃的竖井井口(圆)","开采的小矿井","开采的平洞洞口","开采的竖井井口(圆)",
            "加油站","气象站","路灯","杆式照射灯","喷水池","垃圾台","旗杆","亭","岗亭.岗楼","钟楼.城楼.鼓楼","水塔","水塔烟囱","环保检测站","不依比例粮仓","风车",
            "水磨房.水车","避雷针","水轮泵.抽水机站","地下建筑物通风口","纪念碑","碑.柱.墩","塑像","庙宇","土地庙","教堂","清真寺","敖包.经堆","宝塔.经塔","假石山",
            "塔形建筑物","独立坟","散坟"
    };
    private static String[] codes=new String[]{
      "B0","B1","B2","B3","B4","B5","B6","B7","B8","B9","H0","H1","H2","H3","H4","H5","H6","H7","H8","H9","C0","C1","C2","C3","C4","C5","C6","C7","C8",
       "C9","C10","A00","A01","A02","A03","A04","A05","A06","A07","A08","A09","A10","A11","A12","A13","A14","A15","A16","A17","A18","A19","A20","A21",
            "A22","A23","A24","A25","A26","A27","A28","A29","A30","A31","A32","A33","A34","A35","A36","A37","A38","A39","A40","A41","A42","A43","A44",
            "A45","A56","A47","A48","A49","A50","A51","A52","A53","A54","A55","A56","A57","A58","A59","A60","A61","A62", "A63","A64","A65","A66","A67",
            "A68","A69","A70","A71","A72","A73","A74","A75","A76","A77","A78","A79","A80","A81","A82","A83","A84","A85","A86","A87","A88","A89","A90",
            "A91","A92","A93","A94","A95","A96","A97","A98","A99"
    };
    private static String[] codes2=new String[]{
            "A08","A10","A13","A14","A15","A16","A17","A18","A19","A20","A21",
            "A22","A23","A24","A25","A26","A27","A28","A29","A30","A31","A32","A33","A34","A35","A36","A37","A38","A39","A40","A41","A42","A43",
            "A45","A46","A47","A48","A49","A50","A51","A52","A53","A54","A55","A56","A57","A58","A59","A60","A61","A62", "A63","A64","A65","A66","A67",
            "A68","A69","A70","A71","A72","A73","A74","A75","A76","A77","A78","A79","A80","A81","A82","A83","A84","A85","A86","A87","A88","A89","A90",
            "A91","A92","A93","A95","A96","A97","A98","A99","A00","A01","A02","A03","A04","A07","A09","A11","A12"
    };
    private static String[] titles=new String[]{
            "急流","信号杆","泉","水井","不依比例石堆","学校","不依比例肥气池","卫生所","地上窑洞不依比例",
            "电视发射塔","地下窑洞","窑","蒙古包","上水检修井","下水.雨水检修井","污水蓖子园形","下水暗井","煤气.天然气检修井","热力检修井","电信人孔","电信手孔",
            "电力检修井","工业.石油检修井","不依比例液体.气体储存设备","不明用途的检修井","消火栓","阀门","水龙头","污水蓖子长形","不依比例变电室","无线电杆.塔",
            "电杆","旧碉堡","里程碑","坡度表","路标","汽车站","臂板信号机","阔叶独立树","针叶独立树","果树独立树","椰子.槟榔独立树","烟囱","露天设备",
            "地磅","起重机","探井","钻孔","石油.天然气井","盐井","废弃的小矿井","废弃的平洞洞口","废弃的竖井井口(圆)","开采的小矿井","开采的平洞洞口","开采的竖井井口(圆)",
            "加油站","气象站","路灯","杆式照射灯","喷水池","垃圾台","旗杆","亭","岗亭.岗楼","钟楼.城楼.鼓楼","水塔","水塔烟囱","环保检测站","不依比例粮仓","风车",
            "水磨房.水车","避雷针","水轮泵.抽水机站","地下建筑物通风口","纪念碑","碑.柱.墩","塑像","庙宇","土地庙","教堂","清真寺","宝塔.经塔","假石山",
            "塔形建筑物","独立坟","散坟","水文站","停泊场","航行灯塔","航行灯桩","航行灯船", "系船浮筒","过江管线标","露出的沉船","淹没的沉船"
    };
   /* private static String[] titles=new String[]{
            "不明井盖","厕所","电杆","电力井盖","电信井盖","加油站","垃圾台","路灯","庙宇","旗杆", "燃气井盖","散坟","上水井盖","水井","水塔",
            "塑像","土地庙","网络井盖","卫生所","污水井盖","信号杆","学校","烟囱","雨水井盖"
    };*/
    private static Integer[] imager={R.mipmap.a08,R.mipmap.a10,R.mipmap.a13,
      R.mipmap.a14,R.mipmap.a15,R.mipmap.a16,R.mipmap.a17,R.mipmap.a18,R.mipmap.a19,R.mipmap.a20,R.mipmap.a21,R.mipmap.a22,R.mipmap.a23,R.mipmap.a24,
      R.mipmap.a25,R.mipmap.a26,R.mipmap.a27,R.mipmap.a28,R.mipmap.a29,R.mipmap.a30,R.mipmap.a31,R.mipmap.a32,R.mipmap.a33,R.mipmap.a34,
      R.mipmap.a35,R.mipmap.a36,R.mipmap.a37,R.mipmap.a38,R.mipmap.a39,R.mipmap.a40,R.mipmap.a41,R.mipmap.a42,R.mipmap.a43,
      R.mipmap.a45,R.mipmap.a46,R.mipmap.a47,R.mipmap.a48,R.mipmap.a49,R.mipmap.a50,R.mipmap.a51,R.mipmap.a52,R.mipmap.a53,R.mipmap.a54,
      R.mipmap.a55,R.mipmap.a56,R.mipmap.a57,R.mipmap.a58,R.mipmap.a59,R.mipmap.a60,R.mipmap.a61,R.mipmap.a62,R.mipmap.a63,R.mipmap.a64,
      R.mipmap.a65,R.mipmap.a66,R.mipmap.a67,R.mipmap.a68,R.mipmap.a69,R.mipmap.a70,R.mipmap.a71,R.mipmap.a72,R.mipmap.a73,R.mipmap.a74,
      R.mipmap.a75,R.mipmap.a76,R.mipmap.a77,R.mipmap.a78,R.mipmap.a79,R.mipmap.a80,R.mipmap.a81,R.mipmap.a82,R.mipmap.a83,R.mipmap.a84,
      R.mipmap.a85,R.mipmap.a86,R.mipmap.a87,R.mipmap.a88,R.mipmap.a89,R.mipmap.a90,R.mipmap.a91,R.mipmap.a92,R.mipmap.a93,
      R.mipmap.a95,R.mipmap.a96,R.mipmap.a97,R.mipmap.a98,R.mipmap.a99,R.mipmap.a00,
           R.mipmap.a01,R.mipmap.a02,R.mipmap.a03,R.mipmap.a04,R.mipmap.a07,R.mipmap.a09,R.mipmap.a11,R.mipmap.a12
    };
   /* private static Integer[] imager={
            R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a4,R.mipmap.a5,R.mipmap.a6,R.mipmap.a7,R.mipmap.a8,
            R.mipmap.a9,R.mipmap.a10,R.mipmap.a11,R.mipmap.a12,R.mipmap.a13,R.mipmap.a14,R.mipmap.a15,R.mipmap.a16,
            R.mipmap.a17,R.mipmap.a18,R.mipmap.a19,R.mipmap.a20,R.mipmap.a21,R.mipmap.a22,R.mipmap.a23,R.mipmap.a24,
    };*/
    public static String[] getStrings(){
        return titles;
    }
    public static Integer[] getInters(){
        return imager;
    }
    public static String[] getCodes(){
        return codes2;
    }
}
