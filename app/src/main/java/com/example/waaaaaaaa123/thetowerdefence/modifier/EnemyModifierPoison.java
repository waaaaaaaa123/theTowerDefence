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
        init(MODIFIER_POISON,3);
    }

    public void setCaster(Tower caster) {
        this.caster = caster;
    }

    @Override
    public void onStack() {
        damagepercent+=0.05f;
    }

    @Override
    public void onDuring(long dt) {
        int orb=caster.getMainOrb();
        float d=damagepercent*target.getHp()*dt/1000;
        target.calDamage(orb,d,caster);
    }
}
