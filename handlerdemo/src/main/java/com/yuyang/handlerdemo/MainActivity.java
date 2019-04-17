package com.yuyang.handlerdemo;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    Button btn1, btn2, btn3, btn4;

        //内部类
        class MyHandler extends Handler{
            @Override
            public void handleMessage(Message msg) {

                switch (msg.what){

                    case 1:

                        t1();
                        break;
                    case 2:
                        t2();
                        default:
                            break;
                       }
                  }
        }

        MyHandler handler1 = new MyHandler();

        //匿名内部类
        Handler handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
               switch (msg.what){

               }
            }
        };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.iv);

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }


   public void t1(){

       ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360, 180);

       objectAnimator.setDuration(2000);

       objectAnimator.start();


    }

    void t2(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360, 180);

        objectAnimator.setDuration(2000);

        objectAnimator.start();

    }

    void t3(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0, 200, 180);

        objectAnimator.setDuration(2000);

        objectAnimator.start();

    }

    void t4(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "translationY", 0, 200, 180);

        objectAnimator.setDuration(2000);

        objectAnimator.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_1:
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Message message = Message.obtain();
                        message.what = 1;
                        handler1.sendMessage(message);
                    }
                });
                t1.start();

                break;
            case R.id.btn_2:

                handler1.sendEmptyMessageDelayed(2, 1000);

                break;
            case R.id.btn_3:

                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        t3();

                    }
                }, 1000);



                break;
            case R.id.btn_4:

                handler1.post(new Runnable() {
                    @Override
                    public void run() {
                        t4();
                    }
                });

                break;
                default:
                    break;

        }

    }
}
