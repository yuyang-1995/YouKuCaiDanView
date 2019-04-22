package com.yuyang.toucheventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Author: yuyang
 * Date:2019/4/15 20:32
 */
public class FatherView extends LinearLayout {


    public FatherView(Context context) {
        super(context);
    }

    public FatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("eventTest", "Father | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
      return super.dispatchTouchEvent(ev);
	 // return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("eventTest", "Father | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
         return super.onInterceptTouchEvent(ev);
		//return true;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("eventTest", "Father | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
       // return super.onTouchEvent(ev);
   		return true;
    }
}
