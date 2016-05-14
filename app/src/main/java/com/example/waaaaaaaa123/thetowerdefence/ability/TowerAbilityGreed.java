package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/24.
 */
public class TowerAbilityGreed extends TowerAbility {
    private float bonus=0.2f;
    public TowerAbilityGreed(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_GREED,STATE_KILLED,0);
    }

    @Override
    protected boolean cast() {
        Player.getShop().earnGold((int) (caster.getLastKilled().getDp()*bonus));
        return true;
    }
}
