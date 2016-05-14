package com.example.waaaaaaaa123.thetowerdefence.projectile;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

import java.util.HashSet;

/**
 * Created by aa081_000 on 2016/2/23.
 */
public class ProjectileChain extends Projectile {
    private int stack=5;
    private HashSet<Enemy> hits;
    public ProjectileChain(Tower caster, Enemy target) {
        super(caster, target);
        hits=new HashSet<>(Player.getWave().getEnemies().size());
        hits.add(target);
    }

    @Override
    public void init() {
        init(PROJECTILE_CHAIN, 5);
    }

    @Override
    public void recycle(Tower caster, Enemy target) {
        super.recycle(caster, target);
        stack=3;
        if(hits!=null){
            hits.clear();
            hits.add(target);
        }
    }

    private float calDistance(Enemy enemy){
        float dx=enemy.getPoint().x-getRect().centerX();
        float dy=enemy.getPoint().y-getRect().centerY();
        return (float) Math.sqrt(dx*dx+dy*dy);
    }
    private Enemy searchEnemy(){
        float dmin=Float.MAX_VALUE;
        float d;
        Enemy res=null;
        for (Enemy enemy : Player.getWave().getEnemies()) {
            if(enemy.getState()==Enemy.STATE_ALIVE&&!hits.contains(enemy)){
                d=calDistance(enemy);
                if(d<dmin){
                    res=enemy;
                    dmin=d;
                }
            }
        }
        if(dmin>caster.getRange()* Grid.getLength())
            return null;
        else
            return res;
    }

    @Override
    public void onHit() {
        //getPoint().set(targetPoint);
        target.attackLanded(this);
        stack--;
        if(stack>=0){
            Enemy enemy=searchEnemy();
            if(enemy==null){
                setState(STATE_DEAD);
            }
            else{
                hits.add(enemy);
                target=enemy;
            }
        }
        else{
            setState(STATE_DEAD);
        }
    }
}
