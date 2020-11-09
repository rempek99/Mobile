package com.example.myfirstapp.task3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.myfirstapp.R;

import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class DrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        final DrawView drawView = (DrawView) findViewById(R.id.draw_view);
        Button normalModeButton = (Button) findViewById(R.id.normalModeButton);
        normalModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setOption('1');
            }
        });
        Button blurModeButton = (Button) findViewById(R.id.blurModeButton);
        blurModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setOption('2');
            }
        });
        Button embossModeButton = (Button) findViewById(R.id.embossModeButton);
        embossModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setOption('3');
            }
        });
        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.clearScreen();
            }
        });
    }
}