package com.example.waaaaaaaa123.thetowerdefence.dialog;

import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.waaaaaaaa123.thetowerdefence.Player;

import java.util.ArrayList;

/**
 * Created by aa081_000 on 2016/1/25.
 */
public class Dialog {
    public static final int DIALOG_BASE=0;
    public static final int DIALOG_ITEM=1;
    public static final int DIALOG_INFO=2;
    private int id;
    protected RectF rect;
    private ArrayList<String> text;
    public Dialog(){
        text=new ArrayList<>();
        float l=Player.mainRect.width();
        rect=new RectF(-l/2,-l/2,l/2,l/2);
        rect.inset(l*0.1f,-l*0.1f);
        rect.offset(Player.mainRect.centerX(),Player.mainRect.centerY());
        init();
    }
    public void init(){
        init(0);
    }
    public void init(int id){
        this.id=id;

    }
    public RectF getRect() {
        return rect;
    }

    public int getId() {
        return id;
    }
    public void initText(){

    }
    public ArrayList<String> getText() {
        return text;
    }

    public void onClick(MotionEvent e){

    }
    public void onNotClick(MotionEvent e){
        Player.getDialogs().pop();
    }
}
