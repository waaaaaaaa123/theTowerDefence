package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public class ItemAbilityHeal extends ItemAbility {
    @Override
    public void init() {
        aId= TowerAbility.ABILITY_TOWER_HEAL;
        init(ITEM_ABILITY_HEAL,0,500);
    }
}
