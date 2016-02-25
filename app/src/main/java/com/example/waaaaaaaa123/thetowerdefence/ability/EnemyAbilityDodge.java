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
        init(ABILITY_ENEMY_DODGE,0);
    }

    @Override
    public boolean ruok(int s) {
        if(s== Enemy.STATUS_ATTACKLANDED){
            if(Player.getRandomSeed().nextFloat()<dodge)
                return true;
        }
        return false;
    }
}
