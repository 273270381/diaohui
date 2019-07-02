package com.arcgis.mymap;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.utils.EditTextView;
import com.arcgis.mymap.utils.GetTable;
import com.arcgis.mymap.utils.ToastNotRepeat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.arcgis.mymap.ProjectAttributes.Stringtolong;

public class ActivitySevenParam extends AppCompatActivity implements View.OnClickListener{
    EditTextView editTextView1;
    EditTextView editTextView2;
    EditTextView editTextView3;
    EditTextView editTextView4;
    EditTextView editTextView5;
    EditTextView editTextView6;
    EditTextView editTextView7;
    EditTextView editTextView8;
    Button btn;
    ImageView back;
    private int position;
    private String project_name;
    public MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    private List<NewProject> projects=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_parem);
        try {
            getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        intView();
    }

    private void intView() {
        editTextView1 = (EditTextView) findViewById(R.id.et_1);
        editTextView2 = (EditTextView) findViewById(R.id.et_2);
        editTextView3 = (EditTextView) findViewById(R.id.et_3);
        editTextView4 = (EditTextView) findViewById(R.id.et_4);
        editTextView5 = (EditTextView) findViewById(R.id.et_5);
        editTextView6 = (EditTextView) findViewById(R.id.et_6);
        editTextView7 = (EditTextView) findViewById(R.id.et_7);
        editTextView8 = (EditTextView) findViewById(R.id.et_8);
        btn = (Button) findViewById(R.id.btn);
        back = (ImageView) findViewById(R.id.back);

        editTextView1.getTextView().setText("X平移(米)");
        editTextView2.getTextView().setText("Y平移(米)");
        editTextView3.getTextView().setText("Z平移(米)");
        editTextView4.getTextView().setText("X轴旋转(秒)");
        editTextView5.getTextView().setText("Y轴旋转(秒)");
        editTextView6.getTextView().setText("Z轴旋转(秒)");
        editTextView7.getTextView().setText("尺度(ppm)");
        editTextView8.getTextView().setText("中央子午");

        editTextView1.getEditView(0).setText(String.valueOf(projects.get(position).getC3xzpy()));
        editTextView2.getEditView(0).setText(String.valueOf(projects.get(position).getC3yzpy()));
        editTextView3.getEditView(0).setText(String.valueOf(projects.get(position).getC3zzpy()));
        editTextView4.getEditView(0).setText(String.valueOf(projects.get(position).getC3xzxz()));
        editTextView5.getEditView(0).setText(String.valueOf(projects.get(position).getC3yzxz()));
        editTextView6.getEditView(0).setText(String.valueOf(projects.get(position).getC3zzxz()));
        editTextView7.getEditView(0).setText(String.valueOf(projects.get(position).getC3bl()));
        editTextView8.getEditView(0).setText(String.valueOf(projects.get(position).getC2zyzwx()));

        btn.setOnClickListener(this);
        back.setOnClickListener(this);


    }
    // 查询数据
    public List<NewProject> getData() throws ParseException {
        dbHelper=new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        Intent i = getIntent();
        project_name = i.getStringExtra("form");
        position = Integer.parseInt(i.getStringExtra("position"));
        Cursor cursor=db.query(project_name,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历cursor对象，取出数据
                NewProject newProject=new NewProject();
                //String id=cursor.getString(cursor.getColumnIndex("id"));
//                String name=cursor.getString(cursor.getColumnIndex("pname"));
//                String url=cursor.getString(cursor.getColumnIndex("sname"));
//                String des=cursor.getString(cursor.getColumnIndex("description"));
//                String position=cursor.getString(cursor.getColumnIndex("position"));
//                String pname=cursor.getString(cursor.getColumnIndex("name"));
                String c1code = cursor.getString(cursor.getColumnIndex("c1code"));
                Double c3xzpy = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3xzpy")));
                Double c3yzpy = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3yzpy")));
                Double c3zzpy = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3zzpy")));
                Double c3xzxz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3xzxz")));
                Double c3yzxz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3yzxz")));
                Double c3zzxz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3zzxz")));
                Double c3bl = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c3bl")));
                Double c2zyzwx = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c2zyzwx")));
                Double c1plds = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c1plds")));
                Double c1cbz = Double.parseDouble(cursor.getString(cursor.getColumnIndex("c1cbz")));
//                newProject.setProjectname(name);
//                newProject.setStrname(url);
//                newProject.setDes(des);
//                newProject.setPersonname(pname);
//                newProject.setPosition(String.valueOf(position));
                newProject.setC1code(c1code);
                newProject.setC3xzpy(c3xzpy);
                newProject.setC3yzpy(c3yzpy);
                newProject.setC3zzpy(c3zzpy);
                newProject.setC3xzxz(c3xzxz);
                newProject.setC3yzxz(c3yzxz);
                newProject.setC3zzxz(c3zzxz);
                newProject.setC3bl(c3bl);
                newProject.setC2zyzwx(c2zyzwx);
                newProject.setC1cbz(c1cbz);
                newProject.setC1plds(c1plds);
                projects.add(newProject);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return projects;
    }
    private void saveData(){
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(editTextView1.getEditView(0).getText().toString())){
            values.put("c3xzpy","0.0");
        }else{
            values.put("c3xzpy",editTextView1.getEditView(0).getText().toString());
        }

        if (TextUtils.isEmpty(editTextView2.getEditView(0).getText().toString())){
            values.put("c3yzpy","0.0");
        }else{
            values.put("c3yzpy",editTextView2.getEditView(0).getText().toString());
        }

        if (TextUtils.isEmpty(editTextView3.getEditView(0).getText().toString())){
            values.put("c3zzpy","0.0");
        }else{
            values.put("c3zzpy",editTextView3.getEditView(0).getText().toString());
        }

        if (TextUtils.isEmpty(editTextView4.getEditView(0).getText().toString())){
            values.put("c3xzxz","0.0");
        }else{
            values.put("c3xzxz",editTextView4.getEditView(0).getText().toString());
        }

        if (TextUtils.isEmpty(editTextView5.getEditView(0).getText().toString())){
            values.put("c3yzxz","0.0");
        }else{
            values.put("c3yzxz",editTextView5.getEditView(0).getText().toString());
        }

        if (TextUtils.isEmpty(editTextView6.getEditView(0).getText().toString())){
            values.put("c3zzxz","0.0");
        }else{
            values.put("c3zzxz",editTextView6.getEditView(0).getText().toString());
        }

        if (TextUtils.isEmpty(editTextView7.getEditView(0).getText().toString())){
            values.put("c3bl","0.0");
        }else{
            values.put("c3bl",editTextView7.getEditView(0).getText().toString());
        }

        if (TextUtils.isEmpty(editTextView8.getEditView(0).getText().toString())){
            values.put("c2zyzwx","0.0");
        }else{
            values.put("c2zyzwx",editTextView8.getEditView(0).getText().toString());
        }
        if (project_name.equals("Newproject")){
            db.update(project_name, values, "position=?", new String[]{String.valueOf(position)});
        }else{
            db.update(project_name, values, "gposition=?", new String[]{String.valueOf(position)});
        }
        ToastNotRepeat.show(ActivitySevenParam.this,"保存成功");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                saveData();
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
