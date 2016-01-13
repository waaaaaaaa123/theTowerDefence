package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.Rect;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by aa081_000 on 2016/1/11.
 */
public class TowerManager implements Iterable<Tower>{
    private ArrayList<Tower> towers;
    private  Grid grid;
    public TowerManager(){
        towers=new ArrayList<Tower>();
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void addTower(RectF rect){
        Tower tower=new Tower(rect);
        grid.getBlock(rect.centerX(),rect.centerY()).setId(Block.TOWER);
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
