package com.example.waaaaaaaa123.thetowerdefence;

import android.graphics.RectF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.waaaaaaaa123.thetowerdefence.block.Grid;
import com.example.waaaaaaaa123.thetowerdefence.enemy.Wave;
import com.example.waaaaaaaa123.thetowerdefence.item.Item;
import com.example.waaaaaaaa123.thetowerdefence.projectile.ProjectileManager;
import com.example.waaaaaaaa123.thetowerdefence.tower.TowerManager;

/**
 * Created by aa081_000 on 2016/1/13.
 */
public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int STATE_NONE=0;
    private static final int STATE_TOP=1;
    private static final int STATE_MAIN=2;
    private static final int STATE_BOTTOM=3;
    private static final int STATE_SCROLLBAG =4 ;
    private static final int STATE_SELLECTITEM =5 ;

    private int state;
    private Grid grid;
    private Player player;
    private TowerManager towerManager;
    private ProjectileManager projectileManager;
    private Wave wave;
    private RectF topRect,mainRect,bottomRect;
    private Item item;
    public void init(Grid grid,Player player,TowerManager towerManager,ProjectileManager projectileManager){
        this.grid=grid;
        this.player=player;
        this.towerManager=towerManager;
        this.projectileManager=projectileManager;
        topRect=player.getTopRect();
        bottomRect=player.getBottomRect();
        mainRect=grid.getRect();
    }
    public void setWave(Wave wave){
        this.wave=wave;
    }

    public void onUp(MotionEvent event) {
        Log.i("event",event.getAction()+"");
        switch (state){
            case STATE_SELLECTITEM:
                if(item.isUseable()){
                    item.setState(Item.STATE_USE);

                }
                else
                    item.setState(Item.STATE_INBAG);
                item=null;

                break;
        }
        state=STATE_NONE;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        switch (state){
            case STATE_BOTTOM:
                if(Math.abs(distanceX)>Math.abs(distanceY))
                    state=STATE_SCROLLBAG;
                else
                    state=STATE_SELLECTITEM;
                break;
            case STATE_SCROLLBAG:player.scrollBag(-distanceX);break;
            case STATE_SELLECTITEM:
                if(item!=null){
                    item.onSellect(e2.getX(0),e2.getY(0));
                }

                break;
        }
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.i("onDoubleTap","3");
        return super.onDoubleTap(e);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.i("onDoubleTapEvent","4");
        return super.onDoubleTapEvent(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i("onDown","5");
        float x=e.getX(0);
        float y=e.getY(0);

        if(topRect.contains(x,y)) state=STATE_TOP;
        if(mainRect.contains(x,y))state=STATE_MAIN;
        if(bottomRect.contains(x,y))state=STATE_BOTTOM;

        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("onFling","6");
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.i("onSingleTapConfirmed","7");
        return super.onSingleTapConfirmed(e);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.i("onSingleTapUp","8");
        return super.onSingleTapUp(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.i("onLongPress",e.getX(0)+" "+e.getY(0));
        super.onLongPress(e);
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.i("onShowPress",e.getX(0)+" "+e.getY(0));
        switch (state){
            case STATE_BOTTOM:state=STATE_SELLECTITEM;
                if(item==null)
                    item=player.sellectItem(e.getX(0),e.getY(0));break;
        }
        super.onShowPress(e);
    }
}
