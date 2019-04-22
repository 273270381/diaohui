package com.arcgis.mymap.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arcgis.mymap.R;

import java.util.List;

/**
 * Created by Administrator on 2018/2/9.
 */

public class DataAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public DataAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        String s=list.get(position);
        if (convertView==null){
            convertView=View.inflate(context, R.layout.datalistitem,null);
            holder=new Holder();
            holder.textView= (TextView) convertView.findViewById(R.id.ts);
            convertView.setTag(holder);
        }else {
            holder= (Holder) convertView.getTag();
        }
        holder.textView.setText(s);
        return convertView;
    }
    class Holder{
        TextView textView;
    }
}
