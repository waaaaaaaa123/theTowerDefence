package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/14.
 */
public class TowerAbilityStatusUp extends TowerAbility {
    private float bonusAttack=30f;
    private float bonusSpeed=3f;
    private float bonusRange=3f;
    public TowerAbilityStatusUp(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_STATUSUP,STATE_LEARNED,0);
    }

    @Override
    protected boolean cast() {
        caster.attackUp(bonusAttack);
        caster.speedUp(bonusSpeed);
        caster.rangeUp(bonusRange);
        return true;
    }
}
