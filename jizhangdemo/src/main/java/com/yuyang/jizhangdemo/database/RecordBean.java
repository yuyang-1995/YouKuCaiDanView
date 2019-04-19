package com.yuyang.jizhangdemo.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: yuyang
 * Date:2019/4/18 22:34
 */
public class RecordBean implements Parcelable {

    private String str_content; //记帐的内容
    private  String str_time;  //记账的时间
    private  int money;      //记账的金额




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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static final Creator<RecordBean> CREATOR = new Creator<RecordBean>() {

        //实例化静态内部对象CREATOR实现接口Parcelable.Creator
        @Override
        public RecordBean createFromParcel(Parcel in) {
            RecordBean recordBean = new RecordBean();

            recordBean.str_content = in.readString();
            recordBean.str_time = in.readString();
            recordBean.money = in.readInt();

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
        dest.writeInt(money);


    }
}
