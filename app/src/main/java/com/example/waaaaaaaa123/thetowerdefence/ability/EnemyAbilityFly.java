package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public class EnemyAbilityFly extends EnemyAbility {
    public EnemyAbilityFly(Enemy caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(EnemyAbility.ABILITY_ENEMY_FLY,STATE_SPAWN,0);
    }

    @Override
    protected boolean cast() {
        caster.setBound(3);
        return true;
    }
}
