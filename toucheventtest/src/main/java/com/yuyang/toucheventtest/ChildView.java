package com.yuyang.toucheventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Author: yuyang
 * Date:2019/4/15 20:37
 */
public class ChildView extends LinearLayout {

    public ChildView(Context context) {
        super(context);
    }

    public ChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("eventTest", "Childs | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
//		return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("eventTest", "Childs | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onInterceptTouchEvent(ev);
		//return true;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        android.util.Log.e("eventTest", "Childs | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.onTouchEvent(ev);
      //  return super.onTouchEvent(ev);
    }
}
