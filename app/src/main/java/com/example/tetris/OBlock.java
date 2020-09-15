package com.example.tetris;

import android.graphics.Color;
import android.graphics.Rect;

class OBlock extends Block {
    private int color = Color.rgb(255,122,54);

    @Override
    public void position(int index) {

    }

    @Override
    public void rotate() {

    }

    @Override
    public void upDate() {

    }

    public OBlock(int x, int y){
        super(x,y);
        block.add(new Unit(x- size, y,color));
        block.add(new Unit(x, y,color));
        block.add(new Unit(x, y+size,color));
        block.add(new Unit(x-size, y+size,color));
    }
}
