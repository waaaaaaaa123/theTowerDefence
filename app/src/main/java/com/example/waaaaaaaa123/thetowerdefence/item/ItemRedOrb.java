package com.example.waaaaaaaa123.thetowerdefence.item;

import com.example.waaaaaaaa123.thetowerdefence.orb.Orb;

/**
 * Created by aa081_000 on 2016/5/20.
 */
public class ItemRedOrb extends ItemBuildOrb {
    @Override
    public void init() {
        orbId= Orb.ORB_RED;
        init(ITEM_REDORB,2,100);
    }
}
