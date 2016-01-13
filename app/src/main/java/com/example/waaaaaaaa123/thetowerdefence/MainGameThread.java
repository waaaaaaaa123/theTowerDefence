package com.example.waaaaaaaa123.thetowerdefence;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class MainGameThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private boolean running=false;
    private Grid grid;
    private RectF rect;
    private RectF topRect,mainRect,bottomRect;
    private Wave wave;
    private Context context;
    private long lastTime;
    private DrawableManager drawableManager;
    private TowerManager towerManager;
    private ProjectileManager projectileManager;
    private Player player;
    private MyGestureListener myGestureListener;

    public MainGameThread(SurfaceHolder surfaceHolder,Context context,Handler handler){
        this.surfaceHolder=surfaceHolder;
        this.context=context;
    }
    public void init(float left,float top,float right,float bottom){
        this.rect=new RectF(left,top,right,bottom);
        float l=(rect.height()-rect.width())/2;
        l=l<0?-l:l;
        topRect=new RectF(rect.left,rect.top,rect.right,rect.top+l);
        mainRect=new RectF(rect.left,rect.top+l,rect.right,rect.bottom-l);
        bottomRect=new RectF(rect.left,rect.bottom-l,rect.right,rect.bottom);
        grid=new Grid(mainRect,10,10);
        player=new Player(topRect,bottomRect);

        towerManager=new TowerManager();
        towerManager.setGrid(grid);
        projectileManager=new ProjectileManager();
        Tower.setProjectileManager(projectileManager);
        drawableManager=new DrawableManager(context,player,grid,towerManager,projectileManager);
        wave=new Wave(0);
        wave.setGrid(grid);

        Tower.setWave(wave);
        for(int i=1;i<9;i+=2)
            for(int j=i%3;j<i%3+7;j++){
                grid.setBlockId(i,j,Block.BUILD);
            }
        Item.setGrid(grid);
        Item.setTowerManager(towerManager);
        Item.setPlayer(player);
        for(int i=0;i<10;i++)
        {
            player.addItem(new Item());
        }
        towerManager.addTower(grid.getBlock(1,1).getRect());
        towerManager.addTower(grid.getBlock(3,3).getRect());
        towerManager.addTower(grid.getBlock(5,5).getRect());
        towerManager.addTower(grid.getBlock(7,7).getRect());


        wave.init();
        wave.addEnemy(10);

        drawableManager.setWave(wave);
        myGestureListener.init(grid,player,towerManager,projectileManager);
        myGestureListener.setWave(wave);

    }

    public void setMyGestureListener(MyGestureListener myGestureListener) {
        this.myGestureListener = myGestureListener;
    }
/*private float lastX,lastY;
    public static final int EVENTSTATE_NO=0;
    public static final int EVENTSTATE_DRAGBAR=1;
    public static final int EVENTSTATE_SELECTITEM=2;
    private int eventState=0;
    private MotionEvent motionEvent;
    public void setMotionEvent(MotionEvent motionEvent) {
        this.motionEvent = motionEvent;
        Log.i("motionEvent", motionEvent.toString());
        float x= motionEvent.getX(0);
        float y= motionEvent.getY(0);
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(x>=bottomRect.left&&x<=bottomRect.right&&y>=bottomRect.top&&y<=bottomRect.top+player.getBagHeight()){
                    eventState=EVENTSTATE_DRAGBAR;

                }
                break;
            case MotionEvent.ACTION_MOVE:
                switch (eventState){
                    case EVENTSTATE_DRAGBAR:
                        if(x==lastX&&y==lastY)
                            eventState=EVENTSTATE_SELECTITEM;
                        else
                            player.setOffset((int) (x-lastX));
                        break;
                    case EVENTSTATE_SELECTITEM:

                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
                eventState=EVENTSTATE_NO;
                break;
        }
        lastX=x;lastY=y;
    }*/

    public void setRunning(boolean running) {
        this.running = running;
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
                    towerManager.update(dt);
                    wave.update(dt);
                    projectileManager.update(dt);
                    lastTime=System.currentTimeMillis();
                    drawableManager.draw(canvas);

                }
            }catch (Exception e){
                e.printStackTrace();

            }finally {
                if(canvas!=null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
