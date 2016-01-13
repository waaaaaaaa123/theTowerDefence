package com.example.waaaaaaaa123.thetowerdefence.enemy;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Wave implements Iterable<Enemy> {
    private int id;
    private ArrayList<Enemy> enemies;
    private ArrayList<PointF> path;
    private int spawnSpeed=1;
    private long lastSpawnTime=0;
    private int spawnCount=0;
    private static Grid grid;
    public Wave(int id){
        this.id=id;
        enemies=new ArrayList<Enemy>();

    }

    public static void setGrid(Grid grid) {
        Wave.grid = grid;

    }

    public void init(){
        switch (id){
            case 0:path=grid.buildPath(0,0,8,5,1);break;
        }
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

    public ArrayList<PointF> getPath() {
        return path;
    }

    public void addEnemy(int num){

        for(int i=0;i<num;i++){
            Enemy e=new Enemy();
            e.setSpeed(grid.getLength());
            e.setPath(path);
            enemies.add(e);
        }


    }

    @Override
    public Iterator<Enemy> iterator() {
        return enemies.iterator();
    }
}
