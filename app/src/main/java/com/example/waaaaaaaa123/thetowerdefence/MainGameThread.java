package com.example.waaaaaaaa123.thetowerdefence;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
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
    private Rect rect;
    private Wave wave;
    private Context context;
    private long lastTime;
    private DrawableManager drawableManager;
    private TowerManager towerManager;
    private ProjectileManager projectileManager;
    private MotionEvent motionEvent;
    public MainGameThread(SurfaceHolder surfaceHolder,Context context,Handler handler){
        this.surfaceHolder=surfaceHolder;
        this.context=context;
    }
    public void init(Rect rect){
        this.rect=rect;
        grid=new Grid(rect,9,12);
        towerManager=new TowerManager();
        projectileManager=new ProjectileManager();
        Tower.setProjectileManager(projectileManager);
        drawableManager=new DrawableManager(context,grid,towerManager,projectileManager);
        wave=new Wave();
        Tower.setWave(wave);
        for(int i=1;i<9;i+=2)
            for(int j=i%3;j<i%3+7;j++){
                grid.setBlockId(i,j,Block.BUILD);
            }


        towerManager.addTower(new Tower(grid.getBlock(1,5)));
        towerManager.addTower(new Tower(grid.getBlock(3,5)));
        towerManager.addTower(new Tower(grid.getBlock(5,3)));
        towerManager.addTower(new Tower(grid.getBlock(7,7)));

        ArrayList<Point> path=grid.buildPath(0,0,8,8,1);

        for(int i=0;i<5;i++)
        {
            Enemy enemy=new Enemy(path);
            enemy.setSpeed(grid.getLength());
            enemy.setPath(path);
            wave.addEnemy(enemy);
        }

        drawableManager.setWave(wave);
    }

    public void setMotionEvent(MotionEvent motionEvent) {
        this.motionEvent = motionEvent;
        Log.i("motionEvent",motionEvent.toString());
    }

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
