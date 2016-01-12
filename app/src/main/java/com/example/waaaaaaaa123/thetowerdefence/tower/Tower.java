package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.Point;
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
    private int attackSpeed=5;
    private long attackTime=0;
    private int castPoint;
    private Point point;
    private Enemy target;
    private static Wave wave;
    private Projectile projectile;
    private static ProjectileManager projectileManager;
    public Tower(Block block){
        point=new Point(block.getPoint());
        setRange();
    }

    public static void setProjectileManager(ProjectileManager projectileManager) {
        Tower.projectileManager = projectileManager;
    }

    public void setPoint(Point point) {
        this.point = point;
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

    public Point getPoint() {
        return point;
    }

    public boolean isInRange(Enemy enemy){
        int dx=point.x-enemy.getPoint().x;
        int dy=point.y-enemy.getPoint().y;
        if(range*range>=dx*dx+dy*dy)
            return true;
        else
        {
            target=null;
            return false;
        }
    }
    public void findTarget(){
        if(target==null||!isInRange(target)){
            for(Enemy enemy:wave){
                if(enemy.getState()==Enemy.STATE_ALIVE){
                    if(isInRange(enemy)){
                        target=enemy;
                        return;
                    }
                }
            }
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
