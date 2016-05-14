package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifier;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/15.
 */
public class TowerAbilityArmorReduce extends TowerAbility {


    public TowerAbilityArmorReduce(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_ARMORREDUCE,STATE_ATTACK,0);
    }

    @Override
    protected boolean cast() {
        caster.getProjectile().addModifier(EnemyModifier.MODIFIER_ARMORREDUCE);
        return true;
    }
}
