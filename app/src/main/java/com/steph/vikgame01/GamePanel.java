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
    private String text;


    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int heigth)
    {

    }

   @Override
   public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);
       landscapeBackground = new LandscapeBackground(BitmapFactory.decodeResource(getResources(),R.drawable.oceanreef));
       spriteDiver = new SpriteDiver(this,BitmapFactory.decodeResource(getResources(),R.drawable.herz));


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
//                spriteDiver.turbo();
                break;
            case MotionEvent.ACTION_DOWN:
                //When the user releases the screen
                //do something here
                text = "x=" + motionEvent.getX()+" y="+motionEvent.getY();
                spriteDiver.turbo((int) motionEvent.getX(), (int) motionEvent.getY());
                break;
        }
        return true;
    }

   public void update(){
        landscapeBackground.update();
   }


   @Override
    public void draw(Canvas canvas) {
       super.draw(canvas);
       if (canvas != null) {
 //          canvas.drawColor(Color.WHITE);
           landscapeBackground.draw(canvas);
           spriteDiver.onDraw(canvas);
           Paint paint = new Paint();
           paint.setTextSize(70);
           paint.setColor(Color.MAGENTA);
           canvas.drawText(text,20,100,paint);
       }
   }

}
