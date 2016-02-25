package com.example.waaaaaaaa123.thetowerdefence.projectile;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/23.
 */
public class ProjectileSplit extends Projectile {
    public ProjectileSplit(Tower caster, Enemy target) {
        super(caster, target);
    }

    @Override
    public void init() {
        init(PROJECTILE_SPLIT,5);
    }
}
