package com.example.waaaaaaaa123.thetowerdefence.projectile;

import android.graphics.Point;
import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;
import com.example.waaaaaaaa123.thetowerdefence.util.Vec;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Projectile {
    public static final int STATE_SPAWN=0;
    public static final int STATE_ALIVE=1;
    public static final int STATE_DEAD=2;

    private int id;
    private Tower caster;
    private Enemy target;
    private Point point;
    private Vec pVec;
    private Vec forward;
    private int speed=5;
    private float size=1;
    private int state=STATE_ALIVE;
    public Projectile(Tower caster,Enemy target){
        this.caster=caster;
        this.target=target;

        point=new Point(caster.getPoint());
        pVec=new Vec(point);
        forward=new Vec();
        setSpeed();

    }
    public void recycle(Tower caster,Enemy target){
        this.caster=caster;
        this.target=target;

        point.x=caster.getPoint().x;
        point.y=caster.getPoint().y;
        pVec.x=point.x;
        pVec.y=point.y;
        setSpeed();
        state=STATE_ALIVE;
    }
    public void setSpeed() {
        speed=5* Grid.getLength();
    }

    public int getSize() {
        return (int) (size*Grid.getLength());
    }

    public int getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public Point getPoint() {
        point.x= (int) pVec.x;
        point.y= (int) pVec.y;
        return point;
    }

    public boolean isHit(int l){
        double dx=target.getPoint().x-point.x;
        double dy=target.getPoint().y-point.y;
        double dl=Math.sqrt(dx*dx+dy*dy);
        forward.x= (float) (dx/dl);
        forward.y= (float) (dy/dl);
        return l>dl;
    }
    public void move(long dt){

        int l= (int) (speed*dt/1000);

        if (isHit(l)){
            state=STATE_DEAD;
        }
        else{
            pVec.x+=l*forward.x;
            pVec.y+=l*forward.y;
        }

    }
    public void update(long dt){
        if(state==STATE_DEAD)return;
        move(dt);
    }
}
