package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

/**
 * Created by aa081_000 on 2016/2/23.
 */
public class ConeTower extends Tower {
    public ConeTower(RectF rect) {
        super(rect);
    }

    @Override
    public void init() {
        init(TOWER_CONE,TYPE_MAGICAL, Projectile.PROJECTILE_CONE,10,1,4);
    }
}
