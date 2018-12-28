package com.steph.vikgame01;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class LandscapeBackground {
    private Bitmap image;
    private int x,y;

    public LandscapeBackground(Bitmap bmp) {
        image = bmp;
        x=0;
        y=0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update(){
       // y--;
        //x--;
    }


}
