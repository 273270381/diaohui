package com.arcgis.mymap;
import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.arcgis.mymap.contacts.MyDatabaseHelper;
import com.arcgis.mymap.utils.AppUtils;
import com.arcgis.mymap.utils.LogUtils;
import com.arcgis.mymap.utils.MD5Utils;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/21.
 */
public class WelcomeActivity extends AppCompatActivity {
    TextView etUsername;
    EditText etPassword;
    Button Submit;
    String username,password,confromPwd;
    String Id=null;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public MyDatabaseHelper dbHelper;
    public SQLiteDatabase db;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_PHONE_STATE = 2;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏以及状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题**/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        try {
            setView();
        } catch (Exception e) {
            e.printStackTrace();
        }
        verifyStoragePermissions(this);
        addlistener();
    }
    private void addlistener() {
        Mylistener listener=new Mylistener();
        Submit.setOnClickListener(listener);
    }
    public void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission1 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);
        if (permission1!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_PHONE_STATE);
        }else {
            getId();
        }
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }
    private void setView() throws Exception {
        dbHelper=new MyDatabaseHelper(this, "pointsStore.db", null, 5);
        db = dbHelper.getWritableDatabase();
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        etPassword= (EditText) findViewById(R.id.et_login_password);
        Submit= (Button) findViewById(R.id.login);
        String password=preferences.getString("password","");
        etPassword.setText(password);
        etUsername= (TextView) findViewById(R.id.et_login_username);
    }
    //获取设备IMEI码
    private void getId(){
        final AppUtils appUtils=new AppUtils(this);
        Id= appUtils.getIMEI();
        LogUtils.i("TAG","id="+Id);
        if (Id==null||Id.equals("")){
            Id=appUtils.getAndroidID();
            LogUtils.i("TAG","threadid="+Id);
        }
        etUsername.setText("本机设备ID:"+Id+" (长按复制)");
        if (Id.indexOf("0")!=-1){
            username="OpticsX"+Id.replace("0","a");
        }else {
            username="OpticsX"+Id;
        }
        confromPwd= MD5Utils.getStringMD5(username);
        LogUtils.i("TAG","word="+confromPwd);
        if (!TextUtils.isEmpty(etPassword.getText())){
            etPassword.setVisibility(View.GONE);
            Intent intent=new Intent(WelcomeActivity.this,ProjectActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(WelcomeActivity.this,"连接成功",Toast.LENGTH_LONG).show();
        }
    }
    //登录验证
    class Mylistener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.login:
                    password=etPassword.getText().toString();
                    StringBuilder builder=new StringBuilder();
                    if (!password.equals(confromPwd)){
                        builder.append("密码错误\n");
                        LogUtils.i("TAG","id="+confromPwd);
                    }
                    if (!TextUtils.isEmpty(builder.toString())){
                        Toast.makeText(WelcomeActivity.this,builder.toString(),Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (password.equals(confromPwd)){
                        editor=preferences.edit();
                        editor.putString("password",password);
                        editor.apply();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        String time=simpleDateFormat.format(date);
                        LogUtils.i("TAG",time);
                        ContentValues values=new ContentValues();
                        values.put("time",time);
                        db.insert("Newtimes",null,values);
                        values.clear();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent(WelcomeActivity.this, ProjectActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(WelcomeActivity.this,"连接成功",Toast.LENGTH_LONG).show();
                            }
                        },500);
                    }
                break;
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case REQUEST_PHONE_STATE:
                getId();
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
