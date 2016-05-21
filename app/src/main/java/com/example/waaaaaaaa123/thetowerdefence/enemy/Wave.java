package com.example.waaaaaaaa123.thetowerdefence.enemy;

import android.graphics.PointF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.dialog.Dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Wave implements Iterable<Enemy> {
    private int id=-1;
    private ArrayList<Enemy> enemies;
    private ArrayList<ArrayList<Block>> path;
    private int totalDp=20,minDp=2,minHplvl=1;
    private float spawnSpeed=2;
    private int dpCount,hpCount;
    private long lastSpawnTime=0;
    public Wave(int id){
        this.id=id;
        enemies=new ArrayList<Enemy>();
        path=new ArrayList<>();
        Player.getGrid().setPath();

    }


    public void init(){
        path.add(null);
        path.add(Player.getGrid().buildPath(1));
        path.add(Player.getGrid().buildPath(2));
        path.add(Player.getGrid().buildPath(3));

    }

    public void setPath() {
        for (int i = 1; i < path.size(); i++) {
            path.set(i, Player.getGrid().buildPath(i));
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
        int dead=0;
        for(Enemy enemy:enemies){

            switch (enemy.getState()){
                case Enemy.STATE_ALIVE:enemy.update(dt);break;
                case Enemy.STATE_DEAD:dead++;break;
            }
        }
        if(dead==enemies.size()){
            Reward reward=new Reward(id);
            Player.setState(Player.STATE_PREPARE);
            //Player.getDialogs().add(new Dialog());
        }
        Collections.sort(enemies);
    }
    //smax=10,hmax=10,lmax=4
    private   int s,h,a,l;
    public void nextWave(){

        enemies.clear();
        id++;

        spawnSpeed= id%3==0&&spawnSpeed<5?spawnSpeed+0.3f:spawnSpeed;

        s=id%5==0&&s<10?s+1:s;
        h=id%2==0&&h<10?h+1:h;
        a=id%5-1;
        l=id%10==0&&l<4?l+1:l;

        for (int i = 0; i < id%10*10; i++) {


            Enemy enemy=new Enemy();
            if(i%10<=3){
                enemy.init(s,h,a,l);
            }
            else
                enemy.init(s,h,-1,l);
            enemy.setPath(path.get(enemy.getBound()));
            enemies.add(enemy);
        }

    }
    /*
    public void nextWave1(){
        enemies.clear();

        totalDp*=1.5;
        spawnSpeed=Player.getRandomSeed().nextInt(3)+1;
        if(totalDp/minDp/spawnSpeed>20){

            minDp=totalDp/(spawnSpeed*20);
        }
        hpCount++;
        if(minHplvl<10&&hpCount>3){
            minHplvl++;
            hpCount=0;
        }
        int restDp=totalDp;
        enemies.clear();
        while(restDp>0){
            Enemy enemy=new Enemy();
            restDp-=enemy.init(minHplvl,minDp);

            enemy.setPath(path.get(enemy.getBound()));
            enemies.add(enemy);

        }

    }*/

    public void addEnemy(Enemy e){
        e.setPath(path.get(e.getBound()));
        enemies.add(e);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public Iterator<Enemy> iterator() {
        return enemies.iterator();
    }
}
