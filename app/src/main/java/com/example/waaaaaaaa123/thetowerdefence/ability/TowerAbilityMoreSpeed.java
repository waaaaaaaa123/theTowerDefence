package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/14.
 */
public class TowerAbilityMoreSpeed extends TowerAbility {
    private Enemy target=null;
    private float perBonus=0.1f;
    private float totalBonus=0;
    public TowerAbilityMoreSpeed(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_MORESPEED,STATE_ATTACK,0);
    }

    @Override
    protected boolean cast() {
        if(caster.getTarget()!=null&&caster.getTarget()==target){
            caster.speedUp(perBonus);
            totalBonus+=perBonus;
        }
        else{
            caster.speedUp(-totalBonus/2);
            totalBonus=0/2;
        }
        return true;

    }
}
