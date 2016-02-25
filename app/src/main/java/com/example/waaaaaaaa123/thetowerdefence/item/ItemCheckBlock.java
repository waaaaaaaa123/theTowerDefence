package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;

/**
 * Created by aa081_000 on 2016/2/24.
 */
public class ItemCheckBlock extends Item {
    public ItemCheckBlock(ItemSlot slot) {
        super(slot);
    }

    @Override
    public void init() {
        init(ITEM_CHECKBLOCK,0,1000);
    }
    @Override
    public void setUsable() {
        usable =false;
        if(Player.getState()== Player.STATE_PREPARE&&block.getId()== Block.BASE)
        {
            usable= true;
        }
    }

    @Override
    public void buy() {
        cost*=2;
    }

    @Override
    public void use() {
        Player.getGrid().setBlockId(getRect().centerX(), getRect().centerY(), Block.CHECK);
        Player.getWave().setPath();
        super.use();
    }
}
