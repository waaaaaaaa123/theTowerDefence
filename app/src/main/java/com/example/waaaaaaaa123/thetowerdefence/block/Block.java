package com.example.waaaaaaaa123.thetowerdefence.block;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Block {
    public static final int BASE=0;
    public static final int BUILD=1;
    public static final int START=2;
    public static final int END=3;

    private int id=0;
    private Point point;


    public Block(int x,int y){
        point=new Point(x,y);
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Point getPoint() {
        return point;
    }

}
