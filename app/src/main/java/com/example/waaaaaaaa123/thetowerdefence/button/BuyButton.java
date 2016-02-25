package com.example.waaaaaaaa123.thetowerdefence.button;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;
import com.example.waaaaaaaa123.thetowerdefence.item.ItemSlot;

/**
 * Created by aa081_000 on 2016/1/29.
 */
public class BuyButton extends Button {
    private ItemSlot slot;

    public BuyButton(RectF rect) {
        super(rect);
    }

    @Override
    public void init() {
        init(BUTTON_BUY);
    }

    public void setSlot(ItemSlot slot) {
        this.slot = slot;
    }
    public boolean isBuyable(){


        if(slot!=null&&slot.getItem()!=null&&slot.getItem().getCost()<=Player.getShop().getGold())
            return true;
        else
            return false;
    }

    public ItemSlot getSlot() {
        return slot;
    }

    public int getCost() {
        if(slot==null||slot.getItem()==null)
            return 0;
        else{
            return slot.getItem().getCost();
        }
    }

    @Override
    public void onClick() {
        if(isBuyable())
            Player.getShop().buyItem(slot);
    }
}
