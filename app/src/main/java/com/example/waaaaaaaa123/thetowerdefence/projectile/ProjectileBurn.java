package com.example.waaaaaaaa123.thetowerdefence.projectile;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/7.
 */
public class ProjectileBurn extends Projectile {
    public ProjectileBurn(Tower caster, Enemy target) {
        super(caster, target);
    }

    @Override
    public void recycle(Tower caster, Enemy target) {
        super.recycle(caster, null);
    }

    @Override
    public void init() {
        init(Projectile.PROJECTILE_BURN,1);
    }
}
