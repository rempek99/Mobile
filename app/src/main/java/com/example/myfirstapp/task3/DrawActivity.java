package com.example.myfirstapp.task3;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        drawView = (DrawView) findViewById(R.id.draw_view);
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
        final SeekBar barRed = (SeekBar) findViewById(R.id.seekBarB);
        final TextView barRedTile = (TextView) findViewById(R.id.seekBarBTitle);
        barRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String tmp = getResources().getString(R.string.red);
                barRedTile.setText(tmp + barRed.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}