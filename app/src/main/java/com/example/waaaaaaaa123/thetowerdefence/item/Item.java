package com.example.waaaaaaaa123.thetowerdefence.item;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;

/**
 * Created by aa081_000 on 2016/1/12.
 */
public class Item {
    public static final int STATE_INBAG=0;
    public static final int STATE_ONSELLECT=1;
    public static final int STATE_USE=2;

    private int id=0;
    private int state;

    private float range;

    private RectF rect;
    private RectF bagRect;

    private boolean useable=false;
    private boolean showRange=false;

    private static Grid grid;
    private static TowerManager towerManager;
    private static Player player;

    public Item(){
        rect =new RectF();
        bagRect=new RectF();

        range=Grid.getLength()*2;
    }

    public static void setGrid(Grid grid) {
        Item.grid = grid;
    }

    public static void setTowerManager(TowerManager towerManager) {
        Item.towerManager = towerManager;
    }

    public static void setPlayer(Player player) {
        Item.player = player;
    }

    public RectF getRect() {
        return rect;
    }

    public void setRect(float left,float top,float right,float bottom) {
        rect.set(left,top,right,bottom);
    }

    public float getRange() {
        return range;
    }

    public boolean isShowRange() {
        return showRange;
    }

    public void onSellect(float x,float y){
        float l=Grid.getLength();
        switch (state){
            case STATE_INBAG:
                bagRect.set(rect);
                state=STATE_ONSELLECT;
                break;
            case STATE_ONSELLECT:
                Block block=grid.getBlock(x, y);
                if(block==null)
                {
                    rect.set(x-l/2,y-l/2,x+l/2,y+l/2);
                    useable=false;
                    showRange=false;
                }
                else
                {
                    rect.set(block.getRect());
                    setUseable(block);
                    showRange=true;
                }
                break;

        }

    }
    public void use(){
        towerManager.addTower(rect);
        player.removeItem(this);
    }
    public void setUseable(Block block) {
        if(block.getId()==Block.BUILD)
            useable=true;
        else
            useable=false;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        switch (state){
            case STATE_INBAG:rect.set(bagRect);break;
            case STATE_USE:use();break;
        }
    }

    public void rectOffset(float x,float y) {
        rect.offset(x,y);
    }


    public int getId() {
        return id;
    }

    public boolean isUseable() {
        return useable;
    }
}
