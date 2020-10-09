package com.example.myfirstapp;

import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button addBtn = (Button) findViewById(R.id.AddBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstNumber = (EditText) findViewById(R.id.Num1EditText);
                EditText secondNumber = (EditText) findViewById(R.id.Num2EditText);
                if(firstNumber.getText().toString().isEmpty() || secondNumber.getText().toString().isEmpty())
                    return;

                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
                float result = Float.parseFloat(firstNumber.getText().toString()) + Float.parseFloat(secondNumber.getText().toString());
                resultTextView.setText(String.valueOf(result));
            }
        });

        Button computeBtn = (Button) findViewById(R.id.computeBtn);
        computeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstFactor = (EditText) findViewById(R.id.factor1EditText);
                EditText secondFactor = (EditText) findViewById(R.id.factor2EditText);
                EditText thirdFactor = (EditText) findViewById(R.id.factor3EditText);
                if(firstFactor.getText().toString().isEmpty() || secondFactor.getText().toString().isEmpty() || thirdFactor.getText().toString().isEmpty())
                    return;
                Resources res = getResources();
                TextView resultTextView = (TextView) findViewById(R.id.equalResultTextView);
                float fct1 = Float.parseFloat(firstFactor.getText().toString());
                float fct2 = Float.parseFloat(secondFactor.getText().toString());
                float fct3 = Float.parseFloat(thirdFactor.getText().toString());
                String result = "";
                if(fct1==0)
                {
                    if(fct2==0)
                    {
                        //funkcja stala
                        result = res.getString(R.string.result1) + " 0 = 0";
                        resultTextView.setText(result);
                        if(fct3!=0) {
                            result = res.getString(R.string.result1) + " " + res.getString(R.string.conflict) + " " + String.valueOf(fct3) + " \u2260 0";
                        }
                    }
                    else {
                        //funkcja linionwa
                        float root = -fct3 / fct2;
                        result = res.getString(R.string.result2) + String.valueOf(root);
                    }
                }
                else {
                    //funkcja kwadratowa
                    double delta, root1, root2;
                    delta = fct2*fct2 - (4 * fct1 * fct3);
                    if(delta<0)
                    {
                        result = res.getString(R.string.result3) + String.valueOf(delta);
                    }
                    if(delta == 0)
                    {
                        root1 = -fct2 / (2 * fct1);
                        result = res.getString(R.string.result4a) + String.valueOf(root1) + res.getString(R.string.result4b) + String.valueOf(delta);
                    }
                    if(delta>0)
                    {
                        root1 = (-fct2 - Math.sqrt(delta) ) / (2 * fct2);
                        root2 = (-fct2 + Math.sqrt(delta) ) / (2 * fct2);
                        result = res.getString(R.string.result5a) + String.valueOf(root1) + res.getString(R.string.result5b) + String.valueOf(root2)
                        + res.getString(R.string.result5c) + String.valueOf(delta);
                    }
                }
                resultTextView.setText(result);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}