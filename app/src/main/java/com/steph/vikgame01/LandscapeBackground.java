package com.steph.vikgame01;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class LandscapeBackground {
    private Bitmap backgroundBitmap;
    private GamePanel gamePanel;
    private GameControl gameControl;


    public LandscapeBackground(GamePanel  gamePanel, GameControl gameControl, Bitmap bmp) {
        this.gamePanel = gamePanel;
        this.gameControl = gameControl;
        backgroundBitmap = bmp;
    }

    public void onDraw(Canvas canvas) {
        float drawy = 0;
        if ( gameControl.y >= gameControl.depthLimit ){
            drawy = pool2ImageY(gameControl.y - gameControl.depthLimit);
        }
        canvas.drawBitmap(backgroundBitmap,
                new Rect(pool2ImageX(gameControl.x - gameControl.visibleWidth/2) ,
                        (int)drawy,
                         pool2ImageX(gameControl.x + gameControl.visibleWidth/2),
                        (int)drawy + pool2ImageY(gameControl.visibleDepth)),
                new Rect(0, 0, gamePanel.getWidth(), gamePanel.getHeight()),
                null);
    }

    public int pool2ImageX(float x){
        float temp;
        temp = (x*backgroundBitmap.getWidth()) / gameControl.poolWidth + backgroundBitmap.getWidth()/2;
        return (int)temp;
    }
    public int pool2ImageY(float y){
        float temp;
        temp = (y*backgroundBitmap.getHeight())/gameControl.poolDepth;
        return (int)temp;
    }

    public void update(){
    }


}
