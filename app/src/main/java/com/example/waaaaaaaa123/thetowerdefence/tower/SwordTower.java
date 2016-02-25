package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

/**
 * Created by aa081_000 on 2016/1/20.
 */
public class SwordTower extends Tower{
    public SwordTower(RectF rect) {
        super(rect);
    }

    @Override
    public void init() {
        init(TOWER_SWORD,TYPE_PHYSICAL, Projectile.PROJECTILE_SWORD,10,1,4);
    }

    @Override
    public void onGreenMushroom() {
        super.onGreenMushroom();
        speed+=0.3;
    }

    @Override
    public void onRedMushroom() {
        super.onRedMushroom();
        damage+=5;
    }

    @Override
    public void onYellowMushroom() {
        super.onYellowMushroom();
        range+=0.75;
    }
}
