package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/1/21.
 */
public class ItemAxeTower extends Item {
    public ItemAxeTower(ItemSlot slot) {
        super(slot);
    }

    @Override
    public void init() {
        init(ITEM_AXETOWER,2,300);
    }

    @Override
    public void setUsable() {
        super.setUsable();

        if(block.getId()==Block.TOWER){
            Tower tower=Player.getTowerManager().getTower(block);
            if(tower.getId()==Tower.TOWER_AXE&&tower.getLevel()<Tower.LEVEL_MAX)
                usable=true;
        }
    }

    @Override
    public void use() {
        switch (block.getId()){
            case Block.TOWER:
                Player.getTowerManager().getTower(block).onLevelUp();
                break;
            case Block.BUILD:
                Player.getTowerManager().addTower(Tower.TOWER_AXE,getRect());
                break;
        }
        super.use();
    }
}
