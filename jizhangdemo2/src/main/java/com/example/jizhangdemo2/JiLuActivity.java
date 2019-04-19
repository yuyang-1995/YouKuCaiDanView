package com.example.jizhangdemo2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jizhangdemo2.database.RecordBean;
import com.example.jizhangdemo2.database.RecoreHelper;

import java.util.ArrayList;

public class JiLuActivity extends AppCompatActivity {

    private  String str_kind, str_content, str_money;
    private TextView tv_select;
    private Button btn_save, btn_history;
    private ImageView iv_history;
    private EditText et_content, et_money;
    private RecoreHelper recoreHelper;
    private SQLiteDatabase database;
    private ArrayList<RecordBean> list_record;
    private String str_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_lu);

        initDatas();
        initView();
        initListener();
    }

    private void initDatas() {
      Intent intent = getIntent();
      str_kind = intent.getStringExtra("item_name");

      recoreHelper = new RecoreHelper(this);
      database = recoreHelper.getWritableDatabase();
      list_record = new ArrayList<RecordBean>();


    }

    private void initView(){
        tv_select = findViewById(R.id.tv_select);
        tv_select.setText(str_kind);
        btn_save = findViewById(R.id.btn_save);
        btn_history = findViewById(R.id.btn_history);
        iv_history = findViewById(R.id.iv_history);
        et_content = findViewById(R.id.et_content);
        et_money = findViewById(R.id.et_money);





    }

    private void initListener(){

        //查询历史账单
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        iv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //保存此次账单
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_content =  et_content.getText().toString();
                str_money =  et_money.getText().toString();

                if (str_money!=null && str_kind!=null && str_content!=null){

                    ContentValues values = new ContentValues();
                    values.put("record_content", str_content);
                    values.put("record_time", str_time);
                    values.put("record_money", str_money);
                    values.put("record_kind", str_kind);

                    database.insert("tb_record", null, values);
                    values.clear();
                }else {
                    Toast.makeText(JiLuActivity.this, "数据不完整", Toast.LENGTH_SHORT);

                }



            }
        });



    }






}
