package com.example.waaaaaaaa123.thetowerdefence;

import android.graphics.Rect;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.item.Item;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by aa081_000 on 2016/1/12.
 */
public class Player implements Iterable<Item>{
    private int gold=625;
    private int hp=100;
    private ArrayList<Item> bag;
    private RectF topRect,bottomRect;
    private float offset=0;
    private float bagHeight;
    public Player(RectF topRect,RectF bottomRect){
        bag=new ArrayList<>();
        this.topRect=topRect;
        this.bottomRect=bottomRect;
        bagHeight=Math.min(bottomRect.height(), bottomRect.width() / 6);
    }


    public void addItem(Item item){
        item.setRect(bottomRect.left+bag.size()*bagHeight,bottomRect.top,bottomRect.left+(bag.size()+1)*bagHeight,bottomRect.top+bagHeight);
        bag.add(item);
    }
    public void removeItem(Item item){
        bag.remove(item);
    }

    public void scrollBag(float x){
        for(Item  item:bag){
            item.rectOffset(x,0);
        }
    }
    public Item sellectItem(float x,float y){
        for(Item item:bag){
            if(item.getRect().contains(x,y))
                return item;
        }
        return null;
    }
    public int getGold() {
        return gold;
    }

    public int getHp() {
        return hp;
    }

    public RectF getTopRect() {
        return topRect;
    }

    public RectF getBottomRect() {
        return bottomRect;
    }

    @Override
    public Iterator<Item> iterator() {
        return bag.iterator();
    }
}
