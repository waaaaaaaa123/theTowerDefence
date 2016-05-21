package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;
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
    public static final int ABILITY_TOWER_COMBO=5;//////////////
    public static final int ABILITY_TOWER_HEAL=6;
    public static final int ABILITY_TOWER_IMPETUS=7;
    public static final int ABILITY_TOWER_POISON=8;
    public static final int ABILITY_TOWER_MOREATTACK=9;
    public static final int ABILITY_TOWER_MORESPEED=10;
    public static final int ABILITY_TOWER_MORERANGE=11;
    public static final int ABILITY_TOWER_SPLIT=12;
    public static final int ABILITY_TOWER_NECROMASTERY =13;
    public static final int ABILITY_TOWER_STATUSUP=14;
    public static final int ABILITY_TOWER_NETHERTOXIN =15;

    public static final int STATE_ATTACK=0;
    public static final int STATE_KILLED=1;
    public static final int STATE_LEARNED=2;
    public static final int STATE_FIRED=3;
    public static final int STATE_UPDATED=4;

    protected Tower caster;

    public TowerAbility(Tower caster){
        this.caster=caster;
    }

    public static TowerAbility create(int id,Tower caster){
        TowerAbility ability=null;
        switch (id){
            case ABILITY_TOWER_ARMORREDUCE:ability=new TowerAbilityArmorReduce(caster);break;
            case ABILITY_TOWER_POISON:ability=new TowerAbilityPoison(caster);break;
            case ABILITY_TOWER_IMPETUS:ability=new TowerAbilityImpetus(caster);break;
            case ABILITY_TOWER_HEAL:ability=new TowerAbilityHeal(caster);break;

            case ABILITY_TOWER_CRITICALSTRIKE:ability=new TowerAbilityCriticalStrike(caster);break;
            case ABILITY_TOWER_COMBO:ability=new TowerAbilityCombo(caster);break;

            case ABILITY_TOWER_GREED:ability=new TowerAbilityGreed(caster);break;
            case ABILITY_TOWER_MOREATTACK:ability=new TowerAbilityMoreAttack(caster);break;
            case ABILITY_TOWER_NETHERTOXIN:ability=new TowerAbilityNethertoxin(caster);break;
            case ABILITY_TOWER_SLOWDOWN:ability=new TowerAbilitySlowDown(caster);break;

            case ABILITY_TOWER_SPLIT:ability=new TowerAbilitySplit(caster);break;

            case ABILITY_TOWER_MORESPEED:ability=new TowerAbilityMoreSpeed(caster);break;
            case ABILITY_TOWER_NECROMASTERY:ability=new TowerAbilityNecromastery(caster);break;
            case ABILITY_TOWER_STATUSUP:ability=new TowerAbilityStatusUp(caster);break;
            case ABILITY_TOWER_STUN:ability=new TowerAbilityStun(caster);break;
        }
        return ability;
    }

}
