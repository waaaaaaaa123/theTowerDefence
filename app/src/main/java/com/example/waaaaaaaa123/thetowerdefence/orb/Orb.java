package com.example.waaaaaaaa123.thetowerdefence.orb;

/**
 * Created by aa081_000 on 2016/5/5.
 */
public class Orb {
    public static final int ORB_RED=0;
    public static final int ORB_GREEN=1;
    public static final int ORB_YELLOW=2;
    public static final int ORB_BLUE=3;

    private static final float[][] table={{100,150,75,50},{50,100,175,50},{120,80,100,75},{100,100,100,100}};

    public static float calD(int towerOrb,int enemyOrb){
        if(enemyOrb==-1)//no main orb
            return 1;
        return table[towerOrb][enemyOrb]/100;
    }
}
