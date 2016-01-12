package com.example.waaaaaaaa123.thetowerdefence.util;

import android.graphics.Point;

/**
 * Created by aa081_000 on 2016/1/11.
 */
public class Vec {
    public float x;
    public float y;

    public Vec(){}
    public Vec(Point p){setVec(p);}
    public void setVec(Point p){
        x=p.x;y=p.y;
    }
    public boolean equals(Point p) {
        if(p==null)return false;
        if(Math.round(x)==p.x&&Math.round(y)==p.y)
        {
            setVec(p);
            return true;
        }
        else{
            return false;
        }
    }
}