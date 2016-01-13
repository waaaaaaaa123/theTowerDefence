package com.example.waaaaaaaa123.thetowerdefence;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.example.waaaaaaaa123.thetowerdefence.block.Block;
import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Enemy;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;
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
    private Player player;
    private HashMap<Integer,Drawable> blockPaints;
    private float blockLength;
    private Grid grid;
    private HashMap<Integer,Drawable> enemyPaints;
    private HashMap<Integer,Rect> enemyRects;
    private TowerManager towerManager;
    private HashMap<Integer,Drawable> towerPaints;
    private ProjectileManager projectileManager;
    private HashMap<Integer,Drawable> projectilePaints;

    private Wave wave;
    public DrawableManager(Context context,Player player,Grid grid,TowerManager towerManager,ProjectileManager projectileManager){
        this.context=context;
        this.player=player;
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
        blockPaints.put(0, context.getResources().getDrawable(R.drawable.block));
        blockPaints.put(1, context.getResources().getDrawable(R.drawable.build));
        blockPaints.put(2, context.getResources().getDrawable(R.drawable.build));
        enemyPaints.put(0, context.getResources().getDrawable(R.drawable.dummy));
        enemyRects.put(0, new Rect(0, 0, 50, 50));
        towerPaints.put(0, context.getResources().getDrawable(R.drawable.tower));
        projectilePaints.put(0, context.getResources().getDrawable(R.drawable.projectile));
    }

    public void draw(Canvas canvas){
        drawBackground(canvas);
        drawBlock(canvas);
        drawTower(canvas);
        drawEnemy(canvas);
        drawProjectile(canvas);
        drawPlayer(canvas);
    }
    public void drawBackground(Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }
    public void drawPlayer(Canvas canvas){
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(35);;
        Paint.FontMetrics fontMetrics=paint.getFontMetrics();
        float f=fontMetrics.bottom-fontMetrics.top;
        canvas.drawText("GOLD " + player.getGold(), player.getTopRect().left, player.getTopRect().top + f, paint);
        canvas.drawText("HP " + player.getHp(), player.getTopRect().left, player.getTopRect().top + f * 2, paint);


        Drawable drawable=context.getResources().getDrawable(R.drawable.tower);
        Rect rect=new Rect();
        for(Item item:player){
            //drawable=.get(item.getId());
            if(drawable!=null){
                if(item.isShowRange()){
                    if(item.isUseable())
                        paint.setColor(Color.GREEN);
                    else
                        paint.setColor(Color.RED);
                    paint.setAlpha(64);
                    canvas.drawCircle(item.getRect().centerX(),item.getRect().centerY(),item.getRange(),paint);
                }
                item.getRect().round(rect);
                drawable.setBounds(rect);
                drawable.draw(canvas);
            }
        }
    }
    public void drawBlock(Canvas canvas){
        Drawable drawable;
        Rect rect=new Rect();
        for(Block block:grid){
           drawable=blockPaints.get(block.getId());

            if(drawable!=null){
                block.getRect().round(rect);
                drawable.setBounds(rect);
                drawable.draw(canvas);
            }
        }
    }
    public void drawEnemy(Canvas canvas){
        Drawable drawable;
        Rect rect=new Rect();

        Paint p1=new Paint();
        Paint p2=new Paint();
        Paint p3=new Paint();
        p1.setColor(Color.BLACK);
        p2.setColor(Color.RED);
        p3.setColor(Color.GREEN);
        RectF hpbar=new RectF();

        for(PointF pointF:wave.getPath()){
            canvas.drawCircle(pointF.x,pointF.y,10,p3);
        }

        for(Enemy enemy:wave){
            if(enemy.getState()==Enemy.STATE_ALIVE){
            drawable=enemyPaints.get(enemy.getId());
            if(drawable!=null){
                enemy.getRect().round(rect);
                drawable.setBounds(rect);
                drawable.draw(canvas);

                hpbar.set(rect.left, rect.top - 20, rect.right, rect.top - 10);
                canvas.drawRect(hpbar, p1);
                hpbar.set(hpbar.left + 3, hpbar.top +3, hpbar.right - 3, hpbar.bottom - 3);
                canvas.drawRect(hpbar, p2);
                hpbar.set(hpbar.left,hpbar.top,hpbar.left+enemy.getHpPercent()* hpbar.width(),hpbar.bottom);
                canvas.drawRect(hpbar,p3);
            }
        }
        }
    }
    public void drawTower(Canvas canvas){
        Drawable drawable;
        Rect rect=new Rect();
        for(Tower tower:towerManager){
            drawable=towerPaints.get(tower.getId());

            if(drawable!=null){
                tower.getRect().round(rect);
                drawable.setBounds(rect);
                drawable.draw(canvas);
            }
        }
    }
    public void drawProjectile(Canvas canvas){
        Drawable drawable;
        Rect rect=new Rect();
        for(Projectile projectile:projectileManager){
            if(projectile.getState()!=Projectile.STATE_ALIVE) continue;
            drawable=projectilePaints.get(projectile.getId());

            if(drawable!=null){
               projectile.getRect().round(rect);
                drawable.setBounds(rect);
                drawable.draw(canvas);
            }
        }
    }
}
