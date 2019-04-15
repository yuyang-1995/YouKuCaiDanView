package com.yuyang.guanggaoview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.MessageQueue;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager viewpager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    //ListView的使用
    //1.在布局文件中定义ListView
    //2.在代码中实例化ListView
    //3.准备数据
    //4.设置适配器-item布局-绑定数据

    private ArrayList<ImageView> imageViews;



    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = viewpager.getCurrentItem()+1;
            viewpager.setCurrentItem(item);

            //延迟发消息
            handler.sendEmptyMessageDelayed(0,4000);
        }
    };

    // 图片资源ID
    private final int[] imageIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };

    // 图片标题集合
    private final String[] imageDescriptions = {
            "尚硅谷波河争霸赛！",
            "凝聚你我，放飞梦想！",
            "抱歉没座位了！",
            "7月就业名单全部曝光！",
            "平均起薪11345元"
    };

    private int prePosition;
    private boolean isDragging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initListener();

        tv_title.setText(imageDescriptions[prePosition]);

        //发消息
        handler.sendEmptyMessageDelayed(0,3000);
    }

    private void initListener() {

        MyOnPageChangeListener myOnPageChangeListener = new MyOnPageChangeListener();

        viewpager.addOnPageChangeListener(myOnPageChangeListener);

    }

    private void initView(){

        viewpager = findViewById(R.id.viewpager);

        tv_title = findViewById(R.id.tv_title);

        ll_point_group = findViewById(R.id.ll_point_group);


        imageViews = new ArrayList<>();

       // ArrayList<ImageView> arrayList_imageView = new ArrayList();
        for (int i=0; i<imageIds.length ; i++){
              ImageView imageView = new ImageView(MainActivity.this);
              imageView.setBackgroundResource(imageIds[i]);
              Log.e("MainActivity imageView", imageView.toString());
              imageViews.add(imageView);


              //给ll_point_group添加导航点
            ImageView point = new ImageView(this);
            int width  = DensityUtil.dip2px(this, 8);

            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,width);
            if(i==0){
                point.setEnabled(true); //显示红色
            }else{
                point.setEnabled(false);//显示灰色
                params.leftMargin = width;
            }


            point.setLayoutParams(params);

            ll_point_group.addView(point);
        }

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(imageViews, imageDescriptions, this);

        viewpager.setAdapter(myPagerAdapter);


        //设置中间位置
        int item = Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2%imageViews.size();//要保证imageViews的整数倍


        viewpager.setCurrentItem(item);


    }


    class MyOnPageChangeListener  implements ViewPager.OnPageChangeListener {


        //
        /**
         * 当页面滚动了的时候回调这个方法
         * @param i 当前页面的位置
         * @param v 滑动页面的百分比
         * @param i1 在屏幕上滑动的像数
         */
        @Override
        public void onPageScrolled(int i, float v, int i1) {


        }

        //
        /**
         * 当某个页面被选中了的时候回调
         * @param i 被选中页面的位置
         */
        @Override
        public void onPageSelected(int i) {

            int realPosition = i%imageDescriptions.length;
            Log.e("MainActivity", "onPageSelected==" + realPosition );
           // Toast.makeText(MainActivity.this, imageDescriptions[realPosition], Toast.LENGTH_SHORT).show();
            //设置对应页面的文本信息
            tv_title.setText(imageDescriptions[realPosition]);

            //把上一个高亮的设置默认-灰色
            ll_point_group.getChildAt(prePosition).setEnabled(false);

            //当前的设置为高亮-红色
            ll_point_group.getChildAt(realPosition).setEnabled(true);

            prePosition = realPosition;

        }



        //
        /**
         当页面滚动状态变化的时候回调这个方法
         静止->滑动
         滑动-->静止
         静止-->拖拽
         */
        @Override
        public void onPageScrollStateChanged(int i) {

            if(i == ViewPager.SCROLL_STATE_DRAGGING){
                isDragging = true;
                handler.removeCallbacksAndMessages(null);
                Log.e(TAG,"SCROLL_STATE_DRAGGING-------------------");
            }else if(i == ViewPager.SCROLL_STATE_SETTLING){
                Log.e(TAG,"SCROLL_STATE_SETTLING-----------------");

            }else if(i == ViewPager.SCROLL_STATE_IDLE&&isDragging){
                isDragging = false;
                Log.e(TAG,"SCROLL_STATE_IDLE------------");
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,4000);
            }

        }
    }
}
