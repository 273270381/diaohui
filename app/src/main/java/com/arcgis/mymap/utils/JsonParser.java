package com.arcgis.mymap.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

/**
 * Json结果解析类
 */
public class JsonParser {
	public static String parseIatResult(String json){
		//StringBuffer ret = new StringBuffer();
		String words="";
		try {
			JSONTokener tokener = new JSONTokener(json);
			JSONObject joResult = new JSONObject(tokener);
			words = joResult.getString("text");
		}catch (JSONException e) {
			e.printStackTrace();
		}
		return words;
	}
}
