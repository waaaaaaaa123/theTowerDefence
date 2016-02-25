package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/1/19.
 */
public abstract class TowerAbility extends Ability {

    public static final int ABILITY_TOWER_CRITICALSTRIKE =0;
    public static final int ABILITY_TOWER_SLOWDOWN =1;
    public static final int ABILITY_TOWER_ARMORREDUCE =2;
    public static final int ABILITY_TOWER_STUN =3;
    public static final int ABILITY_TOWER_GREED=4;
    public static final int ABILITY_TOWER_COMBO=5;
    public static final int ABILITY_TOWER_HEAL=6;
    public static final int ABILITY_TOWER_IMPETUS=7;
    public static final int ABILITY_TOWER_POISON=8;

    protected Tower caster;

    public void setCaster(Tower caster) {
        this.caster = caster;
    }
}
