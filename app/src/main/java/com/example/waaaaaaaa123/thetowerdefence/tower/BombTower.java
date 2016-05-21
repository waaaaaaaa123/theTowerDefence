package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

/**
 * Created by aa081_000 on 2016/2/22.
 */
public class BombTower extends Tower {

    public BombTower(Block block) {
        super(block);
    }

    @Override
    public void init() {
        init(TOWER_BOMB,TYPE_MAGICAL, Projectile.PROJECTILE_BOMB,1,3,0.5f,2.7f);
        range=10;
    }
}
