package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/14.
 */
public class TowerAbilityNecromastery extends TowerAbility {
    private float bonus=0.5f;
    public TowerAbilityNecromastery(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_Necromastery,STATE_KILLED,0);
    }

    @Override
    protected boolean cast() {
        caster.attackUp(bonus);
        return true;
    }
}
