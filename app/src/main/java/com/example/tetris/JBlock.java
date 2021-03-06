package com.example.tetris;

import android.graphics.Color;


public class JBlock extends Block{
    private int color = Color.rgb(215, 213, 122);
    public JBlock(int x, int y){
        super(x,y);
        block.add(new Unit(x, y-size,color));
        block.add(new Unit(x, y,color));
        block.add(new Unit(x, y+size,color));
        block.add(new Unit(x-size, y+size,color));
    }
    public void position(int index){
        int x = block.get(1).getX();
        int y = block.get(1).getY();
        switch (index){
            case 1:
                block.get(0).setPosition(x-size, y);
                block.get(2).setPosition(x+size, y);
                block.get(3).setPosition(x+size, y+size);
                break;
            case 2:
                block.get(0).setPosition(x, y+size);
                block.get(2).setPosition(x, y-size);
                block.get(3).setPosition(x+size, y-size);
                break;
            case 3:
                block.get(0).setPosition(x-size, y-size);
                block.get(2).setPosition(x-size, y);
                block.get(3).setPosition(x+size, y);
                break;
            case 0:
                block.get(0).setPosition(x, y-size);
                block.get(2).setPosition(x, y+size);
                block.get(3).setPosition(x-size, y+size);
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
                rotate_position = 1;
                break;
            case 1:
                position(2);
                rotate_position = 2;
                break;
            case 2:
                position(3);
                rotate_position = 3;
                break;
            case 3:
                position(0);
                rotate_position = 0;
        }
    }

}
