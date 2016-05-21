package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.orb.Orb;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/1/21.
 */
public class ItemAxeTower extends ItemBuildTower {

    @Override
    public void init() {
        towerId=Tower.TOWER_AXE;
        init(ITEM_AXETOWER,2,300);
    }

}
