package com.example.waaaaaaaa123.thetowerdefence.modifier;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/2/21.
 */
public class EnemyModifierDodge extends EnemyModifier {
    public EnemyModifierDodge(Enemy target, int stack) {
        super(target, stack);
    }

    @Override
    public void init() {
        init(MODIFIER_DODGE,Long.MAX_VALUE);
    }
}
