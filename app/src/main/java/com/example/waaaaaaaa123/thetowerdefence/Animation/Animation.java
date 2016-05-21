package com.example.waaaaaaaa123.thetowerdefence.Animation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.waaaaaaaa123.thetowerdefence.Player;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;

/**
 * Created by aa081_000 on 2016/5/3.
 */
public class Animation {
    public RectF rect;
    public float duration;
    public float offX,offY,insetX,insetY;
    //public int a,r,g,b;
    public int color;
    private int i;
    private long timer;
    private Paint paint;
    public Animation(){
        paint=new Paint();
        rect=new RectF();
    }
    public void set(RectF rectF){
        duration=Player.getRandomSeed().nextFloat()*2;
        timer=0;

        float l= rectF.width()*Player.getRandomSeed().nextFloat()*0.5f;
        rect.set(-l / 2, -l / 2, l / 2, l / 2);
        rect.offset(rectF.centerX(), rectF.centerY());
        l*=(Player.getRandomSeed().nextFloat()-0.5f)*0.5f;
        rect.inset(l, l);
        rect.offset(rectF.width() * 0.5f * (Player.getRandomSeed().nextFloat() - 0.5f) * 2, rectF.height() * 0.5f * (Player.getRandomSeed().nextFloat() - 0.5f) * 2);

        offX= (Player.getRandomSeed().nextFloat()-0.5f)*2*rect.width()*3;
        offY=(Player.getRandomSeed().nextFloat()-0.5f)*2*rect.height()*3;
        insetX=(Player.getRandomSeed().nextFloat()-0.5f)*2*rect.width()*0.5f;
        insetY=insetX;
        paint.setColor(Color.GRAY);
        /*
        if (Player.getRandomSeed().nextFloat()>0.5f){
            color=Color.BLACK;
        }
        else{
            color=Color.WHITE;
        }
        color=Color.argb(Player.getRandomSeed().nextInt(256),Color.red(color),Color.green(color),Color.blue(color));*/
        color=Color.argb(Player.getRandomSeed().nextInt(256),Player.getRandomSeed().nextInt(256),Player.getRandomSeed().nextInt(256),Player.getRandomSeed().nextInt(256));

        paint.setColor(color);
        color=Color.argb(Player.getRandomSeed().nextInt(256),Player.getRandomSeed().nextInt(256),Player.getRandomSeed().nextInt(256),Player.getRandomSeed().nextInt(256));

    }
    public void update(long dt){
        timer+=dt;
        if(timer<duration*1000){
            float off=dt/(duration*1000);
            rect.offset(offX * off, offY * off);
            rect.inset(insetX * off, insetY * off);
            //color=Color.argb((int) ((Color.alpha(color)+a*off)%256),(int)((Color.red(color)+r*off)%256),(int)((Color.green(color)+g*off)%256), (int) ((Color.blue(color)+b*off)%256));
            //paint.setColor(color);
            i++;
            if(i%5==0){
                i=0;
                off=timer/(duration*1000);
                //paint.setColor((int) (off * (color - paint.getColor())));
                int p=paint.getColor();
                int a=Color.alpha(p),r=Color.red(p),g=Color.green(p),b=Color.blue(p);
                a+=off*(Color.alpha(color)-a);
                r+=off*(Color.red(color)-r);
                g+=off*(Color.green(color)-g);
                b+=off*(Color.blue(color)-b);
                paint.setColor(Color.argb(a,r,g,b));
            }
        }
    }
    public boolean isAlive(){
        return timer<duration*1000;
    }
    public void draw(Canvas canvas){
        canvas.drawRect(rect,paint);
    }
}
