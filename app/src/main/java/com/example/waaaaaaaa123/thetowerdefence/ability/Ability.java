package com.example.waaaaaaaa123.thetowerdefence.ability;

/**
 * Created by aa081_000 on 2016/2/21.
 */
public abstract class Ability {
    private int id;
    private float cooldown;
    private float cd;

    public Ability(){
        init();
    }
    public abstract void init();

    public void init(int id,float cooldown){
        this.id=id;
        this.cooldown=cooldown;
        cd=0;
    }

    public void update(long dt){
        if(cd>0)
            cd-=dt/1000;
        else
            cd=0;
    }

    public void cast(){
        cd=cooldown;
    }

    public boolean isCd(){
        return cd==0;
    }

    public boolean ruok(int state){
        return false;
    }
    public int getId() {
        return id;
    }
}
