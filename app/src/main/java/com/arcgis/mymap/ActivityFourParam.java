package com.arcgis.mymap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.arcgis.mymap.utils.EditTextView;

public class ActivityFourParam extends AppCompatActivity implements View.OnClickListener{
    EditTextView editTextView1;
    EditTextView editTextView2;
    EditTextView editTextView3;
    EditTextView editTextView4;
    EditTextView editTextView5;
    Button btn;
    ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_parem);
        intView();
    }

    private void intView() {
        editTextView1 = (EditTextView) findViewById(R.id.et_1);
        editTextView2 = (EditTextView) findViewById(R.id.et_2);
        editTextView3 = (EditTextView) findViewById(R.id.et_3);
        editTextView4 = (EditTextView) findViewById(R.id.et_4);
        editTextView5 = (EditTextView) findViewById(R.id.et_5);
        btn = (Button) findViewById(R.id.btn);
        back = (ImageView) findViewById(R.id.back);

        editTextView1.getEditView(true);
        editTextView2.getEditView(true);
        editTextView3.getEditView(true);
        editTextView4.getEditView(true);
        editTextView5.getEditView(true);


        editTextView1.getTextView().setText("东平移(米)");
        editTextView2.getTextView().setText("北平移(米)");
        editTextView3.getTextView().setText("旋转角(弧度)");
        editTextView4.getTextView().setText("尺度(ppm)");
        editTextView5.getTextView().setText("中央子午");
        btn.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
