package com.example.waaaaaaaa123.thetowerdefence.projectile;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;

import java.util.HashMap;

import static java.lang.Math.*;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public abstract class Projectile implements Comparable<Projectile> {

    public static final int PROJECTILE_ORB=0;
    public static final int PROJECTILE_AXE=1;
    public static final int PROJECTILE_WHIP=2;
    public static final int PROJECTILE_SWORD=3;
    public static final int PROJECTILE_BOMB=4;
    public static final int PROJECTILE_CONE=5;
    public static final int PROJECTILE_SPLIT=6;
    public static final int PROJECTILE_BURN=7;
    public static final int PROJECTILE_RIFLE=8;
    public static final int PROJECTILE_ROTATE=9;
    public static final int PROJECTILE_CHAIN=10;
    public static final int PROJECTILE_COMBO=11;

    public static final int STATE_SPAWN=0;
    public static final int STATE_ALIVE=1;
    public static final int STATE_DYING=2;
    public static final int STATE_DEAD=3;

    private int id;
    private int state=STATE_ALIVE;

    //private PointF point;
    private PointF forward;
    private RectF rect;
    private float degree;

    protected Tower caster;
    protected Enemy target;
    protected PointF targetPoint;
    private int impetus=0;

    private float speed=5;
    private float damage=10;

    private HashMap<Integer,Integer> modifiers;

    public Projectile(Tower caster,Enemy target){
        rect=new RectF();
        //point=new PointF();
        targetPoint=new PointF();
        forward=new PointF();
        modifiers=new HashMap<>(4);
        init();
        recycle(caster,target);
    }


    public Projectile(Tower caster){

    }
    public abstract void init();

    public void init(int id,float speed){
        this.id=id;
    }
    public void recycle(Tower caster,Enemy target){
        this.caster=caster;
        this.target=target;

        degree=caster.getDegree();
        double radians= toRadians(degree);
        forward.set((float) cos(radians),(float) sin(radians));

        speed=caster.getProjectileSpeed();
        float l=Grid.getLength()*0.5f;
        rect.set(-l / 2, -l / 3, l / 2, l / 3);
        rect.offset(caster.getBlock().getRect().centerX(), caster.getBlock().getRect().centerY());
        //point.set(caster.getPoint());
        initTargetPoint();

        aim(targetPoint);
        rect.offset(caster.getRect().width()/2*forward.x,caster.getRect().width()/2*forward.y);
        rect.offset(rect.width()/2*forward.x,rect.width()/2*forward.y);
        modifiers.clear();
        impetus=0;
        state=STATE_SPAWN;
    }

    public void split(int n,int i){

        float l=(n-1)/2.0f*(rect.height()+rect.height()*0.8f)-i*(rect.height()+rect.height()*0.8f);
        rect.offset(0,l*forward.x);

    }
    public void addImpetus(){
        impetus++;
    }

    public void initTargetPoint(){
        if(target!=null)
            targetPoint.set(target.getPoint());
        else{
            float l=caster.getRange()*Grid.getLength()-caster.getBlock().getRect().width()/2-rect.width()/2;
            targetPoint.set(rect.centerX()+forward.x*l,rect.centerY()+forward.y*l);
        }

    }

    public HashMap<Integer, Integer> getModifiers() {
        return modifiers;
    }

    public void addModifier(int mId){

        if(modifiers.containsKey(mId))
            modifiers.put(mId,modifiers.get(mId)+1);
        else
            modifiers.put(mId,1);

    }

    public int getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public RectF getRect() {
        //rect.offsetTo(point.x-rect.width()/2,point.y-rect.height()/2);
        return rect;
    }


    public float getDamage() {
        return damage;
    }

    public Tower getCaster() {
        return caster;
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setDamage(float damage) {

        /*float d=damage/caster.getDamage();
        rect.set(-10*d,-10*d,10*d,10*d);*/
        this.damage = damage;
    }
    public void strike(float m){
        damage*=m;
    }
    public void setStateAlive(){
        state=STATE_ALIVE;

    }
    public float getDegree() {
        return degree;
    }

    /*public boolean isHit(float l,PointF to){

        return l>=dl;
    }*/
    private float aim(PointF to){
        double dx=to.x-rect.centerX();
        double dy=to.y-rect.centerY();
        double dl= sqrt(dx * dx + dy * dy);
        if(target!=null){
            forward.set((float)(dx/dl),(float)(dy/dl));
            degree= (float) (toDegrees(atan2(forward.y, forward.x)));
        }
        return (float) dl;
    }
    public void onHit()
    {
        if(target!=null){
            target.attackLanded(this);
        }
            state=STATE_DEAD;
    }
    public void checkTargetPoint(){
        if(target!=null)
            targetPoint.set(target.getPoint());
    }

    public boolean isInRange(Enemy enemy){
        return enemy.getRect().intersects(rect.left,rect.top,rect.right,rect.bottom);
    }
    public boolean isInRange(Enemy enemy,float range){

        //return enemy.getRect().intersects(rect.left,rect.top,rect.right,rect.bottom);
        PointF p=enemy.getPoint();
        float r=enemy.getRect().width()/2+range* Grid.getLength();

        float dx=p.x-rect.centerX();
        float dy=p.y-rect.centerY();
        return dx*dx+dy*dy<=r*r;
    }
    private void onImpetus(float l){
        damage*=1+l*impetus*0.1f;
        Log.i("dmg",damage+"");
    }
    public void move(long dt){

        float l= speed*dt/1000;
        if(impetus>0){
            onImpetus(l);
        }
        l*=Grid.getLength();
        /*l+=getRect().width()/2+target.getRect().width()/2;*/

        checkTargetPoint();
        float dl=aim(targetPoint);
        rect.offset(l*forward.x,l*forward.y);
        /*point.x+=l*forward.x;
        point.y+=l*forward.y;*/
        if(target!=null&&target.getState()==Enemy.STATE_ALIVE) {
            if(isInRange(target))
                onHit();
        }
        else
            if(l>=dl)
                onHit();
        /*degree+=0.3*l*360/(2*Math.PI*10);
        if(degree>360) degree-=360;*/

        /*if (isHit(l,targetPoint)){
            onHit();
        }
        else{
            point.x+=l*forward.x;
            point.y+=l*forward.y;
        }*/

    }
    public void update(long dt){
        move(dt);
    }

    @Override
    public int compareTo(Projectile another) {
        return Float.compare(this.getRect().centerY(),another.getRect().centerY());
    }
}
