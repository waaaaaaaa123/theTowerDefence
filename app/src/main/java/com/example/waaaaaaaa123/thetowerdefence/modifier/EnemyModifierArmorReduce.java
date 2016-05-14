package com.example.waaaaaaaa123.thetowerdefence.modifier;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/1/24.
 */
public class EnemyModifierArmorReduce extends EnemyModifier {
    private static final float reduce=1;
    public EnemyModifierArmorReduce(Enemy target, int stack) {
        super(target, stack);
    }

    @Override
    public void init() {
        init(MODIFIER_ARMORREDUCE,2);
    }

    @Override
    public void stackUp(int s) {
        duration=restDuration;
        getTarget().setArmor( getTarget().getArmor()-s*reduce);
        setStack(getStack()+s);
    }

    @Override
    public void onStack() {

    }

    @Override
    public void onStart() {
       getTarget().setArmor( getTarget().getArmor()-getStack()*reduce);
    }

    @Override
    public void onEnd() {
        super.onEnd();
        getTarget().setArmor(getTarget().getArmor()+getStack()*reduce);

    }
}
