package com.example.waaaaaaaa123.thetowerdefence.tower;

import android.graphics.Rect;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
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

    public TowerManager(){
        towers=new ArrayList<Tower>();
    }


    public void addTower(int id,RectF rect){
        Tower tower=null;
        switch (id){
            case Tower.TOWER_AXE:tower=new AxeTower(rect);break;
            case Tower.TOWER_WHIP:tower=new WhipTower(rect);break;
            case Tower.TOWER_SWORD:tower=new SwordTower(rect);break;
            case Tower.TOWER_BOMB:tower=new BombTower(rect);break;
            case Tower.TOWER_CHAIN:tower=new ChainTower(rect);break;
            case Tower.TOWER_SPLIT:tower=new SplitTower(rect);break;
            case Tower.TOWER_CONE:tower=new ConeTower(rect);break;
        }
        Player.getGrid().getBlock(rect.centerX(), rect.centerY()).setId(Block.TOWER);
        towers.add(tower);

    }
    public Tower addTower(Block block,int id){
        Tower tower=new Tower(block);
        switch (id){
            case Tower.TOWER_ORB:break;//id==0 means it's an orb tower.
            case Tower.TOWER_CONE:tower=new ConeTower(block);break;
        }
        block.setId(Block.TOWER);
        towers.add(tower);
        return tower;
    }
    public void removeTower(Block block){
        towers.remove(getTower(block));
        block.setId(Block.BASE);
    }

    public Tower getTower(Block block){
        for (Tower tower : towers) {
            if(tower.getRect().contains(block.getCenter().x,block.getCenter().y))
                return tower;
        }
        return null;
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
