package com.example.waaaaaaaa123.thetowerdefence.item;

/**
 * Created by aa081_000 on 2016/2/22.
 */
public class ItemBomb extends Item {
    public ItemBomb(ItemSlot slot) {
        super(slot);
    }

    @Override
    public void init() {
        init(ITEM_BOMB,1.5f,500);
    }

    @Override
    public void use() {

        super.use();
    }

    @Override
    public void setUsable() {
        usable=true;
    }
}
