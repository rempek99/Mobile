package com.example.myfirstapp.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class DrawActivity extends AppCompatActivity {
    private DrawView drawView;
    private int choosenColor;
    private int choosenThickness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        choosenColor = Color.RED;
        choosenThickness = 20;
        setContentView(R.layout.task3_activity_draw);
        drawView = (DrawView) findViewById(R.id.draw_view);
        buttonsSetup();
        colorPickerSetup();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actionChangeMode) {
            ScrollView modeMenu = (ScrollView) findViewById(R.id.drawModeMenu);
            modeMenu.setVisibility(View.VISIBLE);
            return true;
        }
        if (id == R.id.clearBoard){

                    drawView.clearScreen();
        }
        if (id == R.id.brushSettings){
            ScrollView pickerLayout = (ScrollView) findViewById(R.id.colorThicknessPicker);
            pickerLayout.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    void buttonsSetup()
    {
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
        ImageButton modeMenuClose = (ImageButton) findViewById(R.id.modeMenuCloseButton);
        modeMenuClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScrollView modeMenu = (ScrollView) findViewById(R.id.drawModeMenu);
                modeMenu.setVisibility(View.GONE);
            }
        });
    }
    void colorPickerSetup()
    {
        final SeekBar barRed = (SeekBar) findViewById(R.id.seekBarR);
        final SeekBar barGreen = (SeekBar) findViewById(R.id.seekBarG);
        final SeekBar barBlue = (SeekBar) findViewById(R.id.seekBarB);
        final SeekBar barThickness = (SeekBar) findViewById(R.id.seekBarThickness);
        View colorRewiew = findViewById(R.id.colorRewiev);
        barRed.setOnSeekBarChangeListener(new colorPickerListener((TextView)findViewById(R.id.seekBarRTitle), colorRewiew));
        barGreen.setOnSeekBarChangeListener(new colorPickerListener((TextView)findViewById(R.id.seekBarGTitle), colorRewiew));
        barBlue.setOnSeekBarChangeListener(new colorPickerListener((TextView)findViewById(R.id.seekBarBTitle), colorRewiew));
        barThickness.setOnSeekBarChangeListener(new thicknessPickerListener((TextView) findViewById(R.id.seekBarThicknessTitle),barThickness));
        Button confirmButton = (Button) findViewById(R.id.colorPickerConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setBrushColor(choosenColor);
                drawView.setBrushSize(choosenThickness);
                ScrollView pickerLayout = (ScrollView) findViewById(R.id.colorThicknessPicker);
                pickerLayout.setVisibility(View.GONE);
            }
        });
    }
    class colorPickerListener implements SeekBar.OnSeekBarChangeListener {
        private TextView title;
        private String headline;
        private float red,green,blue;
        private int id;
        private View colorRewiewer;
        colorPickerListener(TextView title, View colorRewiewer){
            super();
            this.title = title;
            this.colorRewiewer = colorRewiewer;
            red = 0; green = 0; blue = 0;
            switch (title.getId()) {
                case R.id.seekBarRTitle:
                    headline = getResources().getString(R.string.red);
                    id = 0;
                    break;
                case R.id.seekBarGTitle:
                    headline = getResources().getString(R.string.green);
                    id = 1;
                    break;
                case R.id.seekBarBTitle:
                    headline = getResources().getString(R.string.blue);
                    id = 2;
                    break;
            }
            title.setText(headline + 0);
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            title.setText(headline + seekBar.getProgress());
            Color color = Color.valueOf(((ColorDrawable)colorRewiewer.getBackground()).getColor());
            red = color.red();
            green = color.green();
            blue = color.blue();
            float value = (float) progress / 255.0f;
            switch(id){
                case 0:
                    red = value;
                    break;
                case 1:
                    green = value;
                    break;
                case 2:
                    blue = value;
                    break;
            }
            choosenColor = Color.rgb(red,green,blue);
            colorRewiewer.setBackgroundColor(choosenColor);
           System.out.println("r: " + red + " g: "+ green + " b: " + blue );
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
    class thicknessPickerListener implements SeekBar.OnSeekBarChangeListener{
        private int thickness;
        private TextView title;
        private String headline;
            thicknessPickerListener(TextView title, SeekBar seekBar){
                this.title = title;
                if(title.getId() == R.id.seekBarThicknessTitle){
                    headline = getResources().getString(R.string.thickness);
                }
                thickness = seekBar.getProgress();
                title.setText(headline + thickness);
            }
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                thickness = progress;
                title.setText(headline + progress);
                choosenThickness = thickness;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}