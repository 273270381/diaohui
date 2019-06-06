package com.arcgis.mymap.utils;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import com.arcgis.mymap.R;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.contacts.NewProject;
import com.arcgis.mymap.contacts.PointGPS;
import com.arcgis.mymap.contacts.PointXYZ;
import com.arcgis.mymap.contacts.SevenPrams;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CoorTransActivity extends AppCompatActivity {
    //与Activity通信的消息类型
    SevenPrams sevenparam = new SevenPrams(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0);
    Spinner spTransType;
    Button btnTransition;
    ImageView changeBack;
    EditTextView etsrcX;
    EditTextView etsrcY;
    EditTextView etsrcZ;

    EditTextView tvdstX;
    EditTextView tvdstY;
    EditTextView tvdstZ;
    public String pposition;
    public List<NewProject> projects=new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_coordinate);
        InitView();
    }

    public void InitView() {
        btnTransition = (Button) findViewById(R.id.btntransition);
        etsrcX = (EditTextView) findViewById(R.id.et_src_x);
        etsrcY = (EditTextView) findViewById(R.id.et_src_y);
        etsrcZ = (EditTextView) findViewById(R.id.et_src_z);

        tvdstX = (EditTextView) findViewById(R.id.tv_dst_x);
        tvdstY = (EditTextView) findViewById(R.id.tv_dst_y);
        tvdstZ = (EditTextView) findViewById(R.id.tv_dst_z);
        changeBack = (ImageView) findViewById(R.id.changeBack);
        dbHelper = new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        GetTable getTable=new GetTable();
        pposition=getTable.getTableposition(CoorTransActivity.this,db,dbHelper,projects);
        changeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spTransType = (Spinner) findViewById(R.id.sp_trans_type);
        spTransType.setOnItemSelectedListener(new SpinnerSelectedListenerTool());
        etsrcX.getEditView(true);
        etsrcY.getEditView(true);
        etsrcZ.getEditView(true);
        etsrcX.getTextView().setText("纬度B");
        etsrcY.getTextView().setText("经度L");
        etsrcZ.getTextView().setText("高程H");

        tvdstX.getEditView(false).setFocusable(false);
        tvdstY.getEditView(false).setFocusable(false);
        tvdstZ.getEditView(false).setFocusable(false);
        tvdstX.getTextView().setText("坐标x");
        tvdstY.getTextView().setText("坐标y");
        tvdstZ.getTextView().setText("坐标z");

        etsrcX.getEditView(true).setHint("度.分分秒秒");
        etsrcY.getEditView(true).setHint("度.分分秒秒");


    }

    public void OnClickCalc(View view) {
        if (!etsrcX.getEditView(true).getText().toString().equals("") && !etsrcY.getEditView(true).getText().toString().equals("") && !etsrcZ.getEditView(true).getText().toString().equals("")) {
            if (Utils.isNumber(etsrcX.getEditView(true).getText().toString()) && Utils.isNumber(etsrcY.getEditView(true).getText().toString()) && Utils.isNumber(etsrcZ.getEditView(true).getText().toString())) {
                NewProject vo = projects.get(Integer.parseInt(pposition));//当前工程
                switch (spTransType.getSelectedItemPosition()) {
                    case 0:
                        PointGPS ptInGPS = new PointGPS(Double.valueOf(etsrcX.getEditView(true).getText().toString()), Double.valueOf(etsrcY.getEditView(true).getText().toString()), Double.valueOf(etsrcZ.getEditView(true).getText().toString()));
                        //转换
                        if (vo.getC1code().equals(CoordTransUtil.WGS84)) {
                            //WGS的空间直角坐标系
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy
                                    (), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointXYZ ptOut = CoordTransUtil.DatumTransWGSBLH2XYZ(ptInGPS, vo.getC2zyzwx(), st, vo.getC2djcs());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getX()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getY()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getZ()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        } else if (vo.getC1code().equals(CoordTransUtil.XIAN)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointXYZ ptOut = CoordTransUtil.DatumTransWGS2XiAn(ptInGPS, vo.getC2zyzwx(), st, vo.getC2djcs());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getX()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getY()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getZ()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        } else if (vo.getC1code().equals(CoordTransUtil.BEIJING54)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointXYZ ptOut = CoordTransUtil.DatumTransWGS2BJ(ptInGPS, vo.getC2zyzwx(), st, vo.getC2djcs());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getX()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getY()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getZ()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        } else if (vo.getC1code().equals(CoordTransUtil.CGCS2000)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointXYZ ptOut = CoordTransUtil.DatumTransWGS2CGCS(ptInGPS, vo.getC2zyzwx(), st, vo.getC2djcs());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getX()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getY()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getZ()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        } else {
                            //自定义坐标系
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointXYZ ptOut = CoordTransUtil.DatumTransWGS2USER(ptInGPS, vo.getC2zyzwx(), st, vo.getC2djcs(), vo.getC1cbz(), vo.getC1plds());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getX()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getY()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getZ()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        }
                        break;
                    case 1:
                        PointXYZ ptInXYZ = new PointXYZ(Double.valueOf(etsrcX.getEditView(true).getText().toString()), Double.valueOf(etsrcY.getEditView(true).getText().toString()), Double.valueOf(etsrcZ.getEditView(true).getText().toString()));
                        //转换
                        if (vo.getC1code().equals(CoordTransUtil.WGS84)) {
                            //WGS的空间直角坐标系
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransWGSXYZ2BLH(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLat()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLon()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgHeight()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        } else if (vo.getC1code().equals(CoordTransUtil.XIAN)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransXiAn2WGS(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLat()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLon()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgHeight()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        } else if (vo.getC1code().equals(CoordTransUtil.BEIJING54)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransBJ2WGS(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLat()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLon()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgHeight()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        } else if (vo.getC1code().equals(CoordTransUtil.CGCS2000)) {
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransCGCS2WGS(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLat()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLon()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgHeight()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        } else {
                            //自定义坐标系
                            SevenPrams st = new SevenPrams(vo.getC3xzpy(), vo.getC3yzpy(), vo.getC3zzpy(), vo.getC3xzxz(), vo.getC3yzxz(), vo.getC3zzxz(), vo.getC3bl());
                            PointGPS ptOut = CoordTransUtil.DatumTransUSER2WGS(ptInXYZ, vo.getC2zyzwx(), st, vo.getC2djcs(), vo.getC1cbz(), vo.getC1plds());
                            tvdstX.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLat()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstY.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgLon()).setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue()));
                            tvdstZ.getEditView(false).setText(String.valueOf(new BigDecimal(ptOut.getWgHeight()).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        }
                        break;
                }
            } else {
                ToastNotRepeat.show(this, "输入有误！");
            }
        } else {



            ToastNotRepeat.show(this, "请检查您的信息是否输入完整！");
            if (etsrcX.getEditView(true).getText().toString().equals("")) {
                etsrcX.requestFocus();
            } else if (etsrcY.getEditView(true).getText().toString().equals("")) {
                etsrcY.requestFocus();
            } else {
                etsrcZ.requestFocus();
            }
        }

    }

    //下拉框事件
    class SpinnerSelectedListenerTool implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                                   long id) {
            switch (position) {
                case 0://从WGS84大地坐标到地方坐标
                    etsrcX.getTextView().setText("纬度B");
                    etsrcY.getTextView().setText("经度L");
                    etsrcZ.getTextView().setText("高程H");

                    tvdstX.getTextView().setText("坐标x");
                    tvdstY.getTextView().setText("坐标y");
                    tvdstZ.getTextView().setText("坐标h");

                    etsrcX.getEditView(true).setText("");
                    etsrcY.getEditView(true).setText("");
                    etsrcZ.getEditView(true).setText("");
                    tvdstX.getEditView(false).setText("");
                    tvdstY.getEditView(false).setText("");
                    tvdstZ.getEditView(false).setText("");


                    break;
                case 1://从地方坐标到WGS84大地坐标
                    etsrcX.getTextView().setText("坐标x");
                    etsrcY.getTextView().setText("坐标y");
                    etsrcZ.getTextView().setText("坐标h");
                    tvdstX.getTextView().setText("纬度B");
                    tvdstY.getTextView().setText("经度L");
                    tvdstZ.getTextView().setText("高程H");
                    etsrcX.getEditView(true).setText("");
                    etsrcY.getEditView(true).setText("");
                    etsrcZ.getEditView(true).setText("");
                    tvdstX.getEditView(false).setText("");
                    tvdstY.getEditView(false).setText("");
                    tvdstZ.getEditView(false).setText("");
                    break;
                default:
                    break;
            }
        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    }


}
