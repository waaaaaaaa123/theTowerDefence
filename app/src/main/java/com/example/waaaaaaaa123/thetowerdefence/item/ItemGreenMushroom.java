package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.modifier.Modifier;
import com.example.waaaaaaaa123.thetowerdefence.modifier.TowerModifier;


/**
 * Created by aa081_000 on 2016/1/20.
 */
public class ItemGreenMushroom extends Item {
    private int mId=TowerModifier.MODIFIER_TOWER_SPEEDUP;
    private int stack=1;

    @Override
    public void init() {
        init(ITEM_GREENMUSHROOM,0,200);
    }

    @Override
    public void setUsable() {
        super.setUsable();
        if(usable&&block.getId()==Block.TOWER&& Player.getTowerManager().getTower(block).hasModifier(mId)<=stack){
            usable =true;
        }
        else
            usable=false;
    }

    @Override
    public void use() {
        Player.getTowerManager().getTower(block).addModifier(mId,stack);
        super.use();
    }
}
