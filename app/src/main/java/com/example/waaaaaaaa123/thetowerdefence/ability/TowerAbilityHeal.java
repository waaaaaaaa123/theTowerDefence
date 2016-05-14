package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/24.
 */
public class TowerAbilityHeal extends TowerAbility{
    private int count=0;

    public TowerAbilityHeal(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_HEAL,STATE_KILLED,0);
    }

    @Override
    public boolean cast() {
        count++;
        if(count>=5){
            Player.addHp(1);
            count=0;
        }
        return true;
    }
}
