package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/24.
 */
public class TowerAbilityCombo extends TowerAbility {
    private int bonus=0;
    public TowerAbilityCombo(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_COMBO,STATE_FIRED,3.0f);
    }

    @Override
    public void update(long dt) {
        super.update(dt);
        if(isCd()&&bonus==0){
            bonus=2;
            caster.combo(bonus);
        }
    }

    @Override
    protected boolean cast() {
        if(bonus>0){
            caster.combo(-bonus);
            bonus=0;
            return true;
        }
        return false;

    }

}
