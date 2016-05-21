package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;

/**
 * Created by aa081_000 on 2016/1/15.
 */
public class ItemBuildBlock extends Item {

    @Override
    public void init() {
        init(ITEM_BUILDBLOCK,0,50);
    }

    @Override
    public void setUsable() {
        super.setUsable();
        if(usable){
            usable =false;
            if(block.getId()==Block.BASE)
            {
                if(!Player.getGrid().getPath().contains(block))
                    usable=true;
                else if(Player.getState()== Player.STATE_PREPARE){
                    block.setId(Block.BUILD);
                    if(Player.getGrid().buildPath(1)!=null)
                        usable =true;
                    block.setId(Block.BASE);

                }
            }
        }
    }

    @Override
    public void use() {
        if(block.getId()==Block.BASE){
            block.setId(Block.BUILD);
            if(Player.getGrid().getPath().contains(block)){
                Player.getGrid().setPath();
                Player.getWave().setPath();

            }
        }
        super.use();
    }

}
