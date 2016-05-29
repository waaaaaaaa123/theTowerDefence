package com.example.waaaaaaaa123.thetowerdefence.modifier;

import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;

/**
 * Created by aa081_000 on 2016/2/21.
 */
public abstract class Modifier {

    private int id;

    protected boolean alive;
    protected float duration;
    //protected float restDuration;
    protected long timer;
    protected int stack;

    protected void recycle(int stack){
        this.stack=stack;
        timer=0;
        alive=true;
        onStart();
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    public abstract void init();

    public void init(int id,float duration){
        this.id=id;
        this.duration=duration;

    }


    public boolean isAlive() {
        return alive;
    }

    public int getId() {
        return id;
    }

    public float getDuration() {
        return duration;
    }
    public float getPercent(){
        return timer/(duration*1000);
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }


    public void stackUp(int s){

        if(s>=stack){
            timer=0;
            for(;stack<s;stack++){
                onStack();
            }

        }
    }

    public void onStack(){}
    public void onStart(){

    }
    public void update(long dt){
        timer+=dt;
        onDuring(dt);
        if(timer>=duration*1000){
            onEnd();
        }
    }
    public void onDuring(long dt){

    }
    public void onEnd(){
        alive=false;
    }
}
