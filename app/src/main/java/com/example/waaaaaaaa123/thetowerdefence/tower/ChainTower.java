package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

/**
 * Created by aa081_000 on 2016/2/23.
 */
public class ChainTower extends Tower {


    public ChainTower(Block block) {
        super(block);
    }

    @Override
    public void init() {
        init(TOWER_CHAIN,TYPE_MAGICAL, Projectile.PROJECTILE_CHAIN,10,10,0.5f,4);
    }
}
