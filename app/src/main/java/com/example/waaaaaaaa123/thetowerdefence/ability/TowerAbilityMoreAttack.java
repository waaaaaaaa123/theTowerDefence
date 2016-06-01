package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/13.
 */
public class TowerAbilityMoreAttack extends TowerAbility {
    private float perBonus=5;
    private Enemy target=null;
    private float totalBonus=0;

    public TowerAbilityMoreAttack(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_MOREATTACK,STATE_FIRED,0);
    }

    @Override
    protected boolean cast() {
        if(target==null){
            target=caster.getTarget();

        }
        if(target!=null&&target==caster.getTarget()){
            caster.attackUp(perBonus);
            totalBonus+=perBonus;

        }
        else{

            caster.attackUp(-totalBonus);
            totalBonus=0;
            target=caster.getTarget();
        }

        return true;
    }
}
