package com.example.waaaaaaaa123.thetowerdefence.item;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;

/**
 * Created by aa081_000 on 2016/1/12.
 */
public abstract class Item {
    public static final int ITEM_BUILDBLOCK=1;
    public static final int ITEM_GREENMUSHROOM=2;
    public static final int ITEM_REDMUSHROOM=3;
    public static final int ITEM_YELLOWMUSHROOM=4;
    public static final int ITEM_AXETOWER=5;
    public static final int ITEM_WHIPTOWER=6;
    public static final int ITEM_SWORDTOWER=7;
    public static final int ITEM_BOMB=8;
    public static final int ITEM_NET=9;
    public static final int ITEM_BOMBTOWER=10;
    public static final int ITEM_CHAINTOWER=11;
    public static final int ITEM_CONETOWER=12;
    public static final int ITEM_SPLITTOWER=13;
    public static final int ITEM_CHECKBLOCK=14;

    public static final int STATE_INSLOT =0;
    public static final int STATE_ONFOCUS =1;
    public static final int STATE_USE=2;

    private int id=0;
    private int state;

    private float range;
    protected int cost;

    private RectF rect;
    private ItemSlot slot;

    protected Block block;
    private boolean showRange=false;
    protected boolean usable =false;



    public Item(ItemSlot slot){
        this.slot=slot;
        rect =new RectF(slot.getRect());
        init();
    }
    public abstract void init();

    public final void init(int id,float range,int cost){
        this.id=id;
        this.range=range;
        this.cost=cost;
    }


    public RectF getRect() {
        return rect;
    }

    public void setRect(float left,float top,float right,float bottom) {
        rect.set(left,top,right,bottom);
    }
    public void setRect(RectF rect){
        this.rect.set(rect);
    }






    public final float getRange() {
        return range*Grid.getLength();
    }

    public int getCost() {
        return cost;
    }

    public final boolean isShowRange() {
        return showRange;
    }

    public final void onFocus(float x, float y){
        float l=Grid.getLength();
        switch (state){
            case STATE_INSLOT:
                state= STATE_ONFOCUS;
                break;
            case STATE_ONFOCUS:
                block=Player.getGrid().getBlock(x, y);
                if(block==null)
                {
                    rect.set(x-l/2,y-l/2,x+l/2,y+l/2);
                    usable =false;
                    showRange=false;
                }
                else
                {
                    rect.set(block.getRect());
                    setUsable();
                    showRange=true;
                }
                break;

        }

    }
    public void use(){
        slot.stackDown();
        setState(STATE_INSLOT);
    }
    public void buy(){

    }
    public void setUsable() {
        usable = block.getId() == Block.BUILD;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        switch (state){
            case STATE_INSLOT:
                rect.set(slot.getRect());
                showRange=false;
                break;
            case STATE_USE:
                use();
                break;
        }
    }

    public void rectOffset(float x,float y) {
        rect.offset(x,y);
    }


    public int getId() {
        return id;
    }

    public boolean isUsable() {
        return usable;
    }

}
