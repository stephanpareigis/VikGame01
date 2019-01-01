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
    private Rect src,dst;

    public SpriteDiver(GamePanel gamePanel, GameControl gameControl, LandscapeBackground landscapeBackground, Bitmap bmp) {
        this.gamePanel = gamePanel;
        this.gameControl = gameControl;
        this.landscapeBackground = landscapeBackground;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        this.dst = new Rect();
        this.src = new Rect();
    }

    public void update() {
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
        float srcX = currentFrame * width;
 //       int srcY = getAnimationRow() * height;
        int srcY = 0;
        src.set((int)srcX, srcY, (int)(srcX + width), (int)(srcY + height));
        // calculate destination rectangle
        int left, top, right, bottom;
        left = visible2ScreenX(gameControl.x );
        top = visible2ScreenY(gameControl.y );
        right = visible2ScreenX(gameControl.x + (width/height)*gameControl.diverSize );
        bottom = visible2ScreenY(gameControl.y + gameControl.diverSize );
        dst.set(left, top, right, bottom);
        canvas.drawBitmap(bmp, src, dst, null);
   }

    // input shall be gameControl.x . Returns x position in screen
    private int visible2ScreenX(float x){
        float tmp, m, b;
        m = gamePanel.getWidth() / gameControl.visibleWidth;
        b = gamePanel.getWidth() / 2;
        tmp = m*(x-gameControl.visibleX) + b;
        return (int)tmp;
    }
    // input shall be y . Returns y position in screen.
    private int visible2ScreenY(float y){
        float tmp, m;
        m = gamePanel.getHeight() / gameControl.visibleDepth;
        tmp = m*(y-gameControl.visibleY);
        return (int)tmp;
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