package com.steph.vikgame01;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteDiver {
    private static final int BMP_ROWS = 1;
    private static final int BMP_COLUMNS = 4;
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private GamePanel gamePanel;
    private LandscapeBackground landscapeBackground;
    private GameControl gameControl;
    private Bitmap bmp;
    private int currentFrame = 0;
    private float width;
    private float height;

    public SpriteDiver(GamePanel gamePanel, GameControl gameControl, LandscapeBackground landscapeBackground, Bitmap bmp) {
        this.gamePanel = gamePanel;
        this.gameControl = gameControl;
        this.landscapeBackground = landscapeBackground;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    public void update() {
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
        float srcX = currentFrame * width;
 //       int srcY = getAnimationRow() * height;
        int srcY = 0;
        float drawy = gameControl.y;
        if ( gameControl.y >= gameControl.depthLimit) drawy = gameControl.depthLimit;
        float destX = landscapeBackground.pool2ImageX(gameControl.x);
        float destY = landscapeBackground.pool2ImageY(drawy);
        Rect src = new Rect((int)srcX, srcY, (int)(srcX + width), (int)(srcY + height));
        Rect dst = new Rect((int)destX,(int)destY,(int)(destX + width/height*gameControl.diverSize),(int)(destY+gameControl.diverSize));
        canvas.drawBitmap(bmp, src, dst, null);

 //       int drawy = gameControl.y;
 //       if ( gameControl.y >= gameControl.depthLimit) drawy = gameControl.depthLimit;
 //       canvas.drawBitmap(bmp,gameControl.x,drawy,null);
       //       canvas.drawBitmap(bmp,300,500,null);
    }

    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    private int getAnimationRow() {
   /*     double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        return DIRECTION_TO_ANIMATION_MAP[direction];*/
   return 1;
    }


}