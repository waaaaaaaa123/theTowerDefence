package com.example.waaaaaaaa123.thetowerdefence.modifier;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

/**
 * Created by aa081_000 on 2016/2/24.
 */
public class EnemyModifierPoison extends EnemyModifier {
    private float damagepercent=0.05f;
    private Tower caster;
    public EnemyModifierPoison(Enemy target, int stack) {
        super(target, stack);
    }

    @Override
    public void recycle(Enemy target, int stack) {
        super.recycle(target, stack);
        caster=null;
    }

    @Override
    public void init() {
        init(MODIFIER_POSION,3);
    }

    public void setCaster(Tower caster) {
        this.caster = caster;
    }

    @Override
    public void stackUp(int s) {
        if(s>=getStack()){
            super.stackUp(s);
            for(int i=getStack();i<s;i++){
                damagepercent+=0.05f;
            }
            setStack(s);
        }
    }

    @Override
    public void onDuring(long dt) {
        int type=Tower.TYPE_MAGICAL;
        if(caster!=null)
            type=caster.getType();
        float d=damagepercent*target.getHp()*dt/1000;
        target.calDamage(type,d,caster);
    }
}