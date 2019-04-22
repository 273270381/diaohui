package com.arcgis.mymap.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.arcgis.mymap.R;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public class FloorAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    public static int  selectItem=-1;

    public FloorAdapter( List<String> list,Context context) {
        this.list=list;
        this.context = context;
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
        ViewHolder holder;
        String num=list.get(position);
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
