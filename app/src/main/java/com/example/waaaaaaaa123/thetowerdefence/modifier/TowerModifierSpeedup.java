package com.example.waaaaaaaa123.thetowerdefence.modifier;

import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/5/14.
 */
public class TowerModifierSpeedup extends TowerModifier {
    private float bonus=0;
    public TowerModifierSpeedup(Tower target, int stack) {
        super(target, stack);
    }

    @Override
    public void recycle(Tower target, int stack) {
        super.recycle(target, stack);
        bonus=0;
    }

    @Override
    public void init() {
        init(MODIFIER_TOWER_SPEEDUP,10.0f);
    }

    @Override
    public void onStart() {
        bonus=target.getSpeed();
        target.speedUp(bonus);
    }

    @Override
    public void onEnd() {
        super.onEnd();
        target.speedUp(-bonus);
    }
}
