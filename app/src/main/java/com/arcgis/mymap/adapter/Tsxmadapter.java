package com.arcgis.mymap.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arcgis.mymap.R;

/**
 * Created by Administrator on 2018/4/28.
 */

public class Tsxmadapter extends BaseAdapter {
    private String[] data;
    private int resourse;
    private Context context;
    public static int  selectItem=-1;

    public Tsxmadapter(String[] data, int resourse, Context context) {
        this.data = data;
        this.resourse = resourse;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        String datas=data[position];
        if (convertView == null){
            convertView = View.inflate(context,resourse,null);
            holder = new ViewHolder();
            holder.tsxt = (TextView) convertView.findViewById(R.id.tsxm);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tsxt.setText(datas);
        if (position == selectItem){
            convertView.setBackgroundColor(Color.YELLOW);
        }else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }
    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
    static class ViewHolder{
        TextView tsxt;
    }
}
