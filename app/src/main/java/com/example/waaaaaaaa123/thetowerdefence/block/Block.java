package com.example.waaaaaaaa123.thetowerdefence.block;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;

import com.example.waaaaaaaa123.thetowerdefence.Player;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Block {
    public static final int BASE=0;
    public static final int BUILD=1;
    public static final int TOWER=2;
    public static final int START=3;
    public static final int END=4;
    public static final int CHECK=5;
    public static final int DAMAGE=6;

    private int id=0;
    private RectF rect;
    private PointF center;
    private int color=0x00FFFFFF;

    private long timer=0;
    private long duration;

    public Block(RectF rect){
        this.rect=rect;
        center=new PointF(rect.centerX(),rect.centerY());
    }

    public void update(long dt){
        //updateColor(dt);
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getColor() {
        return color;
    }


    private void updateColor(long dt){
        timer-=dt;

        if(timer>0){
            //float off=(duration-timer)/duration;
            float off=timer/duration;
            int a=Color.alpha(color),r=Color.red(color),g=Color.green(color),b=Color.blue(color);
            a= (int) (off*(0-a));
            r= (int) (off*(0xFF-r));
            g= (int) (off*(0xFF-g));
            b= (int) (off*(0xFF-b));
            /*a+=off*(0-a);
            r+=off*(0xFF-r);
            g+=off*(0xFF-g);
            b+=off*(0xFF-b);*/
            color=Color.argb(a,r,g,b);

            long nt=100;
            long t= timer-nt;
            if(timer>nt){
                off=t/duration;
                a= (int) (off*(0-a));
                r= (int) (off*(0xFF-r));
                g= (int) (off*(0xFF-g));
                b= (int) (off*(0xFF-b));
                int rc=Color.argb(a,r,g,b);
                Grid grid=Player.getGrid();
                Point c=grid.getBlockCount(this);
                if(c.x-1>=0)grid.getBlockByCount(c.x-1,c.y).setColor(rc,t);
                if(c.x+1<grid.xSize)grid.getBlockByCount(c.x+1,c.y).setColor(rc,t);
                if(c.y-1>=0) grid.getBlockByCount(c.x, c.y - 1).setColor(rc,t);
                if(c.y+1<grid.ySize) grid.getBlockByCount(c.x,c.y+1).setColor(rc,t);
            }

        }
        else{
            color=0x00FFFFFF;
            timer= 0;
        }
    }
    public void setColor(int color,long duration) {
        if(duration>=timer){

            this.color = color;
            timer=duration;
            this.duration=duration;
        }
    }

    public PointF getCenter() {
        return center;
    }

    public RectF getRect() {
        return rect;
    }
}
