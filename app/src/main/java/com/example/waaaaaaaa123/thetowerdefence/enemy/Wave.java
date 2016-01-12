package com.example.waaaaaaaa123.thetowerdefence.enemy;

import android.graphics.Point;
import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Wave implements Iterable<Enemy> {
    private int id;
    private ArrayList<Enemy> enemies;
    private ArrayList<Point> path;
    private int spawnSpeed=1;
    private long lastSpawnTime=0;
    private int spawnCount=0;
    public Wave(){
        enemies=new ArrayList<Enemy>();
    }

    public void init(){

    }
    public void update(long dt){

            lastSpawnTime -= dt ;
            if (lastSpawnTime <= 0) {
                //enemies.get(spawnCount++).setState(Enemy.STATE_ALIVE);
                for(Enemy enemy:enemies){
                    if(enemy.getState()==Enemy.STATE_NOTSPAWN)
                    {
                        enemy.setState(Enemy.STATE_ALIVE);
                        break;
                    }
                }
                lastSpawnTime += 1000/spawnSpeed;
                if (lastSpawnTime <= 0)
                    update(0);

            }

        for(Enemy enemy:enemies){
            if(enemy.getState()==Enemy.STATE_ALIVE){
                enemy.update(dt);
            }
        }

    }
    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }


    public void addEnemy(Enemy e){
        enemies.add(e);
    }

    @Override
    public Iterator<Enemy> iterator() {
        return enemies.iterator();
    }
}
