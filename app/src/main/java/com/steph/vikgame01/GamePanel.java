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
    private SeaObject turtle;
    private SeaObject nemo;
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
       turtle = new SeaObject(0,8,this, gameControl, landscapeBackground, BitmapFactory.decodeResource(getResources(),R.drawable.herz));
       nemo = new SeaObject(-3,12,this, gameControl, landscapeBackground, BitmapFactory.decodeResource(getResources(),R.drawable.nemo));



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
                gameControl.movement(0,0);
                break;
            case MotionEvent.ACTION_MOVE:
                text = "buttonpress";
                gameControl.movement( motionEvent.getX() - getWidth()/2,  motionEvent.getY() - getHeight()/2);
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
        nemo.update();
        turtle.update();
        gameControl.update();
   }


   @Override
    public void draw(Canvas canvas) {
       super.draw(canvas);
       if (canvas != null) {
           landscapeBackground.onDraw(canvas);
           turtle.onDraw(canvas);
           nemo.onDraw(canvas);
           spriteDiver.onDraw(canvas);

           //draw Text for Test
           Paint paint = new Paint();
           paint.setTextSize(100);
           paint.setColor(Color.MAGENTA);
//           canvas.drawText(text,20,100,paint);
           canvas.drawText(gameControl.depthText,3*getWidth()/7,getHeight()/10,paint);
       }
   }

}
