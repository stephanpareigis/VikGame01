package com.steph.vikgame01;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class LandscapeBackground {
    private Bitmap image;
    private int x,y;
    private GamePanel gameView;
    private GameControl gameControl;


    public LandscapeBackground(GamePanel  gameView, GameControl gameControl, Bitmap bmp) {
        this.gameView = gameView;
        this.gameControl = gameControl;
        image = bmp;
        x=0;
        y=0;
    }

    public void onDraw(Canvas canvas) {
        int drawy = 0;
        if ( gameControl.y >= gameControl.ylimit ) drawy = gameControl.ylimit - gameControl.y;
        canvas.drawBitmap(image, null, new RectF(0, drawy, gameView.getWidth(), 3*gameView.getHeight()), null);
    }

    public void update(){
       // y--;
        //x--;
    }


}
