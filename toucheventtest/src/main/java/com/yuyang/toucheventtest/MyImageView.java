package com.yuyang.toucheventtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 作者：杨光福 on 2016/5/16 17:00
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class MyImageView extends ImageView {
    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        android.util.Log.e("eventTest", "MyImageView | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()) );
        return super.dispatchTouchEvent(ev);
//		return true;
    }


    public boolean onTouchEvent(MotionEvent ev) {
        android.util.Log.e("eventTest", "MyImageView | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()) );
//        return super.onTouchEvent(ev);
		return true;
    }
}
