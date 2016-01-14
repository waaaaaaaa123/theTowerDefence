package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileManager;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Tower {
    private int id=0;
    private int range=2;
    private int attackSpeed=3;
    private long attackTime=0;
    private int castPoint;
    private RectF rect;
    private PointF point;
    private Enemy target;
    private static Wave wave;
    private Projectile projectile;
    private static ProjectileManager projectileManager;
    public Tower(RectF rect){
        point=new PointF(rect.centerX(),rect.centerY());
        this.rect=new RectF(rect);

        setRange();
    }

    public static void setProjectileManager(ProjectileManager projectileManager) {
        Tower.projectileManager = projectileManager;
    }


    public int getId() {
        return id;
    }

    public void setRange() {
        range*= Grid.getLength();
    }

    public static void setWave(Wave wave) {
        Tower.wave = wave;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public PointF getPoint() {
        return point;
    }

    public RectF getRect() {
        return rect;
    }

    public boolean isInRange(Enemy enemy){
        float dx=point.x-enemy.getPoint().x;
        float dy=point.y-enemy.getPoint().y;
        if(range*range>=dx*dx+dy*dy)
            return true;
        else
        {
            target=null;
            return false;
        }
    }
    public void findTarget(){
        if(target==null||target.getState()==Enemy.STATE_DEAD||!isInRange(target)){
            for(Enemy enemy:wave){
                if(enemy.getState()==Enemy.STATE_ALIVE){
                    if(isInRange(enemy)){
                        target=enemy;
                        return;
                    }
                }
            }
            target=null;
        }
    }


    public void attack(){
    projectileManager.addProjectile(0,this,target);
}
    public void update(long dt){
        attackTime-=dt;
        findTarget();

        if(target==null) return;
        if(attackTime<=0){
            attack();
            attackTime+=1000/attackSpeed;
            if(attackTime<=0)
                update(0);
        }

    }

}
