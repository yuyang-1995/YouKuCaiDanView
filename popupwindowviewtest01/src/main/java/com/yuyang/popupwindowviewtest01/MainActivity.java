package com.yuyang.popupwindowviewtest01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    PopupWindow popupWindow;

    Button btn_one;

    LinearLayout ll_root;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ll_root = findViewById(R.id.ll_one);
        btn_one = findViewById(R.id.btn_one);

        LayoutInflater inflater = (LayoutInflater) getLayoutInflater();

        final View contentView = inflater.inflate(R.layout.bianji_view, null);

        popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);


        popupWindow.setContentView(contentView);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                WindowManager.LayoutParams lp = getWindow().getAttributes();

                lp.alpha = 1.0f;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                getWindow().setAttributes(lp);

            }
        });




//        et_input.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if( popupWindow == null){
//                    popupWindow = new PopupWindow(MainActivity.this);
//                    popupWindow.setWidth(et_input.getWidth());
//                    int height = DensityUtil.dip2px(MainActivity.this,200);//dp->px
//                    Toast.makeText(MainActivity.this,"height=="+height,Toast.LENGTH_SHORT).show();
//                    popupWindow.setHeight(height);//px
//
//                    popupWindow.setContentView(listview);
//                    popupWindow.setFocusable(true);//设置焦点
//                }
//
//                popupWindow.showAsDropDown(et_input,0,0);
//            }
//        });

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.showAtLocation(ll_root, Gravity.BOTTOM, 0,0);

                WindowManager.LayoutParams lp = getWindow().getAttributes();

                lp.alpha = 0.8f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

                getWindow().setAttributes(lp);

            }
        });






    }
}
