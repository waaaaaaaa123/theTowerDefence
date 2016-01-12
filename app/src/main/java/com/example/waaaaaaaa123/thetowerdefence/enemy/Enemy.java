package com.example.waaaaaaaa123.thetowerdefence.enemy;

import android.graphics.Point;

import com.example.waaaaaaaa123.thetowerdefence.util.Vec;

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
    private int bound=1;

    private Vec forward;
    private Vec pVec;
    private Point point;
    private Point nextPoint;
    private ArrayList<Point> path;
    private float lengthToNextPath;
    private int it;
    private int state=STATE_NOTSPAWN;

    public Enemy(ArrayList<Point> path){
        setPath(path);
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

    public Point getPoint() {
        point.x= (int) pVec.x;
        point.y= (int) pVec.y;
        return point;
    }

    public void setSpeed(int blockLength) {
        speed*=blockLength;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
        point=new Point(path.get(0));
        pVec=new Vec(point);
        nextPoint=path.get(1);
        setForward();
        it=1;
    }

    public void setForward() {

        if(nextPoint==null)
        {
            forward=null;
            return;
        }
        double dx=nextPoint.x-pVec.x;
        double dy=nextPoint.y-pVec.y;
        double dl=Math.sqrt(dx*dx+dy*dy);
        dx/=dl;
        dy/=dl;
        forward=new Vec();
        forward.x=(float)dx;
        forward.y=(float)dy;
        lengthToNextPath=(float)dl;
    }
    private void move(long dt){

        if(forward==null|| pVec.equals(nextPoint))
        {
            if(it<path.size())
                nextPoint=path.get(it++);
            else
            nextPoint=null;
            setForward();
        }

        if(forward!=null){
            float l=  (speed*dt/1000);

            if( l>lengthToNextPath){
                pVec.setVec(nextPoint);
                move((long) (dt-lengthToNextPath/speed*1000));
            }
            else{
                pVec.x+=l*forward.x;
                pVec.y+=l*forward.y;
                lengthToNextPath-=l;
            }

        }

    }
    public void update(long dt){
     move(dt);
    }

}
