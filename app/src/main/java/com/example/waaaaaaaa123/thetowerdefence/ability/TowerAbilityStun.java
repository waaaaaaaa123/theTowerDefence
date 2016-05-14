package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.modifier.EnemyModifier;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/15.
 */
public class TowerAbilityStun extends TowerAbility {
    private float chance=0.25f;
    public TowerAbilityStun(Tower caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_TOWER_STUN,STATE_ATTACK,0.5f);
    }

    @Override
    protected boolean cast() {
        if(Player.getRandomSeed().nextFloat()<chance){
            caster.getProjectile().addModifier(EnemyModifier.MODIFIER_STUN);
            return true;
        }
        return false;
    }
}
