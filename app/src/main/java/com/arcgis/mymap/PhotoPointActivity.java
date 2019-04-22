package com.arcgis.mymap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.arcgis.mymap.adapter.LeftListAdapter;
import com.arcgis.mymap.adapter.RightlistAdapter2;
import com.arcgis.mymap.contacts.LitepalPoints;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.utils.GetTable;
import com.arcgis.mymap.utils.SyncHorizontalScrollView;
import com.arcgis.mymap.utils.ToastNotRepeat;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Administrator on 2018/4/3.
 */

public class PhotoPointActivity extends Activity{
    private TextView tv_table_title_left,str,tv1,tv2;
    private LinearLayout right_title_container;
    private ListView leftlistView,rightlistView;
    private SyncHorizontalScrollView titleHorScv,contentHorScv;
    private MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public String pposition;
    public List<NewProject> projects=new ArrayList<>();
    public List<LitepalPoints> pointsList;
    private LeftListAdapter leftAdapter;
    private RightlistAdapter2 rightAdapter;
    public ImageButton back,search;
    public GridView gridView;
    public int resource,resource2;
    public EditText text,eText3;
    public Button delate,findall,close,detailed,edit;
    public String name,des,classification,la,ln,high;
    public int id,p;
    public boolean b = false;
    public InputMethodManager imm;
    public String[] titles;
    public Integer[] pictrues;
    public Alert_dialogActivity alert_dialogActivity;
    public NewDataActivity.PictureAdapter adapter;
    private List<String> listLa=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photopoint);
        try {
            init();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void init() throws ParseException {
        findByid();
        listener();
        alert_dialogActivity=new Alert_dialogActivity();
        titles=alert_dialogActivity.getStrings();
        pictrues=alert_dialogActivity.getInteger();
    }
    //添加监听
    private void listener() {
        ClickListener listener=new ClickListener();
        LongClickLister longClickLister=new LongClickLister();
        back.setOnClickListener(listener);
        detailed.setOnClickListener(listener);
        edit.setOnClickListener(listener);
        close.setOnClickListener(listener);
        delate.setOnClickListener(listener);
        delate.setOnLongClickListener(longClickLister);
        search.setOnClickListener(listener);
        findall.setOnClickListener(listener);
    }
    //初始化控件
    public void findByid() throws ParseException {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        tv_table_title_left = (TextView) findViewById(R.id.tv_table_title_left);
        tv_table_title_left.setText("序号");
        right_title_container = (LinearLayout) findViewById(R.id.right_title_container);
        getLayoutInflater().inflate(R.layout.table_right_title2, right_title_container);
        leftlistView= (ListView) findViewById(R.id.left_container_listview);
        rightlistView= (ListView) findViewById(R.id.right_container_listview);
        titleHorScv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        contentHorScv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);
        // 设置两个水平控件的联动
        titleHorScv.setScrollView(contentHorScv);
        contentHorScv.setScrollView(titleHorScv);
        dbHelper = new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        //获取表位置
        GetTable getTable=new GetTable();
        pposition=getTable.getTableposition(PhotoPointActivity.this,db,dbHelper,projects);
        int sposition=Integer.parseInt(getTable.getPpposition(PhotoPointActivity.this,db,dbHelper));
        String projectname=projects.get(sposition).getProjectname();
        back = (ImageButton) findViewById(R.id.back);
        search = (ImageButton) findViewById(R.id.search);
        text = (EditText) findViewById(R.id.editText);
        delate = (Button) findViewById(R.id.bt3);
        findall= (Button) findViewById(R.id.bt5);
        close = (Button) findViewById(R.id.bt4);
        edit = (Button) findViewById(R.id.bt2);
        detailed= (Button) findViewById(R.id.bt1);
        str= (TextView) findViewById(R.id.stringstr);
        str.setText("/MyMap/航测/Pictrue/"+projectname);
        initTableView();
    }
    private void initTableView() throws ParseException {
        //查询Newpoints表中的所有数据
        pointsList = new ArrayList<>();
        Cursor cursor = db.query("Photopoints"+pposition, null, null, null, null, null, null);
        pointsList=getData(pointsList, cursor);
        resource=R.layout.table_left_item;
        resource2=R.layout.table_right_item2;
        leftAdapter=new LeftListAdapter(pointsList,resource,PhotoPointActivity.this);
        rightAdapter=new RightlistAdapter2(pointsList,resource2,PhotoPointActivity.this);
        leftlistView.setAdapter(leftAdapter);
        rightlistView.setAdapter(rightAdapter);
        leftAdapter.notifyDataSetInvalidated();
        leftlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
            }
        });
        rightlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                rightAdapter.setSelectItem(position);
                rightAdapter.notifyDataSetChanged();
                leftAdapter.setSelectItem(position);
                leftAdapter.notifyDataSetChanged();
                p=position;
                LitepalPoints point=pointsList.get(position);
                id=point.getId();
                name = point.getName();
                des = point.getDescription();
                classification=point.getClassification();
                la=point.getLa();
                ln=point.getLn();
                high=point.getHigh();
                b = true;
            }
        });
    }
    //查询数据
    public List<LitepalPoints> getData(List<LitepalPoints> list, Cursor cursor) throws ParseException {
        if (cursor.moveToFirst()) {
            do {
                //遍历cursor对象，取出数据
                LitepalPoints litepalPoints = new LitepalPoints();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String la = cursor.getString(cursor.getColumnIndex("la"));
                String ln = cursor.getString(cursor.getColumnIndex("ln"));
                String high = cursor.getString(cursor.getColumnIndex("high"));
                String classification=cursor.getString(cursor.getColumnIndex("classification"));
                String datetime=cursor.getString(cursor.getColumnIndex("time"));
                String code=cursor.getString(cursor.getColumnIndex("code"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                litepalPoints.setId(id);
                litepalPoints.setName(name);
                litepalPoints.setLa(la);
                litepalPoints.setLn(ln);
                litepalPoints.setHigh(high);
                litepalPoints.setClassification(classification);
                litepalPoints.setDatetime(datetime);
                litepalPoints.setDescription(description);
                litepalPoints.setCode(code);
                list.add(litepalPoints);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    /** 由时间戳转化为文本 */
    private String getTime(long time) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = sdf.format(time+28800000);
        return date;
    }
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long Stringtolong(String strTime,String formatType)throws ParseException{
        Date date =stringToDate(strTime, formatType);// String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }
    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }
    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }
    //长按监听功能实现
    private class LongClickLister implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            AlertDialog dialog = new AlertDialog.Builder(PhotoPointActivity.this).setTitle("警告：")
                    .setMessage("是否删除全部数据？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dbHelper.onUpgrade(db, db.getVersion(), db.getVersion() + 1);
                            //删除point表
                            Cursor cursor1=db.query("Photopoints"+pposition,null,null,null,null,null,null);
                            if (cursor1.moveToFirst()){
                                do {
                                    String la=cursor1.getString(cursor1.getColumnIndex("la"));
                                    listLa.add(la);
                                }while(cursor1.moveToNext());
                            }
                            cursor1.close();
                            for (int i=0;i<listLa.size();i++){
                                db.delete("Photopoints"+pposition, "la=?", new String[]{listLa.get(i)});
                            }
                            Cursor cursor = db.query("Photopoints"+pposition, null, null, null, null, null, null);
                            List<LitepalPoints> pointsList2 = new ArrayList<>();
                            try {
                                pointsList2=getData(pointsList2, cursor);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            final List<LitepalPoints> finalPointsList = pointsList2;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    leftAdapter=new LeftListAdapter(finalPointsList,resource,PhotoPointActivity.this);
                                    rightAdapter=new RightlistAdapter2(finalPointsList,resource2,PhotoPointActivity.this);
                                    leftlistView.setAdapter(leftAdapter);
                                    rightlistView.setAdapter(rightAdapter);
                                }
                            }, 400);
                            Intent a=new Intent();
                            a.setAction("com.delatedatas.broadcast");
                            sendBroadcast(a);
                        }
                    })
                    .show();
            Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            btnpositive.setTextColor(getResources().getColor(R.color.color29));
            btnnegative.setTextColor(getResources().getColor(R.color.color29));
            return true;
        }
    }
    //单击监听功能实现
    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.bt5:
                    Intent i=new Intent(PhotoPointActivity.this,MainActivity.class);
                    i.putExtra("list", (Serializable) pointsList);
                    startActivity(i);
                    Intent li=new Intent("com.arcgisline3.broadcasttest");
                    sendBroadcast(li);
                    finish();
                    break;
                case R.id.bt1:
                    if (b){
                        LinearLayout linearLayout2 = (LinearLayout) getLayoutInflater().inflate(R.layout.detaildata_data_dcyx, null);
                        AlertDialog dialog1 = new AlertDialog.Builder(PhotoPointActivity.this)
                                .setTitle("详细：")
                                .setView(linearLayout2)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("查看", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i=new Intent(PhotoPointActivity.this,MainActivity.class);
                                        i.putExtra("jingdu",la);
                                        i.putExtra("weidu",ln);
                                        i.putExtra("gaodu",high);
                                        i.putExtra("classification",classification);
                                        i.putExtra("des",des);
                                        startActivity(i);
                                        Intent li=new Intent("com.arcgisline2.broadcasttest");
                                        sendBroadcast(li);
                                        finish();
                                    }
                                })
                                .show();
                        TextView tv1 = (TextView) dialog1.findViewById(R.id.xuhao);
                        TextView tv2 = (TextView) dialog1.findViewById(R.id.dianming);
                        TextView tv3 = (TextView) dialog1.findViewById(R.id.jingdu);
                        TextView tv4 = (TextView) dialog1.findViewById(R.id.weidu);
                        TextView tv5 = (TextView) dialog1.findViewById(R.id.gaocheng);
                        TextView tv6 = (TextView) dialog1.findViewById(R.id.leibie);
                        TextView tv7 = (TextView) dialog1.findViewById(R.id.miaoshu);
                        tv1.setText(String.valueOf(id));
                        tv2.setText(name);
                        tv3.setText(la);
                        tv4.setText(ln);
                        tv5.setText(high);
                        tv6.setText(classification);
                        tv7.setText(des);
                        Button btnpositive=dialog1.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog1.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt2:
                    if (b){
                        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialogedit2, null);
                        AlertDialog dialog = new AlertDialog.Builder(PhotoPointActivity.this)
                                .setTitle("数据编辑：")
                                .setView(linearLayout)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String finalText3 = eText3.getText().toString();
                                        ContentValues values = new ContentValues();
                                        values.put("description", finalText3);
                                        db.update("Photopoints"+pposition, values, "id=?", new String[]{String.valueOf(id)});
                                        pointsList.get(p).setDescription(finalText3);
                                        if (imm!=null){
                                            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rightAdapter.notifyDataSetChanged();
                                                ToastNotRepeat.show(PhotoPointActivity.this,"保存成功");
                                            }
                                        }, 400);
                                        Intent a=new Intent();
                                        a.setAction("com.delatedatas.broadcast");
                                        sendBroadcast(a);
                                    }
                                })
                                .show();
                        tv1 = (TextView) dialog.findViewById(R.id.tv1);
                        tv1.setText(name);
                        tv2 = (TextView) dialog.findViewById(R.id.tv2);
                        tv2.setText(classification);
                        eText3= (EditText) dialog.findViewById(R.id.etext2);
                        eText3.setText(des);
                        Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt3:
                    if (b){
                        AlertDialog dialog = new AlertDialog.Builder(PhotoPointActivity.this)
                                .setTitle("删除数据")
                                .setMessage("是否删除数据：")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        b = false;
                                        db.delete("Photopoints"+pposition, "id=?", new String[]{String.valueOf(id)});
                                        pointsList.remove(p);

                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                leftAdapter.notifyDataSetChanged();
                                                rightAdapter.notifyDataSetChanged();
                                            }
                                        }, 400);
                                        Intent i=new Intent("com.example.broadcast");
                                        sendBroadcast(i);
                                        Intent a=new Intent();
                                        a.setAction("com.delatedatas.broadcast");
                                        sendBroadcast(a);
                                    }
                                })
                                .show();
                        Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt4:
                    finish();
                    break;
                case R.id.search:
                    String tsname = text.getText().toString();
                    if (TextUtils.isEmpty(text.getText())) {
                        pointsList.clear();
                        Cursor cursor = db.query("Photopoints"+pposition, null, null, null, null, null, null);
                        try {
                            getData(pointsList, cursor);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftAdapter.notifyDataSetChanged();
                                rightAdapter.notifyDataSetChanged();
                            }
                        }, 400);
                    } else {
                        pointsList.subList(0, pointsList.size()).clear();
                        Cursor cursor2 = db.query("Photopoints"+pposition, null, "classification like ?", new String[]{"%" + tsname + "%"}, null, null, null);
                        try {
                            getData(pointsList, cursor2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (pointsList.size() == 0) {
                            ToastNotRepeat.show(PhotoPointActivity.this, "抱歉，没有找到你要的数据！");
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftAdapter.notifyDataSetChanged();
                                rightAdapter.notifyDataSetChanged();
                            }
                        }, 400);
                    }
                    break;
            }
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    //遍历数组
    public  int printArrayto(String [] array,String value){
        for (int i=0;i<array.length;i++){
            if(array[i].equals(value)){
                return i;
            }
        }
        return 0;//当if条件不成立时，默认返回一个负数值-1
    }
    public class PictureAdapter extends BaseAdapter {
        private List<Pictrue> pictures=new ArrayList<Pictrue>();
        public PictureAdapter(String[] titles,Integer[] images){
            super();
            for (int i=0;i<images.length;i++){
                Pictrue pictrue=new Pictrue(titles[i],images[i]);
                pictures.add(pictrue);
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
                convertView= LayoutInflater.from(PhotoPointActivity.this).inflate(R.layout.bitmapitem,null);
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
