package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

/**
 * Created by aa081_000 on 2016/2/22.
 */
public class BombTower extends Tower {
    public BombTower(RectF rect) {
        super(rect);
    }

    @Override
    public void init() {
        init(TOWER_BOMB,TYPE_MAGICAL, Projectile.PROJECTILE_BOMB,30,0.5f,2.7f);
    }
}
