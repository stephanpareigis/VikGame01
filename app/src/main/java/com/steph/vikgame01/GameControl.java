package com.steph.vikgame01;

public class GameControl {
    public int x;
    public int y;
    public int vx;
    public int vy;
    public int ax;
    public int ay;
    public final int ylimit = 1300;
    private GamePanel gamePanel;


    GameControl(GamePanel gamePanel){
        x = 540;
        y = 0;
        vx = 0;
        vy = 0;
        ax = 0;
        ay = 0;
        this.gamePanel = gamePanel;
    }

    public void movement(int x, int y){
        ax += (x - 500)/200;
        ay += (y - 900)/300;
    }

    public void update(){
        vx += ax;
        vy += ay;
        if (x >= gamePanel.getWidth()  - vx || x + vx <= 0) {
            vx = -vx;
            ax = -ax;
        }
        x = x + vx;
        if (y >= 5*gamePanel.getHeight()  - vy || y + vy <= 0) {
            vy = 0;
            ay = 0;
        }
        y = y + vy;
        //Reibung
        if ( ax > 0 ) ax -= 1;
        if ( ax < 0 ) ax += 1;
        if ( ay > 0 ) ay -= 1;
        if ( ay < 0 ) ay += 1;

    }
}
