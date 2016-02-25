package com.example.waaaaaaaa123.thetowerdefence.modifier;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/1/20.
 */
public class EnemyModifierStun extends EnemyModifier {
    public EnemyModifierStun(Enemy target, int stack) {
        super(target, stack);
    }

    @Override
    public void init() {
        init(MODIFIER_STUN, 0.25f);
    }

    @Override
    public void stackUp(int s) {

        if(s>=getStack()){
            super.stackUp(s);
            for(int i=getStack();i<s;i++)
                setDuration(getDuration()+0.1f);
            setStack(s);
        }
    }

    @Override
    public void onStart() {

    }


    
}
