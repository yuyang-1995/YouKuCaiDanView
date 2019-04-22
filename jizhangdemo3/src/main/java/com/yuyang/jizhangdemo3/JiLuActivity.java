package com.yuyang.jizhangdemo3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.yuyang.jizhangdemo3.database.RecordBean;
import com.yuyang.jizhangdemo3.database.RecoreHelper;

import java.util.ArrayList;
import java.util.Map;

public class JiLuActivity extends AppCompatActivity {

    private  String str_kind, str_content, str_money;
    private TextView tv_select;
    private Button btn_save, btn_history;
    private ImageView iv_history, iv_back;
    private EditText et_content, et_money;
    private RecoreHelper recoreHelper;
    private SQLiteDatabase database;
    private ArrayList<RecordBean> list_record;
    private String str_time;

    private ListView listView_history;

    private PopupWindow popupWindow;
    private View popView;
    private int screenHeight;
    private int screenWidth;

    private View mainView;
    private ListView lv_hisinput;

    RDListAdapter adapter;

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



      //查询所有数据
        String str_sql = "select * from tb_record order by record_id asc";


        Cursor cursor = database.rawQuery(str_sql, null);

        while (cursor.moveToNext()){

            RecordBean record = new RecordBean();
            record.setStr_content(cursor.getString(cursor.getColumnIndex("record_content")));
            record.setStr_kind(cursor.getString(cursor.getColumnIndex("record_kind")));
            record.setStr_money(cursor.getString(cursor.getColumnIndex("record_money")));
            record.setStr_time(cursor.getString(cursor.getColumnIndex("record_time")));

            list_record.add(record);
        }

        cursor.close();
        database.close();


        adapter = new RDListAdapter(list_record);

    }

    private void initView(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;

        mainView = findViewById(R.id.ll_jilu);
        iv_back = findViewById(R.id.iv_back);

        tv_select = findViewById(R.id.tv_select);
        tv_select.setText(str_kind);
        btn_save = findViewById(R.id.btn_save);
        btn_history = findViewById(R.id.btn_history);
        iv_history = findViewById(R.id.iv_history);
        et_content = findViewById(R.id.et_content);
        et_money = findViewById(R.id.et_money);


        popView = LayoutInflater.from(this).inflate(R.layout.popw_his, null);

        lv_hisinput = popView.findViewById(R.id.lv_hisinput);
        lv_hisinput.setAdapter(adapter);

        popupWindow = new PopupWindow(popView, (int)(screenWidth*0.8), (int) (screenHeight*0.7),false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp=getWindow().getAttributes();
                lp.alpha=1.0f;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });

    }

    private void initListener(){

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JiLuActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //查询历史账单
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager.LayoutParams lp=getWindow().getAttributes();

                lp.alpha=0.3f;

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
                popupWindow.showAtLocation(mainView, Gravity.TOP, 0, 200);

            }
        });


        iv_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.showAtLocation(mainView, Gravity.TOP, 0, 200);

            }
        });



        //保存此次账单
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_content =  et_content.getText().toString();
                str_money =  et_money.getText().toString();
                long time = System.currentTimeMillis();
                str_time  = DateUtils.getDateFromMillisecond(time);   //"yyyy-MM-dd HH:mm:ss"

                if (str_money!=null && str_kind!=null && str_content!=null){

                    ContentValues values = new ContentValues();
                    values.put("record_content", str_content);
                    values.put("record_time", str_time);
                    values.put("record_money", str_money);
                    values.put("record_kind", str_kind);

                    database.insert("tb_record", null, values);
                    values.clear();
                    database.close();

                    Toast.makeText(JiLuActivity.this, "已保存", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(JiLuActivity.this, "数据不完整", Toast.LENGTH_SHORT);

                }

            }
        });



    }

    class RDListAdapter extends BaseAdapter {

        ArrayList<RecordBean> RdList;

        public RDListAdapter(ArrayList<RecordBean> RdList){
            this.RdList = RdList;
        }


        @Override
        public int getCount() {
            return RdList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;

            if (convertView == null){
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_jz,null);
                viewHolder.tv_kind = convertView.findViewById(R.id.tv_kind);
                viewHolder.tv_num = convertView.findViewById(R.id.tv_num);
                viewHolder.tv_monty = convertView.findViewById(R.id.tv_money);
                viewHolder.tv_content = convertView.findViewById(R.id.tv_content);

                convertView.setTag(viewHolder);
            }else {

                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_num.setText(RdList.get(position).getStr_time());
            viewHolder.tv_kind.setText(RdList.get(position).getStr_kind());
            viewHolder.tv_content.setText(RdList.get(position).getStr_content());
            viewHolder.tv_monty.setText(RdList.get(position).getStr_money());

            return convertView;
        }
    }

    class ViewHolder{
        TextView tv_num;
        TextView tv_kind;
        TextView tv_content;
        TextView tv_monty;

    }

}
