package com.example.waaaaaaaa123.thetowerdefence.orb;

import com.example.waaaaaaaa123.thetowerdefence.ability.TowerAbility;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/5/5.
 */
public class Orb {
    public static final int ORB_RED=0;
    public static final int ORB_GREEN=1;
    public static final int ORB_YELLOW=2;
    public static final int ORB_BLUE=3;

    public static final float[][] damage ={{100,150,75,50},{50,100,175,50},{120,80,100,75},{100,100,100,100}};
    public static final int[][] tower= {
            {0,0,0, Tower.TOWER_AXE},
            {0,0,1,Tower.TOWER_CHAIN},
            {0,0,2,Tower.TOWER_BOMB},

            {1,1,1,Tower.TOWER_WHIP},
            {0,1,1,Tower.TOWER_RIFLE},
            {1,1,2,Tower.TOWER_CONE},

            {2,2,2,Tower.TOWER_SWORD},
            {0,2,2,Tower.TOWER_COMBO},
            {1,2,2,Tower.TOWER_SPLIT},

            {0,1,2,Tower.TOWER_RANDOM},
    };
    public static final int[][] ability={
            {0,0,0,0, TowerAbility.ABILITY_TOWER_CRITICALSTRIKE},
            {0,0,0,1,TowerAbility.ABILITY_TOWER_SLOWDOWN},
            {0,0,0,2,TowerAbility.ABILITY_TOWER_ARMORREDUCE},
            {0,0,1,2,TowerAbility.ABILITY_TOWER_STUN},

            {0,0,1,1,TowerAbility.ABILITY_TOWER_GREED},
            {0,0,2,2,TowerAbility.ABILITY_TOWER_COMBO},

            {0,1,1,1,TowerAbility.ABILITY_TOWER_HEAL},
            {0,1,1,2,TowerAbility.ABILITY_TOWER_IMPETUS},
            {1,1,1,1,TowerAbility.ABILITY_TOWER_POISON},
            {1,1,1,2,TowerAbility.ABILITY_TOWER_MOREATTACK},

            {1,1,2,2,TowerAbility.ABILITY_TOWER_MORESPEED},

            {0,1,2,2,TowerAbility.ABILITY_TOWER_SPLIT},
            {0,2,2,2,TowerAbility.ABILITY_TOWER_NECROMASTERY},
            {1,2,2,2,TowerAbility.ABILITY_TOWER_STATUSUP},
            {2,2,2,2,TowerAbility.ABILITY_TOWER_NETHERTOXIN},
    };

    public static float damage(int towerOrb, int enemyOrb){
        if(enemyOrb==-1)//no main orb
            return 1;
        return damage[towerOrb][enemyOrb]/100;
    }

    public static final int levelUp(ArrayList<Integer> orbs){
        int[][] t=null;
        switch (orbs.size()){
            case 3:t=tower;break;
            case 4:t=ability;break;
            default:return -1;
        }
        for (int[] ts : t) {
            int i=0;
            for (i = 0; i < orbs.size(); i++) {
                if(orbs.get(i)!=ts[i]){
                    break;
                }
            }
            if(i==orbs.size()){
                return ts[i];
            }
        }
        return -1;
    }
}
