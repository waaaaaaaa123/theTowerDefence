package com.example.waaaaaaaa123.thetowerdefence.projectile;

import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by aa081_000 on 2016/1/11.
 */
public class ProjectileManager implements Iterable<Projectile> {
    private ArrayList<Projectile> projectiles;

    public ProjectileManager(){
        projectiles=new ArrayList<Projectile>();
    }
    public void addProjectile(int id,Tower caster,Enemy target){
        //Log.i("pmSize",projectiles.size()+"");
        for(Projectile p:projectiles){
        if(p.getId()==id&&p.getState()==Projectile.STATE_DEAD) {
            p.recycle(caster,target);
            return;
            }
        }
        switch (id){
            case 0:projectiles.add(new Projectile(caster,target));break;
        }
    }
    public void update(long dt){
        for(Projectile projectile:projectiles){
            switch (projectile.getState()){
                case Projectile.STATE_ALIVE:projectile.update(dt);break;
                //case Projectile.STATE_DEAD:projectile=null;break;
            }
        }

    }

    @Override
    public Iterator<Projectile> iterator() {
        return projectiles.iterator();
    }
}
