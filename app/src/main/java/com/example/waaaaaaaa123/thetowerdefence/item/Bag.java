package com.example.waaaaaaaa123.thetowerdefence.item;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.button.ShopButton;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by aa081_000 on 2016/1/14.
 */
public class Bag{

    private ArrayList<ItemSlot> slots;
    private RectF rect;
    private ShopButton shopButton;
    public Bag(){
        rect=Player.getBottomRect();


        slots=new ArrayList<>(8);
        float left,top,right,bottom;
        left=rect.left+10;
        top=rect.top+10;
        right=rect.right-10;
        bottom=rect.bottom-10;
        float l=Math.min((right-left)/6,(bottom-top)/2);
        bottom=top+l;
        for(int i=0;i<2;i++)
        {
            left=(rect.left+rect.right)/2-2*l;
            right=left+l;
            for(int j=0;j<4;j++){
                slots.add(new ItemSlot(new RectF(left+5,top+5,right-5,bottom-5)));
                left=right;
                right+=l;
            }
            top=bottom;
            bottom+=l;
        }

        shopButton=new ShopButton(new RectF(rect.right-l,rect.top,rect.right,rect.top+l));
    }
    public boolean addItem(int id){
        for (ItemSlot slot : slots) {
            Item item=slot.getItem();
            if (item!=null&&id==item.getId())
            {
                slot.stackUp();
                return true;
            }
        }

        for (ItemSlot slot : slots) {
            if(slot.getItem()==null){
                slot.addItem(id,1);
                return true;
            }
        }
        return false;
    }


    public ItemSlot getSlot(float x,float y) {
        for (ItemSlot slot : slots) {
            if(slot.getRect().contains(x,y))
                return slot;
        }
        return null;
    }

    public ShopButton getShopButton() {
        return shopButton;
    }

    public ArrayList<ItemSlot> getSlots() {
        return slots;
    }

}
