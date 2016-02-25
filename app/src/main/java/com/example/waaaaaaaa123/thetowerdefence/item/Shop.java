package com.example.waaaaaaaa123.thetowerdefence.item;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.button.BuyButton;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/1/14.
 */
public class Shop {
    private int gold=625;
    private ArrayList<ItemSlot> slots;
    private RectF rect;
    private BuyButton buyButton;
    private boolean open;

    public Shop(){
        slots=new ArrayList<>(16);
        rect=new RectF(Player.getMainRect());

        float left,top,right,bottom;
        left=rect.left+10;
        top=rect.top+10;
        right=rect.right-10;
        bottom=rect.bottom-10;
        float l=(bottom-top)/6;
        top=rect.centerY()-2*l;
        bottom=top+l;
        for(int i=0;i<4;i++)
        {
            left=rect.centerX()-2*l;
            right=left+l;
            for(int j=0;j<4;j++){
                ItemSlot slot=new ItemSlot(new RectF(left+5,top+5,right-5,bottom-5));
                slots.add(slot);
                left=right;
                right+=l;
            }
            top=bottom;
            bottom+=l;
        }

        for (int i = 1; i < 16; i++) {
            slots.get(i).addItem(i,99);
        }
        buyButton=new BuyButton(new RectF(rect.centerX()-l,rect.bottom-l-10,rect.centerX()+l,rect.bottom-10));
    }

    public boolean isOpen() {
        return open;
    }

    public void turnOpen(){
        open=!open;

    }
    public void buyItem(ItemSlot slot){
        if(Player.getBag().addItem(slot.getItem().getId())){
            gold-=slot.getItem().getCost();
            slot.stackDown();
            slot.getItem().buy();
        }

    }
    public ItemSlot getSlot(float x,float y) {
        for (ItemSlot slot : slots) {
            if(slot.getRect().contains(x,y))
                return slot;
        }
        return null;
    }
    public ArrayList<ItemSlot> getSlots() {
        return slots;
    }

    public RectF getRect() {
        return rect;
    }

    public BuyButton getBuyButton() {
        return buyButton;
    }

    public int getGold() {
        return gold;
    }
    public void earnGold(int earn){gold+=earn;}
}
