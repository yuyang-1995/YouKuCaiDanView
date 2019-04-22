package com.yuyang.jizhangdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yuyang.jizhangdemo.database.RecordBean;
import com.yuyang.jizhangdemo.database.RecoreHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv_jz;
    private Button btn_go;
    private FloatingActionButton fb_plus;
    private PopupWindow popupWindow;
    private TextView tv_empty;

    private RecoreHelper recoreHelper;
    private SQLiteDatabase database;

    private ArrayList<RecordBean> list_rd;




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

      recoreHelper = new RecoreHelper(this);
      database = recoreHelper.getWritableDatabase();
      list_rd = new ArrayList<>();

        //PageSize为10
        //每次刷新获取十条记录， 避免数据过多查询时间过长
        String sql = "select * from tb_record order by record_id asc" ;

        //遍历查询到的记录，将记录封装到RouteRecord集合中
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            RecordBean record =  new RecordBean();
            record.setStr_content(cursor.getString(cursor.getColumnIndex("record_content")));
            record.setMoney(cursor.getInt(cursor.getColumnIndex("record_money")));
            record.setStr_time(cursor.getString(cursor.getColumnIndex("record_time")));

            list_rd.add(record);
        }

        cursor.close();

    }

    private void initView() {
        lv_jz = findViewById(R.id.lv_jz);
        btn_go  =findViewById(R.id.btn_go);
        fb_plus = findViewById(R.id.fb_plus);
        RDListAdapter adapter = new RDListAdapter(list_rd);


        if (list_rd.size() !=0){
            tv_empty.setVisibility(View.GONE);
            lv_jz.setAdapter(adapter);
            lv_jz.setVisibility(View.VISIBLE);
        }

     }



    private void initLsitener(){



        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    class RDListAdapter extends BaseAdapter{

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
