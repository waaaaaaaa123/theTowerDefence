package com.example.waaaaaaaa123.thetowerdefence.projectile;

import android.graphics.PointF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/22.
 */
public class ProjectileBomb extends Projectile {
    private float range=1.5f;
    public ProjectileBomb(Tower caster, Enemy target) {
        super(caster, target);
    }

    @Override
    public void init() {
        init(PROJECTILE_BOMB,3);
    }

    @Override
    public void initTargetPoint() {
        super.initTargetPoint();
        target=null;
    }



    @Override
    public void onHit() {
        for (Enemy enemy : Player.getWave().getEnemies()) {
            if(enemy.getState()==Enemy.STATE_ALIVE&&isInRange(enemy,range))
                enemy.attackLanded(this);
        }
        setState(STATE_DEAD);
    }
}
