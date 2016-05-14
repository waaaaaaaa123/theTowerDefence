package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/2/21.
 */
public abstract class EnemyAbility extends Ability {
    public static final int ABILITY_ENEMY_DODGE=0;
    public static final int ABILITY_ENEMY_HEAL=1;

    public static final int STATE_ATTACKLANDED=0;
    public static final int STATE_READY=1;

    protected Enemy caster;

    public EnemyAbility(Enemy caster){
        this.caster=caster;
    }
}
