package com.steph.vikgame01;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteDiver {
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private int x = 0;
    private int y = 0;
    private int xSpeed = 5;
    private int ySpeed = 2;
    private int xacc = 0;
    private int yacc = 0;
    private GamePanel gamePanel;
    private GameControl gameControl;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;

    public SpriteDiver(GamePanel gamePanel, GameControl gameControl, Bitmap bmp) {
        this.gamePanel = gamePanel;
        this.gameControl = gameControl;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    public void update() {
        xSpeed += xacc;
        ySpeed += yacc;
        if (x >= gamePanel.getWidth() - width - xSpeed || x + xSpeed <= 0) {
            xSpeed = -xSpeed;
            xacc = -xacc;
        }
        x = x + xSpeed;
        if (y >= gamePanel.getHeight() - height - ySpeed || y + ySpeed <= 0) {
            ySpeed = -ySpeed;
            yacc = -yacc;
        }
        y = y + ySpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;
        //Reibung
        if ( xacc > 0 ) xacc -= 1;
        if ( xacc < 0 ) xacc += 1;
        if ( yacc > 0 ) yacc -= 1;
        if ( yacc < 0 ) yacc += 1;
    }

    public void onDraw(Canvas canvas) {
 //       int srcX = currentFrame * width;
 //       int srcY = getAnimationRow() * height;
 //       Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
 //       Rect dst = new Rect(x, y, x + width, y + height);
      //  canvas.drawBitmap(bmp, src, dst, null);
        int drawy = gameControl.y;
        if ( gameControl.y >= gameControl.ylimit) drawy = gameControl.ylimit;
        canvas.drawBitmap(bmp,gameControl.x,drawy,null);
       //       canvas.drawBitmap(bmp,300,500,null);
    }

    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    private int getAnimationRow() {
        double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        return DIRECTION_TO_ANIMATION_MAP[direction];
    }

}