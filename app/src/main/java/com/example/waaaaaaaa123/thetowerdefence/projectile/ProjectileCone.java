package com.example.waaaaaaaa123.thetowerdefence.projectile;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by aa081_000 on 2016/2/23.
 */
public class ProjectileCone extends Projectile {

    private HashSet<Enemy> hits;
    public ProjectileCone(Tower caster, Enemy target) {
        super(caster, target);
        hits=new HashSet<>(Player.getWave().getEnemies().size());
    }

    @Override
    public void recycle(Tower caster, Enemy target) {
        super.recycle(caster, null);
        if(hits!=null)
            hits.clear();
    }

    @Override
    public void init() {
        init(PROJECTILE_CONE,3);
    }


    @Override
    public void update(long dt) {
        for (Enemy enemy : Player.getWave().getEnemies()) {
            if(enemy.getState()==Enemy.STATE_ALIVE&&!hits.contains(enemy)&&isInRange(enemy)){
                enemy.attackLanded(this);
                hits.add(enemy);
            }
        }

        super.update(dt);
    }
}
