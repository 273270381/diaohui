package com.arcgis.mymap.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/1/28.
 */
public class ScaleView extends View {
    private Paint mLinePaint;
    private Paint mTextPaint;
    private Paint kmTextPaint;
    private int max = 101;
    private int min = 0;
    private float centerX;
    private float centerY;
    private String text="";
    public ScaleView(Context context) {
        super(context);
        init();
    }

    public ScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void setText(String mtext){
        this.text=mtext;
        invalidate();
    }
    private void  init(){
        mLinePaint=new Paint();
        mLinePaint.setColor(Color.RED);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(2);
        mTextPaint=new Paint();
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setTextSize(30);
        kmTextPaint=new Paint();
        kmTextPaint.setColor(Color.BLUE);
        kmTextPaint.setAntiAlias(true);
        kmTextPaint.setStyle(Paint.Style.FILL);
        kmTextPaint.setStrokeWidth(1);
        kmTextPaint.setTextSize(30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(setMeasureWidth(widthMeasureSpec), setMeasureHeight(heightMeasureSpec));
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeigt = MeasureSpec.getSize(heightMeasureSpec);
        //获取屏幕中心点
        centerX = measureWidth/2;
        centerY = measureHeigt/2;
    }
    private int setMeasureHeight(int spec){
        int mode= MeasureSpec.getMode(spec);
        int size= MeasureSpec.getSize(spec);
        int result= Integer.MAX_VALUE;
        switch(mode){
            case MeasureSpec.AT_MOST:
                size= Math.min(result,size);
            break;
            case MeasureSpec.EXACTLY:
            break;
            default:
                size=result;
                break;
        }
        return size;
    }
    private int setMeasureWidth(int spec){
        int mode = MeasureSpec.getMode(spec);
        int size = MeasureSpec.getSize(spec);
        int result = Integer.MAX_VALUE;
        switch (mode) {
            case MeasureSpec.AT_MOST:
                size = Math.min(result, size);
                break;
            case MeasureSpec.EXACTLY:
                break;
            default:
                size = result;
                break;
        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try{
            canvas.save();
            String tr="米";
            canvas.drawText(tr,20,centerY-20,kmTextPaint);
            for (int i=min;i<max;i++){
                if (i%10==0){
                    canvas.drawLine(centerX,centerY-44,centerX,centerY,mLinePaint);
                    String t=String.valueOf(i/10*Integer.parseInt(text));
                    Rect rect=new Rect();
                    float txtWidth=mTextPaint.measureText(t);
                    mTextPaint.getTextBounds(t, 0, t.length(), rect);
                    if (i!=0){
                        canvas.drawText(t,centerX - txtWidth / 2,centerY + rect.height() + 10, mTextPaint);
                    }
                }else if (i%5==0){
                    canvas.drawLine(centerX,centerY-28,centerX,centerY,mLinePaint);
                }else {
                    canvas.drawLine(centerX, centerY-12, centerX, centerY, mLinePaint);
                }
                canvas.translate(18, 0);
            }
            canvas.restore();
            for (int i=min;i<max;i++){
                if (i%10==0){
                    canvas.drawLine(centerX,centerY-44,centerX,centerY,mLinePaint);
                    String t=String.valueOf(i/10*Integer.parseInt(text));
                    Rect rect=new Rect();
                    float txtWidth=mTextPaint.measureText(t);
                    mTextPaint.getTextBounds(t, 0, t.length(), rect);
                    if (i!=0){
                        canvas.drawText(t,centerX - txtWidth / 2,centerY + rect.height() + 10, mTextPaint);
                    }
                }else if (i%5==0){
                    canvas.drawLine(centerX,centerY-28,centerX,centerY,mLinePaint);
                }else {
                    canvas.drawLine(centerX, centerY-12, centerX, centerY, mLinePaint);
                }
                canvas.translate(-18, 0);
            }
            canvas.restore();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
