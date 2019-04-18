package com.example.gesturedetectordemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GestorAvtivity extends AppCompatActivity {

    public static void startUp(Context context) {
        context.startActivity(new Intent(context, GestorAvtivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestor_avtivity);
    }
}
