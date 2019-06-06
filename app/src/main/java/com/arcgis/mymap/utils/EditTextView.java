package com.arcgis.mymap.utils;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arcgis.mymap.R;

/**
 * Created by Frank on 2016/9/10.
 */
public class EditTextView extends LinearLayout {

    /**
     * 使用方法
     * 在初始化View(Activity、Fragment等)时，要调用EditTextView.getEditView(boolean);
     * 如果是只需要输入数字、小数、负数等，boolean = true;此时默认软键盘为数字界面，且输入框只能输入数字等
     * 如果是常规的，boolean = false;此时默认软键盘为字母界面，且输入框输入无限制
     */
    private RelativeLayout linearLayout;
    private EditText edit;
    private EditText edit2;
    private EditText edit3;
    private TextView text;
    private TextView point;

    public EditTextView(Context context) {
        super(context);
        initView();
    }

    public EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public EditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.edit_text_layout, this);
        linearLayout = (RelativeLayout) findViewById(R.id.linear);
        edit = (EditText) findViewById(R.id.edit);
        edit2 = (EditText) findViewById(R.id.edit2);
        edit3 = (EditText) findViewById(R.id.edit3);
        text = (TextView) findViewById(R.id.text);
        point = (TextView) findViewById(R.id.textPoint);
        edit.setOnFocusChangeListener(listener);
        edit2.setOnFocusChangeListener(listener);
        edit3.setOnFocusChangeListener(listener);


    }

    //增加输入完毕事件
    public void addTextChangedListener(TextWatcher watcher) {
        edit.addTextChangedListener(watcher);
        edit2.addTextChangedListener(watcher);
        edit3.addTextChangedListener(watcher);
    }

    public void addHint(String hint) {
        edit.setHint(hint);
        edit2.setHint(hint);
        edit3.setHint(hint);
    }

    //焦点改变事件
    OnFocusChangeListener listener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean isFocus) {
            if (isFocus) {
                linearLayout.setBackground(getResources().getDrawable(R.drawable.bg_edittext_focused));
            } else {
                linearLayout.setBackground(getResources().getDrawable(R.drawable.bg_edittext_normal));
            }
        }
    };

    //获取输入框对象
    public EditText getEditView(boolean isNumber) {
        if (isNumber) {
            edit.setVisibility(VISIBLE);
            edit2.setVisibility(GONE);
            edit3.setVisibility(GONE);
            return edit;
        } else {
            edit.setVisibility(GONE);
            edit2.setVisibility(VISIBLE);
            edit3.setVisibility(GONE);
            return edit2;
        }
    }

    public EditText getEditView(int i) {
        if (i == 0) {
            edit.setVisibility(GONE);
            edit2.setVisibility(GONE);
            edit3.setVisibility(VISIBLE);
        }
        return edit3;
    }

    //获取前面提示的对象
    public TextView getTextView() {
        return text;
    }

    //获取冒号的对象
    public TextView getTextPoint() {
        return point;
    }

}
