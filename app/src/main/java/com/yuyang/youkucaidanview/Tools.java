package com.yuyang.youkucaidanview;

import android.animation.ObjectAnimator;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: yuyang
 * Date:2019/4/12 22:27
 */
public class Tools {


    static  void hideView(ViewGroup viewGroup){

        hideView(viewGroup ,0);

    }

    static void  hideView(ViewGroup viewGroup, int startOffset){


        ObjectAnimator animator = ObjectAnimator.ofFloat(viewGroup,"rotation",0,180);
        animator.setDuration(500);
        animator.setStartDelay(startOffset);
        animator.start();

        viewGroup.setPivotX(viewGroup.getWidth()/2);  //x中点
        viewGroup.setPivotY(viewGroup.getHeight());  //
        System.out.print(viewGroup.getHeight() + "   GroupTest" + viewGroup.getWidth());

        Log.e("Test", "Height=" + viewGroup.getHeight() + "   Width=" + viewGroup.getWidth());

    }

    static void showView(ViewGroup viewGroup){
        showView(viewGroup, 0);
    }


    static  void showView(ViewGroup viewGroup, int startOffset){

        ObjectAnimator animator = ObjectAnimator.ofFloat(viewGroup,"rotation",180,360);
        animator.setDuration(500);
        animator.setStartDelay(startOffset);
        animator.start();

        viewGroup.setPivotX(viewGroup.getWidth() / 2);
        viewGroup.setPivotY(viewGroup.getHeight());

    }
}
