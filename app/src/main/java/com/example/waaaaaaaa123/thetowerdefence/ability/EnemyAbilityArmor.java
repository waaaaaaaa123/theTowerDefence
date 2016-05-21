package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public class EnemyAbilityArmor extends EnemyAbility {
    float armor=10.0f;
    public EnemyAbilityArmor(Enemy caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_ENEMY_ARMOR,STATE_SPAWN,0);
    }

    @Override
    protected boolean cast() {
        caster.setArmor(caster.getArmor()+armor);
        return true;
    }
}
