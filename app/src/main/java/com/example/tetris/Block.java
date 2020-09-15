package com.example.tetris;

import android.graphics.Canvas;

import java.util.ArrayList;

public abstract class Block {
    private int speed = 8;
    protected int rotate_position = 0;
    protected ArrayList<Unit> block = new ArrayList<>();
    protected int size = GamePanel.SCALE;
    private int x,y;
    private boolean touch = false;
    protected boolean left = true;
    protected boolean right = true;
    protected boolean rotate = true;

    public abstract void position(int index);
    public abstract void rotate();
    public abstract void upDate();

    public Block(int x, int y) {
        this.x =x;
        this.y= y;
    }

    public void draw(Canvas canvas){
        for(Unit b: block)
            b.draw(canvas);
    }

    public  void update() {

        for(Unit p : block){
            if(p.getY() + size >= GamePanel.HIEGHT) {
                this.touch = true;
                return;
            }
            else this.touch = false;
            for(Unit[] i: GamePanel.handler.getBlocks()) {
                for (Unit j : i){
                    if(j != null)
                        if(p.getBoundsBottom().intersect(j.getBounds())) {
                            this.touch = true;

                            return;
                        }
                }
            }
        }
        for(Unit p : block)
        {
            p.setY(p.getY() + speed);
        }
     }

    public boolean collision(){
        int ii = 0;
        for(Unit b : block) {
            if(ii ==3){
                System.out.println("j");
            }
            ii++;
            if(!(b.getBounds().intersect(GamePanel.handler.RBorder.getBounds()))|| !(b.getBounds().intersect(GamePanel.handler.LBorder.getBounds()))){
                System.out.println("aloha");
                for (Unit[] i : GamePanel.handler.getBlocks()) {
                    for (Unit j : i) {
                        if (j != null)
                            if (b.getBounds().intersect(j.getBounds())){
                                return true;
                            }
                    }
                }
            }else
                return true;
        }
        return false;
    }

    public void moveR(int step){
        for(Unit b: block){
            if(b.getX() == GamePanel.WIDTH - size){
                right = false;
                break;
            }else if(b.getX() == GamePanel.WIDTH - size*2 && step == 2){
                step =1;
            }
            for(Unit[] i: GamePanel.handler.getBlocks()) {
                for(Unit j : i) {
                    if(j != null)
                        if (b.getBoundsRight().intersect(j.getBounds())) {
                            System.out.println("Right");
                            rotate =false;
                            right = false;
                            break;
                        }
                }
            }
        }
        if(right)
            for(Unit b: block)
                b.setX(b.getX() +  size*step);
        right = true;
    }

    public void moveL(int step) {
        for(Unit b: block){
            System.out.println(b.getX());
            if(b.getX() == 0){
                left = false;
                break;
            }else if(b.getX() == size && step == 2){

                step =1;

            }

            for(Unit[] i: GamePanel.handler.getBlocks()) {
                for(Unit j : i) {
                    if(j != null)
                        if (b.getBoundsLeft().intersect(j.getBounds())) {
                            System.out.println("Left");
                            rotate = false;
                            left = false;
                            break;
                        }
                }
            }
        }
        if(left)
            for(Unit b: block)
                b.setX(b.getX()- size*step);
        left = true;
    }

    public void setPosition(int x, int y) {
        for(Unit u : block){
            u.setX(u.getX() + x);
            u.setY(u.getY() + y);
        }
    }

    public boolean isTouch(){
        return touch;
    }

    public void setTouch(boolean touch){
        this.touch = touch;
    }

    public ArrayList<Unit> getBlock(){
        return block;
    }
}
