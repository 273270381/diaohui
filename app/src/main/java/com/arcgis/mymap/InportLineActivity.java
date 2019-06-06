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
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.arcgis.mymap.adapter.GeoRightLineAdapter;
import com.arcgis.mymap.adapter.LeftListLineAdapter;
import com.arcgis.mymap.adapter.RightLineAdapter;
import com.arcgis.mymap.contacts.MoreLines;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.utils.GetTable;
import com.arcgis.mymap.utils.SyncHorizontalScrollView;
import com.arcgis.mymap.utils.ToastNotRepeat;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
/**
 * Created by Administrator on 2018/2/9.
 */

public class InportLineActivity extends Activity {
    TextView left_title;
    private LinearLayout right_title_container;
    private ListView leftlistView,rightlistView;
    private SyncHorizontalScrollView titleHorScv,contentHorScv;
    private MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public String pposition;
    public List<NewProject> projects=new ArrayList<>();
    public List<MoreLines> linesList;
    public int resource,resource2;
    public ImageButton back,search;
    public EditText text,eText2,editText;
    private LeftListLineAdapter leftlineAdapter;
    private RightLineAdapter rightLineAdapter;
    public Button delate,close,edit,detailed,findall;
    public String des,classification;
    public int id,p;
    public boolean b = false;
    public InputMethodManager imm;
    private List<String> listLa=new ArrayList<>();
    private List<String> xla = new ArrayList<>();
    private List<String> xln = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataline_hangce);
        try {
            intview();
            listener();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //给控件添加监听
    private void listener() {
        ClickListener listener=new ClickListener();
        LongClicklistener longClicklistener=new LongClicklistener();
        back.setOnClickListener(listener);
        detailed.setOnClickListener(listener);
        edit.setOnClickListener(listener);
        close.setOnClickListener(listener);
        delate.setOnClickListener(listener);
        delate.setOnLongClickListener(longClicklistener);
        search.setOnClickListener(listener);
        findall.setOnClickListener(listener);
    }
    private void intview() throws ParseException {
        findbyid();
    }
    //初始化控件
    private void findbyid() throws ParseException {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        left_title= (TextView) findViewById(R.id.tv_table_title_left);
        left_title.setText("序号");
        right_title_container = (LinearLayout) findViewById(R.id.right_title_container);
        getLayoutInflater().inflate(R.layout.geotable_right_titleline, right_title_container);
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
        pposition=getTable.getTableposition(InportLineActivity.this,db,dbHelper,projects);
        back = (ImageButton) findViewById(R.id.back);
        search = (ImageButton) findViewById(R.id.search);
        text = (EditText) findViewById(R.id.editText);
        delate = (Button) findViewById(R.id.bt3);
        close = (Button) findViewById(R.id.bt4);
        edit = (Button) findViewById(R.id.bt2);
        eText2 = (EditText) findViewById(R.id.etext2);
        detailed= (Button) findViewById(R.id.bt1);
        findall= (Button) findViewById(R.id.bt5);
        initTableView();
    }
    private void initTableView() throws ParseException {
        //查询Newlines表中的所有数据
        linesList = new ArrayList<>();
        Cursor cursor = db.query("Lineexcel"+pposition, null, null, null, null, null, null);
        linesList=getData(linesList, cursor);
        resource=R.layout.table_left_item;
        resource2=R.layout.table_right_item_line;
        leftlineAdapter=new LeftListLineAdapter(linesList,resource,InportLineActivity.this);
        rightLineAdapter=new RightLineAdapter(linesList,resource2,InportLineActivity.this);
        leftlistView.setAdapter(leftlineAdapter);
        rightlistView.setAdapter(rightLineAdapter);
        leftlineAdapter.notifyDataSetInvalidated();
        leftlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        rightlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long d) {
                rightLineAdapter.setSelectItem(position);
                rightLineAdapter.notifyDataSetChanged();
                leftlineAdapter.setSelectItem(position);
                leftlineAdapter.notifyDataSetChanged();
                p=position;
                MoreLines line = linesList.get(position);
                id=line.getId();
                classification=line.getClassification();
                des=line.getDescription();
                xla=line.getListla();
                xln=line.getListln();
                b = true;
            }
        });
    }
    //查询数据
    public List<MoreLines> getData(List<MoreLines> list, Cursor cursor) throws ParseException {
        if (cursor.moveToFirst()) {
            do {
                //遍历cursor对象，取出数据
                MoreLines lines=new MoreLines();
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String classification=cursor.getString(cursor.getColumnIndex("classification"));
                String xla = cursor.getString(cursor.getColumnIndex("xla"));
                String xln = cursor.getString(cursor.getColumnIndex("xln"));
                String datetime=cursor.getString(cursor.getColumnIndex("time"));
                String code=cursor.getString(cursor.getColumnIndex("code"));
                //String formatType="yyyy-MM-dd HH:mm:ss";
                String description = cursor.getString(cursor.getColumnIndex("description"));
                //long time=Stringtolong(date,formatType);
                //String datetime=getTime(time);
                lines.setId(id);
                lines.setClassification(classification);
                lines.setListla(Arrays.asList(xla.split(",")));
                lines.setListln(Arrays.asList(xln.split(",")));
                lines.setDatatime(datetime);
                lines.setDescription(description);
                lines.setCode(code);
                list.add(lines);
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
    //实现长按监听功能
    private class LongClicklistener implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
           AlertDialog dialog =  new AlertDialog.Builder(InportLineActivity.this).setTitle("警告：")
                    .setMessage("是否删除全部数据？")
                    .setNegativeButton("取消",null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dbHelper.onUpgrade(db, db.getVersion(), db.getVersion() + 1);
                            //清空lines表
                            Cursor cursor1=db.query("Lineexcel"+pposition,null,null,null,null,null,null);
                            if (cursor1.moveToFirst()){
                                do {
                                    String xla=cursor1.getString(cursor1.getColumnIndex("xla"));
                                    listLa.add(xla);
                                }while(cursor1.moveToNext());
                            }
                            cursor1.close();
                            for (int i=0;i<listLa.size();i++){
                                db.delete("Lineexcel"+pposition, "xla=?", new String[]{listLa.get(i)});
                            }
                            Cursor cursor = db.query("Lineexcel"+pposition, null, null, null, null, null, null);
                            List<MoreLines> lineslist2=new ArrayList<>();
                            try {
                                lineslist2=getData(lineslist2,cursor);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            final List<MoreLines> finallinelist=lineslist2;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    leftlineAdapter=new LeftListLineAdapter(finallinelist,resource,InportLineActivity.this);
                                    rightLineAdapter=new RightLineAdapter(finallinelist,resource2,InportLineActivity.this);
                                    leftlistView.setAdapter(leftlineAdapter);
                                    rightlistView.setAdapter(rightLineAdapter);
                                    ToastNotRepeat.show(InportLineActivity.this,"删除成功");
                                }
                            },400);
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
    //实现单击监听功能
    private class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.bt5:
                    Intent i=new Intent(InportLineActivity.this,MainActivity.class);
                    i.putExtra("list1", (Serializable) linesList);
                    startActivity(i);
                    Intent li=new Intent("com.arcgisline3.broadcasttest");
                    sendBroadcast(li);
                    finish();
                    break;
                case R.id.bt1:
                    if (b){
                        LinearLayout linearLayout2 = (LinearLayout) getLayoutInflater().inflate(R.layout.geodetaildata_line, null);
                        AlertDialog dialog1 = new AlertDialog.Builder(InportLineActivity.this)
                                .setTitle("详细：")
                                .setView(linearLayout2)
                                .setNegativeButton("查看", null)
                                .setPositiveButton("查看", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i=new Intent(InportLineActivity.this,MainActivity.class);
                                        List<String> mgla = new ArrayList<>(xla);
                                        List<String> mgln = new ArrayList<>(xln);
                                        i.putStringArrayListExtra("xla", (ArrayList<String>) mgla);
                                        i.putStringArrayListExtra("xln", (ArrayList<String>) mgln);
                                        i.putExtra("clas",classification);
                                        startActivity(i);
                                        Intent li=new Intent("com.arcgisline1.broadcasttest");
                                        sendBroadcast(li);
                                        finish();
                                    }
                                })
                                .show();
                        TextView tv1= (TextView) dialog1.findViewById(R.id.xuhao);
                        TextView tv2= (TextView) dialog1.findViewById(R.id.leibie);
                        TextView tv3= (TextView) dialog1.findViewById(R.id.xjingdu);
                        TextView tv4= (TextView) dialog1.findViewById(R.id.xweidu);
                        TextView tv7= (TextView) dialog1.findViewById(R.id.miaoshu);
                        tv1.setText(String.valueOf(id));
                        tv2.setText(classification);
                        tv3.setText(StringUtils.join(xla,","));
                        tv4.setText(StringUtils.join(xln,","));
                        tv7.setText(des);
                        Button btnpositive=dialog1.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog1.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt2:
                    if (b){
                        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.alert_dialogline, null);
                        AlertDialog dialog = new AlertDialog.Builder(InportLineActivity.this)
                                .setTitle("数据编辑：")
                                .setView(linearLayout)
                                .setNegativeButton("取消", null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String finaltext=editText.getText().toString();
                                        ContentValues values = new ContentValues();
                                        values.put("description", finaltext);
                                        db.update("Lineexcel"+pposition, values, "id=?", new String[]{String.valueOf(id)});
                                        linesList.get(p).setDescription(finaltext);
                                        if (imm!=null){
                                            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                                        }
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rightLineAdapter.notifyDataSetChanged();
                                                ToastNotRepeat.show(InportLineActivity.this,"保存成功");
                                            }
                                        }, 400);
                                    }
                                }).show();
                        TextView textView= (TextView) dialog.findViewById(R.id.etext1);
                        textView.setText(classification);
                        editText= (EditText) dialog.findViewById(R.id.etext2);
                        editText.setText(des);
                        Button btnpositive=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        Button btnnegative=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        btnpositive.setTextColor(getResources().getColor(R.color.color29));
                        btnnegative.setTextColor(getResources().getColor(R.color.color29));
                    }
                    break;
                case R.id.bt3:
                    if (b){
                        AlertDialog dialog = new AlertDialog.Builder(InportLineActivity.this)
                                .setTitle("删除数据")
                                .setMessage("是否删除数据：")
                                .setNegativeButton("取消",null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        b = false;
                                        db.delete("Lineexcel"+pposition, "id=?", new String[]{String.valueOf(id)});
                                        linesList.remove(p);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                leftlineAdapter.notifyDataSetChanged();
                                                rightLineAdapter.notifyDataSetChanged();
                                            }
                                        },400);
                                        Intent i=new Intent("com.exampleline.broadcast");
                                        sendBroadcast(i);
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
                        linesList.clear();
                        Cursor cursor = db.query("Lineexcel"+pposition, null, null, null, null, null, null);
                        try {
                            getData(linesList, cursor);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftlineAdapter.notifyDataSetChanged();
                                rightLineAdapter.notifyDataSetChanged();
                            }
                        }, 400);
                    } else {
                        linesList.subList(0, linesList.size()).clear();
                        Cursor cursor2 = db.query("Lineexcel"+pposition, null, "classification like ?", new String[]{ "%" + tsname + "%" }, null, null, null);
                        try {
                            getData(linesList, cursor2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (linesList.size() == 0) {
                            ToastNotRepeat.show(InportLineActivity.this, "抱歉，没有找到你要的数据！");
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                leftlineAdapter.notifyDataSetChanged();
                                rightLineAdapter.notifyDataSetChanged();
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

}
