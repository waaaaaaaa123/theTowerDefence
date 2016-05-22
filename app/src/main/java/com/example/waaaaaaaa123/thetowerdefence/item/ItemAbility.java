package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public abstract class ItemAbility extends Item {
    protected int aId;



    @Override
    public void setUsable() {
        super.setUsable();
        if(usable){
            usable=false;
            Tower tower= Player.getTowerManager().getTower(block);
            if(tower!=null&&tower.getAbilities().size()<tower.getLevel()){
                usable=true;
            }


        }
    }

    @Override
    public void use() {
        Player.getTowerManager().getTower(block).addAbility(aId);
        super.use();
    }
}
