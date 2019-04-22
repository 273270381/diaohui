package com.arcgis.mymap.Geological.Voice;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arcgis.mymap.dialogActivity.Dixingdimao;
import com.arcgis.mymap.utils.JsonParser;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static android.widget.Toast.makeText;

public class VoiceToText {
    public static void startSpeechDialog(final Context context, final EditText editText){
        final HashMap<String,String> mIatResults = new LinkedHashMap<String,String>();
        //1. 创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(context, new InitListener() {
            @Override
            public void onInit(int i) {
                if(i != ErrorCode.SUCCESS){
                    showTip(context,"初始化失败 ");
                }
            }
        }) ;
        //2. 设置accent、 language等参数,
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn" );// 设置中文
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin" );
        mDialog.setParameter(SpeechConstant.RESULT_TYPE,"json");
        // 若要将UI控件用于语义理解，必须添加以下参数设置，设置之后 onResult回调返回将是语义理解
        mDialog.setParameter("asr_sch", "1");
        mDialog.setParameter("nlp_version", "3.0");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                String result = recognizerResult.getResultString();//为解析的
                String text = JsonParser.parseIatResult(result);//解析过后的
                String sn = null;
                try {
                    JSONObject resultJson = new JSONObject(recognizerResult.getResultString()) ;
                    sn = resultJson.optString("sn" );
                    Log.i("TAG","sn="+sn);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mIatResults .put(sn, text) ;//没有得到一句，添加到
                StringBuffer resultBuffer = new StringBuffer();
                for(String key:mIatResults.keySet()){
                    resultBuffer.append(mIatResults .get(key));
                }
                editText.setText(resultBuffer.toString());// 设置输入框的文本
                editText .setSelection(editText.length()) ;//把光标定位末尾
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        }) ;
        //4. 显示dialog，接收语音输入
        mDialog.show() ;
        TextView txt = (TextView) mDialog.getWindow().getDecorView().findViewWithTag("textlink");
        txt.setText("");
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public static void showTip (Context context,String data) {
        makeText( context, data, Toast.LENGTH_SHORT).show() ;
    }
}
