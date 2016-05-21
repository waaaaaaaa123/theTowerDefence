package com.example.waaaaaaaa123.thetowerdefence.item;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.dialog.DialogItem;

/**
 * Created by aa081_000 on 2016/1/25.
 */
public class ItemSlot {

    private static float length;

    private Item item;
    private int stack;
    private RectF rect;
    private boolean focus;
    private boolean hold;
    private boolean drag;

    public static final int TYPE_BAG=0;
    public static final int TYPE_SHOP=1;
    protected int type;


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

    public static void setLength(float length) {
        ItemSlot.length = length;
    }

    public static float getLength() {
        return length;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    public void addItem(int id,int num){
        item=Item.create(id,num);
        if(type==TYPE_SHOP)
            item.setShop(true);
        if(type==TYPE_BAG)
            item.setBag(true);
    }

    public int getType() {
        return type;
    }

    public void removeItem(){
        item=null;
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
    public void swap(ItemSlot slot){
        Item t=slot.item;
        slot.item=item;
        item=t;

    }
    public void onDrag(float x,float y){
        item.onFocus(x, y);
    }
}
