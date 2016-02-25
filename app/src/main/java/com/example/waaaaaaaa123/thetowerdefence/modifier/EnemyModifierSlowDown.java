package com.example.waaaaaaaa123.thetowerdefence.modifier;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/1/20.
 */
public class EnemyModifierSlowDown extends EnemyModifier {


    private static final float slow=0.75f;
    public EnemyModifierSlowDown(Enemy target, int stack) {
        super(target, stack);
    }

    @Override
    public void init() {
        init(MODIFIER_SLOWDOWN,0.5f);
    }


    @Override
    public void stackUp(int s) {
        if(s>=getStack()){
            super.stackUp(s);
            for(int i=getStack();i<s;i++){
                getTarget().setSpeed(getTarget().getSpeed()*slow);;
            }
            setStack(s);
        }
    }

    @Override
    public void onStart() {
        for (int i = 0; i <getStack(); i++) {
        getTarget().setSpeed(getTarget().getSpeed()*slow);
        }
    }

    @Override
    public void onEnd() {
        super.onEnd();
        for(int i=0;i<getStack();i++){
            getTarget().setSpeed(getTarget().getSpeed()/slow);
        }
    }
}
