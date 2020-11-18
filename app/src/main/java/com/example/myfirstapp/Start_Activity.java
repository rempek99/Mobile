package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class Start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button btn_task1 = (Button) findViewById(R.id.btn_task1);
        btn_task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_task1 = new Intent(getApplicationContext(), com.example.myfirstapp.task1.MainActivity.class);
                startActivity(go_to_task1);
            }
        });
        Button btn_task2 = (Button) findViewById(R.id.btn_task2);
        btn_task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_task2 = new Intent(getApplicationContext(), com.example.myfirstapp.task2.MainActivity.class);
                startActivity(go_to_task2);
            }
        });
        Button btn_task3 = (Button) findViewById(R.id.btn_task3);
        btn_task3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_task3 = new Intent(getApplicationContext(), com.example.myfirstapp.task3.DrawActivity.class);
                startActivity(go_to_task3);
            }
        });
        Button btn_task4 = (Button) findViewById(R.id.btn_task4);
        btn_task4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_task4 = new Intent(getApplicationContext(), com.example.myfirstapp.task4.Dice.class);
                startActivity(go_to_task4);
            }
        });
        Button btn_task5 = (Button) findViewById(R.id.btn_task5);
        btn_task5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_task5 = new Intent(getApplicationContext(), com.example.myfirstapp.task5.MyYTPlayer.class);
                startActivity(go_to_task5);
            }
        });
        if(isNetworkConnected())
            System.out.println("INTERNET DZIALA!!!!!!");


    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}