package com.example.waaaaaaaa123.thetowerdefence.projectile;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/7.
 */
public class ProjectileRifle extends Projectile {
    public ProjectileRifle(Tower caster, Enemy target) {
        super(caster, target);
    }

    @Override
    public void recycle(Tower caster, Enemy target) {
        super.recycle(caster, null);
    }

    @Override
    public void init() {
        init(PROJECTILE_RIFLE, 1);
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
