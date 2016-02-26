package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;

/**
 * Created by aa081_000 on 2016/1/20.
 */
public class ItemGreenMushroom extends Item {
    public ItemGreenMushroom(ItemSlot slot) {
        super(slot);
    }

    @Override
    public void init() {
        init(ITEM_GREENMUSHROOM,0,200);
    }

    @Override
    public void setUsable() {
        usable =false;
        if(block.getId()==Block.TOWER&& Player.getTowerManager().getTower(block).getMushroom()<5){
            usable =true;
        }
    }

    @Override
    public void use() {
        Player.getTowerManager().getTower(Player.getGrid().getBlock(getRect().centerX(),getRect().centerY())).onGreenMushroom();
        super.use();
    }
}