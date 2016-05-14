package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.orb.Orb;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/1/21.
 */
public class ItemWhipTower extends Item {
    public ItemWhipTower(ItemSlot slot) {
        super(slot);
    }

    @Override
    public void init() {
        init(ITEM_WHIPTOWER,2,300);
    }

    @Override
    public void setUsable() {
        super.setUsable();
        if(block.getId()==Block.TOWER){
            Tower tower=Player.getTowerManager().getTower(block);
            if(tower.getLevel()<Tower.LEVEL_MAX)
                usable=true;
        }
    }

    @Override
    public void use() {
        switch (block.getId()){
            case Block.TOWER:
                Player.getTowerManager().getTower(block).addOrb(Orb.ORB_GREEN);
                break;
            case Block.BUILD:
                Player.getTowerManager().addTower(block, Tower.TOWER_ORB).addOrb(Orb.ORB_GREEN);
                break;
        }
        super.use();
    }
}
