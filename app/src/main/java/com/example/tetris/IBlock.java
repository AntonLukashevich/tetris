package com.example.tetris;

import android.graphics.Color;

public class IBlock extends Block{
    private int color = Color.rgb(45,124,10);
    public IBlock(int x, int y){
        super(x,y);
        block.add(new Unit(x, y-size,color));
        block.add(new Unit(x, y,color));
        block.add(new Unit(x, y+size,color));
        block.add(new Unit(x, y+2*size,color));
    }
    
    public void position(int index){
        int x = block.get(1).getX();
        int y = block.get(1).getY();
        switch (index){
            case 1:
                block.get(0).setPosition(x-size, y);
                block.get(2).setPosition(x+size, y);
                block.get(3).setPosition(x+2*size, y);
                break;
            case 0:
                block.get(0).setPosition(x, y-size);
                block.get(2).setPosition(x, y+size);
                block.get(3).setPosition(x, y+2*size);
                break;
        }
    }
    public void upDate(){
        position(rotate_position);
    }
    public void rotate(){
        switch (rotate_position) {
            case 0:
                position(1);
                if(collision()) {
                    position(0);
                    rotate_position = 0;
                } else rotate_position =1;
                break;
            case 1:
                position(0);
                if(collision()){
                    position(1);
                    rotate_position =1;
                }else
                rotate_position = 0;
                break;

        }
    }

}