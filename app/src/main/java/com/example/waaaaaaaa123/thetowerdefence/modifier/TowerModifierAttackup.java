package com.example.waaaaaaaa123.thetowerdefence.modifier;

import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/5/3.
 */
public class TowerModifierAttackup extends TowerModifier {
    private float bonus;


    public TowerModifierAttackup(Tower target, int stack) {
        super(target, stack);
    }

    @Override
    public void recycle(Tower target, int stack) {
        bonus=0;
        super.recycle(target, stack);
        Log.i("rec",bonus+"bonus");
    }

    @Override
    public void init() {
        init(MODIFIER_TOWER_ATTACKUP, 10.0f);
    }


    @Override
    public void onStart() {
        bonus=(target.getAttackMin()+target.getAttackMax())/2.0f;
        target.attackUp(bonus);
    }


    @Override
    public void onEnd() {
        Log.i("end",bonus+"bonus");
        target.attackUp(-bonus);
        super.onEnd();
    }
}
