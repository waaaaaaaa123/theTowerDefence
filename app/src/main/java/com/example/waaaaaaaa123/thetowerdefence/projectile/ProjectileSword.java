package com.example.waaaaaaaa123.thetowerdefence.projectile;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/1/20.
 */
public class ProjectileSword extends Projectile {
    public ProjectileSword(Tower caster, Enemy target) {
        super(caster, target);
    }

    @Override
    public void init() {
        init(PROJECTILE_SWORD,10);
    }
}
