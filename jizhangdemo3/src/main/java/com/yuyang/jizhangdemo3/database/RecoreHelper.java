package com.yuyang.jizhangdemo3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Author: yuyang
 * Date:2019/4/18 22:06
 */
public class RecoreHelper  extends SQLiteOpenHelper {

    Context mContext;

    public RecoreHelper(Context context) {
        super(context,"record.db", null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       //创建一个SQLite数据库， 记录内容， 时间, 金额
        //表名为cycle_route
        db.execSQL(  "CREATE TABLE IF NOT EXISTS  tb_record (record_id integer primary key autoincrement ," +
                "record_content text not null ," +
                "record_time text not null ,"   +
                "record_kind text not null," +
                "record_money text not null)" );

        Toast.makeText(mContext, "record.db成功",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
