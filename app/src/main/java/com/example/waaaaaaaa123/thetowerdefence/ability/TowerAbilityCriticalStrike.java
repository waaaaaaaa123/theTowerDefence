package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/15.
 */
public class TowerAbilityCriticalStrike extends TowerAbility {
    private float chance=0.25f;
    private float multi=2;
    public TowerAbilityCriticalStrike(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_CRITICALSTRIKE,STATE_ATTACK, 0);
    }

    @Override
    protected boolean cast() {
        if(Player.getRandomSeed().nextFloat()<chance){
            caster.getProjectile().strike(multi);
            return true;
        }
        return false;
    }


}
