package com.example.waaaaaaaa123.thetowerdefence.Animation;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/5/17.
 */
public class Animator {
    private ArrayList<Animation> animations;
    private RectF rect;
    private boolean dying;
    private int n;
    private float p;
    public Animator(RectF rect,float p){
        this.rect=rect;
        this.p=p;
        n= (int) (rect.width()/ Grid.getLength()*p);
        animations= new ArrayList<>(n*2);
        for (int i = 0; i < n*2; i++) {
            Animation animation=new Animation();
            if(i<n)
                animation.set(rect);
            animations.add(animation);
        }
    }
    public void recycle(){
        dying=false;
        n= (int) (rect.width()/ Grid.getLength()*p);
        for (int i = 0; i < n; i++) {
            if(i>=animations.size())
                animations.add(new Animation());
            animations.get(i).set(rect);
        }
    }
    public void update(long dt){
        /*if(projectile.getState()==Projectile.STATE_DYING&&!dying){
            dying=true;
            int m=Player.getRandomSeed().nextInt(20-n)+n;
            for (int i = n; i <m ; i++) {
                animations.get(i).set(projectile.getRect(),projectile.getTimerF());
            }
            n=m;
        }*/

        int m= (int) (rect.width()/Grid.getLength()*p);
        for (int i = n; i < m; i++) {
            if(i>=animations.size())
                animations.add(new Animation());
            animations.get(i).set(rect);
            n++;
        }

        for (int i=0;i<n;i++) {
            Animation animation=animations.get(i);
            animation.update(dt);
            if(!animation.isAlive()){
                animations.get(i).set(rect);
            }
        }
    }

    public void draw(Canvas canvas){
        for (int i=0;i<n;i++) {
            Animation animation=animations.get(i);
            if(animation.isAlive())
                animation.draw(canvas);
        }
    }

}
