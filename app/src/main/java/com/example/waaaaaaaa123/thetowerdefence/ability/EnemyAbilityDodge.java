package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/2/21.
 */
public class EnemyAbilityDodge extends EnemyAbility {
    private float dodge=0.1f;

    public EnemyAbilityDodge(Enemy caster) {
        super(caster);
    }

    @Override
    public void init() {
        init(ABILITY_ENEMY_DODGE, STATE_ATTACKLANDED, 0);
    }


    @Override
    protected boolean cast() {
        if(Player.getRandomSeed().nextFloat()<dodge){
            caster.setDodge();
            return true;
        }
        return false;
    }

}
