package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

/**
 * Created by aa081_000 on 2016/1/19.
 */
public class AxeTower extends Tower {
    public AxeTower(RectF rect) {
        super(rect);
    }

    @Override
    public void init() {
        init(TOWER_AXE,TYPE_PHYSICAL, Projectile.PROJECTILE_AXE,10,10,1,2);
    }

}
