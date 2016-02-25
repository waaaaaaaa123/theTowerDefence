package com.example.waaaaaaaa123.thetowerdefence;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.button.NewGameButton;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.item.Bag;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class MainGameThread extends Thread implements View.OnTouchListener {
    private SurfaceHolder surfaceHolder;
    private boolean running=false;
    private Context context;
    private long lastTime=0;
    private boolean hasMotionEvent=false;
    private RectF rect;

    private Player player;
    private DrawableManager drawableManager;
    private MyGestureListener myGestureListener;
    private GestureDetector gestureDetector;
    public MainGameThread(SurfaceHolder surfaceHolder,Context context,Handler handler){
        this.surfaceHolder=surfaceHolder;
        this.context=context;

        myGestureListener=new MyGestureListener();
        gestureDetector = new GestureDetector(context, myGestureListener);
        gestureDetector.setIsLongpressEnabled(false);

        NewGameButton.setMainGameThread(this);

    }
    public void init(float left,float top,float right,float bottom){
        this.rect=new RectF(left,top,right,bottom);
        init();
    }

    public void init(){
        player=new Player(rect);

        drawableManager=new DrawableManager(context,player);


        drawableManager.setWave(player.getWave());
        myGestureListener.init(player);
        myGestureListener.setWave(player.getWave());

    }



    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isHasMotionEvent() {
        return hasMotionEvent;
    }


    @Override
    public void run() {

        lastTime=System.currentTimeMillis();

        while(running){
            Canvas canvas=null;
            long dt=System.currentTimeMillis()-lastTime;
            try{

                canvas=surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder){
                    player.update(dt);
                    if(dt>0)
                        Log.v("fps",1000/dt+"");
                    lastTime=System.currentTimeMillis();
                    drawableManager.draw(canvas);
                    //hasMotionEvent=false;
                }
            }catch (Exception e){
                e.printStackTrace();

            }finally {
                if(canvas!=null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (lastTime==0)
            return false;
        synchronized (surfaceHolder) {
            if (event.getAction() == MotionEvent.ACTION_UP)
                myGestureListener.onUp(event);

            return gestureDetector.onTouchEvent(event);
        }
    }
}
