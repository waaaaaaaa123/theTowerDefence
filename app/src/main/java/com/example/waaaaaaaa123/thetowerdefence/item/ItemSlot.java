package com.example.waaaaaaaa123.thetowerdefence.item;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.dialog.DialogItem;

/**
 * Created by aa081_000 on 2016/1/25.
 */
public class ItemSlot {
    private Item item;
    private int stack;
    private RectF rect;
    private boolean focus;
    private boolean hold;
    private boolean drag;


    public ItemSlot(RectF rect){
        this.rect=rect;
        this.item=null;
    }
    public boolean isHold() {
        return hold;
    }

    public boolean isFocus() {
        return focus;
    }

    public boolean isDrag() {
        return drag;
    }

    public RectF getRect() {
        return rect;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    public void addItem(int id,int num){
        if(item==null){
            switch(id){
                case Item.ITEM_BUILDBLOCK:item=new ItemBuildBlock(this);break;
                case Item.ITEM_GREENMUSHROOM:item=new ItemGreenMushroom(this);break;
                case Item.ITEM_REDMUSHROOM:item=new ItemRedMushroom(this);break;
                case Item.ITEM_YELLOWMUSHROOM:item=new ItemYellowMushroom(this);break;
                case Item.ITEM_AXETOWER:item=new ItemAxeTower(this);break;
                case Item.ITEM_WHIPTOWER:item=new ItemWhipTower(this);break;
                case Item.ITEM_SWORDTOWER:item=new ItemSwordTower(this);break;
                case Item.ITEM_BOMBTOWER:item=new ItemBombTower(this);break;
                case Item.ITEM_CONETOWER:item=new ItemConeTower(this);break;
                case Item.ITEM_CHAINTOWER:item=new ItemChainTower(this);break;
                case Item.ITEM_SPLITTOWER:item=new ItemSplitTower(this);break;
                case Item.ITEM_CHECKBLOCK:item=new ItemCheckBlock(this);break;
            }
            stack=num;
        }
    }
    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }
    public void stackUp() {
        if(item!=null)
            stack++;
    }
    public void stackDown(){
        stack--;
        if(stack<1)
            item=null;
    }
    public void onFocus(){
        focus =!focus;
    }
    public void onHold(){
        if(!hold&&item!=null){
            Player.getDialogs().push(new DialogItem(item));
            hold=true;
        }
    }
    public void offHold(){
        if(hold){
            if(!Player.getDialogs().empty())
                Player.getDialogs().pop();
            hold=false;
        }
    }
    public void onDrag(float x,float y){
        item.onFocus(x, y);
    }
}
