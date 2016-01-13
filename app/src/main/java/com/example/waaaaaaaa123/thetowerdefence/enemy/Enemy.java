package com.example.waaaaaaaa123.thetowerdefence.enemy;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Enemy {

    public static final int STATE_NOTSPAWN=0;
    public static final int STATE_ALIVE=1;
    public static final int STATE_DEAD=2;

    private int id=0;
    private float speed=3;
    private float hp=100;
    private int bound=1;

    private PointF forward;
    private RectF rect;
    private PointF point;
    private PointF nextPoint;
    private ArrayList<PointF> path;
    private float lengthToNextPath;
    private int it;
    private int state=STATE_NOTSPAWN;

    public Enemy(){
        rect=new RectF(-25,-25,25,25);
        point=new PointF();
        forward=new PointF();

    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public int getBound() {
        return bound;
    }

    public PointF getPoint() {

        return point;
    }

    public RectF getRect() {
        rect.offsetTo(point.x-rect.width()/2,point.y-rect.height()/2);
        return rect;
    }

    public void setSpeed(float blockLength) {
        speed*=blockLength;
    }

    public void setPath(ArrayList<PointF> path) {
        this.path = path;

        point.set(path.get(0));

        nextPoint=path.get(1);
        setForward();
        it=1;
    }

    public void setForward() {
        double dx=nextPoint.x-point.x;
        double dy=nextPoint.y-point.y;
        double dl=Math.sqrt(dx*dx+dy*dy);
        dx/=dl;
        dy/=dl;

        forward.x=(float)dx;
        forward.y=(float)dy;
        lengthToNextPath=(float)dl;
    }
    private void move(long dt){

            float l=  (speed*dt/1000);

            if( l>=lengthToNextPath){
                point.set(nextPoint);
                if(it+1<path.size())
                {
                    nextPoint=path.get(++it);
                    long dtt=(long) (dt - lengthToNextPath / speed * 1000);
                    setForward();
                    move(dtt);
                }
                else
                {
                    state=STATE_DEAD;
                }

            }
            else{
                point.x+=l*forward.x;
                point.y+=l*forward.y;
                lengthToNextPath-=l;
            }



    }
    public void update(long dt){
     move(dt);
    }

    public float getHpPercent() {
        return hp/100;
    }

    public void attackLanded(Projectile projectile){
        hp-=projectile.getDamage();
        if(hp<=0)
        {
            hp=0;
            state=STATE_DEAD;
        }
    }
}
