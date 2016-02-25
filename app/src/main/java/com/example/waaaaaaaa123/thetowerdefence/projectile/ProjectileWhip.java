package com.example.waaaaaaaa123.thetowerdefence.projectile;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/1/20.
 */
public class ProjectileWhip extends Projectile {
    private boolean whipOn=false;
    /*public ProjectileWhip(Tower caster) {
        super(caster,null);
        caster.getTarget();
    }*/

    public ProjectileWhip(Tower caster,Enemy target){
        super(caster,target);

    }
    @Override
    public void init() {
        init(PROJECTILE_WHIP, 4);
    }
/*
    @Override
    public void onHit() {
        //getTarget().attackLanded(this);
        if(getTarget()!=null)
        whipOn=true;
    }

    public boolean isWhipOn() {
        return whipOn;
    }

    public void hit(){
        getTarget().attackLanded(this);
        resetModifiers();
        whipOn=false;
    }*/

}
