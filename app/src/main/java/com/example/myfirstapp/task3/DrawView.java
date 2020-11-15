package com.example.myfirstapp.task3;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View implements View.OnTouchListener{

    class PointExtended extends Point
    {
        char option;
        int pointBrushSize;
        int pointColor;
        PointExtended(char option, int brushSize, int pointColor)
        {
            super();
            this.pointBrushSize = brushSize;
            this.option = option;
            this.pointColor = pointColor;
        }

        public int getPointColor() {
            return pointColor;
        }

        public char getOption() {
            return option;
        }

        public int getPointBrushSize() {
            return pointBrushSize;
        }

        public void setOption(char option) {
            this.option = option;
        }
    }

    private Paint paint = new Paint();
    private List<PointExtended> points = new ArrayList<>();
    private int brushSize;
    private int brushColor;
    private char option;
    private Bitmap image;
    private Shader shader;

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }

    public void setBrushColor(int brushColor) {
        this.brushColor = brushColor;
    }

    public DrawView(Context context, AttributeSet set) {
        super(context,set);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
//        System.out.println(isFocusable());
//        System.out.println(isFocusableInTouchMode());
        paint.setColor(Color.RED);
        brushSize = 40;
        option = '1';
        brushColor = Color.RED;
        this.setBackgroundColor(Color.WHITE);
        this.invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        PointExtended point = new PointExtended(option, brushSize,brushColor);
        point.x = (int) motionEvent.getX();
        point.y = (int) motionEvent.getY();
        points.add(point);
        if(point.getOption()=='3')
        {
            PointExtended point2 = new PointExtended('4',brushSize,brushColor);
            point2.x = (int) motionEvent.getX();
            point2.y = (int) motionEvent.getY();
            points.add(point2);
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(PointExtended point:points)
        {
            printSetup(point.getOption(), point);
            canvas.drawCircle(point.x,point.y,point.getPointBrushSize(),paint);
        }
    }

    public void setOption(char option) {
        this.option = option;
    }
    public void clearScreen()
    {
        points.clear();
        this.invalidate();
    }

    public void printSetup(char option, PointExtended point)
    {
        switch(option)
        {
            case '1':
                image = Bitmap.createBitmap(point.getPointBrushSize(), point.getPointBrushSize(), Bitmap.Config.ARGB_8888);
                image.eraseColor(point.getPointColor());
                shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                paint.setStrokeWidth(0);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setShader(shader);
                break;
            case '2':
                paint.setStrokeWidth(5);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                @SuppressLint("DrawAllocation")
                RadialGradient blur = new RadialGradient(point.x, point.y,point.getPointBrushSize(), point.getPointColor(), Color.TRANSPARENT, Shader.TileMode.CLAMP);
                paint.setShader(blur);
                break;
            case '3':
                paint.setStrokeWidth(0);
                image = Bitmap.createBitmap(point.getPointBrushSize(), point.getPointBrushSize(), Bitmap.Config.ARGB_8888);
                image.eraseColor(point.getPointColor());
                paint.setStyle(Paint.Style.FILL);
                shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                paint.setShader(shader);
                break;
            case '4':
                paint.setStrokeWidth(5);
                paint.setStyle(Paint.Style.STROKE);
                shader =new LinearGradient(point.x-point.getPointBrushSize()/2, point.y-point.getPointBrushSize()/2, point.x, point.y, Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP );
                paint.setShader(shader);
                break;
        }


    }
}
