package com.example.jisuanqimodele;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   Button  btn_0,btn_1, btn_2, btn_3, btn_4,btn_5,btn_6,btn_7,btn_8,btn_9, btn_jia,btn_jian,btn_cheng,btn_chu,btn_fu,btn_dian,btn_cal,btn_gen,btn_deng,btn_cle;
   EditText et_num;

   private double sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initLitener();
    }

    private void initLitener() {
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_fu.setOnClickListener(this);
        btn_jia.setOnClickListener(this);
        btn_jian.setOnClickListener(this);
        btn_cheng.setOnClickListener(this);
        btn_chu.setOnClickListener(this);
        btn_dian.setOnClickListener(this);
        btn_deng.setOnClickListener(this);
        btn_cal.setOnClickListener(this);
        btn_cle.setOnClickListener(this);
        btn_gen.setOnClickListener(this);
    }

    private void initView(){
        et_num = findViewById(R.id.et_num);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_fu = findViewById(R.id.btn_fu);
        btn_jia = findViewById(R.id.btn_jia);
        btn_jian = findViewById(R.id.btn_jian);
        btn_cheng = findViewById(R.id.btn_cheng);
        btn_chu = findViewById(R.id.btn_chu);
        btn_gen = findViewById(R.id.btn_gen);
        btn_cal = findViewById(R.id.btn_cal);
        btn_cle = findViewById(R.id.btn_cle);
        btn_deng = findViewById(R.id.btn_deng);
        btn_dian = findViewById(R.id.btn_dian);
      }

    @Override
    public void onClick(View v) {


        switch (v.getId()){


        }





    }
}
