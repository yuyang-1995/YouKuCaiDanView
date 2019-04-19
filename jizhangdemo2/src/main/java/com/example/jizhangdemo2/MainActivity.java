package com.example.jizhangdemo2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jizhangdemo2.database.RecordBean;
import com.example.jizhangdemo2.database.RecoreHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView lv_jz, lv_input;
    private Button btn_go;
    private FloatingActionButton fb_plus;
    private PopupWindow popupWindow;

    private View mainView;

    private RecoreHelper recoreHelper;
    private SQLiteDatabase database;




    private ArrayList<RecordBean> list_rd;

    int screenHeight;
    int screenWidth;

    TextView tv_empty;


     private View popView;
     private String[] str_inputText = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initView();
        initLsitener();
    }


    //获取记录数量
    private void initDatas() {

        str_inputText = new String[]{"早餐", "午餐", "晚餐", "电子产品", "日化用品", "衣服", "娱乐", "医疗", "投资理财","公益慈善"};

        recoreHelper = new RecoreHelper(this);
        database = recoreHelper.getWritableDatabase();
        list_rd = new ArrayList<>();

        String sql = "select * from tb_record order by record_id asc" ;

        //遍历查询到的记录，将记录封装到RouteRecord集合中
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            RecordBean record =  new RecordBean();
            record.setStr_content(cursor.getString(cursor.getColumnIndex("record_content")));
            record.setStr_money(cursor.getString(cursor.getColumnIndex("record_money")));
            record.setStr_time(cursor.getString(cursor.getColumnIndex("record_time")));
            record.setStr_kind(cursor.getString(cursor.getColumnIndex("record_kind")));

            list_rd.add(record);
        }
        cursor.close();
    }

    private void initView() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        lv_jz = findViewById(R.id.lv_jz);
        btn_go  =findViewById(R.id.btn_go);
        fb_plus = findViewById(R.id.fb_plus);
        RDListAdapter adapter = new RDListAdapter(list_rd);
        mainView = findViewById(R.id.ll_main);

        popView = LayoutInflater.from(this).inflate(R.layout.popw_plus,null);
        // popView.setBackgroundDrawable(new ColorDrawable("#666"));
        popupWindow = new PopupWindow(popView, (int)(screenWidth*0.8), (int) (screenHeight*0.7),false);

        if (list_rd.size() !=0){
            lv_jz.setAdapter(adapter);
            lv_jz.setVisibility(View.VISIBLE);
            tv_empty.setVisibility(View.GONE);
        }

        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        lv_input = popView.findViewById(R.id.lv_input);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,str_inputText );

        lv_input.setAdapter(arrayAdapter);
    }

    private void initLsitener(){

        lv_input.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item_name = str_inputText[position];

                Intent intent = new Intent(MainActivity.this, JiLuActivity.class);
                intent.putExtra("item_name", item_name);
                startActivity(intent);

                Toast.makeText(MainActivity.this, "记录:" + str_inputText[position], Toast.LENGTH_SHORT).show();

            }
        });

        fb_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.showAtLocation(mainView,Gravity.TOP, 0, 200);

            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {



            }
        });


        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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
                viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
                viewHolder.tv_num = convertView.findViewById(R.id.tv_num);
                convertView.setTag(viewHolder);
            }else {

                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_num.setText(RdList.get(position).getStr_time());
            viewHolder.tv_content.setText(RdList.get(position).getStr_content());

            return convertView;
        }
    }

    class ViewHolder{
        TextView tv_num;
        TextView tv_content;
    }


}
