package com.example.waaaaaaaa123.thetowerdefence.modifier;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/1/19.
 */
public abstract class EnemyModifier extends Modifier {
    public static final int MODIFIER_SLOWDOWN=1;
    public static final int MODIFIER_STUN=2;
    public static final int MODIFIER_ARMORREDUCE=3;
    public static final int MODIFIER_POSION=4;
    public static final int MODIFIER_DODGE=5;

    private int id;

    private boolean alive;
    private float duration;
    private float restDuration;
    protected int stack;


    protected Enemy target;

    public EnemyModifier(Enemy target, int stack){
        init();
        recycle(target, stack);
    }
    public abstract void init();

    public void init(int id,float duration){
        this.id=id;
        this.duration=duration;


    }

    public void recycle(Enemy target,int stack){
        this.target = target;
        this.stack=stack;
        restDuration=duration;
        alive=true;
        onStart();
    }
    public boolean isAlive() {
        return alive;
    }

    public int getId() {
        return id;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Enemy getTarget() {
        return target;
    }

    public void stackUp(int s){

        restDuration=duration;

    }
    public void onStart(){

    }
    public void update(long dt){
        restDuration-=dt/1000;
        onDuring(dt);
        if(restDuration<=0){
            onEnd();
        }
    }
    public void onDuring(long dt){

    }
    public void onEnd(){
        alive=false;
    }
}
