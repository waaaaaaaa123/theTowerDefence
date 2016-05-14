package com.example.waaaaaaaa123.thetowerdefence.modifier;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/1/19.
 */
public abstract class EnemyModifier extends Modifier {
    public static final int MODIFIER_SLOWDOWN=1;
    public static final int MODIFIER_STUN=2;
    public static final int MODIFIER_ARMORREDUCE=3;
    public static final int MODIFIER_POISON =4;
    public static final int MODIFIER_DODGE=5;
    protected Enemy target;

    public EnemyModifier(Enemy target,int stack) {
        init();
        recycle(target,stack);
    }

    public void recycle(Enemy target,int stack) {
        this.target=target;
        super.recycle(stack);
    }

    public Enemy getTarget() {
        return target;
    }


}
