package com.example.jizhangdemo2.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: yuyang
 * Date:2019/4/18 22:34
 */
public class RecordBean implements Parcelable {

    private String str_content; //记帐的内容
    private  String str_time;  //记账的时间
    private  String str_money;      //金额
    private String str_kind;   //记账种类


    public String getStr_kind() {
        return str_kind;
    }

    public void setStr_kind(String str_kind) {
        this.str_kind = str_kind;
    }

    public String getStr_money() {
        return str_money;
    }

    public void setStr_money(String str_money) {
        this.str_money = str_money;
    }

    public String getStr_content() {
        return str_content;
    }

    public void setStr_content(String str_content) {
        this.str_content = str_content;
    }

    public String getStr_time() {
        return str_time;
    }

    public void setStr_time(String str_time) {
        this.str_time = str_time;
    }


    public static final Creator<RecordBean> CREATOR = new Creator<RecordBean>() {

        //实例化静态内部对象CREATOR实现接口Parcelable.Creator
        @Override
        public RecordBean createFromParcel(Parcel in) {
            RecordBean recordBean = new RecordBean();

            recordBean.str_content = in.readString();
            recordBean.str_time = in.readString();
            recordBean.str_money = in.readString();

            return recordBean;
        }

        @Override
        public RecordBean[] newArray(int size) {
            return new RecordBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //，将你的对象序列化为一个Parcel对象，即：将类的数据写入外部提供的Parcel中，
        // 打包需要传递的数据到Parcel容器保存，以便从 Parcel容器获取数据。
        dest.writeString(str_content);
        dest.writeString(str_time);
        dest.writeString(str_money);


    }
}
