package com.example.myfirstapp.task4;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfirstapp.R;

import java.util.Random;

public class Dice extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private static int SHAKE_THRESHOLD = 3;
    private TextView mNumber,mNumber2,mNumber3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task4_activity_dice);
        mNumber = findViewById(R.id.dice_number);
        mNumber2 = findViewById(R.id.dice_number2);
        mNumber3 = findViewById(R.id.dice_number3);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        diceMenuSetup();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_UI);
    }

    private void generateRandomNumber(){
        Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(6)+1;
        mNumber.setText(Integer.toString(randomNum));
        randomNum = randomGenerator.nextInt(6)+1;
        mNumber2.setText(Integer.toString(randomNum));
        randomNum = randomGenerator.nextInt(6)+1;
        mNumber3.setText(Integer.toString(randomNum));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float acceleration = (float) Math.sqrt(x*x + y*y + z*z) - SensorManager.GRAVITY_EARTH;

        if(acceleration > SHAKE_THRESHOLD)
            generateRandomNumber();
    }
    private void diceMenuSetup(){
        ImageButton diceMenuButton = findViewById(R.id.diceMenuButton);
        diceMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout diceMenu = findViewById(R.id.diceMenu);
                if(diceMenu.getVisibility() == View.GONE)
                    diceMenu.setVisibility(View.VISIBLE);
                else
                    diceMenu.setVisibility(View.GONE);
            }
        });
        DiceOptionListener doListener = new DiceOptionListener();
        Button diceOption1 = findViewById(R.id.diceOption1);
        diceOption1.setOnClickListener(doListener);
        Button diceOption2 = findViewById(R.id.diceOption2);
        diceOption2.setOnClickListener(doListener);
        Button diceOption3 = findViewById(R.id.diceOption3);
        diceOption3.setOnClickListener(doListener);
    }
    private class DiceOptionListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.diceOption1:
                    findViewById(R.id.dice_number2).setVisibility(View.GONE);
                    findViewById(R.id.dice_number3).setVisibility(View.GONE);
                    break;
                case R.id.diceOption2:
                    findViewById(R.id.dice_number2).setVisibility(View.VISIBLE);
                    findViewById(R.id.dice_number3).setVisibility(View.GONE);
                    break;
                case R.id.diceOption3:
                    findViewById(R.id.dice_number2).setVisibility(View.VISIBLE);
                    findViewById(R.id.dice_number3).setVisibility(View.VISIBLE);
                    break;
            }
            findViewById(R.id.diceMenu).setVisibility(View.GONE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}