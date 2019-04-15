package com.yuyang.youkucaidanview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rl_revel1, re_relevl2, re_revel3;

    ImageView iv_home, iv_mean;


    boolean isFirst = true, isSecond = true, isThired = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initListener();
    }

    private void initView() {

        rl_revel1 = findViewById(R.id.level1);
        re_relevl2 = findViewById(R.id.level2);
        re_revel3 = findViewById(R.id.level3);


        iv_home = findViewById(R.id.icon_home);
        iv_mean = findViewById(R.id.icon_menu);

    }

    void initListener(){
        rl_revel1.setOnClickListener(new MyOnClckLitensr());
        re_relevl2.setOnClickListener(new MyOnClckLitensr());
        re_revel3.setOnClickListener(new MyOnClckLitensr());

        iv_mean.setOnClickListener(new MyOnClckLitensr());
        iv_home.setOnClickListener(new MyOnClckLitensr());
    }


    class MyOnClckLitensr implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.level1:
                    Toast.makeText(MainActivity.this, "Level1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.level2:
                    Toast.makeText(MainActivity.this, "Level2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.level3:
                    Toast.makeText(MainActivity.this, "Level3", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.icon_home:
                       if (isSecond){
                        isSecond = false;

                        //隐藏
                        Tools.hideView(re_relevl2);

                        if (isThired){

                            isThired = false;
                            Tools.hideView(re_revel3);
                          }

                    }else{
                        isSecond = true;
                        Tools.showView(re_relevl2);
                    }
                    break;

                case R.id.icon_menu:

                    if (isThired){

                        isThired = false;
                        Tools.hideView(re_revel3);
                    }else {

                        isThired = true;
                        Tools.showView(re_revel3, 200);
                    }
                    break;



            }

        }
    }
}
