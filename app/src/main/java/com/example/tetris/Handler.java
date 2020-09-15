package com.example.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import java.util.Random;

public class Handler {
    private static int colorBorder = Color.rgb(100,100,100);
    private Unit[][] tower = new Unit[GamePanel.HIEGHT/GamePanel.SCALE][GamePanel.WIDTH/GamePanel.SCALE];
    public static Unit LBorder = new Unit( -GamePanel.HIEGHT -2 ,0,colorBorder);
    public static Unit RBorder = new Unit( GamePanel.WIDTH +2,0,colorBorder);
    static int tugriks = 0;
    private  Block playerBlock =null;
    GamePanel gamePanel;

    Handler(){
        LBorder.setSize(GamePanel.HIEGHT);
        RBorder.setSize(GamePanel.HIEGHT);
    }

    public void draw(Canvas canvas) {
        playerBlock.draw(canvas);
        for(Unit[] p: tower) {
            for(Unit s :p)
                if(s!= null ) {
                    s.draw(canvas);}
        }
        LBorder.draw(canvas);
        RBorder.draw(canvas);
    }

    public void update() {
        if(!nullString(1)) {
            System.out.println("GameOver");

            return;
        }

        if(playerBlock == null) {
            playerBlock = generateShape();
        }
        clean();
        playerBlock.update();
        if(playerBlock.isTouch()) {
            addToTower(playerBlock);

            playerBlock = generateShape();
        }

    }
    private Block generateShape() {
        Block block;
        Random rand = new Random();
        int random = rand.nextInt(7);
        //random =3;
        //block = new LBlock(Game.WIDTH -25, Game.HIGHT- 50);
        switch(random){
            case 0:
                block = new JBlock(GamePanel.SCALE*5, 0);
                break;
            case 1:
                block = new LBlock(GamePanel.SCALE*5, 0);
                break;
            case 2:
                block = new OBlock(GamePanel.SCALE*5, 0);
                break;
            case 3:
                block = new SBlock(GamePanel.SCALE*5, 0);
                break;
            case 4:
                block = new ZBlock(GamePanel.SCALE*5, 0);
                break;
            case 5:
                block = new TBlock(GamePanel.SCALE*5, 0);
                break;
            case 6:
                block = new IBlock(GamePanel.SCALE*5, 0);
                break;
            case 7:
                block = new IBlock(GamePanel.SCALE*5, 0);
                break;
            default:
                block = new JBlock(GamePanel.SCALE*5, 0);
        }
        return block;
    }
    private void addToTower(Block block) {
        for(int i = 0 ; i < 4; i++){
            block.getBlock().get(i).setStack(true);
            int x = block.getBlock().get(i).getY()/GamePanel.SCALE;
            int y = block.getBlock().get(i).getX()/GamePanel.SCALE;
            tower[x][y] = block.getBlock().get(i);
        }
    }
    public void cleanRaw(int strIndex){
        for(int i = strIndex; i > 0; i--) {
            if(!nullString(i)) {
                for (int j = 0; j < GamePanel.WIDTH /GamePanel.SCALE; j++) {
                    if(tower[i-1][j] != null) {
                        tower[i-1][j].setY(tower[i-1][j].getY() + GamePanel.SCALE);
                    }
                    tower[i][j] = tower[i-1][j];
                }
            }
            else break;
        }
    }

    public void clean(){
        for(int i = 0; i < GamePanel.HIEGHT/GamePanel.SCALE; i++){
            if(fullString(i)){
                cleanRaw(i);
                tugriks+=100;
            }
        }
    }



    private boolean nullString(int index) {
        for(int i = 0; i < GamePanel.WIDTH/GamePanel.SCALE; i++) {
            if (tower[index][i] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean fullString(int index){
        for(int i = 0; i < GamePanel.WIDTH/GamePanel.SCALE; i++) {
            //System.out.println("i= " + i );
            if (tower[index][i] != null) {
                if(i == GamePanel.WIDTH/GamePanel.SCALE -1){
                    return true;
                }
                continue;
            }
            return false;
        }
        return false;
    }

    public Block getPlayerBlock() {
        return playerBlock;
    }

    public Unit[][] getBlocks() {
        return tower;
    }
}
