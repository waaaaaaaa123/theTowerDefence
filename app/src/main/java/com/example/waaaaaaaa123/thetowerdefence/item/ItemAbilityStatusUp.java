package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;
import com.example.waaaaaaaa123.thetowerdefence.item.ItemAbility;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public class ItemAbilityStatusUp extends ItemAbility {
    @Override
    public void init() {
        aId= TowerAbility.ABILITY_TOWER_STATUSUP;
        init(ITEM_ABILITY_STATUSUP,0,1000);
    }
}
