package com.example.toucheventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchEventFather extends LinearLayout {


    public TouchEventFather(Context context) {
        super(context);
    }

    public TouchEventFather(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.e("eventTest", "TouchEventFather | dispatchTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));
       // return super.dispatchTouchEvent(ev);
        return  false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("eventTest", "TouchEventFather | onInterceptTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));

    // return super.onInterceptTouchEvent(ev);
     // return  true;

        return  false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("eventTest", "TouchEventFather | onTouchEvent -->" + TouchEventUtil.getTouchAction(ev.getAction()));
       // return super.onTouchEvent(ev);

        return true;
    }
}
