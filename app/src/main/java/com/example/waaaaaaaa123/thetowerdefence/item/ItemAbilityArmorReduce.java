package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public class ItemAbilityArmorReduce extends ItemAbility {
    @Override
    public void init() {
        aId= TowerAbility.ABILITY_TOWER_ARMORREDUCE;
        init(ITEM_ABILITY_ARMORREDUCE,0,1000);
    }
}
