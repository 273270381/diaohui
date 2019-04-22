package com.arcgis.mymap.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

/**
 * Created by Administrator on 2018/2/26.
 */

public class AppUtils {
    private Context mcontext;

    public AppUtils(Context mcontext) {
        this.mcontext = mcontext;
    }

    public String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) mcontext.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        String imei = telephonyManager.getDeviceId();
        return imei;
    }
    /* public String getPesuUNID(){
         Stri  ng unid="636"+ Build.BOARD.length()%10+
                 Build.BRAND.length()%10+
                 Build.CPU_ABI.length()%10+
                 Build.DEVICE.length()%10+
                 Build.DISPLAY.length()%10+
                 Build.MANUFACTURER.length()%10+
                 Build.MODEL.length()%10+
                 Build.PRODUCT.length()%10+
                 Build.TAGS.length()%10+
                 Build.TYPE.length()%10+
                 Build.USER.length()%10;
         return unid;
     }*/
    public String getAndroidID(){
        String m_android= Settings.Secure.getString(mcontext.getContentResolver(),Settings.Secure.ANDROID_ID);
        return m_android;
    }
}
