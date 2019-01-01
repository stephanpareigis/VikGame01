package com.steph.vikgame01;

public class GameControl {
    public float x;
    public float y;
    public float vx;
    public float vy;
    public float ax;
    public float ay;
    public float visibleX; // this is the upper middle of
    public float visibleY; // the visible part of the pool in pool-coordinates
    public float poolDepth; // size of pool
    public float poolWidth; // size of pool
    public float visibleDepth; // depicted depth
    public float visibleWidth; // depicted width
    public  float depthLimit; // should be smaller than visibleDepth
    public float  widthLimit; // when to scroll when diver is at the side
    public float diverMaxDepth; // at this depth the diver returns upwards
    public float diverSize; // size of the diver.
    public String depthText; // depth to be displayed
    private GamePanel gamePanel;


    GameControl(GamePanel gamePanel){
        // state of diver
        x = 0; // 0 is the middle
        y = 0; // depth
        vx = 0;
        vy = (float)0.7;
        ax = 0;
        ay = 0;

        visibleDepth = 40;
        visibleWidth = 17;
        // depth and width where background starts to move
        depthLimit = 70;
        widthLimit = 20;
        // size of pool
        poolDepth = 100;
        poolWidth = 42;
        diverMaxDepth = 90;
        diverSize = 150;
        visibleX = x;
        visibleY = y;
        depthText = new String();
        this.gamePanel = gamePanel;
    }

    public void movement(float x, float y){
       // ax += (x / 500);
        if (x>0) {
            vx = 1;
        }
        else {
            vx = -1;
        }
    }

    public void update(){
        vx += ax;
        if ((x >= poolWidth/2 - visibleWidth/2 - vx && vx > 0) || (x<= -poolWidth/2 + visibleWidth/2 - vx && vx <0)) {
            vx = -vx*4/5;
            ax = -ax/2;
        }
        x = x + vx;
        if (y >= diverMaxDepth  || y + vy <= 0) {
            vy = -vy;
        }
        y = y + vy;
        //Reibung
        ax /= 2;
        vx *= 0.8;
        // text (depth) to be displayed
        depthText = "" + (int)y + " meter";
    }
}
