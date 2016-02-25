package com.example.waaaaaaaa123.thetowerdefence.ability;

import android.graphics.RectF;

/**
 * Created by aa081_000 on 2016/2/14.
 */
public class AbilitySlot {
    private TowerAbility towerAbility;
    private RectF rect;

    public AbilitySlot(RectF rect){
        this.rect=rect;
        towerAbility =null;
    }

    public RectF getRect() {
        return rect;
    }

    public static TowerAbility CreateAbility(int id){
        TowerAbility ability=null;
        switch (id){
            case TowerAbility.ABILITY_TOWER_ARMORREDUCE:ability=new TowerAbilityArmorReduce();break;
            case TowerAbility.ABILITY_TOWER_CRITICALSTRIKE:ability=new TowerAbilityCriticalStrike();break;
            case TowerAbility.ABILITY_TOWER_SLOWDOWN:ability=new TowerAbilitySlowDown();break;
            case TowerAbility.ABILITY_TOWER_STUN:ability=new TowerAbilityStun();break;
            //case TowerAbility.ABILITY_TOWER_COMBO:ability=new TowerAbilityCombo();break;
            case TowerAbility.ABILITY_TOWER_GREED:ability=new TowerAbilityGreed();break;
            case TowerAbility.ABILITY_TOWER_HEAL:ability=new TowerAbilityHeal();break;
            case TowerAbility.ABILITY_TOWER_IMPETUS:ability=new TowerAbilityImpetus();break;
            case TowerAbility.ABILITY_TOWER_POISON:ability=new TowerAbilityPoison();break;
        }
        return ability;
    }
    public void setTowerAbility(TowerAbility towerAbility) {
        this.towerAbility = towerAbility;
    }

    public TowerAbility getTowerAbility() {
        return towerAbility;
    }
}
