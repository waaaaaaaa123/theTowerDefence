package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public class ItemRemove extends Item {
    @Override
    public void init() {
        init(ITEM_REMOVE,0,0);
    }

    @Override
    public void setUsable() {
        super.setUsable();
        if(usable){
            usable=false;
            switch (block.getId()){

                case Block.BUILD:
                case Block.TOWER:
                case Block.CHECK:usable=true;
            }
        }
    }

    @Override
    public void use() {
        switch (block.getId()){
            case Block.BUILD:
                block.setId(Block.BASE);
                break;
            case Block.TOWER:
                block.setId(Block.TOWER);
                Player.getTowerManager().removeTower(block);
                break;
            case Block.CHECK:
                block.setId(Block.BASE);
                Player.getGrid().removeCheck(block);
                break;
        }
        if(Player.getState()==Player.STATE_PREPARE){
            Player.getGrid().setPath();
            Player.getWave().setPath();
        }
        super.use();
    }
}
