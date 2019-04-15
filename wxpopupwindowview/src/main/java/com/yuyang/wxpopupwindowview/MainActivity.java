package com.yuyang.wxpopupwindowview;

import android.animation.Animator;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView iv_add;
    TextView tv_1, tv_2, tv_3, tv_4, tv_5;
    PopupWindow mPopupWindow;

    AnimUtil animUtil; //动画工具类
    float bgAlpha = 1f;
    boolean bright = false;

    static final long DURATION = 500;
     static final float START_ALPHA = 0.7f;
     static final float END_ALPHA = 1f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        animUtil = new AnimUtil();
        initView();
        iv_add.setOnClickListener(this);

    }

    private void initView() {
        iv_add = findViewById(R.id.iv_add);

        mPopupWindow = new PopupWindow(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add:
                showPop();
                break;
            case R.id.tv_1:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_1.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_2:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_2.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_3:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_3.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_4:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_4.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_5:
                mPopupWindow.dismiss();
                Toast.makeText(this, tv_5.getText(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }

    }

    //打开popWindow
    private void showPop() {

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.6f;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

        mPopupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.pop_add,null));


        //机型适配
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        mPopupWindow.setAnimationStyle(R.style.pop_add); //设置popView动画

        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        mPopupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        mPopupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        mPopupWindow.setOutsideTouchable(true);
        // 相对于 + 号正下面，同时可以设置偏移量

        mPopupWindow.showAsDropDown(iv_add, -100, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);

            }
        });

        tv_1 = mPopupWindow.getContentView().findViewById(R.id.tv_1);
        tv_2 = mPopupWindow.getContentView().findViewById(R.id.tv_2);
        tv_3 = mPopupWindow.getContentView().findViewById(R.id.tv_3);
        tv_4 = mPopupWindow.getContentView().findViewById(R.id.tv_4);
        tv_5 = mPopupWindow.getContentView().findViewById(R.id.tv_5);

        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        tv_5.setOnClickListener(this);

    }

    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(new AnimUtil.UpdateListener() {
            @Override
            public void progerss(float progress) {
// 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);

            }
        });

        animUtil.addEndListner(new AnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animUtil.startAnimator();
    }

    private void backgroundAlpha(float bgAlpha) {

        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.alpha = bgAlpha;

        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR);

    }

}
