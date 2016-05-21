package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

import java.util.HashSet;

/**
 * Created by aa081_000 on 2016/2/23.
 */
public class SplitTower extends Tower {
    private int split=5;
    private HashSet<Enemy> enemies;
    public SplitTower(Block block) {
        super(block);
        enemies=new HashSet<>(split);
    }

    @Override
    public void init() {
        init(TOWER_SPLIT, TYPE_MAGICAL, Projectile.PROJECTILE_SPLIT, 10,10, 1, 3);
    }

    @Override
    public void attack() {
        super.attack();
        Enemy t=target;
        enemies.clear();
        for (int i = 1; i <split ; i++) {
            for (Enemy enemy : Player.getWave().getEnemies()) {
                if(enemy.getState()==Enemy.STATE_ALIVE&&!enemies.contains(enemy)&&isInRange(enemy)){
                    enemies.add(enemy);
                    target=enemy;
                    super.attack();
                }
            }
        }
        target=t;
    }
}
