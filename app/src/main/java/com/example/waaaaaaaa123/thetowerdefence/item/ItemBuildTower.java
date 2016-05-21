package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.orb.Orb;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/19.
 */
public abstract class ItemBuildTower extends Item {

    protected int towerId;


    @Override
    public void setUsable() {
        super.setUsable();
        if(usable){
            usable =false;
            switch (block.getId()){

                case Block.BASE:

                    if(!Player.getGrid().getPath().contains(block))
                        usable=true;
                    else if(Player.getState()== Player.STATE_PREPARE){
                        block.setId(Block.BUILD);
                        if(Player.getGrid().buildPath(1)!=null)
                            usable =true;
                        block.setId(Block.BASE);

                    }
                break;

                case Block.BUILD:
                    usable=true;
                    break;
                case Block.TOWER:
                    /*Tower tower=Player.getTowerManager().getTower(block);
                    if(tower.getLevel()<Tower.LEVEL_MAX)
                        usable=true;*/
                    break;
            }
        }
    }

    @Override
    public void use() {
        switch (block.getId()){
            case Block.TOWER:

                break;
            case Block.BASE:
                block.setId(Block.BUILD);
                if(Player.getGrid().getPath().contains(block)){
                    Player.getGrid().setPath();
                    Player.getWave().setPath();

                }
            case Block.BUILD:
                Player.getTowerManager().addTower(towerId,block);
                break;
        }
        super.use();
    }
}
