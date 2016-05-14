package com.example.waaaaaaaa123.thetowerdefence.projectile;

import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by aa081_000 on 2016/1/11.
 */
public class ProjectileManager implements Iterable<Projectile> {
    private ArrayList<Projectile> projectiles;

    public ProjectileManager(){
        projectiles=new ArrayList<Projectile>(500);
    }
    public Projectile addProjectile(int id,Tower caster,Enemy target){
        //Log.i("pmSize",projectiles.size()+"");
        for(Projectile p:projectiles){
        if(p.getId()==id&&p.getState()==Projectile.STATE_DEAD) {
            p.recycle(caster,target);
            return p;
            }
        }
        Projectile p=null;
        switch (id){
            case Projectile.PROJECTILE_AXE:p=new ProjectileAxe(caster, target);break;
            case Projectile.PROJECTILE_WHIP:p=new ProjectileWhip(caster,target);break;
            case Projectile.PROJECTILE_SWORD:p=new ProjectileSword(caster, target);break;
            case Projectile.PROJECTILE_BOMB:p=new ProjectileBomb(caster,target);break;
            case Projectile.PROJECTILE_CHAIN:p=new ProjectileChain(caster,target);break;
            case Projectile.PROJECTILE_CONE:p=new ProjectileCone(caster, target);break;
            case Projectile.PROJECTILE_SPLIT:p=new ProjectileSplit(caster, target);break;
            case Projectile.PROJECTILE_BURN:p=new ProjectileBurn(caster, target);break;
            case Projectile.PROJECTILE_ROTATE:p=new ProjectileRotate(caster,target);break;
            case Projectile.PROJECTILE_RIFLE:p=new ProjectileRifle(caster, target);break;
            case Projectile.PROJECTILE_COMBO:p=new ProjectileCombo(caster,target);break;
        }
        if(p!=null)
        projectiles.add(p);
        return p;
    }
    public void update(long dt){
        for(Projectile projectile:projectiles){
            switch (projectile.getState()){
                case Projectile.STATE_ALIVE:projectile.update(dt);break;
                //case Projectile.STATE_DEAD:projectile=null;break;
            }
        }
        Collections.sort(projectiles);

    }

    @Override
    public Iterator<Projectile> iterator() {
        return projectiles.iterator();
    }
}
