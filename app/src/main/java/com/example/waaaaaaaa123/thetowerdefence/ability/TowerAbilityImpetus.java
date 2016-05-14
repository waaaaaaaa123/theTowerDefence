package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/24.
 */
public class TowerAbilityImpetus extends TowerAbility {
    public TowerAbilityImpetus(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_IMPETUS,STATE_ATTACK,0);
    }

    @Override
    protected boolean cast() {
        caster.getProjectile().addImpetus();
        return false;
    }
}
