package com.example.waaaaaaaa123.thetowerdefence.ability;

/**
 * Created by aa081_000 on 2016/2/21.
 */
public abstract class Ability {
    private int id;
    protected int state;
    private float cooldown;
    private long timer;

    public Ability(){
        init();
    }
    public abstract void init();

    public void init(int id,int state,float cooldown){
        this.id=id;
        this.state=state;
        this.cooldown=cooldown;
        timer=0;
    }

    public void update(long dt){
            timer-=dt;
        if(timer<=0){
            timer=0;
        }
    }

    public void cast(int state){
        if(this.state==state&&timer==0){
            if(cast())
                timer= (long) (cooldown*1000);
        }
    }
    protected abstract boolean cast();

    ;
    public boolean isCd(){
        return timer==0;
    }

    public int getId() {
        return id;
    }
}
