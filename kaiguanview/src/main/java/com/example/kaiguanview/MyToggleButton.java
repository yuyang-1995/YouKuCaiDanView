package com.example.kaiguanview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * * 一个视图从创建到显示过程中的主要方法
 *  * //1.构造方法实例化类
 *  * //2.测量-measure(int,int)-->onMeasure();
 *  * 如果当前View是一个ViewGroup,还有义务测量孩子
 *  * 孩子有建议权
 *  * //3.指定位置-layout()-->onLayout();
 *  * 指定控件的位置，一般View不用写这个方法，ViewGroup的时候才需要，一般View不需要重写该方法
 *  * //4.绘制视图--draw()-->onDraw(canvas)
 *  * 根据上面两个方法参数，进入绘制
 */
public class MyToggleButton extends View implements View.OnClickListener{
    private Bitmap backgrooundBitmap;
    private Bitmap slideingBitmap;

    /**
     * 距离左边最大距离
     */

    private int slideLeftMax;
    private int slideLeft;

    private float startX;
    private float lastX;

    private boolean isOpen = false;

    Paint paint;

    public MyToggleButton(Context context) {
        super(context);
    }

    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {

        paint = new Paint();

        paint.setAntiAlias(true);
        backgrooundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        slideingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        //给自定义View注册点击监听
        setOnClickListener(this);
    }

    /**
     * 视图的测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(backgrooundBitmap.getWidth(), backgrooundBitmap.getHeight());

    }

    /**
     * 视图的绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(backgrooundBitmap, 0, 0, paint);
        canvas.drawBitmap(slideingBitmap, slideLeft, 0, paint);
    }

    /**
     * true 触发点击事件
     * false c触发滑动事件
     * @param v
     */

    boolean isEnableClick = true;
    @Override
    public void onClick(View v) {
        if (isEnableClick){
            isOpen = !isOpen;

            flushView();
         }
     }

    private void flushView() {

        if (isOpen){
            slideLeft = slideLeftMax;
            }else {
            slideLeft = 0;
        }

        invalidate();  //会导致onDraw 执行

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
         super.onTouchEvent(event);

        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                //1.记录触摸事件按下的坐标
                isEnableClick = true;
                lastX  = startX = event.getX();

                break;

            case MotionEvent.ACTION_MOVE:

                float endX = event.getX();

                //偏移量
                float distanceX = endX - startX;

                slideLeft += distanceX;
                if (slideLeft < 0){
                    slideLeft = 0;
                    //屏蔽非法制
                }else if (slideLeft > slideLeftMax){
                    slideLeft = slideLeftMax;
                }

                invalidate();

                //数据还原
                startX = event.getX();

                if (Math.abs(endX - startX) > 5){
                    //滑动
                    isEnableClick = false;
                }

                break;
        }


        return  true;



    }
}
