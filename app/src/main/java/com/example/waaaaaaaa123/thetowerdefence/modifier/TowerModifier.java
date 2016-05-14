package com.example.waaaaaaaa123.thetowerdefence.modifier;

import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/21.
 */
public abstract class TowerModifier extends Modifier {
    public static final int MODIFIER_TOWER_ATTACKUP=0;
    public static final int MODIFIER_TOWER_SPEEDUP=1;
    public static final int MODIFIER_TOWER_RANGEUP=2;

    protected Tower target;

    public TowerModifier(Tower target,int stack){
        init();
        recycle(target,stack);
    }

    public void recycle(Tower target,int stack) {
        this.target=target;
        super.recycle(stack);
    }
}
