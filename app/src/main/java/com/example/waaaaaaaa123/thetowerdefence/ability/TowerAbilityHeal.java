package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.Player;

/**
 * Created by aa081_000 on 2016/2/24.
 */
public class TowerAbilityHeal extends TowerAbility{
    private int count=0;
    @Override
    public void init() {
        init(ABILITY_TOWER_HEAL,0);
    }

    @Override
    public void cast() {
        super.cast();
        count++;
        if(count>=5){
            Player.addHp(1);
            count=0;
        }

    }
}
