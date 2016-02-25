package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/22.
 */
public class ItemBombTower extends Item {
    public ItemBombTower(ItemSlot slot) {
        super(slot);
    }

    @Override
    public void init() {
        init(ITEM_BOMBTOWER,2.7f,300);
    }

    @Override
    public void setUsable() {
        super.setUsable();

        if(block.getId()== Block.TOWER){
            Tower tower= Player.getTowerManager().getTower(block);
            if(tower.getId()==Tower.TOWER_BOMB&&tower.getLevel()<Tower.LEVEL_MAX)
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
                Player.getTowerManager().addTower(Tower.TOWER_BOMB,getRect());
                break;
        }
        super.use();
    }
}
