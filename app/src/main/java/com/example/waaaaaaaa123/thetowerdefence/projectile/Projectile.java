package com.example.waaaaaaaa123.thetowerdefence.projectile;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

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
    private PointF point;
    private PointF forward;
    private float speed=5;
    private float damage=10;
    private RectF rect;
    private int state=STATE_ALIVE;
    public Projectile(Tower caster,Enemy target){
        this.caster=caster;
        this.target=target;
        rect=new RectF(-10,-10,10,10);
        point=new PointF();
        point.set(caster.getPoint());
        forward=new PointF();
        setSpeed();

    }
    public void recycle(Tower caster,Enemy target){
        this.caster=caster;
        this.target=target;

        point.x=caster.getPoint().x;
        point.y=caster.getPoint().y;
        setSpeed();
        state=STATE_ALIVE;
    }
    public void setSpeed() {
        speed=5* Grid.getLength();
    }



    public int getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public RectF getRect() {
        rect.offsetTo(point.x-rect.width()/2,point.y-rect.height()/2);
        return rect;
    }

    public PointF getPoint() {

        return point;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public boolean isHit(int l){
        double dx=target.getPoint().x-point.x;
        double dy=target.getPoint().y-point.y;
        double dl=Math.sqrt(dx*dx+dy*dy);
        forward.x= (float) (dx/dl);
        forward.y= (float) (dy/dl);
        return l>=dl;
    }
    public void move(long dt){

        int l= (int) (speed*dt/1000);

        if (isHit(l)){
            target.attackLanded(this);
            state=STATE_DEAD;
        }
        else{
            point.x+=l*forward.x;
            point.y+=l*forward.y;
        }

    }
    public void update(long dt){
        move(dt);
    }
}
