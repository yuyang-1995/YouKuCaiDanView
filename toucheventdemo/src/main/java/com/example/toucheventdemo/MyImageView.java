package com.example.toucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MyImageView extends android.support.v7.widget.AppCompatImageView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public boolean dispatchTouchEvent(MotionEvent ev) {
        android.util.Log.e("eventTest", "MyImageView | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);

    }


    public boolean onTouchEvent(MotionEvent ev) {
        android.util.Log.d("eventTest", "MyImageView | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
       return super.onTouchEvent(ev);

    }
}
