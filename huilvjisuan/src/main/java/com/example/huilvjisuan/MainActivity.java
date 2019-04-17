package com.example.huilvjisuan;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20,btn_now, btn_go;
    private EditText editText;//显示输入的数字
    private String opt = "+";//操作符
    private double n1 = 0.0, n2 = 0.0;//两个操作数
    private TextView textView;//显示算式
    private String s;

    private double count = 0.0;

    private ArrayList<Double>  list_operations;   //操作数List
    private ArrayList<String>  list_operators;  //操作符List
    private ArrayList<String>   list_tv;        //TextView显示

//    StringBuffer str_now;   //



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //获取按钮的id
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6= (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btn10 = (Button) findViewById(R.id.button10);
        btn11 = (Button) findViewById(R.id.button11);
        btn12 = (Button) findViewById(R.id.button12);
        btn13 = (Button) findViewById(R.id.button13);
        btn14 = (Button) findViewById(R.id.button14);
        btn15 = (Button) findViewById(R.id.button15);
        btn16 = (Button) findViewById(R.id.button16);
        btn17 = (Button) findViewById(R.id.button17);
        btn18 = (Button) findViewById(R.id.button18);
        btn19 = (Button) findViewById(R.id.button19);
        btn20 = (Button) findViewById(R.id.button20);

        btn_now = (Button) findViewById(R.id.btn_now);
        btn_go =(Button) findViewById(R.id.btn_go);

        editText = (EditText)findViewById(R.id.et_output);
        textView = (TextView) findViewById(R.id.tv_input);
        s = editText.getText().toString();//获取字符串

        //为按钮添加监听器
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);
        btn18.setOnClickListener(this);
        btn19.setOnClickListener(this);
        btn20.setOnClickListener(this);
        btn_go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        try {
            switch (btn.getId()) {
                case R.id.button1://1
                {
//                    String str = editText.getText().toString();
//                    editText.setText(str + 1);
//                    str = editText.getText().toString();
//                    textView.setText(str);
                    String str = editText.getText().toString();
                    list_tv.add("1");
                    StringBuffer str_now = null;
                    for(int i=0; i<list_tv.size(); i++){
                            str_now.append(list_tv.get(i));
               }

               textView.setText(str_now);
                     break;
                }
                case R.id.button2://+
                {
//                    String str = editText.getText().toString();
//                    if(count != 0.0){
//                        n1 = count;
//                    }else{
//                        n1 = Double.parseDouble(str);
//                    }
//
//                    opt = "+";
//                    textView.setText(n1 + opt);
//                    editText.setText("");
                    list_tv.add("+");
                    StringBuffer str_now = null;
                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                            break;
                }
                case R.id.button3://2
                {
//                    String str = editText.getText().toString();
//                    editText.setText(str + 2);
//                    str = editText.getText().toString();
//                    textView.setText(str);
                    list_tv.add("2");
                    StringBuffer str_now = null;
                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                    break;
                }
                case R.id.button4://操作符=
                {
                    if (opt == "+") {
                        String str = editText.getText().toString();
                        n2 = Double.parseDouble(str);
                        double res;
                        if(count != 0.0){
                            res = count + n2;
                        }else{
                            res = n1 + n2;
                        }
                        count = res;
                        DecimalFormat df = new DecimalFormat("0.0");
                        String str_res = df.format(res).replace(".", ",");
                        String str_n1 = df.format(n1).replace(".", ",");
                        String str_n2 = df.format(n2).replace(".", ",");

                        textView.setText(str_n1 + opt + str_n2 + "=");
                        editText.setText(str_res);
                        editText.setSelection(str_res.length());
                    } else if (opt == "-") {

                        String str = editText.getText().toString();

                        n2 = Double.parseDouble(str);
                        double res;
                        if (count != 0.0){
                            res = count - n2;
                        }else{
                            res = n1 - n2;
                        }
                        count = res;
                        DecimalFormat df = new DecimalFormat("0.0");
                        String str_res = df.format(res).replace(".", ",");
                        String str_n1 = df.format(n1).replace(".", ",");
                        String str_n2 = df.format(n2).replace(".", ",");

                        textView.setText(str_n1 + opt + str_n2 + "=");
                        editText.setText(str_res);

                    } else if (opt == "*") {
                        String str = editText.getText().toString();

                        n2 = Double.parseDouble(str);
                        double res;

                        n2 = Double.parseDouble(str);

                        if (count != 0.0){
                            res = count * n2;
                        }else{
                            res = n1 * n2;
                        }
                        count = res;
                        DecimalFormat df = new DecimalFormat("0.0");
                        String str_res = df.format(res).replace(".", ",");
                        String str_n1 = df.format(n1).replace(".", ",");
                        String str_n2 = df.format(n2).replace(".", ",");

                        textView.setText(str_n1 + opt + str_n2 + "=");
                        editText.setText(str_res);

                    } else if (opt == "/") {
                        String str1 = editText.getText().toString();
                        n2 = Double.parseDouble(str1);

                        if (n2 == 0) {
                            editText.setText("");
                            textView.setText("除数不能为0");
                            break;
                        } else {
                            String str = editText.getText().toString();

                            n2 = Double.parseDouble(str);
                            double res;
                            if (count != 0.0){
                                res = count / n2;
                            }else{
                                res = n1 / n2;
                            }
                            count = res;
                            DecimalFormat df = new DecimalFormat("0.0");
                            String str_res = df.format(res).replace(".", ",");
                            String str_n1 = df.format(n1).replace(".", ",");
                            String str_n2 = df.format(n2).replace(".", ",");

                            textView.setText(str_n1 + opt + str_n2 + "=");
                            editText.setText(str_res);
                        }
                    }

                    break;
                }
                case R.id.button5://3
                {
                    list_tv.add("3");
                    StringBuffer str_now = null;

                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                    break;
                }
                case R.id.button6://4
                {
//                    editText.setText(editText.getText().toString() + 4);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
                    list_tv.add("4");
                    StringBuffer str_now = null;

                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                    break;
                }
                case R.id.button7://5
                {
//                    editText.setText(editText.getText().toString() + 5);
//                    String str = editText.getText().toString();
//                    textView.setText(str);

                    list_tv.add("5");
                    StringBuffer str_now = null;

                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                    break;
                }
                case R.id.button8://6
                {
//                    editText.setText(editText.getText().toString() + 6);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
                    list_tv.add("6");
                    StringBuffer str_now = null;

                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                    break;
                }
                case R.id.button9://7
                {
//                    editText.setText(editText.getText().toString() + 7);
//                    String str = editText.getText().toString();
//                    textView.setText(str);

                    list_tv.add("7");
                    StringBuffer str_now = null;

                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                    break;
                }
                case R.id.button10://8
                {
//                    editText.setText(editText.getText().toString() + 8);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
                    list_tv.add("8");
                    StringBuffer str_now = null;

                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                    break;
                }
                case R.id.button11://9
                {
//                    editText.setText(editText.getText().toString() + 9);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
                    list_tv.add("9");
                    StringBuffer str_now = null;

                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                    break;
                }
                case R.id.button12://0
                {
//                    textView.setText(n1 + opt + 10);
//                    editText.setText(editText.getText().toString() + 0);
//                    String str = editText.getText().toString();
//                    textView.setText(str);
                    list_tv.add("0");
                    StringBuffer str_now = null;

                    for(int i=0; i<list_tv.size(); i++){
                        str_now.append(list_tv.get(i));
                    }

                    textView.setText(str_now);
                    break;
                }
                case R.id.button13://.
                {
                    String str_et = editText.getText().toString();
                    //如果此次输入已经有小数点
                   // String str_last = list_tv.get(list_tv.size()-1);
                    //如果此次输入已经有小数点 | 为空
                    if (str_et.contains(".") || str_et == ""){



                    }else{




                    }
//                    String str = editText.getText().toString();
//                    if (str.indexOf(".") != -1) //判断字符串中是否已经包含了小数点，如果有就什么也不做
//                    {
//
//                    } else //如果没有小数点
//                    {
//                        if (str.equals("0"))//如果开始为0，
//                            editText.setText(("0" + ".").toString());
//                        else if (str.equals(""))//如果初时显示为空，就什么也不做
//                        {
//
//                        } else
//                            editText.setText(str + ".");
//                    }
                    break;
                }
                case R.id.button14://操作符 /
                {
                    String str = editText.getText().toString();
                    if (count != 0.0){
                        n1 = count;
                    }else {
                        n1 = Double.parseDouble(str);
                    }
                    opt = "/";
                    editText.setText("");
                    textView.setText(n1 + opt);
                    break;
                }
                case R.id.button15://操作符*
                {
                    String str = editText.getText().toString();
                    if (count != 0.0){
                        n1 = count;

                    }else {
                        n1 = Double.parseDouble(str);
                    }

                    opt = "*";
                    editText.setText("");
                    textView.setText(n1 + opt);
                    break;
                }
                case R.id.button16://操作符-
                {

                    String str = editText.getText().toString();
                    if (count != 0.0){
                        n1 = count;
                    }else {
                        n1 = Double.parseDouble(str);
                    }
                    opt = "-";
                    editText.setText("");
                    textView.setText(n1 + opt);
                    break;
                }
                case R.id.button17://genhao
                {
                    String str = editText.getText().toString();
                    n1 = Double.parseDouble(str);
                    if (n1 < 0) {
                        editText.setText("");
                        textView.setText("负数没有平方根");
                    } else {
                        editText.setText(Math.sqrt(n1) + "");
                        textView.setText(n1 + "的平方根是");
                    }
                    break;
                }
                case R.id.button18://+/-
                {
                    String str = editText.getText().toString();
                    n1 = Double.parseDouble(str);
                    if (str.length() > 0)
                        editText.setText(-n1 + "");
                    textView.setText(-n1 + "");
                    break;
                }
                case R.id.button19://CE
                {
                    String str = editText.getText().toString();
                    if (str.length() > 0)
                        editText.setText("");
                    textView.setText("");
                    n1 = 0.0;
                    n2 = 0.0;
                    count = 0.0;
                    break;
                }
                case R.id.button20://<-
                {
                    String str = editText.getText().toString();
                    if (str.length() > 0)
                        editText.setText(str.substring(0, str.length() - 1));
                    break;
                }

//                case R.id.btn_go: {
//                    if(TextUtils.isEmpty(SharePreUtil.getString(SecondActivity.this, Constant.MOBILE, ""))){
//                        startActivity(new Intent(SecondActivity.this, LoginActivity.class));
//                    }else{
//                        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
//                        startActivity(intent);
//
//                    }
//
//
//
//                }

            }
        } catch (Exception e) {
        }
    }
}
