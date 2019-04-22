package com.arcgis.mymap.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arcgis.mymap.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 */

public class DzstAdapter extends BaseAdapter {
    private List<SpannableString> data;
    private Context context;
    public static int  selectItem=-1;

    public DzstAdapter(List<SpannableString> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        SpannableString num=data.get(position);
        if (convertView==null){
            convertView=View.inflate(context, R.layout.colnumitem,null);
            holder=new ViewHolder();
            holder.textView= (TextView) convertView.findViewById(R.id.tx);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(num);
        if (position==selectItem){
            convertView.setBackgroundColor(Color.YELLOW);
        }else {
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }

    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    class ViewHolder{
        TextView textView;
    }

}
