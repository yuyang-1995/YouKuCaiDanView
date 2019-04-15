package com.yuyang.guanggaoview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Author: yuyang
 * Date:2019/4/12 23:13
 */
public class MyPagerAdapter extends PagerAdapter {

     ArrayList<ImageView> arrayList_iamge;
     String[] strings;
     Context context;

     public   MyPagerAdapter(ArrayList arrayList, String[] strings, Context context){

         this.arrayList_iamge = arrayList;
         this.strings = strings;
         this.context = context;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
         int realPosition = position%arrayList_iamge.size();

        final ImageView imageView =  arrayList_iamge.get(realPosition);
        container.addView(imageView);//添加到ViewPager中

        Log.e("MyPagerAdapter", "instantiateItem==" + realPosition + ",---imageView==" + imageView);


        imageView.setTag(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MyAdapter","点击事件");
                int position = (int) v.getTag()%arrayList_iamge.size();
                String text = strings[position];
                Toast.makeText(context, "text=="+text, Toast.LENGTH_SHORT).show();

            }
        });

        return imageView;
        //return super.instantiateItem(container, position);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

         container.removeView((View) object);
    }
}
