package com.arcgis.mymap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arcgis.mymap.R;
import com.arcgis.mymap.utils.DisplayUtil;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerVIewHolder> implements View.OnClickListener{
    private String[] str = {"点绘线","手绘线","文字"};
    private Integer[] imager = {R.mipmap.dianhuixian,R.mipmap.shouhuixian,R.mipmap.wenzi};
    private int oldPosition = 0;
    public static int  selectItem=-1;
    private Context context;
    private OnItemClickListener itemClickListener;

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerVIewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerVIewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyvlerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerVIewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        //layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;//屏幕自适应
        layoutParams.width = DisplayUtil.getAndroiodScreenWidth(context);
        holder.imageView.setImageResource(imager[position]);
        holder.textView.setText(str[position]);
        holder.itemView.setTag(position);
        if (position == selectItem){
            holder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.color4));
            holder.textView.setTextColor(context.getResources().getColor(R.color.color22));
        }else {
            holder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.color3));
            holder.textView.setTextColor(context.getResources().getColor(R.color.color18));
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClick(v);
    }

    public interface OnItemClickListener{
        void onItemClick(View view);
    }
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public  void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }
    class RecyclerVIewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public RecyclerVIewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            textView = (TextView) itemView.findViewById(R.id.textview);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);

            itemView.setOnClickListener(RecyclerAdapter.this);
        }
    }
}
