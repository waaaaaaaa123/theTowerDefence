package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.orb.Orb;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/1/21.
 */
public class ItemSwordTower extends ItemBuildTower {

    @Override
    public void init() {
        towerId=Tower.TOWER_SWORD;
        init(ITEM_SWORDTOWER, 4, 300);
    }


}
