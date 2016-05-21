package com.example.waaaaaaaa123.thetowerdefence.enemy;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.item.Bag;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public class Reward {
    public static final int table[][]={};
    public Reward(int id){
        Bag bag=Player.getBag();
        int i=0,n=0;

        for (int j = 0; j < id % 6; j++) {
            if(id%2==0)
                i=Player.getRandomSeed().nextInt(32-18)+18;
            else
                i=Player.getRandomSeed().nextInt(17-1)+1;
            n=Player.getRandomSeed().nextInt(3)+1;
            if(!bag.addItem(i,n))
                bag.buy(-n*100);
        }
        if(id%10==0)
            bag.addItem(Item.ITEM_CHECKBLOCK,1);
        bag.addItem(Item.ITEM_BUILDBLOCK,id/10+5);
        bag.buy(-id*300);
    }
}
