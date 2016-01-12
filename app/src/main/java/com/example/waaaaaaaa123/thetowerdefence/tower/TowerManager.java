package com.example.waaaaaaaa123.thetowerdefence.tower;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by aa081_000 on 2016/1/11.
 */
public class TowerManager implements Iterable<Tower>{
    private ArrayList<Tower> towers;
    public TowerManager(){
        towers=new ArrayList<Tower>();
    }
    public void addTower(Tower tower){
        towers.add(tower);
    }
    public void update(long dt){
        for(Tower tower:towers){
            tower.update(dt);
        }
    }
    @Override
    public Iterator<Tower> iterator() {
        return towers.iterator();
    }
}
