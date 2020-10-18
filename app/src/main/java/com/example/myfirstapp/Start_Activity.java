package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.myfirstapp.zadanie1.Zadanie1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class Start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn_zadanie1 = (Button) findViewById(R.id.btn_zadanie1);
        btn_zadanie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_task1 = new Intent(getApplicationContext(), Zadanie1.class);
                startActivity(go_to_task1);
            }
        });

    }
}