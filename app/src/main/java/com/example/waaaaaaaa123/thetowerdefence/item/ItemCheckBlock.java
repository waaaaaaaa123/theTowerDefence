package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;

/**
 * Created by aa081_000 on 2016/2/24.
 */
public class ItemCheckBlock extends Item {

    @Override
    public void init() {
        init(ITEM_CHECKBLOCK,0,1000);
    }
    @Override
    public void setUsable() {
        super.setUsable();
        if(usable&&Player.getState()== Player.STATE_PREPARE&&block.getId()== Block.BASE)
        {
            usable= true;
        }
        else
            usable=false;
    }


    @Override
    public void use() {
        //Player.getGrid().setBlockId(getRect().centerX(), getRect().centerY(), Block.CHECK);
        block.setId(Block.CHECK);
        Player.getGrid().getChecks().add(Player.getGrid().getBlockCount(block));
        Player.getWave().setPath();
        Player.getGrid().setPath();
        super.use();
    }
}
