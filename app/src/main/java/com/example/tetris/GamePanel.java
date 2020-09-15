package com.example.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private Button button;
    private MainThread thread;
    private Unit unit, u2;
    private Context context;
    boolean gameOver = false;
    public static Handler handler;
    public static int WIDTH = MainActivity.displayMetrics.widthPixels;
    public static int HIEGHT = MainActivity.displayMetrics.heightPixels;
    public static int SCALE = WIDTH / 9;
    OnSwipeTouchListener onSwipeTouchListener;


    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(),this);
        handler =new Handler();
        //setOnTouchListener();
        onSwipeTouchListener = new OnSwipeTouchListener(context);
        setFocusable(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry =true;
        while(true){
            try {
                thread.setRunning(false);
                thread.join();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            retry= false;
        }
    }


    private float startX =0, stopX =0;
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(!gameOver) {
            boolean swipe = false;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: //первое касание
                    startX = event.getX();
                    break;
                case MotionEvent.ACTION_UP: //отпускание
                    stopX = event.getX();
                    swipe = true;
                    break;
            }
            if (swipe) {

                if (stopX - startX > 100) {
                    onSwipeTouchListener.onSwipeRight(1);

                } else if (stopX - startX < -100) {
                    onSwipeTouchListener.onSwipeLeft(1);
                } else handler.getPlayerBlock().rotate();


                swipe = false;
                startX = 0;
                stopX = 0;
            }
            //return super.onTouchEvent(event);
            return true;
        } return false;
    }




    public void update(){
        handler.update();
    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        handler.draw(canvas);
    }

    public static Block getPlayer() {
        return handler.getPlayerBlock();
    }

    public MainThread getThread() {
        return thread;
    }

    public void setThread(MainThread thread) {
        this.thread = thread;
    }
}
