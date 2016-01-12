package com.example.waaaaaaaa123.thetowerdefence;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.projectile.Projectile;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.Tower;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;

import java.util.HashMap;

/**
 * Created by aa081_000 on 2016/1/9.
 */
public class DrawableManager {
    private Context context;
    private HashMap<Integer,Drawable> blockPaints;
    private int blockLength;
    private Grid grid;
    private HashMap<Integer,Drawable> enemyPaints;
    private HashMap<Integer,Rect> enemyRects;
    private TowerManager towerManager;
    private HashMap<Integer,Drawable> towerPaints;
    private ProjectileManager projectileManager;
    private HashMap<Integer,Drawable> projectilePaints;

    private Wave wave;
    public DrawableManager(Context context,Grid grid,TowerManager towerManager,ProjectileManager projectileManager){
        this.context=context;
        this.grid=grid;
        this.towerManager=towerManager;
        this.projectileManager=projectileManager;
        blockLength=grid.getLength();
        blockPaints=new HashMap<Integer,Drawable>();
        enemyPaints=new HashMap<Integer,Drawable>();
        enemyRects=new HashMap<Integer,Rect>();
        towerPaints=new HashMap<Integer,Drawable>();
        projectilePaints=new HashMap<Integer,Drawable>();

    }




    public void setWave(Wave wave) {
        this.wave = wave;
        blockPaints.put(0,context.getResources().getDrawable(R.drawable.block));
        blockPaints.put(1,context.getResources().getDrawable(R.drawable.build));
        enemyPaints.put(0,context.getResources().getDrawable(R.drawable.dummy));
        enemyRects.put(0,new Rect(0,0,50,50));
        towerPaints.put(0,context.getResources().getDrawable(R.drawable.tower));
        projectilePaints.put(0,context.getResources().getDrawable(R.drawable.projectile));
    }

    public void draw(Canvas canvas){
        drawBackground(canvas);
        drawBlock(canvas);
        drawTower(canvas);
        drawEnemy(canvas);
        drawProjectile(canvas);
    }
    public void drawBackground(Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }
    public void drawBlock(Canvas canvas){
        Drawable drawable;
        int left,right;
        for(Block block:grid){
           drawable=blockPaints.get(block.getId());

            if(drawable!=null){
                Point point=block.getPoint();
                left=(blockLength-1)/2;
                right=left%2==0?left:left+1;
                drawable.setBounds(point.x-left,point.y-left,point.x+right,point.y+right);
                drawable.draw(canvas);
            }
        }
    }
    public void drawEnemy(Canvas canvas){
        Drawable drawable;
        Rect rect;
        int leftWidth,rightWidth,topHeight,bottomHeight;
        for(Enemy enemy:wave){
            if(enemy.getState()>Enemy.STATE_NOTSPAWN){
            drawable=enemyPaints.get(enemy.getId());
            if(drawable!=null){
                rect=enemyRects.get(enemy.getId());
                Point point=enemy.getPoint();
                leftWidth=(rect.width()-1)/2;
                rightWidth=leftWidth%2==0?leftWidth:leftWidth+1;
                topHeight=(rect.height()-1)/2;
                bottomHeight=topHeight%2==0?topHeight:topHeight+1;
                drawable.setBounds(point.x - leftWidth, point.y - topHeight, point.x + rightWidth, point.y + bottomHeight);
                drawable.draw(canvas);
            }
        }
        }
    }
    public void drawTower(Canvas canvas){
        Drawable drawable;
        int left,right;
        for(Tower tower:towerManager){
            drawable=towerPaints.get(tower.getId());

            if(drawable!=null){
                Point point=tower.getPoint();
                left=(blockLength-1)/2;
                right=left%2==0?left:left+1;
                drawable.setBounds(point.x-left,point.y-left,point.x+right,point.y+right);
                drawable.draw(canvas);
            }
        }
    }
    public void drawProjectile(Canvas canvas){
        Drawable drawable;
        int left,right;
        for(Projectile projectile:projectileManager){
            if(projectile.getState()!=Projectile.STATE_ALIVE) continue;
            drawable=projectilePaints.get(projectile.getId());

            if(drawable!=null){
                Point point=projectile.getPoint();
                left=(projectile.getSize()-1)/2;
                right=left%2==0?left:left+1;
                drawable.setBounds(point.x-left,point.y-left,point.x+right,point.y+right);
                drawable.draw(canvas);
            }
        }
    }
}
