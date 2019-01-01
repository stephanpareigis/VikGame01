package com.steph.vikgame01;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class LandscapeBackground {
    private Bitmap backgroundBitmap;
    private GamePanel gamePanel;
    private GameControl gameControl;
    private final RectF dstR;
    private Rect srcR;
    private final int numberOfPoints;
    private float[] pX;
    private float[] pY;


    public LandscapeBackground(GamePanel  gamePanel, GameControl gameControl, Bitmap bmp) {
        this.gamePanel = gamePanel;
        this.gameControl = gameControl;
        this.dstR = new RectF(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
        this.srcR = new Rect();
        backgroundBitmap = bmp;
        numberOfPoints = 1000;
        pX = new float[numberOfPoints];
        pY = new float[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            pX[i] = (float) ((Math.random() * gameControl.poolWidth) - (gameControl.poolWidth / 2));
            pY[i] = (float) ((Math.random() * gameControl.poolDepth) );
        }

    }

    public void onDraw(Canvas canvas) {
        int left, top, right, bottom;
        left = pool2ImageX(gameControl.visibleX - gameControl.visibleWidth/2);
        top = pool2ImageY(gameControl.visibleY);
        right = pool2ImageX(gameControl.visibleX + gameControl.visibleWidth/2);
        bottom = pool2ImageY((gameControl.visibleY + gameControl.visibleDepth));
        srcR.set(left,top,right,bottom);
        canvas.drawBitmap(backgroundBitmap, srcR, dstR,null);
        drawPoints(canvas);

    }

    public int pool2ImageX(float x){
        float temp,m,b;
        m = backgroundBitmap.getWidth() / gameControl.poolWidth;
        b = backgroundBitmap.getWidth()/2;
        temp = m*x + b;
        return (int)temp;
    }
    public int pool2ImageY(float y){
        float temp;
        temp = (y*backgroundBitmap.getHeight())/(gameControl.poolDepth);
        return (int)temp;
    }
    // input shall be gameControl.x in pool coordinates. Returns x position in screen
    private int visible2ScreenX(float x){
        float tmp, m, b;
        m = gamePanel.getWidth() / gameControl.visibleWidth;
        b = gamePanel.getWidth() / 2;
        tmp = m*(x - gameControl.visibleX) + b;
        return (int)tmp;
    }
    // input shall be gameControl.y in pool coordinates. Returns y position in screen.
    private int visible2ScreenY(float y){
        float tmp, m;
        m = gamePanel.getHeight() / gameControl.visibleDepth;
        tmp = m*(y - gameControl.visibleY);
        return (int)tmp;
    }

    private void drawPoints(Canvas canvas){
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        for ( int i = 0; i<numberOfPoints; i++){
            canvas.drawCircle(visible2ScreenX(pX[i]), 2*visible2ScreenY(pY[i]),3,p);
        }
    }

    public void update(){
    }


}
