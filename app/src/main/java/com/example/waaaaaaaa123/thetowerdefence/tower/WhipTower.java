package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileWhip;

/**
 * Created by aa081_000 on 2016/1/20.
 */
public class WhipTower extends Tower {
    ProjectileWhip whip;

    public WhipTower(Block block) {
        super(block);
    }


    @Override
    public void init() {
        init(TOWER_WHIP,TYPE_PHYSICAL, Projectile.PROJECTILE_WHIP,2,2,5,2);
    }


/*
    @Override
    public void findTarget() {
        super.findTarget();
        whip.setTarget(getTarget());
    }

    @Override
    public Projectile getProjectile() {
        return whip;
    }

    @Override
    public void attack() {
        if(whip.isWhipOn())
        {
            super.attack();
            whip.hit();
        }

    }

    @Override
    public void update(long dt) {
        findTarget();
        if(whip.isWhipOn())
            timer(dt);

    }*/
}
