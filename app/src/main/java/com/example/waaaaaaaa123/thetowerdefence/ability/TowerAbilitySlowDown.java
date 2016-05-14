package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifier;
import com.example.waaaaaaaa123.thetowerdefence.modifier.Modifier;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/15.
 */
public class TowerAbilitySlowDown extends TowerAbility {
    public TowerAbilitySlowDown(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_SLOWDOWN,STATE_ATTACK,0);
    }

    @Override
    protected boolean cast() {
        caster.getProjectile().addModifier(EnemyModifier.MODIFIER_SLOWDOWN);
        return true;
    }
}
