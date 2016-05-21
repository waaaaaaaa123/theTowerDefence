package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/23.
 */
public class ItemSplitTower extends ItemBuildTower {

    @Override
    public void init() {
        towerId=Tower.TOWER_SPLIT;
        init(ITEM_SPLITTOWER, 3, 300);
    }

}
