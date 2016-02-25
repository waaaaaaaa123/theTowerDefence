package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/23.
 */
public class ItemConeTower extends Item {
    public ItemConeTower(ItemSlot slot) {
        super(slot);
    }

    @Override
    public void init() {
        init(ITEM_CONETOWER,4,300);
    }
    @Override
    public void setUsable() {
        super.setUsable();

        if(block.getId()== Block.TOWER){
            Tower tower= Player.getTowerManager().getTower(block);
            if(tower.getId()==Tower.TOWER_CONE&&tower.getLevel()<Tower.LEVEL_MAX)
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
                Player.getTowerManager().addTower(Tower.TOWER_CONE,getRect());
                break;
        }
        super.use();
    }
}
