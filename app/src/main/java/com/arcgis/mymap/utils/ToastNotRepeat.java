package com.arcgis.mymap.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/12.
 */
public class ToastNotRepeat {
    /**
     * 避免Toast排队现象
     */
    private static Toast mToast;
    //判断Toast显示状态来控制不重复出现
    public static void show(Context context,String message){
        if (mToast==null){
            mToast=Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(message);
        }
        mToast.show();
    }
}
