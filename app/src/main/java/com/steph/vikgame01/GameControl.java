package com.steph.vikgame01;

public class GameControl {
    // Position of Diver
    public float x;
    public float y;
    // Velocity of diver
    public float vx;
    public float vy;
    // acceleration of diver
    public float ax;
    public float ay;
    // visible part of pool
    public float visibleX; // this is the upper middle of
    public float visibleY; // the visible part of the pool in pool-coordinates
    public final float visibleDepth; // depicted depth
    public final float visibleWidth; // depicted width
    public final float visibleDepthLimit; // should be smaller than visibleDepth
    public final float visibleWidthLimit; // when to scroll when diver is at the side
    // pool size
    public final float poolDepth; // size of pool
    public final float poolWidth; // size of pool
    public final float diverMaxDepth; // at this depth the diver returns upwards
    public final float diverSize; // size of the diver.
    public String depthText; // depth to be displayed
    private final GamePanel gamePanel;


    GameControl(GamePanel gamePanel){
        // state of diver
        x = 0; // 0 is the middle
        y = 0; // depth
        vx = 0;
        vy = (float)0.3;
        ax = 0;
        ay = 0;


        visibleDepth = 15; // size of visible rectangle. should be less than size of pool.
        visibleWidth = 7;
        visibleX = x; // position of visible rectangle in
        visibleY = y; //  pool coordinates
        visibleDepthLimit = 8; // depth of visible rectangle where background starts to move. should be less than visibleDepth
        visibleWidthLimit = 2; // width where visible rectangle starts to move. should be less than half of visibleWidth
        // size of pool
        poolDepth = 30;
        poolWidth = 14;
        diverMaxDepth = 28; // here the diver goes back up
        diverSize = 2;
        depthText = new String();
        this.gamePanel = gamePanel;
    }

    public void movement(float x, float y){
        ax = (x / 1000);
/*        if (x>0) {
            vx = (float)0.3;
        }
        else {
            vx = (float)-0.3;
        }
        */
    }

    public void update(){
        if ((x >= poolWidth/2 - diverSize  ) && (ax>0 || vx>0)) {
            vx = 0;
            ax = 0;
        }
        if ( (x<= -poolWidth/2 + diverSize ) && (ax<0 || vx<0)) {
            vx = 0;
            ax = 0;
        }
        vx += ax;
        x = x + vx;
        vx=0;
        if (y >= diverMaxDepth)
            vy = -vy;
        if (y + vy <= 0){
            vy = 0;
            vx = 0;
            y = 0;
        }

        y = y + vy;
        // text (depth) to be displayed
        depthText = "" + (int)y + " meter";
        if(y<=0.0) depthText = "GAME OVER";
        // visible rectangle
        if ( x > visibleX + visibleWidth/2 - visibleWidthLimit) // move if x is outside right border
            visibleX = x + visibleWidthLimit - visibleWidth/2;
        if ( x < visibleX - visibleWidth/2 + visibleWidthLimit - diverSize/2) // move visible if x is outside left border
            visibleX = x - visibleWidthLimit + visibleWidth/2 + diverSize/2;
       if ( visibleX + visibleWidth/2 > poolWidth/2 ) // don´t extend visible over pool to right
            visibleX = poolWidth/2 - visibleWidth/2;
        if ( visibleX - visibleWidth/2 < - poolWidth/2 ) // don´t extend visible over pool to left
            visibleX = -poolWidth/2 + visibleWidth/2;

        if ( y > visibleY + visibleDepthLimit) // move if y is outside bottom
            visibleY = y - visibleDepthLimit;
        if ( y < visibleY + visibleDepth -  visibleDepthLimit ) // move if y is over top border
            visibleY = y - visibleDepth + visibleDepthLimit;
        if ( visibleY < 0 ) // don´t extend visible over top of pool
            visibleY = 0;
        if ( visibleY + visibleDepth >= poolDepth) // don´t extend visible over bottom of pool
            visibleY = poolDepth - visibleDepth;
    }
}
