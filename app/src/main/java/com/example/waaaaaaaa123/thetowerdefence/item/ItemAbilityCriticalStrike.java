package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public class ItemAbilityCriticalStrike extends ItemAbility {
    @Override
    public void init() {
        aId= TowerAbility.ABILITY_TOWER_CRITICALSTRIKE;
        init(ITEM_ABILITY_CRITICALSTRIKE,0,1000);
    }
}
