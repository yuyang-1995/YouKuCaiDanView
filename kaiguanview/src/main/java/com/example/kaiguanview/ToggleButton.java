package com.example.kaiguanview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ToggleButton extends View implements View.OnClickListener{

    Bitmap bitmap_bg, bitmap_for;
    float startX, lastX;

    Paint paint;
    int slideLeft, slideLeftMax;

    boolean isOpen = false, isClickable=true;



    public ToggleButton(Context context) {
        super(context);
        initView();
    }

     public ToggleButton(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(bitmap_bg.getWidth(), bitmap_bg.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap_bg,0,0, paint);
        canvas.drawBitmap(bitmap_for, slideLeft, 0, paint);
    }

    private void initView() {
        bitmap_bg = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);

        bitmap_for = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);

        slideLeftMax = bitmap_bg.getWidth() - bitmap_for.getWidth();

        setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        //可点击才可刷新View
        if (isClickable){
            isOpen = !isOpen;
            flushView();
        }
    }

    //根据isOpen 修改slideLeft实现开关移动
    private void flushView() {

        if (isOpen){
            slideLeft = slideLeftMax;

        }else {
            slideLeft = 0;
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       switch (event.getAction()) {
           case MotionEvent.ACTION_DOWN:
               startX = lastX = event.getX();

               isClickable = true;
               break;
           case MotionEvent.ACTION_MOVE:

               float endX = event.getX();

               float distanceX = endX - startX;

               //记录move事件中 的滑动距离
               slideLeft += distanceX;
               //避免无效值
               if (slideLeft < 0) {
                   slideLeft = 0;
               } else if (slideLeft > slideLeftMax) {
                   slideLeft = slideLeftMax;
               }
               //Move过程中根据slideLeft 的刷新实现滑动
               invalidate();

               startX = event.getX();

               //在滑动过程中抬起前的x 坐标大于按下时x坐标超过5 则按钮设置为不可点击
               //关闭点击
               if (Math.abs(endX - lastX) > 5) {
                   isClickable = false;
               }

               break;
           case MotionEvent.ACTION_UP:
               //不可点击时才触发抬起事件
               if (!isClickable) {

                   if (slideLeft > slideLeftMax / 2) {
                       isOpen = true;
                   } else {
                       isOpen = false;
                   }
                   flushView();

               }
               break;


       }

       return  true;
    }
}
