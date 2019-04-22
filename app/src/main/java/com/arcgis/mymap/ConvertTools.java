package com.arcgis.mymap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import com.arcgis.mymap.utils.ConverUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/18.
 */

public class ConvertTools extends AppCompatActivity {
    Spinner sp1,sp2,sp3;
    EditText et1,et2;
    TextView tv1,tv2;
    String s1,s2,s3,s4;
    Button bt;
    ImageButton back;
    private List<String> list1=new ArrayList<>();
    private List<String> list2=new ArrayList<>();
    private List<String> list3=new ArrayList<>();
    private List<String> list4=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converttools);
        intViews();
        addListener();
    }
    private void addListener() {
        Clicklistener clicklistener=new Clicklistener();
        bt.setOnClickListener(clicklistener);
        back.setOnClickListener(clicklistener);
    }
    //初始化控件
    private void intViews() {
        sp1= (Spinner) findViewById(R.id.sp1);
        sp2= (Spinner) findViewById(R.id.sp2);
        sp3= (Spinner) findViewById(R.id.sp3);
        et1= (EditText) findViewById(R.id.et1);
        et2= (EditText) findViewById(R.id.et2);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        bt= (Button) findViewById(R.id.bt);
        back= (ImageButton) findViewById(R.id.back);
        getData();
        final ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,list1);
        final ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,list2);
        final ArrayAdapter adapter3=new ArrayAdapter(this,android.R.layout.simple_spinner_item,list3);
        final ArrayAdapter adapter4=new ArrayAdapter(this,android.R.layout.simple_spinner_item,list4);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter1);
        //实现控件sp1的功能
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    sp2.setAdapter(adapter2);
                    sp3.setAdapter(adapter2);
                    //tv1.setText(adapter2.getItem(position).toString());
                }
                if (position==1){
                    sp2.setAdapter(adapter3);
                    sp3.setAdapter(adapter3);
                }
                if (position==2){
                    sp2.setAdapter(adapter4);
                    sp3.setAdapter(adapter4);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //实现控件sp2的功能
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter adapter= (ArrayAdapter) parent.getAdapter();
                tv1.setText(adapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //实现控件sp3的功能
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter adapter= (ArrayAdapter) parent.getAdapter();
                tv2.setText(adapter.getItem(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //为list添加数据
    private void getData() {
        list1.add("面积");
        list1.add("长度");
        list1.add("重量");
        list2.add("平方米");
        list2.add("平方公里");
        list2.add("公顷");
        list2.add("亩");
        list3.add("米");
        list3.add("尺");
        list3.add("英尺");
        list3.add("里");
        list3.add("英里");
        list3.add("公里");
        list4.add("克");
        list4.add("千克");
        list4.add("吨");
    }
    //单位换算功能监听
    private class Clicklistener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.bt:
                    s1=tv1.getText().toString();
                    s2=tv2.getText().toString();
                    s3=et1.getText().toString();
                    if (TextUtils.isEmpty(et1.getText())){
                        et2.setText("");
                    }else {
                        if (s1.equals("平方米")&&s2.equals("平方公里")){
                            s4=ConverUtils.PfmToPfgl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("平方米")&&s2.equals("公顷")){
                            s4=ConverUtils.PfmToGq(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("平方米")&&s2.equals("亩")){
                            s4=ConverUtils.PfmToM(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("平方公里")&&s2.equals("平方米")){
                            s4=ConverUtils.PfglToPfm(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("平方公里")&&s2.equals("公顷")){
                            s4=ConverUtils.PfglToGq(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("平方公里")&&s2.equals("亩")){
                            s4=ConverUtils.PfglToM(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("公顷")&&s2.equals("平方米")){
                            s4=ConverUtils.GqToPfm(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("公顷")&&s2.equals("平方公里")){
                            s4=ConverUtils.GqToPfgl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("公顷")&&s2.equals("亩")){
                            s4=ConverUtils.GqToM(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("亩")&&s2.equals("平方米")){
                            s4=ConverUtils.MToPfm(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("亩")&&s2.equals("平方公里")){
                            s4=ConverUtils.MToPfgl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("亩")&&s2.equals("公顷")){
                            s4=ConverUtils.MToGq(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("米")&&s2.equals("尺")){
                            s4=ConverUtils.MtoC(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("米")&&s2.equals("英尺")){
                            s4=ConverUtils.MtoYc(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("米")&&s2.equals("里")){
                            s4=ConverUtils.MtoL(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("米")&&s2.equals("英里")){
                            s4=ConverUtils.MtoYl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("米")&&s2.equals("公里")){
                            s4=ConverUtils.MtoGl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("尺")&&s2.equals("米")){
                            s4=ConverUtils.CtoM(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("尺")&&s2.equals("英尺")){
                            s4=ConverUtils.CtoYc(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("尺")&&s2.equals("里")){
                            s4=ConverUtils.CtoL(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("尺")&&s2.equals("英里")){
                            s4=ConverUtils.CtoYl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("尺")&&s2.equals("公里")){
                            s4=ConverUtils.CtoGl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英尺")&&s2.equals("米")){
                            s4=ConverUtils.YctoM(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英尺")&&s2.equals("尺")){
                            s4=ConverUtils.YctoC(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英尺")&&s2.equals("里")){
                            s4=ConverUtils.YctoL(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英尺")&&s2.equals("英里")){
                            s4=ConverUtils.YctoYl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英尺")&&s2.equals("公里")){
                            s4=ConverUtils.YctoGl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("里")&&s2.equals("米")){
                            s4=ConverUtils.LtoM(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("里")&&s2.equals("尺")){
                            s4=ConverUtils.LtoC(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("里")&&s2.equals("英尺")){
                            s4=ConverUtils.LtoYc(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("里")&&s2.equals("英里")){
                            s4=ConverUtils.LtoYl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("里")&&s2.equals("公里")){
                            s4=ConverUtils.LtoGl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英里")&&s2.equals("米")){
                            s4=ConverUtils.YltoM(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英里")&&s2.equals("尺")){
                            s4=ConverUtils.YltoC(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英里")&&s2.equals("英尺")){
                            s4=ConverUtils.YltoYc(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英里")&&s2.equals("里")){
                            s4=ConverUtils.YltoL(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("英里")&&s2.equals("公里")){
                            s4=ConverUtils.YltoGl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("公里")&&s2.equals("米")){
                            s4=ConverUtils.GltoM(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("公里")&&s2.equals("尺")){
                            s4=ConverUtils.GltoC(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("公里")&&s2.equals("英尺")){
                            s4=ConverUtils.GltoYc(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("公里")&&s2.equals("里")){
                            s4=ConverUtils.GltoL(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("公里")&&s2.equals("英里")){
                            s4=ConverUtils.GltoYl(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("克")&&s2.equals("千克")){
                            s4=ConverUtils.KtoQk(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("克")&&s2.equals("吨")){
                            s4=ConverUtils.KtoD(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("千克")&&s2.equals("克")){
                            s4=ConverUtils.QktoK(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("千克")&&s2.equals("吨")){
                            s4=ConverUtils.QktoD(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("吨")&&s2.equals("克")){
                            s4=ConverUtils.DtoK(Double.parseDouble(s3));
                            et2.setText(s4);
                        }
                        if (s1.equals("吨")&&s2.equals("千克")){
                            s4=ConverUtils.DtoQk(Double.parseDouble(s3));
                            et2.setText(s4);
                        }


                        if (s1.equals("平方米")&&s2.equals("平方米")){
                            et2.setText(s3);
                        }
                        if (s1.equals("平方公里")&&s2.equals("平方公里")){
                            et2.setText(s3);
                        }
                        if (s1.equals("公顷")&&s2.equals("公顷")){
                            et2.setText(s3);
                        }
                        if (s1.equals("亩")&&s2.equals("亩")){
                            et2.setText(s3);
                        }
                        if (s1.equals("米")&&s2.equals("米")){
                            et2.setText(s3);
                        }
                        if (s1.equals("尺")&&s2.equals("尺")){
                            et2.setText(s3);
                        }
                        if (s1.equals("英尺")&&s2.equals("英尺")){
                            et2.setText(s3);
                        }
                        if (s1.equals("里")&&s2.equals("里")){
                            et2.setText(s3);
                        }
                        if (s1.equals("英里")&&s2.equals("英里")){
                            et2.setText(s3);
                        }
                        if (s1.equals("公里")&&s2.equals("公里")){
                            et2.setText(s3);
                        }
                        if (s1.equals("克")&&s2.equals("克")){
                            et2.setText(s3);
                        }
                        if (s1.equals("千克")&&s2.equals("千克")){
                            et2.setText(s3);
                        }
                        if (s1.equals("吨")&&s2.equals("吨")){
                            et2.setText(s3);
                        }
                    }
                    break;
                case R.id.back:
                    finish();
                    break;
            }
        }
    }
}
