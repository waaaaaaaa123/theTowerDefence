package com.example.waaaaaaaa123.thetowerdefence.projectile;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/10.
 */
public class ProjectileCombo extends Projectile {
    public ProjectileCombo(Tower caster, Enemy target) {
        super(caster, target);
    }

    @Override
    public void init() {
        init(PROJECTILE_COMBO, 1);
    }

    @Override
    public void recycle(Tower caster, Enemy target) {
        super.recycle(caster, null);
    }
    @Override
    public void update(long dt) {
        for (Enemy enemy : Player.getWave().getEnemies()) {
            if(enemy.getState()==Enemy.STATE_ALIVE&&isInRange(enemy)){
                enemy.attackLanded(this);
                onHit();
            }
        }

        super.update(dt);
    }
}
