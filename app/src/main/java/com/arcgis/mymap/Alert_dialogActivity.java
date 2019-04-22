package com.arcgis.mymap;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arcgis.mymap.contacts.ContactDB;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.utils.GetTable;
import com.arcgis.mymap.utils.LogUtils;
import com.arcgis.mymap.utils.PinyinAPI;
import com.arcgis.mymap.utils.ToastNotRepeat;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/1/31.
 */
public class Alert_dialogActivity extends Activity {
    public long i=1;
    public Button bt1,bt2;
    public EditText et,et2,et3;
    public ImageButton search;
    public ImageView imag,imag2;
    public String ut,pt,mla,atotitle,pposition;
    public Boolean hasfocus=true;
    public int p=-1;
    public int ap;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    public String totitle=null;
    public List<NewProject> projects=new ArrayList<>();
    public Cursor utc,ptc;
    public int number=0;
    private GridView gridView;
    public String[] titles= ContactDB.getStrings();
    public Integer[] imager=ContactDB.getInters();
    public String[] codes=ContactDB.getCodes();
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialogview);
        intViews();
        Clicklistener clicklistener=new Clicklistener();
        bt1.setOnClickListener(clicklistener);
        bt2.setOnClickListener(clicklistener);
        search.setOnClickListener(clicklistener);
        final PictureAdapter pictureAdapter=new PictureAdapter(titles,imager);
        gridView.setAdapter(pictureAdapter);
        setdrawable();
        if (number!=0){
            setEt();
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imag.setImageResource(imager[position]);
                p=position;
                totitle=titles[p];
            }
        });
    }
    public void setdrawable(){
        ptc=db.query("Newpoints"+pposition,new String[]{"classification"},null,null,null,null,null);
        if (ptc.moveToLast()){
            pt=ptc.getString(ptc.getColumnIndex("classification"));
        }
        ptc.close();
        if (pt!=null){
            int index=printArray(titles,pt);
            imag.setImageResource(imager[index]);
        }
    }
    //遍历数组
    public static int printArray(String [] array,String value){
        for (int i=0;i<array.length;i++){
            if(array[i].equals(value)){
                return i;
            }
        }
        return 0;//当if条件不成立时，默认返回一个负数值-1
    }
    public Integer[] getInteger(){
        return imager;
    }
    public String[] getStrings(){
        return titles;
    }
    public void setEt(){
        //utc=db.rawQuery("select * from Newpoints0 where name like'[!R]'",null);
        utc=db.query("Newpoints"+pposition,null,"name != ? and name != ? and name != ? and name != ? and name != ? and name != ? and name != ?",
                new String[]{"H","Z","J","P","T","R","FY"},null,null,null);
        if (utc.moveToLast()){
            ut=utc.getString(utc.getColumnIndex("name"));
        }
        utc.close();
        if (ut==null){
            ut="a0";
        }
        if (isContainNumber(ut)){
            int x=getInt(ut);
            ut=ut.replace(String.valueOf(x),String.valueOf(x+1));
            et.setText(ut);
        }else {
            ut=ut+"1";
            et.setText(ut);
        }
    }
    /**
     * 截取字符串
     * @param str
     * @return
     */
    public int getInt(String str){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        int num=Integer.parseInt(m.replaceAll("").trim());
        return num;
    }

    /**
     * 判断字符串中是否包含数字
     */
    public static boolean isContainNumber(String company) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(company);
        if (m.find()) {
            return true;
        }
        return false;
    }
   //初始化控件
    private void intViews() {
        search= (ImageButton) findViewById(R.id.search);
        gridView= (GridView) findViewById(R.id.gridView);
        et = (EditText) findViewById(R.id.etext1);
        et2 = (EditText) findViewById(R.id.etext2);
        et3 = (EditText) findViewById(R.id.etext3);
        bt1 = (Button) findViewById(R.id.cancel);
        bt2 = (Button) findViewById(R.id.comfron);
        imag= (ImageView) findViewById(R.id.ig);
        imag2= (ImageView) findViewById(R.id.ib);
        dbHelper=new MyDatabaseHelper(this,"pointsStore.db",null,5);
        db=dbHelper.getWritableDatabase();
        //获取表位置
        GetTable getTable=new GetTable();
        pposition=getTable.getTableposition(Alert_dialogActivity.this,db,dbHelper,projects);
        Cursor c=db.rawQuery("select*from Newpoints"+pposition,null);
        number=c.getCount();
        intent=getIntent();
        et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean Focus) {
                if (Focus){
                    gridView.setVisibility(View.GONE);
                    imag.setVisibility(View.GONE);
                    imag2.setVisibility(View.VISIBLE);
                    et3.setText("");
                    hasfocus=true;
                    et.setFocusableInTouchMode(true);
                }
            }
        });
        et3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean Focus) {
                if (Focus){
                    et2.setText("");
                    hasfocus=false;
                    gridView.setVisibility(View.VISIBLE);
                    imag.setVisibility(View.VISIBLE);
                    imag.setImageResource(R.mipmap.white);
                    imag2.setVisibility(View.GONE);
                }
            }
        });
    }
    //给控件添加监听
    private class Clicklistener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.search:
                    String text=et3.getText().toString();
                    List<String> Slist=new ArrayList<>();
                    List<Integer> Ilist=new ArrayList<>();
                    if (text!=""){
                        for (int i=0;i<titles.length;i++){
                            String ss= PinyinAPI.getPinYinHeadChar(titles[i]);
                            if (ss.contains(text)){
                                Slist.add(titles[i]);
                                Ilist.add(imager[i]);
                            }
                        }
                        final String[] titlesh = Slist.toArray(new String[Slist.size()]);
                        final Integer[] imagerh = Ilist.toArray(new Integer[Ilist.size()]);
                        PictureAdapter pictureAdapter=new PictureAdapter(titlesh,imagerh);
                        gridView.setAdapter(pictureAdapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                imag.setImageResource(imagerh[position]);
                                p=position;
                                totitle=titlesh[p];
                            }
                        });
                    }else {
                        PictureAdapter pictureAdapter=new PictureAdapter(titles,imager);
                        gridView.setAdapter(pictureAdapter);
                    }
                    break;
                case R.id.cancel:
                    finish();
                    break;
                case R.id.comfron:
                    if (number!=0){
                        Cursor cla=db.query("Newpoints"+pposition,new String[]{"la"},null,null,null,null,null);
                        if (cla.moveToLast()){
                            mla=cla.getString(cla.getColumnIndex("la"));
                        }
                        cla.close();
                    }else {
                        mla="";
                    }
                    if (hasfocus){
                        String la=intent.getStringExtra("jingdu");
                        String ln=intent.getStringExtra("weidu");
                        String high=intent.getStringExtra("gaodu");
                        String time=intent.getStringExtra("shijian");
                        String text2=et2.getText().toString();
                        Bundle bundle =new Bundle();
                        bundle.putString("second",text2);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK,intent);
                        if (mla.equals(la)){
                            ToastNotRepeat.show(Alert_dialogActivity.this,"请确认坐标点是否已添加！");
                        }else if (TextUtils.isEmpty(text2)){
                            ToastNotRepeat.show(Alert_dialogActivity.this,"请确认通用点类别！");
                        }else {
                            ContentValues values=new ContentValues();
                            values.put("name",et.getText().toString());
                            values.put("la",la);
                            values.put("ln",ln);
                            values.put("high",high);
                            values.put("time",time);
                            values.put("code","null");
                            values.put("classification",text2);
                            db.insert("Newpoints"+pposition,null,values);
                            values.clear();
                            ToastNotRepeat.show(Alert_dialogActivity.this,"添加成功");
                            finish();
                        }
                    }else {
                        if (intent!=null){
                            String la=intent.getStringExtra("jingdu");
                            String ln=intent.getStringExtra("weidu");
                            String high=intent.getStringExtra("gaodu");
                            String time=intent.getStringExtra("shijian");
                            atotitle=intent.getStringExtra("xiabiao");
                            Bundle bundle =new Bundle();
                            if (totitle!=null){
                                bundle.putString("second",totitle);
                                intent.putExtras(bundle);
                                setResult(RESULT_OK,intent);
                                if (mla.equals(la)){
                                    ToastNotRepeat.show(Alert_dialogActivity.this,"请确认坐标点是否已添加");
                                }else {
                                    for (int k=0;k<titles.length;k++){
                                        if (titles[k].equals(totitle)){
                                            ap=k;
                                        }
                                        }
                                    ContentValues values=new ContentValues();
                                    values.put("name",et.getText().toString());
                                    values.put("la",la);
                                    values.put("ln",ln);
                                    values.put("high",high);
                                    values.put("time",time);
                                    values.put("classification",totitle);
                                    values.put("code",codes[ap]);
                                    db.insert("Newpoints"+pposition,null,values);
                                    values.clear();
                                    Intent i=new Intent("com.arcgis.broadcasttest");
                                    sendBroadcast(i);
                                    ToastNotRepeat.show(Alert_dialogActivity.this,"添加成功");
                                    finish();
                                }
                            }else {
                                ToastNotRepeat.show(Alert_dialogActivity.this,"请选择标记点！");
                            }

                        }
                    }
                    break;
            }
        }
    }
    //gridView配置Adapter
    public class PictureAdapter extends BaseAdapter {
        private List<Pictrue> pictures=new ArrayList<Pictrue>();
        public PictureAdapter(String[] titles,Integer[] images){
            super();
            for (int i=0;i<images.length;i++){
                Pictrue picture=new Pictrue(titles[i],images[i]);
                pictures.add(picture);
            }
        }
        @Override
        public int getCount() {
            return pictures.size();
        }

        @Override
        public Object getItem(int position) {
            return pictures.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView==null){
                viewHolder=new ViewHolder();
                // 获得容器
                convertView= LayoutInflater.from(Alert_dialogActivity.this).inflate(R.layout.bitmapitem,null);
                // 初始化组件
                viewHolder.image= (ImageView) convertView.findViewById(R.id.gtimage);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv);
                // 给converHolder附加一个对象
                convertView.setTag(viewHolder);
            }else {
                // 取得converHolder附加的对象
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // 给组件设置资源
            Pictrue pictrue=pictures.get(position);
            viewHolder.image.setImageResource(pictrue.getImageId());
            viewHolder.title.setText(pictrue.getTitled());
            return convertView;
        }
    }
    class ViewHolder{
        public TextView title;
        public ImageView image;
    }
    class Pictrue{
        private String titled;
        private int imageId;
        public Pictrue(String title,Integer imageId){
            this.imageId=imageId;
            this.titled = title;
        }
        public int getImageId() {
            return imageId;
        }
        public String getTitled() {
            return titled;
        }
    }
}
