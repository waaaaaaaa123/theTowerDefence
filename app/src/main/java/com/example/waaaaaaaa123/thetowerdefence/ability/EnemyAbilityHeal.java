package com.example.waaaaaaaa123.thetowerdefence.ability;

import android.graphics.PointF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/2/21.
 */
public class EnemyAbilityHeal extends EnemyAbility {
    private float healpercent=0.1f;
    private float range=2;

    public EnemyAbilityHeal(Enemy caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_ENEMY_HEAL,STATE_READY,3);
    }

    private boolean isInRange(Enemy enemy){
        PointF p=enemy.getPoint();
        float r=enemy.getRect().width()/2+range*Grid.getLength();

        float dx=p.x-caster.getPoint().x;
        float dy=p.y-caster.getPoint().y;
        return dx*dx+dy*dy<=r*r;
    }


    @Override
    public boolean cast() {
        if(caster.getHpPercent()<1){
            caster.heal(healpercent);
            return true;
        }
        for (Enemy enemy : Player.getWave().getEnemies()) {
            if(enemy.getState()==Enemy.STATE_ALIVE&&isInRange(enemy)){
                if(enemy.getHpPercent()<1){
                    enemy.heal(healpercent);
                    return true;
                }
            }
        }
        return false;
    }
}
