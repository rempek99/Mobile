package com.example.myfirstapp.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myfirstapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task2_activity_main);

        Button second_activity_button = findViewById(R.id.second_activity_btn);
        second_activity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(startIntent);
            }
        });

        Button google_button = findViewById(R.id.google_button);
        google_button.setOnClickListener(new GoogleButtonListener(this));

        Button map_button = findViewById(R.id.map_btn);
        map_button.setOnClickListener(new MapButtonListener(this));

        Button phone_button = findViewById(R.id.phone_btn);
        phone_button.setOnClickListener(new PhoneButtonListener(this));

        Button goto_task1_button = findViewById(R.id.goto_task1_btn);
        goto_task1_button.setOnClickListener(new Task1ButtonListener(this));
    }
}

class GoogleButtonListener implements View.OnClickListener {

    Uri webadress = Uri.parse("http://www.google.com");
    AppCompatActivity parent;
    GoogleButtonListener(AppCompatActivity parent)
    {
        this.parent = parent;
    }
    @Override
    public void onClick(View view) {
        Intent gotoGoogle = new Intent(Intent.ACTION_VIEW,webadress);
        if(gotoGoogle.resolveActivity(parent.getPackageManager()) != null) {
            parent.startActivity(gotoGoogle);
        }
    }
}

class MapButtonListener implements View.OnClickListener
{
    AppCompatActivity parent;
    Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
    MapButtonListener(AppCompatActivity parent) {
        this.parent = parent;
    }
    @Override
    public void onClick(View view) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,location);
        if(mapIntent.resolveActivity(parent.getPackageManager()) != null){
            parent.startActivity(mapIntent);
        }
    }
}

class PhoneButtonListener implements View.OnClickListener
{
    AppCompatActivity parent;
    Uri number = Uri.parse("tel:5551234");
    PhoneButtonListener(AppCompatActivity parent) {
        this.parent = parent;
    }
    @Override
    public void onClick(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL,number);
        if(callIntent.resolveActivity(parent.getPackageManager()) != null){
            parent.startActivity(callIntent);
        }
    }
}

class Task1ButtonListener implements View.OnClickListener
{
    AppCompatActivity parent;
    Task1ButtonListener(AppCompatActivity parent) {
        this.parent = parent;
    }
    @Override
    public void onClick(View view) {
        Intent gotoTask1 = new Intent(parent.getApplicationContext(), com.example.myfirstapp.task1.MainActivity.class);
        parent.startActivity(gotoTask1);
    }
}