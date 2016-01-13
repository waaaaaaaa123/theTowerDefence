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

/**
 * Created by aa081_000 on 2016/1/7.
 */
public class Block {
    public static final int BASE=0;
    public static final int BUILD=1;
    public static final int TOWER=2;
    public static final int START=3;
    public static final int END=4;

    private int id=0;
    private RectF rect;
    private PointF center;



    public Block(RectF rect){
        this.rect=rect;
        center=new PointF(rect.centerX(),rect.centerY());
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public PointF getCenter() {
        return center;
    }

    public RectF getRect() {
        return rect;
    }
}
