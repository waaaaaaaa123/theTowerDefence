package com.example.waaaaaaaa123.thetowerdefence.ability;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/2/21.
 */
public abstract class EnemyAbility extends Ability {
    public static final int ABILITY_ENEMY_DODGE=0;
    public static final int ABILITY_ENEMY_HEAL=1;
    public static final int ABILITY_ENEMY_ARMOR=2;
    public static final int ABILITY_ENEMY_FLY=3;

    public static final int STATE_ATTACKLANDED=0;
    public static final int STATE_READY=1;
    public static final int STATE_SPAWN=2;

    protected Enemy caster;

    public EnemyAbility(Enemy caster){
        this.caster=caster;
    }

    public static EnemyAbility create(int id,Enemy caster){
        EnemyAbility ability=null;
        switch (id){
            case ABILITY_ENEMY_DODGE:ability=new EnemyAbilityDodge(caster);break;
            case ABILITY_ENEMY_HEAL:ability=new EnemyAbilityHeal(caster);break;
            case ABILITY_ENEMY_ARMOR:ability=new EnemyAbilityArmor(caster);break;
            case ABILITY_ENEMY_FLY:ability=new EnemyAbilityFly(caster);break;
        }

        return ability;
    }
}
