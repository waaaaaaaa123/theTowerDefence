package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/14.
 */
public class TowerAbilityNethertoxin extends TowerAbility {
    public TowerAbilityNethertoxin(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_NETHERTOXIN,STATE_ATTACK,0);
    }

    @Override
    protected boolean cast() {
        caster.getProjectile().strike(1+1-caster.getTarget().getHpPercent());
        return true;
    }
}
