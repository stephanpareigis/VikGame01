package com.steph.vikgame01;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;



public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private LandscapeBackground landscapeBackground;
    private SpriteDiver spriteDiver;
    private GameControl gameControl;
    private String text;


    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        text = new String();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int heigth)
    {

    }

   @Override
   public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);
       gameControl = new GameControl(this);
       landscapeBackground = new LandscapeBackground(this, gameControl, BitmapFactory.decodeResource(getResources(),R.drawable.meerhintergrund));
       spriteDiver = new SpriteDiver(this, gameControl, landscapeBackground, BitmapFactory.decodeResource(getResources(),R.drawable.taucherspritesheet));


        thread.setRunning(true);
        thread.start();

   }

   @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while (true) {
            try{
                thread.setRunning(false);
                thread.join();
            } catch(Exception e) {e.printStackTrace();}
            retry = false;
        }
   }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                //When the user presses on the screen
                //we will do something here
                break;
            case MotionEvent.ACTION_DOWN:
                //When the user releases the screen
                //do something here
                text = "x=" + ((int) motionEvent.getX() - getWidth()/2) ;
                gameControl.movement( motionEvent.getX() - getWidth()/2,  motionEvent.getY() - getHeight()/2);
                break;
        }
        return true;
    }

   public void update(){
        landscapeBackground.update();
        spriteDiver.update();
        gameControl.update();
   }


   @Override
    public void draw(Canvas canvas) {
       super.draw(canvas);
       if (canvas != null) {
           landscapeBackground.onDraw(canvas);
           spriteDiver.onDraw(canvas);

           //draw Text for Test
           Paint paint = new Paint();
           paint.setTextSize(70);
           paint.setColor(Color.MAGENTA);
           canvas.drawText(text,20,100,paint);
       }
   }

}
