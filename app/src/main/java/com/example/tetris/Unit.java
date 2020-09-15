package com.example.tetris;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Unit {
    private Rect rect;
    private int color;
    private int size = GamePanel.SCALE;
    private int x,y;
    private boolean stack = false;



    public Unit(int x,int y,int color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);

        canvas.drawRect(x,y,x+size,y+size,paint);

    }
    public void update(){

    }

    public void somethingDO (){

    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isStack() {
        return stack;
    }
    public void setStack(boolean stack) {
        this.stack = stack;
    }

    public Rect getBounds(){
        return new Rect(x-1, y-1, x+ size+2, y + size+2);
    }
    public Rect getBoundsTop(){
        return new Rect(x+5, y-1, x+size-10, y+5);
    }
    public Rect getBoundsLeft(){
        return new Rect(x-1, y+ 5, x+5, y+size - 10 );
    }
    public Rect getBoundsRight(){
        return new Rect(x+size-5, y+5, x+size+6, y+size - 10);
    }
    public Rect getBoundsBottom(){
        return new Rect(x+5, y+size-5, x+size-10, y+size+6);
    }
}
