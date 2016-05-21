package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/23.
 */
public class ItemChainTower extends ItemBuildTower {

    @Override
    public void init() {
        towerId=Tower.TOWER_CHAIN;
        init(ITEM_CHAINTOWER, 4, 300);
    }

}
